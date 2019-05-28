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
<title>수시 활동</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<!-- seungwon 19.02.22 -->
<script src="js/submit.js"></script>


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
			<span>수시 활동</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
				홈<span>></span>수시 활동
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		
		<!-- seungwon 19.02.15 -->
		<div class="table_margin">
		<table class="table table-striped sub_table table01">
		<colgroup>
			<col width="10%">
			<col width="20%">
			<col width="40%">
			<col width="20%">
			<col width="10%">
		</colgroup>
		<tbody style="text-align: center">
			<tr>
				<th style="text-align: center"><c:out value="부대활동유형"/></th>	
				<td>${rqstAct.incdt_actvt_type_cd_nm }</td>
			</tr>
			<tr>
				<th>요청 일자</th>
				<td><c:out value="${rqstAct.rqst_date}"/></td>
			</tr> 			
			<tr>
				<th>활동 일자</th>
				<td><c:out value="${rqstAct.actvt_date}"/></td>
			</tr> 
			<tr>
				<th>요청 사유</th>
				<td><c:out value="${rqstAct.rsn}"/></td>
			</tr> 	
			
			<tr>
				<th>검토 의견</th>
					<c:choose>
						<c:when test="${rqstAct.opn == null || rqstAct.opn == ''}">
							<c:choose>
								<c:when test="${fn:trim(rqstAct.state_cd) == 'D1'}">
									<td>검토 중</td>
								</c:when>
								<c:when test="${fn:trim(rqstAct.state_cd) == 'D2'}">
									<td>승인완료</td>
								</c:when>
								<c:otherwise>
									<td>반려</td>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<td>${rqstAct.opn}</td>
						</c:otherwise>
					</c:choose>						
				<!--  <input style="box-shadow:0px 0px 1px #757575;border:none;font-size:15pt;background-color: lightgray;">${rqstAct.opn}</input></td>-->
			</tr>		
			
		</tbody>
		</table>
		<div class="div_bottom_btn">
			<button type="submit" class="btn btn-sm btn-primary btn-width" id="back" onclick="javascript:history.back();">
			<i class="fas fa-list"></i>&nbsp;목록</button>
		</div>	
		
		</div>
		<!-- seungwon 19.02.15 -->
		
		</section>	
		
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
	
</body>
</html>