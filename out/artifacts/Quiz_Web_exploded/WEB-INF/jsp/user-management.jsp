<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/11
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/admin-base.jsp">
    <layout:put block="title">User Management</layout:put>
    <layout:put block="head">
        <script type="text/javascript" src="/js/userManagement.js"></script>
    </layout:put>
    <layout:put block="contents">
        <h2>User List</h2>
        <div id="error"></div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Admin</th>
                <th scope="col">Active</th>
                <th scope="col">Operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.admin}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose>
                    </td>
                    <td id="user-${user.id}-status">
                        <c:choose>
                            <c:when test="${user.active}">Yes</c:when>
                            <c:otherwise>No</c:otherwise>
                        </c:choose>
                    </td>
                    <td id="user-${user.id}-operation">
                        <c:choose>
                            <c:when test="${user.admin}"><button disabled="disabled">Suspend</button></c:when>
                            <c:when test="${user.active}"><button onclick="suspendUser(${user.id})">Suspend</button></c:when>
                            <c:otherwise><button onclick="activateUser(${user.id})">Activate</button></c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </layout:put>
</layout:extends>