<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- seungwon 19.02.14 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.14 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>예정된 안전관리활동</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<!-- seungwon 19.02.22 -->
<script src="js/submit.js"></script>


<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!-- seungwon 19.02.17 detail css -->
<link href="css/detail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">
</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
		
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>예정된 안전관리활동</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
				홈<span>></span>예정된 안전관리활동
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		
		<!-- seungwon 19.02.15 -->
		<div class="table_margin">
		<table class="table table-striped sub_table">
		<colgroup>
			<col width="10%">
			<col width="20%">
			<col width="40%">
			<col width="20%">
			<col width="10%">
		</colgroup>
		<thead class="thead_title">
			<tr>
				<td style="text-align: center"><c:out value="체크리스트"/></td>	
			</tr>
			<tr>
			</tr>
		</thead>
		<tbody>
			<c:choose>
			<c:when test="${checklistItem == null}">
				<c:choose>
					<c:when test="${SS_athrt == 'M'}">
						<tr>
							<td colspan="6">현재 상세 내용이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">현재 상세 내용이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr><td style="padding: 41px"><c:out value="${checklistItem.ctlg_itm_ctnt}"/></td></tr> <!-- 체크리스트 항목 상세 내용 -->	
				
				<tr>
					<td style="text-align: center">
					<form name="doCheck" method="POST">
						<!-- <div><label><input class="checkbox_fixed" type="checkbox" name="check" id="completeCheck">실시</label></div> -->
					</form>
					
					</td>
				</tr>	
				<!-- button : complete, cancel, completeRequest -->
				<tr>
					<td style="text-align: center">
						<!-- 
						<button type="submit" class="btn2 button" id="complete" onclick="javascript:fn_itemComplete('${checklistItem.ctlg_itm_cd}')">확인</button>
						<button type="submit" class="btn2 button" id="cancel" style="margin-left: 10px">취소</button>
						 -->
						<button type="submit" class="btn btn-sm btn-primary" id="completeRequest" style="width: auto;"
						onclick="javascript:fn_onClick_requestApproval(
						'${checklistItem.ctlg_cd}',
						'${checklistItem.ctlg_itm_cd}')">
						승인요청
						</button>
						<button type="submit" class="btn btn-sm btn-primary btn-width" id="back" style="margin-left: 10px" onclick="javascript:history.back();">
						<i class="fas fa-undo"></i>&nbsp;취소</button>
						<input type="hidden" id="itm_ctnt" value="${checklistItem.ctlg_itm_ctnt}"/>
					</td>
				</tr>
				<form:form commandName="listsearchVO" name="checklistForm" method="post">
					<form:hidden path="findCode"/>
				</form:form>
				
				<form:form commandName="checklistItemVO" name="requestApprovalForm" method="post">
					<form:hidden path="ctlg_cd"/>
					<form:hidden path="ctlg_itm_cd"/>
					<form:hidden path="ctlg_itm_ctnt"/>
				</form:form>
			</c:otherwise>
			</c:choose>			
		</tbody>
		</table>
		</div>
		<!-- seungwon 19.02.15 -->
		
		</section>	
		
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
	
</body>
</html>