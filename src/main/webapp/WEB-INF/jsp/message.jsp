<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/15
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/admin-base.jsp">
  <layout:put block="head">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  </layout:put>
  <layout:put block="title">Message</layout:put>
  <layout:put block="contents">
    <h4>${message.title}</h4>
    <h6>${message.user.name}(${message.user.email}) sent on ${message.time.toString().substring(0,19)}</h6>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="content" readonly>
      ${message.content}
    </textarea>
    <br>
    <div style="text-align: center">
      <button type="button" class="btn btn-primary" onclick="window.location.href='/admin/inbox'">Back</button>
    </div>
  </layout:put>
</layout:extends>