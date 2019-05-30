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
<title>조치 요청</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/CheckApproval.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">

<link rel="stylesheet" type="text/css" href="css/main_detail.css">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>조치 요청</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>지휘 및 통제<span>></span>조치 요청
			</div>		
		</article>
			
		<section class="subContent_section" id="AssignTask">
			<p style="text-align:center;">
			<img src="images/title_img/CheckApproval.png" alt="승인 목록"  style="width:330px; height:80px;">
			</p> 
			<table class="table table-stripped sub_table table01"  style="text-align:center; width:90%; margin:auto;">
				<thead class="thead_title">
				<tr>
					<th>요청 일자</th>
					<th>요청자</th>
					<th>활동 유형</th>
					<th>활동 일자</th>
					<th>상태</th>
					<th>선택</th>
				</tr>
				</thead>
				<tbody>
				
				<c:forEach var="list" items="${ApprovalList}" varStatus="statics">
					<tr>
						<td>${list.rqst_date}</td>
						<td>${list.rqstr_srvno}</td>
						<td>${list.incdt_actvt_type_cd}</td>
						<td>${list.actvt_date}</td>
						<td>${list.state_cd}</td>
						<td>
							<button type="modify" class="btn btn-sm btn-success" 	id="modify" onclick="Approve_select('${list.seq}')"  value=${list.seq}><i class="fas fa-check"></i>&nbsp;선택</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
		<br>
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
</body>
</html>