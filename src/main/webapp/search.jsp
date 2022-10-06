<%@ page import="Manager.Manager" %>
<%@ page import="DTO.WifiDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");
	String count = request.getParameter("count");
    if (lat == null || lat.equals(""))
	{
        lat = "0";
    }
    if (lnt == null || lnt.equals(""))
    {
        lnt = "0";
    }
    if (count == null || count.equals("")) {
        count = "20";
    }
    Manager manager = new Manager();
    //기록남기기
    manager.saveHistory(lat, lnt);
	//출력
    List<WifiDTO> list = manager.getNearWifiInfos(lat,lnt,Integer.parseInt(count));
    Gson gson = new Gson();
	String jsonText = gson.toJson(list);
	out.print(jsonText);
%>