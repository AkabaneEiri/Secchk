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

//function Code_Update(){
//	var cd = event.srcElement.id;
function Code_Update(cd){

	document.write("");
	location.href="Code_Modify.do?cd="+ cd;
}

function Code_Delete(cd){
	//var cd = event.srcElement.id;
	var con_delete = confirm("정말 삭제하시겠습니까?");
	if(con_delete == true){
		document.codelistForm.action = "Code_Delete_Action.do?cd="+ cd;
		document.codelistForm.submit();
	}
	else
		{
		alert("취소하셨습니다");
		document.write("");
		window.location.replace('ManageCode.do');	
		
		}	
	
}

function Code_Search() {
	var pageNo = 1;
	
	document.codeForm.currentPageNo.value = pageNo;
	document.codeForm.currentSelected.value = $('#search_dvs option:selected').val();
	document.codeForm.currentSearch.value = $('#search_nm').val();
	
	document.listForm.currentPageNo.value = pageNo;	
	document.listForm.currentSelected.value = $('#search_dvs option:selected').val();
	document.listForm.currentSearch.value = $('#search_nm').val();
	document.listForm.action = "ManageCode.do";
	document.listForm.submit();
}

function do_insertCode() {
	document.insertCodeForm.cd.value = $("input#cd").val();
	document.insertCodeForm.cd_nm.value = $("input#cd_nm").val();
	document.insertCodeForm.rmrk.value = $("input#rmrk").val();
	document.insertCodeForm.grp_cd.value = $('#search_dvs option:selected').val();
	document.insertCodeForm.action = "Code_Insert_Action.do";
	document.insertCodeForm.submit();
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

//	var cd = event.srcElement.id;
	document.write("");
	location.href="GroupCode_Modify.do?cd="+ cd;
}

function groupCode_Delete(cd){
	//var cd = event.srcElement.id;
	var con_delete = confirm("정말 삭제하시겠습니까?");
	if(con_delete == true){
		document.codelistForm.action = "GroupCode_Delete_Action.do?cd="+ cd;
		document.codelistForm.submit();
	}
	else
		{
		alert("취소하셨습니다");
		document.write("");
		window.location.replace('ManageGroupCode.do');	
		
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
	document.insertCodeForm.cd.value = $("input#cd").val();
	document.insertCodeForm.cd_nm.value = $("input#cd_nm").val();
	document.insertCodeForm.rmrk.value = $("input#rmrk").val();
	document.insertCodeForm.action = "GroupCode_Insert_Action.do";
	document.insertCodeForm.submit();
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
