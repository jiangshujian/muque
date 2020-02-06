package org.muque.lark.common.mix;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * 类型转换工具�?
 * 
 * @author jiangshujian
 * @version 1.0,2013.5.20
 */
@Slf4j
public class ConvertUtil {

	/**
	 * Convert to String.
	 * 
	 * @param o
	 * @return
	 */
	public static String toString(Object o) {
		if (o == null)
			return null;
		return o.toString();
	}

	/**
	 * Convert to String. (with default value)
	 * 
	 * @param o
	 * @return
	 */
	public static String toString(Object o, String defaultValue) {
		if (o == null)
			return defaultValue;
		return o.toString();
	}

	/**
	 * Convert to short.
	 * 
	 * @param o
	 * @return
	 */
	public static short toShort(Object o) {
		if (o == null)
			return 0;
		try {
			return Short.parseShort(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	/**
	 * Convert to short. (with default value)
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static short toShort(Object o, short defaultValue) {
		if (o == null)
			return defaultValue;
		try {
			return Short.parseShort(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return defaultValue;
		}
	}

	/**
	 * Convert to int.
	 * 
	 * @param o
	 * @return
	 */
	public static int toInt(Object o) {
		if (o == null)
			return 0;
		try {
			return Integer.parseInt(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return 0;
		}
	}

	/**
	 * Convert to int. (with default value)
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static int toInt(Object o, int defaultValue) {
		if (o == null)
			return defaultValue;
		try {
			return Integer.parseInt(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return defaultValue;
		}
	}

	/**
	 * Convert to long.
	 * 
	 * @param o
	 * @return
	 */
	public static long toLong(Object o) {
		if (o == null)
			return 0L;
		try {
			return Long.parseLong(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return 0L;
		}
	}

	/**
	 * Convert to long. (with default value)
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static long toLong(Object o, long defaultValue) {
		if (o == null)
			return defaultValue;
		try {
			return Long.parseLong(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return defaultValue;
		}
	}

	/**
	 * Convert to float.
	 * 
	 * @param o
	 * @return
	 */
	public static float toFloat(Object o) {
		if (o == null)
			return 0.0f;
		try {
			return Float.parseFloat(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return 0.0f;
		}
	}

	/**
	 * Convert to float. (with default value)
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static float toFloat(Object o, float defaultValue) {
		if (o == null)
			return defaultValue;
		try {
			return Float.parseFloat(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return defaultValue;
		}
	}

	/**
	 * Convert to double.
	 * 
	 * @param o
	 * @return
	 */
	public static double toDouble(Object o) {
		if (o == null)
			return 0.0d;
		try {
			return Double.parseDouble(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return 0.0d;
		}
	}

	/**
	 * Convert to double. (with default value)
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static double toDouble(Object o, double defaultValue) {
		if (o == null)
			return defaultValue;
		try {
			return Double.parseDouble(o.toString());
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return defaultValue;
		}
	}

	/**
	 * Convert to BigDecimal.
	 * 
	 * @param o
	 * @return
	 */
	public static BigDecimal toBigDecimal(Object o) {
		if (o == null)
			return BigDecimal.ZERO;
		try {
			return BigDecimal.valueOf(toDouble(o));
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return BigDecimal.ZERO;
		}
	}

	/**
	 * Convert to BigDecimal. (with default value)
	 * 
	 * @param o
	 * @param defaultValue
	 * @return
	 */
	public static BigDecimal toBigDecimal(Object o, double defaultValue) {
		if (o == null)
			return BigDecimal.valueOf(defaultValue);
		try {
			return BigDecimal.valueOf(toDouble(o));
		} catch (NumberFormatException e) {
			log.error(e.getMessage());
			return BigDecimal.valueOf(defaultValue);
		}
	}

	/**
	 * Convert to boolean.
	 * 
	 * @param o
	 * @return
	 */
	public static boolean toBoolean(Object o) {
		if (o == null)
			return false;

		String s = o.toString();
		if (s.equals("") || s.equals("0") || s.equalsIgnoreCase("false")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Convert to Date,only support the format "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param o
	 * @return
	 */
	public static Date toDate(Object o) {
		if (o == null)
			return null;

		String s = o.toString();
		Date retValue = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			retValue = sdf.parse(s);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return retValue;
	}

	/**
	 * Convert to Date by your support date format string, such as "yyyy-MM-dd
	 * HH:mm:ss" "yyyyMMddHHmmss" "yyyy/MM/dd HH:mm:ss" etc.
	 * 
	 * @param o
	 * @param dateFormatStr
	 * @return
	 */
	public static Date toDate(Object o, String dateFormatStr) {
		if (o == null)
			return null;

		String s = o.toString();
		Date retValue = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
			retValue = sdf.parse(s);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return retValue;
	}

	/**
	 * format date to string
	 * 
	 * @param date
	 * @param dateFormatStr
	 * @return
	 */
	public static String toDateString(Object date, String dateFormatStr) {
		if (date == null)
			return null;
		String retValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormatStr);
		retValue = sdf.format(date);
		return retValue;
	}

	public static void main(String[] args) {
		System.out.println(toBigDecimal(10000.6666666666).toString());
	}
}
