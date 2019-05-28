$(document).ready(function(){
	$.ajax({
		url:"Task_Large_input.do",
		type:"post",
		success:whenSuccessLoad,
		error:whenError
	});
});

//////////////////////////////////////////
//	모달용 펑션 입니다.
//////////////////////////////////////////
function clickTdEvent(tdObj){						// TaskSmall(과업 소분류 테이블)을 클릭했을 때에 유발되는 Function
	
	var Code = document.getElementById("ctlg_cd");
	//////////////////////////////////////////
	//	테이블 클릭 시 과업 대쿤류 & 중분류 가져오는 통신
	//////////////////////////////////////////
	var i;
	var task = new Object();						 
	task.Code = Code;
	
	var jsonString = JSON.stringify(task);
	
	$.ajax({
		url:"Modal_Cls_Search.do",
		type:"post",
		data:{"jsonString":jsonString},
		success:whenSuccessModal,
		error:whenError
	});
	//////////////////////////////////////////
	//	대분류, 중분류 최신화 하기 위한 루틴	
	//////////////////////////////////////////
	var Large = $("#searchConditionLage option:selected").val();			// 대분류 코드
	var Large_nm = $("#searchConditionLage option:selected").attr('name');	// 대분류 값
	var Middle = $("#searchConditionMiddle option:selected").attr('id');	// 중분류 코드
	var Middle_nm = $("#searchConditionMiddle option:selected").val();		// 중분류 값

	var result = false;
//	
//	for(var i = 0; i < parent.document.scripts.length; ++i) {
//		console.log(parent.document.scripts.item(i));
//		if (parent.document.scripts.item(i).getAttribute('src') == "js/submit.js") {
//			result = true;
//		}
//	}
//	
//	if (result == true)	
//		parent.selectedEvent(Large, Large_nm, Middle, Middle_nm);
}
function SelectEvent()
{
	var ctlg_select = document.getElementById("Task_name");
	var ctlg_cd = ctlg_select.options[ctlg_select.selectedIndex].value;
	
	var i;
	var task = new Object();						 
	task.Code = ctlg_cd;
	
	var jsonString = JSON.stringify(task);
	
	$.ajax({
		url:"Modal_Cls_Search.do",
		type:"post",
		data:{"jsonString":jsonString},
		success:whenSuccessModal,
		error:whenError
	});
	//////////////////////////////////////////
	//	대분류, 중분류 최신화 하기 위한 루틴	
	//////////////////////////////////////////
	var Large = $("#searchConditionLage option:selected").val();			// 대분류 코드
	var Large_nm = $("#searchConditionLage option:selected").attr('name');	// 대분류 값
	var Middle = $("#searchConditionMiddle option:selected").attr('id');	// 중분류 코드
	var Middle_nm = $("#searchConditionMiddle option:selected").val();		// 중분류 값
	}
function Taskcls_search()
{
	var code = new Object();
	var Taskcls = document.getElementById("ClsSelect");
	var TaskClsValue = Taskcls.options[Taskcls.selectedIndex].value;
	var TaskNameSearch = document.getElementById("TaskNameSearch").value;				// 검색어
	var OptionBoxLarge = document.getElementById("searchConditionLage");				// 대분류 코드 가져오기
	var OptionLarge = OptionBoxLarge.options[OptionBoxLarge.selectedIndex].value;		// 선택된 대분류 코드
	var OptionBoxMiddle = document.getElementById("searchConditionMiddle");				// 중분류 코드 가져오기
	var OptionMiddle = OptionBoxMiddle.options[OptionBoxMiddle.selectedIndex].id;		// 선택된 중분류 코드

	
	if(TaskClsValue == "ctlg_nm")
	{
		code.TaskNameSearch = TaskNameSearch;												// AJAX 통신을 위한 JSON처리 루틴
	}
	else
	{
		code.TaskCodeSearch = TaskNameSearch;
	}
	code.OptionLarge = OptionLarge;
	code.OptionMiddle = OptionMiddle;

	var jsonString = JSON.stringify(code);
	
	 $.ajax({
		 	url: "GroupNameSearchName.do",
		 	type: "post",
		 	data: {"jsonString":jsonString}, 
		 	success : whenSuccessSearch,
		 	error: whenError
	 });		
	}

//////////////////////////////////
//	테이블 값 클릭 시 대분류&중분류 자동 선택 루틴
//////////////////////////////////

function whenSuccessModal(resdata){
	
	var mdcls = JSON.parse(resdata);										// AJAX 통신 결과값
	var obj = mdcls['md'];
	var lrg = mdcls['lrg'];
	var Large = document.getElementById("searchConditionLage");			// 대분류 Element 가져오기
	var Middle = document.getElementById("searchConditionMiddle");		// 중분류 Element 가져오기
	
	var MiddleCls = lrg[0].mdcls_select;								// AJAX 통신 결과로 받은 중분류 데이터
	
//	var MiddleOps = 0 != $('#searchConditionMiddle option[value='+MiddleCls+']').length;
	var MiddleOps = 0 != $(MiddleCls).length;
	
	if(MiddleOps == false)																//값이 없으면
		{
//		jQuery("#searchConditionLage").val(obj[0].lrgcls).prop("selected", true);		//클릭된 데이터의 대분류값을 선택하고
		for(var i = Middle.length; i>0;i--)
			{
			Middle.options[i] = null;													//중분류 SelectBox를 초기화
			}
		for(var i=0;i<obj.length;i++)
			{
				var objOption = document.createElement("option");						//중분류 SelectBox를 생성
				objOption.text = obj[i].mdcls_nm;
				objOption.value = obj[i].mdcls_nm;
				objOption.id = obj[i].mdcls;
				
				Middle.options.add(objOption);
			}
		}
		jQuery("#searchConditionLage").val(lrg[0].lrgcls).prop("selected", true);		// 대분류값 선택
		jQuery("#searchConditionMiddle").val(lrg[0].mdcls_select_nm).prop("selected", true);	//중분류값 선택
}

function whenError(resdata){															//AJAX통신 에러 시 루틴
	alert("Error!");
	console.log("Error by :"+ resdata);													//Error라고 ALert 띄우고, 콘솔에 에러 코드 로깅
}

function LargeChange(){																//체크리스트 대분류 변경 시 작동
	var code = new Object();
//	var Large = obj.value;
	var OptionBoxLarge = document.getElementById("searchConditionLage");				// 대분류 코드 가져오기
	var OptionLarge = OptionBoxLarge.options[OptionBoxLarge.selectedIndex].value;
	code.Large = OptionLarge;
	
	var jsonString = JSON.stringify(code);
	
	if( jsonString == "" || jsonString == null || jsonString == "undefined")			//대분류가 잘못된 값인지 체크
		{
			return false;
		}
	
	 $.ajax({
		 	url: "GroupNameSearchLarge.do?Large="+encodeURI(OptionLarge),						//request보낼 경로
		 	type: "post",																//메소드(post로 적용)
		 	data: {"jsonString":jsonString}, 											//보낼 데이터
		 	success : whenSuccessLarge,													//성공 시 값 보낼 펑션
		 	error: whenError
	 });
}
function whenSuccessLarge(result){														//대분류 AJAX 성공 시
	var obj_Total = JSON.parse(result);														//AJAX 결과값을 Parse
	var obj = obj_Total.Middle;
	var obj_small = obj_Total.Small;
	var OptionBox = document.getElementById("searchConditionMiddle");
	var OptionBoxsmall = document.getElementById("Task_name");
	for(var i = OptionBox.length; i>0; i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
			var objOption = document.createElement("option");
			objOption.text = obj[i].Middle_cd_nm;
			objOption.value = obj[i].Middle_cd_nm;
			objOption.id = obj[i].Middle_cd;
			
			OptionBox.options.add(objOption);
		}
	for(var i = OptionBoxsmall.length; i>0;i--)
	{
		OptionBoxsmall.options[i] = null;
	}
	for(var i=0;i<obj_small.length;i++)
	{
		var objOption = document.createElement("option");
		objOption.text = obj_small[i].ctlg_cd + "\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+ obj_small[i].ctlg_nm;
		objOption.value = obj_small[i].ctlg_cd;
		objOption.id = obj_small[i].ctlg_nm;
		
		OptionBoxsmall.options.add(objOption);
	}
	$("#Task_name").val(obj_small[0].ctlg_cd).prop("selected", true);
	AssignTask_inputParameterMiddle();
	SelectEvent();
}

function MiddleChange(obj){

	var code = new Object();
	var Middle = obj.value;
	code.Middle = Middle;
	
	var jsonString = JSON.stringify(code);
	
	if( jsonString == "" || jsonString == null || jsonString == "undefined")	//대분류가 잘못된 값인지 체크
	{
		return false;
	}

 $.ajax({
	 	url: "GroupNameSearchMiddle.do?Middle="+encodeURI(Middle),										//request보낼 경로
	 	type: "post",																		//메소드(post로 적용)
	 	data: {"jsonString":jsonString}, 													//보낼 데이터
	 	contnetType:"application/json; charset=utf-8",
	 	success : whenSuccessMiddle,																					//성공 시 값 보낼 펑션
	 	error: whenError
 });
}

function whenSuccessMiddle(res){
	console.log("success:"+res);
	var obj = JSON.parse(res);
	var OptionBox = document.getElementById("Task_name");
	for(var i = OptionBox.length; i>0; i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
			var objOption = document.createElement("option");
			objOption.text = obj[i].ctlg_cd + "\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+ obj[i].ctlg_nm;
			objOption.value = obj[i].ctlg_cd;
			objOption.id = obj[i].ctlg_nm;
			
			OptionBox.options.add(objOption);
		}
	$("#Task_name").val(obj[0].ctlg_cd).prop("selected", true);
}

function whenSuccessSearch(res){
	console.log("success:"+res);
	var obj = JSON.parse(res);
	var OptionBox = document.getElementById("Task_name");
	

	
	if(obj[0].ctlg_cd == 'E001_001')
		{
			alert('데이터가 없습니다');
		}
	else
		{
			for(var ii = OptionBox.length; ii>0; ii--)
			{
				OptionBox.options[ii] = null;
			}
			for(var i=0;i<obj.length;i++)
				{
					var objOption = document.createElement("option");
					objOption.text = obj[i].ctlg_cd + "\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+ obj[i].ctlg_nm;
					objOption.value = obj[i].ctlg_cd;
					objOption.id = obj[i].ctlg_nm;
					
					OptionBox.options.add(objOption);
				}
			$("#Task_name").val(obj[0].ctlg_cd).prop("selected", true);
		}
	SelectEvent();
}
function Submit()
{
	var lrgcls_select;
	var mdcls_select;
	var ctlg_select;
	var ctlg_cd;
	var ctlg_nm;
	var lrgcls;
	var mdcls;
	
	// SelectBox에서 값 가져오는 구문
	
	lrgcls_select = document.getElementById("searchConditionLage");
	mdcls_select = document.getElementById("searchConditionMiddle");
	ctlg_select = document.getElementById("Task_name");
	
	// SelectBox에서 선택된 값을 가져오는 구문
	
	lrgcls = lrgcls_select.options[lrgcls_select.selectedIndex].value;
	mdcls = mdcls_select.options[mdcls_select.selectedIndex].id;
	ctlg_nm = ctlg_select.options[ctlg_select.selectedIndex].id;
	ctlg_cd = ctlg_select.options[ctlg_select.selectedIndex].value;
	
	// 각 값들의 Type이 Char 값이라 들어간 공백 제거하는 구문
	
	lrgcls = lrgcls.trim();
	mdcls = mdcls.trim();
	ctlg_nm = ctlg_nm.trim();
	ctlg_cd = ctlg_cd.trim();
	
	//각 엘리먼트를 result에 담기. 앞으로 이 값을 쓰면 됩니다.
	
	document.getElementById("result_lrgcls").value = lrgcls;
	document.getElementById("result_mccls").value = mdcls;
	document.getElementById("result_nm").value = ctlg_nm;
	document.getElementById("result_cd").value = ctlg_cd;
	
	Submit_parent();
	document.getElementById("TaskNameSearch").value = "";
}
function whenSuccessLoad(res)
{
	lrgcls_select = document.getElementById("searchConditionLage");
	
	var obj = JSON.parse(res);
	for(var i = lrgcls_select.length; i>0;i--)
		{
		lrgcls_select.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
		
			var objOption = document.createElement("option");
			objOption.text = obj[i].cd_nm;
			objOption.value = obj[i].cd;
			objOption.id = obj[i].cd;
			
			lrgcls_select.options.add(objOption);
		}
			AssignTask_inputParameter();
	
	}
