<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수시활동 검토</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/CheckOccasionalSafeManagement.js"></script>
<script src="js/submit.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">
<link rel="stylesheet" type="text/css" href="css/datepicker.css">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="sub_contents_wrap">	

		<article class="sub_title">
			<span>수시활동 검토</span>
		</article>
			
		<article class="cur_page">
			<div id="title">
			홈<span>></span>지휘 및 통제<span>></span>수시활동 검토
			</div>		
		</article>
		
		<br>
	
		<section class="subContent_section" id="AssignTask">
		<jsp:include page="RequestSearch.jsp"></jsp:include>
		<table class="table table-stripped sub_table table01" id="table_result" style="text-align:center;width:90%; margin:auto;margin-top:10px;">
			<thead class="thead_title">
			<tr>
				<th>활동 일자</th>
				<th>활동 유형</th>
				<th>요청자</th>
				<th>요청 일자</th>
				<th>상태</th>
				<th>선택</th>
			</tr>
			</thead>
			<tbody>
			<c:choose>
			<c:when test="${fn:length(RequestList) eq 0}">
				<tr>
					<td colspan = 6>
						데이터가 없습니다.
					</td>
			</c:when>
			
			<c:otherwise>
			<c:forEach var="list" items="${RequestList}" varStatus="statics">
				<tr>
					<td>${list.actvt_date}</td>
					<td>${list.incdt_actvt_type_cd}</td>
					<td>${list.rqstr_srvno}</td>
					<td>${list.rqst_date}</td>
					<td>${list.state_cd}</td>
					<td>
						<button type="button" class="btn btn-sm btn-success" 	id="modify" onclick="Approve_select('${list.seq}')"  value=${list.seq}><i class="fas fa-check"></i>&nbsp;선택</button>
					</td>
				</tr>
			</c:forEach>
			</c:otherwise>
			</c:choose>
			</tbody>
			<tfoot>
			</tfoot>
		</table>
			<br>
			
			<table style="width:100%; margin:auto;">
			<tr>
			<td style="text-align:center; width:100%">
				<div class="pagination_fixed">
					<form action="CheckOccasionalSafeManagement.do" method="post" name="checkOccForm">
						<c:if test="${not empty paginationInfo}">
							<div class="pagination">
								<ul class="pagination -sm">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_checkOcc_page" />
								</ul>
							</div>
						</c:if>
						<input type="hidden" id="currentPageNo" name="currentPageNo" />
					</form>
				</div>
			</td>
			</tr>
			</table>
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
</body>
</html>