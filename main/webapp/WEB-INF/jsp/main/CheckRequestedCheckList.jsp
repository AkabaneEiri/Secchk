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
<title>체크리스트 검토</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/CheckRequestedCheckList.js"></script>
<script src="js/submit.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">
<link rel="stylesheet" type="text/css" href="css/datepicker.css">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>체크리스트 검토</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>시스템관리<span>></span>체크리스트 검토
			</div>		
		</article>
		
		<br>
		
		<section class="subContent_section" id="CheckRequest">
		<table class="table table-stripped sub_table table01"  style="text-align:center;width:90%; margin:auto;">
		<colgroup>
			<col width="87px"/>
			<col width="220px"/>
			<col width="87px"/>
			<col width="220px"/>
			<col width="87px"/>
			<col width="220px"/>
			<col width="87px"/>
			<col width="220px"/>
		</colgroup>
		<tbody style="border-bottom: 2px solid #17a2b8d6;">
			<tr>
				<th>신규여부</th>
				<td>
					<select style="display:inline" name="New_YN" id="New_YN" class="form-control">
						<option value="">선&emsp;택</option>
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</td>
				<th>체크리스트</th>
				<td>
					<input class="sub_input input_23" type="text" placeholder="체크리스트" maxlength="25" name="ctlg_cd" id="ctlg_cd" autocomplete="off" style="width:75%;"value="${requestChecklistVO.ctlg_cd}"/>
				</td>
				<th>요청자	</th>
				<td colspan="1">
					<input class="sub_input input_23" type="text" placeholder="요청자" maxlength="25" name="rqstr_srvno" id="rqstr_srvno" autocomplete="off" style="width:75%;"value="${requestChecklistVO.rqstr_srvno}"/>
				</td>
				
				<th>요청 일자</th>
				<td colspan="1">
					<input class="sub_input input-datepicker" type="text" placeholder="요청일자" maxlength="25" name="rqst_date" id="rqst_date" autocomplete="off" style="width:75%;"value="${Date}"/>
				</td>
					
			</tr>
			<tr>
				<th>상태</th>
				<td>
					<select style="display:inline" name="state_cd" id="state_cd" class="form-control">
						<option value="">선&emsp;택</option>
						<option value="D1">미승인</option>
						<option value="D2">승인완료</option>
						<option value="D3">반려</option>
					</select>
				</td>
				<th>현재 체크리스트</th>
				<td>
					<input class="sub_input input_23" type="text" placeholder="현재 체크리스트" maxlength="25" name="ctlg_itm_cd" id="ctlg_itm_cd" autocomplete="off" style="width:75%;" value="${requestChecklistVO.ctlg_itm_cd_1}"/>
				</td>
				
				<th>요청 내용</th>
				<td colspan="1" style="border-right: none;">
					<input class="sub_input input_23" type="text" placeholder="요청내용" maxlength="25" name="rqst_ctnt" id="rqst_ctnt" autocomplete="off" style="width:75%;"value="${requestChecklistVO.rqst_ctnt}"/>
				</td>
				<td style="border-right: none;">
					<button type="button" class="btn btn-sm btn-primary" style="margin:auto" onclick="request_search()"> 
						<i class="fas fa-search"></i>&nbsp;검색
					</button>
				</td>
				<td></td>
			</tr>
		</tbody>
		</table>
		<br>
		<table class="table table-stripped sub_table table01"  style="text-align:center;width:90%; margin:auto;" id="Request">
			<thead class="thead_title">
			<tr>
				<th style="width:5%">신 규</th>
				<th style="width:13%">체크리스트 분류</th>
				<th style="width:23%">현재 체크리스트</th>
				<th style="width:23%">요청 내용</th>
				<th style="width:9%">요청자</th>
				<th style="width:10%">요청 일자</th>
				<th style="width:7%">상태</th>
				<th style="width:7%">선택</th>
			</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${fn:length(CheckReuqest) eq 0}">
					<tr>
						<td colspan = 8>
							데이터가 없습니다.
						</td>
				</c:when>
			
			<c:otherwise>
			<c:forEach var="list" items="${CheckReuqest}" varStatus="statics">
				<tr>
					<td>${list.new_yn }</td>
					<td>${list.ctlg_cd}</td>
					<td><span class="word_break">${list.ctlg_itm_cd_1}</span></td>
					<td><span class="word_break">${list.rqst_ctnt}</span></td>
					<td>${list.rqstr_srvno}</td>
					<td>${list.rqst_date}</td>
					<td>${list.state_cd}</td>
					<td>
						<button type="button" class="btn btn-sm btn-success" 	id="modify" onclick="Request_select('${list.seq}')"value=${list.seq} ><i class="fas fa-check"></i>&nbsp;선택</button>
					</td>
				</tr>
			</c:forEach>
			</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		
		<table style="width:100%; margin:auto; margin-top: 10px;">
			<tr>
			<td style="text-align:center; width:100%">
				<div class="pagination_fixed">
					<form action="CheckRequestedCheckList.do" method="post" name="checkListForm">
						<c:if test="${not empty paginationInfo}">
							<div class="pagination">
								<ul class="pagination -sm">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_checkList_page" />
								</ul>
							</div>
						</c:if>
						<input type="hidden" id="currentPageNo" name="currentPageNo" />
					</form>
				</div>
			</td>
			</tr>
		</table>
		
		</section>
		
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>	
</body>
</html>