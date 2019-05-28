function Approve_select(index){
	//var index =event.srcElement.value;
	if(index == null || index == "" || index =="undefined")
	{
		return false;
	}
	else{
	location.href ="CheckOccasionalSafeManagement_Select.do?seq="+index;
	}
}