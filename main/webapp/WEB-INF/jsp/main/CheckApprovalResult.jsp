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
<title>조치 요청</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/CheckApprovalResult.js"></script>

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
			<span>조치 요청</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>지휘 및 통제<span>></span>조치 요청
			</div>		
		</article>
		<br>
			
		<section class="subContent_section" id="AssignTask">		
		<table class="table table-border sub_table table01"  style="text-align:center;width:90%; margin:auto;">
			<tbody>
			<form name="Approve_Value">
			<c:forEach var="Check" items="${Selected}" varStatus="statics">
				<tr>
					<th>요청자</th>
					<td>
						<input class="DiableInputbox" name="Value_srvno" value="${Check.rqstr_srvno}" disabled>
					</td>
					<th>부대활동 일자</th>
					<td >
						<input class="DiableInputbox" name="Value_rqst_date"  value="${Check.rqst_date}" disabled>
					</td>
				</tr>
				<tr>
					<th>부대활동 유형</th>
					<td>
						<input class="DiableInputbox" name="Value_type"  value="${Check.incdt_actvt_type_cd}" disabled>
					</td>
					<th>체크리스트</th>
					<td >
						<input class="DiableInputbox" name="Value_ctlg_cd"  value="${Check.ctlg_cd}" disabled>
					</td>
				</tr>
				<tr>
					<th>체크리스트 항목</th>
					<td colspan="3" >
						<input class="DiableInputbox" name="Value_ctlg_itm_cd"  value="${Check.ctlg_itm_cd}" style="ime-mode:active"disabled>
					</td>
				</tr>
				<tr>
					<th>사유</th>
					<td colspan="3" >
						<input class="DiableInputbox" name="Value_rsn" value="${Check.rsn}" style="ime-mode:active"disabled>
					</td>
				</tr>
				<tr>
					<th>검토 의견</th>
					<td colspan="3" style="color:#757575">
						<c:choose>
                    		<c:when test="${Check.state_cd eq '미승인'}">
                    			<input class="sub_input" name="Value_opn" id="opn" style="width:100%;border:0.5px solid #4e6361;ime-mode:active;" value="${Check.opn}">
                    			<br>
                    			<input class="sub_input" name="opn_res" id="opn_res" style="color:red;background-color: white;font-size: 12px;margin-top:10px;" value= "0/80"disabled>
                    		</c:when>
                    		<c:otherwise>
                    			<input class="DiableInputbox" name="Value_opn" value="${Check.opn}" disabled>
                    		</c:otherwise>
                    	</c:choose>				
					</td>
				</tr>
				</c:forEach>
				</form>
			</tbody>
			<tfoot>
			</tfoot>			
			</table>

			<br>
			<div class="SubmitButton" style="text-align:center;">
			<c:if test="${checkApprovalVO.state_cd eq '미승인'}">
				<button type="button" class="btn btn-sm btn-primary"  onclick="Approval_Approve()">
				<i class="fas fa-check"></i>&nbsp;승인</button>
				&nbsp;
				<!--  By mina 소요부서 요청으로 반려 기능 제거
				<button type="button" class="btn btn-sm btn-primary"  onclick="Approval_Deny()">
				<i class="fas fa-minus-circle"></i>&nbsp;반려</button>
				&nbsp;
				 -->
			</c:if>
				<button type="button" class="btn btn-sm btn-primary"  onclick="Approval_Cancle()">
				<i class="fas fa-list"></i>&nbsp;목록</button>
				
			</div> 
			<br>
		
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>

</body>
</html>