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
<title>과업 부여</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/AssignTask.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/json_paging.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">

<link rel="stylesheet" type="text/css" href="css/main_detail.css">
<link rel="stylesheet" type="text/css" href="css/json_paging.css">

 
</head>

<body>

	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		<article class="sub_title">
			<span>과업 부여</span>
		</article>
		<article class="cur_page">
			<div id="title">
				홈<span>></span>지휘 및 통제<span>></span>과업 부여
			</div>		
		</article>
		<section class="subContent_section" id="AssignTask">
		<br>
		
		<!-- 검색조건 -->
		<jsp:include page="TaskSearch_date.jsp"></jsp:include>
		<br>
		<table id = "Table_Task"class="table table-striped sub_table table01 tbl paginated" style="text-align:center;width:90%; margin:auto; margin-top:10px;margin-bottom:10px;border-bottom:none;">
		<thead style="text-align:center;">
			<tr>
				<th style="width:3%"> 선택</th>
				<th style="width:15%">대분류</th>
				<th style="width:15%">중분류</th>
				<th style="width:20%">세부활동</th>
				<th style="width:20%">과업</th>
				<th style="width:15%">담당자</th>
			</tr>
		</thead>
		<tbody>
				<tr>
				</tr>
			</tbody>
		</table>
		<table class="tbl paginated" id="tbl" style="margin:auto">
		</table>
		<div style="text-align:right; margin-right:5%;">
			<button type="button" class="btn btn-sm btn-primary" id="insert" onclick="Task_Insert()">
			<i class="fas fa-pencil-alt"></i>&nbsp;등록</button>
			<button type="button" class="btn btn-sm btn-danger" id="delete" onclick="Task_Delete()">
			<i class="fas fa-pencil-alt"></i>&nbsp;삭제</button>
		</div>
		<br>
		</section>	

		<jsp:include page="Footer.jsp"></jsp:include>		
</div>	
</body>
</html>