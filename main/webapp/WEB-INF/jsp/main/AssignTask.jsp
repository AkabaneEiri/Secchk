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
				홈<span>></span>지휘 및 통제<span>></span>과업 부여
			</div>		
		</article>
		<section class="subContent_section" id="AssignTask">
		
		<br>

		<table style="text-align:left;width:90%; margin:auto; margin-top:10px;margin-bottom:10px;">
			<tr>
			<td style="width:9%">■ 부대활동</td>
			<td style="width:15%">
				<form method="post" name="SelectCode" action="">
					<input id="searchCondition1" name="searchCondition1" style="background-color:#CDCDCD;box-shadow : 0px 0px 1px #757575;border:none;text-align:center;font-size:14pt;color :#757575; height:30px;" value="<c:out value="${membersearchVO.searchCondition1}"/>"disabled />
				</form>
			</td>
			<td style="width:7%; text-align:left; padding-left:10px;">
				<button type="Search" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#TaskSearch" ><i class="fas fa-search"></i>&nbsp;검색</button> 
				<jsp:include page="taskSearchModal.jsp"></jsp:include>
			</td>
			<td style="width:6%; text-align:center;">일시</td>
			<td style="width:13%">
				<input type="text" style="background-color: lightgray; box-shadow : 0px 0px 1px #757575; font-size:17px; text-align:center;border:none; height:30px;" id="TaskDatepicker" onclick="Task_datepicker(this);" name="searchCondition2" value=<c:out value="${membersearchVO.searchCondition2}"/>>
			</td>
			<td style="text-align:left; padding-left:10px;">
				<form:form commandName="membersearchVO" name="TaskSearch" method="post" >
					<input type="hidden"  id="searchKeyword" name="searchKeyword" value=<c:out value="${membersearchVO.searchKeyword}"/> >
					<input type="hidden"  id="searchKeyword2" name="searchKeyword2" value=<c:out value="${membersearchVO.searchKeyword2}"/>>
					<input type="hidden"  id="searchCode" name="searchCode" value=""/>
					<button type="Search" class="btn btn-sm btn-primary" 	id="search"	onclick="Task_Search()"><i class="fas fa-search"></i>조회</button>
				</form:form>
			</td>
			</tr>
		</table>

		<table class="table table-striped sub_table table01" style="text-align:center;width:90%; margin:auto; margin-top:10px;margin-bottom:10px;">
		<thead style="text-align:center;">
		</thead>
		<tbody>
			<td>과업</td>
			<td>담당 부대</td>
			<td>담당자</td>
			</tr>
			<c:choose>
			<c:when test="${fn:length(taskList)==0}">
				<tr>
					<td></td>
					<td> 데이터가 존재하지 않습니다</td>
					<td></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="Task" items="${taskList}" varStatus="statics">
					<tr>
						<td id="TaskName" name="TaskName"><c:out value="${Task.task}"/></td>
						<td><c:out value="${Task.incdt_idtf_cd}"/></td>
						<td><c:out value=" ${Task.task_psnchnrg_rnk}"/>  <c:out value=" ${Task.task_psnchnrg_srvno}"/> </td>
					</tr>
				</c:forEach>
			</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		<div style="text-align:right; margin-right:5%;">
			<button type="insert" class="btn btn-sm btn-primary" id="insert" onclick="Task_Insert()">
			<i class="fas fa-pencil-alt"></i>&nbsp;등록</button>
		</div>
		<br>
		</section>	

		<jsp:include page="Footer.jsp"></jsp:include>		
</div>	
</body>
</html>