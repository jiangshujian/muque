package org.muque.mold.codes.common;

import org.muque.mold.codes.util.PathUtil;
import org.muque.mold.codes.util.TxtReaderUtil;

public class LoopFrag {
	private String content;
	private String concat = "";
	private String prefix = "";
	private String suffix = "";
	private StringBuilder targetContent;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getConcat() {
		return concat;
	}

	public void setConcat(String concat) {
		this.concat = concat;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public StringBuilder getTargetContent() {
		return targetContent;
	}

	public void setTargetContent(StringBuilder targetContent) {
		this.targetContent = targetContent;
	}

	public void setPath(String path) {
		try {
			String filePath = PathUtil.getAppPath()
					.concat(PathUtil.getSeparator())
					.concat(PathUtil.getPath(path));
			System.out.println("load loop fragment file:"+filePath);
			this.content = TxtReaderUtil.readToEnd(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
