
$(document).ready(function(){
		$("#tablesort").dataTable();
	});
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function makeSelectValue() {
	
	var Name = document.SelectCode.searchCondition1.value;
	
	if (Name=="")
	{
	 Name =  document.TaskSearch.searchCondition1.value;
	}
	var SptlyList = document.getElementById("Task_name");
	var seq = target.options[target.selectedIndex].value;
	var loc ="SelectCheckListResult.do?ctlg_cd="+Name+"&seq="+seq+"&code="
	var value="";
	var actionForm = document.rep;
	var selectSize = document.rep.category_selected.length;
	if(selectSize > 10)
	{
		alert("체크리스트 항목은 10개 이상 선택하실 수 없습니다.");
		return;
	}
	for(i = 0; i < selectSize; i++) {
	var newElement = document.createElement("INPUT");
	actionForm.insertAdjacentElement("afterBegin", newElement);
	newElement.type = "hidden";
	newElement.name = "memovalue";
	newElement.value = document.rep.category_selected.options[i].value;
	value= value +"/"+ newElement.value;
	
	}
	loc = loc + value;
	console.log(loc);
	location.href=loc;
}


function Checklist_Insert()
{
	var Name = document.SelectCode.searchCondition1.value;
	
	if (Name=="")
	{
	 Name =  document.TaskSearch.searchCondition1.value;
	}
	document.write("");
	location.href ="SelectCheckListItem.do?ctlg_cd="+Name+"&Code="+Code;
}

function SelectReset()
{
	var Name = document.SelectCode.searchCondition1.value;
//	var Task = document.rep.PreSetNew.value;
	var Actvt = document.SelectCode.searchCondition1.value;
	var val = document.getElementById("Task_name");
	var seq;
	var actionForm = document.rep;
	var selectSize = document.rep.category_selected.length;
	var value ="";
	var Task_seq;
	var Task_nm;
	
	for(i=0;i<val.options.length;i++){
		if(val.options[i].selected == true)
			{
			Task_seq = val.options[i].value;
			Task_nm = val.options[i].text;
				break;
			}
	}
	
	var loc = "SelectCheckListPreSetCreate.do?ctlg_cd="+Name+"&Task_seq="+Task_seq+"&Task_nm="+Task_nm+"&task="+Actvt+"&code="
	loc = loc + value+"&Reset=Y";
	console.log(loc);
	location.href=loc;
}
function MakeSelectPre()
{
	var Name = document.SelectCode.searchCondition1.value;
//	var Task = document.rep.PreSetNew.value;
	var Actvt = document.SelectCode.searchCondition1.value;
	var val = document.getElementById("Task_name");
	var seq;
	var actionForm = document.rep;
	var selectSize = document.rep.category_selected.length;
	var value ="";
	var Task_seq;
	var Task_nm;
	
	for(i=0;i<val.options.length;i++){
		if(val.options[i].selected == true)
			{
			Task_seq = val.options[i].value;
			Task_nm = val.options[i].text;
				break;
			}
	}
	
	var loc = "SelectCheckListPreSetCreate.do?ctlg_cd="+Name+"&Task_seq="+Task_seq+"&Task_nm="+Task_nm+"&task="+Actvt+"&code="
	if(selectSize > 10)
	{
		alert("체크리스트 항목은 10개 이상 선택하실 수 없습니다.");
		return;
	}
	

	
	for(i = 0; i < selectSize; i++) {
	var newElement = document.createElement("INPUT");
	actionForm.insertAdjacentElement("afterBegin", newElement);
	newElement.type = "hidden";
	newElement.name = "memovalue";
	newElement.value = document.rep.category_selected.options[i].value;
	value= value +"/"+ newElement.value;
	}
	loc = loc + value;
	console.log(loc);
	location.href=loc;
	}

function Task_Search()
{
	var Name = document.SelectCode.searchCondition1.value;

	document.write("");
	location.href ="SelectCheckListItem.do?ctlg_cd="+Name;
	
}
function access(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
}

function clickTdEvent(tdObj){
	
	var Name = tdObj.innerText;

	document.SelectCode.searchCondition1.value=Name;
}

function ClearList(OptionList, TitleName) {
	OptionList.length = 0;
}
	
function move(side){   
	var temp1 = new Array();
	var temp2 = new Array();
	var tempa = new Array();
	var tempb = new Array();
	var current1 = 0;
	var current2 = 0;
	var y=0;
	var attribute;
	
	if (side == "right")	{  
		attribute1 = document.rep.category_name; 
		attribute2 = document.rep.category_selected;
	}
	else	{  
		attribute1 = document.rep.category_selected;
		attribute2 = document.rep.category_name;  
	}

	for (var i = 0; i < attribute2.length; i++)	{  
		y=current1++
		temp1[y] = attribute2.options[i].value;
		tempa[y] = attribute2.options[i].text;
	}

	for (var i = 0; i < attribute1.length; i++)	{   
		if ( attribute1.options[i].selected )		{  
			y=current1++
			temp1[y] = attribute1.options[i].value;
			tempa[y] = attribute1.options[i].text;
		}
		else		{  
			y=current2++
			temp2[y] = attribute1.options[i].value; 
			tempb[y] = attribute1.options[i].text;
		}
	}

	for (var i = 0; i < temp1.length; i++)	{  
		attribute2.options[i] = new Option();
		attribute2.options[i].value = temp1[i];
		attribute2.options[i].text =  tempa[i];
	}

	ClearList(attribute1,attribute1);
	if (temp2.length>0)	{	
		for (var i = 0; i < temp2.length; i++)
		{   
			attribute1.options[i] = new Option();
			attribute1.options[i].value = temp2[i];
			attribute1.options[i].text =  tempb[i];
		}
	}
}
function test(obj){
	var code = new Object();
	var seq = obj.value;
	var Task = document.SelectCode.searchCondition1.value;
	code.seq = seq;
	
	var jsonString = JSON.stringify(code);
	
	 $.ajax({
		url:"SelectChecklistItem_ajax.do?code="+seq+"&Task="+Task,
		type:"post",
		data:{"jsonString":jsonString},
		success:whenSuccessChange,
		error:whenError
	})
	
}
function whenSuccessChange(res)
{
	console.log(res);
	var obj = JSON.parse(res)
	var OptionBox = document.getElementById("category_selected");
	for(var i = OptionBox.length; i>=0;i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
			var objOption 	= document.createElement("option");
			if(obj[i].ctlg_itm_ctnt.length > 25)
				{
				obj[i].ctlg_itm_ctnt = obj[i].ctlg_itm_ctnt.substring(0,25) + "...";
				}
			var frqc = obj[i].prtcuse_frqc;
			if (frqc.length == 1)
				{
					frqc = "\u00A0"+ frqc;
				}
			objOption.text 	= 	"\u00A0"+"\u00A0"+ "\u00A0"+
								frqc +
								"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+
								"\u00A0"+
								obj[i].stdd_yn + 
								"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+
								"\u00A0"+"\u00A0"+"\u00A0"+
								obj[i].ctlg_itm_ctnt;
			objOption.value = 	obj[i].ctlg_itm_cd;
			objOption.id 	= 	obj[i].ctlg_itm_cd;
			
			OptionBox.options.add(objOption);
		}

}
function whenError(res)
{
	alert("Error!!");
}
