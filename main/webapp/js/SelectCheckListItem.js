$(document).ready(function(){
		$("#tablesort").dataTable();
		var Taskparam = getParameterByName("task");
		var TaskList = document.getElementById("Task_name");

		if(Taskparam != "")
			{
				var code = new Object();
				var seq = Taskparam;
				var Task = document.getElementById("result_nm").value;
				code.seq = seq;
				
				var jsonString = JSON.stringify(code);
				
				 $.ajax({
					url:"SelectChecklistItem_ajax.do?code="+encodeURI(seq)+"&Task="+encodeURI(Task),
					type:"post",
					data:{"jsonString":jsonString},
					success:whenSuccessChange,
					error:whenError
				});
			for(var i=0;i<TaskList.length;i++)
				{
					if(TaskList.options[i].value!='')
						{
							if(TaskList.options[i].value.trim()==Taskparam.trim())
							{
								TaskList.options[i].selected = true;
							}
						}
					else
					{
					if(TaskList.options[i].value.trim()==Taskparam.trim()){
						TaskList.options[i].selected = true;
					}
				}
				}
			}
		
	});
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function SelectReset()
{
	
	// define various
	var code 				= new Object();
	
	var TaskSmall 			= document.getElementById("Task_name");
	var TaskSmallSelected 	= TaskSmall.options[TaskSmall.selectedIndex].value;
	var PreSetid 			= document.getElementById("Task_name_List");
	var PreSetSelected		= PreSetid.options[PreSetid.selectedIndex].value;
	var Task				= PreSetid.options[PreSetid.selectedIndex].text;

	//make various to Object
	code.TaskSmall 			= TaskSmallSelected;
	code.TaskPreSet			= PreSetSelected;
	code.Task				= Task;
	code.reset				= "Y";
	
	// make Code to JSON String
	var jsonString 			= JSON.stringify(code);
	
	//Use Ajax Connection
	$.ajax({
		url 	: "SelectCheckList_Ajax_JSON.do",
		tpye	: "post",
		data	: {"jsonString":jsonString},
		success	:whenSuccessrestore,
		error 	: whenError
	})
	
}
function MakeSelectPre()
{
	// define various
	var code 				= new Object();
	
	var TaskSmall 			= document.getElementById("Task_name");
	var TaskSmallSelected 	= TaskSmall.options[TaskSmall.selectedIndex].value;
	var PreSetid 			= document.getElementById("Task_name_List");
	var PreSetSelected		= PreSetid.options[PreSetid.selectedIndex].value;
	var Task				= PreSetid.options[PreSetid.selectedIndex].text;
	var CtlgList			= document.getElementById("category_selected");
	var Ctlg_size			= CtlgList.length; 
	var Ctlg				= "";
	
	// check Ctlg_Size
	if(Ctlg_size > 10)
		{
			alert("체크리스트 항목은 10개 이상 선택하실 수 없습니다.");
			return false;
		}
	// make SelectBox to String List
	for(i = 0; i < Ctlg_size; i++) {
		var newElement;
		 newElement = CtlgList.options[i].value.trim();
		Ctlg= Ctlg +"/"+ newElement;
		}
	
	//put Various to Object
	code.TaskSmall 		= TaskSmallSelected;
	code.TaskPreSet 	= PreSetSelected;
	code.task 			= Task;
	code.CltgList 		= Ctlg;
	code.reset			= "N";
	
	//make code to JSON String
	var jsonString			= JSON.stringify(code);
	
	$.ajax({
		url 	: "SelectCheckList_Ajax_JSON.do",
		tpye	: "post",
		data	: {"jsonString":jsonString},
		success	:whenSuccesssubmit,
		error 	: whenError
	})
	
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
function selectPre(obj){
	var code = new Object();
	var seq = obj.value;
	if(seq != "")
		{
			var Task = document.getElementById("result_cd").value;
			code.seq = seq;
			
			var jsonString = JSON.stringify(code);
			if(seq != 'E001')
				{
					 $.ajax({
						url:"SelectChecklistItem_ajax.do?code="+encodeURI(seq)+"&Task="+encodeURI(Task),
						type:"post",
						data:{"jsonString":jsonString},
						success:whenSuccessChange,
						error:whenError
					});
				}
		}
}
function whenSuccessChange(res)
{
//	console.log(res);					// 콘솔 통신 확인을 위한 구문
	var obj = JSON.parse(res)			// Parse JSON Data
	
	var OptionBox = document.getElementById("category_selected"); //get Selected OptionBox
	var OptionBox_All = document.getElementById("categofy_name"); //get All Checklist's OptionBox
	
	
	//Restore OptionBox
	for(var i = OptionBox.length; i>=0;i--)
		{
			OptionBox.options[i] = null;
		}
	
	//Input New OptionBox
	for(var i=0;i<obj.Selected.length;i++)
		{
			var objOption 	= document.createElement("option");
			if(obj.Selected[i].ctlg_itm_ctnt.length > 25)
				{
				obj.Selected[i].ctlg_itm_ctnt = obj.Selected[i].ctlg_itm_ctnt.substring(0,25) + "...";
				}
			var frqc = obj.Selected[i].prtcuse_frqc;
			if (frqc.length == 1)
				{
					frqc = "\u00A0"+ frqc;
				}
			objOption.text 	= 	"\u00A0"+"\u00A0"+ "\u00A0"+
								frqc +
								"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+
								"\u00A0"+
								obj.Selected[i].stdd_yn + 
								"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+
								"\u00A0"+"\u00A0"+"\u00A0"+
								obj.Selected[i].ctlg_itm_ctnt;
			objOption.value = 	obj.Selected[i].ctlg_itm_cd;
			objOption.id 	= 	obj.Selected[i].ctlg_itm_cd;
			
			OptionBox.options.add(objOption);
		}
	
	//Restore All Option Box
	for(var i = OptionBox_All.length; i>=0;i--)
	{
		OptionBox_All.options[i] = null;
	}
	//input All Option Box
	for(var i=0;i<obj.Preset.length;i++)
		{
			var checkNew = 0;

			for(var v=0;v<obj.Selected.length;v++)
				{
					if(OptionBox.options[v].id == obj.Preset[i].ctlg_itm_cd)
						{
							checkNew++;
						}
				}
			if(checkNew == 0)
				{
					var objOption 	= document.createElement("option");
					
					if(obj.Preset[i].ctlg_itm_ctnt.length > 25)
						{
						obj.Preset[i].ctlg_itm_ctnt = obj.Preset[i].ctlg_itm_ctnt.substring(0,25) + "...";
						}
					var frqc = obj.Preset[i].prtcuse_frqc;
					if (frqc.length == 1)
						{
							frqc = "\u00A0"+ frqc;
						}
					objOption.text 	= 	"\u00A0"+"\u00A0"+ "\u00A0"+
										frqc +
										"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+
										"\u00A0"+
										obj.Preset[i].stdd_yn + 
										"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+
										"\u00A0"+"\u00A0"+"\u00A0"+
										obj.Preset[i].ctlg_itm_ctnt;
					objOption.value = 	obj.Preset[i].ctlg_itm_cd;
					objOption.id 	= 	obj.Preset[i].ctlg_itm_cd;
					
					OptionBox_All.options.add(objOption);
				}
		}
		
	
}
function whenSuccessrestore()
{
	var OptionBox = document.getElementById("category_selected"); //get Selected OptionBox
	var length = OptionBox.options.length;
	
	attribute1 = document.rep.category_selected;
	attribute2 = document.rep.category_name;
	//Restore OptionBox

	for(var i=0; i<length; i++)
		{
		var objOption = document.createElement("option");
		objOption.text 	= attribute1[i].text;
		objOption.value = attribute1[i].value;
		objOption.id 	= attribute1[i].id;
		
//		attribute2.options.add(objOption);
		$('#category_name').prepend(objOption);
		}
	
	for(var i = OptionBox.length; i>=0;i--)
	{
		OptionBox.options[i] = null;
	}
	
	alert("초기화 되었습니다.");
	}

function whenSuccesssubmit()
{
	alert("적용되었습니다.");
}
function whenError(res)
{
	alert("Error!!");
}

function Submit_parent(){
	var Name = document.getElementById("result_nm").value;
	var Code = document.getElementById("result_cd").value;
	var obj = new Object();
	
	obj.Code = Code;
	
	var jsonString = JSON.stringify(obj);
	
	$.ajax({
		url		:"SelectChecklistItem_AJAX_JSON.do",
		type	:"post",
		data	:{"jsonString":jsonString},
		success	:whenSuccessItemSearch,
		error	:whenError
		
	})
	
}
function whenSuccessItemSearch(res)
{
	var obj				 	= JSON.parse(res);
	var OptionBox 			= document.getElementById("Task_name_List");
	var OptionBox_All 		= document.getElementById("categofy_name");
	var OptinoBox_Selected 	= document.getElementById("category_selected"); 
	
	for (var i = OptionBox_All.length; i>=0;i--)
		{
			OptionBox_All.options[i] = null;
		}
	for (var i = OptinoBox_Selected.length; i>=0;i--)
	{
		OptinoBox_Selected.options[i] = null;
	}
	for (var i =OptionBox.length; i>=0; i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
			var objOption = document.createElement("option");
			objOption.text = obj[i].cd_nm;
			objOption.value = obj[i].cd;
			objOption.id = obj[i].cd;
			
			OptionBox.options.add(objOption);
		}
	}

function AssignTask_inputParameter()
{
	}
function AssignTask_inputParameterMiddle()
{
	}