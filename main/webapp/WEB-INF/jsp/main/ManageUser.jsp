<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>사용자정보 편집</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">
</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		
		<article class="sub_title">
			<span>사용자정보 편집</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>사용자정보 편집
			</div>		
		</article>
		
		<section class="subContent_section" id="sub2">

		<section class="login_section">
			<div class="body">
				<form action="ModifyResult.do" name="form" method="post" class="form-inline">
		 			<table class="table sub_table" style="text-align:center;">
						<tbody>
							<tr>
								<td>
									<input type="text" 		style="display:inline" class="form-control" id="ID" value="${srvno}" disabled/><input type="hidden" name="ID" value="${srvno}"/>
								</td>
							</tr>
							
							<tr>
								<td>
									<input type="password" 	style="display:inline" 	class="form-control" placeholder="비밀번호" 	name="password" 		id="password" autocomplete="off"/>
								</td>
							</tr>
							
							<tr>
								<td>
									<input type="password" 	style="display:inline" 	class="form-control" placeholder="비밀번호 확인" name="password_check" 	id="password_check" autocomplete="off"/>
								</td>
							</tr>
							
							<tr>
								<td>
								  	<select style="display:inline" name="rank" id="rank" class="form-control">
										<option value="">계급 선택</option>
										<option value="대장">대장</option>
										<option value="중장">중장</option>
										<option value="소장">소장</option>
										<option value="준장">준장</option>
										<option value="대령">대령</option>
										<option value="중령">중령</option>
										<option value="소령">소령</option>
										<option value="대위">대위</option>
										<option value="중위">중위</option>
										<option value="소위">소위</option>
										<option value="준위">준위</option>
										<option value="원사">원사</option>
										<option value="상사">상사</option>
										<option value="중사">중사</option>
										<option value="하사">하사</option>
										<option value="1급">1급</option>
										<option value="2급">2급</option>
										<option value="3급">3급</option>
										<option value="4급">4급</option>
										<option value="5급">5급</option>
										<option value="6급">6급</option>
										<option value="7급">7급</option>
										<option value="8급">8급</option>
										<option value="9급">9급</option>
										<option value="개발자">개발자</option>
									</select>
								</td>
							</tr>
							
							<tr>
								<td>
									<input style="display:inline;ime-mode:disabled" type="text" class="form-control" placeholder="이름" maxlength="25" name="name" id="name" autocomplete="off"/>
								</td>
							</tr>
						</tbody>
					</table>
					<div class="row" style="height:60px;text-align:center;">
						<button type="submit" id="submit" class="btn"><i class="fas fa-pen-square"></i>&nbsp;변경</button>
						<button type="submit" id="cancle" class="btn"><i class="fas fa-undo"></i>&nbsp;취소</button>
					</div>
					
		   		</form>
			</div>			
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>
			
</body>
</html>