	function access(){
		var iframe = document.getElementById("iframe");
		var innerDoc = iframe.contentDocument || iframe.contentWindow.document;
		console.log(innerDoc.body)
	}

	function clickTdEvent(tdObj){
		
		var Name = tdObj.innerText;
		var Code = tdObj.id;

		document.InsertForm.incdt_idtf_cd.value=Name;
//		document.TaskSearch.searchCode.value=Code;	
	}
function Insert_Cancle(){

	document.write("");
	
	window.location.replace('Userlist.do');	
}
function Insert_Submit()
{
	var stmt 						=	$("input[name=stmt]").val();
	var rspofc_nm 			= $("input[name=rspofc_nm]").val();
	var srvno 						= $("input[name=srvno]").val();
	var password 				= $("input[name=password]").val();
	var rnkcd 						= $("select[name=rnkcd]").val();
	var password_check	= $("input[name=password_check]").val();		
	var prtbl_telno 			= $("input[name=prtbl_telno]").val();
	var incdt_idtf_cd 			= $("input[name=incdt_idtf_cd]").val();
	var athrt 				= $("select[name=athrt]").val();
	var montr 			= $("select[name=montr]").val();
	
	 if (password != password_check)
		{
		alert("비밀번호가 일치하지 않습니다.");
			return false;
		}	
	 else if
		(
				stmt==""||
				rspofc_nm==""||
				srvno==""||
				password==""||
				rnkcd==""||
				password_check==""||
				prtbl_telno==""||
				incdt_idtf_cd==""||
				athrt==""
					)
			{
			alert("공란 없이 작성해 주십시오");
				return false;
			}
	else 
	{
		if(confirm("해당 정보로 관리자를 등록하시겠습니까?"))
		{
			document.write("");
			location.href="Member_Insert_Result.do?&stmt="+stmt+"&rspofc_nm="+rspofc_nm+"&srvno="+srvno+"&password="+password+"&rnkcd="+rnkcd+"&prtbl_telno="+prtbl_telno+
			"&incdt_idtf_cd="+incdt_idtf_cd+"&athrt="+athrt+"&montr="+montr;
		}
		else
			{
			return false;
			}
		
	}

}			


