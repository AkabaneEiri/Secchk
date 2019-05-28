$(document).ready(function() {
	$("#search_dvs").on("change", function() {
		
		var htmlTop = "";
		var htmlInnerHead = "";
		var htmlInnerBody = "<input class='sub_input sub_ctlg_input' style='text-align: center;' type='text' id='ctlgCode' class='form-control grp_input_width' disabled='disabled'/>";
		var htmlSeparator = "<span id='separatorMarker' class='import_marker'> + </span>";
		var htmlBottom = "";
		
		var html = htmlTop + htmlInnerHead + htmlInnerBody + htmlSeparator + htmlBottom;
		
		// if (display) > display none
		$("#search_large").addClass("display_none");
		$("#search_middle").addClass("display_none");
		$("#search_act").addClass("display_none");
		$("#L9_large").addClass("display_none");
		$("#search_cls_large").addClass("display_none");
		$("#search_cls_middle").addClass("display_none");
		if (document.getElementById("ctlgCode") != null) {
			$("#ctlgCode").remove();
			$("#separatorMarker").remove();
			$("#code_tr2").children().eq(1).children().removeClass("sub_ctlg_input");
		}
		
		// if select task code
		if ($(this).val().trim() == "L12") {	
			$("#search_large").removeClass("display_none");
			$("#search_middle").removeClass("display_none");
			$("#search_act").removeClass("display_none");
			if (document.getElementById("ctlgCode") == null) {
				$("#code_tr2").children().eq(1).children().addClass("sub_ctlg_input");
				$("#code_tr2").children().eq(1).children().before(html);
			}			
		}
		else if ($(this).val().trim() == "L9") {			
			$("#L9_large").removeClass("display_none");
		}
		else if ($(this).val().trim() == "L7") {
			$("#search_cls_large").removeClass("display_none");
			$("#search_cls_middle").removeClass("display_none");
		}
	});
})

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
		objOption.text = obj_act[i].ctlg_cd + "\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+ obj_act[i].ctlg_nm;
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
			objOption.text = obj[i].ctlg_cd + "\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0"+"\u00A0" + obj[i].ctlg_nm;
			objOption.id = obj[i].ctlg_cd.trim();
			objOption.value = obj[i].lrgcls.trim() + "/" + obj[i].mdcls.trim();
			
			OptionBox.options.add(objOption);
		}	
	$("#activity option:eq(1)").prop("selected", true);
	$("#activity").change();
}

function whenSuccessSearch(result){
	console.log("success:"+result);
	jQuery("#table_hover>tbody:last>tr").remove();
	var obj = JSON.parse(result);
	var OptionBoxLarge = document.getElementById("searchConditionLage");
	var OptionBox = document.getElementById("searchConditionMiddle");
	if(obj[0].ctlg_cd == "E001_001")
		{
		var str = '';
			jQuery.each(obj, function(i){
				str += '<tr id="'+obj[i].ctlg_cd+'"name="'+obj[i].ctlg_nm+'" onclick="javascript:clickTrEvent(this)">';
				str += '<td style="text-align:left;"id="'+obj[i].ctlg_cd+'" value="'+obj[i].ctlg_nm+'">'+obj[i].ctlg_nm+'</td>';
				str += '<td style="text-align:left;">'+obj[i].ctlg_cd+'</td>';
				str += '</tr>'
			});
			jQuery("#table_hover").append(str);
		}
	else
		{
			var str = '';
			
			jQuery.each(obj, function(i){
				str += '<tr id="'+obj[i].ctlg_cd+'"name="'+obj[i].ctlg_nm+'" onclick="javascript:clickTrEvent(this)">';
				str += '<td style="text-align:left;"id="'+obj[i].ctlg_cd+'" value="'+obj[i].ctlg_nm+'">'+obj[i].ctlg_nm+'</td>';
				str += '<td style="text-align:left;">'+obj[i].ctlg_cd+'</td>';
				str += '</tr>'
			});
			
			jQuery("#table_hover").append(str);
		}
}


function ActivityChange(obj) {
	var ctlg_cd = $("#activity option:selected").attr("id");
	var ctlg_nm = $("#activity option:selected").text();
	
	console.log("ctlg_cd : " + ctlg_cd);
	console.log("ctlg_nm : " + ctlg_nm);
	
	$("#ctlgCode").val(ctlg_cd);
}

function onChange_cls_large() {
	var target = document.getElementById("select_cls_middle");
	
	var arr = $("input[name=" + $("#select_cls_large option:selected").val() + "]");
	var length = arr.length;
	
	for(var i = target.length; i>0;i--) {
		target.options[i] = null;
	}
	
	for (var i = 0; i < length; ++i) {
		var objOption = document.createElement("option");
		
		objOption.value = arr[i].id;
		objOption.text = arr[i].value;
		
		target.options.add(objOption);
	}	
}