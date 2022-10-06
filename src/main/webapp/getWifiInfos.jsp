<%@ page import="Manager.Manager" %>
<%@ page import="DTO.WifiDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Manager manager = new Manager();
    int count = manager.saveApiData();
	Gson gson = new Gson();
	String str = gson.toJson(count);
	out.print(str);
%>