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
<script src="js/LoginJs/jquery-3.1.1.min.js"></script>
<script src="js/LoginJs/bootstrap.min.js"></script>
<script src="js/LoginJs/common.js"></script>	
<script src="js/LoginJs/fontawesome-all.js"></script>	
<script src="js/LoginJs/swiper.min.js"></script>
<script src="js/LoginJs/swiper.js"></script>	
<script src="js/regula.js"></script>
<script src="js/crypto/sha256.js"></script>
<script src="js/key_check_com.js"></script>
<link href="css/logincss/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/logincss/reset.css">
<link rel="stylesheet" href="css/logincss/swiper.css">
<link rel="stylesheet" type="text/css" href="css/logincss/main.css">
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
<!--  
	<header class="header">
		<a id="cd-logo" href="index.do">
			<span class="fas fa-home"></span>
		</a>
		
		<div class="logo">
			<a href="index.do">
			<img src="images/m_logo.png"></a>
		</div>
		<div id="home"></div>
		
	</header>
-->
		<article class="m_menu">
			<ul>
				<li><a href="ReservedSafeManagement.do">예정된 안전관리활동</a></li>
				<li><a href="OccasionalSafeManagement.do">수시 안전관리활동</a></li>
				<li><a href="CheckListManagement.do">체크리스트</a></li>
			</ul>
			<span class="v-line" id="v-line1"></span>
			<span class="v-line" id="v-line2"></span>
		</article>
	<div class="contents_wrap">		
		
		<article class="login_title">
			<span>로그인</span>
		</article>		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>로그인
			</div>		
		</article>		
		<section class="login_section">
			<div>
				<div id="txt">현역 실명 인증</divn>
			</div>
			<div id="img">
				<img src="images/logIcon.png">
			</div>
			<div class="login_table">
				<form class="form-inline loginform" name="keyCheck" method="POST" action="loginAction.do">			
				<table>
					<tbody>
						<tr class="login_id">
							<td width="78px">군번</td>
							<td id="txt">
								<input type="text" class="form-control input id loginInputbox" ime-mode="disabled" placeholder="아이디(군번)" name="srvno" id="srvno" autocomplete="off"/>
							</td>
						
						</tr>
						
						<tr class="login_pw">
							<td width="78px">인증번호</td>
							<td id="txt">
								<input type="password" class="form-control input password loginInputbox" placeholder="인증번호" name="certno" id="certno" autocomplete="off" maxlength="6"/>
							</td>							
						</tr>					
					</tbody>
				<input type="hidden" name="system_cd"  value="safeck"> <!-- 검증요청체계 -->
				<input type="hidden" name="vfcturl"    value="http://army.mil.kr/vfct/vfctP.do"> <!-- 검증 페이지 URL -->
				<input type="hidden" name="receiveurl" value="http://10.1.20.57:8080/mobile/KeyCheck_Action.do"> <!-- 검증후 넘길 페이지 URL -->
				
				<div class="login_button">				
					<button type="submit" class="btn button" id="check">인증</button>
				</div>

				</table>
				</form>
			</div>			
		</section>	

		<div class="separate_line" id="line3"></div>
		
		<jsp:include page="Footer.jsp"></jsp:include>
	</div> 
</body>


</html>