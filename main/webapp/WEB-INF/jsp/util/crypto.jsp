<%@ page import="egovframework.main.util.*" %>
<%
    String a = "random pass phrase";
    String b = "72216a6607a2a2d8939d5a324b195ba32bab81cd";
    String c = "91a90d6aa4241465fb2ac9ab0e06eba0";
    AesHelper ah = new AesHelper(a,b,c);
%>