function checkFileType(filePath) {
	var fileFormat = filePath.split(".");
	if (fileFormat.indexOf("xlsx") > -1 || fileFormat.indexOf("xls") > -1) {
		return true;
	} else {
		return false;
	}

}

function check() {
	var file = $("#excelFile").val();
	if (file == "" || file == null) {
		alert("파일을 선택해주세요.");
		return false;
	} else if (!checkFileType(file)) {
		alert("xls 또는 xlsx 파일만 업로드 가능합니다.");
		return false;
	}

	document.excelUploadForm.submit();
}

function whenSuccess_read() {
	document.excelUploadForm.submit();
}

function whenError_read() {
	alert("readError!");
}

function Code_Insert(){
	location.href="Code_Insert.do";	
}

function Code_Insert_Excel() {
	location.href="Code_Insert_Excel.do";
}

function Code_Update(cd){
	location.href="Code_Modify.do?cd="+ cd;
}

function Code_Delete(cd){
	var con_delete = confirm("정말 삭제하시겠습니까?");
	if(con_delete == true){
		location.href = "Code_Delete_Action.do?cd="+ cd;
	}
	else {
		alert("취소하셨습니다");
		return false;			
	}		
}

function Code_Search() {
	var pageNo = 1;
	
	document.codeForm.currentPageNo.value = pageNo;
	document.codeForm.currentSelected.value = $('#search_dvs option:selected').val();
	document.listForm.currentSelectedCondition.value = $('#search_condition option:selected').val();
	document.codeForm.currentSearch.value = $('#search_nm').val();
	
	document.listForm.currentPageNo.value = pageNo;	
	document.listForm.currentSelected.value = $('#search_dvs option:selected').val();
	document.listForm.currentSelectedCondition.value = $('#search_condition option:selected').val();
	document.listForm.currentSearch.value = $('#search_nm').val();
	document.listForm.action = "ManageCode.do";
	document.listForm.submit();
}

function do_insertCode() {
	if (document.getElementById("ctlgCode") != null) {
		if ($("input#ctlgCode").val() == null || $("input#ctlgCode").val() == "") {
			alert("활동 유형을 선택해주십시오.")
			return false;
		}
	}
	
	if ($("#search_dvs option:selected").val() == "L9") {
		if ($("#select_L9_large").val() == null || $("#select_L9_large").val() == "") {
			alert("대분류를 선택해주십시오.");
			return false;
		}
	}
	
	if ($("#search_dvs option:selected").val() == "L7") {
		if ($("#select_cls_large").val() == null || $("#select_cls_large").val() == "") {
			alert("대분류를 선택해주십시오.");
			return false;
		}
		
		if ($("#select_cls_middle").val() == null || $("#select_cls_middle").val() == "") {
			alert("대분류를 선택해주십시오.");
			return false;
		}
	}
	
	if ($("input#cd").val() == null || $("input#cd").val() == "") {
		alert("코드를 입력해주십시오.")
		return false;
	}
	if ($("input#cd_nm").val() == null || $("input#cd").val() == "") {
		alert("코드명을 입력해주십시오.")
		return false;
	}
	if ($("#search_dvs option:selected").val() == null || $("#search_dvs option:selected").val() == "") {
		alert("그룹코드를 선택해주십시오.")
		return false;
	}
	
	var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	
    if(check.test($("input#cd").val())){
       alert("코드에 한글이 포함되어 있습니다. 다시 입력해주십시오.");
       return false;
    }
    
    var resultCode = $("input#cd").val();
    if (document.getElementById("ctlgCode") != null) {
    	console.log("before sum : " + resultCode);
    	
    	resultCode = $("input#ctlgCode").val().trim() + $("input#cd").val();
    	if ($('#search_dvs option:selected').val().trim() == 'L12') {
    		resultCode = $("input#ctlgCode").val().trim() + $("input#cd").val();
    	}	
    	
    	console.log("after sum : " + resultCode);
    }
    
    console.log("final result code : " + resultCode);
    
    // ajax ////////
    var codeVO = new Object();
    codeVO.cd = resultCode;
    
    var jsonString = JSON.stringify(codeVO);
    console.log(jsonString);
    
    $.ajax({
	 	url: "CheckDuplicateAjax.do",												//request보낼 경로
	 	type: "post",																//메소드(post로 적용)
	 	data: {"jsonString":jsonString}, 											//보낼 데이터
	 	success : whenSuccess,														//성공 시 값 보낼 펑션
	 	error: function(result) {
	 		console.log("on dupl ajax error");
	 	}
    });
    // ajax end ////   
}

function do_modifyCode(cd) {
	if(($('#changeName').val() == null || $('#changeName').val() == "")
		|| ($('#changeRmrk').val() == null || $('#changeRmrk').val() == "")) {
		if($('#changeName').val() == null || $('#changeName').val() == "") {
			alert("변경할 코드명을 입력해 주십시오.");
		}
		if($('#changeRmrk').val() == null || $('#changeRmrk').val() == "") {
			alert("변경할 비고 내용을 입력해 주십시오.");
		}
		return false;
	}		
	
	document.changeCodeForm.cd.value = cd;
	document.changeCodeForm.chng_cd_nm.value = $('#changeName').val();
	document.changeCodeForm.chng_rmrk.value = $('#changeRmrk').val();
	
	document.changeCodeForm.action = "Code_Modify_Action.do";
	document.changeCodeForm.submit();
}

function fn_search(pageNo){	
	document.listForm.currentPageNo.value = pageNo;
	document.listForm.currentSelected.value = $('#search_dvs option:selected').val();
	document.listForm.currentSearch.value = $('#search_nm').val();
	
	document.codeForm.currentPageNo.value = pageNo;
	document.codeForm.currentSelected.value = $('#search_dvs option:selected').val();	
	document.codeForm.currentSearch.value = $('#search_nm').val();
	document.codeForm.action = "ManageCode.do";
	document.codeForm.submit();
}

//go group code
function fn_go_grpCode() {
	location.href = "ManageGroupCode.do";
}

function groupCode_Search() {
	var pageNo = 1;
	
	document.codeForm.currentPageNo.value = pageNo;
	document.codeForm.currentSearch.value = $('#search_nm').val();
	
	document.listForm.currentPageNo.value = pageNo;	
	document.listForm.currentSearch.value = $('#search_nm').val();
	document.listForm.action = "ManageGroupCode.do";
	document.listForm.submit();
}

function groupCode_Insert() {
	location.href = "GroupCode_Insert.do";
}

function groupCode_Update(cd){
	location.href="GroupCode_Modify.do?cd="+ cd;
}

function groupCode_Delete(cd){
	var con_delete = confirm("정말 삭제하시겠습니까?");
	if(con_delete == true){
		document.codelistForm.action = "GroupCode_Delete_Action.do?cd="+ cd;
		document.codelistForm.submit();
	}
	else {
		alert("취소하셨습니다");
		return false;			
	}		
}

function fn_search_group(pageNo) {
	document.listForm.currentPageNo.value = pageNo;
	document.listForm.currentSearch.value = $('#search_nm').val();
	
	document.codeForm.currentPageNo.value = pageNo;	
	document.codeForm.currentSearch.value = $('#search_nm').val();
	document.codeForm.action = "ManageGroupCode.do";
	document.codeForm.submit();
}

function do_insertGroupCode() {
	if ($("input#cd").val() == null || $("input#cd").val() == "") {
		alert("코드를 입력해주십시오.");
		return false;
	}
	else {
		if ($("input#cd").val().trim() == "") {
			alert("코드를 입력해주십시오.");
			return false;
		}
	}
	
	if ($("input#cd_nm").val() == null || $("input#cd_nm").val() == "") {
		alert("코드명을 입력해주십시오.");
		return false;
	}
	else {
		if ($("input#cd_nm").val().trim() == "") {
			alert("코드명을 입력해주십시오.");
			return false;
		}
	}
	
	var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	
    if(check.test($("input#cd").val())){
       alert("코드에 한글이 포함되어 있습니다. 다시 입력해주십시오.");
       return false;
    }
    
    var resultCode = $("input#cd").val().trim();
    
    // ajax ////////
    var grpCodeVO = new Object();
    grpCodeVO.grp_cd = resultCode;
    
    var jsonString = JSON.stringify(grpCodeVO);
    console.log(jsonString);
    
    $.ajax({
	 	url: "CheckDuplicateGroupCodeAjax.do",												//request보낼 경로
	 	type: "post",																//메소드(post로 적용)
	 	data: {"jsonString":jsonString}, 											//보낼 데이터
	 	success : whenSuccessGrpCode,														//성공 시 값 보낼 펑션
	 	error: function(result) {
	 		console.log("on dupl ajax error");
	 	}
    });
    // ajax end ////
}

function do_modifyGroupCode(cd) {
	if(($('#changeName').val() == null || $('#changeName').val() == "")
		|| ($('#changeRmrk').val() == null || $('#changeRmrk').val() == "")) {
		if($('#changeName').val() == null || $('#changeName').val() == "") {
			alert("변경할 코드명을 입력해 주십시오.");
		}
		if($('#changeRmrk').val() == null || $('#changeRmrk').val() == "") {
			alert("변경할 비고 내용을 입력해 주십시오.");
		}
		return false;
	}		
	
	document.changeCodeForm.cd.value = cd;
	document.changeCodeForm.chng_cd_nm.value = $('#changeName').val();
	document.changeCodeForm.chng_rmrk.value = $('#changeRmrk').val();
	
	document.changeCodeForm.action = "GroupCode_Modify_Action.do";
	document.changeCodeForm.submit();
}

function access(){
	var iframe = document.getElementById("iframe");
	var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
	console.log(innerDoc.body)
}

function onChange_selectedGroup(obj) {
	var grpCode = "";
	if (obj.value != null && obj.value != "")
		grpCode = obj.value.trim();
	
	if (grpCode == "L12") {
		if ($("code_tr1").children().length == 2) {
			$("#code_tr1").append("<td></td>");
			$("#code_tr2").append("<td></td>");
			$("#code_tr3").append("<td></td>");
			$("#code_tr4").append("<td></td>");
		}		
	}	
}

function fn_go_mgrCode() {
	location.href = "ManageCode.do";
}

function whenSuccess(result) {
	var obj = JSON.parse(result);
	console.log(obj);
	var onDuplicate = false;
	var cd = "";
	onDuplicate = obj.onDuplicate;
	cd = obj.cd;
	
	if (onDuplicate == true) {
    	alert("이미 등록되어있는 코드입니다.");
    	return false;
    }
    
	document.insertCodeForm.cd.value = cd;
	document.insertCodeForm.cd_nm.value = $("input#cd_nm").val();
	document.insertCodeForm.rmrk.value = $("input#rmrk").val();
	document.insertCodeForm.grp_cd.value = $('#search_dvs option:selected').val();
	if ($('#search_dvs option:selected').val() == "L9")
		document.insertCodeForm.action = "Code_Insert_Action.do?large=" + $("#select_L9_large option:selected").val();
	else if ($('#search_dvs option:selected').val() == "L7")
		document.insertCodeForm.action = "Code_Insert_Action.do?large=" + $("#select_cls_large option:selected").val() + "&middle=" + $("#select_cls_middle option:selected").val();
	else
		document.insertCodeForm.action = "Code_Insert_Action.do";
	document.insertCodeForm.submit();
}

function whenSuccessGrpCode(result) {
	var obj = JSON.parse(result);
	console.log(obj);
	var onDuplicate = false;
	var grp_cd = "";
	onDuplicate = obj.onDuplicate;
	grp_cd = obj.grp_cd;
	
	if (onDuplicate == true) {
    	alert("이미 등록되어있는 코드입니다.");
    	return false;
    }
	
	document.insertCodeForm.cd.value = grp_cd;
	document.insertCodeForm.cd_nm.value = $("input#cd_nm").val();
	document.insertCodeForm.rmrk.value = $("input#rmrk").val();
	document.insertCodeForm.action = "GroupCode_Insert_Action.do";
	document.insertCodeForm.submit();
}