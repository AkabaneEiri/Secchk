package egovframework.main.service.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.main.util.CustomTimerTask;

public class TimerInterceptor extends HandlerInterceptorAdapter{
	// 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {		
		
			
		return true;
	}

	// 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
//		System.out.println("timer interceptor start");
//		if (CustomTimerTask.getInstance() != null) {
//			System.out.println("timer interceptor in if");
//			CustomTimerTask.getInstance().setResponse(response);
//		}
//		System.out.println("timer interceptor finish");
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
