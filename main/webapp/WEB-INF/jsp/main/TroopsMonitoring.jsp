<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!-- seungwon 19.02.21 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.21 -->

<%
	Object obj = session.getAttribute("loginedUser");
	String athrt = (String) session.getAttribute("SS_ATHRT");
	if(session.getAttribute("SS_ATHRT") != null) {
		athrt = ( (String) session.getAttribute("SS_ATHRT") ).trim();
	}
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>부대활동 모니터링</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script src="<%=request.getContextPath()%>/js/fontawesome-all.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.js"></script>
<!-- seungwon 19.02.21 -->
<script src="<%=request.getContextPath()%>/js/submit.js"></script>
<script src="<%=request.getContextPath()%>/js/TroopsMonitoring.js"></script>
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
			<span>부대활동 모니터링</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
				홈<span>></span>지휘 및 통제<span>></span>부대활동 모니터링
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		<div class="table_margin">
			<table style="text-align:left;width:100%; margin:auto; margin-top:10px;margin-bottom:10px;">
				<form:form commandName="listsearchVO" name="activityDateForm" method="get">
				<%
				if(obj != null && athrt.equals("B4")) {
				%>
					<tr style="line-height: 2;">
						<td style="width:9%">■ 부대 </td>
						<td style="width:40%">
							<input class="sub_input DiableInputbox" style="height:27px;font-size: 12pt;box-shadow : 0px 0px 1px #757575;color: #757575;width:73%;background-color:#eaeaea;"
								type="text" placeholder="소속부대검색"  name="incdt_idtf_nm" id="incdt_idtf_nm" autocomplete="off" value="${curIdtfName }" disabled/>
								
							<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#TaskSearch" ><i class="fas fa-search"></i>&nbsp;검색</button>
							<jsp:include page="TroopsSearchModal.jsp"></jsp:include>
						</td>
						<td style="width:45px; text-align:left;">일시</td>
						<td style="width:184px;" >
						<select id="activityYear" style="height:30px;">
						<c:set var="year" value="2019"/>
						<c:forEach begin="2019" end="2030">
							<c:choose>
								<c:when test="${year == curYear }">
									<option value="${year}" selected="selected">${year}</option>
								</c:when>
								<c:otherwise>
									<option value="${year}">${year}</option>
								</c:otherwise>
							</c:choose>								
							<c:set var="year" value="${year+1}"/>
						</c:forEach>
						</select>
						<select id="activityMonth" style="height:30px;">
						<c:set var="month" value="1"/>
						<c:forEach begin="1" end="12">
							<c:choose>
								<c:when test="${month == curMon }">
									<option value="${month}" selected="selected">${month}</option>
								</c:when>
								<c:otherwise>
									<option value="${month}">${month}</option>
								</c:otherwise>
							</c:choose>	
							<c:set var="month" value="${month+1}"/>
						</c:forEach>
						</select>
						<select id="activityDay" style="height:30px;">
						<c:set var="day" value="1"/>
						<c:forEach begin="1" end="31">
							<c:choose>
								<c:when test="${day == curDay }">
									<option value="${day}" selected="selected">${day}</option>
								</c:when>
								<c:otherwise>
									<option value="${day}">${day}</option>
								</c:otherwise>
							</c:choose>	
							<c:set var="day" value="${day+1}"/>
						</c:forEach>
						</select>
					</td>	
					<td>
						<button type="submit" class="btn btn-sm btn-primary btn-width" id="search" onclick="javascript:fn_activitySearch()">
						<i class="fas fa-search"></i>&nbsp;조회</button>
					</td>
					</tr>
				<%
				}		
				else {
				%>
				<tr>			
					<td style="width:45px; text-align:left;">일시</td>
					<td style="width:184px;" >
						<select id="activityYear" style="height:30px;">
						<c:set var="year" value="2019"/>
						<c:forEach begin="2019" end="2030">
							<c:choose>
								<c:when test="${year == curYear }">
									<option value="${year}" selected="selected">${year}</option>
								</c:when>
								<c:otherwise>
									<option value="${year}">${year}</option>
								</c:otherwise>
							</c:choose>								
							<c:set var="year" value="${year+1}"/>
						</c:forEach>
						</select>
						<select id="activityMonth" style="height:30px;">
						<c:set var="month" value="1"/>
						<c:forEach begin="1" end="12">
							<c:choose>
								<c:when test="${month == curMon }">
									<option value="${month}" selected="selected">${month}</option>
								</c:when>
								<c:otherwise>
									<option value="${month}">${month}</option>
								</c:otherwise>
							</c:choose>	
							<c:set var="month" value="${month+1}"/>
						</c:forEach>
						</select>
						<select id="activityDay" style="height:30px;">
						<c:set var="day" value="1"/>
						<c:forEach begin="1" end="31">
							<c:choose>
								<c:when test="${day == curDay }">
									<option value="${day}" selected="selected">${day}</option>
								</c:when>
								<c:otherwise>
									<option value="${day}">${day}</option>
								</c:otherwise>
							</c:choose>	
							<c:set var="day" value="${day+1}"/>
						</c:forEach>
						</select>
					</td>	
					<td>
						<button type="submit" class="btn btn-sm btn-primary btn-width" id="search" onclick="javascript:fn_activitySearch()">
						<i class="fas fa-search"></i>&nbsp;조회</button>
					</td>		
				</tr>
				<%
				}
				%>
				<form:hidden path="incdt_idtf_cd" value="${curIdtf }"/>
				<form:hidden path="date"/>
				</form:form>
			</table>
			
		<table class="table table-striped sub_table table01" style="margin-top: 10px;">
		<colgroup>
			<col width="36%"/>
			<col width="10%"/>
			<col width="17%"/>
			<col width="17%"/>
			<% if(obj != null && athrt.equals("B2")) { %>
			<col width="20%"/>
			<% } %>
		</colgroup>
			<thead class="thead_text" style="text-align: center;">
				<tr>
					<th>부대활동유형</th>
					<th>상태</th>
					<th style="min-width: 74px;">착수시간</th>
					<th style="min-width: 74px;">종료시간 </th>
					<% if(obj != null && athrt.equals("B2")) { %>
					<th>지휘관 지침</th>
					<% } %>
				</tr>
			</thead>
			<tbody style="text-align: center">
			<c:choose>
				<c:when test="${fn:length(activityList) == 0 }">
				<tr>
					<td colspan="5">해당 일자에 진행 중인 부대활동이 없습니다.</td>
				</tr>
				</c:when>
			</c:choose>
			<c:forEach var="actList" items="${activityList}" varStatus="status">
				<c:choose>
					<c:when test="${actList == null}">
						<tr>
							<td>해당 일자에 시행되는 부대활동이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
							<tr>
								<td>
									<a href="javascript:fn_taskSearch('${actList.id}')">
									<span class="word_break"><c:out value="${actList.incdt_actvt_type_cd_nm}"/></span>
									</a>
								</td>
								
								<c:choose>
									<c:when test="${fn:trim(actList.state_cd) == 'E1'}">
										<td><button type="button" class="btn btn-sm btn-secondary btn-width" id="e1" disable>미착수</button></td>
									</c:when>
									<c:when test="${fn:trim(actList.state_cd) == 'E2'}">
										<td><button type="button" class="btn btn-sm btn-success btn-width" id="e2" disable>진행중</button></td>
									</c:when>
									<c:otherwise>								
										<td><button type="button" class="btn btn-sm btn-primary btn-width" id="e3" disable>완료</button></td>
									</c:otherwise>
								</c:choose>	
								
								<td>${actList.start_date}</td>
								<td>${actList.fnsh_date}</td>
								<% if(obj != null && athrt.equals("B2")) { %>
								<td>
									<c:choose>
										<c:when test="${fn:trim(actList.state_cd) == 'E3'}">
											<button type="insert" class="btn btn-sm btn-success" id="insert"
											onclick="go_Guide_Insert('${fn:trim(actList.id) }')">
											<i class="fas fa-pencil-alt"></i>&nbsp;상세
											</button>
										</c:when>
										<c:otherwise>
											<button type="insert" class="btn btn-sm btn-primary" id="insert"
											onclick="go_Guide_Insert('${fn:trim(actList.id) }')">
											<i class="fas fa-pencil-alt"></i>&nbsp;입력
											</button>
										</c:otherwise>
									</c:choose>
									
								</td>
								<% } %>
							</tr>
					</c:otherwise>
				</c:choose>				
			</c:forEach>
			<form:form commandName="listsearchVO" name="taskDataForm" method="post">
					<form:hidden path="findCode"/>
					<form:hidden path="date"/>
					<form:hidden path="actId"/>
					<form:hidden path="state_cd"/>
			</form:form>	
			</tbody>
		</table>
		
		<div class="pagination_fixed">
			<form action="TroopsMonitoring_Search.do" method="post" name="troopsForm">
				<c:if test="${not empty paginationInfo}">
					<div class="pagination">
						<ul class="pagination -sm">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search_troops" />
						</ul>
					</div>
				</c:if>
				<input type="hidden" id="currentPageNo" name="currentPageNo"/>
				<input type="hidden" id="date" name="date"/>
			</form>
		</div>

		</div>

		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>
