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
<title>지휘관지침 입력</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/EnterCommanderGuide.js"></script>
<script src="js/EnterComGuide.js"></script>
<script src="js/jquery-ui.min.js"></script>

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
			<span>지휘관지침 입력</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>지휘 및 통제<span>></span>지휘관지침 입력
			</div>		
		</article>
		
		<section class="subContent_section" id="CommanderGuide">
		<br>
		<table style="text-align:left;width:70%; margin:auto; margin-top:10px;margin-bottom:10px;">
			<tr>
			<td style="width:12%">■ 부대활동</td>
			<td style="width:25%">
				<form method="post" name="SelectCode" action="">
					<input id="searchCondition1" name="searchCondition1" style="height:30px;background-color:#CDCDCD;box-shadow:0px 0px 1px #757575;border:none;text-align:center;font-size:14pt;color :inherit;"value="${listsearchVO.search_by_name}" disabled />
					<input type="hidden"  id="searchCode" name="searchCode" value=""/>
				</form>
			</td>
			<td style="width:7%; text-align:left; padding-left:10px;">
				<button type="Search" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#TaskSearch" ><i class="fas fa-search"></i>&nbsp;검색</button> 
				<jsp:include page="taskSearchModal.jsp"></jsp:include>
			</td>
			<td style="width:7%; text-align:center;">일시</td>
			<td style="width:13%">
				<form method="post" name="taskdate" action="">
					<input type="text" id="TaskDatepicker" style="height:30px;background-color: lightgray;box-shadow : 0px 0px 1px #757575;text-align:center;font-size:14pt;border:none;"  name="searchCondition2" value="${listsearchVO.date}">
				</form>
			</td>
			<td style="text-align:left; padding-left:10px;">
				<button type="Search" class="btn btn-sm btn-primary" 	id="search"	onclick="fn_search_task()"><i class="fas fa-search"></i>&nbsp;조회</button>
			</td>
			</tr>
		</table>

		<table class="table table-condensed sub_table table01" style="text-align:center;width:70%; margin:auto;">
			<tbody>
			<tr>
			<th colspan = "4">지휘관 지침</th>
			</tr>
			<c:choose>
				<c:when test="${taskData == null }">
				<tr>
					<td>데이터가 존재하지 않습니다.</td>
				</tr>
				</c:when>
				<c:otherwise>
				
				<form method="post" name="guidnc_name" action="">
					<c:choose>
						<c:when test="${ fn:trim(taskData.state_cd) eq 'E3'}">
							<tr> <!-- 지침1 -->
								<td style="width:200px">지침1</td>
								<td>
									<input id="CUI1" name="CUI1" style="box-shadow : 0px 0px 1px #848484;background-color:#CDCDCD;text-align:center;font-size:15pt;border:none;"value="${taskData.guidnc_1}" disabled/>
								</td>
								<td style="width:200px"></td>
							</tr>
							
							<tr> <!-- 지침2 -->
								<td>지침2	</td>
								<td>
									<input id="CUI2" name="CUI2" style="box-shadow : 0px 0px 1px #848484;background-color:#CDCDCD;text-align:center;font-size:15pt;border:none;" value="${taskData.guidnc_2}" disabled/>
								</td>
								<td style="width:200px"></td>
							</tr>
							
							<tr> <!-- 지침3 -->
								<td>지침3</td>
								<td>
									<input id="CUI3" name="CUI3" style="box-shadow : 0px 0px 1px #848484;background-color:#CDCDCD;text-align:center;font-size:15pt;border:none;"value="${taskData.guidnc_3}" disabled/>
								</td>
								<td style="width:200px"></td>
							</tr>
						</c:when>
						
						<c:otherwise>
							<tr> <!-- 지침1 -->
								<td style="width:200px">지침1</td>
								<td>
									<input id="CUI1" name="CUI1" style="box-shadow : 0px 0px 1px #848484;background-color:white;border:none ;text-align:center;font-size:15pt;color :#585858;border:none;" value="${taskData.guidnc_1}" />
								</td>
								<td style="width:200px">
									<button type="Search" class="btn btn-sm btn-danger" 	id="search"	onclick="Guide_Delete1()"><i class="fas fa-trash"></i>삭제</button>
								</td>
							</tr>
							<tr> <!-- 지침2 -->
								<td>지침2</td>
								<td>
									<input id="CUI2" name="CUI2" style="box-shadow : 0px 0px 1px #848484;background-color:white;border:none ;text-align:center;font-size:15pt;color :#585858;border:none;"value="${taskData.guidnc_2}" />
								</td>
								<td>
									<button type="Search" class="btn btn-sm btn-danger" 	id="search"	onclick="Guide_Delete2()"><i class="fas fa-trash"></i>삭제</button>
								</td>
							</tr>
							<tr> <!-- 지침3 -->
								<td>지침3</td>
								<td>
									<input id="Modify3" name="CUI3" style="box-shadow : 0px 0px 1px #848484;background-color:white;border:none ;text-align:center;font-size:15pt;color :#585858;border:none;"value="${taskData.guidnc_3}">
								</td>
								<td>
									<button type="Search" class="btn btn-sm btn-danger" 	id="search"	onclick="Guide_Delete3()"><i class="fas fa-trash"></i>삭제</button>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
					
					<input type="hidden" id="taskDataID" value="${taskData.id }"/>
				</form>				
					
				</c:otherwise>
			</c:choose>							
				
			</tbody>
		</table>

		<c:if test="${taskData != null }">
			<div class="div_bottom_btn" style="margin-top:5px;">
				<button type="button" id="submitModify"
					class="btn btn-sm btn-primary btn-width"
					onclick="javascript:Guide_Insert();">
					<i class="fas fa-check"></i>&nbsp;확인
				</button>

				<button type="button" id="back"
					class="btn btn-sm btn-primary btn-width"
					onclick="javascript:history.back();">
					<i class="fas fa-undo"></i>&nbsp;뒤로
				</button>
			</div>
		</c:if>
		
		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>