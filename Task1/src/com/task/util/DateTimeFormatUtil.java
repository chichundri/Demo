package com.task.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatUtil {
	public static String DateAsDDMMYYYY(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//		System.out.println(sdf.format(d));
		String date = sdf.format(d); 
//		Date d1 = new Date(date);
//		System.out.println(d1);
		return date;
	}
	public static void main(String[] args) {
		DateTimeFormatUtil.DateAsDDMMYYYY(new Date());
	}
}
