<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- seungwon 19.02.27 -->
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- seungwon 19.02.27 -->   
 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>체크리스트 추가/수정</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<script src="js/submit.js"></script>
<link href="css/detail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">

<script type="text/javascript">	
	$(document).ready(function () {
		var checkList = new Array();
		var checkListItemCode = new Array();
		var checkListCode = new Array();
		
		var length = $("input[name = 'cd']").length;
		
		for(var i = 0; i < length; ++i) {
			checkListItemCode[i] = new Array();
			checkList[i] = new Array();
			
			checkListCode[i] = $("input[name = 'cd']")[i].value;
			console.log($("input[name = 'itm_cd']").length);
			
			var num = 0;
			var max = i*10 + 10;
			
			for(var x = i*10; x < max; ++x) {				
				checkListItemCode[i][num] = $("input[name = 'itm_cd']")[x].value;
				checkList[i][num] = $("input[name = 'ctnt']")[x].value;
				num += 1;
			}
		}
		
		var optionText, array = [], newString, maxChar = 15;
		
		$("#select").on("click", function(){
			var target = document.getElementById("checkListByLogined");
			var selectedCode = $("#selected_code").val();
			var selectedNum = -1;
			
			console.log(selectedNum);
			target.options.length = 0;
			$('textarea#detail_area').text("");
			
			var result = false;
			
			for(resultNum in checkListCode) {
				if(!result) {
					if(checkListCode[resultNum] == selectedCode) {
						selectedNum = resultNum;
						result = true;
					}
				}				
			}
			
			if(selectedNum == -1) {
				var opt = document.createElement("option");
				opt.value = -1;
				opt.innerHTML = "선택된 부대활동이 없습니다.";
				target.appendChild(opt);
			}
			else {
				var opt = document.createElement("option");
				opt.value = -1;
				opt.innerHTML = "항목을 선택해주십시오.";
				target.appendChild(opt);
				for(x in checkList[selectedNum]) {	
					var optList = document.createElement("option");
					optList.value = checkListItemCode[selectedNum][x];
					optList.innerHTML = checkList[selectedNum][x];
					target.appendChild(optList);
				}
			}		
			
			$('#checkListByLogined').each(function(){
			    $(this).find('option').each(function(i,e) {
			        $(e).attr('title',$(e).text());
			        optionText = $(e).text();
			        newString = '';
			        if (optionText.length > maxChar) {
			            array = optionText.split(' ');
			            $.each(array,function(ind,ele) { 
			                newString += ele+' ';
			                if (ind > 0 && newString.length > maxChar) {
			                	newString += ".."
			                    $(e).text(newString);
			                    return false;
			                }
			            });
			        }
			    });
			});
		});	
		
		
		$('#checkListByLogined').on("change", function(){
			var selectedValue = $('#checkListByLogined option:selected').val();
			var arrIndex_1 = -1;
			var arrIndex_2 = -1;
			
			if(selectedValue == -1) {
				$('textarea#detail_area').text("항목을 선택해주십시오.");
			}
			else {
				for(x in checkListItemCode) {
					if(checkListItemCode[x].indexOf(selectedValue) !== -1) {
						arrIndex_1 = x;
						arrIndex_2 = (checkListItemCode[x].indexOf(selectedValue));					
					}					
				}
				$('textarea#detail_area').text(checkList[arrIndex_1][arrIndex_2]);
			}			
		})
		
		function selectBox() {
			
		}
		
		$("#newCheck").click(function(){
			if($(this).is(":checked") == true) {
				$("#checkListByLogined option:eq(0)").prop('selected', 'selected');
				$("textarea#detail_area").text("");
				$("#checkListByLogined").prop('disabled', true);
			}				
			else {
				$("#checkListByLogined").prop('disabled', false);
			}				
		});
	});	
</script>

</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>	
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>체크리스트 추가/수정</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>체크리스트 추가/수정
			</div>		
		</article>
		
		<section class="subContent_section" id="auto">
		
		<div class="table_margin">
		<p style="text-align:center;">
			<img src="images/title_img/CheckListManagement_Request.png" alt="체크리스트 요청"  style="width:330px; height:80px;">
		</p>
		
		<table class="table table-striped sub_table table01" style="margin:auto;margin-bottom:10px;">
		<colgroup>
			<col width="20%"/>
			<col width="80%"/>
		</colgroup>
		<tr>
			<th scope="20%">신규 여부</th>
			<td scope="80%" style="text-align:left; padding-left:12px; padding-bottom:9px;">
				<input class="checkbox_fixed forChecklist" type="checkbox" id="newCheck" onclick=""/>
				<span>&emsp;* 신규항목 요청 시 체크해 주세요.</span> 
			</td>
		</tr>
		<tr>
			<th scope="20%">부대활동</th>
			<td scope="80%">
				<!-- data form -->
				<form id="ctlg_datas" name="ctlg_datas" method="post">
				<c:forEach items="${checkList}" var="checkLS" varStatus="status">
					<input type="hidden" name="cd" value="${checkLS.ctlg_cd}"/>
				
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_1_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_2_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_3_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_4_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_5_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_6_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_7_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_8_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_9_nm}"/>
					<input type="hidden" name="ctnt" value="${checkLS.ctlg_itm_cd_10_nm}"/>
					
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_1}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_2}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_3}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_4}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_5}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_6}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_7}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_8}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_9}"/>
					<input type="hidden" name="itm_cd" value="${checkLS.ctlg_itm_cd_10}"/>
				</c:forEach>
				</form>
			
				<form method="post" name="selectedCode" action="">
						<input type="hidden" id="selected_code" name="selected_code" 
						style="background-color:white;border:1px solid blue;text-align:center;font-size:15pt;background-color:gray;color :black" 
						value="" disabled="disabled"/>
				</form>
	
				<form method="post" name="selectedName" action="" style="float: left; margin: 0;">
						<input id="selected_name" name="selected_name" 
						class="input-text-disabled"
						value="" disabled="disabled"/>
				</form>
			
				<button type="Search" class="btn btn-sm btn-primary btn-width" data-toggle="modal" data-target="#TaskSearch" style="margin-left: 10px;"><i class="fas fa-search"></i>&nbsp;검색</button>
				<jsp:include page="taskSearchModal.jsp"></jsp:include>
			</td>
		</tr>
		<tr>
			<th scope="20%">체크리스트</th>
			<td scope="80%">
				<select id="checkListByLogined" style="width: 100%; height:30px;">
					<option>부대활동을 선택해주십시오.</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<th scope="20%">상세 내용</th>					
			<td scope="80%">
				<textarea id="detail_area" name="detail_area" rows="4" cols="10" disabled="disabled" style="box-shadow : 0px 0px 1px #757575;font-size: 16px;border:none;"></textarea>
			</td>
		</tr>
		<tr>
			<th scope="20%">이유</th>
			<td scope="80%">
				<textarea id="rsn_area" name="rsn_area" rows="4" cols="10" placeholder="수정이 필요한 이유를 입력해주세요." style="font-size: 16px;box-shadow : 0px 0px 1px #757575;border:none;"></textarea>
			</td>
		</tr>
		<tr>
			<th scope="20%">건의 내용</th>					
			<td scope="80%">
				<textarea id="comment_area" name="comment_area" rows="4" cols="10" placeholder="건의할 체크리스트 내용을 입력해주세요." style="font-size: 16px;box-shadow : 0px 0px 1px #757575;border:none;"></textarea>
			</td>
		</tr>
		
		<form:form commandName="requestChecklistVO" name="requestChecklistForm" method="post">
			<form:hidden path="ctlg_cd"/>
			<form:hidden path="ctlg_itm_cd_1"/>
			<form:hidden path="rqst_ctnt"/>
			<form:hidden path="rsn"/>
			<form:hidden path="new_yn"/>
		</form:form>
		</table>
		
		<div style="text-align: center;">
			<button type="button" class="btn btn-sm btn-primary btn-width" id="request" onclick="javascript:fn_onClick_requestChecklist()">
			<i class="fas fa-pencil-alt"></i>&nbsp;등록</button>
			<button type="button" class="btn btn-sm btn-primary btn-width" id="back" onclick="javascript:history.back();">
			<i class="fas fa-undo"></i>&nbsp;취소</button>
		</div>	
		<br>	
		</div>
	
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>

</body>
</html>