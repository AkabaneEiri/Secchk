$(document).ready(function(){
	var Date = $("#TaskDatepicker").val();					// DatePicker의 값 적용하는 부문
	
	var TaskCode;
	
	$("#TaskDatepicker").datepicker({						// DatePicker Design
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
	
	DateParam = document.getElementById("TaskDatepicker");
	if(getParameterByName("Date") != "")
		{
			DateParam.value = getParameterByName("Date");
		}
	$("#TaskDatepicker").bind("propertychange change keyup paste input", checkVal);
})
function checkVal(){
	var nowDate = document.getElementById("TaskDatepicker").value;
	var DateArray = nowDate.split('-');
	var nowYear = DateArray[0];
	var nowMonth = DateArray[1];
	var nowDay = DateArray[2];
	
	var dateNowDate = new Date();
	dateNowDate.setFullYear(nowYear, nowMonth - 1, nowDay);
	
	var today = new Date();
	
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	var todaystring = yyyy+'-'+mm+'-'+dd;

	if(dateNowDate.getTime() < today.getTime())
		{
			alert("오늘 이후의 날짜를 등록하여 주십시오")
			document.getElementById("TaskDatepicker").value = todaystring;
		}
}
function getParameterByName(name) {						// Parameter Get 방식 처리
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function clickTdEvent(tdObj){				// 테이블 td 클릭하였을 때에 정보 처리
	
	var Code = tdObj.id;
	var srvno = tdObj.attributes.value.value;

	document.TaskCharge.AssignCharge.value=Code;
	document.TaskCharge.Chargesrvno.value=srvno;
}

function clickTrEvent(tdObj){				// 테이블 tr 눌렀을 때에 정보 처리
	
	var Code = tdObj.id;
	var Name = tdObj.innerText;

//	document.TaskName.TaskInsert_Task.value=Name;
	document.TaskName.TaskInsert_Seq.value=Code;
}

function Insert_Submit(){					// 정보 입력 후 제출하기 위한 시퀀스
	var Task;
	var Charge;
	var Chargesrvno;
	var TaskName;
	var TaskDate;
	var TaskSeq = document.TaskName.TaskInsert_Seq.value;
	
	TaskName = document.getElementById('result_cd').value;
	
	TaskDate = document.getElementById('TaskDatepicker').value;
	
	Task = document.TaskName.TaskInsert_Task.value;
	
	Charge = document.TaskCharge.AssignCharge.value;
	Chargesrvno = document.TaskCharge.Chargesrvno.value
	
	// 입력값이 없으면 Alert창 띄우고 다시 Return
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
	
	var Code = new Object();
	Code.Task = Task;
	Code.Chargesrvno = Chargesrvno;
	Code.TaskName = TaskName;
	Code.TaskDate = TaskDate;
	Code.TaskSeq = TaskSeq;
	
	var jsonString = JSON.stringify(Code);
	//입력값 받으면 Ajax 통신으로 해당 값이 중복인지 검사
	$.ajax({
			url:"TaskInsert_already_Check_Ajax_json.do",
			type:"post",
			data:{"jsonString":jsonString},
			success:whenSuccess,
			error:whenError
	})
	
//	location.href ="Task_Insert_Result.do?INCDT_ACTVT_TYPE_CD="+encodeURI(TaskName)+ "&ACTVT_DATE="+encodeURI(TaskDate)+"&Task="+encodeURI(Task)+"&TASK_PSNCHNRG_SRVNO="+(Chargesrvno)+"&taskSeq="+encodeURI(TaskSeq);
	
	
}
//과업 세부사항 Modal Open 시 입력된 ctlg_cd를 읽게 하는 구문
function open_small(){
	var ctlg_cd = document.getElementById("result_cd").value;
	var task =  document.getElementById("result_nm");
	var iframe = document.getElementById("iframe_small");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;

	iframe.contentWindow.readthis(ctlg_cd);
}
	// 입력 취소 시 정보 처리
function Insert_Cancle(){
	var Name 	= getParameterByName('Name');
	
	var Date 	= getParameterByName('Date');
	
	var Large 	= getParameterByName('Large');
	var Middle 	= getParameterByName('Middle');
	var Small 	= getParameterByName('Small');
	
	location.href ="AssignTask.do?Name="+encodeURI(Name)+"&Date="+encodeURI(Date)+"&Large="+encodeURI(Large)+"&Middle="+encodeURI(Middle)+"&Small="+encodeURI(Small);
}
// iframe을 물러왔을 때 콘솔에 로그 남기는 작업
function access(){
	var iframe = document.getElementById("iframe_small");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	console.log(innerDoc.body)
}
//iframe을 물러왔을 때 콘솔에 로그 남기는 작업
function access_Task(){
	var iframe = document.getElementById("iframe_small");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	var task =  document.getElementById("result_nm");
}
//tasksearch에서 받은 데이터 처리
function Submit_parent(){
	var ctlg_cd = document.getElementById("result_cd").value;
	var task =  document.getElementById("result_nm");
	var iframe = document.getElementById("iframe_small");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	
	if(ctlg_cd != "")
		{
		iframe.contentWindow.readthis(ctlg_cd);
		$("#btn_tasksmall").attr('data-toggle',"modal");
		$("#btn_tasksmall").attr('data-target',"#TaskSearch2");
		}
	else
		{
			alert("활동 유형을 선택해 주십시오");
		}
}
// 모달 창 닫거나 확인 시, 기존 검색 창 날려주는 작업
function Preset_Refresh(){
	iframe_small.location.reload();
}
// 확인 버튼 눌렀을 때 Function
function Preset_Select(){
	
	var iframe = document.getElementById("iframe_small");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	var NameTest= innerDoc.getElementById("searchKeyword");
	var Name = innerDoc.getElementById("searchKeyword").value;
	
	
	// AssignTask_insert에 값 넣어주고
	document.TaskName.TaskInsert_Task.value=Name;

	// Refresh
	Preset_Refresh();
	Submit_parent();
}
function Member_Select(res){
	var Code = res.id;
	var srvno = res.attributes.value.value;
	
	document.getElementById("Chargesrvno").value = srvno;
}
// 사용자 선택 버튼 누르고 모달창 초기화하는 Function
function User_Refresh(){
	document.getElementById("iframe_user").contentWindow.location.reload();
}
// 사용자 선택버튼 눌렀을때의 Function
function User_select(){
	
	var iframe = document.getElementById("iframe_user");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	var Name = innerDoc.getElementById("searchKeyword").value;
	
	document.getElementById("AssignCharge").value=Name;

	User_Refresh();
}
//과업 입력 버튼 누른 뒤, 중복작업 었었을때의 Function
function whenSuccess(res){
	var Task;
	var Charge;
	var Chargesrvno;
	var TaskName;
	var TaskDate;
	var TaskSeq = document.TaskName.TaskInsert_Seq.value;
	var Large 		= $("#searchConditionLage option:selected").val();	// 대분류 코드
	var Middle 		= $("#searchConditionMiddle option:selected").val();	// 중분류 값
	var Small	 	= $("#Task_name option:selected").val();				// 세부활동 값 
	
	TaskName = document.getElementById('result_cd').value;
	
	TaskDate = document.getElementById('TaskDatepicker').value;
	
	Task = document.TaskName.TaskInsert_Task.value;
	
	Charge = document.TaskCharge.AssignCharge.value;
	Chargesrvno = document.TaskCharge.Chargesrvno.value
	
	if(res == "ok")
		{
		
		location.href ="Task_Insert_Result.do?INCDT_ACTVT_TYPE_CD="+encodeURI(TaskName)+ "&ACTVT_DATE="+encodeURI(TaskDate)+"&Task="+encodeURI(Task)+"&TASK_PSNCHNRG_SRVNO="+(Chargesrvno)+"&taskSeq="+encodeURI(TaskSeq)+"&Large="+encodeURI(Large)+"&Middle="+encodeURI(Middle)+"&Small="+encodeURI(Small);
		}
	else
		{
			alert(res);
		}
}

// Ajax 통신이 비정상적인 오류가 발생했을 때의Function
function whenError(res){
	alert("일시적인 오류가 발생하였습니다.");
	console.log(res);
}
// 아래의 두 Function 은 사용되지 않는 dummy Function 입니다.
function AssignTask_inputParameter(){
	
}
function AssignTask_inputParameterMiddle(){
	
}
