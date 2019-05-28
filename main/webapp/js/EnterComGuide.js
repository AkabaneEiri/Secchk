function fn_search_task() {
	var Name = document.SelectCode.searchCondition1.value;
	var Code = document.SelectCode.searchCode.value;
	var Date = document.taskdate.searchCondition2.value;	
	
	if(Name == "") {
		alert("부대활동을 선택해주십시오.");
		return false;
	}
	
	if(Date == "") {
		alert("일시를 선택해 주십시오");
		return false;
	}
	
	location.href = "EnterComGuide_Search.do?Name=" + encodeURI(Name) + "&Date=" + encodeURI(Date) + "&Code=" + Code;
}

function Guide_Insert() {
	var CUI1 = document.guidnc_name.CUI1.value;
	var CUI2 = document.guidnc_name.CUI2.value;
	var CUI3 = document.guidnc_name.CUI3.value;
	var id = document.guidnc_name.taskDataID.value;
	var date = document.guidnc_name.date.value;
	
	location.href = "EnterComGuide_InsertAll.do?guidnc_1=" + encodeURI(CUI1) + "&guidnc_2=" + encodeURI(CUI2) + "&guidnc_3=" + encodeURI(CUI3) + "&id=" + encodeURI(id)  + "&date=" + encodeURI(date);
}

function Guide_Delete1() {
	var id = document.guidnc_name.taskDataID.value;
	var date = document.guidnc_name.date.value;

	var DeleteConfirm = confirm('정말 삭제하시겠습니까?')

	if (DeleteConfirm) {		
		location.href = "EnterComGuide_Delete1.do?id=" + id + "&date=" + encodeURI(date);
	} 
	else {
		alert("취소하셨습니다.");
	}
}

function Guide_Delete2() {
	var id = document.guidnc_name.taskDataID.value;
	var date = document.guidnc_name.date.value;
	
	var DeleteConfirm = confirm('정말 삭제하시겠습니까?')

	if (DeleteConfirm) {		
		location.href = "EnterComGuide_Delete2.do?id=" + id + "&date=" + encodeURI(date);
	} 
	else {
		alert("취소하셨습니다.");
	}
}

function Guide_Delete3() {
	var id = document.guidnc_name.taskDataID.value;
	var date = document.guidnc_name.date.value;

	var DeleteConfirm = confirm('정말 삭제하시겠습니까?')

	if (DeleteConfirm) {		
		location.href = "EnterComGuide_Delete3.do?id=" + id + "&date=" + encodeURI(date);
	} 
	else {
		alert("취소하셨습니다.");
	}
}

function go_Monitor(date) {
	var dateForm = date.replace(/\-/g, '');
	location.href = "TroopsMonitoring_Search.do?incdt_idtf_cd=&date=" + dateForm;
}