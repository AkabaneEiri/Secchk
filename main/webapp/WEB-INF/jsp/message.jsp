<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메시지창</title>
<c:choose>
	<c:when test="${!empty RST && RST == 'InsertOK'}">
		<c:set var="Message">정상적으로 등록하였습니다</c:set>
		<c:set var="GoUrl"><c:out value="${returnUrl}"/></c:set>
	</c:when>
	<c:when test="${!empty RST && RST == 'SrvnoAlready'}">
		<c:set var="Message">이미 있는 계정입니다.</c:set>
		<c:set var="GoUrl"><c:out value="${returnUrl}"/></c:set>
	</c:when>
	
	<c:when test="${!empty RST && RST == 'DeleteOK'}">
		<c:set var="Message">정상적으로 삭제하였습니다</c:set>
		<c:set var="GoUrl"><c:out value="${returnUrl}"/></c:set>
	</c:when>
	<c:when test="${!empty RST && RST == 'TaskInsertOK'}">
		<c:set var="Message">과업을 성공적으로 등록하였습니다</c:set>
		<c:set var="GoUrl"><c:out value="${returnUrl}"/></c:set>
	</c:when>
	<c:when test="${!empty RST && RST == 'ApproveOK'}">
		<c:set var="Message">정상적으로 처리되었습니다.</c:set>
		<c:set var="GoUrl"><c:out value="${returnUrl}"/></c:set>
	</c:when>
	<c:when test="${!empty RST && RST == 'CommandModifyOK'}">
		<c:set var="Message">정상적으로 처리되었습니다.</c:set>
		<c:set var="GoUrl"><c:out value="${returnUrl}"/></c:set>
	</c:when>
	<c:when test="${!empty RST && RST == 'TaskInsertFailed' }">
		<c:set var="Message">이미 과업이 등록된 사람입니다.</c:set>
		<c:set var="GoUrl"><c:out value="${returnUrl}"/></c:set>
	</c:when>
	<c:when test="${!empty RST && RST == 'CodeOnDuplicate' }"> <!-- add by seungwon -->
		<c:set var="Message">이미 등록되어있는 코드입니다.</c:set>
		<c:set var="GoUrl"><c:out value="${returnUrl}"/></c:set>
	</c:when>	
</c:choose> 

<script type="text/javascript">
window.onload = function(){
	console.log("${returnUrl}");
	alert('<c:out value="${Message}"/>');
	//location.href = "Userlist.do"
	location.href = "${returnUrl}";
}
</script>
</head>
<body>

</body>
</html>