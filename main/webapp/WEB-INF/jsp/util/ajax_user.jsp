<%@ page import="java.util.*" %>
<%@ page import="egovframework.main.VO.UserVO" %>
<%@ page import="egovframework.main.common.UserDAO" %>
<%@ include file="./function.jsp" %>
<%@ include file="./crypto.jsp" %>
<jsp:useBean id="UserManager" scope="request" class="egovframework.main.sessionManager.UserManager"/>
<%
	String command = Filter(request.getParameter("action"),"","");
	String srvno = Filter(request.getParameter("srvno"),"","");if(!srvno.equals("")){srvno = ah.decrypt(srvno);}
	String pw = Filter(request.getParameter("pw"),"","");
/* 	String fulnm = Filter(request.getParameter("fulnm"),"","");if(!fulnm.equals("")){fulnm = ah.decrypt(fulnm);}
	String email = Filter(request.getParameter("email"),"","");if(!email.equals("")){email = ah.decrypt(email);}
	String hp = Filter(request.getParameter("hp"),"","");if(!hp.equals("")){hp = ah.decrypt(hp);}
	String pw = Filter(request.getParameter("pw"),"","");
	String oripw = Filter(request.getParameter("oripw"),"","");
	String chpw = Filter(request.getParameter("chpw"),"","");		 */
	
	if(command != null)
	{
		if(command.equals("login"))
		{			
			String status = UserManager.getUserStatus(srvno);
			int errCmd = 0; // default
			
			if(status == "" || status == null)
			{
				errCmd = -1; // 해당 군번은 사용자로 등록되어있지 않습니다.
			}
			else
			{
				if(UserManager.CountChangePwDate(srvno) >= 30)
				{
					errCmd = -4; // 비밀번호 변경한지 30일이 지나 접속이 불가한 상태입니다. 비밀번호를 변경해 주십시오.
				}
				else
				{
					// password err
					if(status.equals("K1"))
					{
						UserManager.IncErr(srvno);
						if( UserManager.CountErr(srvno) >= 5 )
						{
							errCmd = -3;	// 비밀번호 5회 오류로 잠금상태로 전환됩니다.
						}
						else
							errCmd = -2;	// 비밀번호가 맞지 않습니다.
					}
				}	
			}			
			
			out.println(errCmd);
		}
	}
%>