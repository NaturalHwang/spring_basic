package com.beyond.basic.servletjsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hello/servlet/jsp/get")
public class HelloServletJspGet extends HttpServlet {
//    @Override // 안달아도 됨(근데 달아놓는게 좋긴 함)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        src/main/webapp 폴더를 찾아가는 것으로 약속되어있다(thymeleaf는 resources 밑에 templates)
        req.setAttribute("myData", "hello world java");
        req.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(req, resp);

    }
}
