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
	
	document.getElementById("searchKeyword").value = Code;

	parent.Member_Select(tdObj);
}
/////////////////////////////////////////////
//사용자검색 모달용 펑션
/////////////////////////////////////////////
function Member_Search(){
	var searchvalue = document.getElementById("searchKeyword").value;
	var searchCondition = document.getElementById("searchCondition");
	var ConditionSelected = searchCondition.options[searchCondition.selectedIndex].value;
	if (searchvalue.value == "") 
	{
		alert("검색어를 입력해주시기 바랍니다.");
		return false;
	}
	else
	{
		var code = new Object();
		code.searchCondition = ConditionSelected;
		code.searchvalue = searchvalue;
		
		var jsonString = JSON.stringify(code);
		
		$.ajax({
				url:"MemberSearch_AJAX.do",
				type:'post',
				data:{"jsonString":jsonString},
				success: whenSuccessUserSearch,
				error:function(request,status,error){
			        console.log("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			       }
				})
	}
}
function whenSuccessUserSearch(res)
{
	console.log(res);
	$("#table_hover>tbody:last>tr").remove();
	var obj = JSON.parse(res);
	var str;
	if(obj[0].srvno =='E001')
		{
		$.each(obj,function(i){
			str += '<tr>';
			str += '<td style="text-align: left; font-size 15px">데이터가 없습니다.</td>';
			str += '</tr>';
		});
		}
	else
		{
			$.each(obj,function(i){
				str +=	'<tr id="'+obj[i].stmt+'" name="'+obj[i].srvno+'" value="'+obj[i].srvno+'" onclick="javascript:clickTdEvent(this)" style="background-color: rgb(216, 216, 216);">';
				str += 	'<td style="text-align: left; font-size 15px">'+obj[i].srvno+'</td>';
				str +=	'<td style="text-align: left; font-size 15px">'+obj[i].cd_nm+'</td>';
				str +=	'<td style="text-align: left; font-size 15px">'+obj[i].stmt+'</td>';
				str +=	'<td style="text-align: left; font-size 15px">'+obj[i].rspofc_nm+'</td>';
			});
		}
	$('#table_hover > tbody').append(str);
	}
function whenErrorUserSearch(res)
{
	alert("일시적인 오류가 발생하였습니다.");
	console.log(res);
}

/////////////////////////////////////////////
//모달용 펑션 끝
/////////////////////////////////////////////

//#D8D8D8