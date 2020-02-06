package org.muque.lark.common.mix;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static String toHexString(byte[] b) {
		// String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	/**
	 * MD5 32 bit upper string
	 * 
	 * @param source
	 * @return
	 */
	public static String makeMD5Str(String source) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(source.getBytes());
			byte messageDigest[] = digest.digest();
			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static void main(String[] args) {
		System.out.println(makeMD5Str("123456!@#$%^&*qwertyuiop"));
	}

}
