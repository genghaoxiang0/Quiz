<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/15
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/admin-base.jsp">
    <layout:put block="head">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </layout:put>
    <layout:put block="title">Inbox</layout:put>
    <layout:put block="contents">
        <h2>Inbox</h2>
        You have ${unread} unread messages
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Sender</th>
                <th scope="col">Subject</th>
                <th scope="col">Time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${messages}" var="message">
                <tr onclick="window.location.href='/admin/message/${message.id}'" class="clickable-row">
                    <c:choose>
                        <c:when test="${message.unread}">
                            <td style="font-weight: bold">${message.user.name}</td>
                            <td style="font-weight: bold">${message.title}</td>
                            <td style="font-weight: bold">${message.time.toString().substring(0,19)}</td>
                        </c:when>
                        <c:otherwise>
                            <td>${message.user.name}</td>
                            <td>${message.title}</td>
                            <td>${message.time.toString().substring(0,19)}</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </layout:put>
</layout:extends>