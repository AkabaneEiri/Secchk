package egovframework.main.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import egovframework.main.util.eventListener.PushEventListener;
import egovframework.main.util.eventListener.PushService;


public class CustomTimerTask extends TimerTask{
	private HttpServletResponse httpResponse = null;
	
	private String scriptStr = "<script>alert('비밀번호가 맞지않습니다.');</script>";
	
	private CustomTimerTask() {	}	
	
	@Bean
	public PushService pushService() {
		PushService push = new PushService();		
		return push;
	}
	
	@Bean
	public PushEventListener pushEventListener() {
		PushEventListener pushListener = new PushEventListener();		
		return pushListener;
	}
	
	private static class LazyHolder {
		public static final CustomTimerTask INSTANCE = new CustomTimerTask();
	}
	
	public static CustomTimerTask getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public int getStrLength() { return scriptStr.length(); }
	
	public void setResponse(HttpServletResponse response) { 
		httpResponse = response;
	}
	
	@Override
	public void run() {
	}
}
