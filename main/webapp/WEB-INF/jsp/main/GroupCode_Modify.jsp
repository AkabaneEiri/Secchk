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
			<span>코드관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>코드관리<span>></span>그룹코드 관리
			</div>		
		</article>
		
		<section class="subContent_section" id="auto" style="height: auto">
		<div class="table_margin">
		
		<p style="text-align:center;">
			<img src="images/title_img/GroupCode_Modify.png" alt="그룹코드 수정"  style="width:330px; height:80px;">
		</p>
		
		<table class="table table-striped page_table sub_table table01" style="width:50%; text-align:center;margin:auto; margin-top:10px;margin-bottom:10px;">
		<colgroup>
			<col width="20%"/>
			<col width="40%"/>
			<col width="40%"/>
		</colgroup>
		<thead class="thead_title">
			<tr>
				<th scope="20%"></th>
				<th scope="40%" class="thead_text">변경 이전</th>
				<th scope="40%" class="thead_text">변경 후</th>
			</tr>			
			<tr>
				<th class="thead_text">코드</th>
				<td>${modCode.cd}</td>
				<td></td>				
			</tr>
			<tr>
				<th class="thead_text">코드명</th>
				<td>${modCode.cd_nm}</td>
				<td><input class="sub_input" type="text" id="changeName" class="form-control" placeholder="변경할 코드명" style="width:90%"/></td>
				
			</tr>	
			<tr>
				<th class="thead_text">비고</th>
				<td>${modCode.rmrk}</td>
				<td><input class="sub_input" type="text" id="changeRmrk" class="form-control" placeholder="변경할 비고" style="width:90%"/></td>
			</tr>		
		</thead>
		</table>
		<div class="div_bottom_btn">
			<button type="submit" id="submitModify" class="btn btn-sm btn-primary btn-width" onclick="javascript:do_modifyGroupCode('${modCode.cd}');" >
			<i class="fas fa-check"></i>&nbsp;확인</button>
			
			<button type="button" id="back" class="btn btn-sm btn-primary btn-width" onclick="javascript:history.back();" >
			<i class="fas fa-undo"></i>&nbsp;취소</button>
		</div>
		
		<form:form commandName="groupCodeVO" name="changeCodeForm" method="post">
			<form:hidden path="cd"/>
			<form:hidden path="chng_cd_nm"/>
			<form:hidden path="chng_rmrk"/>
		</form:form>		
		</div>
		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>
