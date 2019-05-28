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
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/AssignTask.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/Modal.js"></script>
<script src="js/resizeModal.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
 
</head>

<body> 

<form:form commandName="membersearchVO" name="listForm" method="post">
	<table id="modal-table-head" style="text-align:left;width:90%; margin:auto; margin-top:10px;margin-bottom:10px;">
		<tr>
			<td style="width:12%">■ 대분류</td>
			<td style="width:38%; padding-left:10px;">
				<select id="searchConditionLage" name="searchConditionLage" onchange="LargeChange(this)" style="width:80%">
				<option value="">선택</option>
				<c:forEach var="taskLarge" items="${taskLarge}" varStatus="statics">
					<option value="${taskLarge.cd}" name="${taskLarge.cd_nm}"><script>$("#searchConditionLage").val("<c:out value="${membersearchVO.searchConditionLarge}"/>").attr("selected","selected");</script><c:out value="${taskLarge.cd_nm}"/></option>
				</c:forEach>
				</select>
			</td>
			<td style="width:12%">■ 중분류</td>
			<td style="width:38%; padding-left:10px;">
				<select id="searchConditionMiddle" name="searchConditionMiddle" onchange="MiddleChange(this)" style="width:80%">
				<option value="">선택</option>
				</select>
				<script>$("#searchCondition").val("<c:out	 value="${membersearchVO.searchCondition}"/>").attr("selected","selected");</script>
			</td>
		</tr>
	</table>
</form:form>
	<table class="table  table-condensed sub_table table01" style="width:90%; margin:auto">
		<tr>
			<td id="td_head">■ 부대활동명</td>
			<td>
				<input id="TaskNameSearch" name="TaskNameSearch" type="text" style="border:1px solid gray; font-size:15px" value="<c:out value="${membersearchVO.searchCondition}"/>"/>
			</td>
			<td>
				<button type="button" class="btn btn-sm btn-primary" id="TaskName" onclick="Search_TaskName()">
				<i class="fas fa-search"></i>&nbsp;검색</button>
			</td>
		</tr>
		<tr>
			<td>■ 부대활동코드</td>
			<td>
				<input id="TaskCodeSearch" name="TaskCodeSearch" style="border:1px solid gray; font-size:15px"type="text" value="<c:out value="${membersearchVO.searchKeyword}"/>"/>
			</td>
			<td>
				<button type="button" class="btn btn-sm btn-primary" id="TaskName" onclick="Search_TaskCode()">
				<i class="fas fa-search"></i>&nbsp;검색</button>
			</td>
		</tr>
	</table>

<div style="overflow:scroll; width:90%; height:150px; padding:10px; background-color:#F2F2F2; margin:auto;border:1px solid gray">
	<table class="table table-hover" id = "table_hover">
		<thead style="text-align:center; color:black;"> 
			<tr>
				<td style="padding:0px">
					세부 활동
				</td>
				<td style="padding:0px">
					세부활동 코드
				</td>
			</tr>
		</thead>
		<tbody id="tabList">
		<c:forEach var="CheckCode" items="${TaskSearched}" varStatus="statics">
			<tr id="${CheckCode.ctlg_cd}" >
				<td style="text-align: left; font-size 15px"  id="${CheckCode.ctlg_cd}"  value="${CheckCode.ctlg_nm}"onclick="javascript:clickTdEvent(this)" >${CheckCode.ctlg_nm}</td>
			</tr>
		</c:forEach>
		
		</tbody>
	</table>
</div>
<table class="table  table-condensed sub_table table01" style="width:90%; margin:auto">
<tbody>
<tr>
	<td>■ 부대활동명</td>
	<td id="td_colspan1">
		<form method="post" name="TaskName" action="">
			<input name="TaskName_name" id="TaskName_name"style="box-shadow:0px 0px 1px #757575;border:none;text-align:center;font-size:12pt;color :gray;"value="" disabled >
		</form>
	</td>
	<td>
	</td>
	</tr>
	<tr>
		<td>■ 부대활동코드</td>
		<td id="td_colspan2">
		<form method="post" name="TaskCode" action="">
			<input name="TaskCode_name" id="TaskCode_name"style="box-shadow:0px 0px 1px #757575;border:none;text-align:center;font-size:12pt;color :gray;"value="" disabled >
		</form>
		</td>
		<td></td>
	</tr>
</tbody>
</table>
</body>
</html>