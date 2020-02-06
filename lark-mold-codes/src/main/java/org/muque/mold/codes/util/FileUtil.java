package org.muque.mold.codes.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	/**
	 * 递归列出所有文件
	 * 
	 * @param path
	 * @return
	 */
	public static List<File> listAllFiles(String path) {
		List<File> results = new ArrayList<File>();
		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (null != files && files.length > 0) {
				addArryToList(files,results);
				for (File f : files) {
					List<File> subResults = listAllFiles(f.getPath());
					if (subResults.size() > 0)
						results.addAll(subResults);
				}
			}
		}
		return results;
	}

	private static void addArryToList(File[] source, List<File> target) {
		for (File f : source) {
			target.add(f);
		}
	}
}
