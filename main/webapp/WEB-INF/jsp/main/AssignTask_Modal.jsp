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
<script src="js/MemberModal.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
 
</head>

<body> 

<form:form commandName="membersearchVO" name="listForm" method="post">
		<div class="btn" style="text-align:center;margin:auto; width:90%;margin-left:3%">
			<select id="searchCondition" name="searchCondition" style="height:30px;font-size:15px">
			<script>$("#searchCondition").val("<c:out	 value="${membersearchVO.searchCondition}"/>").attr("selected","selected");</script>
				<option value="00" >성명</option>
				<option value="01">	군번	</option>
				<option value="02">	직책	</option>
			</select>
			<input id="searchKeyword" name="searchKeyword" type="text"  style="border:1px solid gray; height:30px;font-size:15px;width:80%;padding-left:3px;"value="<c:out value="${membersearchVO.searchKeyword}"/>"/>
			<button type="Search" class="btn btn-sm btn-info" 	id="search"	onclick="Member_Search()"><i class="fas fa-search"></i>&nbsp;검색</button>
		</div>
	</form:form>
	<div style="overflow:scroll; width:90%; height:350px; padding:10px; background-color:#F2F2F2; margin:auto;border:1px solid gray">
	<table class="table table-hover" id="table_hover"> 
		<tbody>
			<tr>
				<td>아이디(군번)</td>
				<td>계급</td>
				<td>성명</td>
				<td>직책</td>
			</tr>
		</thead>
		<tbody>
		<form  name="NameForm" method="post" action="">
		<c:forEach var="list" items="${memberList}" varStatus="statics">
			<tr id="${list.stmt}"  name ="${list.srvno}"  value= "${list.srvno}"onclick="javascript:clickTdEvent(this)">
						<td style="text-align: left; font-size 15px">${list.srvno}</td>
						<td style="text-align: left; font-size 15px">${list.cd_nm}</td>
						<td style="text-align: left; font-size 15px"  id="${list.stmt}" >${list.stmt}</td>
						<td style="text-align: left; font-size 15px"  id="${list.stmt}" >${list.rspofc_nm}</td>
						
			</tr>
		</c:forEach>
		</form>
		</tbody>
	</table>
				
	</div>
</body>
</html>