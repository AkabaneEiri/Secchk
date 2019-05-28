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
<title>부대활동 모니터링</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script src="<%=request.getContextPath()%>/js/fontawesome-all.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.js"></script>
<!-- seungwon 19.02.21 -->
<script src="<%=request.getContextPath()%>/js/submit.js"></script>

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
			홈<span>></span>부대활동 모니터링
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		
		<div class="table_margin">
		
		<div class="div_title"><span style="margin-right: 10px;">${actVO.actvt_date}</span>   <span style="margin-left: 10px;">${actVO.incdt_actvt_type_cd_nm}</span></div>		
		
		<table class="table table-striped indexboard sub_table table01">
			<thead class="thead_text" style="text-align: center">
				<tr>
					<th>과업</th>
					<th>담당자</th>
					<th>상태</th>
					<th>착수시간</th>
					<th>종료시간 </th>
				</tr>
			</thead>
			<tbody style="text-align: center">
			<c:forEach var="taskList" items="${actTaskList}" varStatus="status">
				<c:choose>
					<c:when test="${taskList == null}">
						<tr>
							<td>해당 일자에 시행되는 부대활동이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
							<tr>
								<td><c:out value="${taskList.task}"/></td>
								<td><c:out value="${taskList.task_psnchnrg_nm}"/></td>
								
								<c:choose>
									<c:when test="${fn:trim(taskList.state_cd) == 'E1'}">
										<td><button type="submit" class="btn btn-sm btn-secondary btn-width" id="e1">미착수</button></td>
									</c:when>
									<c:when test="${fn:trim(taskList.state_cd) == 'E2'}">
										<td><button type="submit" class="btn btn-sm btn-success btn-width" id="e2">진행중</button></td>
									</c:when>
									<c:when test="${fn:trim(taskList.state_cd) == 'E3'}">
										<td><button type="submit" class="btn btn-sm btn-primary btn-width" id="e3">완료</button></td>
									</c:when>
									<c:otherwise>								
										<td><button type="submit" class="btn btn-sm btn-danger btn-width" id="e4">승인대기</button></td>
									</c:otherwise>
								</c:choose>	
								
								<td>${taskList.start_date}</td>
								<td>${taskList.fnsh_date}</td>
							</tr>
					</c:otherwise>
				</c:choose>				
			</c:forEach>
			</tbody>
		</table>
		
		</div>

		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>