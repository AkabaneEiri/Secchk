function go_Guide_Insert(actId) {
	location.href = "Guide_Insert.do?id=" + actId;
}

function clickTdEvent(tdObj){
	
	var Name = tdObj.innerText;
	var Code = tdObj.id;
	
	document.activityDateForm.incdt_idtf_nm.value = Name;
	document.activityDateForm.incdt_idtf_cd.value = Code.trim();
}