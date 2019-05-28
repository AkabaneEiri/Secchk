
$(document).ready(function(){
})

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function checkFileType(filePath) {
	var fileFormat = filePath.split(".");
	if (fileFormat.indexOf("xlsx") > -1 || fileFormat.indexOf("xls") > -1) {
		return true;
	} else {
		return false;
	}

}


function Submit_parent()
{
	var Name = document.getElementById("result_nm").value;
	var Code = document.getElementById("result_cd").value;
	var lrgcls = document.getElementById("result_lrgcls").value;
	var mdcls = document.getElementById("result_mccls").value;
	var code = new Object();
	
	if(lrgcls == "")
		{
			alert("검색어를 입력하여 주십시오");
			return false;
		}
	
	code.name = Code;
	code.lrgcls = lrgcls;
	code.mdcls = mdcls;
	
	var jsonString = JSON.stringify(code);
	
	$.ajax({
		url:"ManageCheckListItem_AJAX_JSON.do",
		type:"post",
		data:{"jsonString":jsonString},
		success:whenSuccess,
		error:whenError
	})

	
}
function whenSuccess(res)
{
	console.log(res);
	$("#table_hover>tbody:last > tr").remove();
	$("#table_hover>td:last").remove();
	var obj = JSON.parse(res);
	var str = '';
	if(obj[0].ctlg_cd=="E001")
		{
			$.each(obj, function(i){
				str+= '<tr>';
				str += '<td colspan =3 >데이터가 없습니다.</td>';
				str += '</tr>';
			});
		}
	else
		{
			$.each(obj, function(i){
				str+= '<tr>';
				str += '<td style="text-align:center;">'+obj[i].ctlg_itm_cd+'</td>';
				str += '<td  class="word_break" style="text-align:left;">'+obj[i].ctlg_itm_ctnt+'</td>';
				str += '<td style="text-align:center;">'+obj[i].prtcuse_frqc+'</td>';
				str += '</tr>';
			});
		}
	console.log(str);
	$('#table_hover > tbody').append(str);
	page();
}
function whenError(res)
{
	alert("Error!!");
	console.log("Error by : "+ res);
}
	
function access(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	console.log(innerDoc.body)
}

function ChecklistInsert(){
	var Name = document.getElementById("result_nm").value;
	var loc = "ManageCheckListItem_Insert.do";
	location.href=loc;
}
function ChecklistExcel() {
	//var Check	= document.SelectCode.searchCondition1.value;
	location.href="Checklist_Insert_Excel.do";
}

function check() {
	var file = $("#excelFile").val();
	if (file == "" || file == null) {
		alert("파일을 선택해주세요.");
		return false;
	} else if (!checkFileType(file)) {
		alert("xls 또는 xlsx 파일만 업로드 가능합니다.");
		return false;
	}
	document.excelUploadForm.submit();
}
function fn_checkApp_page(pageNo){
	alert(pageNo);

//	var Name = document.getElementById("result_cd").value;
//	var code = new Object();
//	
//	if(Name == "")
//		{
//			alert("검색어를 입력하여 주십시오");
//			return false;
//		}
//	
//	code.name = Name;
//	code.currentPageNo = pageNo;
//	
//	var jsonString = JSON.stringify(code);
//	
//	$.ajax({
//		url:"ManageCheckListItem_AJAX_JSON.do",
//		type:"post",
//		data:{"jsonString":jsonString},
//		success:whenSuccess,
//		error:whenError
//	})

//	document.checkAppForm.currentPageNo.value = pageNo;
//	document.checkAppForm.action = "CheckApproval.do";
//	document.checkAppForm.submit();
}
function AssignTask_inputParameter(){
	
}
