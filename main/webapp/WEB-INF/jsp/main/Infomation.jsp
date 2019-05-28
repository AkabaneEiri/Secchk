<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- seungwon 19.02.14 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt"	   uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- seungwon 19.02.14 -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>신규 검토 알림</title>

<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/swiper.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main_detail.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/detail.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/info.css">


<script type="text/javascript">
function openUgcy() {
	opener.location.href = "CheckApproval.do";
}

function openAct() {
	opener.location.href = "CheckOccasionalSafeManagement.do";
}

function openCheck() {
	opener.location.href = "CheckRequestedCheckList.do";
}
</script>

</head>

<body>				
	<div class="contents_wrap" style="padding-top: 0px; padding-bottom: 0px;">
		<div class="div_title">
			■ 미검토 요청사항
		</div>
		
		<table class="table table-striped page_table sub_table table01" style="margin:auto; margin-top:10px;margin-bottom:10px;">
		<tbody style="text-align: center;">
			<tr>
				<th>조치 요청</th>
				<td>${cntRqstUgcy}</td>
				<td><span id="moreBtn"><a href="javascript:openUgcy();"><img src="images/more_btn.png" style="width: 100px;"></a></span></td>
			</tr>		
			<tr>
				<th>수시 활동 요청</th>
				<td>${cntRqstAct}</td>
				<td><span id="moreBtn"><a href="javascript:openAct();"><img src="images/more_btn.png" style="width: 100px;"></a></span></td>
			</tr>
			<tr>
				<th>체크리스트 요청</th>
				<td>${cntRqstChk}</td>
				<td><span id="moreBtn"><a href="javascript:openCheck();"><img src="images/more_btn.png" style="width: 100px;"></a></span></td>
			</tr>
			
		</tbody>
		</table>
	</div>
</body>
</html>