package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.GregorianCalendar;

public class DateUtil {

	private static final Logger log = LogManager.getLogger(DateUtil.class);

	public static final SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("hhmmss", Locale.US);
	public static final SimpleDateFormat timeFormatTH = new SimpleDateFormat("HH:mm", Locale.US);
	public static final SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	public static final SimpleDateFormat dtTHFormat = new SimpleDateFormat("ddMMyyyy", Locale.US);
	public static final SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS", Locale.US);
	public static final SimpleDateFormat hFormat = new SimpleDateFormat("HH", Locale.US);
	public static final SimpleDateFormat mFormat = new SimpleDateFormat("mm", Locale.US);
	public static final SimpleDateFormat sqldFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	public static final Calendar MIN_DATE = getCalendar(Calendar.getInstance(Locale.US), "30/12/1899 00:00", dtFormat);
	public static final SimpleDateFormat dtTHFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
	
	public static final SimpleDateFormat dtTHDocFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
	public static final SimpleDateFormat dtTHReceiptFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

	private static final String WEEK_TH = "Week";
	private static final String WEEK_EN = "Week";

	private static final String[] MONTH_TH = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private static final String[] MONTH_EN = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	public static final String DATE_FORMAT_NOW = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_1 = "dd/MM/yyyy";

	private DateUtil() {
	}
	
	public static int trimCompareTo(Date sDate, Date nDate){
	 	return DateUtil.trimDate(sDate).compareTo(DateUtil.trimDate(nDate));
	}

	public static Date addMonth(Date d, int num){
		Calendar c =  Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MONTH, num );
		return c.getTime(); 
	}
	
	public static Date addYear(Date d, int num){
		Calendar c =  Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.YEAR, num );
		return c.getTime(); 
	}

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static String getDateStr(Date date, boolean isTH) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);
			if (isTH)
				cal.add(Calendar.YEAR, 543);

			return dFormat.format(cal.getTime());
		}
	}

	public static String getTimeStr(Date date, boolean isTH) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);
			if (isTH)
				cal.add(Calendar.YEAR, 543);

			return timeFormatTH.format(cal.getTime());
		}
	}

	public static String getDateTimeStr(Date date, boolean isTH) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);
			if (isTH)
				cal.add(Calendar.YEAR, 543);

			return dtFormat.format(cal.getTime());
		}
	}

	public static String getDateTimeStampStr(Date date, boolean isTH) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);
			if (isTH)
				cal.add(Calendar.YEAR, 543);

			return timeStampFormat.format(cal.getTime());
		}
	}

	public static String getHourStr(Date date) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);

			return hFormat.format(cal.getTime());
		}
	}

	public static String getMinuteStr(Date date) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);

			return mFormat.format(cal.getTime());
		}
	}

	public static String getSqlDateStr(Date date) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);

			return sqldFormat.format(cal.getTime());
		}
	}

	public static String getSqlDateEndStr(Date date) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);

			return sqldFormat.format(cal.getTime()) + " 23:59:59.999";
		}
	}

	public static String getSqlWeekStr(String week, boolean isTH) {
		// Week ww, yyyy -> yyyy, ww
		String[] ss = StringUtil.toArrayString(week, " ");
		if (ss != null && ss.length == 3) {
			String w = StringUtil.toString(ss[1]).replaceAll(",", "");
			String y = getSqlYearStr(StringUtil.toString(ss[2]), isTH);
			return y + ", " + w;
		}
		return "";
	}

	public static String getSqlMonthStr(String month, boolean isTH) {
		// mm, yyyy -> yyyy, mm
		String[] ss = StringUtil.toArrayString(month, " ");
		if (ss != null && ss.length == 2) {
			String m = StringUtil.toString(ss[0]).replaceAll(",", "");
			String y = getSqlYearStr(StringUtil.toString(ss[1]), isTH);
			return y + ", " + m;
		}
		return "";
	}

	public static String getSqlQuarterStr(String quarter, boolean isTH) {
		// q, yyyy -> yyyy, q
		String[] ss = StringUtil.toArrayString(quarter, " ");
		if (ss != null && ss.length == 2) {
			String q = StringUtil.toString(ss[0]).replaceAll(",", "");
			String y = getSqlYearStr(StringUtil.toString(ss[1]), isTH);
			return y + ", " + q;
		}
		return "";
	}

	public static String getSqlYearStr(String year, boolean isTH) {
		// yyyy -> yyyy
		int y = StringUtil.toInt(year);
		if (y > 0) {
			if (isTH)
				y -= 543;
			return y + "";
		}
		return "";
	}

	public static String getWeekStr(String week, boolean isTH) {
		// yyyy, ww -> Week ww, yyyy
		String[] ss = StringUtil.toArrayString(week, " ");
		if (ss != null && ss.length == 2) {
			String y = getYearStr(StringUtil.toString(ss[0]).replaceAll(",", ""), isTH);
			String w = StringUtil.toString(ss[1]);
			if (isTH)
				return WEEK_TH + " " + w + ", " + y;
			else
				return WEEK_EN + " " + w + ", " + y;
		}
		return "";
	}

	public static String getMonthStr(String month, boolean isTH) {
		// yyyy, mm -> MMM-yyyy
		String[] ss = StringUtil.toArrayString(month, " ");
		if (ss != null && ss.length == 2) {
			String y = getYearStr(StringUtil.toString(ss[0]).replaceAll(",", ""), isTH);
			int m = StringUtil.toInt(ss[1]);
			if (m > 0 && m <= MONTH_EN.length) {
				if (isTH)
					return MONTH_TH[m - 1] + "-" + y;
				else
					return MONTH_EN[m - 1] + "-" + y;
			}
		}
		return "";
	}

	public static String getQuarterStr(String quarter, boolean isTH) {
		// yyyy, q -> MMM-MMM, yyyy
		String[] ss = StringUtil.toArrayString(quarter, " ");
		if (ss != null && ss.length == 2) {
			String y = getYearStr(StringUtil.toString(ss[0]).replaceAll(",", ""), isTH);
			int q = StringUtil.toInt(ss[1]);
			if (q > 0 && q <= 4) {
				if (isTH)
					return MONTH_TH[(q - 1) * 3] + "-" + MONTH_TH[(q * 3) - 1] + ", " + y;
				else
					return MONTH_EN[(q - 1) * 3] + "-" + MONTH_EN[(q * 3) - 1] + ", " + y;
			}
		}
		return "";
	}

	public static String getYearStr(String year, boolean isTH) {
		int y = StringUtil.toInt(year);
		if (y > 0) {
			if (isTH)
				y += 543;
			return y + "";
		}
		return "";
	}

	public static String getYearStr(int year, boolean isTH) {
		int y = StringUtil.toInt(year);
		if (y > 0) {
			if (isTH)
				y += 543;
			return y + "";
		}
		return "";
	}

	public static Date createYear(String strYear) {
		if (StringUtil.isEmpty(strYear)) {
			return null;
		}
		int yyyy = Integer.parseInt(strYear);
		log.debug("DateUtil.java 'getYear'== " + yyyy);
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.set(yyyy + 1, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date createDate(String strDate, boolean isTH) {
		if (StringUtil.isEmpty(strDate) || strDate.length() < 10) {
			return null;
		}

		int dd = StringUtil.toInt(strDate.substring(0, 2));
		int mm = StringUtil.toInt(strDate.substring(3, 5));
		int yyyy = StringUtil.toInt(strDate.substring(6, 10));

		return createDate(dd, mm, yyyy, isTH);
	}

	public static Date createDate(int dd, int mm, int yyyy, boolean isTH) {
		if (dd <= 0 || mm <= 0 || yyyy <= 0) {
			return null;
		}

		Calendar cal = Calendar.getInstance(Locale.US);
		if (isTH)
			cal.set(yyyy - 543, mm - 1, dd, 0, 0, 0);
		else
			cal.set(yyyy, mm - 1, dd, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date createDateTime(String strDate, String hour, String minute, boolean isTH) {
		if (StringUtil.isEmpty(strDate) || strDate.length() < 10) {
			return null;
		}

		int dd = StringUtil.toInt(strDate.substring(0, 2));
		int mm = StringUtil.toInt(strDate.substring(3, 5));
		int yyyy = StringUtil.toInt(strDate.substring(6, 10));
		int h = StringUtil.toInt(hour);
		int m = StringUtil.toInt(minute);

		if (dd <= 0 || mm <= 0 || yyyy <= 0) {
			return null;
		}

		Calendar cal = Calendar.getInstance(Locale.US);
		if (isTH)
			cal.set(yyyy - 543, mm - 1, dd, h, m, 0);
		else
			cal.set(yyyy, mm - 1, dd, h, m, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date createDateTime(String strDate, String hour, String minute, String second, boolean isTH) {
		if (StringUtil.isEmpty(strDate) || strDate.length() < 10) {
			return null;
		}

		int dd = StringUtil.toInt(strDate.substring(0, 2));
		int mm = StringUtil.toInt(strDate.substring(3, 5));
		int yyyy = StringUtil.toInt(strDate.substring(6, 10));
		int h = StringUtil.toInt(hour);
		int m = StringUtil.toInt(minute);
		int s = StringUtil.toInt(second);

		if (dd <= 0 || mm <= 0 || yyyy <= 0) {
			return null;
		}

		Calendar cal = Calendar.getInstance(Locale.US);
		if (isTH)
			cal.set(yyyy - 543, mm - 1, dd, h, m, s);
		else
			cal.set(yyyy, mm - 1, dd, h, m, s);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date createDateTimeTo(String strDate, String hour, String minute, boolean isTH) {
		if (StringUtil.isEmpty(strDate) || strDate.length() < 10) {
			return null;
		}

		int dd = StringUtil.toInt(strDate.substring(0, 2));
		int mm = StringUtil.toInt(strDate.substring(3, 5));
		int yyyy = StringUtil.toInt(strDate.substring(6, 10));
		int h = StringUtil.toInt(hour);
		int m = StringUtil.toInt(minute);

		if (dd <= 0 || mm <= 0 || yyyy <= 0) {
			return null;
		}

		Calendar cal = Calendar.getInstance(Locale.US);
		if (isTH)
			cal.set(yyyy - 543, mm - 1, dd, h, m, 59);
		else
			cal.set(yyyy, mm - 1, dd, h, m, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date createTime(String hour, String minute) {
		int dd = 1;
		int mm = 1;
		int yyyy = 1900;
		int h = StringUtil.toInt(hour);
		int m = StringUtil.toInt(minute);

		Calendar cal = Calendar.getInstance(Locale.US);
		cal.set(yyyy, mm - 1, dd, h, m, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static int getDate(Date d) {
		if (d == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		return cal.get(Calendar.DATE);
	}

	public static int getMonth(Date d) {
		if (d == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getYear(Date d) {
		if (d == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		return cal.get(Calendar.YEAR);
	}
	
	public static int getYearTH(Date d) {
		if (d == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance( new Locale("th", "TH"));
		cal.setTime(d);
		return cal.get(Calendar.YEAR);
	}

	public static Date setYear(Date d, int year) {
		if (d == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		cal.set(Calendar.YEAR, year);
		d.setYear(cal.getTime().getYear());
		return d;
	}

	public static int getHour(Date d) {
		if (d == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		return cal.get(Calendar.HOUR);
	}

	public static int getHour24(Date d) {
		if (d == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static Date setHour(Date d, int hour) {
		if (d == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		cal.set(Calendar.HOUR, hour);
		d.setHours(cal.getTime().getHours());
		return d;
	}

	public static int getMinute(Date d) {
		if (d == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		return cal.get(Calendar.MINUTE);
	}

	public static Date setMinute(Date d, int min) {
		if (d == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		cal.set(Calendar.MINUTE, min);
		d.setMinutes(cal.getTime().getMinutes());
		return d;
	}

	public static Date setTimeUpper(Date d) {
		if (d == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(d);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static Date trimDate(Date d) {
		if (d != null) {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(d);

			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			return cal.getTime();
		}
		return null;
	}

	public static Calendar getCalendar(Calendar cal, String dateString, SimpleDateFormat oDateFormat) {
		return getCalendar(cal, dateString, oDateFormat, MIN_DATE);
	}

	public static Calendar getCalendar(Calendar cal, String dateString, SimpleDateFormat oDateFormat, Calendar _default) {
		Calendar oCalendar = cal;
		if (oCalendar == null)
			oCalendar = _default;
		oDateFormat = dtFormat;

		try {
			oCalendar.setTime(oDateFormat.parse(dateString));
		} catch (Exception e) {
			if (_default == null) {
				oCalendar = null;
			} else {
				oCalendar.setTime(_default.getTime());
			}
		}
		return oCalendar;
	}

	public static Date addDate(Date date, int dateAdd) {
		Calendar cal;
		if (date == null) {
			return null;
		} else {
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, dateAdd);
			return cal.getTime();
		}
	}

	public static Date addSecond(Date date, int secondAdd) {
		Calendar cal;
		if (date == null) {
			return null;
		} else {
			cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.SECOND, secondAdd);
			return cal.getTime();
		}
	}

	public static Date getDate(boolean isTH) {
		Calendar cal;
		cal = Calendar.getInstance(Locale.US);
		if (isTH) {
			cal.add(Calendar.YEAR, 543);
		}

		return cal.getTime();
	}

	public static void addTime(Date date, Date time) {
		log.info("date " + date);
		log.info("time " + time);
		if (date != null && time != null) {
			date.setHours(time.getHours());
			date.setMinutes(time.getMinutes());
		}
	}

	public static String getMaxDayOfMonthOfCurrentYear(int month) {
		Calendar calendar = Calendar.getInstance();
		if (month == 1) {
			month = Calendar.JANUARY;
		} else if (month == 2) {
			month = Calendar.FEBRUARY;
		} else if (month == 3) {
			month = Calendar.MARCH;
		} else if (month == 4) {
			month = Calendar.APRIL;
		} else if (month == 5) {
			month = Calendar.MAY;
		} else if (month == 6) {
			month = Calendar.JUNE;
		} else if (month == 7) {
			month = Calendar.JULY;
		} else if (month == 8) {
			month = Calendar.AUGUST;
		} else if (month == 9) {
			month = Calendar.SEPTEMBER;
		} else if (month == 10) {
			month = Calendar.OCTOBER;
		} else if (month == 11) {
			month = Calendar.NOVEMBER;
		} else if (month == 12) {
			month = Calendar.DECEMBER;
		}
		calendar.set(getYear(getCurrentDate()), month, 1);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String day = Integer.toString(maxDay, 10);
		return day;
	}

	public static String getMonthName(int month) {
		String name = "xxxx";
		if (month == 1) {
			name = "เธกเธ�เธฃเธฒเธ�เธก";
		} else if (month == 2) {
			name = "เธ�เธธเธกเธ เธฒเธ�เธฑเธ�เธ�เน�";
		} else if (month == 3) {
			name = "เธกเธตเธ�เธฒเธ�เธก";
		} else if (month == 4) {
			name = "เน€เธกเธฉเธฒเธขเธ�";
		} else if (month == 5) {
			name = "เธ�เธคเธฉเธ เธฒเธ�เธก";
		} else if (month == 6) {
			name = "เธกเธดเธ–เธธเธ�เธฒเธขเธ�";
		} else if (month == 7) {
			name = "เธ�เธฃเธ�เธ�เธฒเธ�เธก";
		} else if (month == 8) {
			name = "เธชเธดเธ�เธซเธฒเธ�เธก";
		} else if (month == 9) {
			name = "เธ�เธฑเธ�เธขเธฒเธขเธ�";
		} else if (month == 10) {
			name = "เธ•เธธเธฅเธฒเธ�เธก";
		} else if (month == 11) {
			name = "เธ�เธคเธจเธ�เธดเธ�เธฒเธขเธ�";
		} else if (month == 12) {
			name = "เธ�เธฑเธ�เธงเธฒเธ�เธก";
		}
		return name;
	}

	public static String getMonthShortName(int month) {
		String name = "xxxx";
		if (month == 1) {
			name = "เธก.เธ�.";
		} else if (month == 2) {
			name = "เธ�.เธ�.";
		} else if (month == 3) {
			name = "เธกเธต.เธ�.";
		} else if (month == 4) {
			name = "เน€เธก.เธข.";
		} else if (month == 5) {
			name = "เธ�.เธ�.";
		} else if (month == 6) {
			name = "เธกเธด.เธข.";
		} else if (month == 7) {
			name = "เธ�.เธ�.";
		} else if (month == 8) {
			name = "เธช.เธ�.";
		} else if (month == 9) {
			name = "เธ�.เธข.";
		} else if (month == 10) {
			name = "เธ•.เธ�.";
		} else if (month == 11) {
			name = "เธ�.เธข.";
		} else if (month == 12) {
			name = "เธ�.เธ�.";
		}
		return name;
	}
	
	public static String getDayOfWeekName(Date d) {
		String name = "";
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			name = "เธญเธฒเธ—เธดเธ•เธขเน�";
		}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
			name = "เธ�เธฑเธ�เธ—เธฃเน�";
		}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
			name = "เธญเธฑเธ�เธ�เธฒเธฃ";
		}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
			name = "เธ�เธธเธ�";	
		}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
			name = "เธ�เธคเธซเธฑเธช";
		}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
			name = "เธจเธธเธ�เธฃเน�";
		}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
			name = "เน€เธชเธฒเธฃเน�";
		}
		return name;
	}
	
	public static String getTextThaiDate(Date d){
		if (d == null) 
			return null;
		return ""+getDate(d)+" "+getMonthName(getMonth(d))+" "+getYearStr(getYear(d), true);
	}
	public static String getTextThaiDate_MonthShortName(Date d){
		if (d == null) 
			return null;
		return ""+getDate(d)+" "+getMonthShortName(getMonth(d))+" "+dateToStringTH(d, "yy");
	}
	public static String getTextThaiDate_MonthShortName2(Date d){
		if (d == null) 
			return null;
		return ""+getDate(d)+" "+getMonthShortName(getMonth(d))+" "+dateToStringTH(d, "yyyy");
	}
	public static String getTextThaiDate_ThaiNumber(Date d){
		if (d == null) 
			return null;
		return ""+NumberUtil.convertAlrabicToThaiNumber(getDate(d))+" "+getMonthName(getMonth(d))+" "+NumberUtil.convertAlrabicToThaiNumber(getYearStr(getYear(d), true));
	}
	
	// เน’เน— เธ�เธคเธจเธ�เธดเธ�เธฒเธขเธ� /เน’เน•เน•เน—
	public static String getTextThaiDate_ThaiNumber02(Date d){
		if (d == null) 
			return null;
		return ""+NumberUtil.convertAlrabicToThaiNumber(getDate(d))+" "+getMonthName(getMonth(d))+" /"+NumberUtil.convertAlrabicToThaiNumber(getYearStr(getYear(d), true));
	}
	
	public static String getFullTextThaiDate(Date d){
		if (d == null) 
			return null;
		return "เธงเธฑเธ�"+getDayOfWeekName(d)+"เธ—เธตเน� "+getDate(d)+" "+getMonthName(getMonth(d))+" เธ�.เธจ. "+getYearStr(getYear(d), true);
	}
	public static String getFullTextThaiDate_02(Date d){
		if (d == null) 
			return null;
		return "เธงเธฑเธ�"+getDayOfWeekName(d)+"เธ—เธตเน� "+getDate(d)+" "+getMonthName(getMonth(d))+" "+getYearStr(getYear(d), true);
	}
	public static String getFullTextThaiDate_ThaiNumber(Date d){
		if (d == null) 
			return null;
		return "เธงเธฑเธ�"+getDayOfWeekName(d)+"เธ—เธตเน� "+NumberUtil.convertAlrabicToThaiNumber(getDate(d))+" "+getMonthName(getMonth(d))+" เธ�.เธจ. "+NumberUtil.convertAlrabicToThaiNumber(getYearStr(getYear(d), true));
	}
	
	public static String getFullTextThaiDate_ThaiNumber_01(Date d){
		if (d == null) 
			return null;
		return "เธงเธฑเธ�เธ—เธตเน� "+NumberUtil.convertAlrabicToThaiNumber(getDate(d))+" "+getMonthName(getMonth(d))+" เธ�.เธจ. "+NumberUtil.convertAlrabicToThaiNumber(getYearStr(getYear(d), true));
	}
	
	public static String getFullTextThaiDate2(Date d){
		if (d == null) 
			return null;
		return "เธงเธฑเธ�เธ—เธตเน� "+getDate(d)+" "+getMonthName(getMonth(d))+" "+getYearStr(getYear(d), true);
	}
	public static String getFullTextThaiDate2_ThaiNumber(Date d){
		if (d == null) 
			return null;
		return "เธงเธฑเธ�เธ—เธตเน� "+NumberUtil.convertAlrabicToThaiNumber(getDate(d))+" "+getMonthName(getMonth(d))+" "+NumberUtil.convertAlrabicToThaiNumber(getYearStr(getYear(d), true));
	}
	
	public static String getFullTextThaiDate3(Date d){
		if (d == null) 
			return null;
		return "เธงเธฑเธ�เธ—เธตเน� "+getDate(d)+" "+getMonthName(getMonth(d))+" "+getYearStr(getYear(d), true)+" เน€เธงเธฅเธฒ "+new SimpleDateFormat("HH:mm",  Locale.US).format(d)+" เธ�.";
	}
	public static String getFullTextThaiDate3_ThaiNumber(Date d){
		if (d == null) 
			return null;
		return "เธงเธฑเธ�เธ—เธตเน� "+NumberUtil.convertAlrabicToThaiNumber(getDate(d))+" "+getMonthName(getMonth(d))+" "+NumberUtil.convertAlrabicToThaiNumber(getYearStr(getYear(d), true))
				+" เน€เธงเธฅเธฒ "+NumberUtil.convertAlrabicToThaiNumber(new SimpleDateFormat("HH:mm",  Locale.US).format(d))+" เธ�.";
	}
	
	public static String getddMMyyyyThai(Date date) {
		if (date == null) {
			return "";
		} else {
			//Calendar cal = Calendar.getInstance(Locale.US);
			Calendar cal = Calendar.getInstance(new Locale("th","TH"));
			cal.setTime(date);
			//cal.add(Calendar.YEAR, 543);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", new Locale("th","TH"));
			return dateFormat.format(cal.getTime());
		}
	}

	public static String getddMMyyyyThai2(Date date) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);
			cal.add(Calendar.YEAR, 543);

			return dtTHFormat2.format(cal.getTime());
		}
	}
	
	public static String dateToStringTH(Date date,String format){
		if(date==null)
			return "";
		return new SimpleDateFormat(format,new Locale("th","TH")).format(date);
	}
	public static String dateToStringEn(Date date,String format){
		if(date==null)
			return "";
		return new SimpleDateFormat(format, Locale.US).format(date);
	}
	
	public static Date strToDateTH(String dateString,String format) throws ParseException{
		if(dateString==null)
			return null;
		return (Date)new SimpleDateFormat(format,new Locale("th","TH")).parse(dateString);
	}

	public static String getYYFromDateTH(Date date){
		if(date==null)
			return "";
		return new SimpleDateFormat("yy",new Locale("th","TH")).format(date);
	}
	
	public static int getDateDiffRoundUp(Date sDate, Date eDate) {
		long diff = eDate.getTime() - sDate.getTime();
	//	int diffDay = (int) (diff / (1000 * 60 * 60 * 24));
		double diffDay = (double) ((diff*1.0) / (1000 * 60 * 60 * 24));
		return (int) Math.ceil(diffDay);
	}

	public static int getDateDiff(Date sDate, Date eDate) {
		long diff = eDate.getTime() - sDate.getTime();
		int diffDay = (int) (diff / (1000 * 60 * 60 * 24));
		return diffDay;
	}
	
	public static String getddMMyyyy(Date date) {
		if (date == null) {
			return "";
		} else {
			Calendar cal = Calendar.getInstance(Locale.US);
			cal.setTime(date);

			return dtTHFormat.format(cal.getTime());
		}
	}
	
	public static String format(Date d){
		SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
		return dFormat.format(d);
	}
	
	public static String formatDateCache(Date d){
		SimpleDateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", new Locale("th", "TH"));
		return dFormat.format(d);
	}
	
	public static Boolean isServiceHorus(String openTime){//HH.mm
		openTime = openTime.replaceAll("\\.", "");
		String hhmm = new SimpleDateFormat("HHmm", Locale.US).format(DateUtil.getCurrentDate());
		if( (new Long(openTime) <= (new Long(hhmm))) ){
			System.out.println("inTime");
			return true;
		}
		else{
			System.out.println("outTime");
			return false;
		}
	}
	public static Boolean isServiceHorus(String openTime, String closeTime){//HH.mm
		openTime = openTime.replaceAll("\\.", "");
		closeTime = closeTime.replaceAll("\\.", "");
		String hhmm = new SimpleDateFormat("HHmm", Locale.US).format(DateUtil.getCurrentDate());
		if( (new Long(openTime) <= (new Long(hhmm))) && ((new Long(hhmm)) <= (new Long(closeTime))) ){
			System.out.println("inTime");
			return true;
		}
		else{
			System.out.println("outTime");
			return false;
		}
	}
	public static Date addDateByPassSaturdayAndSunday(Date startDate, Integer addDate){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		//System.out.println("Start : "+calendar.getTime());
		for(int i=0; i<addDate; i++){
			calendar.add(Calendar.DATE, Integer.parseInt("1", 10));//add 1 day
			
			if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
				//System.out.println("hello SATURDAY : "+calendar.getTime());
				calendar.add(Calendar.DATE, Integer.parseInt("2", 10));//byPass 2 day (SATURDAY AND SUNDAY)
			}
			else if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
				//System.out.println("hello SUNDAY : "+calendar.getTime());
				calendar.add(Calendar.DATE, Integer.parseInt("1", 10));//byPass 1 day (SUNDAY)
			}
		}
		//System.out.println("Finish: "+DateUtil.trimDate(calendar.getTime()));	
		return DateUtil.trimDate(calendar.getTime());
	}
	
//	public static String formatDateThai(Date date, String format) {
//		SimpleDateFormat dateFormat = null;
//		try {
//			dateFormat = new SimpleDateFormat(format, Locale.US);
//			date.setYear(date.getYear()+543);
//			return dateFormat.format(date);
//		} catch (Exception e) {
//			return "";
//		}
//	}
	
	public static String formatDateThaiDoc(Date date, SimpleDateFormat format){
//		SimpleDateFormat dateFormat = null;
		try {
//			dateFormat = new SimpleDateFormat(format, Locale.US);
			
			if(date.getYear() < 2500){
				date.setYear(date.getYear()+543);
			}
			
			return format.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	
	public static String getDateStrReport(Date criStartDate) {
		String dateStartReport;
		int monthlyStartValue = DateUtil.getMonth(criStartDate);
		String monthStartStr = "";
		if (monthlyStartValue == 1)
			monthStartStr = "เธกเธ�เธฃเธฒเธ�เธก";
		else if (monthlyStartValue == 2)
			monthStartStr = "เธ�เธธเธกเธ เธฒเธ�เธฑเธ�เธ�เน�";
		else if (monthlyStartValue == 3)
			monthStartStr = "เธกเธตเธ�เธฒเธ�เธก";
		else if (monthlyStartValue == 4)
			monthStartStr = "เน€เธกเธฉเธฒเธขเธ�";

		else if (monthlyStartValue == 5)
			monthStartStr = "เธ�เธคเธฉเธ เธฒเธ�เธก";
		else if (monthlyStartValue == 6)
			monthStartStr = "เธกเธดเธ–เธธเธ�เธฒเธขเธ�";
		else if (monthlyStartValue == 7)
			monthStartStr = "เธ�เธฃเธ�เธ�เธฒเธ�เธก";
		else if (monthlyStartValue == 8)
			monthStartStr = "เธชเธดเธ�เธซเธฒเธ�เธก";

		else if (monthlyStartValue == 9)
			monthStartStr = "เธ�เธฑเธ�เธขเธฒเธขเธ�";
		else if (monthlyStartValue == 10)
			monthStartStr = "เธ•เธธเธฅเธฒเธ�เธก";
		else if (monthlyStartValue == 11)
			monthStartStr = "เธ�เธคเธจเธ�เธดเธ�เธฒเธขเธ�";
		else if (monthlyStartValue == 12)
			monthStartStr = "เธ�เธฑเธ�เธงเธฒเธ�เธก";
		dateStartReport = String.valueOf(DateUtil.getDate(criStartDate)) + " " + monthStartStr + " เธ�.เธจ. " + String.valueOf(DateUtil.getYear(criStartDate) + 543);
		return dateStartReport;
	}
	
	public static String getDateStrReport2(Date criStartDate) {
		String dateStartReport;
		int monthlyStartValue = DateUtil.getMonth(criStartDate);
		String monthStartStr = "";
		if (monthlyStartValue == 1)
			monthStartStr = "เธกเธ�เธฃเธฒเธ�เธก";
		else if (monthlyStartValue == 2)
			monthStartStr = "เธ�เธธเธกเธ เธฒเธ�เธฑเธ�เธ�เน�";
		else if (monthlyStartValue == 3)
			monthStartStr = "เธกเธตเธ�เธฒเธ�เธก";
		else if (monthlyStartValue == 4)
			monthStartStr = "เน€เธกเธฉเธฒเธขเธ�";

		else if (monthlyStartValue == 5)
			monthStartStr = "เธ�เธคเธฉเธ เธฒเธ�เธก";
		else if (monthlyStartValue == 6)
			monthStartStr = "เธกเธดเธ–เธธเธ�เธฒเธขเธ�";
		else if (monthlyStartValue == 7)
			monthStartStr = "เธ�เธฃเธ�เธ�เธฒเธ�เธก";
		else if (monthlyStartValue == 8)
			monthStartStr = "เธชเธดเธ�เธซเธฒเธ�เธก";

		else if (monthlyStartValue == 9)
			monthStartStr = "เธ�เธฑเธ�เธขเธฒเธขเธ�";
		else if (monthlyStartValue == 10)
			monthStartStr = "เธ•เธธเธฅเธฒเธ�เธก";
		else if (monthlyStartValue == 11)
			monthStartStr = "เธ�เธคเธจเธ�เธดเธ�เธฒเธขเธ�";
		else if (monthlyStartValue == 12)
			monthStartStr = "เธ�เธฑเธ�เธงเธฒเธ�เธก";
		dateStartReport = String.valueOf(DateUtil.getDate(criStartDate)) + " " + monthStartStr + " " + String.valueOf(DateUtil.getYear(criStartDate) + 543);
		return dateStartReport;
	}
	
	public static String getBudgetYearTh(Date date, String format) throws ParseException
	{
		//FIXED [2015-01-15]: เธ�เธตเธ�เธ�เธ�เธฃเธฐเธกเธฒเธ“ budgetYear [1 October to 30 September] : EX. year 58 = [1 October 2557 to 30 September 2558]
		//เธ�เธตเธ�เธ�เธ�เธฃเธฐเธกเธฒเธ“ budgetYear [1 October to 30 September] : EX. year 57 = [1 October 2557 to 30 September 2558]
		String th = "";
		Integer yearTh = Integer.parseInt(DateUtil.dateToStringTH(date, "yyyy"));
		
		Date endDate = DateUtil.strToDateTH("30/09/"+(yearTh), "dd/MM/yyyy");//FIX
		
		Integer currentDateInt = Integer.parseInt(DateUtil.dateToStringTH(date, "yyyyMMdd"));
		Integer endDateInt = Integer.parseInt(DateUtil.dateToStringTH(endDate, "yyyyMMdd"));
		
		if(currentDateInt > endDateInt){
			th = String.valueOf(Integer.parseInt(DateUtil.dateToStringTH(date, format))+1);
		}
		else{
			th = String.valueOf(Integer.parseInt(DateUtil.dateToStringTH(date, format)));
		}
		return th;
	} 
	
	public static String getAcademicYearTh(Date date, String format) throws ParseException
	{
		//FIXED [2016-12-15]: เธ�เธตเธ�เธฒเธฃเธจเธถเธ�เธฉเธฒ academicYear [1 June to 31 May] : EX. year 60 = [1 June 2560 to 31 May 2561]
		String th = "";
		Integer yearTh = Integer.parseInt(DateUtil.dateToStringTH(date, "yyyy"));
		
		Date startDate = DateUtil.strToDateTH("01/06/"+(yearTh), "dd/MM/yyyy");//FIX
		Date endDate = DateUtil.strToDateTH("31/05/"+(yearTh+1), "dd/MM/yyyy");//FIX
		
		Integer currentDateInt = Integer.parseInt(DateUtil.dateToStringTH(date, "yyyyMMdd"));
		Integer startDateInt = Integer.parseInt(DateUtil.dateToStringTH(startDate, "yyyyMMdd"));
		Integer endDateInt = Integer.parseInt(DateUtil.dateToStringTH(endDate, "yyyyMMdd"));
		
		if(currentDateInt >= startDateInt && currentDateInt <= endDateInt){
			th = String.valueOf(Integer.parseInt(DateUtil.dateToStringTH(date, format)));
		}
		else if(currentDateInt > endDateInt){
			th = String.valueOf(Integer.parseInt(DateUtil.dateToStringTH(date, format))+1);
		}
		else{
			th = String.valueOf(Integer.parseInt(DateUtil.dateToStringTH(date, format))-1);
		}
		return th;
	}    
	
	public static String getTermAndAcademicYearTh(Date date, String format) throws ParseException
	{
		//เธ�เธตเธ�เธฒเธฃเธจเธถเธ�เธฉเธฒ academicYear Term1 [1 August to 30 November] : EX. year 59 = [1 August 2559 to 30 November 2559]
		//เธ�เธตเธ�เธฒเธฃเธจเธถเธ�เธฉเธฒ academicYear Term2 [1 January to 30 April] : EX. year 59 = [1 January 2560 to 30 April 2560]
		//เธ�เธตเธ�เธฒเธฃเธจเธถเธ�เธฉเธฒ academicYear Summer [1 May to 30 June] : EX. year 59 = [1 May 2560 to 30 June 2560]
		
		String term = "";
		Integer yearTh = Integer.parseInt(DateUtil.dateToStringTH(date, "yyyy"));
		
		Date startDateTerm1 = DateUtil.strToDateTH("01/08/"+(yearTh), "dd/MM/yyyy");//FIX
		Date endDateTerm1 = DateUtil.strToDateTH("30/11/"+(yearTh), "dd/MM/yyyy");//FIX
		
		Date startDateTerm2 = DateUtil.strToDateTH("01/01/"+(yearTh+1), "dd/MM/yyyy");//FIX
		Date endDateTerm2 = DateUtil.strToDateTH("30/04/"+(yearTh+1), "dd/MM/yyyy");//FIX
		
		Date startDateSummer = DateUtil.strToDateTH("01/05/"+(yearTh+1), "dd/MM/yyyy");//FIX
		Date endDateSummer = DateUtil.strToDateTH("30/06/"+(yearTh+1), "dd/MM/yyyy");//FIX
		
		Integer currentDateInt = Integer.parseInt(DateUtil.dateToStringTH(date, "yyyyMMdd"));
		
		Integer startDateTerm1Int = Integer.parseInt(DateUtil.dateToStringTH(startDateTerm1, "yyyyMMdd"));
		Integer endDateTerm1Int = Integer.parseInt(DateUtil.dateToStringTH(endDateTerm1, "yyyyMMdd"));
		
		Integer startDateTerm2Int = Integer.parseInt(DateUtil.dateToStringTH(startDateTerm2, "yyyyMMdd"));
		Integer endDateTerm2Int = Integer.parseInt(DateUtil.dateToStringTH(endDateTerm2, "yyyyMMdd"));
		
		Integer startDateSummer1Int = Integer.parseInt(DateUtil.dateToStringTH(startDateSummer, "yyyyMMdd"));
		Integer endDateSummerInt = Integer.parseInt(DateUtil.dateToStringTH(endDateSummer, "yyyyMMdd"));
		
		if(currentDateInt >= startDateTerm1Int && currentDateInt <= endDateTerm1Int){
			term = "1/"+String.valueOf(Integer.parseInt(DateUtil.dateToStringTH(date, format)));
		}
		else if(currentDateInt >= startDateTerm2Int && currentDateInt <= endDateTerm2Int){
			term = "2/"+String.valueOf(Integer.parseInt(DateUtil.dateToStringTH(date, format))-1);
		}
		else if(currentDateInt >= startDateSummer1Int && currentDateInt <= endDateSummerInt){
			term = String.valueOf(Integer.parseInt(DateUtil.dateToStringTH(date, format))-1);
		}
		return term;
	}
	
	public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int workDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++workDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

	    System.out.println(workDays);
	    
	    return workDays;
	}
}
