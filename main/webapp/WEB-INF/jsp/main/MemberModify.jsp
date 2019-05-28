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
<title>사용자 관리</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/MemberModify.js"></script>
<script src="js/engine.js"></script>

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
			<span>사용자 관리</span>
		</article>
		<article class="cur_page">
			<div id="title">
			홈<span>></span>사용자 관리<span>></span>수정
			</div>		
		</article>
		
		<section class="subContent_section" id="sub2">
		<br>
		<div class="Insert_Table">
			<form action =""name="Modify_value" id="Modify_value" method="post" onsubmit="">
		 		<table class="table table-border sub_table table01" style="text-align:center;width:90%;margin:auto;margin-top:10px;margin-bottom:10px;border-top:0px" >
		 		<thead style="border:0px">
						<tr>
							<td colspan = "4"style="border:0px; text-align:left;font-size:15px;">
							</td>
						</tr>
					</thead>
				<tbody>
					<c:forEach var="memberselect" items="${memberselect}" varStatus="statics">
					<tr>
						<th>이름</th>
						<td>
							<input class="DiableInputbox" type="text" placeholder="이름" name="stmt" id="stmt" autocomplete="off"value="${memberselect.stmt }" style="ime-mode:active"/>
						</td>
						<th style="text-align:center;">직책</th>					
						<td style="width:34%;">
							<input class="DiableInputbox" type="text" placeholder="직책" name="rspofc_nm" id="rspofc_nm" value="${memberselect.rspofc_nm }"/>
						</td>
					</tr>
					<tr>
						<th>ID(군번)</th>
						<td>
							<input class="DiableInputbox" type="text" placeholder="ID(군번)" name="srvno" id="srvno" autocomplete="off" value="${memberselect.srvno}" disabled/>
						</td>
						<th style="text-align:center;">비밀번호</th>
							<td style="background-color:white;">
								<input class="sub_input" type="password" placeholder="비밀번호" 	name="password" id="password" autocomplete="off" />
								<br>
								<input type="text" id="pwalert" name="pwalert" style="border:0px;background-color:white;font-size:0.8rem;" disabled />
							</td>
					</tr>
					<tr>
						<th> 계급 선택</th>
						<td>
						<select style="background-color:white;display:inline" name="rnkcd" id="rnkcd" class="form-control">
								<option value="">선택</option>
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
						<th style="text-align:center;">비밀번호확인</th>
							<td style="background-color:white;">
								<input class="sub_input" type="password" placeholder="비밀번호 확인" name="password_check" 	id="password_check" autocomplete="off"/>
							</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<input class="DiableInputbox" type="text" placeholder="전화번호" name="prtbl_telno" id="prtbl_telno" autocomplete="off" value="${memberselect.prtbl_telno }"/>
						</td>
						<th> 소속부대</th>
						<td>
						<div style="text-align:left; ">
							<input class="DiableInputbox2" type="text" placeholder="소속부대검색"  name="incdt_idtf_cd" id="incdt_idtf_cd" autocomplete="off"
							style="font-size: 12pt;box-shadow : 0px 0px 1px #757575;color: #757575;width:73%;background-color:#eaeaea;" 
							value="${memberselect.incdt_idtf_cd }"disabled/>
							<button type="button" class="btn btn-sm btn-primary btn-width" data-toggle="modal" data-target="#TaskSearch" ><i class="fas fa-search"></i>&nbsp;검색</button>
							</div>
						  	<jsp:include page="TroopsSearchModal.jsp"></jsp:include>
						</td>
					</tr>
					<tr>
						<th> 사용자 권한</th>
						<td>
							<select style="display:inline" name="athrt" id="athrt" class="form-control">
								<option value="">선택</option>
								<option value="B1"><script>$("#athrt").val("<c:out	 value="${fn:trim(memberselect.athrt)}"/>").attr("selected","selected");</script>일반 사용자</option>
								<option value="B2"><script>$("#athrt").val("<c:out	 value="${fn:trim(memberselect.athrt)}"/>").attr("selected","selected");</script>중간 관리자</option>
								<option value="B3"><script>$("#athrt").val("<c:out	 value="${fn:trim(memberselect.athrt)}"/>").attr("selected","selected");</script>최고 관리자</option>
							</option>
							</select>
						</td>
						<th> 모니터링 권한</th>
						<td>
						<select style="display:inline" name="montr" id="montr" class="form-control">
								<option value="">선택</option>
								<option value="C1"><script>$("#montr").val("<c:out	 value="${fn:trim(memberselect.montr)}"/>").attr("selected","selected");</script>권한 부여</option>
								<option value="C2"><script>$("#montr").val("<c:out	 value="${fn:trim(memberselect.montr)}"/>").attr("selected","selected");</script>권한 미부여</option>
							</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>계정 상태</th>
						<td id="td_acc" colspan="3">
							<select style="width:38%;" name="athrt" id="acc_state_info" class="form-control">
								<option value="">선택</option>
								<option value="K1"><script>$("#acc_state_info").val("<c:out	 value="${fn:trim(memberselect.acc_state_info)}"/>").attr("selected","selected");</script>사용 가능</option>
								<option value="K2"><script>$("#acc_state_info").val("<c:out	 value="${fn:trim(memberselect.acc_state_info)}"/>").attr("selected","selected");</script>계정 잠김</option>
							</option>
							</select>
						</td>
						<c:if test="${fn:trim(memberselect.athrt) == 'B2' || fn:trim(memberselect.athrt) == 'B3' }">
							<script>$("#td_acc").attr("colspan", 1); $("#acc_state_info").css("width", "100%");</script>
							<th style="text-align:center;">사용자 IP</th>
							<td><input class="sub_input" type="text" placeholder="IP" maxlength="16" name="ip" id="ip" autocomplete="off" value="${memberselect.ip }" onkeydown=""/></td>
						</c:if>
					</tr>
					</c:forEach>
				</tbody>
		 		</table>
			</form>
			<div class="row" style="margin: auto; text-align: center;">
				<button type="submit" id="submit" class="btn btn-sm btn-primary btn-width" id="modify" onclick="Midify_Submit()">
				<i class="fas fa-pen-square"></i>&nbsp;수정</button>
				&nbsp;
				<button type="cancle" class="btn btn-sm btn-primary btn-width" id="cancle" onclick="Modify_Cancle()">
				<i class="fas fa-list"></i>&nbsp;목록</button>
			</div>
		</div>
		</section>
	<jsp:include page="Footer.jsp"></jsp:include>	
	</div>
			
</body>
</html>