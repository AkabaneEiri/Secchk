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
<title>코드관리</title>
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
<script src="<%=request.getContextPath()%>/js/ManageCode_Insert_Selectbox.js"></script>
</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
		
	<div class="sub_contents_wrap">	
		<article class="sub_title">
			<span>코드관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>시스템관리<span>></span>코드관리<span>></span>입력
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">

		<div class="grp_table_margin">		
		<div style="text-align: left;width: 40%;font-size: 14px;"><span class="import_marker">* </span><span>는 필수입력 항목입니다.</span></div>
		
		<table class="table table-striped page_table sub_table table01" style="margin:auto; margin-top:10px;margin-bottom:10px;">
		<colgroup>
			<col width="20%">
			<col width="20%">
			<col width="60%">
		</colgroup>		
		<tbody style="text-align: center;">
			<tr id="code_tr1">
				<th colspan="2"><span class="import_marker">* </span>그룹코드</th>
				<td style="text-align: left;">
					<select id="search_dvs" name="search_dvs" style="width:78%; height:34px;">
						<option value="">선택</option>
						<c:forEach var="grpLS" items="${groupList}" varStatus="statics">
							<option value="${fn:trim(grpLS.cd)}">${grpLS.cd_nm}</option>
						</c:forEach>
					</select>
					<script>
						$("#search_dvs").val("<c:out value="${codeSearchVO.search_dvs}"/>").attr("selected", "selected");
					</script>
				</td>
			</tr>		
			<tr id="search_large" class="display_none">
				<th rowspan="3"><span class="import_marker">* </span>활동 유형</th>
				<th>대분류</th>
				<td style="text-align: left;">
					<select id="largeCondition" name="largeCondition" onchange="LargeChange(this)" style="width:78%;height:30px;">
						<option value="">선택</option>
						<c:forEach var="largeList" items="${largeList}" varStatus="statics">
							<option id="${fn:trim(largeList.cd)}" value="${fn:trim(largeList.cd)}"><c:out value="${largeList.cd_nm}"/></option>
						</c:forEach>
					</select>					
				</td>
			</tr>				
			<tr id="search_middle" class="display_none">
				<th>중분류</th>
				<td style="text-align: left;">
					<select id="middleCondition" name="middleCondition" onchange="MiddleChange(this)" style="width:78%;height:30px;">
						<option value="">선택</option>
					</select>
				</td>
			</tr>
			<tr id="search_act" class="display_none">
				<th>세부활동</th>
				<td style="text-align: left;">
					<select id="activity" name="activity" onchange="ActivityChange(this)" style="width:78%;height:30px;">
						<option value="">선택</option>
					</select>
				</td>
			</tr>
			<tr id="L9_large" class="display_none">				
				<th colspan="2"><span class="import_marker">* </span>대분류</th>
				<td style="text-align: left;">
					<select id="select_L9_large" name="select_L9_large" onchange="" style="width:78%;height:30px;">
						<option value="">선택</option>
						<c:forEach var="largeList" items="${largeList}" varStatus="statics">
							<option id="${fn:trim(largeList.cd)}" value="${fn:trim(largeList.cd)}"><c:out value="${largeList.cd_nm}"/></option>
						</c:forEach>
					</select>					
				</td>
			</tr>
			<tr id="search_cls_large" class="display_none">		
				<th rowspan="2"><span class="import_marker">* </span>활동유형</th>		
				<th>대분류</th>
				<td style="text-align: left;">
					<select id="select_cls_large" name="select_cls_large" onchange="onChange_cls_large()" style="width:78%;height:30px;">
						<option value="">선택</option>
						<c:forEach var="largeList" items="${largeList}" varStatus="statics">
							<option id="${fn:trim(largeList.cd)}" value="${fn:trim(largeList.cd)}"><c:out value="${largeList.cd_nm}"/></option>
						</c:forEach>
					</select>					
				</td>
			</tr>
			<tr id="search_cls_middle" class="display_none">	
				<th>중분류</th>
				<td style="text-align: left;">
					<select id="select_cls_middle" name="select_cls_middle" onchange="" style="width:78%;height:30px;">
						<option value="">선택</option>
					</select>					
				</td>
			</tr>
			<tr id="code_tr2">
				<th colspan="2"><span class="import_marker">* </span>코드</th>
				<td style="text-align: left;"><input class="sub_input" type="text" id="cd" class="form-control grp_input_width" placeholder="코드" style="ime-mode:disabled;" onkeyup="javascript:fn_notKor(this);"/></td>
			</tr>
			<tr id="code_tr3">
				<th colspan="2"><span class="import_marker">* </span>코드명</th>
				<td><input class="sub_input" type="text" id="cd_nm" class="form-control grp_input_width" placeholder="코드명" /></td>
			</tr>
			<tr id="code_tr4">
				<th colspan="2">비고</th>
				<td><input class="sub_input" type="text" id="rmrk" class="form-control grp_input_width" placeholder="비고" /></td>
			</tr>	
			
		</tbody>
		</table>
		<div class="div_bottom_btn">
			<button type="submit" id="submitModify" class="btn btn-sm btn-primary btn-width" onclick="javascript:do_insertCode();" >
			<i class="fas fa-check"></i>&nbsp;저장</button>
			
			<button type="button" id="back" class="btn btn-sm btn-primary btn-width" onclick="javascript:history.back();" >
			<i class="fas fa-undo"></i>&nbsp;취소</button>
		</div>		
		</div>
		
		<form:form commandName="codeVO" name="insertCodeForm" method="post">
			<form:hidden path="cd"/>
			<form:hidden path="cd_nm"/>
			<form:hidden path="rmrk"/>
			<form:hidden path="grp_cd"/>
		</form:form>		
		
		<!-- cls datas -->
		<c:forEach var="clsLS" items="${clsList }" varStatus="statics">
			<input type="hidden" name="${fn:trim(clsLS.lrgcls) }" id="${fn:trim(clsLS.mdcls) }" value="${clsLS.mdcls_nm }"/>
		</c:forEach>
		
		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>
