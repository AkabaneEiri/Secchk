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
<script src="js/ManageCheckListResult.js"></script>
<script src="js/ManageCode_Insert_Selectbox.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">
<link rel="stylesheet" type="text/css" href="css/detail.css">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="sub_contents_wrap">	
		<article class="sub_title">
			<span>체크리스트항목관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>시스템관리<span>></span>체크리스트 항목 관리<span>></span>입력
			</div>		
		</article>
	
		<section class="subContent_section" id="SelectCheckListItem" style="height:700px">
		<!--
		<br>
		<div>
			<input name="Text" style="background-color:white;border:0px;text-align:center;font-size:18pt;"  value="${ctlg_cd} - ${checklistItemVO.ctlg_cd}" disabled>
		</div>
		<br>
		-->
		
		<table class="table table-condensed sub_table table01"  style="text-align:center;width: 60%;margin:auto;margin-top:10px;margin-bottom:10px;">
		<colgroup>
			<col width="20%">
			<col width="20%">
			<col width="30%">
			<col width="30%">
		</colgroup>
		<thead>
		</thead>		
		<tbody>
			<tr id="search_large" class="">
				<th rowspan="3"><span class="import_marker">* </span>활동 유형</th>
				<th>대분류</th>
				<td colspan="3" style="text-align: left; padding: 10px;">
					<select id="largeCondition" name="largeCondition" onchange="LargeChange(this)" style="width:78%;">
						<option value="">선택</option>
						<c:forEach var="largeList" items="${largeList}" varStatus="statics">
							<option id="${fn:trim(largeList.cd)}" value="${fn:trim(largeList.cd)}"><c:out value="${largeList.cd_nm}"/></option>
						</c:forEach>
					</select>					
				</td>
			</tr>				
			<tr id="search_middle" class="">
				<th>중분류</th>
				<td colspan="3" style="text-align: left; padding: 10px;">
					<select id="middleCondition" name="middleCondition" onchange="MiddleChange(this)" style="width:78%;">
						<option value="">선택</option>
					</select>
				</td>
			</tr>
			<tr id="search_act" class="">
				<th>활동</th>
				<td colspan="3" style="text-align: left; padding: 10px;">
					<select id="activity" name="activity" onchange="ActivityChange(this)" style="width:78%;">
						<option value="">선택</option>
					</select>
				</td>
			</tr>
			<tr>
				<th colspan="2"><span class="import_marker">* </span>체크리스트항목코드</th>
				<form name="CheckCode">
					<td style="border-right: none; padding: 10px;">
						<input class='sub_input sub_ctlg_input' style='text-align: center;' type='text' id='ctlgCode' name="ctlgCode" class='form-control grp_input_width' disabled='disabled'/>
						<span style="color: blue;"> _ </span>
						<input class="sub_input sub_ctlg_input" style='text-align: center;' name="Checklist_code"  id="Checklist_code" placeholder="" disabled>
					</td>
					<td style="text-align: left; padding: 10px;">
						<span id="codeCheck"class="badge badge-ready">i</span>
						<span class="alert alert-info" role="alert" style="font-size:14px;">항목 코드는 자동 입력됩니다.</span>
						<!-- <input type="text" id="codeCheck_result" size="12" style="background-color:#CDCDCD;box-shadow : 0px 0px 1px #757575;border:none;width: 85%;height: 25px; text-align:center;"disabled> -->
					</td>
				</form>
			</tr>
			<tr>
				<th colspan="2" style="width:25%;"><span class="import_marker">* </span>체크리스트 항목</th>
				<td colspan="3" style="padding: 10px;">
					<form name="ChecnName">
					<input class="sub_input" name="Checklist_name" id="AssignCharge" >
					</form>
				</td>
			</tr>
		</tbody>
			<%-- <tr>
			<form name="Task">
				<th style="border-left:1px solid #ccc">부대활동</th>
				<td colspan="2" style="border-right: none;"> 
					<form method="post" name="SelectCode" action="">
						<input id="searchCondition1" name="searchCondition1" style="background-color:#CDCDCD;box-shadow : 0px 0px 1px #757575;border:none;text-align:center;font-size:14pt;color :#757575; height:30px;" value="<c:out value="${membersearchVO.searchCondition1}"/>"disabled />
					</form>
				</td>
				<td style="width:18%;text-align:left; padding-left:10px;">
					<button type="Search" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#TaskSearch" ><i class="fas fa-search"></i>&nbsp;검색</button> 
					<jsp:include page="taskSearchModal.jsp"></jsp:include>
				</td>
				</form>
			</tr> --%>			
		</table>
			<br>
			<form name="button_form">
			<div class="SubmitButton" style="margin-left:44%">
				<button type="button" class="btn btn-sm btn-primary" id="submit" onclick="Insert_Checklist()">
					<i class="fas fa-check"></i>&nbsp;저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="Cancle" onclick="Insert_Cancle()">
					<i class="fas fa-undo"></i>&nbsp;취소</button>
			</div>
			</form>
			<br>				
	</section>
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>	
</body>
</html>