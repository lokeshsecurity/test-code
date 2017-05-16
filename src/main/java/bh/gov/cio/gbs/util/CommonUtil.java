/**
 * 
 */
package bh.gov.cio.gbs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;



/**
 * @author CSDVEDD
 * @since Feb 26, 2014
 * 
 */

public class CommonUtil {

	public static Integer[] convertToIntegerArray(String[] numericArray) {
		Integer[] intArray = null;
		if (numericArray != null && (numericArray.length != 0)) {

			int length = numericArray.length;

			intArray = new Integer[length];

			for (int i = 0; i < length; i++) {
				intArray[i] = Integer.parseInt(numericArray[i]);
			}

			return intArray;

		}

		return intArray;

	}

	public static Long[] convertToLongArray(String[] numericArray) {
		Long[] result = null;
		if (numericArray != null && (numericArray.length != 0)) {

			int length = numericArray.length;

			result = new Long[length];

			for (int i = 0; i < length; i++) {
				result[i] = Long.parseLong(numericArray[i]);
			}

			return result;

		}

		return result;

	}

	public static Map<String, Integer[]> differentiate(Integer[] a, Integer[] b) {
		Map<String, Integer[]> differnece = new HashMap<String, Integer[]>();
		List<Integer> removedItemsList = new ArrayList<Integer>();
		List<Integer> commonItemsList = new ArrayList<Integer>();

		if (a != null && b != null) {
			Set<Integer> setB = buildSet(b);
			int aLength = a.length;
			int bLength = b.length;
			boolean isExist = false;
			for (int i = 0; i < aLength; i++) {
				isExist = false;
				for (int j = 0; j < bLength; j++) {
					if (a[i].intValue() == b[j].intValue()) {
						isExist = true;
						commonItemsList.add(a[i]);
						setB.remove(b[j]);
						break;
					}

				}

				if (!isExist) {
					removedItemsList.add(a[i]);
				}
			}

			Integer[] commonItems = new Integer[commonItemsList.size()];
			commonItemsList.toArray(commonItems);
			differnece.put("=", commonItems);

			Integer[] removedItems = new Integer[removedItemsList.size()];
			removedItemsList.toArray(removedItems);
			differnece.put("-", removedItems);

			Integer[] addedItems = new Integer[setB.size()];
			setB.toArray(addedItems);
			differnece.put("+", addedItems);

		} else if (a == null && b != null) {
			differnece.put("+", b);
		}

		else if (b == null && a != null) {
			differnece.put("-", a);
		}

		return differnece;
	}

	public static Map<String, Long[]> differentiate(Long[] a, Long[] b) {

		Map<String, Long[]> differnece = new HashMap<String, Long[]>();
		List<Long> removedItemsList = new ArrayList<Long>();
		List<Long> commonItemsList = new ArrayList<Long>();
		Set<Long> setB = buildSet(b);
		int aLength = a!=null ? a.length : 0;
		int bLength = b!=null ? b.length : 0;
		boolean isExist = false;
		if (a != null && b != null)
			for (int i = 0; i < aLength; i++) {
				isExist = false;
				for (int j = 0; j < bLength; j++) {
					if (a[i] .equals(b[j]) ) {
						isExist = true;
						commonItemsList.add(a[i]);
						setB.remove(b[j]);
						break;
					}

				}

				if (!isExist) {
					removedItemsList.add(a[i]);
				}
			}

		Long[] commonItems = new Long[commonItemsList.size()];
		commonItemsList.toArray(commonItems);
		differnece.put("=", commonItems);

		Long[] removedItems = new Long[removedItemsList.size()];
		removedItemsList.toArray(removedItems);
		differnece.put("-", removedItems);

		Long[] addedItems = new Long[setB.size()];
		setB.toArray(addedItems);
		differnece.put("+", addedItems);

		return differnece;
	}

	private static Set<Long> buildSet(Long[] a) {
		Set<Long> setA = new LinkedHashSet<Long>();
		int length = a.length;

		for (int i = 0; i < length; i++) {
			setA.add(a[i]);
		}

		return setA;
	}

	private static Set<Integer> buildSet(Integer[] a) {
		Set<Integer> setA = null;
		if (a != null) {
			setA = new LinkedHashSet<Integer>();

			int length = a.length;

			for (int i = 0; i < length; i++) {
				setA.add(a[i]);
			}
		}

		return setA;
	}

	public static int[] convertToIntArray(List<Integer> list) {
		Integer[] integers = new Integer[list.size()];
		list.toArray(integers);
		return ArrayUtils.toPrimitive(integers);
	}

	public static String[] convertToStringArray(List<String> list) {
		String[] tags = new String[list.size()];
		list.toArray(tags);
		return tags;
	}

	public static Object[] convertToObjectArray(List<Object> list) {
		Object[] objects = new Object[list.size()];
		list.toArray(objects);
		return objects;
	}

	public static int[] convertListOfObjectToIntArray(Integer[] array) {
		return ArrayUtils.toPrimitive(array);
	}
	
	
	public static Integer[] convertArrayToListOfObjects(int[] array) {
		return ArrayUtils.toObject(array);
	}

	/**
	 * @author CSDVZZE
	 * @since Mar 4, 2014 getInt
	 * @param numericText
	 * @return int
	 */
	public static Integer getInt(String numericText) {
		// TODO Auto-generated method stub
		Integer number = null;
		if (numericText != null && !numericText.isEmpty()) {
			number = Integer.valueOf(numericText);
		}
		return number;
	}

	public static void main(String[] args) {
		
		String str = "[2, 15]";
		String newStr = str.substring(0, str.length()-1).substring(1).replaceAll("\\s+","");
		String [] ids = newStr.split(",");
		Integer[] i =convertToIntegerArray(ids);
		System.out.println(i.length);
		Integer[] values = new Integer[2];
		values[0] = null;
		values[1] = 20;
		System.out.println("remove : " + removeNullValues(values).length);
		

//		System.out.println(getInt(""));
//		System.out.println(Integer.MAX_VALUE);
//
//		String numberText = "46579813268465798";
//		System.out.println("  Number  " + numberText.matches("[0-9]*"));
//
//		double value = 12;
//		System.out.println(new DecimalFormat("#0.00").format(value));
//
//		String z = new DecimalFormat("#0.00").format(value);
//		value = Double.parseDouble(z);
//
//		System.out.println(value);
//		Integer[] x = new Integer[] { 177, 174 };
//		Integer[] y = new Integer[] { 177, 174 };
//		differentiate(x, y);
//
//		if (x[0].intValue() == y[0].intValue()) {
//			System.out.println("  Yes  ");
//		}
//
//		String likeValue = "موضوع كبير";
//
//		String[] words = likeValue.split("\\s");
//		int wordCount = words.length;
//		likeValue = "";
//		for (int i = 0; i < wordCount; i++) {
//			likeValue = likeValue + "%" + words[i];
//		}
//		likeValue = likeValue + "%";
//		System.out.println(" Like Value    " + likeValue);
//
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("  ? , ?,");
//		buffer.replace(buffer.length() - 1, buffer.length(), "");
//
//		System.out.println(" Buffer    " + buffer);
//		// referenceNumber = new ReferenceNumber();
//		// referenceNumber.setRefNumber("3/2014/MUS/OUT/106");
//		// String s = "54654654654654";
//		// String[] vals = s.split("/");
//		//
//		// System.out.println("  sadfusdg  "+vals.length);
//
//
//		Long[] a = new Long[] { 1l, 2l, 3l, 4l, 5l, 6l };
//		Long[] b = new Long[] { 3l, 5l, 8l, 9l };
//
//		long runningTime = System.nanoTime();
//		Map<String, Long[]> differnece = differentiate(a, b);
//		System.out.println(" Running  Time "
//				+ (System.nanoTime() - runningTime));
//
//		String data = "";
//		for (Entry<String, Long[]> entry : differnece.entrySet()) {
//			Long[] elements = entry.getValue();
//			data = "";
//			for (int i = 0; i < elements.length; i++) {
//				data = data + elements[i] + ",";
//			}
//			data = data.substring(0, data.length() - 1);
//			System.out.println(entry.getKey() + "     " + data);
//		}

		/*
		 * System.out.println(Integer.MAX_VALUE);
		 * System.out.println(Long.MAX_VALUE);
		 * 
		 * String s = null; System.out.println(CommonUtil.getInt(s)); String
		 * dateText = "03/13/2014"; System.out.println(getDate(dateText));
		 * 
		 * String refNumber = "2014/CIO/3/130";
		 * 
		 * String[] refNumberParts = refNumber.split("/"); int refNumberLength =
		 * refNumber.length(); String serialPart =
		 * refNumberParts[refNumberParts.length - 1]; int serialPartLength =
		 * serialPart.length(); String constantPart = refNumber.substring(0,
		 * refNumberLength - serialPartLength - 1);
		 * 
		 * 
		 * System.out.println("Const Part  "+constantPart+" Serial  "+serialPart)
		 * ;
		 */

	}


	public static boolean isRequestDateIsMoreThanPeriodInMinutes(
			Long currentDate, Long requestDate, int periodInMinutes) {
		boolean validRequest = false;
		if ((currentDate - requestDate) <= (periodInMinutes * 60 * 1000)) {
			validRequest = true;
		}
		return validRequest;
	}

	public static String getDateTimeFromDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}

	/**
	 * @author csdvedd
	 * @param request
	 * @param string
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request,
			String cookieName) {
		Cookie cookieObject = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					cookieObject = cookie;
					break;
				}
			}
		}
		return cookieObject;
	}
	
	public static List<Integer> getIntegerList(Integer[] array){
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(array));
		return list;
	}
	
	public static List<Integer> getIntegerListFromIntArray(int[] array){
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(Arrays.asList(ArrayUtils.toObject(array)));
		return list;
	}
	
	public static Integer[] convertToIntegerArray(List<Integer> list) {
		Integer[] integers = new Integer[list.size()];
		list.toArray(integers);
		return integers;
	}
	
	public static Integer [] removeNullValues(Integer[] values){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < values.length; i++) {
			if(values[i] !=null )
				list.add(values[i]);
		}
		return convertToIntegerArray(list);
	}

}
