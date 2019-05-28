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
<title>체크리스트 항목 관리</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/ManageCheckListItem.js"></script>
<script src="js/submit.js"></script>
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
			<span>체크리스트 항목 관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>시스템관리<span>></span>체크리스트 항목 관리
			</div>		
		</article>
	
		<section class="subContent_section" id="SelectCheckListItem" style="height:700px">
		<br>
		<jsp:include page="TaskSearch.jsp"></jsp:include>		
		<table class="table table-striped sub_table table01" style="text-align:center; margin:auto;width:90%;"id="tablesort">
		<colgroup>
			<col width="25%"/>
			<col width="53%"/>
			<col width="25%"/>
		</colgroup>
			<thead class="thead_title">
			<tr>
				<th style="width:25%">체크리스트 항목코드</th>
				<th>체크리스트 항목</th>
				<th style="width:20%">활용빈도</th>
			</tr>
			</thead>
		</table>
		
		<form name="rep" method="post">
		<!--div style="overflow:scroll; width:90%; height:380px; padding:10px; background-color:#F2F2F2; margin:auto"-->
		<table class="table table-striped sub_table table01 paginated" style="width:90%;text-align:center; margin:auto; border-bottom:none;border-top:none;" id="table_hover">
		<colgroup>
			<col width="25%"/>
			<col width="53%"/>
			<col width="22%"/>
		</colgroup>
		<thead>
		</thead>
		<tbody>
		</tbody>
		</table>
		
		</form>
		<table class="tbl paginated" id="tbl" style="margin:auto;">
		</table>
		
		
		<br>
		<div id="bottom_btn" style="text-align:right; margin-right:5%;">
			<button type="insert" class="btn btn-sm btn-primary" id="insert_excel"
				onclick="ChecklistExcel()">
				<i class="fas fa-pencil-alt"></i>&nbsp;일괄등록
			</button>
			<button type="insert" class="btn btn-sm btn-primary" id="insert"
				onclick="ChecklistInsert()">
				<i class="fas fa-pencil-alt"></i>&nbsp;입력
			</button>
		</div>
		</br>
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>		
</body>
</html>