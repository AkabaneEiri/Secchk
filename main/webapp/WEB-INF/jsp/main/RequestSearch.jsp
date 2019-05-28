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
<script src="js/RequestSearch.js"></script>

</head>
			<br>
			<table class="table talble-stripped sub_table table01" style="text-align:center;width: 90%; margin:auto;">
			<colgroup>
			<col width="87px"/>
			<col width="220px"/>
			<col width="87px"/>
			<col width="220px"/>
			<col width="87px"/>
			<col width="220px"/>
			<col width="87px"/>
			<col width="220px"/>
			<col width="87px"/>
			<col width=""/>
			<col width="70px"/>
			</colgroup>
				<thead class="thead_title" style="border-bottom: 2px solid #17a2b8d6;">
					<tr>
						<th style="vertical-align: middle;">활동일자</th>
						<td style="text-align: left;">
							<input class="sub_input input-datepicker margin-left-for-input" type="text" placeholder="활동일자" maxlength="25" name="actvt_date" id="actvt_date" autocomplete="off" style="width:75%;"value="${Date}"/>
						</td>
						<th style="vertical-align: middle;">요청일자</th>
						<td style="text-align: left;">
							<input class="sub_input input-datepicker margin-left-for-input" type="text" placeholder="요청 일자" maxlength="25" name="rqst_date" id="rqst_date" autocomplete="off" style="width:75%;"value="${rqstDate}"/>
						</td>						
						<th style="vertical-align: middle;">요청자	</th>
						<td style="text-align: left;">
							<input class="sub_input margin-left-for-input" type="text" placeholder="요청자" maxlength="25" name="rqstr_srvno" id="rqstr_srvno" autocomplete="off" style="width:75%;" value="${rqstr_srvno }"/>
						</td>
						<th style="vertical-align: middle;">활동유형</th>
						<td style="text-align: left;">
							<input class="sub_input margin-left-for-input" type="text" placeholder="활동유형" maxlength="25" name="actvt_type" id="actvt_type" autocomplete="off" style="width:75%;"value="${actvt_type }"/>
						</td>
						<th style="vertical-align: middle;">상태</th>
						<td style="border-right: none;">							
							<select style="display:inline; min-width: 100px" name="state_cd" id="state_cd" class="form-control">
								<option value="">선&emsp;택</option>
								<option value="D1">미승인</option>
								<option value="D2">승인완료</option>
									<c:if test="${CheckApproval ne 'yes'}">
										<option value="D3">반려</option>
									</c:if>
								<!-- script>$("#athrt").val("<c:out	 value="${memberselect.athrt}"/>").attr("selected","selected");</script-->
							</select>
						</td>
						<td style="text-align: right;">
						<button type="button" class="btn btn-sm btn-primary" style=""onclick="Request_Search()"><i class="fas fa-search"></i>&nbsp;검색</button>
						</td>
					</tr>
				</thead>
				</table>
</html>