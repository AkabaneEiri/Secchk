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
<title>체크리스트</title>
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

<script src="js/submit.js"></script>
<link href="css/detail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">

<script src="js/CheckListManagement_Request.js"></script>
<script src="js/OccasionalSafeManagement_Request.js"></script>
<script src="js/engine.js"></script>

</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>	
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>체크리스트</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>체크리스트<span>></span>등록
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		
		<div class="table_margin">
		<!-- 
		<div style="text-align: left;font-size: 14px;"><span class="import_marker">&nbsp;*</span><span>&nbsp;는 필수입력 항목입니다.</span></div>
		 -->
		<table class="table table-striped sub_table table01" style="margin:auto;margin-top:10px;margin-bottom:10px;">
		<colgroup>
			<col width="23%"/>
			<col width="80%"/>
		</colgroup>
		<tr>
			<th>신 규</th>
			<td style="text-align:left; padding-left:12px; padding-bottom:9px;">
				<input class="checkbox_fixed forChecklist" type="checkbox" id="newCheck" onclick=""/>
				<span>&emsp;* 신규항목 요청 시 체크해 주세요.</span>
			</td>
		</tr>
		<tr>
			<!-- <th rowspan="4">활<br>동<br>유<br>형</th> -->
			<th style="text-align:center;">대분류</td>
			<td style="text-align: left">
				<select id="largeCondition" name="largeCondition" onchange="LargeChange(this)" style="width:100%;height:30px;">
					<option value="">선택</option>
						<c:forEach var="largeList" items="${largeList}" varStatus="statics">
							<option id="${fn:trim(largeList.cd)}" value="${fn:trim(largeList.cd)}"><c:out value="${largeList.cd_nm}"/></option>
						</c:forEach>
				</select>
			</th>
		</tr>			
		<tr>
			<th style="text-align:center;">중분류</th>
			<td style="text-align: left">
				<select id="middleCondition" name="middleCondition" onchange="MiddleChange(this)" style="width:100%;height:30px;">
					<option value="">선택</option>
				</select>
			</td>
		</tr>
		<tr>
			<th rowspan="2" style="text-align:center;"><!-- <span class="import_marker">* </span> -->세부활동</th>
			<td style="text-align: left">
				<select id="select_condition" name="select_condition" style="height: 31px;">
					<option value="ctlg_nm">세부활동</option>
					<option value="ctlg_cd">세부활동 코드</option>
				</select>
				<input type="text" id="input_text_condition" class="sub_input" style="width:40%;height:30px;box-shadow : 0px 0px 1px #757575;border:none;"/>
				<button type="button" class="btn btn-sm btn-primary"onclick="onClick_Search()" style="vertical-align: top;"><i class="fas fa-search"></i>&nbsp;검색</button>
			</td>
		</tr>
		<tr>
			<td style="text-align: left">
				<select id="activity" name="activity" style="width:100%;height:30px;">
					<option value="">선택</option>
				</select>
			</td>
		</tr>
		<%-- <tr>
			<th scope="20%"><span class="import_marker">* </span>부대활동</th>
			<td scope="80%">
				<!-- data form -->
				<form id="ctlg_datas" name="ctlg_datas" method="post">
				<c:forEach items="${checkList}" var="checkLS" varStatus="status">					
					<input type="hidden" name="${fn:trim(checkLS.ctlg_cd)}" id="${fn:trim(checkLS.ctlg_itm_cd)}" value="${checkLS.ctlg_itm_ctnt}"/>
				</c:forEach>
				</form>
			
				<form method="post" name="selectedCode" action="">
						<input type="hidden" id="selected_code" name="selected_code" 
						style="background-color:white;border:1px solid blue;text-align:center;font-size:15pt;background-color:gray;color :black" 
						value="" disabled="disabled"/>
				</form>
	
				<form method="post" name="selectedName" action="" style="float: left; margin: 0; width:75%">
						<input id="selected_name" name="selected_name" 
						class="input-text-disabled"
						value="" disabled="disabled" style="font-size: 12pt;color: #757575;height:29px;"/>
				</form>
			
				<button type="Search" class="btn btn-sm btn-primary btn-width" data-toggle="modal" data-target="#TaskSearch" style="margin-left: 10px;"><i class="fas fa-search"></i>&nbsp;검색</button>
				<jsp:include page="taskSearchModal.jsp"></jsp:include>
			</td>
		</tr> --%>
		<tr>
			<th>체크리스트</th>
			<td>
				<select id="checkListByLogined" style="width: 100%; height:30px;">
					<option>부대활동을 선택해주십시오.</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<th>상세 내용</th>					
			<td>
				<textarea id="detail_area" name="detail_area" rows="4" cols="10" disabled="disabled" style="box-shadow : 0px 0px 1px #757575;border:none;"></textarea>
			</td>
		</tr>
		<tr>
			<th><!-- <span class="import_marker">* </span> -->이유</th>
			<td>
				<textarea id="rsn_area" name="rsn_area" rows="4" cols="10" placeholder="수정이 필요한 이유를 입력해주세요." style="box-shadow : 0px 0px 1px #757575;border:none;" onkeydown="javascript:fn_maxLength(this, 100);"></textarea>
			</td>
		</tr>
		<tr>
			<th><!-- <span class="import_marker">* </span> -->건의 내용</th>					
			<td>
				<textarea id="comment_area" name="comment_area" rows="4" cols="10" placeholder="건의할 체크리스트 내용을 입력해주세요." style="box-shadow : 0px 0px 1px #757575;border:none;" onkeydown="javascript:fn_maxLength(this, 500);"></textarea>
			</td>
		</tr>
		
		<form:form commandName="requestChecklistVO" name="requestChecklistForm" method="post">
			<form:hidden path="ctlg_cd"/>
			<form:hidden path="ctlg_itm_cd_1"/>
			<form:hidden path="rqst_ctnt"/>
			<form:hidden path="rsn"/>
			<form:hidden path="new_yn"/>
		</form:form>
		</table>
		
		<form id="ctlg_datas" name="ctlg_datas" method="post">
			<c:forEach items="${checkList}" var="checkLS" varStatus="status">					
				<input type="hidden" name="${fn:trim(checkLS.ctlg_cd)}" id="${fn:trim(checkLS.ctlg_itm_cd)}" value="${checkLS.ctlg_itm_ctnt}"/>
			</c:forEach>
		</form>
		
		<div style="text-align: center;">
			<button type="button" class="btn btn-sm btn-primary btn-width" id="request" onclick="javascript:fn_onClick_requestChecklist()">
			<i class="fas fa-pencil-alt"></i>&nbsp;등록</button>
			<button type="button" class="btn btn-sm btn-primary btn-width" id="back" onclick="javascript:location.href = 'CheckListManagement.do';">
			<i class="fas fa-undo"></i>&nbsp;취소</button>
		</div>	
		<br>	
		</div>
	
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>

</body>
</html>