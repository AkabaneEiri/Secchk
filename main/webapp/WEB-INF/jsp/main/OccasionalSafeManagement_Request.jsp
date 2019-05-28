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
<title>수시 활동</title>
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
<script src="js/engine.js"></script> 
<script src="js/OccasionalSafeManagement_Request.js"></script> 
</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>수시 활동</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>수시 활동<span>></span>등록
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		<div class="table_margin">	
		<!-- 
		<div style="text-align: left;font-size: 14px;"><span class="import_marker">&nbsp;*</span><span>&nbsp;는 필수입력 항목입니다.</span></div>
		 -->
		<table class="table table-striped sub_table table01" style="text-align:center;margin:auto;margin-top:10px;margin-bottom:10px;">
		<colgroup>
			<col width="23%"/>
			<col width="80%"/>
		</colgroup>
		<tr>
			<th style="text-align:center;"><!-- <span class="import_marker">* </span> -->일시</th>
			<td style="text-align: left">					
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
			</td>
		</tr>
		<tr>
			<!-- <th rowspan="4">활<br>동<br>유<br>형</th> -->
			<th style="text-align:center;">대분류</td>
			<td style="text-align: left">
				<select id="largeCondition" name="largeCondition" onchange="LargeChange(this)" style="width:78%;height:30px;">
					<option value="">선택</option>
						<c:forEach var="largeList" items="${largeList}" varStatus="statics">
							<option id="${fn:trim(largeList.cd)}" value="${fn:trim(largeList.cd)}"><c:out value="${largeList.cd_nm}"/></option>
						</c:forEach>
				</select>
			</th>
		</tr>			
		<tr>
			<th style="text-align:center;">중분류</th>
			<td style="text-align: left">
				<select id="middleCondition" name="middleCondition" onchange="MiddleChange(this)" style="width:78%;height:30px;">
					<option value="">선택</option>
				</select>
			</td>
		</tr>
		<tr>
			<th rowspan="2" style="text-align:center;"><!-- <span class="import_marker">* </span> -->세부활동</th>
			<td style="text-align: left">
				<select id="select_condition" name="select_condition" style="height: 31px;">
					<option value="ctlg_nm">세부활동</option>
					<option value="ctlg_cd">세부활동 코드</option>
				</select>
				<input type="text" id="input_text_condition" class="sub_input" style="width:40%;height:30px;box-shadow : 0px 0px 1px #757575;border:none;"/>
				<button type="button" class="btn btn-sm btn-primary"onclick="onClick_Search()" style="vertical-align: top;"><i class="fas fa-search"></i>&nbsp;검색</button>
			</td>
		</tr>
		<tr>
			<td style="text-align: left">
				<select id="activity" name="activity" onchange="ActivityChange(this)" style="width:78%;height:30px;">
					<option value="">선택</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<th style="text-align:center;"><!-- <span class="import_marker">* </span> -->사유</th>
			<td>
				<div style="text-align: center; vertical-align: top;">
					<textarea id="rsn_area" name="rsn_area" rows="3" cols="10" placeholder="사유를 입력해주십시오"
					style="width: 90%; padding: 10px; box-shadow : 0px 0px 1px #757575;border:none;" onkeydown="javascript:fn_maxLength(this, 100)"></textarea>
				</div>
			</td>
		</tr>				
		</table>
		
		<div style="text-align: center; margin-top: 10px;">
			<button type="button" class="btn btn-sm btn-primary btn-width" id= "ok" onclick="javascript:fn_submit_safeRequest()">
				<i class="fas fa-check"></i>&nbsp;저장</button>
			<button type="button" class="btn btn-sm btn-primary btn-width" id= "back" onclick="javascript:location.href = 'OccasionalSafeManagement.do';">
				<i class="fas fa-undo"></i>&nbsp;취소</button>
		</div>


		<form:form commandName="requestActivityVO" name="safeRequestForm" method="post">
						<form:hidden path="actvt_date"/>
						<form:hidden path="rsn"/>
						<form:hidden path="ctlg_cd"/>
		</form:form>
		</div>
		</section>	
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>
</body>
</html>
