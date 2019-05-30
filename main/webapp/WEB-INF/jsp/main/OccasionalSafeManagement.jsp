<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- seungwon 19.02.27 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.27 -->   
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>수시 안전관리 활동</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">

<script src="js/submit.js"></script>
<link href="css/detail.css" rel="stylesheet">

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
		<div class="table_margin">
		<p style="text-align:center;">
			<img src="images/title_img/OccasionalSafeManagement.png" alt="요청한 수시 안전관리활동"  style="width:330px; height:80px;">
		</p> 
		
		<table class="table table-striped indexboard sub_table table01" style="margin:auto;margin-top:10px;margin-bottom:10px;">
		<colgroup>
			<col width="25%"/>
			<col width="45%"/>
			<col width="20%"/>
			<col width="10%"/>
		</colgroup>
		<thead class="thead_title" style="text-align: center;">
			<tr>
				<th>일자</th>
				<th>요청한 활동</th>
				<th>결과</th>
				<th>상세</th>
			</tr>
		</thead>
		<tbody>			
			<c:choose>
				<c:when test="${fn:length(requestList) == 0}">
					<tr><td colspan="4">요청한 안전관리 활동이 없습니다.</td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="rqstLS" items="${requestList}" varStatus="status">
						<tr>
							<td>${rqstLS.actvt_date}</td>
							<td>${rqstLS.incdt_actvt_type_cd_nm}</td>
							<td style="text-align: center">
								<c:choose>
									<c:when test="${fn:trim(rqstLS.state_cd) == 'D1'}">검토 중</c:when>
									<c:when test="${fn:trim(rqstLS.state_cd) == 'D2'}">승인 완료</c:when>
									<c:otherwise>반려</c:otherwise>
								</c:choose>								
							</td>
							<td>								
								<button type="modify" class="btn btn-sm btn-success btn-width" id="modify" onclick="javascript:fn_go_rqstActDetail(this)" value="${rqstLS.seq}"><i class="fas fa-pen-square"></i> 상세</button>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>			
		
		</tbody>
		</table>

		<table style="width:90%; margin:auto;">
			<tr>
			<td style="text-align:center; width:90%">
				<div class="pagination_fixed">
					<form action="OccasionalSafeManagement.do" method="post" name="occasionForm">
						<c:if test="${not empty paginationInfo}">
							<div class="pagination">
								<ul class="pagination -sm">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search_occasion" />
								</ul>
							</div>
						</c:if>
						<input type="hidden" id="currentPageNo" name="currentPageNo"/>
					</form>
				</div>
			</td>
			<td style="text-align:right; width:10%; vertical-align: middle;">
				<div style="text-align: center; margin-top: 10px;">
					<a href="OccasionalSafeManagement_Request.do"><button type="button" class="btn btn-sm btn-primary btn-width" id="search" onclick="">
					<i class="fas fa-pencil-alt"></i>&nbsp;등록</button></a></td>
				</div>
			</td>
			</tr>
		</table>
		
		</div>
		
		</section>	
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>
	
</body>
</html>
