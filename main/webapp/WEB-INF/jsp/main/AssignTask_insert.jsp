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
<script src="js/jquery-ui.min.js"></script>
<script src="js/TaskSearch.js"></script>

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
			<span>과업 부여</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>과업 부여<span>></span>등록
			</div>		
		</article>
		
		<section class="subContent_section" id="AssignTask_insert">
		<br>
		<table class="table table-condensed sub_table table01"  style="text-align:center;width:60%; margin:auto;margin-top:10px;margin-bottom:10px;">
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="40%">
				<col width="20%">
			</colgroup>		
			<tr>
				<th colspan="2">일시</th>
				<td colspan="2" style="text-align: left;">
					<input type="text" style="box-shadow : 0px 0px 1px #757575; text-align:center;border:none; height:30px; width:93%;" id="TaskDatepicker"  name="searchCondition2" value="<c:out value="${membersearchVO.searchCondition2}"/>">
				</td>
			</tr>
			<tr>
				<th rowspan="3">활동 유형</th>
				<th>대분류</th>
				<td colspan="2" style="text-align: left;">
					<select id="searchConditionLage" name="searchConditionLage" onchange="LargeChange(this)" style="width:93%;height:30px;" align="center">
						<option value="">대 분 류</option>
						<c:forEach var="taskLarge" items="${taskLarge}" varStatus="statics">
							<option value="${taskLarge.cd}" name="${taskLarge.cd_nm}"><c:out value="${taskLarge.cd_nm}"/></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>중분류</th>
				<td colspan="2" style="text-align: left;">
					<select id="searchConditionMiddle" name="searchConditionMiddle" onchange="MiddleChange(this)" style="width:93%;height:30px;" align="center">
						<option value="">중 분 류</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>세부 활동</th>
				<td colspan="2" style="text-align: left;">
					<select class="select_stripped" name="Task_name" id="Task_name" onchange="SelectEvent()" style="width:93%;height:30px;" align="center">
						<option value="">세부 활동</option>
					</select>
				</td>
			</tr>
			<tr>
				<th colspan="2">과업</th>
				<td style="border-right:none;text-align: left;">
				<form name="TaskName">
					<input class="DiableInputbox2" name="TaskInsert_Task" id="TaskInsert_Task"  style="background-color:#eaeae; font-size: 12pt;color: #757575;" disabled>
					<input type="hidden" id="TaskInsert_Seq">
					<input type="hidden" id="result_lrgcls">
					<input type="hidden" id="result_mccls">
					<input type="hidden" id="result_nm">
					<input type="hidden" id="result_cd">
					
				</form>
				</td>
				<td>
					<button type="button" class="btn btn-sm btn-primary" id="btn_tasksmall" onclick="Submit()"><i class="fas fa-search"></i>&nbsp;검색</button>
					 
					<jsp:include page="taskSearchModal2.jsp"></jsp:include>
				</td>
			</tr>
			<tr>
				<th colspan="2">담당자</th>
				<td style="border-right:none;text-align: left;">
					<form name="TaskCharge">
					<input class="DiableInputbox2" name="AssignCharge"  id="AssignCharge" value="" style="background-color:#eaeae; font-size: 12pt;color: #757575;" disabled>
					<input type="hidden" id="Chargesrvno" name="Chargesrvno" value="">
					</form>
					</td>
					<td>
						<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#UserSearch" ><i class="fas fa-search"></i>&nbsp;검색</button> 
						<jsp:include page="UserSearchModal.jsp"></jsp:include>
					</td>
				</tr>
			</table>
			
		<div class="SubmitButton" style="margin-left:44%">
			<button type="button" class="btn btn-sm btn-primary" id="submit" onclick="Insert_Submit()">
				<i class="fas fa-check"></i>&nbsp;저장</button>
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