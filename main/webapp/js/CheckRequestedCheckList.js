$(document).ready(function(){
	$("#rqst_date").datepicker({
		dateFormat:'yy-mm-dd',
		prevText:"이전 달",
		nextText: "다음 달",
		monthNames:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		monthNamesShort:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
		dayNames:['일','월','화','수','목','금','토'],
		dayNamesShort:['일','월','화','수','목','금','토'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		showMonthAfterYear: true,
		changeMonth:true,
		changeYear:true,
		yearSuffix:'년',
		showOn: "button",
		buttonText: "<i class='far fa-calendar-alt'></i>"
	});
	
	var condition_yn = getParameterByName("new_select");
	$("#New_YN").val(condition_yn).attr("selected", "true")
	var condition_state = getParameterByName("state_cd");
	$("#state_cd").val(condition_state).attr("selected", "true")
	
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Request_select(index){
	//var index = event.srcElement.value;
	if(index == null || index == "" || index =="undefined")
	{
		return false;
	}
	else{
	location.href ="CheckRequestSelect.do?seq="+encodeURI(index);
	}
}

function fn_checkList_page(pageNo) {
	document.checkListForm.currentPageNo.value = pageNo;
	document.checkListForm.action = "CheckRequestedCheckList.do";
	document.checkListForm.submit();
}

function request_search(){
	var new_yn 		= document.getElementById("New_YN");
	var new_select 	= new_yn.options[new_yn.selectedIndex].value; 
	
	var state 		= document.getElementById("state_cd");
	var state_cd 	= state.options[state.selectedIndex].value;
	
	var ctlg_cd 	= document.getElementById("ctlg_cd").value;
	
	var ctlg_itm_cd = document.getElementById("ctlg_itm_cd").value;
	
	var rqst_ctnt 	= document.getElementById("rqst_ctnt").value;
	var rqst_date 	= document.getElementById("rqst_date").value;
	var rqstr_srvno = document.getElementById("rqstr_srvno").value;
	var loc = "CheckRequestedCheckList.do?search=yes";

	if(rqst_date != "")
		{
			loc += "&new_select="+new_select;
		}
	if(state_cd != "")
		{
			loc += "&state_cd="+state_cd;
		}
	if(ctlg_cd != "")
		{
			loc += "&ctlg_cd="+encodeURI(ctlg_cd);
		}
	if(ctlg_itm_cd != "")
		{
			loc += "&ctlg_itm_cd="+encodeURI(ctlg_itm_cd);
		}
	if(rqst_ctnt != "")
	{
		loc += "&rqst_ctnt="+encodeURI(rqst_ctnt);
	}
	if(rqstr_srvno != "")
	{
		loc += "&rqstr_srvno="+encodeURI(rqstr_srvno);
	}
	if(rqst_date != "")
		{
			loc += "&rqst_date="+rqst_date;
		}
	document.location.href=loc;
	
//	var code = new Object();
//	
//	code.new_yn 		= new_select;
//	code.state_cd 		= state_cd;
//	code.ctlg_cd 		= ctlg_cd;
//	code.ctlg_itm_cd 	= ctlg_itm_cd;
//	code.rqst_ctnt 		= rqst_ctnt;
//	code.rqst_date 		= rqst_date;
//	code.rqstr_srvno 	= rqstr_srvno;
//	
//	var jsonString = JSON.stringify(code);
//		
//	$.ajax({
//			url 	: "CheckRequestCheckList_Search_Ajax_Json.do",
//			type	: "post",
//			data 	: {"jsonString":jsonString},
//			success	: whenSuccessSearch,
//			error	: whenError
//	})
}
function whenError(res){
	alert("일시적 오류가 발생하였습니다.")
	console.log("오류 발생 :" + res);
}
function whenSuccessSearch(res)
{
	console.log("success! :" + res);
	$("#Request>tbody:last>tr").remove();
	var obj = JSON.parse(res);
	var str = "<tr>";
	if(obj[0].ctlg_cd == "E001")
		{
			$.each(obj, function(i){
				str +="<td colspan=8>";
				str +="데이터가 없습니다.";
				str +="</td>";
			})
		}
	else
		{
//		신규	체크리스트분류	현재체크리스트	요청내용	요청자	요청일자	상태	버튼
//		"rqst_date":"2019-05-13",
//		"rqstr_srvno":"김준성일반",
//		"state_cd":"미승인",
//		"ctlg_cd":"해\/강안 수색정찰",
//		"New_yn":"Y",
//		"ctlg_itm_cd":"이동 및 작전활동간 위험지역은 사전 판단하여 작전지역 조정 등 대비책을 강구하였다.\r\n *낭떠러지, 암석지대, 절벽, 결빙 등 기동 위험지역, 투입지점 일대 상습 교통 혼잡지역 등",
//		"rqst_ctnt":"테스트"		
			$.each(obj, function(i){
				str +="<td>"+obj[i].New_yn+"</td>";
				str +="<td>"+obj[i].ctlg_cd+"</td>";
				str +="<td><span class='word_break'>"+obj[i].ctlg_itm_cd+"</td>";
				str +="<td><span class='word_break'>"+obj[i].rqst_ctnt+"</td>";
				str +="<td>"+obj[i].rqstr_srvno+"</td>";
				str +="<td>"+obj[i].rqst_date+"</td>";
				str +="<td>"+obj[i].state_cd+"</td>";
				str +='<td><button type="button" class="btn btn-sm btn-success" 	id="modify" onclick="Request_select('+obj[i].seq+')"value='+obj[i].seq+' ><i class="fas fa-check"></i>&nbsp;선택</button></td>';
				str+="</tr>";
			})
		}
	$('#Request>tbody').append(str);
	}