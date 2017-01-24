package com.gary.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static ZonedDateTime toZonedDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault());
	}
    public static LocalDateTime toLocalDateTime(Date date) {
        return toZonedDateTime(date).toLocalDateTime();
    }
    public static LocalDate toLocalDate(Date date) {
    	return toZonedDateTime(date).toLocalDate();
	}
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    public static Date toDate(LocalDate localDate) {
    	return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	public static Date startOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}
	public static Date startOfDate(LocalDate localDate) {
		return DateUtils.toDate(localDate.atStartOfDay());
	}
	public static Date endOfDate(LocalDate localDate) {
		return DateUtils.toDate(localDate.atTime(LocalTime.MAX));
	}

	/**
	 * @return 这个月第一天的开始时间
	 */
	public static Date getFirstDayOfThisMonth() {
		LocalDate today = LocalDate.now();
		LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
		return Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date getStartOfToday() {
		LocalDate today = LocalDate.now();
		return Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static Date expiresInToDate(long expireIn, TemporalUnit temporalUnit) {
		return Date.from(Instant.now().plus(expireIn, temporalUnit));
	}

	/**
	 * 转换yyyy-MM-dd HH:mm:ss或者yyyy-MM-dd格式的字符串为Date
     */
	public static Date parseDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e1) {
				throw new RuntimeException(e);
			}
		}
	}
}
