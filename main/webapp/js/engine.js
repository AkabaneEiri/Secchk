// 숫자만 입력받는 함수
function fn_onlyNum(event, type) {
	if (type == "numbers") {
		if (event.keyCode < 48 || event.keyCode > 57)
			return false;
	}
}

// 한글입력 방지 함수
function fn_notKor(obj) {
	if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46)
		return;
	obj.value = obj.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
}

// 입력 길이 제한
function fn_maxLength(obj, maxLen) {
	var strLen = obj.value.length;
	
	if (strLen >= maxLen) {
		if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46)
			return;
		alert("입력은 "+ maxLen +"자를 초과할 수 없습니다.");
		obj.value = obj.value.substr(0, maxLen-1);
		return false;
	}
}

//한글 + 영문 입력 방지 함수
function fn_notKorEng(obj) {
	if (event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46)
		return;
	obj.value = obj.value.replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g, '');
	obj.value = obj.value.replace(/[\A-Za-z]/g, '');
}
//한글만 입력받는 함수
function fn_onlykor(obj){
	obj.value = obj.value.replace(/[\A-Za-z]/g, '');
}