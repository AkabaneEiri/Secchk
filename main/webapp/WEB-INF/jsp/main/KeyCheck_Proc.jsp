<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%

String srvno = request.getParameter("srvno");
String certno = request.getParameter("certno");
String system_cd = "regula";  // local test???
String vfcturl = "http://army.mil.kr/vfct/vfctP.do";
String receiveurl = "http://10.1.19.103:8080/eGovTest/rglt_ph/jsp/register.jsp";

//	String temp = request.getSession(false).setAttribute("srvno", srvno);

String temp = (String) request.getSession(false).getAttribute("srvno");

%>

<form name="keycheck" action="http://www.army.mil.kr/vfct/vfctP.do" method="post">
	<input type="hidden" name="srvno" value="<%=srvno%>" />
	<input type="hidden" name="certno" value="<%=certno%>" />
	<input type="hidden" name="system_cd" value="<%=system_cd%>" />
	<input type="hidden" name="vfcturl" value="<%=vfcturl%>" />
	<input type="hidden" name="receiveurl" value="<%=receiveurl%>" />
</form>

<script>
	document.keycheck.submit();
</script>