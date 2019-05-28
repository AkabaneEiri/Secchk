// util
function getSelectedText(elementId) {
	var elt = document.getElementById(elementId);

	if (elt.selectedIndex == -1)
		return null;

	return elt.options[elt.selectedIndex].text;
}

function getInnerText(elementId) {
	var elt = document.getElementById(elementId);
	
	return elt.innerHTML;
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function fn_go_checkList(actId, srvno) {
	var seq = 0;
	
	if (document.getElementById("taskSeq")) {
		seq =  $("#taskSeq").val();
		document.checklistForm.seq.value = seq;
	}
		
	document.checklistForm.srvno.value = srvno;
	document.checklistForm.actId.value = actId;
	document.checklistForm.action = "ReservedSafeManagement_Checklist.do";
	document.checklistForm.submit();
}

function fn_set_starttime(actId, seq) {
	document.checklistForm.actId.value = actId;
	document.checklistForm.seq.value = seq;
	document.checklistForm.action = "Task_Start.do";
	document.checklistForm.submit();
}

function fn_set_finishtime(seq) {	
	documnet.checklistForm.id.value = actId;
	document.checklistForm.seq.value = seq;
	document.checklistForm.action = "Task_Finish.do";
	document.checklistForm.submit();
}

function fn_go_detail(ctlg_itm_cd) {
	document.checklistForm.findCode.value = ctlg_itm_cd;
	document.checklistForm.action = "RequestApproval.do";
	document.checklistForm.submit();
}

function fn_activitySearch() {
	// for B4 user
	var idtf = "";
	
	if(document.getElementById('idtf')) {
		idtf = $("#idtf option:selected").val();
		document.activityDateForm.incdt_idtf_cd.value = idtf;
	}
	
	//////////////
	
	var year = $("#activityYear option:selected").val();
	var month = $("#activityMonth option:selected").val();
	var day = $("#activityDay option:selected").val();

	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}

	document.activityDateForm.date.value = year + month + day;
	document.activityDateForm.action = "TroopsMonitoring_Search.do";
	document.activityDateForm.submit();
}

function fn_taskSearch(id) {
	document.taskDataForm.actId.value = id;
	document.taskDataForm.action = "TroopsMonitoring_Activity_Detail.do";
	document.taskDataForm.submit();
}

function splitDate(date) {
	return date.substring(11, 15);
}

function fn_itemComplete(ctlg_itm_cd) {
	if($('input:checkbox[id="completeCheck"]').is(":checked") == true) {
		document.checklistForm.findCode.value = ctlg_itm_cd;
		document.checklistForm.action = "ListItem_Complete.do";
		document.checklistForm.submit();
	}	
}

// submit to database
function fn_submit_safeRequest() {
	if ($("#activity option:selected").attr("id") == null || $("#activity option:selected").attr("id") == "") {
		alert("부대활동을 검색하여 입력해주시기 바랍니다.");
		return false;
	}
	
	if ($("textarea#rsn_area").val() == null) {
		alert("사유를 입력해주시기 바랍니다.");
		return false;
	}
	
	if ($("textarea#rsn_area").val() != null) {
		if ($("textarea#rsn_area").val().trim() == "") {
			alert("사유를 입력해주시기 바랍니다.");
			return false;
		}	
	}
	
	var year = $("#activityYear option:selected").val();
	var month = $("#activityMonth option:selected").val();
	var day = $("#activityDay option:selected").val();

	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	
	document.safeRequestForm.actvt_date.value = year + month + day;
	document.safeRequestForm.rsn.value = $("textarea#rsn_area").val();
	document.safeRequestForm.ctlg_cd.value = $("#activity option:selected").attr("id");
	document.safeRequestForm.action = "Add_SafeRequest.do";
	document.safeRequestForm.submit();
}

// 선택된 활동명, 코드 -> view로 출력
function fn_onClick_selectedAct(tdObj) {
	// 활동 코드, 활동명
	var code = tdObj.id;
	var name = tdObj.innerText;

	document.actName.actName_nm.value = name;
	document.actCode.actCode_cd.value = code;

	document.selectedCode.selected_code.value = code;
	document.selectedName.selected_name.value = name;
}

// 선택된 대분류 중분류 -> view로 출력
function fn_onClick_selectedActOption() {
	// 대분류 중분류
	var large = getSelectedText('searchConditionLage');
	var middle = getSelectedText('searchConditionMiddle');

	if(document.getElementById('selectedLarge'))
		document.selectedLarge.selected_large.value = large;
	
	if(document.getElementById('selectedMiddle'))
		document.selectedMiddle.selected_middle.value = middle;
}

// 긴급 승인요청
function fn_onClick_requestApproval(ctlg_cd, ctlg_itm_cd) {	
	document.requestApprovalForm.ctlg_cd.value = ctlg_cd;
	document.requestApprovalForm.ctlg_itm_cd.value = ctlg_itm_cd;
	document.requestApprovalForm.ctlg_itm_ctnt.value = $('#itm_ctnt').val();
	document.requestApprovalForm.action = "RequestApproval.do";
	document.requestApprovalForm.submit();
}

function fn_onClick_submitApproval(ctlg_cd, ctlg_itm_cd) {
	document.approvalSubmitForm.ctlg_cd.value = ctlg_cd;
	document.approvalSubmitForm.ctlg_itm_cd.value = ctlg_itm_cd;
	document.approvalSubmitForm.rsn.value = $("textarea#rsn_area").val();
	document.approvalSubmitForm.action = "Add_RequestApproval.do";
	document.approvalSubmitForm.submit();
}

function fn_go_commanderDetail(seq, cmdNum) {
	document.commanderForm.seq.value = seq;
	document.commanderForm.cmdNum.value = cmdNum;
	document.commanderForm.action = "ReservedSafeManagement_Commander_Detail.do";
	document.commanderForm.submit();
}

function fn_onClick_requestChecklist() {
	if ($("#activity option:selected").attr("id") == null || $("#activity option:selected").attr("id") == "") {
		alert("부대활동을 선택해주십시오.");
		return false;
	}
	
	if ($("textarea#rsn_area").val() == null || $("textarea#rsn_area").val() == "") {
		alert("이유를 입력해주십시오.");
		return false;
	}
	else {
		if ($("textarea#rsn_area").val().trim() == "") {
			alert("이유를 입력해주십시오.");
			return false;
		}
	}
	
	if ($("textarea#comment_area").val() == null || $("textarea#comment_area").val() == "") {
		alert("건의내용을 입력해주십시오.");
		return false;
	}
	else {
		if ($("textarea#comment_area").val().trim() == "") {
			alert("건의내용을 입력해주십시오.");
			return false;
		}
	}
	
	if($('input:checkbox[id="newCheck"]').is(":checked") == true)
		document.requestChecklistForm.new_yn.value = "1";
	else
		document.requestChecklistForm.new_yn.value = "0";
	
	document.requestChecklistForm.ctlg_cd.value = $("#activity option:selected").attr("id");
	document.requestChecklistForm.rsn.value = $("textarea#rsn_area").val();
	document.requestChecklistForm.rqst_ctnt.value = $("textarea#comment_area").val();	
	document.requestChecklistForm.ctlg_itm_cd_1.value = $("#checkListByLogined option:selected").val();
	
	document.requestChecklistForm.action = "Add_RequestChecklist.do";
	document.requestChecklistForm.submit();	
}

function fn_cmdComplete(actCode, cmdNum) {
	if($('input:checkbox[id="completeCheck"]').is(":checked") == true) {
		document.requestApprovalCmdForm.findCode.value = actCode;
		document.requestApprovalCmdForm.cmdNum.value = cmdNum;
		document.requestApprovalCmdForm.action = "CmdList_Complete.do";
		document.requestApprovalCmdForm.submit();
	}	
}

function setStatus() {
	console.log(document.getElementsByName("actCheck").length);
	
	var size = document.getElementsByName("actCheck").length;
	
	for(var i = 0; i < size; ++i)
		console.log(document.getElementsByName("actCheck")[i].value);
	
	// guidnc1
	if(document.getElementById("guidnc_1_y_chk").checked == true && document.getElementById("guidnc_1_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("guidnc_1_y_chk").checked == true)
		document.ynForm.guidnc_yn_1.value = "Y ";
	else if(document.getElementById("guidnc_1_n_chk").checked == true)
		document.ynForm.guidnc_yn_1.value = "N ";
	else
		document.ynForm.guidnc_yn_1.value = "";
	
	// guidnc2
	if(document.getElementById("guidnc_2_y_chk").checked == true && document.getElementById("guidnc_2_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("guidnc_2_y_chk").checked == true)
		document.ynForm.guidnc_yn_2.value = "Y ";
	else if(document.getElementById("guidnc_2_n_chk").checked == true)
		document.ynForm.guidnc_yn_2.value = "N ";
	else
		document.ynForm.guidnc_yn_2.value = "";
	
	// guidnc3
	if(document.getElementById("guidnc_3_y_chk").checked == true && document.getElementById("guidnc_3_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("guidnc_3_y_chk").checked == true)
		document.ynForm.guidnc_yn_3.value = "Y ";
	else if(document.getElementById("guidnc_3_n_chk").checked == true)
		document.ynForm.guidnc_yn_3.value = "N ";
	else
		document.ynForm.guidnc_yn_3.value = "";
	
	// ctlg itm 1
	if(document.getElementById("ctlg_1_y_chk").checked == true && document.getElementById("ctlg_1_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_1_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_1.value = "Y ";
	else if(document.getElementById("ctlg_1_n_chk").checked == true) {
		if($("#ctlg_1_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_1.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_1.value = "C ";
		}
	}		
	else
		document.ynForm.ctlg_itm_yn_1.value = "";
	
	// ctlg itm 2
	if(document.getElementById("ctlg_2_y_chk").checked == true && document.getElementById("ctlg_2_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_2_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_2.value = "Y ";
	else if(document.getElementById("ctlg_2_n_chk").checked == true) {
		if($("#ctlg_2_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_2.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_2.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_2.value = "";
	
	// ctlg itm 3
	if(document.getElementById("ctlg_3_y_chk").checked == true && document.getElementById("ctlg_3_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_3_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_3.value = "Y ";
	else if(document.getElementById("ctlg_3_n_chk").checked == true) {
		if($("#ctlg_3_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_3.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_3.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_3.value = "";
	
	// ctlg itm 4
	if(document.getElementById("ctlg_4_y_chk").checked == true && document.getElementById("ctlg_4_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_4_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_4.value = "Y ";
	else if(document.getElementById("ctlg_4_n_chk").checked == true) {
		if($("#ctlg_4_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_4.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_4.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_4.value = "";
	
	// ctlg itm 5
	if(document.getElementById("ctlg_5_y_chk").checked == true && document.getElementById("ctlg_5_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_5_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_5.value = "Y ";
	else if(document.getElementById("ctlg_5_n_chk").checked == true) {
		if($("#ctlg_5_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_5.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_5.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_5.value = "";
	
	// ctlg itm 6
	if(document.getElementById("ctlg_6_y_chk").checked == true && document.getElementById("ctlg_6_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_6_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_6.value = "Y ";
	else if(document.getElementById("ctlg_6_n_chk").checked == true) {
		if($("#ctlg_6_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_6.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_6.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_6.value = "";
	
	// ctlg itm 7
	if(document.getElementById("ctlg_7_y_chk").checked == true && document.getElementById("ctlg_7_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_7_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_7.value = "Y ";
	else if(document.getElementById("ctlg_7_n_chk").checked == true) {
		if($("#ctlg_7_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_7.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_7.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_7.value = "";
	
	// ctlg itm 8
	if(document.getElementById("ctlg_8_y_chk").checked == true && document.getElementById("ctlg_8_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_8_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_8.value = "Y ";
	else if(document.getElementById("ctlg_8_n_chk").checked == true) {
		if($("#ctlg_8_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_8.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_8.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_8.value = "";
	
	// ctlg itm 9
	if(document.getElementById("ctlg_9_y_chk").checked == true && document.getElementById("ctlg_9_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_9_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_9.value = "Y ";
	else if(document.getElementById("ctlg_9_n_chk").checked == true) {
		if($("#ctlg_9_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_9.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_9.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_9.value = "";
	
	// ctlg itm 10
	if(document.getElementById("ctlg_10_y_chk").checked == true && document.getElementById("ctlg_10_n_chk").checked == true) {
		alert("중복체크할 수 없습니다.");
	}		
	else if(document.getElementById("ctlg_10_y_chk").checked == true)
		document.ynForm.ctlg_itm_yn_10.value = "Y ";
	else if(document.getElementById("ctlg_10_n_chk").checked == true) {
		if($("#ctlg_10_n_chk").parent().hasClass("checks-img") === true)
			document.ynForm.ctlg_itm_yn_10.value = "N ";
		else {
			document.ynForm.ctlg_itm_yn_10.value = "C ";
		}
	}
	else
		document.ynForm.ctlg_itm_yn_10.value = "";
}

function fn_isAllChecked() {
	var size = document.getElementsByName("actCheck").length;
	
	var cnt = 0;
	var result = new Array();
	var classHasRed = new Array("", "");
	
	result[0] = false;
	result[1] = false;
	var allCheck = false;
	
	$('input:checkbox[name="actCheck"]').each(function(index, item){
		if($(item).next().hasClass("chkbox_none") === false) {			
			
			classHasRed[cnt] = $(item).parent().hasClass("checks-img-red");
			result[cnt] = $(item).is(":checked");			
			
			if(cnt % 2 != 0) {
				if(result[0] == false && result[1] == false) {
					allCheck = false;
					return false;
				}
				else if(result[1] == true) {
					if(classHasRed[1] == true) {
						allCheck = false;
						return false;
					}
				}
				else
					allCheck = true;
				
				cnt = 0;
			}
			else {
				++cnt;
			}			
		}
	})
	
	return allCheck;
}

// set yes or no status submit
function fn_set_ynStatus(seq) {	
	document.ynForm.seq.value = seq;	
	
	setStatus();
	
	if (fn_isAllChecked() == true) {
		console.log("all checked");
		document.ynForm.isAllChecked.value = "true";
	}
	else {
		console.log("is not all checked");
		document.ynForm.isAllChecked.value = "false";
	}
	
	document.ynForm.action = "CompleteSave.do";
	document.ynForm.submit();
}

function fn_set_actFinishtime(incdt_idtf_cd, incdt_actvt_type_cd, actvt_date) {	
	var size = document.getElementsByName("actCheck").length;
	
	for(var i = 0; i < size; i += 2) {
		if(document.getElementsByName("actCheck")[i].checked == false && document.getElementsByName("actCheck")[i+1].checked == false) {
			alert("실시여부를 등록하지 않았거나, 검토중인 항목이 있습니다.");
			return false;
		}		
	}
	
	// 실시여부가 모두 체크되어있으면 submit
	setStatus();

	document.ynForm.incdt_idtf_cd.value = incdt_idtf_cd;
	document.ynForm.incdt_actvt_type_cd.value = incdt_actvt_type_cd;
	document.ynForm.actvt_date.value = actvt_date;
	document.ynForm.action = "Activity_Finish.do";
	document.ynForm.submit();
}

function fn_go_rqstActDetail(obj) {
	var seq = obj.value;
	location.href = "RequestActivity_Detail.do?seq=" + seq;
}

function fn_go_rqstChkDetail(obj) {
	var seq = obj.value;
	location.href = "RequestChecklist_Detail.do?seq=" + seq;
}

function fn_go_CheckApprovalDeatale_seq(obj){
	var seq = obj;
	location.href = "CheckApprovalResult.do?seq=" +seq;
}
function fn_go_CheckReqIncdt_seq(obj){
	var seq = obj;
	location.href = "CheckOccasionalSafeManagement_Select.do?seq="+seq;
}
function fn_go_CheckReqCheck_seq(obj){
	var seq = obj;
	location.href = "CheckRequestSelect.do?seq="+seq;
}
function clickTdEvent(tdObj) {
	var Name = tdObj.innerText;
	var Code = tdObj.id;
	document.selectedCode.selected_code.value=Code;
	document.selectedName.selected_name.value=Name;
}

function selectedEvent(large, large_nm, middle, middle_nm) {
	if(large_nm != undefined) {
		document.selectedLarge.largeCode.value=large;
		document.selectedLarge.selected_large.value=large_nm;
	}
	
	if(middle_nm != undefined) {
		document.selectedMiddle.middleCode.value=middle;
		document.selectedMiddle.selected_middle.value=middle_nm;
	}	
}

function fn_go_rqstActDetail_seq(_seq) {
	var seq = _seq;
	location.href = "RequestActivity_Detail.do?seq=" + seq;
}

function fn_go_rqstChkDetail_seq(_seq) {
	var seq = _seq;
	location.href = "RequestChecklist_Detail.do?seq=" + seq;
}

function fn_search_troops(pageNo) {
	
	var year = $("#activityYear option:selected").val();
	var month = $("#activityMonth option:selected").val();
	var day = $("#activityDay option:selected").val();

	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}

	document.troopsForm.date.value = year + month + day;
	
	document.troopsForm.currentPageNo.value = pageNo;
	document.troopsForm.action = "TroopsMonitoring_Search.do";
	document.troopsForm.submit();
}

function fn_search_occasion(pageNo) {	
	var today = getParameterByName("date");
	var search_nm = getParameterByName("search_nm");
	var state_cd = getParameterByName("state_cd");
	
	location.href="OccasionalSafeManagement.do?do=1&date=" + today + "&search_nm=" + encodeURI(search_nm) + "&state_cd=" + state_cd +"&currentPageNo=" + pageNo;
}

function fn_search_checklist(pageNo) {	
	var today = getParameterByName("date");
	var search_nm = getParameterByName("search_nm");
	var state_cd = getParameterByName("state_cd");
	
	location.href="CheckListManagement.do?do=1&date=" + today + "&search_nm=" + encodeURI(search_nm) + "&state_cd=" + state_cd +"&currentPageNo=" + pageNo;
}

function fn_search_reserved(pageNo) {	
	var today = getParameterByName("date");
	var search_nm = getParameterByName("search_nm");
	var state_cd = getParameterByName("state_cd");
	
	location.href="ReservedSafeManagement.do?do=1&date=" + today + "&search_nm=" + encodeURI(search_nm) + "&state_cd=" + state_cd +"&currentPageNo=" + pageNo;
}
function fn_link_User(pageNo){
	document.pageForm.currentPageNo.value = pageNo;
	document.pageForm.action = "UserList.do";
	document.PageForm.submit();
}
function fn_checkApp_page(pageNo){
	document.checkAppForm.currentPageNo.value = pageNo;
	document.checkAppForm.action = "CheckApproval.do";
	document.checkAppForm.submit();
}
function fn_checkOcc_page(pageNo){
	document.checkOccForm.currentPageNo.value = pageNo;
	document.checkOccForm.action = "CheckOccasionalSafeManagement.do";
	document.checkOccForm.submit();
}
function fn_checkList_page(pageNo){
	document.checkListForm.currentPageNo.value = pageNo;
	document.checkListForm.action = "CheckRequestedCheckList.do";
	document.checkListForm.submit();
}
function fn_go_taskList(actId, srvno) {
	var seq = 0;
	
	if (document.getElementById("taskSeq")) {
		seq =  $("#taskSeq").val();
		document.checklistForm.seq.value = seq;
	}
	
	document.checklistForm.srvno.value = srvno;
	document.checklistForm.actId.value = actId;
	document.checklistForm.action = "TroopsMonitoring_Task_Detail.do";
	document.checklistForm.submit();
}

function fn_go_rqstUgcyDetail(actvt_date, ctlg_itm_cd, srvno) {
	location.href = "RequestApproval_Detail.do?date=" + actvt_date + "&item=" + ctlg_itm_cd + "&srvno=" + srvno;
}

function preventDefaultAction(rtnValue) {
	if (!rtnValue) {
		if (typeof event.preventDefault != 'undefined') {
			event.preventDefault(); // W3C
		} else {
			event.returnValue = false; // IE
		}
	}
	return rtnValue;
}

function fn_search_task() {
	var year = $("#activityYear option:selected").val();
	var month = $("#activityMonth option:selected").val();
	var day = $("#activityDay option:selected").val();

	if (month != null && month != "") {
		if (month < 10) {
			month = "0" + month;
		}
	} 
	
	if (day != null && day != "") {
		if (day < 10) {
			day = "0" + day;
		}
	}	
	
	var today = year + month + day;
	var search_nm = $("#search_nm").val();
	var state_cd = $("#select_state option:selected").val();
	
	if (today.length < 8 && today.length > 0) {
		alert("년 / 월 / 일 모두 선택해 주십시오.");
		return false;
	}
	
	location.href="ReservedSafeManagement.do?do=1&date=" + today + "&search_nm=" + encodeURI(search_nm) + "&state_cd=" + state_cd;
}

function fn_search_rqstAct() {
	var year = $("#activityYear option:selected").val();
	var month = $("#activityMonth option:selected").val();
	var day = $("#activityDay option:selected").val();

	if (month != null && month != "") {
		if (month < 10) {
			month = "0" + month;
		}
	} 
	
	if (day != null && day != "") {
		if (day < 10) {
			day = "0" + day;
		}
	}
	
	var today = year + month + day;
	var search_nm = $("#search_nm").val();
	var state_cd = $("#select_state option:selected").val();
	
	if (today.length < 8 && today.length > 0) {
		alert("년 / 월 / 일 모두 선택해 주십시오.");
		return false;
	}
	
	location.href="OccasionalSafeManagement.do?do=1&date=" + today + "&search_nm=" + encodeURI(search_nm) + "&state_cd=" + state_cd;
}

function fn_search_rqstChk() {
	var year = $("#activityYear option:selected").val();
	var month = $("#activityMonth option:selected").val();
	var day = $("#activityDay option:selected").val();	

	if (month != null && month != "") {
		if (month < 10) {
			month = "0" + month;
		}
	} 
	
	if (day != null && day != "") {
		if (day < 10) {
			day = "0" + day;
		}
	}
	
	var today = year + month + day;
	var search_nm = $("#search_nm").val();
	var state_cd = $("#select_state option:selected").val();
	
	if (today.length < 8 && today.length > 0) {
		alert("년 / 월 / 일 모두 선택해 주십시오.");
		return false;
	}
	
	location.href="CheckListManagement.do?do=1&date=" + today + "&search_nm=" + encodeURI(search_nm) + "&state_cd=" + state_cd;
}