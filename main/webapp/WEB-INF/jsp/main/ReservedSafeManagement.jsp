<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- seungwon 19.02.14 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.14 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>예정된 안전관리활동</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<!-- seungwon 19.02.14 -->
<script src="js/submit.js" type="text/javascript"></script>
<!-- seungwon 19.02.14 -->
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!-- seungwon 19.02.17 detail css -->
<link href="css/detail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">
</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
		
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>예정된 안전관리활동</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
				홈<span>></span>예정된 안전관리활동
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		
		<!-- seungwon 19.02.14 -->
		<div class="table_margin">
		
		<p style="text-align:center;">
		<img src="images/title_img/ReservedSafeManagement.png" alt="예정된 안전관리활동"  style="width:330px; height:80px;">
		</p> 
		
		<table class="table table-striped indexboard sub_table">
		<thead class="thead_title">
			<tr>
				<th><c:out value="일자"/></td>
				<th><c:out value="부대활동"/></td>
				<th><c:out value="과업"/></td>
				<th><c:out value="상태"/></td>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${fn:length(taskList)==0}">
				<c:choose>
					<c:when test="${SS_athrt == 'M'}">
						<tr>
							<td colspan="6">현재 부여된 과업이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">현재 부여된 과업이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>				
				<c:forEach var="mmLS" items="${taskList}" varStatus="status">
					<tr>
						<c:if test="${SS_athrt == 'M'}">
							<td><input type="checkbox" name="check" title="체크"/></td>
						</c:if>
							<td><c:out value="${mmLS.actvt_date}"/></td> <!-- 일자 -->
							<td>
								<!-- <a href="javascript:fn_go_checkList('<c:out value="${mmLS.incdt_actvt_type_cd}"/>', '${mmLS.actvt_date}')"> -->
									<c:out value="${mmLS.incdt_actvt_type_cd_nm}"/>
								<!-- </a> -->
							</td> <!-- 부대활동명 -->
							<td><c:out value="${mmLS.task}"/></td> <!-- 과업 -->							
						
						<c:choose>
							<c:when test="${fn:trim(mmLS.state_cd) == 'E1'}">
								<td><button type="submit" class="btn btn-sm btn-primary btn-width" id="Start" onclick="javascript:fn_set_starttime('${mmLS.id}', '${mmLS.seq}')">시작</button></td>
							</c:when>
							<c:when test="${fn:trim(mmLS.state_cd) == 'E2'}">
								<td><button type="submit" class="btn btn-sm btn-success btn-width" id="doing" onclick="javascript:fn_go_checkList('${mmLS.id}', '${mmLS.task_psnchnrg_srvno}')">진행중</button></td>
							</c:when>
							<c:otherwise>								
								<td><button type="submit" class="btn btn-sm btn-secondary btn-width" id="End" onclick="javascript:fn_go_checkList('${mmLS.id}', '${mmLS.task_psnchnrg_srvno}')">종료</button></td>
							</c:otherwise>
						</c:choose>				
						<!-- <input type="hidden" id="taskSeq" value="${mmLS.seq}"/> --> 
					</tr>
				</c:forEach>				
				<form:form commandName="listsearchVO" name="checklistForm" method="post">
					<form:hidden path="findCode"/>
					<form:hidden path="task"/>
					<form:hidden path="seq"/>
					<form:hidden path="date"/>
					<form:hidden path="actId"/>
					<form:hidden path="srvno"/>
				</form:form>				
			</c:otherwise>
		</c:choose>
		</tbody>
		</table>
		</div>
		<!-- seungwon 19.02.14 -->
		<br>
		</section>	
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
	
</body>
</html>
