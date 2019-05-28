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
<title>체크리스트 엑셀 입력</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script src="<%=request.getContextPath()%>/js/fontawesome-all.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.js"></script>
<!-- seungwon 19.02.21 -->
<script src="<%=request.getContextPath()%>/js/submit.js"></script>
<script src="<%=request.getContextPath()%>/js/ManageCheckListItem.js"></script>

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
			<span>체크리스트 항목 관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>시스템관리<span>></span>일괄등록
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">

		<p style="text-align:center;">
			<img src="images/title_img/Checklist_Insert_Excel.png" alt="일괄 등록"  style="width:330px; height:80px;">
		</p>
		
		<table class="table table-striped sub_table table01" style="width:50%; margin:auto;margin-top:10px;margin-bottom:10px;">
			<tr>
				<form id="excelUploadForm" name="excelUploadForm"
					enctype="multipart/form-data" method="post""
					action="${pageContext.request.contextPath}/ChecklistexcelUploadAjax.do">
				
				<th rowspan="2">코드 파일</th>
				<td>
					<div style="margin: 10px 10px;">
					<input id="excelFile" type="file" name="excelFile" style="width:90%; height:30px;" />
					<br>
					<div style="font-size:14px; text-align:left">&nbsp;
					첨부파일은 한개만 등록 가능합니다.&nbsp;&nbsp;* 허용 확장자 : xls, xlsx</div>
					</div>
				</td>
				</form>
			</tr>
		</table>
		<div id="bottom_btn" style="margin:auto; text-align:center;">
			<button type="button" class="btn btn-sm btn-primary" id="insert_excel" onclick="javascript:check();">
				<i class="fas fa-pencil-alt"></i>&nbsp;등록
			</button>
		</div>
		
		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>
