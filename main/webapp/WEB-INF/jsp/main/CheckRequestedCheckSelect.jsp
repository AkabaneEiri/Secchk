<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>요청된 체크리스트 검토</title>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/fontawesome-all.js"></script>
<script src="js/swiper.min.js"></script>
<script src="js/swiper.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/CheckRequestedCheckSelect.js"></script>
 
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" href="css/swiper.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="css/main_detail.css">
 
</head>
 
<body>
 
    <jsp:include page="Header.jsp"></jsp:include>
    <div class="sub_contents_wrap">    
        
        <article class="sub_title">
            <span>요청된 체크리스트 검토</span>
        </article>
        
        <article class="cur_page">
            <div id="title">
            홈<span>></span>시스템관리<span>></span>요청된 체크리스트 검토
            </div>        
        </article>
        
        <section class="subContent_section" id="CheckRequest">
       	<p style="text-align:center;">
		<img src="images/title_img/CheckRequestedCheckSelect.png" alt="승인 요청"  style="width:330px; height:80px;">
		</p> 
		<form name="Approve_Value">
            <c:forEach var="Check" items="${CheckReuqest}" varStatus="statics">
				<c:if test="${Check.ctlg_itm_cd_1} == null ">
					<div class="btn-group-toggle" data-toggle="buttons" value= "IsNew">
                    	<label class="btn btn-light"><input type="checkbox"> 신규 여부</label>
                    </div>
                 </c:if>
                
        	<table class="table table-border sub_table table01"  style="text-align:center;width:90%; margin:auto;">
            <tbody>
                <tr>
                    <th colspan= "2">요청자</th>
                    <td>
                        <input class="DiableInputbox" name="Value_srvno" value="${Check.rqstr_srvno}" disabled>
                    </td>
                    <th colspan= "2">요청일자</th>
                    <td>
                        <input class="DiableInputbox" name="Value_rqst_date" value="${Check.rqst_date}" disabled>
                    </td>
                </tr>
                <tr>
                    <th colspan= "2">부대활동 유형</th>
                    <td>
                        <input class="DiableInputbox" name="Value_type" value="${Check.ctlg_cd}" disabled>
                    </td>
                    <th colspan= "2">체크리스트</th>
                    <td>
                        <input class="DiableInputbox" name="Value_ctlg_cd" value="${Check.ctlg_cd}" disabled>
                    </td>
                </tr>
                <tr>
                    <th rowspan = "2" style="vertical-align:middle;height:200px">체크리스트 항목</th>
                    <th>변경 전</th>
                    <td colspan= "4">
                        <textarea class="DiableInputbox" name="Value_ctlg_itm_cd" style="width:100%;border:0.5px solid #FAFAFA;text-align:center;font-size:14pt;color:#4e6361;" value="${Check.ctlg_itm_cd_1}" disabled>${Check.ctlg_itm_cd_1}</textarea>
                        <input type="hidden"name="Value_ctlg_itm_cd_1" style="width:100%;line-height:5;border:0.5px solid #FAFAFA;text-align:center;font-size:14pt;color:#4e6361;" value="${Check.ctlg_itm_cd_2}" disabled>
                    </td>
                </tr>
                <tr>
                    <th>변경 후</th>
                    <td colspan= "4">
                        <textarea class="DiableInputbox" name="Value_ctnt" value="${Check.rqst_ctnt}" disabled>${Check.rqst_ctnt}</textarea>
                    </td>
                </tr>
                <tr>
                    <th colspan= "2">사유</th>
                    <td colspan= "4">
                        <input class="DiableInputbox" name="Value_rsn" value="${Check.rsn}" disabled>
                    </td>
                </tr>
                <tr>
                    <th colspan= "2">검토 의견</th>
                    <td colspan="4" >
                    <c:choose>
                        <c:when test="${requestChecklistVO.state_cd eq '미승인'}">
                            <input class="sub_input" name="Value_opn"  value="${Check.opn}">
                        </c:when>
                        <c:otherwise>
                            <input class="sub_input" name="Value_opn" value="${Check.opn}" disabled>
                        </c:otherwise>
                    </c:choose>                    
                    </td>
                </tr>                
            </tbody>

		</table>
		</c:forEach>
	</form>
	<br>
    <div class="SubmitButton" style="text-align:center;">
    <c:if test="${requestChecklistVO.state_cd eq '미승인'}">
        <button type="button" class="btn btn-sm btn-primary"  onclick="Approval_Approve()">
        <i class="fas fa-check"></i>&nbsp;승인</button>
        &nbsp; 
        <button type="button" class="btn btn-sm btn-primary"  onclick="Approval_Deny()">
        <i class="fas fa-minus-circle"></i>&nbsp;반려</button>
    </c:if>
    	&nbsp;
        <button type="button" class="btn btn-sm btn-primary"  onclick="Approval_Cancle()">
        <i class="fas fa-undo"></i>&nbsp;취소</button>
    </div> 
	<br>
    </section>
    <jsp:include page="Footer.jsp"></jsp:include>        
	</div>          
   
</body>
</html>