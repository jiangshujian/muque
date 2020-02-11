package org.muque.mold.codes.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.muque.mold.codes.common.LoopFrag;
import org.muque.mold.codes.common.PlaceholderConst;
import org.muque.mold.codes.dao.SourceDao;
import org.muque.mold.codes.dto.SourceColumn;
import org.muque.mold.codes.dto.SourceTable;
import org.muque.mold.codes.service.GenCodesService;
import org.muque.mold.codes.util.CleanBomUtil;
import org.muque.mold.codes.util.FileUtil;
import org.muque.mold.codes.util.PathUtil;
import org.muque.mold.codes.util.StringUtil;
import org.muque.mold.codes.util.TxtReaderUtil;
import org.muque.mold.codes.util.TxtWriterUtil;

public class GenCodesServiceImpl implements GenCodesService {
	@Resource
	private SourceDao sourceDaoMysql;

	@Resource
	private HashMap<String, String> mysqlToJavaTypeMap;

	@Resource
	private HashMap<String, String> mysqlToJdbcTypeMap;

	@Resource
	private HashMap<String, LoopFrag> loopColumnFragMap;

	private String targetPath;

	private String databaseName;

	private String packageName;

	private static final String templetsTargetPath = PathUtil.getAppConfPath().concat(PathUtil.getSeparator())
			.concat("templets").concat(PathUtil.getSeparator()).concat("target");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mytools.auto_codes.service.impl.GenCodesService#excute()
	 */
	@Override
	public void excute() {
		try {
			// 获取模板文件列表
			System.out.println(">>>>>>模板读取开始....");
			List<File> templetTargetFiles = FileUtil.listAllFiles(templetsTargetPath);
			for (File f : templetTargetFiles) {
				if (f.isFile()) {
					System.out.println("模板文件=>" + f.getPath());
				} else {
					System.out.println("模板目录=>" + f.getPath());
				}
			}
			System.out.println(">>>>>>生成代码开始....");
			// 根据模板生成代码文件
			List<SourceTable> sourceTables = this.sourceDaoMysql.getTableList(this.databaseName);
			for (File tmplFile : templetTargetFiles) {
				this.genTargetFileByTempletFile(tmplFile, sourceTables);
			}

			System.out.println(">>>>>>生成代码结束....");
			System.out.println(">>>>>>清理Bom开始....");
			CleanBomUtil.cleanBom(targetPath);
			System.out.println(">>>>>>清理Bom结束....");
			System.out.println(">>>>>>代码生成完毕:" + this.targetPath + "....");
		} catch (Exception e) {
			System.out.println(">>>>>>生成代码异常....");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param tmplTargetFiles
	 * @return
	 * @throws IOException
	 */
	private void genTargetFileByTempletFile(File templetFile, List<SourceTable> tabs) throws IOException {
		
		String targetFilePath = templetFile.getPath().substring(templetsTargetPath.length(),
				templetFile.getPath().length());
		targetFilePath = this.targetPath.concat(PathUtil.getSeparator())
				.concat(this.replaceDBLevelForPath(targetFilePath));

		String templetFileContent = "";
		if (templetFile.isFile()) {
			templetFileContent = TxtReaderUtil.readToEnd(templetFile.getPath());
		}
		if (targetFilePath.contains(PlaceholderConst.TABLE_J_NAME)
				|| targetFilePath.contains(PlaceholderConst.TABLE_JU_NAME)) {
			// do loop tables
			for (SourceTable tab : tabs) {
				String tableName = tab.getName();
				String tableJUName = StringUtil.replaceSpecCharAndUpperFirst(tableName, "_");
				tab.setJName(StringUtil.lowerFirstChar(tableJUName));
				tab.setJUName(StringUtil.upperFirstChar(tableJUName));
				String targetFilePathWithTable = this.replaceTableLevel(targetFilePath, tab);
				if (templetFile.isFile()) {
					// 根据模板生成目标文件
					String targetFileContentWithTable = this.getTargetFileContent(templetFileContent, tab,
							targetFilePathWithTable);
					TxtWriterUtil.write(targetFilePathWithTable, targetFileContentWithTable);
					System.out.println("生成目标$文件=>" + targetFilePathWithTable);
				} else {
					TxtWriterUtil.mkdirs(targetFilePathWithTable);
					System.out.println("生成目标$目录=>" + targetFilePathWithTable);
				}
			} // end loop
		} else {
			// 替换内容
			if (templetFile.isFile()) {
				String targetFileContent = this.replaceDBLevel(templetFileContent);
				TxtWriterUtil.write(targetFilePath, targetFileContent);
				System.out.println("生成目标文件=>" + targetFilePath);
			} else {
				TxtWriterUtil.mkdirs(templetFile.getAbsolutePath());
				System.out.println("生成目标目录=>" + targetFilePath);
			}
		}
	}

	/**
	 * 
	 * @param src
	 * @param targFilePath
	 */
	private String getTargetFileContent(String src, SourceTable tab, String targFilePath) {

		String target = this.replaceTableLevel(src, tab);
		List<SourceColumn> cols = this.sourceDaoMysql.getColumnList(this.databaseName, tab.getName());
		HashMap<String, LoopFrag> fragMap = this.loopColumnFragMap;
		// init frag map
		Iterator<String> iterator = this.loopColumnFragMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			LoopFrag frag = fragMap.get(key);
			frag.setTargetContent(new StringBuilder(""));
		}
		// loop column
		boolean isDoFirstPK = true;
		for (SourceColumn col : cols) {
			String columnTemp = StringUtil.replaceSpecCharAndUpperFirst(col.getName(), "_");
			col.setJName(StringUtil.lowerFirstChar(columnTemp));
			col.setJUName(StringUtil.upperFirstChar(columnTemp));
			col.setJdbcType(this.mysqlToJdbcTypeMap.get(col.getDataType().toLowerCase()));
			col.setJType(this.mysqlToJavaTypeMap.get(col.getDataType().toLowerCase()));
			if (col.isPrimaryKey() && isDoFirstPK) {
				tab.setPk(col.getName());
				tab.setPkJType(col.getJType());
				tab.setPkJName(StringUtil.lowerFirstChar(StringUtil.replaceSpecCharAndUpperFirst(col.getName(), "_")));
				isDoFirstPK = false;
			}
			Iterator<String> i = fragMap.keySet().iterator();
			while (i.hasNext()) {
				String key = i.next();
				LoopFrag frag = fragMap.get(key);
				String fragContent = replaceColumnLevel(frag.getContent(), col);
				fragContent = frag.getPrefix() + fragContent + frag.getSuffix();
				frag.getTargetContent().append(frag.getConcat() + fragContent);
			}
		}
		target = this.replaceColumnLoopFrag(target, fragMap);
		target = this.replacePKLevel(target, tab);
		return target;
	}

	/**
	 * 
	 * @param src
	 * @param srcTable
	 * @return
	 */
	private String replaceDBLevelForPath(String path) {
		String pathPackageName = this.packageName.replace('.', '\\');
		String targetFilePath = path;
		targetFilePath = targetFilePath.replace(PlaceholderConst.PACKAGE_NAME, pathPackageName);
		targetFilePath = targetFilePath.replace(PlaceholderConst.DATABASE_NAME, this.databaseName);
		return targetFilePath;
	}

	/**
	 * 
	 * @param src
	 * @param srcTable
	 * @return
	 */
	private String replaceDBLevel(String src) {
		String target = src;
		target = target.replaceAll(toRgxStr(PlaceholderConst.PACKAGE_NAME), this.packageName);
		target = target.replaceAll(toRgxStr(PlaceholderConst.DATABASE_NAME), this.databaseName);
		return target;
	}

	/**
	 * 
	 * @param src
	 * @param tab
	 * @return
	 */
	private String replaceTableLevel(String src, SourceTable tab) {
		String target = src;
		target = this.replaceDBLevel(src);
		target = target.replaceAll(toRgxStr(PlaceholderConst.TABLE_NAME), tab.getName());
		target = target.replaceAll(toRgxStr(PlaceholderConst.TABLE_J_NAME), tab.getJName());
		target = target.replaceAll(toRgxStr(PlaceholderConst.TABLE_JU_NAME), tab.getJUName());
		target = target.replaceAll(toRgxStr(PlaceholderConst.TABLE_DES), tab.getDescription());
		return target;
	}

	/**
	 * 
	 * @param src
	 * @param srcTable
	 * @return
	 */
	private String replaceColumnLevel(String src, SourceColumn col) {
		String target = src;
		target = target.replaceAll(toRgxStr(PlaceholderConst.COLUMN), col.getName());
		target = target.replaceAll(toRgxStr(PlaceholderConst.COLUMN_DES), col.getDescription());
		target = target.replaceAll(toRgxStr(PlaceholderConst.COLUMN_J_NAME), col.getJName());
		target = target.replaceAll(toRgxStr(PlaceholderConst.COLUMN_J_U_NAME), col.getJUName());
		target = target.replaceAll(toRgxStr(PlaceholderConst.COLUMN_JDBC_TYPE), col.getJdbcType());
		target = target.replaceAll(toRgxStr(PlaceholderConst.COLUMN_J_TYPE), col.getJType());
		return target;
	}

	/**
	 * 
	 * @param src
	 * @param srcTable
	 * @return
	 */
	private String replacePKLevel(String src, SourceTable tab) {
		String target = src;
		target = target.replaceAll(toRgxStr(PlaceholderConst.PK), tab.getPk());
		target = target.replaceAll(toRgxStr(PlaceholderConst.PK_J_TYPE), tab.getPkJType());
		target = target.replaceAll(toRgxStr(PlaceholderConst.PK_J_NAME), tab.getPkJName());
		return target;
	}

	private String replaceColumnLoopFrag(String src, HashMap<String, LoopFrag> map) {
		String target = src;
		Iterator<String> i = map.keySet().iterator();
		while (i.hasNext()) {
			String key = i.next();
			String placeholderKey = "${" + key + "}";
			if (target.contains(placeholderKey)) {
				LoopFrag frag = map.get(key);
				String content = frag.getTargetContent().toString();
				if (StringUtils.isNotEmpty(frag.getConcat()) && StringUtils.isNotEmpty(content)) {
					content = content.substring(frag.getConcat().length());
				}
				target = target.replace(placeholderKey, content);
			}
		}
		return target;
	}
	/**
	 * 
	 * @param src
	 * @return
	 */
	private String toRgxStr(String src) {
		String trg=src.replace("$", "\\$");
		trg=trg.replace("{", "\\{");
		trg=trg.replace("}", "\\}");
		return trg;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public static void main(String[] args) {
		try {
			String path = PathUtil.getAppConfPath().concat(PathUtil.getSeparator()).concat("templets");
			String targetPath = path.concat(PathUtil.getSeparator()).concat("target");
			List<File> files = FileUtil.listAllFiles(targetPath);
			for (File f : files) {
				if (f.isFile()) {
					System.out.println("file=>" + f.getPath());
				} else {
					System.out.println("Directory=>" + f.getPath());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
