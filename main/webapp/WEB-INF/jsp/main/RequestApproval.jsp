<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- seungwon 19.02.27 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.27 -->   
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>예정 활동</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">

<script src="js/submit.js"></script>
<link href="css/detail.css" rel="stylesheet">

</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>예정 활동</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>예정 활동<span>></span>조치 요청
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">

		<div class="table_margin">

		<!--  <div class="div_title">예정된 안전관리활동</div>-->
		
		<table class="table table-striped sub_table table01" style="/*width: 90%; margin: auto; margin-bottom: 10px; margin-top: 10px;*/">		
		<colgroup>
			<col width="25%">
			<col width="75%">
		</colgroup>
		<tbody>
			<tr>
				<th style="text-align: center;">상세 내용</th>
				<c:choose>
					<c:when test="${checklistItem == null }">				
						<td>체크리스트 항목을 가져오지 못했습니다. 다시시도해주시기 바랍니다.</td>
					</c:when>
					<c:otherwise>			
						<td  class="word_break" style="padding: 30px;">${checklistItem.ctlg_itm_ctnt}</td>
					</c:otherwise>
				</c:choose>
			</tr>	
			<tr>
				<th style="text-align: center;"><span class="import_marker">* </span>사유</th>
				<td>
					<textarea id="rsn_area" name="rsn_area" rows="3" cols="10" placeholder="사유를 입력해주십시오"
					style="width: 90%; box-shadow: 0px 0px 1px #757575;border: none;"></textarea>
				</td>
			</tr>		
		</tbody>
		</table>

		<div style="text-align: center; margin-top: 10px;">
			<c:if test="${fn:trim(listsearchVO.state_cd) != 'E3'}">
				<c:if test="${(checkState != null || checkState != '') && checkState != 'C'}">
					<button type="button" class="btn btn-sm btn-primary btn-width" id= "ok" onclick="javascript:fn_onClick_submitApproval('${checklistItem.ctlg_cd}', '${checklistItem.ctlg_itm_cd}')">
				<i class="fas fa-check"></i>&nbsp;확인</button>
				</c:if>				
			</c:if>			
			<button type="button" class="btn btn-sm btn-primary btn-width" id= "back" onclick="javascript:history.back();">
			<i class="fas fa-undo"></i>&nbsp;취소</button>
		</div>
		
		<form:form commandName="checkApprovalVO" name="approvalSubmitForm" method="post">
			<form:hidden path="rsn"/>
			<form:hidden path="ctlg_cd"/>
			<form:hidden path="ctlg_itm_cd"/>
			<form:hidden path="id" value="${listsearchVO.actId}"/>
			<form:hidden path="taskDataSeq" value="${listsearchVO.seq}"/>
		</form:form>
		</div>
		</section>	
		
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
		
</body>
</html>
