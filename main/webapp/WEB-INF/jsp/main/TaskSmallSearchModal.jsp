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
<script src="js/jquery-ui.min.js"></script>
<script src="js/TaskSmallSearchModal.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
 
</head>

<body> 

		<div class="btn" style="text-align:center;margin:auto; width:50%;padding-left:28px;">
		    <span>■ 과업명&nbsp</span>
			<input id="searchKeyword" name="searchKeyword" type="text"  style="border:1px solid gray; height:27px;font-size:15px;width:80%;padding-left:3px;"value="<c:out value="${membersearchVO.searchKeyword}"/>"/>
			<button type="button" class="btn btn-sm btn-info" 	id="search"	onclick="Member_Search()"><i class="fas fa-search"></i>&nbsp;검색</button>
		</div>
	
	<div style="width:90%; height:40px; padding:10px; background-color:#F2F2F2; margin:auto;border:1px solid gray;border-bottom:none">
	<table class="table table-hover" id="table_title"> 
		<thead style="text-align: center;	color: black;">
			<tr>
				<td>과업명</td>
			</tr>
		</thead>
	</table>
	</div>
	<div style="overflow:scroll; width:90%; height:300px; padding:10px; background-color:#F2F2F2; margin:auto;border:1px solid gray">
	<table class="table table-hover" id="table_hover"> 
		<tbody>
		</tbody>
	</table>
				
	</div>
</body>
</html>