<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/13
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="/WEB-INF/jsp/user-base.jsp">
    <layout:put block="title">Begin Quiz</layout:put>
    <layout:put block="contents">
        <div style="text-align: center">
            <h1>You are going to take ${category} quiz.</h1>
            <h1>The quiz contains 10 questions.</h1>
            <h1>You have 15 minutes to finish the quiz.</h1>
            <h1>Are you ready to begin?</h1>
            <a href="/quiz/${category}" style="text-decoration: none">
                <button type="button" class="btn btn-primary">Begin</button>
            </a>
            <a href="/" style="text-decoration: none">
                <button type="button" class="btn btn-primary">Back</button>
            </a>
        </div>
    </layout:put>
</layout:extends>
