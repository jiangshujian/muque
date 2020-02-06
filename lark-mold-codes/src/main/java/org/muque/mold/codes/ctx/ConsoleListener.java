package org.muque.mold.codes.ctx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;

/**
 * 控制台
 * 
 * @author jiangshujian
 * @version 1.1,20130608
 * 
 */
public class ConsoleListener {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void listenning(String[] args) throws IOException {
		String cmdLine = "";

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		while (AppContext.CurrentAppStatus == AppContext.STATUS_RUNNING) {
			cmdLine = reader.readLine();
			if (StringUtils.isNotBlank(cmdLine)) {
				System.out.println("cmdLine=>" + cmdLine);
			}
		}

	}

}
