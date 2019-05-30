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
})

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Task_Insert()
{
	var Name = document.SelectCode.searchCondition1.value
	var Date = $("#TaskDatepicker").val();
	
	if(Date == "")
		{
			alert("일시를 입력해 주십시오");
				return false;
		}
	if (Name=="")
		{
		 Name =  document.TaskSearch.searchKeyword.value;
		 	if(Name == "")
		 		{
		 			alert("부대활동을 검색해 주십시오");
		 			return false;
		 		}
		}
	
	document.write("");
	location.href ="AssignTask_insert.do?Name="+Name+"&Date="+Date;
}

function Task_Search()
{
	var Name = document.SelectCode.searchCondition1.value
	var Date = $("#TaskDatepicker").val();
	var Code = document.TaskSearch.searchCode.value;
	
	if(Date == "")
	{
		alert("일시를 선택해 주십시오");
		return false;
	}
	if(Name == "")
	{
		alert("부대활동을 검색해 주십시오");
		return false;
	}
	else{
	document.write("");
	location.href ="AssignTask.do?Name="+Name+"&Date="+Date;
	}
	
}
function access(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	console.log(innerDoc.body)
}

function clickTdEvent(tdObj){
	
	var Name = tdObj.innerText;
	var Code = tdObj.id;

	document.SelectCode.searchCondition1.value=Name;
	document.TaskSearch.searchCode.value=Code;	
}