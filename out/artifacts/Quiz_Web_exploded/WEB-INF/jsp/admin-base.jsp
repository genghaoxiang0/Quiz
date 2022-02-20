<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/11
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="/WEB-INF/jsp/base.jsp">
    <layout:put block="nav-bar">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-s-block">
            <div class="sidebar-sticky pt-3">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/user-management">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/quiz/1">Quizzes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/choice-question">Questions</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/feedback">Feedback</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/inbox">Inbox</a>
                    </li>
                </ul>
            </div>
        </nav>
    </layout:put>
</layout:extends>