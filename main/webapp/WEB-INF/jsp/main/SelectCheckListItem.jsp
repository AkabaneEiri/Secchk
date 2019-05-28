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
<title>체크리스트 항목 선별</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/SelectCheckListItem.js"></script>
<script src="js/jquery.dataTables.min.js"></script>

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
			<span>체크리스트 항목 선별</span>
		</article>
		<article class="cur_page">
			<div id="title">
			홈<span>></span>시스템 관리<span>></span>체크리스트 항목 선별
			</div>		
		</article>
		
		<section class="subContent_section" id="SelectCheckListItem">
		<br>
		<jsp:include page="TaskSearch.jsp"></jsp:include>		
		<table class="table table-striped sub_table table01" style="text-align:center;width:90%; margin:auto;margin-bottom:10px;margin-top:10px;" id=tablesort">
			<thead>
			<tr>
				<th style="font-size: 16px;">과업 종류</th>
				<th style="width:40%;text-align: left;font-size: 16px;">활용빈도&ensp;&ensp;표준여부&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;체크리스트 항목</th>
				<th></th>
				<th style="width:40%;text-align: left;font-size: 16px;">활용빈도&ensp;&ensp;표준여부&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;체크리스트 항목</th>
			</tr>
			</thead>
		<form name="rep" method="post">
			<tbody>
				<tr>
					<td style="padding:0px;">
					<select class="select_stripped" name="Task_name_List" id="Task_name_List"style="width:100%; height:350px;" multiple size="1" onclick="selectPre(this)">
							<c:forEach var="Task_List" items="${taskDataList}" varStatus="statics">
								<option value="${Task_List.cd}"<c:if test="${Task_seq == Task_List.cd}"> selected</c:if>>
									<c:out value="${Task_List.cd_nm}"/>
							</c:forEach>
					</select>
					<!--input id="PreSetNew" name="PreSetNew"style="box-shadow:0px 0px 1px;border:none;text-align:center;font-size:15pt;color :gray;"value=""/-->
					</td>
					<td style="padding:0px;">
						<select class="select_stripped" name="category_name" id="categofy_name"style="width:100%; height:350px; overflow-x: auto;" multiple size="10" onDblClick="move('right')" >
						<c:forEach var="list_false" items="${Checklist}" varStatus="statics">
							<option value="${list_false.ctlg_itm_cd }">
								<c:choose>
									<c:when test="${fn:length(list_false.prtcust_frqc_String) eq '1'}">
										&emsp;&nbsp;&nbsp;<c:out value="${list_false.prtcust_frqc_String}"/>&emsp;&emsp;&emsp;<c:out value="${list_false.stdd_yn}"/>&emsp;&emsp;&emsp;
											<c:choose>
												<c:when test ="${fn:length(list_false.ctlg_itm_ctnt) > 25}">
													<c:out value="${fn:substring(list_false.ctlg_itm_ctnt,0,25)}"/>...
												</c:when>
												<c:otherwise>
													<c:out value="${fn:substring(list_false.ctlg_itm_ctnt,0,25)}"/>...
												</c:otherwise>
											</c:choose>
									</c:when>
									<c:otherwise>
										&emsp;<c:out value="${list_false.prtcust_frqc_String}"/>&emsp;&emsp;&emsp;<c:out value="${list_false.stdd_yn}"/>&emsp;&emsp;&emsp;<c:choose>
												<c:when test ="${fn:length(list_false.ctlg_itm_ctnt) > 25}">
													<c:out value="${fn:substring(list_false.ctlg_itm_ctnt,0,25)}"/>...
												</c:when>
												<c:otherwise>
													<c:out value="${fn:substring(list_false.ctlg_itm_ctnt,0,25)}"/>...
												</c:otherwise>
											</c:choose>
									</c:otherwise>
								</c:choose>
							</option>
						</c:forEach>
						</select>
					</td>
					<td style="width:20px;">	
						<img src="images/btn_ll.png" onclick="move('left')" style="width:60px; height:70px">
						<br/>
						<br/>
						<br/>
						<img src="images/btn_rr.png" onclick="move('right')"style="width:60px; height:70px"> 
					</td>
					<td style="padding:0px;">		
						<select class="select_stripped" name="category_selected" id="category_selected"style="width:100%; height:350px; overflow-x: auto;" multiple size="10" onDblClick="move('left')">
						</select>
					</td>
				</tr>
			</tbody>
			</form>
		</table>
		
		<div style="width: 90%; margin: auto; font-size: 15px;margin-bottom: 10px;">
			<span class="import_marker">* </span><span>각 부대활동에 대한 과업 종류는 코드관리 메뉴를 통해서 입력해야 합니다.</span>
		</div>
		<div class="div_bottom_btn">
			<!--<button type="button" class="btn btn-sm btn-primary" 		id="test" onclick="SelectReset()"><i class="fas fa-redo"></i>&nbsp;초기화</button>
			&emsp;-->
			<button type="button" class="btn btn-sm btn-primary" 		id="test" onclick="MakeSelectPre()"><i class="fas fa-pencil-alt"></i>&nbsp;적용</button>
		</div>
		<br>
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>		
	</div>	
	
</body>
</html>