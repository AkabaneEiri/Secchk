package egovframework.main.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletResponse;


public class CustomTimerTask extends TimerTask{
	private PrintWriter out = null;
	private HttpServletResponse httpResponse = null;
	
	private String scriptStr = "<script>alert('비밀번호가 맞지않습니다.');</script>";
	
	private CustomTimerTask() {	}	
	
	private static class LazyHolder {
		public static final CustomTimerTask INSTANCE = new CustomTimerTask();
	}
	
	public static CustomTimerTask getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public PrintWriter getWriter() { return out; }
	
	public void setWriter(PrintWriter writer) {
		System.out.println("before set out : "+ out);
		System.out.println("before set writer : "+ writer);
		out = writer;
		System.out.println("after set out : "+ out);
	}
	
	public int getStrLength() { return scriptStr.length(); }
	
	public void setResponse(HttpServletResponse response) throws IOException { 
		System.out.println("in set function");
		httpResponse = response;
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("before set : " + out);
		out = response.getWriter();
		System.out.println("after set : " + out);
	}
	
	public void flush() {
		out.flush();
		out.close();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub		
		System.out.println("start run()");
		System.out.println("out : " + out);
		out.println(scriptStr+"tttt");
		//out.flush();
		//out.close();
		System.out.println("finish run()");
	}
}
