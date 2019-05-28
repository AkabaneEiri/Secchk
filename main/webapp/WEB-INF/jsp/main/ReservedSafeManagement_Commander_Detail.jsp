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
<title>예정 활동</title>
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
			<span>예정 활동</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>예정 활동
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">

		<div class="table_margin">

		<!--  <div class="div_title">예정된 안전관리활동</div>-->
		
		<table class="table table-striped sub_table table01" style="width: 90%; margin: auto; margin-bottom: 10px; margin-top: 10px;">
		<thead>
			<tr>
				<th style="text-align: center;">지휘관 지침</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${taskData == null}">
				<tr>
					<td>체크리스트 항목을 가져오지 못했습니다. 다시시도해주시기 바랍니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${cmdNum == 1}">
						<tr><td style="padding: 30px"><c:out value="${taskData.guidnc_1}"/></td></tr> <!-- 체크리스트 항목 상세 내용 -->	
					</c:when>
					<c:when test="${cmdNum == 2}">
						<tr><td style="padding: 30px"><c:out value="${taskData.guidnc_2}"/></td></tr> <!-- 체크리스트 항목 상세 내용 -->	
					</c:when>
					<c:when test="${cmdNum == 3}">
						<tr><td style="padding: 30px"><c:out value="${taskData.guidnc_3}"/></td></tr> <!-- 체크리스트 항목 상세 내용 -->	
					</c:when>
					<c:otherwise>
						<tr><td style="padding: 30px"><c:out value="선택된 내용을 불러오지 못했습니다."/></td></tr> <!-- 체크리스트 항목 상세 내용 -->	
					</c:otherwise>
				</c:choose>				
			</c:otherwise>
			</c:choose>				
		</tbody>
		</table>

		<div style="text-align: center; margin-top: 10px;">		
			<button type="button" class="btn btn-sm btn-primary btn-width" id="back" onclick="javascript:history.back();">
				<i class="fas fa-list"></i>&nbsp;목록</button>
		</div>
		</div>
		</section>	
		
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
		
</body>
</html>
