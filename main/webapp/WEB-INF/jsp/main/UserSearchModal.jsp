<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- 모달 팝업 --> 
<div class="modal" id="UserSearch"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" > 
	<div class="modal-dialog" height="450px"> 
		<div class="modal-content" style="height: 565px"> 
			<div class="modal-header"> 
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
					<span class="sr-only">Close</span>
				</button> 
					<h4 class="modal-title" id="TaskSearchLabel">담당자 검색</h4> 
			</div>
			<div class="modal-body" style="height:80%">
				<iframe src="UserSearchModal.do" width="100%" scrolling="yes" height="100%" id="iframe" onload="access()"></iframe>
			</div>
			<div class="modal-footer"> 
				<button type="button" class="btn btn-sm btn-primary" id= "select"  data-dismiss="modal">
					<i class="fas fa-check"></i>&nbsp;확인</button> 
				<button type="button" class="btn btn-sm btn-default" data-dismiss="modal">
					<i class="fas fa-undo"></i>&nbsp;취소</button> 
			</div> 
		</div>
	</div> 
</div> 