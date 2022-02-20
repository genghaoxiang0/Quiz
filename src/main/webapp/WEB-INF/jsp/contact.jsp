<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/15
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/user-base.jsp">
    <layout:put block="title">Contact us</layout:put>
    <layout:put block="contents">
        <c:if test="${success != null}">
            <div class="alert alert-success" role="alert">
                    ${success}
            </div>
        </c:if>
        <div style="text-align: center">
            <br>
            <form method="post" action="/contact">
                <label>Subject</label>
                <input class="form-control" name="title" type="text">
                <br>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Content</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="content"></textarea>
                </div>
                <button class="btn btn-primary">Send</button>
            </form>
        </div>
    </layout:put>
</layout:extends>