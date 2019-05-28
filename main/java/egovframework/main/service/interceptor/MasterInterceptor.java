package egovframework.main.service.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MasterInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		Object obj = httpSession.getAttribute("loginedUser");
		String athrt = (String) httpSession.getAttribute("SS_ATHRT");

		if (obj != null) {
			if (athrt != null && athrt != "") {
				if(!athrt.equals("B3")) {
					response.setContentType("text/html; charset=UTF-8");

					PrintWriter out = response.getWriter();

					out.println("<script>alert('B3 / 해당 페이지에 권한이 없습니다.'); location.href='index.do';</script>");

					out.flush();

					return false;
				}			
			}
			else {
				response.setContentType("text/html; charset=UTF-8");

				PrintWriter out = response.getWriter();

				out.println("<script>alert('권한이 부여되지않은 사용자입니다. 관리자에게 문의하십시오.'); location.href='index.do';</script>");

				out.flush();
				
				return false;
			}			
		}
		return true;
	}

	// 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}
