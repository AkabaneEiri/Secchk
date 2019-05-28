<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>사용자 관리</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/UserList.js"></script>
<script src="js/FormSubmit.js"></script>
<script src="js/submit.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">

</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">
		<article class="sub_title">
			<span>사용자 관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>사용자 관리
			</div>		
		</article>
		
	
	<div class="memberwrapper">
	<div id="Wrap">
			<table style="text-align:center;margin:auto;margin-top:10px;margin-bottom:10px;width:90%">
				<tr>
				<%
				String athrt = "";
				
				if(session.getAttribute("SS_ATHRT") != null) {
					athrt = ( (String) session.getAttribute("SS_ATHRT") ).trim();
				}
				
				if (athrt.equals("B3")) {
				%>
				<td style="width: 92px;">
					<select id="searchAthrt" name="searchAthrt" style="height:32px;font-size:15px">
						<option value="B1">일반사용자</option>
						<option value="B2">중간사용자</option>
					</select>
				</td>
				<%
				}
				%>
				<td style="width: 60px;">
					<select id="searchCondition" name="searchCondition" style="height:32px;font-size:15px">
						<option value="00" >성명</option>
						<option value="01">	군번	</option>
						<option value="02">	직책	</option>
					</select>
				</td>
				<td style="width:20%">
					<input id="searchKeyword" name="searchKeyword" type="text"  style="border:1px solid gray; height:30px;
					font-size:15px;"value="<c:out value="${membersearchVO.searchKeyword}"/>"/>
				</td>
				<td style="text-align:left; padding-left:10px;">
					<button type="Search" class="btn btn-sm btn-primary" id="search"	onclick="Member_Search_ex()">
					<i class="fas fa-search"></i>&nbsp;검색</button>
				</td>
				</tr>
			</table>
	</div>
	<table class="table table-striped sub_table table01"  style="width: 90%; text-align: center;margin:auto" >
		<thead class="thead_title">
			<tr>
				<th>권한</th>
				<th>아이디(군번)</th>
				<th>계급</th>
				<th>성명</th>
				<th>직책</th>
				<th>비밀번호 변경일</th>
				<th>수정/삭제</th>
				<!--				
				<c:if test="${sessionScope.cd == '1'}">
					<td>수정/삭제</td>
				</c:if>
				-->
			</tr>
		</thead>
		<tbody>
		<c:forEach var="list" items="${memberList}" varStatus="statics">
			<tr>
						<td>
							<c:choose>
								<c:when test="${fn:trim(list.athrt) == 'B1' }">일반사용자</c:when>
								<c:when test="${fn:trim(list.athrt) == 'B2' }">중간사용자</c:when>
								<c:when test="${fn:trim(list.athrt) == 'B3' }">최고사용자</c:when>
							</c:choose>
						</td>
						<td name="srvno">${list.srvno}</td>
						<td>${list.cd_nm}</td>
						<td>${list.stmt}</td>
						<td>${list.rspofc_nm}</td>
						<td>${list.chng_date}</td>
						<td>
							<button type="modify" class="btn btn-sm btn-success" 	id="${list.srvno}" onclick="Member_Update('${list.srvno}')" value="${list.srvno}"><i class="fas fa-pen-square"></i>&nbsp;수정</button>
							<button type="delete" class="btn btn-sm btn-danger" 	id="${list.srvno}" onclick="Member_Delete('${list.srvno}')"value="${list.srvno}"><i class="fas fa-trash"></i>&nbsp;삭제</button>
						</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br>
	</div>
	<table style="width:90%; margin:auto;" id="paginationTable">
			<tbody>
			<tr>
			<td style="text-align:center; width:100%">
				<div class="pagination_fixed">
					<form action="Userlist.do" method="post" name="checkAppForm">
						<c:if test="${not empty paginationInfo}">
							<div class="pagination">
								<ul class="pagination -sm">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_Userlist_page" />
								</ul>
							</div>
						</c:if>
						<input type="hidden" id="currentPageNo" name="currentPageNo" />
					</form>
				</div>
			</td>
			<td>
			<button type="button" class="btn btn-sm btn-primary" style="vertical-align:top;"id="insert" onclick="Member_Insert()"><i class="fas fa-pencil-alt"></i>&nbsp;등록</button>
			</td>
			</tr>
			</tbody>
	</table>
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
		
</body>
</html>