<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<script src="js/MemberInsert.js"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">

<script src="js/MemberInsert.js"></script>
</head>

<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="sub_contents_wrap">	
		<article class="sub_title">
			<span>사용자 등록</span>
		</article>
		
		<article class="cur_page">
			<div id="title">
			홈<span>></span>사용자 등록
			</div>		
		</article>
		
		<section class="subContent_section" id="sub2">
		<br>
		<div class="Insert_Table">
			<form action ="Member_Insert_Result.do"name="InsertForm" id="InsertForm" method="post" onsubmit="">
	 			 <table class="table table-border sub_table table01" style="text-align:center;width:80%;margin:auto;margin-top:10px;margin-bottom:10px;" >
					<tbody>
						<tr>
							<th style="text-align:center; width:20%" >이름</th>
							
							<td style="background-color:white;width:30%">
								<input class="sub_input" type="text" placeholder="이름" name="stmt" id="stmt" autocomplete="off"value="${memberselect.stmt }"/>
							</td>
							<th style="text-align:center;width:20%">직책</th>
							
							<td style="background-color:white;width:30%">
								<input class="sub_input" type="text" placeholder="직책" maxlength="50" name="rspofc_nm" id="rspofc_nm" value="${memberselect.rspofc_nm }"/>
							</td>
						</tr>
						<tr>
							<th style="text-align:center;">ID(군번)</th>
							<td style="background-color:white;">
								<input class="sub_input" type="text" placeholder="ID(군번)" maxlength="25" name="srvno" id="srvno" autocomplete="off" value="${memberselect.srvno}"/>
							</td>
							<th style="text-align:center;">비밀번호</th>
							<td style="background-color:white;">
								<input class="sub_input" type="text" placeholder="비밀번호" 	name="password" id="password" autocomplete="off"/>
							</td>
						</tr>
						<tr>
							<th style="text-align:center;">계급</th>
							<td style="background-color:white;">
							<select style="background-color:white;display:inline" name="rnkcd" id="rnkcd" class="form-control">
									<option value="" style="text-align:center;">선&emsp;택</option>
									<option value="A01"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>대장</option>
									<option value="A02"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>중장</option>
									<option value="A03"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>소장</option>
									<option value="A04"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>소장(진)</option>
									<option value="A05"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>준장</option>
									<option value="A06"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>준장(진)</option>
									<option value="A07"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>대령</option>
									<option value="A08"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>대령(진)</option>
									<option value="A09"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>중령</option>
									<option value="A10"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>임중령</option>
									<option value="A11"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>중령(진)</option>
									<option value="A12"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>소령</option>
									<option value="A13"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>임소령</option>
									<option value="A14"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>소령(진)</option>
									<option value="A15"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>대위</option>
									<option value="A16"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>대위(진)</option>
									<option value="A17"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>중위</option>
									<option value="A18"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>중위(진)</option>
									<option value="A19"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>소위</option>
									<option value="A20"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>원사</option>
									<option value="A21"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>원사(진)</option>
									<option value="A22"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>상사</option>
									<option value="A23"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>상사(진)</option>
									<option value="A24"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>중사</option>
									<option value="A25"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>중사(진)</option>
									<option value="A26"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>하사</option>
									<option value="A27"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>병장</option>
									<option value="A28"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>상병</option>
									<option value="A29"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>일병</option>
									<option value="A30"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>이병</option>
									<option value="A31"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>1급</option>
									<option value="A32"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>2급</option>
									<option value="A33"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>3급</option>
									<option value="A34"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>4급</option>
									<option value="A35"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>5급</option>
									<option value="A36"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>6급</option>
									<option value="A37"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>7급</option>
									<option value="A38"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>8급</option>
									<option value="A39"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>9급</option>
									<option value="A40"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능1급</option>
									<option value="A41"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능2급</option>
									<option value="A42"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능3급</option>
									<option value="A43"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능4급</option>
									<option value="A44"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능5급</option>
									<option value="A45"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능6급</option>
									<option value="A46"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능7급</option>
									<option value="A47"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능8급</option>
									<option value="A48"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능9급</option>
									<option value="A49"><script>$("#rnkcd").val("<c:out	 value="${memberselect.rnkcd}"/>").attr("selected","selected");</script>기능10급</option>
								</select>
							</td>
							<th style="text-align:center; font-size:15px">비밀번호확인</th>
							<td style="background-color:white;">
								<input class="sub_input" type="text" placeholder="비밀번호 확인" name="password_check" 	id="password_check" autocomplete="off"/>
							</td>
						</tr>
						<tr>
							<th style="text-align:center;">전화번호</th>
							<td style="background-color:white;">
								<input class="sub_input" type="text" placeholder="전화번호" maxlength="25" name="prtbl_telno" id="prtbl_telno" autocomplete="off" value="${memberselect.prtbl_telno }"/>
							</td>
							<th style="text-align:center;">소속부대</th>
							<td style="background-color:white;">
							<div style="text-align:center;">
								<input class="sub_input DiableInputbox" style="width:74%; height:27px;" type="text" placeholder="소속부대검색"  name="incdt_idtf_cd" id="incdt_idtf_cd" autocomplete="off" value="${memberselect.incdt_idtf_cd }"disabled/>
								<button type="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#TaskSearch" ><i class="fas fa-search"></i>&nbsp;검색</button>
								</div>
							  	<jsp:include page="taskSearchModal.jsp"></jsp:include>
						</tr>
						<tr>
							<th style="text-align:center;">사용자 정보</th>
							<td style="background-color:white;">
								<select style="display:inline" name="athrt" id="athrt" class="form-control">
									<option value="">선&emsp;택</option>
									<option value="B1"><script>$("#athrt").val("<c:out	 value="${memberselect.athrt}"/>").attr("selected","selected");</script>일반 사용자</option>
									<option value="B2"><script>$("#athrt").val("<c:out	 value="${memberselect.athrt}"/>").attr("selected","selected");</script>중간 관리자</option>
									<option value="B3"><script>$("#athrt").val("<c:out	 value="${memberselect.athrt}"/>").attr("selected","selected");</script>최고 관리자</option>
								</option>
								</select>
							</td>
							<th style="text-align:center;">모니터링 권한</th>
							<td style="background-color:white;">
							<select style="display:inline" name="montr" id="montr" class="form-control">
									<option value="">선&emsp;택</option>
									<option value="C1"><script>$("#montr").val("<c:out	 value="${memberselect.montr}"/>").attr("selected","selected");</script>권한 부여</option>
									<option value="C2"><script>$("#montr").val("<c:out	 value="${memberselect.montr}"/>").attr("selected","selected");</script>권한 미부여</option>
								</option>
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			<form:form name="btnForm" method="post">
				<div class="row" style="height:60px;text-align:center;">
							<button type="Submit" 	class="btn btn-sm btn-primary" id="Submit"	onclick="Insert_Submit()">
							<i class="fas fa-pen-square"></i>&nbsp;수정</button>
							&nbsp;
							<button type="Cancle"	 class="btn btn-sm btn-primary" id="Cancle"	onclick="Insert_Cancle()">
							<i class="fas fa-undo"></i>&nbsp;취소</button>
				</div>
			</form:form>
				
	   		</form>
		</div>			
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>
</body>
</html>