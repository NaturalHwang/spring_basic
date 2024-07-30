<%-- jsp 문법 사용한다는 설정 --%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>helloworld</title>
</head>
<body>
    <form>
        <p>name: <%String name = (String)request.getAttribute("name");
        out.print(name);%></p>
        <p>email: <%String email = (String)request.getAttribute("email");
            out.print(email);%></p>
        <p>pw : <%String password = (String)request.getAttribute("password");
            out.print(password);%></p>
    </form>
</body>
</html>