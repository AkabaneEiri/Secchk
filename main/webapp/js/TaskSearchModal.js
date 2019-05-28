function TaskSelect(){
	var iframeEle = document.getElementById("iframe");
	
	var Name = iframeEle.contentWindow.document.TaskName.TaskName_name.value;
	var Code = iframeEle.contentWindow.document.TaskCode.TaskCode_name.value;
	var Date = $("#TaskDatepicker").val();
	
	document.getElementById("searchCondition1").value=Name;
	document.getElementById("searchCode").value=Code;	
	
	
	var thisURL = window.location.pathname;
	
	if(thisURL.includes("AssignTask")){
		if(thisURL.includes("insert"))
			{
				var task = new Object();
				
				task.Name = Name;
				
				var jsonString = JSON.stringify(task);
				console.log(jsonString);
				
				$.ajax({
					url:'./AssignTask_TaskPre_Ajax.do?Name='+encodeURI(Name),
					type:'post',
					data:{"jsonString":jsonString},
					success: whenSuccessPre,
					error:function(err){
						alert("error! : " + err);
					}
				});
			}
		else{
			
		
				var task = new Object();
				
				task.Name = Name;
				task.Date = Date;
				
				var jsonString = JSON.stringify(task);
				console.log(jsonString);
				
				$.ajax({
					url:'./AssignTask_Ajax.do?Name='+encodeURI(Name)+"&Date="+encodeURI(Date),
					type:'post',
					data:{"jsonString":jsonString},
					success: whenSuccessTask,
					error:function(err){
					alert("error! :"+err);
					}
				});
			}
	}
}
function Task_Cancle(){
	document.getElementById("iframe").contentDocument.location.reload(true);
}