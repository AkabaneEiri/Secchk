<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	Object year = session.getAttribute("SS_YEAR");
	Object mon = session.getAttribute("SS_MON");
	Object day = session.getAttribute("SS_DAY");
	
	pageContext.setAttribute("curYear", year);
	pageContext.setAttribute("curMon", mon);
	pageContext.setAttribute("curDay", day);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수시 안전관리 활동</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/AssignTask.js"></script>
<script src="js/jquery-ui.min.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">

<script src="js/submit.js"></script>
 
</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>수시 안전관리 활동</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>수시 안전관리 활동
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">

		<p style="text-align:center;">
			<img src="images/title_img/OccasionalSafeManagement_Request.png" alt="수시 안전관리활동 요청"  style="width:330px; height:80px;">
		</p>
		
		<table class="table table-striped sub_table table01" style="text-align:center;margin:auto;">
		<colgroup>
			<col width="30%"/>
			<col width="70%"/>
		</colgroup>
		
		<tr>
			<th scope="30%" style="text-align:center;">대분류</td>
			<td scope="70%">
				<form method="post" name="selectedLarge" id="selectedLarge" action="">
					<input type="hidden" id="largeCode" value=""/>
					<input id="selected_large" name="selected_large" 
					class="input-text-disabled"
					style="font-size: 14pt;color: #757575;"
					value="" disabled="disabled"/>
				</form>
			</th>
		</tr>			
		<tr>
			<th scope="30%" style="text-align:center;">중분류</th>
			<td scope="70%">
				<form method="post" name="selectedMiddle" id="selectedMiddle" action="">
					<input type="hidden" id="middleCode" value=""/>
					<input id="selected_middle" name="selected_middle"
					style="font-size: 14pt;color: #757575;" 
					class="input-text-disabled" 
					value="<c:out value='${membersearchVO.searchKeyword}'/>" disabled="disabled"/>
				</form>
			</td>
		</tr>
		<tr>
			<th scope="30%" style="text-align:center;">부대활동코드</th>
			<td scope="70%">
				<form method="post" name="selectedCode" action="">
					<input id="selected_code" name="selected_code" 
					class="input-text-disabled"
					style="font-size: 14pt;color: #757575;"
					value="" disabled="disabled"/>
				</form>
			</th>
		</tr>
		<tr>
			<th scope="30%" style="text-align:center;">부대활동명</th>
			<td scope="70%">
				<form method="post" name="selectedName" action="" style="float: left; margin: 0;">
					<input id="selected_name" name="selected_name" 
					class="input-text-disabled"
					style="font-size: 14pt;color: #757575; width:140px;"
					value="" disabled="disabled"/>
				</form>
					
				<button type="Search" class="btn btn-sm btn-primary btn-width" data-toggle="modal" data-target="#TaskSearch" style="margin-left: 10px;"><i class="fas fa-search"></i>&nbsp;검색</button>
				<jsp:include page="taskSearchModal.jsp"></jsp:include>
			</th>
		</tr>
		<tr>
			<th scope="30%" style="text-align:center;">일시</th>
			<td scope="70%" style="text-align: left">					
				
			<select id="activityYear" style="height:30px;">
			<c:set var="year" value="2019"/>
			<c:forEach begin="2019" end="2030">
				<c:choose>
					<c:when test="${year == curYear }">
						<option value="${year}" selected="selected">${year}</option>
					</c:when>
					<c:otherwise>
						<option value="${year}">${year}</option>
					</c:otherwise>
				</c:choose>								
				<c:set var="year" value="${year+1}"/>
			</c:forEach>
			</select>
			<select id="activityMonth" style="height:30px;">
			<c:set var="month" value="1"/>
			<c:forEach begin="1" end="12">
				<c:choose>
					<c:when test="${month == curMon }">
						<option value="${month}" selected="selected">${month}</option>
					</c:when>
					<c:otherwise>
						<option value="${month}">${month}</option>
					</c:otherwise>
				</c:choose>	
				<c:set var="month" value="${month+1}"/>
			</c:forEach>
			</select>
			<select id="activityDay" style="height:30px;">
			<c:set var="day" value="1"/>
			<c:forEach begin="1" end="31">
				<c:choose>
					<c:when test="${day == curDay }">
						<option value="${day}" selected="selected">${day}</option>
					</c:when>
					<c:otherwise>
						<option value="${day}">${day}</option>
					</c:otherwise>
				</c:choose>	
				<c:set var="day" value="${day+1}"/>
			</c:forEach>
			</select>									
										
			</th>
		</tr>
		<tr>
			<th scope="30%" style="text-align:center;">사유</th>
			<td scope="70%">
				<div style="text-align: center; vertical-align: top;">
					<textarea id="rsn_area" name="rsn_area" rows="3" cols="10" placeholder="사유를 입력해주십시오"
					style="width: 90%; font-size: 16px; padding: 10px; box-shadow : 0px 0px 1px #757575;border:none;"></textarea>
				</div>
			</th>
		</tr>				
		</table>
		
		<div style="text-align: center; margin-top: 10px;">
			<button type="button" class="btn btn-sm btn-primary btn-width" id= "ok" onclick="javascript:fn_submit_safeRequest()">
				<i class="fas fa-check"></i>&nbsp;확인</button>
			<button type="button" class="btn btn-sm btn-primary btn-width" id= "back" onclick="javascript:history.back();">
				<i class="fas fa-undo"></i>&nbsp;취소</button>
		</div>


		<form:form commandName="requestActivityVO" name="safeRequestForm" method="post">
						<form:hidden path="actvt_date"/>
						<form:hidden path="rsn"/>
						<form:hidden path="ctlg_cd"/>
		</form:form>
		
		</section>	
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>
</body>
</html>
