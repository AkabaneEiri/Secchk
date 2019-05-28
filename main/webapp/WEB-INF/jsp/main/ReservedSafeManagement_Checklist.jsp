<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- seungwon 19.02.14 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.14 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>예정된 안전관리활동</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>

<!-- seungwon 19.02.15 -->
<script src="js/submit.js"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<!-- seungwon 19.02.17 detail css -->
<link href="css/detail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">
</head>


<body>
	<jsp:include page="ListCheckbox_Proc.jsp"></jsp:include>
	<jsp:include page="Header.jsp"></jsp:include>
		
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>예정된 안전관리활동</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
				홈<span>></span>예정된 안전관리활동
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		
		<!-- seungwon 19.02.14 -->
		<div class="table_margin">
		<div class="div_title"><span style="margin-right: 10px;">${taskData.actvt_date}</span>   <span style="margin-left: 10px;">${taskData.ctlg_nm}</span></div>
		<table class="table table-striped sub_table table01" style="text-align:left;">
		<colgroup>
			<col width="70%"/>
			<col width="15%"/>
			<col width="15%"/>
		</colgroup>
		<thead class="thead_title">
			<tr>
				<th class="table_text_center" scope="70%"><c:out value="지휘관 지침"/></th>
				<th class="table_text_center" scope="15%"><c:out value="Yes"/></th>
				<th class="table_text_center" scope="15%"><c:out value="No"/></th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${taskData == null}">
				<c:choose>
					<c:when test="${SS_athrt == 'M'}">
						<tr>
							<td colspan="6">현재 지휘관 지침이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">현재 지휘관 지침이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>			
				<tr>
				<td style="padding-bottom: 10px; text-align: left;">
					<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
					
					<c:choose>
						<c:when test="${taskData.guidnc_1 == null || taskData.guidnc_1 == ''}">
						<div class="div_text" style="text-align: center;">
							<c:out value="-"/>
						</div>
						</c:when>
						<c:otherwise>
							<div class="div_text">
								<a href="javascript:fn_go_commanderDetail('${taskData.ctlg_cd}', '1', '${taskData.actvt_date}')"><c:out value="${taskData.guidnc_1}"/></a>
							</div>
						</c:otherwise>
					</c:choose>	
				</td>				
				<td class="table_text_center">
					<input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="guidnc_1_y_chk"/>
				</td>
				<td class="table_text_center">
					<input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="guidnc_1_n_chk"/>
				</td>
				</tr>			
				<tr>
				<td style="padding-bottom: 10px; text-align: left;">
					<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
					<c:choose>
						<c:when test="${taskData.guidnc_2 == null || taskData.guidnc_2 == ''}">
							<div class="div_text" style="text-align: center;">
								<c:out value="-"/>
							</div>
						</c:when>
						<c:otherwise>
							<div class="div_text">
								<a href="javascript:fn_go_commanderDetail('${taskData.ctlg_cd}', '2', '${taskData.actvt_date}')"><c:out value="${taskData.guidnc_2}"/></a>
							</div>
						</c:otherwise>
					</c:choose>						
					
				</td>
				<td class="table_text_center">
						<input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="guidnc_2_y_chk"/>
				</td>
				<td class="table_text_center">
						<input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="guidnc_2_n_chk"/>
				</td>
				</tr> <!-- 지침2 -->
				<tr>
				<td style="padding-bottom: 10px; text-align: left;">
					<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
					<c:choose>
						<c:when test="${taskData.guidnc_3 == null || taskData.guidnc_3 == ''}">
							<div class="div_text" style="text-align: center;">
								<c:out value="-"/>
							</div>
						</c:when>
						<c:otherwise>
							<div class="div_text">
								<a href="javascript:fn_go_commanderDetail('${taskData.incdt_actvt_type_cd}', '3', '${taskData.actvt_date}')"><c:out value="${taskData.guidnc_3}"/></a>
							</div>
						</c:otherwise>
					</c:choose>					
					
				</td>
				<td class="table_text_center">
					<input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="guidnc_3_y_chk"/>
				</td>
				<td class="table_text_center">
					<input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="guidnc_3_n_chk"/>
				</td>
				</tr> <!-- 지침3 -->
			<form:form commandName="listsearchVO" name="commanderForm" method="post">
				<form:hidden path="date"/>
				<form:hidden path="findCode"/>
				<form:hidden path="cmdNum"/>
			</form:form>			
			</c:otherwise>
		</c:choose>
		</tbody>
		</table>
		<table class="table table-striped sub_table table01" style="text-align:left;">
		<colgroup>
			<col width="70%"/>
			<col width="15%"/>
			<col width="15%"/>
		</colgroup>
		<thead class="thead_title">
			<tr>
				<th class="table_text_center" scope="70%"><c:out value="체크리스트"/></th>
				<th class="table_text_center" scope="15%"><c:out value="Yes"/></th>
				<th class="table_text_center" scope="15%"><c:out value="No"/></th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${taskData == null}">
				<c:choose>
					<c:when test="${SS_athrt == 'M'}">
						<tr>
							<td colspan="6">현재 체크리스트가 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5">현재 체크리스트가 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_1 == null || fn:trim(taskData.ctlg_itm_cd_1) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_1}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_1_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>						
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_1_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_1_n_chk"/></td>
				</tr>
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_2 == null || fn:trim(taskData.ctlg_itm_cd_2) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_2}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_2_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>							
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_2_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_2_n_chk"/></td>
				</tr> <!-- 체크리스트2 -->
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_3 == null || fn:trim(taskData.ctlg_itm_cd_3) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>	
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_3}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_3_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_3_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_3_n_chk"/></td>
				</tr> <!-- 체크리스트3 -->		
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_4 == null || fn:trim(taskData.ctlg_itm_cd_4) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>	
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_4}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_4_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_4_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_4_n_chk"/></td>
				</tr> <!-- 체크리스트4 -->
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_5 == null || fn:trim(taskData.ctlg_itm_cd_5) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>	
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_5}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_5_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>						
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_5_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_5_n_chk"/></td>
				</tr> <!-- 체크리스트5 -->
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_6 == null || fn:trim(taskData.ctlg_itm_cd_6) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>	
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_6}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_6_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>							
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_6_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_6_n_chk"/></td>
				</tr> <!-- 체크리스트6 -->	
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_7 == null || fn:trim(taskData.ctlg_itm_cd_7) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>	
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_7}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_7_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>	
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_7_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_7_n_chk"/></td>
				</tr> <!-- 체크리스트7 -->
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_8 == null || fn:trim(taskData.ctlg_itm_cd_8) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>	
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_8}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_8_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>	
						
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_8_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_8_n_chk"/></td>	
				</tr> <!-- 체크리스트8 -->
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_9 == null || fn:trim(taskData.ctlg_itm_cd_9) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>	
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_9}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_9_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>							
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_9_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_9_n_chk"/></td>
				</tr> <!-- 체크리스트9 -->	
				<tr>
					<td style="padding-bottom: 10px; text-align: left;">
						<!--  <div class="div_icon"><img id="img_list_icon" src="images/board_bullet.png"/></div> -->
						<c:choose>
							<c:when test="${taskData.ctlg_itm_cd_10 == null || fn:trim(taskData.ctlg_itm_cd_10) == ''}">
								<div class="div_text" style="text-align: center;">
									<c:out value="-"/>
								</div>
							</c:when>
							<c:otherwise>	
								<div class="div_text"><a href="javascript:fn_go_detail('<c:out value="${taskData.ctlg_itm_cd_10}"/>')"><span class="word_break"><c:out value="${taskData.ctlg_itm_cd_10_nm}"/></span></a></div>
							</c:otherwise>
						</c:choose>							
					</td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_10_y_chk"/></td>
					<td class="table_text_center"><input class="checkbox_fixed forChecklist" type="checkbox" name="actCheck" id="ctlg_10_n_chk"/></td>
				</tr> <!-- 체크리스트10 -->			
			</c:otherwise>
		</c:choose>
		</tbody>
		</table>
		</div>
		
		<div style="text-align: center">
		<c:choose>
			<c:when test="${fn:trim(taskData.state_cd) == 'E3' }">
				<button type="submit" class="btn btn-sm btn-primary btn-width" id="back" onclick="javascript:history.back();">
				<i class="fas fa-undo"></i>&nbsp;취소</button>
			</c:when>
			<c:otherwise>
				<button class="btn btn-sm btn-primary btn-width" id="save" onclick="fn_set_ynStatus('${taskData.seq}')">
					<i class="fas fa-check"></i>&nbsp;확인</button>
				<button type="submit" class="btn btn-sm btn-primary btn-width" id="back" onclick="javascript:history.back();">
				<i class="fas fa-undo"></i>&nbsp;취소</button>
				<!-- <button class="btn btn-sm btn-primary btn-width" id="complete" onclick="fn_set_finishtime('${taskData.seq}')" style="width: 75px;">최종완료</button> -->
				<!-- <button class="btn2 button" style="width: 90px" id="taskComplete" onclick="fn_set_finishtime('${activity.incdt_actvt_type_cd}')">과업완료</button> -->
			</c:otherwise>
		</c:choose>			
		</div>
		
		<form:form commandName="taskDataVO" name="ynForm" method="post">
			<form:hidden path="seq"/>
			<form:hidden path="incdt_idtf_cd"/>
			<form:hidden path="id"/>
			<form:hidden path="task_psnchnrg_srvno"/>
			<form:hidden path="guidnc_yn_1"/>
			<form:hidden path="guidnc_yn_2"/>
			<form:hidden path="guidnc_yn_3"/>
			<form:hidden path="ctlg_itm_yn_1"/>
			<form:hidden path="ctlg_itm_yn_2"/>
			<form:hidden path="ctlg_itm_yn_3"/>
			<form:hidden path="ctlg_itm_yn_4"/>
			<form:hidden path="ctlg_itm_yn_5"/>
			<form:hidden path="ctlg_itm_yn_6"/>
			<form:hidden path="ctlg_itm_yn_7"/>
			<form:hidden path="ctlg_itm_yn_8"/>
			<form:hidden path="ctlg_itm_yn_9"/>
			<form:hidden path="ctlg_itm_yn_10"/>
			<form:hidden path="isAllChecked"/>
		</form:form>
		
		<form:form commandName="listsearchVO" name="checklistForm" method="post">
			<form:hidden path="findCode"/>
			<form:hidden path="actId" value="${taskData.id}"/>
			<form:hidden path="srvno" value="${taskData.task_psnchnrg_srvno}"/>
			<form:hidden path="seq" value="${taskData.seq}"/>
			<form:hidden path="state_cd" value="${taskData.state_cd}"/>
		</form:form>	
		<!-- seungwon 19.02.14 -->
		<br>
		
		</section>	
				
	<jsp:include page="Footer.jsp"></jsp:include>
	</div>
	
</body>
</html>