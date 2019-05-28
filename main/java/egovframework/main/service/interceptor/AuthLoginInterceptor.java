package egovframework.main.service.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{
	
	// 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		HttpSession httpSession = request.getSession();
		Object obj = httpSession.getAttribute("loginedUser");
		
		//System.out.println("request url : " + request.getRequestURI());
		
		if( obj == null ) {
			response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            if (request.getRequestURI() == "/Infomation.do" || request.getRequestURI() == "/mobile/Infomation.do") {
            	out.println("<script>alert('로그인이 필요한 서비스입니다.'); window.close();</script>");
            }
            else {
            	out.println("<script>alert('로그인이 필요한 서비스입니다.'); location.href='index.do';</script>");
            }            

            out.flush(); 
            
			return false;
		}
			return true;		
	}
	
	// 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
	}
}
