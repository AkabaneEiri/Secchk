<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleList.jsp
  * @Description : Sample List 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>로그인 화면</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>	
<script src="js/fontawesome-all.js"></script>	
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>	
<script src="js/submit.js"></script>
<script src="js/regula.js"></script>
<script src="js/crypto/sha256.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link href="css/detail.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">

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
	
	<div class="contents_wrap">		
		
		<article class="login_title" style="margin-top: 0px;">
			<span>로그인</span>
		</article>		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>로그인<span>></span>비밀번호 변경
			</div>		
		</article>		
		<section class="login_section">
			<div>
				<div id="txt">비밀번호 변경 페이지입니다.</divn>
			</div>
			<div id="img">
				<img src="images/logIcon.png">
			</div>
			<div class="login_table">
				<form class="form-inline loginform" name="pwChagne" method="POST" action="PasswordChangeAction.do">			
				<table>
					<tbody>
						<tr class="login_pw">
							<td width="78px">변경 전</td>
							<td id="txt">
								<input type="password" class="form-control input password" ime-mode="disabled" placeholder="현재 비밀번호" name="pw_current" id="pw_current" autocomplete="off" maxlength="13"/>
							</td>						
						</tr>
						
						<tr class="login_pw">
							<td width="78px">변경 후</td>
							<td id="txt">
								<input type="password" class="form-control input password" placeholder="변경할 비밀번호" name="pw_new" id="pw_new" autocomplete="off" maxlength="13"/>
							</td>							
						</tr>	
						
						<tr class="login_pw">
							<td width="78px">변경 확인</td>
							<td id="txt">
								<input type="password" class="form-control input password" placeholder="변경할 비밀번호 확인" name="pw_new_confirm" id="pw_new_confirm" autocomplete="off" maxlength="13"/>
							</td>
						
						</tr>				
					</tbody>

					<div class="login_button" style="top: 20%;">				
						<button type="submit" class="btn-sm btn-primary btn_password_change button" id="pw_change">
							<i class="fas fa-check"></i>&nbsp;저장</button>
					</div>
				</table>			
<!--		<div id="joins"><a href="Signin.do">회원가입</a></div> -->
			</form>
			</div>			
		</section>	
		
		<jsp:include page="Footer.jsp"></jsp:include>
	</div> 
</body>

</html>
