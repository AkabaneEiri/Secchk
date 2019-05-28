function Member_Insert(){
	location.href="member_insert.do";
	
}

//function Member_Update(){
function Member_Update(srvno){
	//var srvno = event.srcElement.id;

	document.write("");
	document.location.href="member_modify.do?srvno="+ srvno;
}

function Member_Delete(srvno){
		
	//var srvno = event.srcElement.id;
	var con_delete = confirm("정말 삭제하시겠습니까?");
	if(con_delete == true){
		document.memberlist.action = "DeleteMember.do?srvno="+srvno;
		document.memberlist.submit();
	}
	else
		{
		alert("취소하셨습니다");
		document.write("");
		window.location.replace('Userlist.do');	
		
		}	
	
}
function fn_egov_link_page(pageNo) {
	$("input[name='pageIndex']").val(pageNo);
	$("#flag").val("0");
}	
