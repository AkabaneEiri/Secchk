function fn_egov_link_page(pageNo) {
	$("input[name='pageIndex']").val(pageNo);
	$("#flag").val("0");
	$("form[name='Frm']").submit();
}	

function fn_link_page(pageNo){
	document.pageForm.action = "Userlist.do?pageIndex=" + pageNo;
	document.pageForm.submit();
}