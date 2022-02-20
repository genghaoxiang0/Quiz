<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/14
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/user-base.jsp">
    <layout:put block="title">Quiz Results</layout:put>
    <layout:put block="contents">
        <h2>Quiz results</h2>
        <div id="error"></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Category</th>
                <th scope="col">Start time</th>
                <th scope="col">Finish time</th>
                <th scope="col">Score</th>
                <th scope="col">Result</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${quizzes}" var="quiz">
                <tr onclick="window.location.href='/quiz/result/${quiz.id}'" class="clickable-row">
                    <th scope="row">${quiz.name}</th>
                    <td>${quiz.category}</td>
                    <td>${quiz.startTime.toString().substring(0,19)}</td>
                    <td>${quiz.finishTime.toString().substring(0,19)}</td>
                    <td>${quiz.score}</td>
                    <td>
                        <c:choose>
                            <c:when test="${quiz.score >= 60}">Pass</c:when>
                            <c:otherwise>Fail</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </layout:put>
</layout:extends>