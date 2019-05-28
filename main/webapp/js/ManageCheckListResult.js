$(document).ready(function(){
//	$('#Checklist_code').keyup(function(){
//		var ctlg_cd = $("#ctlgCode").val().trim();
//		var ctlg_itm_cd = document.CheckCode.Checklist_code.value;
//		var resultCode = ctlg_cd.trim() + "_" + ctlg_itm_cd.trim();
//		if(resultCode.length >= 8){
//			var code = new Object();
//			
//			//Task = document.Task.searchCondition1.value;
//			
////			if(ctlg_cd == "")
////			{
////				ctlg_cd = Task;
////			}
//			
//			code.code1 = resultCode;
//			
//			var jsonString = JSON.stringify(code);
//			console.log(jsonString);
//			
//			$.ajax({
//				url:'./ManageCheckList_Ajax_json.do?cltg_cd='+encodeURI(ctlg_cd)+"&ctlg_itm_cd="+encodeURI(resultCode), //request 보낼 서버의 경로
//				type:'post', // 메소드(get, post, put 등)
//				data: {"jsonString":jsonString}, //보낼 데이터
//				success: function(result) {
//				//서버로부터 정상적으로 응답이 왔을 때 실행 
//					var obj = jQuery.parseJSON(result);
//					var resultinfo = document.getElementById("codeCheck");
//					var Accept = 'OK';
//					if(obj.code1 == Accept){
//						resultinfo.setAttribute('class', 'badge badge-primary');
//						document.CheckCode.codeCheck_result.value= "사용가능";
//						document.button_form.submit.disabled = false;
//					}else{
//						resultinfo.setAttribute('class', 'badge badge-danger');
//						document.CheckCode.codeCheck_result.value= obj.code1;
//						document.button_form.submit.disabled = 'disabled';
//					}
//				},
//				error: function(err) {
//				//서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
//					alert("error! : "+err);
//				}
//			});
//		}
//	})
	
	// 190521 add by seungwon ///////////
	// for get next code by ajax ////////
	$("#activity").on("change", function() {
		$("#Checklist_code").val("");
		
		var ctlg_cd = $("#activity option:selected").attr("id");
		var ctlg = new Object();
		ctlg.ctlg_cd = ctlg_cd;
		
		var jsonString = JSON.stringify(ctlg);
		console.log(jsonString);
		
		$.ajax({
			url: "ItemCodeCheckAjax.do",
			type: "post",
			data: {"jsonString":jsonString},
			success: function(result) {
				console.log("success : " + result);
				var obj = JSON.parse(result);
				var arr = obj.nextCode.split("_");
				$("#Checklist_code").val(arr[1]);
			},
			error: function(result) {
				console.log("err : " + result);
			}
		})
	});
	
	/////////////////////////////////////
})
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Insert_Cancle(){
	
	ctlg_cd = getParameterByName('ctlg_cd');
	location.href = "ManageCheckListItem.do?ctlg_cd="+encodeURI(ctlg_cd);
}

function Insert_Submit(){
	var Task = document.Task.searchCondition1.value;
	var Code = "";
	var Code1 = document.getElementById("ctlgCode").value;
	var Code2 = document.getElementById("Checklist_code").value;
	var Name = document.ChecnName.Checklist_name.value;
	var ctlg_cd = getParameterByName('ctlg_cd');
	if(ctlg_cd == "")
		{
			ctlg_cd = Task;
		}
	Code = Code1+"_"+Code2;
	
	location.href="ManageCheckListItem_Result.do?Ctlg_cd="+encodeURI(ctlg_cd)+"&Code="+encodeURI(Code)+"&Name="+encodeURI(Name);
}
function access(){
	
}
function clickTdEvent(tdObj){
	
	var Name = tdObj.innerText;
	
	document.Task.searchCondition1.value=Name;
}

function Insert_Checklist() {
	var ctlg_cd = $("#ctlgCode").val().trim();
	var resultCode = $("#ctlgCode").val() + $("#Checklist_code").val();
	var Code = "";
	var Code1 = document.getElementById("ctlgCode").value.trim();
	var Code2 = document.getElementById("Checklist_code").value.trim();
	Code = Code1+"_"+Code2;
	var Name = document.ChecnName.Checklist_name.value;
	
	if ($("#activity option:selected").val() == "" || $("#activity option:selected").val() == null || $("#activity option:selected").val() == undefined) {
		alert("활동을 선택해주십시오.");
		return false;
	}
	
	if ($("#AssignCharge").val().trim() == "") {
		alert("항목 내용을 입력해주십시오.");
		return false;
	}
	
	location.href = "ManageCheckListItem_Result.do?Ctlg_cd="+encodeURI(ctlg_cd)+"&Code="+encodeURI(Code)+"&Name="+encodeURI(Name);
}