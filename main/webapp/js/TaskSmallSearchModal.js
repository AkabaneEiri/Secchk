$(document).ready(function(){
 var Name = parent.getParameterByName('Name');
 var code = new Object();
 code.Name = Name;
 
 var jsonString = JSON.stringify(code);
 $.ajax({
	 	url: "TaskSmallSearch.do",															//request보낼 경로
	 	type: "post",																		//메소드(post로 적용)
	 	data: {"jsonString":jsonString}, 													//보낼 데이터
	 	success : whenSuccessLoad,
	 	//성공 시 값 보낼 펑션
	 	error: whenError
});
});
function clickTdEvent1(tdObj){
	
	var Code = tdObj.id;
	var Name = tdObj.innerText;
	var ColorChange;
	
	var table = document.getElementById("table_hover");
	var tr = table.getElementsByTagName("td");
	
	for(var i=0; i<tr.length; i++){
		tr[i].style.backgroundColor = "#f4f4f4";
	}
	
	tdObj.style.backgroundColor = "#D8D8D8";
	
	document.listForm.searchKeyword.value = Name;
	
	parent.clickTrEvent(tdObj);
}

function whenSuccessLoad(res){
//	alert(res);
	jQuery("#table_hover>tbody:last>tr").remove();
	var obj = JSON.parse(res);
	var OptionBox = document.getElementById("table_hover");
	var str = '<tr>';

	jQuery.each(obj, function(i){
		str +='<td style="text-align:left;"id = ' + obj[i].seq + ' onclick="javascript:clickTdEvent1(this)">'+ obj[i].task_nm +'</td>';
//		str += 'id='+obj[i].seq + ' value='+obj[i].task_nm+'><td style="text-align:left;">'
		str += '</tr>';
	})
	
	jQuery("#table_hover").append(str);
}
function whenError(){
	alert("Error!!");
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