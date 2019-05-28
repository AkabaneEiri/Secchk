function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function clickTdEvent(tdObj){
	
	var Code = tdObj.id;
	var srvno = tdObj.attributes.value.value;

	document.TaskCharge.AssignCharge.value=Code;
	document.TaskCharge.Chargesrvno.value=srvno;
}
function clickTrEvent(tdObj){
	
	var Code = tdObj.id;
	var Name = tdObj.innerText;

	document.TaskName.TaskInsert_Task.value=Name;
	document.TaskName.TaskInsert_Seq.value=Code;
}
function Insert_Submit(){
	var Task;
	var Charge;
	var Chargesrvno;
	var TaskName;
	var TaskDate;
	var TaskSeq = document.TaskName.TaskInsert_Seq.value;
	TaskName = getParameterByName('Name');
	
	TaskDate = getParameterByName('Date');
	
	Task = document.TaskName.TaskInsert_Task.value;
	
	Charge = document.TaskCharge.AssignCharge.value;
	Chargesrvno = document.TaskCharge.Chargesrvno.value
	
	if(Task == "")
		{
			alert("과업을 입력하세요");
			return false;
		}
	if(Charge == "" || Chargesrvno == "")
		{
			alert("담당자를 선택해주세요");
			return false;
		}
	document.write("");
	location.href ="Task_Insert_Result.do?INCDT_ACTVT_TYPE_CD="+TaskName+ "&ACTVT_DATE="+TaskDate+"&Task="+Task+"&TASK_PSNCHNRG_SRVNO="+Chargesrvno+"&taskSeq="+TaskSeq;
	
	
}
function Insert_Cancle(){
	Name = getParameterByName('Name');
	
	Date = getParameterByName('Date');
	document.write("");
	location.href ="AssignTask.do?Name="+Name+"&Date="+Date;
}
function access(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	console.log(innerDoc.body)
}
function access_Task(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	var task =  getParameterByName('Name');
	console.log(innerDoc.body)
}
