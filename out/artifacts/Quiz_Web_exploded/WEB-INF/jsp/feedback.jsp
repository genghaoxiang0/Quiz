<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/15
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/user-base.jsp">
    <layout:put block="title">Feedback</layout:put>
    <layout:put block="contents">
        <c:if test="${success != null}">
            <div class="alert alert-success" role="alert">
                    ${success}
            </div>
        </c:if>
        <div style="text-align: center">
            <br>
            <form method="post" action="/feedback">
                Rate:
                <select class="form-select" aria-label="Default select example" name="rate">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                <br>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Comment</label>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="comment"></textarea>
                </div>
                <button class="btn btn-primary">Submit</button>
            </form>
        </div>
    </layout:put>
</layout:extends>