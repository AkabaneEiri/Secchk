<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>안전관리 체크리스트체계</title>
</head>
<body>
	<nav id="cd-lateral-nav">
		<ul class="cd-navigation">
			<a href="login.do">로그인</a>
			<li class="item-has-children">
				<a href="#0">지휘 및 통제</a>
				<ul class="sub-menu">
					<li><a href="TroopsMonitoring.do"><img src="images/cd_bullet.png">부대활동 모니터링</a></li>
					<li><a href="EnterCommanderGuide.do"><img src="images/cd_bullet.png">지휘관지침 입력</a></li>
				</ul>
			</li> <!-- item-has-children -->
			<li>
				<a href="ReservedSafeManagement.do">예정된 안전관리활동</a>
			</li>
			<li>
				<a href="OccasionalSafeManagement.do">수시 안전관리활동</a>
			</li>
			<li>
				<a href="CheckListManagement.do">체크리스트</a>
			</li>
		</ul>
		<span id="cd-line"></span>
		<ul class="cd-navigation cd-single-item-wrapper">
				<li><a class="current" href="TroopsMonitoring.do">부대활동 모니터링</a></li>
				<li><a href="AssignTask.do">과업 부여</a></li>
				<li><a href="#0">DB관리</a></li>
				<li><a href="UserList.do">사용자 관리</a></li>
				<li class="item-has-children">
					<a href="#0">시스템 관리</a>
					<ul class="sub-menu">
						<li><a href="SelectCheckListItem.do">체크리스트 항목 선별</a></li>
						<li><a href="ManageCheckListItem.do">체크리스트 항목 관리</a></li>
					</ul>
				</li>
		</ul>
		<ul class="army_logo">
			<span id="logo"><img src="images/army_logo.png"></span>
		</ul>	
	</nav>
</body>
</html>