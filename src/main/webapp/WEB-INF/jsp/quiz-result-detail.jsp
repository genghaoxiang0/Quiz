<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/14
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/user-base.jsp">
    <layout:put block="title">Quiz Result</layout:put>
    <layout:put block="contents">
        <h4>Quiz name: ${quiz.name}</h4>
        <h4>Quiz taker: ${sessionScope.user.name}</h4>
        <h4>Quiz Score: ${quiz.score}</h4>
        <c:choose>
            <c:when test="${quiz.score >= 60}">
                <h4 style="color: green">Pass</h4>
            </c:when>
            <c:otherwise>
                <h4 style="color: red">Fail</h4>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="0" end="9" step="1" var="i">
            <hr>
            Question ${i+1}
            <br>
            ${choiceQuestions.get(i).description}
            <br>
            <c:choose>
                <c:when test='${quizChoiceQuestions.get(i).answer=="A"}'>
                    <input class="form-check-input" type="radio" id="flexRadioDefault1" checked disabled>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" type="radio" id="flexRadioDefault1" disabled>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test='${choiceQuestions.get(i).answer=="A"}'>
                    <label class="form-check-label" for="flexRadioDefault1" style="color: green">
                            ${choiceQuestions.get(i).choiceA}
                    </label>
                </c:when>
                <c:when test='${quizChoiceQuestions.get(i).answer=="A" && choiceQuestions.get(i).answer!="A"}'>
                    <label class="form-check-label" for="flexRadioDefault1" style="color: red">
                            ${choiceQuestions.get(i).choiceA}
                    </label>
                </c:when>
                <c:otherwise>
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${choiceQuestions.get(i).choiceA}
                    </label>
                </c:otherwise>
            </c:choose>
            <br>
            <c:choose>
                <c:when test='${quizChoiceQuestions.get(i).answer=="B"}'>
                    <input class="form-check-input" type="radio" id="flexRadioDefault2" checked disabled>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" type="radio" id="flexRadioDefault2" disabled>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test='${choiceQuestions.get(i).answer=="B"}'>
                    <label class="form-check-label" for="flexRadioDefault2" style="color: green">
                            ${choiceQuestions.get(i).choiceB}
                    </label>
                </c:when>
                <c:when test='${quizChoiceQuestions.get(i).answer=="B" && choiceQuestions.get(i).answer!="B"}'>
                    <label class="form-check-label" for="flexRadioDefault1" style="color: red">
                            ${choiceQuestions.get(i).choiceB}
                    </label>
                </c:when>
                <c:otherwise>
                    <label class="form-check-label" for="flexRadioDefault2">
                            ${choiceQuestions.get(i).choiceB}
                    </label>
                </c:otherwise>
            </c:choose>
            <br>
            <c:choose>
                <c:when test='${quizChoiceQuestions.get(i).answer=="C"}'>
                    <input class="form-check-input" type="radio" id="flexRadioDefault3" checked disabled>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" type="radio" id="flexRadioDefault3" disabled>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test='${choiceQuestions.get(i).answer=="C"}'>
                    <label class="form-check-label" for="flexRadioDefault3" style="color: green">
                            ${choiceQuestions.get(i).choiceC}
                    </label>
                </c:when>
                <c:when test='${quizChoiceQuestions.get(i).answer=="C" && choiceQuestions.get(i).answer!="C"}'>
                    <label class="form-check-label" for="flexRadioDefault1" style="color: red">
                            ${choiceQuestions.get(i).choiceC}
                    </label>
                </c:when>
                <c:otherwise>
                    <label class="form-check-label" for="flexRadioDefault3">
                            ${choiceQuestions.get(i).choiceC}
                    </label>
                </c:otherwise>
            </c:choose>
            <br>
            <c:choose>
                <c:when test='${quizChoiceQuestions.get(i).answer=="D"}'>
                    <input class="form-check-input" type="radio" id="flexRadioDefault4" checked disabled>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" type="radio" id="flexRadioDefault4" disabled>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test='${choiceQuestions.get(i).answer=="D"}'>
                    <label class="form-check-label" for="flexRadioDefault4" style="color: green">
                            ${choiceQuestions.get(i).choiceD}
                    </label>
                </c:when>
                <c:when test='${quizChoiceQuestions.get(i).answer=="D" && choiceQuestions.get(i).answer!="D"}'>
                    <label class="form-check-label" for="flexRadioDefault1" style="color: red">
                            ${choiceQuestions.get(i).choiceD}
                    </label>
                </c:when>
                <c:otherwise>
                    <label class="form-check-label" for="flexRadioDefault4">
                            ${choiceQuestions.get(i).choiceD}
                    </label>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <div style="text-align: center">
            <button type="button" class="btn btn-primary" onclick="window.location.href='/quiz/results'">Back</button>
        </div>
    </layout:put>
</layout:extends>