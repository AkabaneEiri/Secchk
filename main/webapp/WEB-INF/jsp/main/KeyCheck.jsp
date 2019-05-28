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
<title>현역실명인증 화면</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>	
<script src="js/fontawesome-all.js"></script>	
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>	
<script src="js/regula.js"></script>
<script src="js/crypto/sha256.js"></script>
<script src="js/key_check_com.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
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
	<div class="contents_wrap login_wrap">		
		
		<article class="login_title" style="margin-top: 0px;">
			<span>로그인</span>
		</article>		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>로그인<span>></span>현역실명인증
			</div>		
		</article>		
		<section class="login_section">
			<div>
				<div id="txt">현역 실명 인증</div>
			</div>
			<div id="img">
				<img src="images/logIcon.png">
			</div>
			<div class="login_table">
				<form class="form-inline loginform" name="keyCheck" method="POST" action="http://www.army.mil.kr/vfct/vfctP.do">	
				<div class="login_table_div">		
				<table>
					<tbody>
						<tr class="login_id">
							<td style="width: 85px;">군번</td>
							<td id="" style="width: 75%;">
								<input style="width: 88%;" type="text" class="form-control input id loginInputbox" ime-mode="disabled" placeholder="아이디(군번)" name="srvno" id="srvno" autocomplete="off"/>
							</td>
						
						</tr>
						
						<tr class="login_pw">
							<td style="width: 85px;">인증번호</td>
							<td id="" style="width: 75%;">
								<input style="width: 88%;" type="password" class="form-control input password loginInputbox" placeholder="인증번호" name="certno" id="certno" autocomplete="off" maxlength="6"/>
							</td>							
						</tr>					
					</tbody>
				</table>
				</div>
				<input type="hidden" name="system_cd"  value="safeck"> <!-- 검증요청체계 -->
				<input type="hidden" name="vfcturl"    value="http://army.mil.kr/vfct/vfctP.do"> <!-- 검증 페이지 URL -->
				<input type="hidden" name="receiveurl" value="http://safety.army.mil.kr/KeyCheck_Action.do"> <!-- 검증후 넘길 페이지 URL -->
				
				<div class="login_button_div">				
					<button type="submit" class="btn button" id="check" style="width: 64px; height: inherit;">인증</button>
				</div>				
				</form>
			</div>			
		</section>
		
		<jsp:include page="Footer.jsp"></jsp:include>
	</div> 
</body>


</html>