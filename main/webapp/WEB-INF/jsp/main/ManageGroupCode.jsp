<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!-- seungwon 19.02.21 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui"	   uri="http://egovframework.gov/ctl/ui" %>
<!-- seungwon 19.02.21 -->
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>그룹코드 관리</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>
<script src="<%=request.getContextPath()%>/js/fontawesome-all.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.min.js"></script>
<script src="<%=request.getContextPath()%>/js/swiper.js"></script>
<!-- seungwon 19.02.21 -->
<script src="<%=request.getContextPath()%>/js/submit.js"></script>
<script src="<%=request.getContextPath()%>/js/ManageCode.js"></script>

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
			<span>그룹코드 관리</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>코드관리<span>></span>그룹코드 관리
			</div>		
		</article>
		
		<section class="subContent_section" id="auto" style="height: auto">
		<div class="table_margin">
		<div class="memberwrapper">
			<form:form commandName="codeSearchVO" name="listForm" method="post">
				<table style="text-align:left;margin:auto;margin-top:10px;margin-bottom:10px;width:90%">
					<tr>
						<td style="width:97px;">■ 코드명 : </td>
						<td style="width:25%">
							<input id="search_nm" name="search_nm" style="height:30px;box-shadow:0px 0px 1px #757575;border:none;text-align:center;font-size:15pt;color :gray;" type="text" value="<c:out value="${codeSearchVO.search_nm}"/>" />
						</td>
						<td style="text-align:left; padding-left:10px;">
							<button type="button" class="btn btn-sm btn-primary" id="search" onclick="groupCode_Search()">
								<i class="fas fa-search"></i>&nbsp;검색
							</button>
							<button type="button" class="btn btn-sm btn-primary" id="mgrCode" onclick="fn_go_mgrCode()">
								<i class="fas fa-pencil-alt"></i>&nbsp;코드관리
							</button>
						</td>
					</tr>
				</table>
				<input type="hidden" id="currentPageNo" name="currentPageNo"/>
				<input type="hidden" id="currentSearch" name="currentSearch"/>
			</form:form>			
			
				<table class="table table-striped page_table sub_table table01" style="width:90%; text-align:center;margin:auto;margin-top:10px;margin-bottom:10px">
					<thead class="thead_text">
						<tr>
							<th>코드</th>
							<th>코드명</th>
							<th>비고</th>
							<th>수정/삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="list" items="${codeList}" varStatus="statics">
							<tr>
								<td name="cd">${list.cd}</td>
								<td>${list.cd_nm}</td>
								<td>${list.rmrk}</td>
								<td>
									<button type="modify" class="btn btn-sm btn-success"
										id="${list.cd}" onclick="groupCode_Update('${list.cd}')"
										value="${list.cd}">
										<i class="fas fa-pen-square"></i> 수정
									</button>
									<button type="delete" class="btn btn-sm btn-danger"
										id="${list.cd}" onclick="groupCode_Delete('${list.cd}')"
										value="${list.cd}">
										<i class="fas fa-trash"></i> 삭제
									</button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>			

			<table style="margin:auto; width:90%">
			<tr>
				<td style="text-align:center; width:70%">
					<div class="pagination_fixed">
					<form action="ManageGroupCode.do" method="post" name="codeForm">
						<c:if test="${not empty paginationInfo}">
							<div class="pagination">
								<ul class="pagination -sm">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search_group" />
								</ul>
							</div>
						</c:if>
						<input type="hidden" id="currentPageNo" name="currentPageNo"/>
						<input type="hidden" id="currentSearch" name="currentSearch"/>
					</form>
					</div>
				</td>
				<td style="text-align:right; width:20%; vertical-align: middle;">
					<div id="bottom_btn" style="text-align:right; margin-right:5%;">
						<button type="insert" class="btn btn-sm btn-primary" id="insert"
							onclick="groupCode_Insert()">
							<i class="fas fa-pencil-alt"></i>&nbsp;입력
						</button>
					</div>
				</td>
			</tr>
			</table>
			
		</div>	
		</div>
		
		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>
