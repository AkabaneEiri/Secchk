<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 모달 팝업 --> 
<div class="modal" id="TaskSearch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" > 
	<div class="modal-dialog"> 
		<div class="modal-content"> 
			<div class="modal-header"> 
				<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">×</span>
				<span class="sr-only">Close</span>
				</button> 
				<p style="text-align:center;">
					<img src="images/title_img/SearchTroops.png" alt="부대 검색"  style="width:330px; height:70px;">
				</p>  
			</div>
			<div class="modal-body" style="height:335px">
			<iframe src="TroopsSearchModal.do" width="100%" scrolling="yes" height="100%" id="iframe" onload="access()"></iframe>
			</div>
			<div class="modal-footer"> 
				<button type="button" class="btn btn-sm btn-primary" onclick="submit_troops()"id= "select"  data-dismiss="modal">
					<i class="fas fa-check"></i>&nbsp;확인</button>
				<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
					<i class="fas fa-undo"></i>&nbsp;취소</button> 
			</div> 
		</div>
	</div> 
</div>
<!--  모달 팝업 끝 -->