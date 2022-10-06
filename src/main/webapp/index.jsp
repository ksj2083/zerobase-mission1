<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css?after" type="text/css">
    <meta charset="UTF-8">
    <title>가까운 공공 와이파이 찾기</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $("#buttonSubmit").on('click', function () {
                $.ajax({
                    url: "search.jsp",
                    type: "get",
                    data: $("#sendForm").serialize(),

                    dataType: "json",
                    cache: false,
                    success: function (data) {
                        console.log(data);
                        str = "<table id=\"apiData\">";
                        str += "<tr>";
                        str += "<th>관리번호</th>";
                        str += "<th>자치구</th>";
                        str += "<th>와이파이명</th>";
                        str += "<th>도로명주소</th>";
                        str += "<th>상세주소</th>";
                        str += "<th>설치위치(층)</th>";
                        str += "<th>설치유형</th>";
                        str += "<th>설치기관</th>";
                        str += "<th>서비스구분</th>";
                        str += "<th>망종류</th>";
                        str += "<th>설치년도</th>";
                        str += "<th>실내외구분</th>";
                        str += "<th>접속환경</th>";
                        str += "<th>위도</th>";
                        str += "<th>경도</th>";
                        str += "<th>작업일자</th>";
                        str += "</tr>";
                        $.each(data , function(){
                            str += "<tr>";
                            str += "<td>" + this.MGR_NO + "</td>";
                            str += "<td>" + this.WRDOFC + "</td>";
                            str += "<td>" + this.MAIN_NM + "</td>";
                            str += "<td>" + this.ADRES1 + "</td>";
                            str += "<td>" + this.ADRES2 + "</td>";
                            str += "<td>" + this.INSTL_FLOOR + "</td>";
                            str += "<td>" + this.INSTL_TY + "</td>";
                            str += "<td>" + this.INSTL_MBY + "</td>";
                            str += "<td>" + this.SVC_SE + "</td>";
                            str += "<td>" + this.CMCWR + "</td>";
                            str += "<td>" + this.CNSTC_YEAR + "</td>";
                            str += "<td>" + this.INOUT_DOOR + "</td>";
                            str += "<td>" + this.REMARS3 + "</td>";
                            str += "<td>" + this.LAT + "</td>";
                            str += "<td>" + this.LNT + "</td>";
                            str += "<td>" + this.WORK_DTTM + "</td>";
                            str += "</tr>";
                        });
                        str += "</table>";
                        $("#display").html(str);
                    },
                    error: function (e) {
                        console.log("ERROR : ", e);
                    }
                });
            });

            $("#buttonGetWifi").on('click', function () {
                $.ajax({
                    url: "getWifiInfos.jsp",
                    dataType: "json",
                    beforeSend : function () {
                      str = "<h1>정보 저장중입니다. 기다려 주세요.</h1>";
                      $("#display").html(str);
                    },
                    success: function (data) {
                        str = "<h1>" +data+ " 개의 정보를 저장했습니다.</h1>";
                        $("#display").html(str);
                    },
                    error: function (e) {
                        console.log("ERROR : ", e);
                    }
                });
            });

        });
    </script>
    <h1>가까운 와이파이 찾기</h1>
</head>
<body>

<div id = "box">
    <input id="buttonGetWifi" type="button" value="와이파이 정보 가져오기">
    <a href="history.jsp" target = "_blank">히스토리 페이지로 이동</a>
    <form id="sendForm">
        위도 <input type="text" name = "lat" value="37.5666805">
        경도 <input type="text" name = "lnt" value ="126.9784147">
        출력개수 <input type = "text" name = "count" value = "20">
        <input id="buttonSubmit" type="button" value="검색">
    </form>
</div>

<div id="display">
    <h1>좌표를 입력하고 검색버튼을 누르세요.</h1>
</div>
</body>
</html>