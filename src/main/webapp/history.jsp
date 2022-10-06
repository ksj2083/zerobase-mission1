<%@ page import="Manager.Manager" %>
<%@ page import="java.util.List" %>
<%@ page import="DTO.HistoryDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    Manager m = new Manager();
    List<HistoryDTO> list = m.getHistory();
%>
<html>
<head>
    <link rel="stylesheet" href="style.css?after" type="text/css">
    <meta charset="UTF-8">
    <title>검색 기록</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <h1>검색기록</h1>
</head>
<body>
    <table id ="apiData">
        <tr>
            <th>번호</th>
            <th>위도</th>
            <th>경도</th>
            <th>일시</th>
        </tr>
        <%
            for(HistoryDTO dto : list)
            {
        %>
        <tr>
            <td><%=dto.getHIS_NO()%></td>
            <td><%=dto.getLAT()%></td>
            <td><%=dto.getLNT()%></td>
            <td><%=dto.getLKUP_DTTM()%></td>
        </tr>
        <%
            };
        %>
    </table>
</body>
</html>