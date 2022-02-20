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
            <div class="sidebar-sticky pt-3" style="width: 500px">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Quiz</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/quiz/results">Results</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/feedback">Feedback</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/contact">Contact us</a>
                    </li>
                </ul>
            </div>
        </nav>
    </layout:put>
</layout:extends>