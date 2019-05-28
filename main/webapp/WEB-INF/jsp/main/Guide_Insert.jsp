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
			<span>지휘관지침</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>지휘 및 통제<span>></span>부대활동 모니터링<span>></span>지휘관지침
			</div>		
		</article>
		
		<section class="subContent_section" id="CommanderGuide">
		<br>		
		
		<div class="div_title"><span style="margin-right: 10px;">${actVO.actvt_date}</span>   <span style="margin-left: 10px;">${actVO.incdt_actvt_type_cd_nm}</span></div>

		<table class="table table-condensed sub_table table01" style="text-align:center;width:70%; margin:auto;margin-bottom:10px;">
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
						<c:when test="${ fn:trim(actVO.state_cd) eq 'E3'}">
							<tr> <!-- 지침1 -->
								<td style="width:200px">지침1</td>
								<td>
									<input class="sub_input" id="CUI1" name="CUI1" style="box-shadow : 0px 0px 1px #848484;background-color:#CDCDCD;text-align:left;border:none;"value="${taskData.guidnc_1}" disabled/>
								</td>
								<td style="width:200px"></td>
							</tr>
							
							<tr> <!-- 지침2 -->
								<td>지침2	</td>
								<td>
									<input class="sub_input" id="CUI2" name="CUI2" style="box-shadow : 0px 0px 1px #848484;background-color:#CDCDCD;text-align:left;border:none;" value="${taskData.guidnc_2}" disabled/>
								</td>
								<td style="width:200px"></td>
							</tr>
							
							<tr> <!-- 지침3 -->
								<td>지침3</td>
								<td>
									<input class="sub_input" id="CUI3" name="CUI3" style="box-shadow : 0px 0px 1px #848484;background-color:#CDCDCD;text-align:left;border:none;"value="${taskData.guidnc_3}" disabled/>
								</td>
								<td style="width:200px"></td>
							</tr>
						</c:when>
						
						<c:otherwise>
							<tr> <!-- 지침1 -->
								<td style="width:200px">지침1</td>
								<td>
									<input class="sub_input" id="CUI1" name="CUI1" style="background-color:white;border:none ;text-align: left;color :#585858;border:none;" value="${taskData.guidnc_1}" />
								</td>
								<td style="width:200px">
									<button type="button" class="btn btn-sm btn-danger" 	id="search"	onclick="Guide_Delete1()"><i class="fas fa-trash"></i>&nbsp;삭제</button>
								</td>
							</tr>
							<tr> <!-- 지침2 -->
								<td>지침2</td>
								<td>
									<input class="sub_input" id="CUI2" name="CUI2" style="background-color:white;border:none ;text-align: left;color :#585858;border:none;"value="${taskData.guidnc_2}" />
								</td>
								<td>
									<button type="button" class="btn btn-sm btn-danger" 	id="search"	onclick="Guide_Delete2()"><i class="fas fa-trash"></i>&nbsp;삭제</button>
								</td>
							</tr>
							<tr> <!-- 지침3 -->
								<td>지침3</td>
								<td>
									<input class="sub_input" id="Modify3" name="CUI3" style="background-color:white;border:none ;text-align: left;color :#585858;border:none;"value="${taskData.guidnc_3}">
								</td>
								<td>
									<button type="button" class="btn btn-sm btn-danger" 	id="search"	onclick="Guide_Delete3()"><i class="fas fa-trash"></i>&nbsp;삭제</button>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
					
					<input type="hidden" id="taskDataID" value="${taskData.id }"/>
					<input type="hidden" id="date" value="${actVO.actvt_date }"/>
				</form>				
					
				</c:otherwise>
			</c:choose>							
				
			</tbody>
		</table>

		<c:if test="${taskData != null }">
			<div class="div_bottom_btn" style="margin-top:5px;">
				<c:if test="${ fn:trim(actVO.state_cd) != 'E3'}">
					<button type="button" id="submitModify"
						class="btn btn-sm btn-primary btn-width"
						onclick="javascript:Guide_Insert();">
						<i class="fas fa-check"></i>&nbsp;확인
					</button>
				</c:if>

				<button type="button" id="back"
					class="btn btn-sm btn-primary btn-width"
					onclick="javascript:go_Monitor('${actVO.actvt_date }');">
					<i class="fas fa-undo"></i>&nbsp;취소
				</button>
			</div>
		</c:if>
		
		</section>	
		<jsp:include page="Footer.jsp"></jsp:include>
		
</div>	
</body>
</html>