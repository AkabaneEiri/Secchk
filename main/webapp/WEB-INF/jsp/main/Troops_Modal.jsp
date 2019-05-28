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
<script src="js/TroopsModal.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
 
</head>

<body> 
		<div class="btn" style="text-align:center;margin:auto; width:90%;margin-left:3%">
			<button type="button" class="btn btn-default" disabled>부대명</button>
			<input id="TroopsSearch" name="TroopsSearch" type="text"  style="border:1px solid gray; height:30px;font-size:15px;width:77%;padding-left:3px;"/>
			<button type="button" class="btn btn-sm btn-primary" 	id="search"	onclick="Troops_Search()"><i class="fas fa-search"></i>&nbsp;검색</button>
		</div>
	<div style="overflow:scroll;width:90%; height:265px; padding:10px; background-color:#F2F2F2; margin:auto">
	<table class="table table-hover" style="border:1px solid gray" id="table_hover">
		<thead>
			<tr>
				<td style="text-align:center;color:black;">부대명</td>
			</tr>
		</thead>
		<tbody>
		<form  name="NameForm" method="post" action="">
		<c:forEach var="list" items="${TroopsList}" varStatus="statics">
			<tr>
				<td style="text-align: left; font-size 15px"  id="${list.incdt_idtf_cd}"  name ="${list.incdt_idtf_cd}"  value= "${list.incdt_idtf_cd}"onclick="javascript:clickTdEvent(this)" >${list.incdt_nm}</td>
			</tr>
		</c:forEach>
		</form>
		</tbody>
	</table>
				
	</div>
</body>
</html>