$(document).ready(function(){
	var requsetdate = $("#rqst_date").val();
	var actvtdate = $("#actvt_date").val();
	
	
	
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
	
	$("#actvt_date").datepicker({
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
	var condition = getParameterByName("state_select");
	$("#state_cd").val(condition).attr("selected", "true")
	
})
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Approve_select(index){
	//var index =  event.srcElement.value;
	if(index == null || index == "" || index =="undefined")
	{
		return false;
	}
	else{
	location.href ="CheckApprovalResult.do?seq="+index;
	}
}

function fn_checkApp_page(pageNo) {
	document.checkAppForm.currentPageNo.value = pageNo;
	document.checkAppForm.action = "CheckApproval.do";
	document.checkAppForm.submit();
}

function Request_Search(){
	
	var actvt_date 		= document.getElementById("actvt_date").value;
	var rqst_date 		= document.getElementById("rqst_date").value;
	var rqstr_srvno		= document.getElementById("rqstr_srvno").value;
	var actvt_type 		= document.getElementById("actvt_type").value;
	var state_cd 		= document.getElementById("state_cd");
	var state_select	= state_cd.options[state_cd.selectedIndex].value; 

	var searchList = new Object();
	
	searchList.actvt_date 	= actvt_date;
	searchList.rqst_date 	= rqst_date;
	searchList.rqstr_srvno 	= rqstr_srvno;
	searchList.actvt_type 	= actvt_type;
	searchList.state_cd 	= state_cd;
	searchList.state_select	= state_select;
	
	var jsonString = JSON.stringify(searchList);
	
	var loc = "CheckApproval.do?search=yes";
	if(actvt_date != "")
		{
			loc += "&actvt_date="+actvt_date;
		}
	if(rqst_date != "")
		{
			loc += "&rqst_date="+rqst_date;
		}
	if(rqstr_srvno != "")
		{
			loc += "&rqstr_srvno="+encodeURI(rqstr_srvno);
		}
	if(actvt_type != "")
		{
			loc += "&actvt_type="+encodeURI(actvt_type);
		}
//	if(state_cd != "")
//		{
//			loc += "&state_cd="+state_cd;
//		}
	if(state_select != "")
		{
			loc += "&state_select="+state_select;
		}
	document.location.href=loc;
	
//	$.ajax({
//		url:"CheckApproval_search_AJAX.do",
//		type:"post",
//		data:{"jsonString":jsonString},
//		success:whenSuccess,
//		error:whenError
//	});
	
}
function whenSuccess(result)
{
	obj = JSON.parse(result);
	$("#table_result>tbody>tr").remove();
	var str = '<tr>';
	if(obj[0].ctlg_cd == 'E001_001')
		{
			str += '<td colspan = 6> 결과가 없습니다</td></tr>'
		}
	else
		{
			$.each(obj, function(i){
				str += '<td>'+obj[i].actvt_date+'</td><td>'+obj[i].ctlg_cd+'</td><td>'+obj[i].rqstr_srvno+'</td>';
				str += '<td>'+obj[i].rqst_date+'</td><td>'+obj[i].state_cd+'</td>';
				str += '<td><button type="modify" class="btn btn-sm btn-success" 	id="modify" onclick="Approve_select('+obj[i].seq+')"  value="'+obj[i].seq;
				str += '"><i class="fas fa-check"></i>&nbsp;선택</button></td></tr>';
								
			})
		}
	$("#table_result>tbody").append(str);
}
function whenError(obj)
{
	alert("error!:"+obj);
}