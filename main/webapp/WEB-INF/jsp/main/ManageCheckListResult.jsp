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
<title>체크리스트항목관리</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/ManageCheckListResult.js"></script>

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
			<span>체크리스트항목관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>시스템관리<span>></span>체크리스트항목관리<span>></span>체크리스트 항목 입력
			</div>		
		</article>
	
		<section class="subContent_section" id="SelectCheckListItem" style="height:700px">
		<p style="text-align:center;">
			<img src="images/title_img/Code_Insert.png" alt="그룹코드 등록"  style="width:330px; height:80px;">
		</p>
		<!--
		<br>
		<div>
			<input name="Text" style="background-color:white;border:0px;text-align:center;font-size:18pt;"  value="${ctlg_cd} - ${checklistItemVO.ctlg_cd}" disabled>
		</div>
		<br>
		-->
		
		<table class="table table-condensed sub_table table01"  style="text-align:center;width: 60%;margin:auto;margin-top:10px;margin-bottom:10px;">
			<tr>
				<th style="padding-top: 20px;padding-bottom: 20px;">체크리스트항목코드</th>
				<td>
				<form name="CheckCode">
					<input class="sub_input" name="Checklist_code"  id="Checklist_code">
					<span id="codeCheck"class="badge badge-ready">i</span>
					<input type="text" id="codeCheck_result" size="12" style="background-color:#CDCDCD;box-shadow : 0px 0px 1px #757575;border:none;width: 40%;height: 25px; text-align:center;"disabled>
					<div class="alert alert-info" role="alert" style="font-size:14px;">
						예)G001_001
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span>
						  </button>							
					</div>
				</td>
				</form>
			</tr>
			<tr>
				<th style="width:25%;">체크리스트 항목</th>
				<td colspan="3">
					<form name="ChecnName">
					<input class="sub_input" name="Checklist_name" id="AssignCharge" >
					</form>
				</td>
			</tr>
		</table>
			<br>
			<div class="SubmitButton" style="margin-left:44%">
				<button type="button" class="btn btn-sm btn-primary" id="submit" onclick="Insert_Submit()">
					<i class="fas fa-check"></i>&nbsp;확인</button>
				<button type="button" class="btn btn-sm btn-primary" id="Cancle" onclick="Insert_Cancle()">
					<i class="fas fa-undo"></i>&nbsp;취소</button>
			</div>
			<br>				
	</section>
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>	
</body>
</html>