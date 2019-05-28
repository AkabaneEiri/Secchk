package egovframework.main.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	SimpleDateFormat dateFormat = null;
	
	// year
	public static int getCurrentYear() throws Exception {
		return Calendar.getInstance().get(Calendar.YEAR);
	}	
	
	// month
	public static int getCurrentMonth() throws Exception {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	
	// day
	public static int getCurrentDay() throws Exception {
		return Calendar.getInstance().get(Calendar.DATE);
	}
	
	// Today Date ( date only )
	public String getTodayDate(boolean isKorea) throws Exception {
		if(dateFormat != null) {
			dateFormat = null;
		}
		
		if(isKorea == true)
			dateFormat = new SimpleDateFormat ( "yyyy년 MM월dd일");
		else
			dateFormat = new SimpleDateFormat ( "yyyy-MM-dd");
			
		String date = dateFormat.format(Calendar.getInstance().getTime());
		return date;
	}
	
	// Today time
	public String getTodayTime(boolean isKorea) throws Exception {
		if(dateFormat != null) {
			dateFormat = null;
		}
		
		if(isKorea == true)
			dateFormat = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
		else
			dateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
			
		String time = dateFormat.format(Calendar.getInstance().getTime());
		return time;
	}
	
	// get now
	public static Date getNow() { return Calendar.getInstance().getTime(); }
}
