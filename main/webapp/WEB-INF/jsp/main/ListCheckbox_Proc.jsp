<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions"%> 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script>
$(document).ready(function(){
	
	// display none set
	if(${taskData.guidnc_1 != null && taskData.guidnc_1 != ''}) {
		$("#label_gui_1_y").removeClass("chkbox_none");
		$("#label_gui_1_n").removeClass("chkbox_none");
	}
	if(${taskData.guidnc_2 != null && taskData.guidnc_2 != ''}) {
		$("#label_gui_2_y").removeClass("chkbox_none");
		$("#label_gui_2_n").removeClass("chkbox_none");
	}
	if(${taskData.guidnc_3 != null && taskData.guidnc_3 != ''}) {
		$("#label_gui_3_y").removeClass("chkbox_none");
		$("#label_gui_3_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_1 != null && taskData.ctlg_itm_cd_1 != ''}) {
		$("#label_1_y").removeClass("chkbox_none");
		$("#label_1_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_2 != null && taskData.ctlg_itm_cd_2 != ''}) {
		$("#label_2_y").removeClass("chkbox_none");
		$("#label_2_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_3 != null && taskData.ctlg_itm_cd_3 != ''}) {
		$("#label_3_y").removeClass("chkbox_none");
		$("#label_3_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_4 != null && taskData.ctlg_itm_cd_4 != ''}) {
		$("#label_4_y").removeClass("chkbox_none");
		$("#label_4_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_5 != null && taskData.ctlg_itm_cd_5 != ''}) {
		$("#label_5_y").removeClass("chkbox_none");
		$("#label_5_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_6 != null && taskData.ctlg_itm_cd_6 != ''}) {
		$("#label_6_y").removeClass("chkbox_none");
		$("#label_6_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_7 != null && taskData.ctlg_itm_cd_7 != ''}) {
		$("#label_7_y").removeClass("chkbox_none");
		$("#label_7_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_8 != null && taskData.ctlg_itm_cd_8 != ''}) {
		$("#label_8_y").removeClass("chkbox_none");
		$("#label_8_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_9 != null && taskData.ctlg_itm_cd_9 != ''}) {
		$("#label_9_y").removeClass("chkbox_none");
		$("#label_9_n").removeClass("chkbox_none");
	}
	if(${taskData.ctlg_itm_cd_10 != null && taskData.ctlg_itm_cd_10 != ''}) {
		$("#label_10_y").removeClass("chkbox_none");
		$("#label_10_n").removeClass("chkbox_none");
	}
		
	
	// commanderlist ////////////////////////////////////////////////////////////////////
	if(${fn:trim(taskData.guidnc_yn_1) == 'Y'})
		$("input:checkbox[id='guidnc_1_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.guidnc_yn_1) == 'N'})
		$("input:checkbox[id='guidnc_1_n_chk']").prop('checked', true);
	else if(${fn:trim(taskData.guidnc_yn_1) == 'C'}) {
		$("input:checkbox[id='guidnc_1_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='guidnc_1_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.guidnc_yn_2) == 'Y'})
		$("input:checkbox[id='guidnc_2_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.guidnc_yn_2) == 'N'})
		$("input:checkbox[id='guidnc_2_n_chk']").prop('checked', true);
	else if(${fn:trim(taskData.guidnc_yn_2) == 'C'}) {
		$("input:checkbox[id='guidnc_2_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='guidnc_2_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.guidnc_yn_3) == 'Y'})
		$("input:checkbox[id='guidnc_3_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.guidnc_yn_3) == 'N'})
		$("input:checkbox[id='guidnc_3_n_chk']").prop('checked', true);
	else if(${fn:trim(taskData.guidnc_yn_3) == 'C'}) {
		$("input:checkbox[id='guidnc_3_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='guidnc_3_n_chk']").prop('disabled', 'disabled');
	}
	
	// checklist ////////////////////////////////////////////////////////////////////
	if(${fn:trim(taskData.ctlg_itm_yn_1) == 'Y'})
		$("input:checkbox[id='ctlg_1_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_1) == 'N'}) {
		$("input:checkbox[id='ctlg_1_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_1_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_1_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_1_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_1_n_chk']").prop('checked', true);
	}		
	else if(${fn:trim(taskData.ctlg_itm_yn_1) == 'C'}) {	// check red
		$("input:checkbox[id='ctlg_1_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_1_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_1_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_2) == 'Y'})
		$("input:checkbox[id='ctlg_2_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_2) == 'N'}) {
		$("input:checkbox[id='ctlg_2_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_2_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_2_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_2_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_2_n_chk']").prop('checked', true);
	}
	else if(${fn:trim(taskData.ctlg_itm_yn_2) == 'C'}) {
		$("input:checkbox[id='ctlg_2_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_2_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_2_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_3) == 'Y'})
		$("input:checkbox[id='ctlg_3_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_3) == 'N'}) {
		$("input:checkbox[id='ctlg_3_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_3_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_3_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_3_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_3_n_chk']").prop('checked', true);
	}		
	else if(${fn:trim(taskData.ctlg_itm_yn_3) == 'C'}) {
		$("input:checkbox[id='ctlg_3_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_3_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_3_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_4) == 'Y'})
		$("input:checkbox[id='ctlg_4_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_4) == 'N'}) {
		$("input:checkbox[id='ctlg_4_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_4_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_4_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_4_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_4_n_chk']").prop('checked', true);
	}				
	else if(${fn:trim(taskData.ctlg_itm_yn_4) == 'C'}) {
		$("input:checkbox[id='ctlg_4_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_4_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_4_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_5) == 'Y'})
		$("input:checkbox[id='ctlg_5_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_5) == 'N'}) {
		$("input:checkbox[id='ctlg_5_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_5_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_5_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_5_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_5_n_chk']").prop('checked', true);
	}			
	else if(${fn:trim(taskData.ctlg_itm_yn_5) == 'C'}) {
		$("input:checkbox[id='ctlg_5_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_5_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_5_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_6) == 'Y'})
		$("input:checkbox[id='ctlg_6_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_6) == 'N'}) {
		$("input:checkbox[id='ctlg_6_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_6_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_6_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_6_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_6_n_chk']").prop('checked', true);
	}				
	else if(${fn:trim(taskData.ctlg_itm_yn_6) == 'C'}) {
		$("input:checkbox[id='ctlg_6_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_6_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_6_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_7) == 'Y'})
		$("input:checkbox[id='ctlg_7_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_7) == 'N'}) {
		$("input:checkbox[id='ctlg_7_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_7_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_7_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_7_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_7_n_chk']").prop('checked', true);
	}		
	else if(${fn:trim(taskData.ctlg_itm_yn_7) == 'C'}) {
		$("input:checkbox[id='ctlg_7_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_7_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_7_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_8) == 'Y'})
		$("input:checkbox[id='ctlg_8_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_8) == 'N'}) {
		$("input:checkbox[id='ctlg_8_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_8_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_8_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_8_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_8_n_chk']").prop('checked', true);
	}		
	else if(${fn:trim(taskData.ctlg_itm_yn_8) == 'C'}) {
		$("input:checkbox[id='ctlg_8_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_8_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_8_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_9) == 'Y'})
		$("input:checkbox[id='ctlg_9_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_9) == 'N'}) {
		$("input:checkbox[id='ctlg_9_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_9_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_9_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_9_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_9_n_chk']").prop('checked', true);
	}				
	else if(${fn:trim(taskData.ctlg_itm_yn_9) == 'C'}) {
		$("input:checkbox[id='ctlg_9_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_9_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_9_n_chk']").prop('disabled', 'disabled');
	}
	
	if(${fn:trim(taskData.ctlg_itm_yn_10) == 'Y'})
		$("input:checkbox[id='ctlg_10_y_chk']").prop('checked', true);
	else if(${fn:trim(taskData.ctlg_itm_yn_10) == 'N'}) {
		$("input:checkbox[id='ctlg_10_n_chk']").parent().removeClass("checks-img-red");
		$("input:checkbox[id='ctlg_10_n_chk']").parent().addClass("checks-img");
		$("input:checkbox[id='ctlg_10_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_10_n_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_10_n_chk']").prop('checked', true);
	}					
	else if(${fn:trim(taskData.ctlg_itm_yn_10) == 'C'}) {
		$("input:checkbox[id='ctlg_10_n_chk']").prop('checked', true);
		$("input:checkbox[id='ctlg_10_y_chk']").prop('disabled', 'disabled');
		$("input:checkbox[id='ctlg_10_n_chk']").prop('disabled', 'disabled');
	}
	
	// task end, just looking for
	var size = document.getElementsByName("actCheck").length;
	
	if (${fn:trim(taskData.state_cd) == 'E3'}) {
		for(var i = 0; i < size; ++i) {
			document.getElementsByName("actCheck")[i].disabled = true;
		}
	}
	
	// checkbox click event - check another	
	document.getElementsByName("actCheck")[0].onclick = function(){
		if (document.getElementsByName("actCheck")[1].checked == true)
			document.getElementsByName("actCheck")[1].checked = false;
	}
	document.getElementsByName("actCheck")[1].onclick = function(){
		if (document.getElementsByName("actCheck")[0].checked == true)
			document.getElementsByName("actCheck")[0].checked = false;
	}	
	document.getElementsByName("actCheck")[2].onclick = function(){
		if (document.getElementsByName("actCheck")[3].checked == true)
			document.getElementsByName("actCheck")[3].checked = false;
	}
	document.getElementsByName("actCheck")[3].onclick = function(){
		if (document.getElementsByName("actCheck")[2].checked == true)
			document.getElementsByName("actCheck")[2].checked = false;
	}
	document.getElementsByName("actCheck")[4].onclick = function(){
		if (document.getElementsByName("actCheck")[5].checked == true)
			document.getElementsByName("actCheck")[5].checked = false;
	}
	document.getElementsByName("actCheck")[5].onclick = function(){
		if (document.getElementsByName("actCheck")[4].checked == true)
			document.getElementsByName("actCheck")[4].checked = false;
	}
	document.getElementsByName("actCheck")[6].onclick = function(){
		if (document.getElementsByName("actCheck")[7].checked == true)
			document.getElementsByName("actCheck")[7].checked = false;
	}
	document.getElementsByName("actCheck")[7].onclick = function(){
		if (document.getElementsByName("actCheck")[6].checked == true)
			document.getElementsByName("actCheck")[6].checked = false;
	}
	document.getElementsByName("actCheck")[8].onclick = function(){
		if (document.getElementsByName("actCheck")[9].checked == true)
			document.getElementsByName("actCheck")[9].checked = false;
	}
	document.getElementsByName("actCheck")[9].onclick = function(){
		if (document.getElementsByName("actCheck")[8].checked == true)
			document.getElementsByName("actCheck")[8].checked = false;
	}
	document.getElementsByName("actCheck")[10].onclick = function(){
		if (document.getElementsByName("actCheck")[11].checked == true)
			document.getElementsByName("actCheck")[11].checked = false;
	}
	document.getElementsByName("actCheck")[11].onclick = function(){
		if (document.getElementsByName("actCheck")[10].checked == true)
			document.getElementsByName("actCheck")[10].checked = false;
	}
	document.getElementsByName("actCheck")[12].onclick = function(){
		if (document.getElementsByName("actCheck")[13].checked == true)
			document.getElementsByName("actCheck")[13].checked = false;
	}
	document.getElementsByName("actCheck")[13].onclick = function(){
		if (document.getElementsByName("actCheck")[12].checked == true)
			document.getElementsByName("actCheck")[12].checked = false;
	}
	document.getElementsByName("actCheck")[14].onclick = function(){
		if (document.getElementsByName("actCheck")[15].checked == true)
			document.getElementsByName("actCheck")[15].checked = false;
	}
	document.getElementsByName("actCheck")[15].onclick = function(){
		if (document.getElementsByName("actCheck")[14].checked == true)
			document.getElementsByName("actCheck")[14].checked = false;
	}
	document.getElementsByName("actCheck")[16].onclick = function(){
		if (document.getElementsByName("actCheck")[17].checked == true)
			document.getElementsByName("actCheck")[17].checked = false;
	}
	document.getElementsByName("actCheck")[17].onclick = function(){
		if (document.getElementsByName("actCheck")[16].checked == true)
			document.getElementsByName("actCheck")[16].checked = false;
	}
	document.getElementsByName("actCheck")[18].onclick = function(){
		if (document.getElementsByName("actCheck")[19].checked == true)
			document.getElementsByName("actCheck")[19].checked = false;
	}
	document.getElementsByName("actCheck")[19].onclick = function(){
		if (document.getElementsByName("actCheck")[18].checked == true)
			document.getElementsByName("actCheck")[18].checked = false;
	}
	document.getElementsByName("actCheck")[20].onclick = function(){
		if (document.getElementsByName("actCheck")[21].checked == true)
			document.getElementsByName("actCheck")[21].checked = false;
	}
	document.getElementsByName("actCheck")[21].onclick = function(){
		if (document.getElementsByName("actCheck")[20].checked == true)
			document.getElementsByName("actCheck")[20].checked = false;
	}
	document.getElementsByName("actCheck")[22].onclick = function(){
		if (document.getElementsByName("actCheck")[23].checked == true)
			document.getElementsByName("actCheck")[23].checked = false;
	}
	document.getElementsByName("actCheck")[23].onclick = function(){
		if (document.getElementsByName("actCheck")[22].checked == true)
			document.getElementsByName("actCheck")[22].checked = false;
	}
	document.getElementsByName("actCheck")[24].onclick = function(){
		if (document.getElementsByName("actCheck")[25].checked == true)
			document.getElementsByName("actCheck")[25].checked = false;
	}
	document.getElementsByName("actCheck")[25].onclick = function(){
		if (document.getElementsByName("actCheck")[24].checked == true)
			document.getElementsByName("actCheck")[24].checked = false;
	}
});
</script>