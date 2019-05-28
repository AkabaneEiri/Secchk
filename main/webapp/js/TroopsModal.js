function clickTdEvent(tdObj){
		
		var Name = tdObj.innerText;
		var Code = tdObj.id;
		var table = document.getElementById("table_hover");
		var tr = table.getElementsByTagName("td");
		
		for(var i=0; i<tr.length; i++){
			tr[i].style.backgroundColor = "#f4f4f4";
		}
		
		tdObj.style.backgroundColor = "#D8D8D8";
		
		parent.clickTdEvent(tdObj);
		Code.style.backgroundColor = "gray";
}