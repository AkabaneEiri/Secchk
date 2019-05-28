<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!-- seungwon 19.02.21 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.21 -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>부대활동 모니터링</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script src="<%=request.getContextPath()%>/js/fontawesome-all.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.js"></script>
<!-- seungwon 19.02.21 -->
<script src="<%=request.getContextPath()%>/js/submit.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/detail.css">

<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/swiper.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main_detail.css">

</head>

<body>
		<jsp:include page="Header.jsp"></jsp:include>
		
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>부대활동 모니터링</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>부대활동 모니터링
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">	
		
		<%
		HttpSession httpSession = request.getSession(false);
		httpSession.setMaxInactiveInterval(1800);
		String SS_SN = (String)httpSession.getAttribute("SS_SN");
		String SS_RANK = (String)httpSession.getAttribute("SS_RANK");
		String SS_NAME = (String)httpSession.getAttribute("SS_NAME");
		String SS_UN = (String)httpSession.getAttribute("SS_UN");
		String SS_PN = (String)httpSession.getAttribute("SS_PN");
		%>
		<form id="identify" method="post" action="<c:out value="${identity_page}"/>">
			<input type="hidden" name="originalurl" value="<c:out value="${originalurl}"/>"/>
			<input type="hidden" name="sn" value="<c:out value="${sn}"/>"/>
			<input type="hidden" name="key" value="<c:out value="${SS_KEY}"/>"/>
			<input type="hidden" name="parm7" value="UTF8"/>
			
			<script language="JavaScript">
				window.onload = function(){
					sn = "<%= SS_SN %>";
					if(sn == "null" || sn == null || sn == "") {
						document.getElementById("identify").submit();	// 인증
					}
					else {
						location.href = "<c:url value='Authorization.do'/>";
					}
				}
			</script>
		</form>
		
		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>
