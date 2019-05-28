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
<title>체크리스트 검토</title>
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
<link rel="stylesheet" type="text/css" href="css/ime_mode.css">
 
</head>
 
<body>
 
    <jsp:include page="Header.jsp"></jsp:include>
    <div class="sub_contents_wrap">    
        
        <article class="sub_title">
            <span>체크리스트 검토</span>
        </article>
        
        <article class="cur_page">
            <div id="title">
            홈<span>></span>시스템관리<span>></span>체크리스트 검토
            </div>        
        </article>
        
        <section class="subContent_section" id="CheckRequest">
		<form name="Approve_Value">
            <c:forEach var="Check" items="${CheckReuqest}" varStatus="statics">
            	
            	                
        	<table class="table table-border sub_table table01"  style="text-align:center;width:90%; margin:auto; margin-top:20px;">
            <tbody>
                
                <tr>
                	<th colspan= "2">신규여부</th>
                    <td>
                        <!--  <input class="DiableInputbox" name="Value_ctlg_cd" value="${Check.ctlg_cd}" disabled> -->
                        <c:choose>
		            		<c:when test="${Check.new_yn == 'Y'}">
		            			 <input class="DiableInputbox" name="new_yn" value="Y" disabled>
		            		</c:when>
		            		<c:otherwise>
		            			<input class="DiableInputbox" name="new_yn" value="N" disabled>
		            		</c:otherwise>
		            	</c:choose>
                    </td>
                    <th colspan= "2">부대활동 유형</th>
                    <td>
                        <input class="DiableInputbox" name="Value_type" value="${Check.ctlg_cd}" disabled>
                    </td>
                    
                </tr>
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
                    <th rowspan = "2" style="vertical-align:middle;height:auto;">체크리스트 항목</th>
                    <th>변경 전</th>
                    <td colspan= "4">
                        <input class="DiableInputbox textarea_noresize" name="Value_ctlg_itm_cd" style="width:100%;border:0.5px solid #FAFAFA;text-align:center;color:#4e6361;" value="${Check.ctlg_itm_cd_1}" disabled>
                        <input type="hidden"name="Value_ctlg_itm_cd_1"  value="${Check.ctlg_itm_cd_2}" disabled>
                    </td>
                </tr>
                <tr>
                    <th>변경 후</th>
                    <td colspan= "4">
                        <input class="DiableInputbox textarea_noresize" name="Value_ctnt" style="width:100%;border:0.5px solid #FAFAFA;text-align:center;color:#4e6361;"value="${Check.rqst_ctnt}" disabled>
                    </td>
                </tr>
                <tr>
                    <th colspan= "2">사유</th>
                    <td colspan= "4">
                        <input class="DiableInputbox" name="Value_rsn" style="width:100%;border:0.5px solid #FAFAFA;text-align:center;color:#4e6361;" value="${Check.rsn}" disabled>
                    </td>
                </tr>
                <tr>
                    <th colspan= "2">검토 의견</th>
                    <td colspan="4" style="color:#757575">
                    <c:choose>
                        <c:when test="${requestChecklistVO.state_cd eq '미승인'}">
                            <input class="sub_input ime_input" name="Value_opn"  id = "opn"value="${Check.opn}" style="width:100%;border:0.5px solid #4e6361;ime-mode:active">
                            <br>
                    		<input class="sub_input " name="opn_res" id="opn_res" style="color:red;background-color: white;font-size: 12px;margin-top:10px;" value= "0/80"disabled>
                        </c:when>
                        <c:otherwise>
                        	<input class="DiableInputbox" name="Value_opn" style="width:100%;border:0.5px solid #FAFAFA;text-align:center;color:#4e6361;" value="${Check.opn}" disabled>
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
        <i class="fas fa-list"></i>&nbsp;목록</button>
    </div> 
	<br>
    </section>
    <jsp:include page="Footer.jsp"></jsp:include>        
	</div>          
   
</body>
</html>