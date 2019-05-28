<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="js/TaskSearch.js"></script>
<link rel="stylesheet" type="text/css" href="css/datepicker.css">
<table class="table sub_table table01" id="table_cls" style="width:90%;margin:auto; margin-bottom:10px;">
<colgroup>
<col width="87px"/>
<col width="200px"/>
<col width="87px"/>
<col width="376px"/>
<col width="87px"/>
<col width="600px"/>
<col width="70px"/>
</colgroup>
	<thead style="border-bottom: 2px solid #17a2b8d6;">
		<tr>
			<th colspan="1" style="text-align:center; vertical-align:middle;">일시</th>
			<td colspan="1" style="text-align:left;">
				<input type="text" style="box-shadow : 0px 0px 1px #757575; text-align:center;border:none; height:30px; width:140px;" id="TaskDatepicker"  name="searchCondition2" value=<c:out value="${membersearchVO.searchCondition2}"/>>
			</td>
			<th style="vertical-align:middle;">검색조건</th>
			<td style="text-align:left;">
				<select id="ClsSelect" name="ClsSelect" style="height:30px;" align="center">
					<option value="ctlg_nm">세부활동</option>
					<option value="ctlg_cd">세부활동 코드</option>
				</select>
				<input class="sub_input" type="text" name="TaskNameSearch" id="TaskNameSearch" style="border:1px solid;height:30px;width: 160px;"/>
				<button type="button" class="btn btn-sm btn-primary"onclick="Taskcls_search()"><i class="fas fa-search"></i>&nbsp;검색</button>
			</td>		
			<th style="vertical-align:middle;">활동유형</th>
			<td colspan="1" style="text-align:left;border-right:none;">
				<select id="searchConditionLage" name="searchConditionLage" onchange="LargeChange(this)" style="width:152px;height:30px;" align="center">
					<option value="">대 분 류</option>
				</select>
				&nbsp;
				<select id="searchConditionMiddle" name="searchConditionMiddle" onchange="MiddleChange(this)" style="width:152px;height:30px;" align="center">
					<option value="">중 분 류</option>
				</select>
				&nbsp;
				<select class="select_stripped" name="Task_name" id="Task_name" onchange="SelectEvent()" style="width:250px;height:30px;min-width:150px;" align="center">
					<option value="">세부 활동</option>
				</select>
			</td>	
			<td style="text-align: right;">
				<button type="button" class="btn btn-sm btn-primary" style="text-align: center; height: inherit;" onclick="Submit()">
					<i class="fas fa-search"></i>&nbsp;조회
				</button>
			</td>
		</tr>
	</thead>		
</table>
<input type="hidden" id="result_lrgcls" name="result_lrgcls">
<input type="hidden" id="result_mccls" name="result_mccls">
<input type="hidden" id="result_nm" name="result_nm">
<input type="hidden" id="result_cd" name="result_cd">


