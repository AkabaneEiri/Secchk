$(document).ready(function(){
	var Date = $("#TaskDatepicker").val();
	
	var TaskCode;
	
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
		yearSuffix:'년',
		showOn: "button",
		buttonText: "<i class='far fa-calendar-alt'></i>"
	});
	Submit();
})

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Task_Insert()
{
	var Name = document.getElementById("result_nm").value;
	var Date = $("#TaskDatepicker").val();
	
	var Large 		= $("#searchConditionLage option:selected").val();	// 대분류 코드
	var Middle 		= $("#searchConditionMiddle option:selected").val();	// 중분류 값
	var Small	 	= $("#Task_name option:selected").val();				// 세부활동 값 
	
	location.href ="AssignTask_insert.do?Name="+encodeURI(Name)+"&Date="+encodeURI(Date)+"&Large="+encodeURI(Large)+"&Middle="+encodeURI(Middle)+"&Small="+encodeURI(Small);
}

function Submit_parent(){
	
	var Name = document.getElementById("result_nm").value;
	var Date = $("#TaskDatepicker").val();
	var Code = document.getElementById("result_cd").value;
	var lrgcls = document.getElementById("result_lrgcls").value;
	var mdcls = document.getElementById("result_mccls").value;
	
	if(Date == "")
	{
		alert("일시를 선택해 주십시오");
		return false;
	}
	else{
		var task = new Object();
		
		task.Name = Name;
		task.Date = Date;
		task.Code = Code;
		task.lrgcls = lrgcls;
		task.mdcls = mdcls;
		
		var jsonString = JSON.stringify(task);
		console.log(jsonString);
		
		$.ajax({
			url:'./AssignTask_Ajax.do',
			type:'post',
			data:{"jsonString":jsonString},
			success: whenSuccessTask,
			error:function(err){
			alert("일시적인 오류가 발생하였습니다.");
			}
		});
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
	var Date = $("#TaskDatepicker").val();
	
	document.SelectCode.searchCondition1.value=Name;
	document.TaskSearch.searchCode.value=Code;	
	
	var task = new Object();
	
	task.Name = Name;
	task.Date = Date;
	
	var jsonString = JSON.stringify(task);
	console.log(jsonString);
	
	$.ajax({
		url:'./AssignTask_Ajax.do?Name='+encodeURI(Name)+"&Date="+encodeURI(Date),
		type:'post',
		data:{"jsonString":jsonString},
		success: whenSuccessTask,
		error:function(err){
			alert("일시적인 오류가 발생하였습니다.");
		}
	});
	
	
}

function whenSuccessTask(result){
	console.log("Success! : " + result);
	
	jQuery("#Table_Task>tbody>tr").remove();
	var obj = JSON.parse(result);
	var str = '<tr>';
	if(obj[0].incdt_actvt_type_cd == 'E001'){
			str +='<td colspan = 6> 데이터가 없습니다 </td>';
			str += '</tr>';
	}
	else
		{
	jQuery.each(obj, function(i){
		str +='<td><input type="checkbox" name="check[]" id="check" value='+obj[i].seq+'></td>';
		str +='<td>'+obj[i].lrgcls_nm;
		str +='</td> <td>'+obj[i].mdcls_nm;
		str +='</td> <td>'+obj[i].incdt_actvt_type_cd;
		str +='</td> <td id="TaskName" name="TaskName">'+obj[i].task;
		str +='</td> <td>'+obj[i].task_psnchnrg_srvno+'</td>';
		str += '</tr>';
	})
		}
	console.log(str);
	jQuery("#Table_Task").append(str);
	page();
	jQuery("#Table_Task").addClass('table-striped sub_table table01');
	
}
function AssignTask_inputParameter(){
	var Large 	= getParameterByName('Large');
	
	
	if(Large != "")
	{
		$("#searchConditionLage").val(Large).attr("selected","true");
		LargeChange();
	}
}
function AssignTask_inputParameterMiddle(){
	var Middle 	= getParameterByName('Middle');
	var Small 	= getParameterByName('Small');
	var Back	= getParameterByName('back');
	
	if(Middle 	!= "")
		{
		$("#searchConditionMiddle").val(Middle).attr("selected","true");
		}
	if(Small != "")
		{
		$("#Task_name").val(Small).attr("selected","true");
		}
	if(Small != "")
		{
		Submit();
		}
//	
}
function Task_Delete(){
	var check = document.getElementsByName("check[]");
	var Code = new Array();
	
		for(var i=0;i<check.length;i++){
			if(check[i].checked == true)
				{
				var Arr = new Object();
				Arr["seq"] = check[i].value;
				Code["seq"] = Arr[i];
				

				var jsonString = JSON.stringify(Arr);
				$.ajax({
				 	url: "AssignTask_Delete.do",											//request보낼 경로
				 	type: "post",																//메소드(post로 적용)
				 	data: {"jsonString":jsonString}, 											//보낼 데이터
				 	success : whenSuccessDelete,													//성공 시 값 보낼 펑션
				 	error: whenErrorDelete
				});
				
				}
			
		}
}
function whenSuccessDelete(){
	Submit();
}
function whenErrorDelete(){
	
}