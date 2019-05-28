function whenError(resdata){															//AJAX통신 에러 시 루틴
	alert("서버와 통신 중 에러가 발생했습니다. 다시 시도해주십시오.");
	console.log(resdata);																//Error라고 ALert 띄우고, 콘솔에 에러 코드 로깅
}

function LargeChange(obj){																//체크리스트 대분류 변경 시 작동
	var code = new Object();
	var Large = $("#largeCondition option:selected").attr("id");
	code.Large = Large;
	
	var jsonString = JSON.stringify(code);
	console.log(jsonString);
	
	if( jsonString == "" || jsonString == null || jsonString == "undefined")			//대분류가 잘못된 값인지 체크
		{
			return false;
		}
	
	var OptionBoxMiddle = document.getElementById("middleCondition");					//대분류 Option Box 정보 가져오기
	var OptionBoxAct = document.getElementById("activity");	
	for(var i = OptionBoxMiddle.length; i>0;i--) {
		OptionBoxMiddle.options[i] = null;
	}
	for(var i = OptionBoxAct.length; i>0;i--) {
		OptionBoxAct.options[i] = null;
	}
	
	$("#ctlgCode").val("");				// delete code data
	
	 $.ajax({
		 	url: "SelectLargeAjax.do?Large=" + encodeURI(Large),						//request보낼 경로
		 	type: "post",																//메소드(post로 적용)
		 	data: {"jsonString":jsonString}, 											//보낼 데이터
		 	success : whenSuccessLarge,													//성공 시 값 보낼 펑션
		 	error: whenError
	 });
	 
	 $("#input_text_condition").val("");
}

function whenSuccessLarge(result){		//대분류 AJAX 성공 시
	console.log("when succ large");
	console.log(result);
	var obj = JSON.parse(result);		//AJAX 결과값을 Parse
	var obj_mid = obj.Middle;
	var obj_act = obj.Small;
	
	var OptionBox = document.getElementById("middleCondition");					//대분류 Option Box 정보 가져오기
	for(var i = OptionBox.length; i>0;i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj_mid.length;i++)
		{
			var objOption = document.createElement("option");
			objOption.text = obj_mid[i].Middle_cd_nm;
			objOption.id = obj_mid[i].Middle_cd.trim();
			objOption.value = obj_mid[i].Middle_cd.trim();
			
			OptionBox.options.add(objOption);
		}
	
	var OptionBoxAct = document.getElementById("activity");	
	for(var i = OptionBoxAct.length; i>0;i--)
	{
		OptionBoxAct.options[i] = null;
	}
	for(var i=0;i<obj_act.length;i++)
	{
		var objOption = document.createElement("option");
		objOption.text = obj_act[i].ctlg_cd + "\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+ obj_act[i].ctlg_nm;
		objOption.id = obj_act[i].ctlg_cd.trim();
		objOption.value = obj_act[i].lrgcls.trim() + "/" + obj_act[i].mdcls.trim();
		
		OptionBoxAct.options.add(objOption);
	}
	
	$("#activity option:eq(1)").prop("selected", true);
	$("#activity").change();
}

function MiddleChange(obj){

	var code = new Object();
	var Middle = $("#middleCondition option:selected").attr("id");
	code.Middle = Middle;
	
	var jsonString = JSON.stringify(code);
	
	if( jsonString == "" || jsonString == null || jsonString == "undefined")	//대분류가 잘못된 값인지 체크
	{
		return false;
	}
	
	$("#ctlgCode").val("");		// delete code data

	$.ajax({
		url : "SearchActivityAjax.do", // request보낼 경로
		type : "post", // 메소드(post로 적용)
		data : {"jsonString" : jsonString}, // 보낼 데이터
		contnetType : "application/json; charset=utf-8",
		success : whenSuccessMiddle, // 성공 시 값 보낼 펑션
		error : whenError
	});
 
	$("#input_text_condition").val("");
}

function whenSuccessMiddle(result){
	var obj = JSON.parse(result);														//AJAX 결과값을 Parse
	var OptionBox = document.getElementById("activity");					//대분류 Option Box 정보 가져오기
	for(var i = OptionBox.length; i>0;i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
			var objOption = document.createElement("option");
			objOption.text = obj[i].ctlg_cd + "\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0" + obj[i].ctlg_nm;
			objOption.id = obj[i].ctlg_cd.trim();
			objOption.value = obj[i].lrgcls.trim() + "/" + obj[i].mdcls.trim();
			
			OptionBox.options.add(objOption);
		}	
	$("#activity option:eq(1)").prop("selected", true);
	$("#activity").change();
}

function ActivityChange(obj) {
//	var ctlg_cd = $("#activity option:selected").attr("id");
//	var ctlg_nm = $("#activity option:selected").text();
//	var cls = obj.value.split("/");
//	var large = cls[0];
//	var middle = cls[1];
	
//	console.log("ctlg_cd : " + ctlg_cd);
//	console.log("ctlg_nm : " + ctlg_nm);
//	console.log("large : " + large);
//	console.log("middle : " + middle);
	
//	var obj = new Object();
//	obj.selectedCondition = "ctlg_cd";
//	obj.textCondition = ctlg_cd;
//	
//	var jsonString = JSON.stringify(obj);
//	
//	$.ajax({
//		url : "SearchByConditionAjax.do", 			//request보낼 경로
//		type : "post", 							//메소드(post로 적용)
//		data : {"jsonString" : jsonString}, 	//보낼 데이터
//		contnetType : "application/json; charset=utf-8",
//		success : whenSuccessTotalSearch, 			//성공 시 값 보낼 펑션
//		error : whenError
//	});
	
	//$("#ctlgCode").val(ctlg_cd);
}

// if click search button -> ajax
function onClick_Search() {
	// search condition : name or code + text
	var large = $("#largeCondition option:selected").val();
	var middle = $("#middleCondition option:selected").val();
	var selectedCondition = $("#select_condition option:selected").val();
	var textCondition = $("#input_text_condition").val();
	
	console.log(large + " / " + middle + " / " + selectedCondition + " / " + textCondition);
	
	var obj = new Object();
	obj.large = large;
	obj.middle = middle;
	obj.selectedCondition = selectedCondition;
	obj.textCondition = textCondition;
	
	var jsonString = JSON.stringify(obj);	

	$.ajax({
		url : "SearchByConditionAjax.do", 			//request보낼 경로
		type : "post", 							//메소드(post로 적용)
		data : {"jsonString" : jsonString}, 	//보낼 데이터
		contnetType : "application/json; charset=utf-8",
		success : whenSuccessSearch, 			//성공 시 값 보낼 펑션
		error : whenError
	});
}

function whenSuccessSearch(result) {
	var obj = JSON.parse(result);														//AJAX 결과값을 Parse
	var OptionBox = document.getElementById("activity");					//대분류 Option Box 정보 가져오기
	for(var i = OptionBox.length; i>0;i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
			var objOption = document.createElement("option");
			objOption.text = obj[i].ctlg_cd + "\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0" + obj[i].ctlg_nm;
			objOption.id = obj[i].ctlg_cd.trim();
			objOption.value = obj[i].lrgcls.trim() + "/" + obj[i].mdcls.trim();
			
			OptionBox.options.add(objOption);
		}	
	$("#activity option:eq(1)").prop("selected", true);	
}

function whenSuccessTotalSearch(result) {
	var obj = JSON.parse(result);
}