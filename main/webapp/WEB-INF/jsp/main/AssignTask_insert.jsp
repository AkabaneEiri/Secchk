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
<script src="js/Modal.js"></script>
<script src="js/AssignTask_Insert.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<link rel="stylesheet" type="text/css" href="css/main_detail.css">
</head>

<body>

	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>과업 등록</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>과업 부여<span>></span>과업 등록
			</div>		
		</article>
		
		<section class="subContent_section" id="AssignTask_insert">
		<br>
		<div>
			<input name="Text" style="background-color:white;border:0px;text-align:center;font-size:18pt;"  value="${Date}&emsp;&emsp;${Name}" disabled>
		</div>
		<br>
		<!--<form name = "InsertForm" action="AssignInsert.do" method="post">-->
			<table class="table table-condensed sub_table table01"  style="text-align:center;width:40%; margin:auto;margin-top:10px;margin-bottom:10px;">
				<tr>
					<th>과업</th>
					<td style="border-right:none;">
					<form name="TaskName">
						<input class="DiableInputbox2" name="TaskInsert_Task" id="TaskInsert_Task"  disabled>
						<input type="hidden" id="TaskInsert_Seq">
					</form>
					</td>
					<td>
						<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#TaskSearch" ><i class="fas fa-search"></i>검색</button> 
						<jsp:include page="taskSearchModal2.jsp"></jsp:include>
					</td>
				</tr>
				<tr>
					<th>담당자</th>
					<td style="border-right:none;">
						<form name="TaskCharge">
						<input class="DiableInputbox2" name="AssignCharge"  id="AssignCharge" value="" disabled>
						<input type="hidden" id="Chargesrvno" name="Chargesrvno" value="">
						</form>
					</td>
					<td>
						<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#UserSearch" ><i class="fas fa-search"></i>&nbsp;검색</button> 
						<jsp:include page="UserSearchModal.jsp"></jsp:include>
					</td>
				</tr>
			</table>
		<!--</form>-->
			
			<div class="SubmitButton" style="margin-left:44%">
				<button type="button" class="btn btn-sm btn-primary" id="submit" onclick="Insert_Submit()">
					<i class="fas fa-check"></i>&nbsp;확인</button>
				&nbsp;
				<button type="button" class="btn btn-sm btn-primary" id="Cancle" onclick="Insert_Cancle()">
					<i class="fas fa-undo"></i>&nbsp;취소</button>
			</div>
		<br>
		</section>	
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>	
</body>
</html>