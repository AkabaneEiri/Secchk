$(document).ready(function(){
	var condition = getParameterByName("searchCondition");
	if( getParameterByName("searchvalue") == "")
		{
			condition = "00";
		}
	$("#searchCondition").val(condition).attr("selected", "true");
	
	var athrt = getParameterByName("searchAthrt");
	if( getParameterByName("searchAthrt") == "") {
		athrt = "B1";
	}
	if (document.getElementById("searchAthrt") != null) {
		$("#searchAthrt").val(athrt).attr("selected", "true");
	}	
});

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function Member_Insert(){
	location.href="member_insert.do";
	
}

//function Member_Update(){
function Member_Update(srvno){
	//var srvno = event.srcElement.id;

	document.location.href="member_modify.do?srvno="+ encodeURI(srvno);
}

function Member_Delete(srvno){
		
	//var srvno = event.srcElement.id;
	var con_delete = confirm("정말 삭제하시겠습니까?");
	if(con_delete == true){
		document.location.href= "DeleteMember.do?srvno="+encodeURI(srvno);
	}
	else
		{
		alert("취소하셨습니다");
		return false;
		
		}	
	
}
function fn_egov_link_page(pageNo) {
	$("input[name='pageIndex']").val(pageNo);
	$("#flag").val("0");
}	

function Member_Search_ex() {
	var searchvalue = document.getElementById("searchKeyword").value;
	var searchCondition = document.getElementById("searchCondition");
	var ConditionSelected = searchCondition.options[searchCondition.selectedIndex].value;
	
	// 190520 add by seungwon ///////////
	var searchAthrt = $("#searchAthrt option:selected").val(); // b1 or b2 or b3
	if (searchAthrt == undefined) { searchAthrt = ""; }
	/////////////////////////////////////
	
	if (searchvalue.value == "") 
	{
		alert("검색어를 입력해주시기 바랍니다.");
		return false;
	}
	else
	{
		location.href="Userlist.do?searchvalue="+encodeURI(searchvalue)+"&searchCondition="+encodeURI(ConditionSelected) + "&searchAthrt=" + encodeURI(searchAthrt);
	}
}
function whenErrorUserSearch(res)
{
alert("error!");
console.log(res);
}

function fn_Userlist_page(pageNo) {
	// 190520 add by seungwon ///////////
	var searchvalue = getParameterByName("searchvalue");
	var ConditionSelected = getParameterByName("searchCondition");
	var searchAthrt = getParameterByName("searchAthrt"); // b1 or b2 or b3
	/////////////////////////////////////
	
	document.checkAppForm.currentPageNo.value = pageNo;
	document.checkAppForm.action = "Userlist.do?searchvalue="+encodeURI(searchvalue)+"&searchCondition="+encodeURI(ConditionSelected) + "&searchAthrt=" + encodeURI(searchAthrt) + "&pageNo=" + pageNo;
	document.checkAppForm.submit();
}