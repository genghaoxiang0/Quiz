<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/14
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/admin-base.jsp">
    <layout:put block="title">Quiz Record</layout:put>
    <layout:put block="contents">
        <h2>Quiz results</h2>
        <div id="error"></div>
        <form id="form" method="post" action="/admin/quiz/${pageNo}">
            <input id="sort" name="sort" value="${sort}" hidden>
            <table>
                <tr>
                    <td>Category</td>
                    <td>
                        <select name="categoryFilter">
                            <option value="Any">- Any -</option>
                            <c:forEach items="${categories}" var="category">
                                <c:choose>
                                    <c:when test="${categoryFilter.equals(category.name)}">
                                        <option value="${category.name}" selected>${category.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${category.name}">${category.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>User</td>
                    <td>
                        <input name="user" list="users" value="${name}">
                            <datalist id="users">
                                <c:forEach items="${users}" var="user">
                                    <option value="${user.name}"></option>
                                </c:forEach>
                            </datalist>
                        </input>
                    </td>
                </tr>
            </table>
            <button class="btn btn-primary">Filter</button>
            <br><br>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <c:choose>
                        <c:when test='${sort.equals("user")}'>
                            <th scope="col" style="background-color: red">
                                User
                            </th>
                        </c:when>
                        <c:otherwise>
                            <th scope="col" style="background-color: aqua" class="clickable-row" onclick="document.getElementById('sort').setAttribute('value', 'user');document.getElementById('form').submit()">
                                User
                            </th>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test='${sort.equals("category")}'>
                            <th scope="col" style="background-color: red">
                                Category
                            </th>
                        </c:when>
                        <c:otherwise>
                            <th scope="col" style="background-color: aqua" class="clickable-row" onclick="document.getElementById('sort').setAttribute('value', 'category');document.getElementById('form').submit()">
                                Category
                            </th>
                        </c:otherwise>
                    </c:choose>
                    <th scope="col">Start time</th>
                    <th scope="col">Finish time</th>
                    <th scope="col">Score</th>
                    <th scope="col">Result</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${quizzes}" var="quiz">
                    <tr onclick="window.location.href='/admin/quiz/result/${quiz.id}'" class="clickable-row">
                        <th scope="row">${quiz.name}</th>
                        <td>${userMap.get(quiz.userId)}</td>
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
            <div style="text-align: center">
                <c:if test="${currentPage>1}">
                    <button class="btn btn-link" onclick="handleGoToPage(${currentPage-1})">Prev</button>
                </c:if>
                <c:forEach end="${totalPages}" var="te" step="1" begin="1" varStatus="name">
                    <c:choose>
                        <c:when test="${currentPage==te}">
                            <button class="btn btn-primary" disabled="disabled">${te}</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-link" onclick="handleGoToPage(${te})">${te}</button>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage<totalPages}">
                    <button type="button" class="btn btn-link" onclick="handleGoToPage(${currentPage+1})">Next</button>
                </c:if>
            </div>
        </form>

        <script>
            function handleGoToPage(targetPage) {
                document.getElementById('form').setAttribute('action', '/admin/quiz/'+targetPage)
                document.getElementById('form').submit()
            }
        </script>
    </layout:put>
</layout:extends>