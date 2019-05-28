<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!-- seungwon 19.02.21 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.21 -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>그룹코드 관리</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script src="<%=request.getContextPath()%>/js/fontawesome-all.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.js"></script>
<!-- seungwon 19.02.21 -->
<script src="<%=request.getContextPath()%>/js/submit.js"></script>
<script src="<%=request.getContextPath()%>/js/ManageCode.js"></script>
<script src="<%=request.getContextPath()%>/js/engine.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/detail.css">

<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/swiper.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main_detail.css">

</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
		
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>그룹코드 관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>코드관리<span>></span>그룹코드 관리<span>></span>입력
			</div>		
		</article>
		
		<section class="subContent_section" id="auto" style="height: auto">
		<div class="table_margin">
		<div style="text-align: left;margin: auto;font-size: 14px; width:40%;"><span class="import_marker">* </span><span>는 필수입력 항목입니다.</span></div>
		
		<table class="table table-striped page_table sub_table table01" style="width:40%; text-align:center;margin:auto;margin-top:10px;margin-bottom:10px;">
		<thead class="thead_text" style="text-align: center;">
		</thead>
		<tbody style="text-align: center;">
			<tr>
				<th class="thead_text"><span class="import_marker">* </span>그룹코드</th>
				<td><input class="sub_input" type="text" id="cd" class="form-control" placeholder="코드" style="width:90%; ime-mode:disabled;"  onkeyup="javascript:fn_notKor(this);"/></td>
			</tr>
			<tr>
				<th class="thead_text"><span class="import_marker">* </span>그룹코드명</th>
				<td><input class="sub_input" type="text" id="cd_nm" class="form-control" placeholder="코드명" style="width:90%"/></td>
			</tr>
			<tr>
				<th class="thead_text">비고</th>
				<td><input class="sub_input" type="text" id="rmrk" class="form-control" placeholder="비고" style="width:90%"/></td>
			</tr>			
		</tbody>
		</table>
	
		<div class="div_bottom_btn">
			<button type="submit" id="submitModify" class="btn btn-sm btn-primary btn-width" onclick="javascript:do_insertGroupCode('${modCode.cd}');" >
			<i class="fas fa-check"></i>&nbsp;저장</button>
			
			<button type="button" id="back" class="btn btn-sm btn-primary btn-width" onclick="javascript:history.back();" >
			<i class="fas fa-undo"></i>&nbsp;취소</button>
		</div>
		
		<form:form commandName="groupCodeVO" name="insertCodeForm" method="post">
			<form:hidden path="cd"/>
			<form:hidden path="cd_nm"/>
			<form:hidden path="rmrk"/>
		</form:form>		
		</div>
		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>
