
//////////////////////////////////////////
//	모달용 펑션 입니다.
//////////////////////////////////////////
function clickTdEvent(tdObj){
	
	var Name = tdObj.innerText;
	var Code = tdObj.id;
	document.TaskName.TaskName_name.value=Name;
	document.TaskCode.TaskCode_name.value=Code;
	var i;
	
	var table = document.getElementById("table_hover");
	var tr = table.getElementsByTagName("td");
	
	for(var i=0; i<tr.length; i++){
		tr[i].style.backgroundColor = "#f4f4f4";
	}
	
	tdObj.style.backgroundColor = "#D8D8D8";
	
	// add
	var Large = $("#searchConditionLage option:selected").val();
	var Large_nm = $("#searchConditionLage option:selected").attr('name');
	var Middle = $("#searchConditionMiddle option:selected").val();
	var Middle_nm = $("#searchConditionMiddle option:selected").attr('name');
	
		parent.clickTdEvent(tdObj);
		
		
		parent.selectedEvent(Large, Large_nm, Middle, Middle_nm);
}

function Search_TaskName(){
	var code = new Object();
	var TaskNameSearch = document.NameForm.TaskNameSearch.value;
	code.TaskNameSearch = TaskNameSearch;
	
	var jsonString = JSON.stringify(code);
	
	 $.ajax({
		 	url: "GroupNameSearchName.do?TaskNameSearch="+TaskNameSearch,
		 	type: "post",
		 	data: {"jsonString":jsonString}, 
		 	success : whenSuccessSearch,
		 	error: whenError
	 });		 	
}
function Search_TaskCode(){
	var code = new Object();
	var TaskNameSearch = document.NameForm.TaskCodeSearch.value;
	code.TaskNameCode = TaskNameSearch;
	
	var jsonString = JSON.stringify(code);
	
	 $.ajax({
		 	url: "GroupNameSearchCode.do?TaskNameSearch="+TaskNameSearch,
		 	type: "post",
		 	data: {"jsonString":jsonString}, 
		 	success : whenSuccessSearch,
		 	error: whenError
	 });		 	
}

function whenSuccess(resdata){
	$("#ajaxReturn").html(resdata);
	document.write("");
	document.write(resdata);
	
}

function whenError(resdata){
	alert("Error!");
	console.log(resdata);
}
function LargeChange(obj){														//체크리스트 대분류 변경 시 작동
	var code = new Object();
	var Large = obj.value;
	code.Large = Large;
	
	var jsonString = JSON.stringify(code);
	
	if( jsonString == "" || jsonString == null || jsonString == "undefined")	//대분류가 잘못된 값인지 체크
		{
			return false;
		}
	
	 $.ajax({
		 	url: "GroupNameSearchLarge.do?Large="+Large,										//request보낼 경로
		 	type: "post",																		//메소드(post로 적용)
		 	data: {"jsonString":jsonString}, 													//보낼 데이터
		 	success : whenSuccessLarge,																					//성공 시 값 보낼 펑션
		 	error: whenError
	 });
}
function whenSuccessLarge(result){
	var obj = JSON.parse(result);
	var OptionBox = document.getElementById("searchConditionMiddle");
	for(var i = OptionBox.length; i>0;i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
			var objOption = document.createElement("option");
			objOption.text = obj[i].Middle_cd_nm;
			objOption.value = obj[i].Middle_cd_nm;
			objOption.id = obj[i].Middle_cd;
			
			OptionBox.options.add(objOption);
		}
}

function MiddleChange(obj){

	var code = new Object();
	var Middle = obj.value;
	code.Middle = Middle;
	
	var jsonString = JSON.stringify(code);
	
	if( jsonString == "" || jsonString == null || jsonString == "undefined")	//대분류가 잘못된 값인지 체크
	{
		return false;
	}

 $.ajax({
	 	url: "GroupNameSearchMiddle.do?Middle="+Middle,										//request보낼 경로
	 	type: "post",																		//메소드(post로 적용)
	 	data: {"jsonString":jsonString}, 													//보낼 데이터
	 	success : whenSuccessMiddle,																					//성공 시 값 보낼 펑션
	 	error: whenError
 });
}

function whenSuccessMiddle(result){
	console.log("success:"+result);
	jQuery("#table_hover>tbody:last>tr").remove();
	var obj = JSON.parse(result);
	var str = '<tr';
	jQuery.each(obj, function(i){
		str += ' id='+obj[i].ctlg_cd+'><td style="text-align:left;"id="'+obj[i].ctlg_cd+'" value="'+obj[i].ctlg_nm+'" onclick="javascript:clickTdEvent(this)">'+obj[i].ctlg_nm+'</td>';
		str += '</tr>'
	});
	jQuery("#table_hover").append(str);
	
}
function whenSuccessSearch(result){
	console.log("success:"+result);
	jQuery("#table_hover>tbody:last>tr").remove();
	var obj = JSON.parse(result);
	var OptionBoxLarge = document.getElementById("searchConditionLage");
	var OptionBox = document.getElementById("searchConditionMiddle");

	var str = '<tr';
	for(var i = OptionBox.length; i>0;i--)
		{
		OptionBox.options[i] = null;
		}
	jQuery.each(obj, function(i){
		str += ' id='+obj[i].ctlg_cd+'><td style="text-align:left;"id="'+obj[i].ctlg_cd+'" value="'+obj[i].ctlg_nm+'" onclick="javascript:clickTdEvent(this)">'+obj[i].ctlg_nm+'</td>';
		str += '</tr>'
	});
	
	for(var i = OptionBox.length; i>0;i--)
		{
			OptionBox.options[i] = null;
		}
	for(var i=0;i<obj.length;i++)
		{
			var objOption = document.createElement("option");
			objOption.text = obj[i].mdcls_nm;
			objOption.value = obj[i].mdcls_nm;
			objOption.id = obj[i].mdcls;
			
			OptionBox.options.add(objOption);
		}
	for(var i=0;i<obj.length;i++)
	{
		var objOption = document.createElement("option");
		objOption.text = obj[i].lrgcls_nm;
		objOption.value = obj[i].lrgcls_nm;
		objOption.id = obj[i].lrgcls;
		
		OptionBoxLarge.options.add(objOption);
	}
	
	jQuery("#table_hover").append(str);
	jQuery("#searchConditionLage option:last").prop("selected", true);
	jQuery("#searchConditionMiddle option:last").prop("selected", true);
	
}
/////////////////////////////////////////////
// 모달용 펑션 끝
/////////////////////////////////////////////
