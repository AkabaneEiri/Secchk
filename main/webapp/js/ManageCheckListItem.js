
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

function Task_Insert()
{
	var Name = document.SelectCode.searchCondition1.value
	
	if (Name=="")
		{
		 Name =  document.TaskSearch.searchKeyword.value;
		}
	
	document.write("");
	location.href ="AssignTask_insert.do?Name="+Name+"&Date="+Date;
}

function Task_Search()
{
	var Name = document.SelectCode.searchCondition1.value;

	document.write("");
	location.href ="ManageCheckListItem.do?ctlg_cd="+Name;
	
}
	
function access(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	console.log(innerDoc.body)
}

function ChecklistInsert(){
	var Name = document.SelectCode.searchCondition1.value;
	var loc = "ManageCheckListItem_Insert.do?ctlg_cd="+Name;
	document.write("");
	location.href=loc;
}
function ChecklistExcel() {
	var Check	= document.SelectCode.searchCondition1.value;
	location.href="Checklist_Insert_Excel.do?Check="+Check;
}

function clickTdEvent(tdObj){
	
	var Name = tdObj.innerText;
	var Code = tdObj.id;

	document.SelectCode.searchCondition1.value=Name;
	document.TaskSearch.searchCode.value=Code;	
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