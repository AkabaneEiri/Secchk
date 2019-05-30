<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">

<link href="<%=request.getContextPath()%>/css/detail.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/dropdown.css" rel="stylesheet">

<script src="js/dropdown.js"></script>
<title>안전관리 체크리스트체계</title>
</head>
<body>
	<%
	Object obj = session.getAttribute("loginedUser");
	String rnk = (String) session.getAttribute("SS_RNK");
	String nm = (String) session.getAttribute("SS_NM");
	String idtf = (String) session.getAttribute("SS_IDTF");
	String athrt = "";
	String monitor = (String) session.getAttribute("SS_MNT");
	
	if(session.getAttribute("SS_ATHRT") != null) {
		athrt = ( (String) session.getAttribute("SS_ATHRT") ).trim();
	}	
	%>
	<header class="header">
			<div class="login" style="text-align:right;">
            	<%
            	if(obj != null) {
            	%>
            	<span class="userInfo idtf_in_header"><c:out value="<%=idtf%>"/></span>
            	<span class="userInfo"><c:out value="<%=rnk%>"/> / <c:out value="<%=nm%>"/></span>
				<%
            	}
				%>
			
				<span class="">
				<%						
				if(obj == null) {
				%>
					<a href="login.do">
						<img src="<%=request.getContextPath()%>/images/login.png">
					</a>
				<%
				}
				else {
				%>
					<a href="logout.do">
						<img src="<%=request.getContextPath()%>/images/logout.png">
					</a>
				<%
				}
				%>
				</span>
			</div>
			<table class="header_table">
				<tr>
					<td style="max-width:270px; height:53px; padding-top:5px;">
						<h1 class="logo"><a href="index.do">
						<img src="images/m_logo_3.png"></a></h1>
					</td>
					<td style="width:100%;">
						<nav id="top_nav" class="">
	                		<ul class="" style="margin-top:0px;">
			                	<%
			                	if (obj == null || athrt.equals("B1")) {
			                	%>
			                		<li>
			                    		<a href="ReservedSafeManagement.do">예정된 안전관리활동</a>
			                   		</li>
			                    	<li>
			                    		<a href="OccasionalSafeManagement.do">수시 안전관리활동</a>
			                    	</li>
			                    	<li>
			                    		<a href="CheckListManagement.do">체크리스트</a>
			                    	</li>
			                    	<%
			                    	if (obj != null && monitor.equals("C1")) {
			                    	%>
			                    	<li class="item-has-children">
			                    		<a href="#0">지휘 및 통제</a>
			                    		<ul class="sub-menu" style="display: none;">
											<li><a href="TroopsMonitoring.do"><img src="images/cd_bullet.png">&nbsp;부대활동 모니터링</a></li>
										</ul>
			                    	</li>
			                    	<%
			                    	}
			                    	%>
			                	<%	
			                	}
			                	
			                	if (obj == null || (athrt.equals("B2") || athrt.equals("B3"))) {
			                	%>
			                   	
			                    <li class="item-has-children">
			                    	<a href="#0">지휘 및 통제</a>
			                    	<ul class="sub-menu" style="display: none;">
										<li><a href="TroopsMonitoring.do"><img src="images/cd_bullet.png"><span>&nbsp;부대활동 모니터링</span></a></li>
										<li><a href="AssignTask.do"><img src="images/cd_bullet.png">&nbsp;과업부여</a></li>
										<li><a href="EnterCommanderGuide.do"><img src="images/cd_bullet.png">&nbsp;지휘관지침 입력</a></li>
										<li><a href="CheckApproval.do"><img src="images/cd_bullet.png">&nbsp;조치 요청</a></li>
										<li><a href="CheckOccasionalSafeManagement.do"><img src="images/cd_bullet.png">&nbsp;수시안전관리활동 검토</a></li>
									</ul>
			                    </li>
			                    <li class="item-has-children">
			                    	<a href="#0">시스템관리</a>
			                    	<ul class="sub-menu" style="display: none;">
										<li><a href="Userlist.do"><img src="images/cd_bullet.png">&nbsp;사용자 관리</a></li>
										<li><a href="SelectCheckListItem.do"><img src="images/cd_bullet.png">&nbsp;체크리스트항목 선별</a></li>
										<li><a href="ManageCheckListItem.do"><img src="images/cd_bullet.png">&nbsp;체크리스트항목 관리</a></li>
										<li><a href="CheckRequestedCheckList.do"><img src="images/cd_bullet.png">&nbsp;요청한 체크리스트 검토</a></li>
										<li><a href="ManageCode.do"><img src="images/cd_bullet.png">&nbsp;코드관리</a></li>
									</ul>
			                    </li>
			                    <%
			                	}
			                    %>
			                    
			                    <!-- 최고관리자 B4 -->
			                    <%
			                    if (obj != null && athrt.equals("B4")) {
			                	%>
			                   	
			                    <li class="item-has-children">
			                    	<a href="#0">지휘 및 통제</a>
			                    	<ul class="sub-menu" style="display: none;">
										<li><a href="TroopsMonitoring.do"><img src="images/cd_bullet.png">&nbsp;부대활동 모니터링</a></li>
									</ul>
			                    </li>
			                    <%
			                	}
			                    %>
			                </ul>
	            		</nav>
					</td>
				</tr>
			</table>
            
            <span class="menu-toggle-btn">
                <span></span>
                <span></span>
                <span></span>
            </span>
        </header>
</body>
</html>