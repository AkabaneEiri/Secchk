function clickTdEvent(tdObj){
		
		var Name = tdObj.innerText;
		var Code = tdObj.id;
		var table = document.getElementById("table_hover");
		var tr = table.getElementsByTagName("td");
		var textBox = document.getElementById("TroopsSearch");
		
		for(var i=0; i<tr.length; i++){
			tr[i].style.backgroundColor = "#f4f4f4";
		}
		
		tdObj.style.backgroundColor = "#D8D8D8";
		
		textBox.value = Name;
		
//		parent.clickTdEvent(tdObj);
}

function Troops_Search(){
	var textBox = document.getElementById("TroopsSearch");
	var Name = textBox.value;
	var Code = new Object();
	
	Code.Name = Name;
	
	var jsonString = JSON.stringify(Code);
	
	$.ajax({
			url		: "TroopsSearchAjax.do",
			type	: "post",
			data	: {"jsonString":jsonString},
			success	: whenSuccess,
			error	: whenError
	})
}
function whenSuccess(res){
	console.log(res);
	$("#table_hover>tbody:last > tr").remove();
	var obj = JSON.parse(res);
	var str = '';
	if(obj[0].ctlg_cd=="E001")
		{
			$.each(obj, function(i){
				str+= '<tr>';
				str += '<td style="text-align:center;">데이터가 없습니다.</td>';
				str += '</tr>';
			});
		}
	else
		{
			$.each(obj, function(i){
				str+= '<tr>';
				str += '<td style="text-align:left;" onclick="javascript:clickTdEvent(this)">'+obj[i].incdt_nm+'</td>';
				str += '</tr>';
			});
		}
	console.log(str);
	$('#table_hover > tbody').append(str);
}
function whenError(res)
{
	alert("일시적인 오류가 발생하였습니다.");
	console.log(res)}
