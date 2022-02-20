<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/10
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="/WEB-INF/jsp/user-base.jsp">
    <layout:put block="title">Home</layout:put>
    <layout:put block="contents">
        <div class="container">
            <div class="row">
                <div class="col-lg-4" style="text-align: center; padding: 10px">
                    <a href="/quiz/begin/Math">
                        <img src="../../img/math.jpg">
                        <label>Math</label>
                    </a>
                </div>
                <div class="col-lg-4" style="text-align: center; padding: 10px">
                    <a href="/quiz/begin/Java">
                        <img src="../../img/java.png">
                        <label>Java</label>
                    </a>
                </div>
                <div class="col-lg-4" style="text-align: center; padding: 10px">
                    <a href="/quiz/begin/English">
                        <img src="../../img/english.jpeg">
                        <label>English</label>
                    </a>
                </div>
            </div>
        </div>
    </layout:put>
</layout:extends>
