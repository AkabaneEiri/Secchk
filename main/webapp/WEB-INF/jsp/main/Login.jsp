<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>로그인 화면</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/common.js"></script>	
<script src="<%=request.getContextPath()%>/js/fontawesome-all.js"></script>	
<script src="<%=request.getContextPath()%>/js/LoginAction.js"></script>
<script src="<%=request.getContextPath()%>/js/regula.js"></script>
<script src="<%=request.getContextPath()%>/js/crypto/sha256.js"></script>
<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/detail.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main_detail.css">

<script src="<%=request.getContextPath()%>/js/engine.js"></script>

<script>
function maxLengthCheck(object){
   if (object.value.length > object.maxLength){
    object.value = object.value.slice(0, object.maxLength);
   }    
  }
</script>
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	
	<div class="contents_wrap login_wrap">		
		
		<article class="login_title" style="margin-top: 0px;">
			<span>로그인</span>
		</article>		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>로그인
			</div>		
		</article>		
		<section class="login_section">
			<div>
				<div id="txt">서비스 이용을 위한 로그인 페이지입니다.</divn>
			</div>
			<div id="img">
				<img src="images/logIcon.png">
			</div>
			<div class="login_table">
				<form class="form-inline loginform" name="login" method="POST" action="loginAction.do">	<!-- for service : KeyCheck.do / for debug : loginAction.do-->
				<div class="login_table_div">				
				<table>
					<tbody>
						<tr class="login_id">
							<td style="width: 85px;">군번</td>
							<td id="" style="width: 75%;">
								<!-- input type="text" MaxLength="15" class="loginInputbox" OnInput="maxLengthCheck(this)"-->
								<input style="width: 88%;" type="text" class="form-control input id loginInputbox" ime-mode="disabled" placeholder="아이디(군번)" name="srvno" id="srvno" autocomplete="off" maxlength="12" onkeyup="javascript:fn_notKorEng(this);"/>
							</td>
						
						</tr>
						
						<tr class="login_pw">
							<td style="width: 85px;">비밀번호</td>
							<td id="" style="width: 75%;">
								<!-- input type="password" MaxLength="15" class="loginInputbox" OnInput="maxLengthCheck(this)"-->
								<input style="width: 88%;" type="password" class="form-control input password loginInputbox" placeholder="비밀번호" name="pw" id="pw" autocomplete="off" maxlength="13"/>
							</td>							
						</tr>					
					</tbody>
				</table>	
				</div>
				<div class="login_button_div">				
					<button type="submit" class="btn button" id="login" style="width: 64px; height: inherit;">로그인</button>
				</div>		
				</form>
			</div>			
		</section>	
		
		<jsp:include page="Footer.jsp"></jsp:include>
		
	</div> 
</body>
</html>
