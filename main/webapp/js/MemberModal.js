function clickTdEvent(tdObj){
	
	var Code = tdObj.id;
	var srvno = tdObj.attributes.value.value;
	var ColorChange;
	
	var table = document.getElementById("table_hover");
	var tr = table.getElementsByTagName("tr");
	
	for(var i=0; i<tr.length; i++){
		tr[i].style.backgroundColor = "#f4f4f4";
	}
	
	tdObj.style.backgroundColor = "#D8D8D8";
	
	document.listForm.searchKeyword.value = Code;
	parent.clickTdEvent(tdObj);
}
/////////////////////////////////////////////
//사용자검색 모달용 펑션
/////////////////////////////////////////////
function Member_Search(){
	var searchCondition;
	var searchKeyword;
	searchCondition = document.listForm.searchCondition.value;
	searchKeyword = document.listForm.searchKeyword.value;
}
/////////////////////////////////////////////
//모달용 펑션 끝
/////////////////////////////////////////////

//#D8D8D8