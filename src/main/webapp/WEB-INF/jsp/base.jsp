<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/11
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>
        <layout:block name="title"/>
    </title>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-LCPyFKQyML7mqtS+4XytolfqyqSlcbB3bvDuH9vX2sdQMxRonb/M3b9EmhCNNNrV" crossorigin="anonymous"></script> -->

    <link rel="stylesheet" href="/css/base.css" type="text/css">

    <script type="text/javascript" src="/js/base.js"></script>
    <layout:block name="head"/>

</head>
<body>

<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <i class="navbar-brand col-md-3 col-lg-2 mr-0 px-3">Quiz</i>
    <div style="color: #eeeeee">
        <c:if test="${sessionScope.user != null}">
            Welcome ${sessionScope.user.name}
        </c:if>
    </div>
    <div class="navbar-nav px-3">
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <a class="nav-link" href="/logout">Sign out</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link" href="/login">Login</a>
            </c:otherwise>
        </c:choose>

    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-lg-2">
            <layout:block name="nav-bar"/>
        </div>
        <div class="col-lg-10">
            <layout:block name="contents"/>
        </div>
    </div>
</div>
</body>
</html>