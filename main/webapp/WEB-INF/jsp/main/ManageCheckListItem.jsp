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
<script src="js/ManageCheckListItem.js"></script>

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
			홈<span>></span>시스템관리<span>></span>체크리스트항목관리
			</div>		
		</article>
	
		<section class="subContent_section" id="SelectCheckListItem" style="height:700px">
		<br>
		<table style="text-align:left;width:90%;margin:auto;margin-top:10px;margin-bottom:15px;width:90%;">
			<td style="width:9%">■ 부대활동</td>
			<td style="width:20%">
				<form method="post" name="SelectCode" action="">
					<input id="searchCondition1" name="searchCondition1" style="height:30px;box-shadow:0px 0px 1px #757575;border:none;text-align:center;font-size:15pt;color :#757575;" value="${checklistItemVO.ctlg_cd}" disabled />
				</form>
			</td>
			<td style="width:7%; text-align:left; padding-left:10px;">
				<button type="Search" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#TaskSearch" ><i class="fas fa-search"></i>&nbsp;검색</button> 
				<jsp:include page="taskSearchModal.jsp"></jsp:include>
			</td>
			<td style="text-align:left; padding-left:10px;">
				<input type="hidden"  id="searchCode" name="searchCode" value=""/>
				<button type="Search" class="btn btn-sm btn-primary" 	id="search"	onclick="Task_Search()"><i class="fas fa-search"></i>&nbsp;조회</button>
			</td>
		</table>
		
		<table class="table table-striped sub_table" style="text-align:center; margin:auto;width:90%;"id=tablesort">
			<thead class="thead_title">
			<tr>
				<th style="width:25%">체크리스트 항목코드</th>
				<th>체크리스트 항목</th>
				<th style="width:20%">활용빈도</th>
			</tr>
			</thead>
		</table>
		
		<form name="rep" method="post">
		<div style="overflow:scroll; width:90%; height:440px; padding:10px; background-color:#F2F2F2; margin:auto">
		<table class="table table-striped" style="text-align:center; margin:auto;"id=tablesort">
			<tbody>
				<c:forEach var="list_false" items="${Checklist}" varStatus="statics">
				<tr>
					<td style="width:25%">
						<c:out value="${list_false.ctlg_itm_cd }"/>
					</td>
					<td>
						<c:out value="${list_false.ctlg_itm_ctnt}"/>
					</td>
					<td style="width:15%">
						<c:out value="${list_false.prtcuse_frqc}"/>
					</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
			</tfoot>
		</table>
		</div>
		</form>
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