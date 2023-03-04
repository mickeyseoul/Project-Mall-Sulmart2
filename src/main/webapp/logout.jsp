<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
logout.jsp<br>

<%
session.invalidate(); //세션 해제
response.sendRedirect("start.jsp");
%>