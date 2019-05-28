function fn_checkSame() {
	if ($("#pw_new").val().trim() != $("#pw_new_confirm").val().trim()) {
		alert("변경할 비밀번호가 일치하지 않습니다. 다시 확인해주십시오.");
		return false;
	}
}