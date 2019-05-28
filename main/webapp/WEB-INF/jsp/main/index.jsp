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
<title>안전관리 체크리스트체계</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/common.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/fontawesome-all.js" type="text/javascript"></script>
<%-- <script src="<%=request.getContextPath()%>/js/swiper.min.js" type="text/javascript"></script> --%>
<script src="<%=request.getContextPath()%>/js/swiper.js" type="text/javascript"></script>
<!-- seungwon 19.02.21 -->
<script src="<%=request.getContextPath()%>/js/submit.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/detail.css">

<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/swiper.css"> --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main_detail.css">

<!-- 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.1.6/css/swiper.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.1.6/css/swiper.min.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.1.6/js/swiper.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.1.6/js/swiper.min.js"></script>
 -->
 
<script src="<%=request.getContextPath()%>/js/swiper/swiper.js"  type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/swiper/swiper.min.js"  type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/swiper/swiper.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/swiper/swiper.min.css">


<%
Object obj = session.getAttribute("loginedUser");
String athrt = "";
if(session.getAttribute("SS_ATHRT") != null) {
	athrt = ( (String) session.getAttribute("SS_ATHRT") ).trim();
}

if(obj != null) {
	if(athrt.equals("B2")) {
%>
<script>
$(document).ready(function(){
	var obj = new Object(); //컨트롤러의 리턴값을 저장할 객체

	$.ajax({
		url:'./InfomationAjax.do', //request 보낼 서버의 경로
		type:'post', // 메소드(get, post, put 등)
		data: {}, //보낼 데이터
		success: function(result) {
			//서버로부터 정상적으로 응답이 왔을 때 실행 
			obj = jQuery.parseJSON(result);
			if(obj.cntRqstUgcy == 0 && obj.cntRqstAct == 0 && obj.cntRqstChk == 0) {
				console.log("Ajax No Data.");
			}else{
				window.open("Infomation.do", "popup", "width=500, height=250, resizable=no");
			}
		},
		error: function(err) {
			//서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
			console.log("ajax : false");
		}
	});
})
</script>
<%
	}
}
%>

<script>
function maxLengthCheck(object){
   if (object.value.length > object.maxLength){
    object.value = object.value.slice(0, object.maxLength);
   }    
  }
</script>
</head>

<body>

	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="contents_wrap" style="background-color: #eaeaea;">		
		<article class="slider" style="height: auto; margin-top: 0px;">
			<div class="swiper-container">
				<!--   <div class="swiper-scrollbar"></div> -->
				<div class="swiper-button-prev"></div>
				<div class="swiper-button-next"></div>
				<div class="swiper-wrapper">
					<div class="swiper-slide"><img src="images/slider_4.png" alt=""></div>
					<div class="swiper-slide"><img src="images/slider_1.jpg" alt=""></div>
					<div class="swiper-slide"><img src="images/slider_2.jpg" alt=""></div>
				</div>
				<div class="swiper-pagination"></div>
			</div>
		</article>
		<section class="display-section">
			<div class="container">
				<div class="row">
					<div class="col-xs-4">
						<%				
							if(obj == null || athrt.equals("B1")) {
						%>
							<a href="ReservedSafeManagement.do">
								<div class="icon" id="icon1">
									<div id="icon1">
										<img id="img_in_div" src="<%=request.getContextPath()%>/images/reserv_safe.png" alt="예정활동">
										<!-- <i class="fas fa-user-circle"></i>	 -->
									</div>
									<!--  <span class="txt">예정된 안전관리활동</span>  //by mina -->
								</div>
							</a>
						<%
							} 
							else if(athrt.equals("B2") || athrt.equals("B3")) {
						%>
							<a href="TroopsMonitoring.do">
								<div class="icon" id="icon1">
									<div id="icon1">
										<img id="img_in_div" src="<%=request.getContextPath()%>/images/monitor.png" alt="부대활동 모니터링">
										<!-- <i class="fas fa-user-circle"></i>	 -->
									</div>
									<!-- <span class="txt">부대활동 모니터링</span> //by mina -->
								</div>
							</a>
						<%
							}
						%>
						
					</div>
					<div class="col-xs-4">
						<%
							if(obj == null || athrt.equals("B1")) {
						%>
							<a href="OccasionalSafeManagement.do">
								<div class="icon" id="icon1">
									<div id="icon1">
										<img id="img_in_div" src="<%=request.getContextPath()%>/images/occ_safe.png" alt="수시활동">
										<!-- <i class="fas fa-user-circle"></i>	 -->
									</div>
									<!--  <span class="txt">수시 안전관리활동</span> //by mina-->
								</div>
							</a>
						<%
							} 
							else if(athrt.equals("B2") || athrt.equals("B3")) {
						%>
							<a href="ManageCode.do">
								<div class="icon" id="icon1">
									<div id="icon1">
										<img id="img_in_div" src="<%=request.getContextPath()%>/images/manage_sys.png" alt="코드관리">
										<!-- <i class="fas fa-user-circle"></i>	 -->
									</div>
									<!-- <span class="txt">코드 관리</span> //by mina  -->
								</div>
							</a>							
						<%
							}
						%>
						
						<!-- 최고관리자 B4 -->
						<%
							if(obj != null && athrt.equals("B4")) {
						%>
							<a href="TroopsMonitoring.do">
								<div class="icon" id="icon1">
									<div id="icon1">
										<img id="img_in_div" src="<%=request.getContextPath()%>/images/monitor.png" alt="부대활동 모니터링">
										<!-- <i class="fas fa-user-circle"></i>	 -->
									</div>
									<!-- <span class="txt">부대활동 모니터링</span> //by mina -->
								</div>
							</a>
						<%
							}
						%>
					</div>
					<div class="col-xs-4">
						<%			
							if(obj == null || athrt.equals("B1")) {
						%>
							<a href="CheckListManagement.do">
								<div class="icon" id="icon1">
									<div id="icon1">
										<img id="img_in_div" src="<%=request.getContextPath()%>/images/manage_checklist.png" alt="체크리스트 추가/수정">
										<!-- <i class="fas fa-user-circle"></i>	 -->
									</div>
									<!-- <span class="txt">체크리스트 추가/수정</span> //by mina -->
								</div>
							</a>
						<%
							} 
							else if(athrt.equals("B2") || athrt.equals("B3")) {
						%>
							<a href="Userlist.do">
								<div class="icon" id="icon1">
									<div id="icon1">
										<img id="img_in_div" src="<%=request.getContextPath()%>/images/manage_user.png" alt="사용자 관리">
										<!-- <i class="fas fa-user-circle"></i>	 -->
									</div>
									<!-- <span class="txt">사용자 관리</span> //by mina -->
								</div>
							</a>
						<%
							}
						%>				
						
					</div>
				</div>				
			</div>
		</section>
		<div class="separate_line" id="line1"></div>		
	<section class="mainBoard" id="visible">
		<%
		if(obj == null || athrt.equals("B1")) {
		%>
		<ul id="tabmenu1"> 		
			<li class="tab_btn1" style="width: 100%;">
				<a href="ReservedSafeManagement.do">예정 활동</a>
			</li>	
			<dd>
				<ul  class="txt1" style="background-color:white">
					<!-- start -->
					<table class="table indexboard">
						<thead class="thead_text">
							<tr>
								<td>활동일자</td>
								<td>활동 명</td>
								<td>진행 상태</td>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${taskList == null }">
								</c:when>
								<c:otherwise>
									<c:forEach var="taskLS" items="${taskList}" varStatus="status">
										<tr>
											<td><fmt:parseDate value="${taskLS.actvt_date}" var="dateFmt" pattern="yyyy-MM-dd" /><fmt:formatDate value="${dateFmt}" pattern="yyyy.MM.dd" /></td>
											<td><a href="ReservedSafeManagement.do">${taskLS.incdt_actvt_type_cd_nm}</a></td>
											<c:choose>
												<c:when test="${fn:trim(taskLS.state_cd) == 'E1' }">
													<td>미착수</td>
												</c:when>
												<c:when test="${fn:trim(taskLS.state_cd) == 'E2' }">
													<td>진행중</td>
												</c:when>
												<c:otherwise>
													<td>종료</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<!-- end -->				
					<li>
						<span id="moreBtn"><a href="ReservedSafeManagement.do"><img src="images/more_btn.png" alt="더보기">		
						</a></span>
					</li>
				</ul>
			</dd>
			<!-- start -->
			<!-- 						
			<dd style="display: none;">
				<ul  class="txt1" style="background-color:white">
					
					<table class="table indexboard">
						<thead class="thead_text">
							<tr>
								<td>요청 일자</td>
								<td>활동 명</td>
								<td>진행 상태</td>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${reqActList == null }">
								</c:when>
								<c:otherwise>
									<c:forEach var="reqActLS" items="${reqActList}" varStatus="status">
										<tr>
											<td><fmt:parseDate value="${reqActLS.rqst_date}" var="dateFmt" pattern="yyyy-MM-dd" /><fmt:formatDate value="${dateFmt}" pattern="yyyy.MM.dd" /></td>
											<td><a href="javascript:fn_go_rqstActDetail_seq('${reqActLS.seq}');">${reqActLS.incdt_actvt_type_cd_nm}</a></td>
											<c:choose>
												<c:when test="${fn:trim(reqActLS.state_cd) == 'D1' }">
													<td>검토 중</td>
												</c:when>
												<c:when test="${fn:trim(reqActLS.state_cd) == 'D2' }">
													<td>승인 완료</td>
												</c:when>
												<c:otherwise>
													<td>반려</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					
					<li>
						<span id="moreBtn"><a href="OccasionalSafeManagement.do"><img src="images/more_btn.png">		
						</a></span>
					</li>					
				</ul>
			</dd>	
			 -->
			<!-- end -->	
			<!-- start -->
			<!-- 			
			<dd style="display: none;">
				<ul  class="txt1" style="background-color:white">
					
					<table class="table indexboard">
						<thead class="thead_text">
							<tr>
								<td>요청 일자</td>
								<td>활동 명</td>
								<td>진행 상태</td>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${reqCheckList == null }">
								</c:when>
								<c:otherwise>
									<c:forEach var="reqCheckLS" items="${reqCheckList}" varStatus="status">
										<tr>
											<td><fmt:parseDate value="${reqCheckLS.rqst_date}" var="dateFmt" pattern="yyyy-MM-dd" /><fmt:formatDate value="${dateFmt}" pattern="yyyy.MM.dd" /></td>
											<td><a href="javascript:fn_go_rqstChkDetail_seq('${reqCheckLS.seq}');">${reqCheckLS.ctlg_nm}</a></td>
											<c:choose>
												<c:when test="${fn:trim(reqCheckLS.state_cd) == 'D1' }">
													<td>검토 중</td>
												</c:when>
												<c:when test="${fn:trim(reqCheckLS.state_cd) == 'D2' }">
													<td>승인 완료</td>
												</c:when>
												<c:otherwise>
													<td>반려</td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					
					<li>
						<span id="moreBtn"><a href="CheckListManagement.do"><img src="images/more_btn.png">	
						</a></span>
					</li>
				</ul>	
			</dd>
			 -->
			 <!-- end -->
		</ul>
		<%
		}
		else if(athrt.equals("B2") || athrt.equals("B3")) {
		%>
		<ul id="tabmenu1"> 				
			<li class="tab_btn1">
				<a href="CheckApproval.do">조치요청 검토</a>
			</li>	
			<li class="tab_btn2">
				<a href="CheckOccasionalSafeManagement.do">수시활동 검토</a>
			</li>	
			<li class="tab_btn3">
				<a href="CheckRequestedCheckList.do">체크리스트 검토</a>
			</li>
			<dd>
			<ul  class="txt1" style="background-color:white">
			<table class="table indexboard">
			<thead class="thead_text">
				<tr>
					<td>활동일자</td>
					<td>활동유형</td>
					<td>내용</td>
					<td>요청자</td>
				</tr>
			</thead>
			<tbody>
			<c:choose>
			<c:when test="${approList == null }">
			</c:when>
			<c:otherwise>
				<c:forEach var="appLS" items="${approList}" varStatus="status">
					<tr>
						<td><fmt:parseDate value="${appLS.actvt_date}" var="dateFmt" pattern="yyyy-MM-dd"/><fmt:formatDate value="${dateFmt}" pattern="yyyy.MM.dd"/></td>
						<td>
						<a href="javascript:fn_go_CheckApprovalDeatale_seq('${appLS.seq}');">${appLS.incdt_actvt_type_cd_nm}</a>
						</td>
						<td>${appLS.ctlg_itm_nm}</td>
						<td>${appLS.rqstr_rnk}/${appLS.rqstr_nm}</td>
					</tr>
				</c:forEach>
			</c:otherwise>				
			</c:choose>				
			</tbody>
			</table>
				<li>
					<span id="moreBtn"><a href="CheckApproval.do"><img src="images/more_btn.png" alt="더보기">		
					</a></span>
				</li>
			</ul>
			</dd>						
			<dd style="display: none;">
			<ul  class="txt1" style="background-color:white">
			<table class="table indexboard">
			<thead class="thead_text">
				<tr>
					<td>활동일자</td>
					<td>요청된 활동유형</td>
					<td>사유</td>
					<td>요청자</td>
				</tr>
			</thead>
			<tbody>
			<c:choose>
			<c:when test="${chkActList == null }">
			</c:when>
			<c:otherwise>
				<c:forEach var="chkActLS" items="${chkActList}" varStatus="status">
					<tr>
						<td><fmt:parseDate value="${chkActLS.actvt_date}" var="dateFmt" pattern="yyyy-MM-dd"/><fmt:formatDate value="${dateFmt}" pattern="yyyy.MM.dd"/></td>
						<td>
							<a href="javascript:fn_go_CheckReqIncdt_seq('${chkActLS.seq}');">${chkActLS.incdt_actvt_type_cd_nm}</a>
						</td>
						<td>${chkActLS.rsn}</td>
						<td>${chkActLS.rqstr_rnk}/${chkActLS.rqstr_nm}</td>
					</tr>
				</c:forEach>
			</c:otherwise>				
			</c:choose>				
			</tbody>
			</table>
				<li>
					<span id="moreBtn"><a href="CheckOccasionalSafeManagement.do"><img src="images/more_btn.png" alt="더보기">		
					</a></span>
				</li>
			</ul>
			</dd>					
			<dd style="display: none;">
			<ul  class="txt1" style="background-color:white">
			<table class="table indexboard">
			<thead class="thead_text">
				<tr>
					<td>요청일자</td>
					<td>요청된 체크리스트</td>
					<td>내용</td>
					<td>요청자</td>
				</tr>
			</thead>
			<tbody>
			<c:choose>
			<c:when test="${chkCheckList == null }">
			</c:when>
			<c:otherwise>
				<c:forEach var="chkCheckLS" items="${chkCheckList}" varStatus="status">
					<tr>
						<td><fmt:parseDate value="${chkCheckLS.rqst_date}" var="dateFmt" pattern="yyyy-MM-dd"/><fmt:formatDate value="${dateFmt}" pattern="yyyy.MM.dd"/></td>
						<td>
						<a href="javascript:fn_go_CheckReqCheck_seq('${chkCheckLS.seq}');">${chkCheckLS.ctlg_nm}</a>
						</td>
						<td>${chkCheckLS.ctlg_itm_nm}</td>
						<td>${chkCheckLS.rqstr_rnk}/${chkCheckLS.rqstr_nm}</td>
					</tr>
				</c:forEach>
			</c:otherwise>				
			</c:choose>				
			</tbody>
			</table>
				<li>
					<span id="moreBtn"><a href="CheckRequestedCheckList.do"><img src="images/more_btn.png" alt="더보기">		
					</a></span>
				</li>
			</ul>	
			</dd>
		</ul>
		<%
		}
		%>		
		<form:form commandName="listsearchVO" name="checklistForm" method="post">
			<form:hidden path="findCode"/>
			<form:hidden path="date"/>
		</form:form>
	</section>
		<jsp:include page="Footer.jsp"></jsp:include>
	</div>			
				
	
</body>
</html>