$(document).ready(function(){
	var Date = $("#TaskDatepicker").val();
	
	var Disabledsolve;
	var TaskCode;
		
	if(Disabledsolve == undefined){
		Disabledsolve = "";
	}
	
	$("#TaskDatepicker").datepicker({
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
		yearSuffix:'년'
	});

});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function Task_Search(){
	var Name = document.SelectCode.searchCondition1.value;
	var Date = document.taskdate.searchCondition2.value;
	
	if(Date == "")
		{
			alert("일시를 선택해 주십시오");
		}
	else{
	document.write("");
	location.href ="EnterCommanderGuide.do?Name="+Name+"&Date="+Date;
	}
}
function Task_Input_cui1(){
	var CUI1 = document.guidnc_name.CUI1.value;
	var Name = getParameterByName("Name");
	var Date = getParameterByName("Date");
	var id = document.guidnc_name.taskDataID.value;
	
	if(Date == "")
		{
		 Date = document.taskdate.searchCondition2.value;
			if(Date == "")
			{
				alert("일시를 선택해 주십시오");
			}
		}
	
	document.write("");
	location.href="EnterCommanderGuide_Modify1.do?Name="+Name+"&Date="+Date + "&guidnc_1=" + CUI1 + "&id=" + id;
}
function Task_Input_cui2(){
	var CUI2 = document.guidnc_name.CUI2.value;
	var Name = getParameterByName("Name");
	var Date = getParameterByName("Date");
	var id = document.guidnc_name.taskDataID.value;
	
	if(Date == "")
	{
	 Date = document.taskdate.searchCondition2.value;
		if(Date == "")
		{
			alert("일시를 선택해 주십시오");
		}
	}
	document.write("");
	location.href="EnterCommanderGuide_Modify2.do?Name="+Name+"&Date="+Date + "&guidnc_2=" + CUI2 + "&id=" + id;
}
function Task_Input_cui3(){
	var CUI3 = document.guidnc_name.CUI3.value;
	var Name = getParameterByName("Name");
	var Date = getParameterByName("Date");
	var id = document.guidnc_name.taskDataID.value;
	
	if(Date == "")
	{
	 Date = document.taskdate.searchCondition2.value;
		if(Date == "")
		{
			alert("일시를 선택해 주십시오");
		}
	}
	document.write("");
	location.href="EnterCommanderGuide_Modify3.do?Name="+Name+"&Date="+Date + "&guidnc_3=" + CUI3 + "&id=" + id;
}
function Guide_Delete1(){
	var Name = getParameterByName("Name");
	var Date = getParameterByName("Date");
	var id = document.guidnc_name.taskDataID.value;
	
	if(Date == "")
	{
	 Date = document.taskdate.searchCondition2.value;
		if(Date == "")
		{
			alert("일시를 선택해 주십시오");
		}
	}
var DeleteConfirm = confirm('정말 삭제하시겠습니까?')
	
	if(DeleteConfirm)
		{
			document.write("");
			location.href="EnderCommanderGuide_Delete1.do?Name="+Name+"&Date="+Date + "&guidnc_1=CUI1" + "&id=" + id;
		}
	else{
		alert("취소하셨습니다.");
	}
}
function Guide_Delete2(){
	var Name = getParameterByName("Name");
	var Date = getParameterByName("Date");
	var id = document.guidnc_name.taskDataID.value;
	
	if(Date == "")
	{
	 Date = document.taskdate.searchCondition2.value;
		if(Date == "")
		{
			alert("일시를 선택해 주십시오");
		}
	}
var DeleteConfirm = confirm('정말 삭제하시겠습니까?')
	
	if(DeleteConfirm)
		{
	document.write("");
	location.href="EnderCommanderGuide_Delete2.do?Name="+Name+"&Date="+Date + "&guidnc_2=CUI2" + "&id=" + id;
		}
	else{
		alert("취소하셨습니다.");
	}
}
function Guide_Delete3(){
	var Name = getParameterByName("Name");
	var Date = getParameterByName("Date");
	var id = document.guidnc_name.taskDataID.value;
	
	if(Date == "")
	{
	 Date = document.taskdate.searchCondition2.value;
		if(Date == "")
		{
			alert("일시를 선택해 주십시오");
		}
	}
var DeleteConfirm = confirm('정말 삭제하시겠습니까?')
	
	if(DeleteConfirm)
		{
	document.write("");
	location.href="EnderCommanderGuide_Delete3.do?Name="+Name+"&Date="+Date + "&guidnc_3=CUI3" + "&id=" + id;
}
else{
	alert("취소하셨습니다.");
}
}

function Task_Modify_cui1(){
	var button_modify1 = document.getElementById("Modify1");
//	 $("#Modify1").attr("disabled",false);
	button_modify1.disabled = false;
	
}
function clickTdEvent(tdObj){
	
	var Name = tdObj.innerText;
	var Code = tdObj.id;

	document.SelectCode.searchCondition1.value=Name;
	document.TaskSearch.searchCode.value=Code;	
}

function access(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	console.log(innerDoc.body)
}


function Guide_Insert() {
	var CUI1 = document.guidnc_name.CUI1.value;
	var CUI2 = document.guidnc_name.CUI2.value;
	var CUI3 = document.guidnc_name.CUI3.value;
	var Name = getParameterByName("Name");
	var Date = getParameterByName("Date");
	var id = document.guidnc_name.taskDataID.value;
	
	location.href = "EnderCommanderGuide_InsertAll.do?Name=" + Name + "&Date="+Date + "&guidnc_1=" + CUI1 + "&guidnc_2=" + CUI2 + "&guidnc_3=" + CUI3 + "&id=" + id;
}
