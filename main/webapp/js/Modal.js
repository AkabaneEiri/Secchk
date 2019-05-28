
//////////////////////////////////////////
//	모달용 펑션 입니다.
//////////////////////////////////////////
function clickTdEvent(tdObj){						// TaskSmall(과업 소분류 테이블)을 클릭했을 때에 유발되는 Function
	
	var Name = tdObj.innerText;						// TaskSmall의 Text를 가져온다(한글명, IE에서 Controller와 사용하기 위해서는 Encoding 필
	var Code = tdObj.id;							// TaskSmall의 ID를 가져온다(G00X) 코드명 검색을 위해 사용한다
	document.TaskName.TaskName_name.value=Name;		// 모달 창 내의 과엄명의 VALUE를 지정하여, 선택한 코드를 가시적으로 표시한다
	document.TaskCode.TaskCode_name.value=Code;		// 모달 창 내의 과엄명의 VALUE를 지정하여, 선택한 코드를 가시적으로 표시한다
	
	//////////////////////////////////////////
	//	테이블 클릭 시 과업 대쿤류 & 중분류 가져오는 통신
	//////////////////////////////////////////
	var i;
	var task = new Object();						 
	task.Code = Code;
	
	var jsonString = JSON.stringify(task);
	
	$.ajax({
		url:"Modal_Cls_Search.do",
		type:"post",
		data:{"jsonString":jsonString},
		success:whenSuccessModal,
		error:whenError
	});
	
	//////////////////////////////////////////
	//	테이블 클릭 시 TR색 변경으로 가시적 Effect 주는 루틴
	//////////////////////////////////////////
	
	var table = document.getElementById("table_hover");
	var tr = table.getElementsByTagName("td");
	
	for(var i=0; i<tr.length; i++){
		tr[i].style.backgroundColor = "#f4f4f4";
	}
	
	tdObj.style.backgroundColor = "#D8D8D8";
	
	//////////////////////////////////////////
	//										//	
	//////////////////////////////////////////
	
	//////////////////////////////////////////
	//	대분류, 중분류 최신화 하기 위한 루틴	
	//////////////////////////////////////////
	var Large = $("#searchConditionLage option:selected").val();			// 대분류 코드
	var Large_nm = $("#searchConditionLage option:selected").attr('name');	// 대분류 값
	var Middle = $("#searchConditionMiddle option:selected").attr('id');	// 중분류 코드
	var Middle_nm = $("#searchConditionMiddle option:selected").val();		// 중분류 값
	
//		parent.clickTdEvent(tdObj);											// 클릭 시 모달 밖 부모 창에 선택값 전달(확인 버튼 클릭시로 루틴 변경)
	
	var result = false;
	for(var i = 0; i < parent.document.scripts.length; ++i) {
		console.log(parent.document.scripts.item(i));
		if (parent.document.scripts.item(i).getAttribute('src') == "js/submit.js") {
			result = true;
		}
	}
	
	if (result == true)	
		parent.selectedEvent(Large, Large_nm, Middle, Middle_nm);
}

function clickTrEvent(trObj){
	var Name = trObj.getAttribute('name');
	var Code = trObj.id;
	
	document.getElementById("TaskName_name").value = Name;
	document.getElementById("TaskCode_name").value = Code;
	
	//////////////////////////////////////////
	//	테이블 클릭 시 과업 대쿤류 & 중분류 가져오는 통신
	//////////////////////////////////////////
	var i;
	var task = new Object();						 
	task.Code = Code;
	
	var jsonString = JSON.stringify(task);
	
	$.ajax({
		url:"Modal_Cls_Search.do",
		type:"post",
		data:{"jsonString":jsonString},
		success:whenSuccessModal,
		error:whenError
	});
	
	//////////////////////////////////////////
	//	테이블 클릭 시 TR색 변경으로 가시적 Effect 주는 루틴
	//////////////////////////////////////////
	
	var table = document.getElementById("table_hover");
	var tr = table.getElementsByTagName("tr");
	
	for(var i=0; i<tr.length; i++){
		tr[i].style.backgroundColor = "#f4f4f4";
	}
	
	trObj.style.backgroundColor = "#D8D8D8";
	
	//////////////////////////////////////////
	//										//	
	//////////////////////////////////////////
	
	//////////////////////////////////////////
	//	대분류, 중분류 최신화 하기 위한 루틴	
	//////////////////////////////////////////
	var Large = $("#searchConditionLage option:selected").val();			// 대분류 코드
	var Large_nm = $("#searchConditionLage option:selected").attr('name');	// 대분류 값
	var Middle = $("#searchConditionMiddle option:selected").attr('id');	// 중분류 코드
	var Middle_nm = $("#searchConditionMiddle option:selected").val();		// 중분류 값
	
//		parent.clickTdEvent(tdObj);											// 클릭 시 모달 밖 부모 창에 선택값 전달(확인 버튼 클릭시로 루틴 변경)
	
	var result = false;
	for(var i = 0; i < parent.document.scripts.length; ++i) {
		console.log(parent.document.scripts.item(i));
		if (parent.document.scripts.item(i).getAttribute('src') == "js/submit.js") {
			result = true;
		}
	}
	
	if (result == true)	
		parent.selectedEvent(Large, Large_nm, Middle, Middle_nm);
}

/////////////////////
//	이름으로 과업 검색하는 루틴
/////////////////////
function Search_TaskName(){
	var code = new Object();
	var TaskNameSearch = document.getElementById("TaskNameSearch").value;				// 검색어
	var OptionBoxLarge = document.getElementById("searchConditionLage");				// 대분류 코드 가져오기
	var OptionLarge = OptionBoxLarge.options[OptionBoxLarge.selectedIndex].value;		// 선택된 대분류 코드
	var OptionBoxMiddle = document.getElementById("searchConditionMiddle");				// 중분류 코드 가져오기
	var OptionMiddle = OptionBoxMiddle.options[OptionBoxMiddle.selectedIndex].id;		// 선택된 중분류 코드

	if(TaskNameSearch == "")															// 공란검색 예외처리
		{
			alert("검색어를 입력하여 주십시오");
			return false;
		}
	code.TaskNameSearch = TaskNameSearch;												// AJAX 통신을 위한 JSON처리 루틴
	code.OptionLarge = OptionLarge;
	code.OptionMiddle = OptionMiddle;
	
	var jsonString = JSON.stringify(code);
	
	 $.ajax({
		 	url: "GroupNameSearchName.do?TaskNameSearch="+encodeURI(TaskNameSearch),
		 	type: "post",
		 	data: {"jsonString":jsonString}, 
		 	success : whenSuccessSearch,
		 	error: whenError
	 });		 	
}

/////////////////////
//코드로 과업 검색하는 루틴
/////////////////////
function Search_TaskCode(){
	var code = new Object();
	var TaskNameSearch = document.getElementById("TaskCodeSearch").value;
	var OptionBoxLarge = document.getElementById("searchConditionLage");				// 대분류 코드 가져오기
	var OptionLarge = OptionBoxLarge.options[OptionBoxLarge.selectedIndex].value;		// 선택된 대분류 코드
	var OptionBoxMiddle = document.getElementById("searchConditionMiddle");				// 중분류 코드 가져오기
	var OptionMiddle = OptionBoxMiddle.options[OptionBoxMiddle.selectedIndex].id;	// 선택된 중분류 코드
	if(TaskNameSearch == "")
	{
		alert("검색어를 입력하여 주십시오");
		
		return false;
	}
	code.TaskNameCode = TaskNameSearch;
	code.OptionLarge = OptionLarge;
	code.OptionMiddle = OptionMiddle;
	
	var jsonString = JSON.stringify(code);
	
	 $.ajax({
		 	url: "GroupNameSearchCode.do?TaskNameSearch="+encodeURI(TaskNameSearch),
		 	type: "post",
		 	data: {"jsonString":jsonString}, 
		 	success : whenSuccessSearch,
		 	error: whenError
	 });		 	
}

//////////////////////////////////
//	테이블 값 클릭 시 대분류&중분류 자동 선택 루틴
//////////////////////////////////

function whenSuccessModal(resdata){
	
	var obj = JSON.parse(resdata);										// AJAX 통신 결과값
	var Large = document.getElementById("searchConditionLage");			// 대분류 Element 가져오기
	var Middle = document.getElementById("searchConditionMiddle");		// 중분류 Element 가져오기
	
	var MiddleCls = obj[0].mdcls_nm;									// AJAX 통신 결과로 받은 중분류 데이터
	
	var MiddleOps = false; 												// 현재 중분류 값에 AJAX통신 결과로 받은 값이 있는지 검사
		$('searchConditionMiddle option').each(function(){
			if(this.value == MiddleCls){
				MiddleOps = true;
				return false;}
		})
	
	if(MiddleOps == false)																//값이 없으면
		{
//		jQuery("#searchConditionLage").val(obj[0].lrgcls).prop("selected", true);		//클릭된 데이터의 대분류값을 선택하고
		for(var i = Middle.length; i>0;i--)
			{
			Middle.options[i] = null;													//중분류 SelectBox를 초기화
			}
		for(var i=0;i<obj.length;i++)
			{
				var objOption = document.createElement("option");						//중분류 SelectBox를 생성
				objOption.text = obj[i].mdcls_nm;
				objOption.value = obj[i].mdcls_nm;
				objOption.id = obj[i].mdcls;
				
				Middle.options.add(objOption);
			}
		}
		jQuery("#searchConditionLage").val(obj[0].lrgcls).prop("selected", true);		// 대분류값 선택
		jQuery("#searchConditionMiddle").val(obj[0].mdcls_nm).prop("selected", true);	//중분류값 선택
}

function whenError(resdata){															//AJAX통신 에러 시 루틴
	alert("Error!");
	console.log(resdata);																//Error라고 ALert 띄우고, 콘솔에 에러 코드 로깅
}
function LargeChange(obj){																//체크리스트 대분류 변경 시 작동
	var code = new Object();
	var Large = obj.value;
	code.Large = Large;
	
	var jsonString = JSON.stringify(code);
	
	if( jsonString == "" || jsonString == null || jsonString == "undefined")			//대분류가 잘못된 값인지 체크
		{
			return false;
		}
	
	 $.ajax({
		 	url: "GroupNameSearchLarge.do?Large="+encodeURI(Large),						//request보낼 경로
		 	type: "post",																//메소드(post로 적용)
		 	data: {"jsonString":jsonString}, 											//보낼 데이터
		 	success : whenSuccessLarge,													//성공 시 값 보낼 펑션
		 	error: whenError
	 });
}
function whenSuccessLarge(result){														//대분류 AJAX 성공 시
	var obj = JSON.parse(result);														//AJAX 결과값을 Parse
	var OptionBox = document.getElementById("searchConditionMiddle");					//대분류 Option Box 정보 가져오기
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
	 	url: "GroupNameSearchMiddle.do?Middle="+encodeURI(Middle),										//request보낼 경로
	 	type: "post",																		//메소드(post로 적용)
	 	data: {"jsonString":jsonString}, 													//보낼 데이터
	 	contnetType:"application/json; charset=utf-8",
	 	success : whenSuccessMiddle,																					//성공 시 값 보낼 펑션
	 	error: whenError
 });
}

function whenSuccessMiddle(result){
	console.log("success:"+result);
	jQuery("#table_hover>tbody:last>tr").remove();
	var obj = JSON.parse(result);
	var str = '';
	jQuery.each(obj, function(i){
		str += '<tr id="'+obj[i].ctlg_cd+'"name="'+obj[i].ctlg_nm+'" onclick="javascript:clickTrEvent(this)">';
		str += '<td style="text-align:center;"id="'+obj[i].ctlg_cd+'" value="'+obj[i].ctlg_nm+'">'+obj[i].ctlg_nm+'</td>';
		str += '<td style="text-align:center;">'+obj[i].ctlg_cd+'</td>';
		str += '</tr>'
	});
	console.log(str);
	jQuery("#table_hover").append(str);
	
}
function whenSuccessSearch(result){
	console.log("success:"+result);
	jQuery("#table_hover>tbody:last>tr").remove();
	var obj = JSON.parse(result);
	var OptionBoxLarge = document.getElementById("searchConditionLage");
	var OptionBox = document.getElementById("searchConditionMiddle");
	if(obj[0].ctlg_cd == "E001_001")
		{
		var str = '';
			jQuery.each(obj, function(i){
				str += '<tr id="'+obj[i].ctlg_cd+'"name="'+obj[i].ctlg_nm+'">';
				str += '<td colspan = "2" style="text-align:center;"id="'+obj[i].ctlg_cd+'" value="'+obj[i].ctlg_nm+'">'+obj[i].ctlg_nm+'</td>';
				str += '</tr>'
			});
			jQuery("#table_hover").append(str);
		}
	else
		{
			var str = '';
			
			jQuery.each(obj, function(i){
				str += '<tr id="'+obj[i].ctlg_cd+'"name="'+obj[i].ctlg_nm+'" onclick="javascript:clickTrEvent(this)">';
				str += '<td style="text-align:center;"id="'+obj[i].ctlg_cd+'" value="'+obj[i].ctlg_nm+'">'+obj[i].ctlg_nm+'</td>';
				str += '<td style="text-align:center;">'+obj[i].ctlg_cd+'</td>';
				str += '</tr>'
			});
			
			jQuery("#table_hover").append(str);
		}
}
/////////////////////////////////////////////
// 모달용 펑션 끝
/////////////////////////////////////////////
