package egovframework.main.service.VO;

public class DateVO {
	private String year = "";		
	private String month = "";
	private String day = "";	
	private String date = "";
	
	// 합 8자리 20190101
	public void setDate() {
		date = year + month + day;
	}
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
}
