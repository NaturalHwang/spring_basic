<%-- jsp 문법 사용한다는 설정 --%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>helloworld</title>
</head>
<body>
    <p>data(EL문법) : ${myData}</p>
    <p>data(jstl문법-java코드) : <%
        String getData = (String) request.getAttribute("myData");
        out.print(getData);
    %></p>
</body>
</html>