<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/13
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<layout:extends name="/WEB-INF/jsp/base.jsp">
    <layout:put block="title">Quiz</layout:put>
    <layout:put block="contents">
        <c:set var="chosen" value="${sessionScope.userAnswer.get(questionNum-1)}"/>
        <fmt:parseNumber var="seconds" value="${sessionScope.quizTimeLimit-(now.time-sessionScope.quiz.startTime.time)/1000}" integerOnly="true"/>
        <fmt:parseNumber var="minute" value="${seconds/60}" integerOnly="true"/>
        <fmt:parseNumber var="second" value="${seconds%60}" integerOnly="true"/>
        <br>
        <div class="row">
            <div class="col-lg-10" id="timer" style="text-align: center">
                Time left: ${minute}:${second}
            </div>
            <div class="col-lg-2">
                <button class="btn btn-primary" onclick="submit()">Submit</button>
            </div>
        </div>
        <c:choose>
            <c:when test="${sessionScope.marks.contains(Integer.parseInt(questionNum))}">
                <input class="form-check-input" type="checkbox" id="markCheckbox" onclick="markQuestion()" checked>
            </c:when>
            <c:otherwise>
                <input class="form-check-input" type="checkbox" id="markCheckbox" onclick="markQuestion()">
            </c:otherwise>
        </c:choose>
        <label class="form-check-label" for="markCheckbox">
            Mark question
        </label>
        <h6>Question ${questionNum}: ${sessionScope.choiceQuestions.get(questionNum-1).description}</h6>
        <div class="form-check">
            <c:choose>
                <c:when test='${chosen == "A"}'>
                    <input class="form-check-input" type="radio" name="answer" value="A" onchange="saveAnswer()" id="flexRadioDefault1" checked>
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${sessionScope.choiceQuestions.get(questionNum-1).choiceA}
                    </label>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" type="radio" name="answer" value="A" onchange="saveAnswer()" id="flexRadioDefault1">
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${sessionScope.choiceQuestions.get(questionNum-1).choiceA}
                    </label>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-check">
            <c:choose>
                <c:when test='${chosen == "B"}'>
                    <input class="form-check-input" type="radio" name="answer" value="B" onchange="saveAnswer()" id="flexRadioDefault1" checked>
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${sessionScope.choiceQuestions.get(questionNum-1).choiceB}
                    </label>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" type="radio" name="answer" value="B" onchange="saveAnswer()" id="flexRadioDefault1">
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${sessionScope.choiceQuestions.get(questionNum-1).choiceB}
                    </label>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-check">
            <c:choose>
                <c:when test='${chosen == "C"}'>
                    <input class="form-check-input" type="radio" name="answer" value="C" onchange="saveAnswer()" id="flexRadioDefault1" checked>
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${sessionScope.choiceQuestions.get(questionNum-1).choiceC}
                    </label>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" type="radio" name="answer" value="C" onchange="saveAnswer()" id="flexRadioDefault1">
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${sessionScope.choiceQuestions.get(questionNum-1).choiceC}
                    </label>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-check">
            <c:choose>
                <c:when test='${chosen == "D"}'>
                    <input class="form-check-input" type="radio" name="answer" value="D" onchange="saveAnswer()" id="flexRadioDefault1" checked>
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${sessionScope.choiceQuestions.get(questionNum-1).choiceD}
                    </label>
                </c:when>
                <c:otherwise>
                    <input class="form-check-input" type="radio" name="answer" value="D" onchange="saveAnswer()" id="flexRadioDefault1">
                    <label class="form-check-label" for="flexRadioDefault1">
                            ${sessionScope.choiceQuestions.get(questionNum-1).choiceD}
                    </label>
                </c:otherwise>
            </c:choose>
        </div>
        <div style="position: fixed; bottom: 50px">
            <c:if test="${questionNum>1}">
                <a href="/quiz/question/${questionNum-1}" style="text-decoration: none">
                    <button class="btn btn-link">Prev</button>
                </a>
            </c:if>
            <c:forEach end="10" var="te" step="1" begin="1" varStatus="name">
                <c:choose>
                    <c:when test="${questionNum==te}">
                        <button class="btn btn-primary" disabled="disabled">${te}</button>
                    </c:when>
                    <c:when test="${sessionScope.marks.contains(Integer.parseInt(te))}">
                        <a href="/quiz/question/${te}" style="text-decoration: none">
                            <button class="btn btn-link" style="background-color: red; color: #FFFFFF">${te}</button>
                        </a>
                    </c:when>
                    <c:when test="${sessionScope.userAnswer.get(te-1).length() > 0}">
                        <a href="/quiz/question/${te}" style="text-decoration: none">
                            <button class="btn btn-link" style="background-color: green; color: #FFFFFF">${te}</button>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="/quiz/question/${te}" style="text-decoration: none">
                            <button class="btn btn-link">${te}</button>
                        </a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${questionNum<10}">
                <a href="/quiz/question/${questionNum+1}" style="text-decoration: none">
                    <button class="btn btn-link">Next</button>
                </a>
            </c:if>
        </div>

        <script>
            function saveAnswer() {
                let radio = document.getElementsByName("answer")
                for (let i = 0; i < radio.length; i++) {
                    if (radio[i].checked) {
                        $.ajax({
                            url: "/quiz/update-answer",
                            type: "POST",
                            data: "questionNum=" + ${questionNum} + "&value=" + radio[i].value,
                            dataType: "json"
                        });
                        return
                    }
                }
            }

            function submit() {
                saveAnswer()
                $.ajax({
                    url: "/quiz/check-finish",
                    type: "POST",
                    success: checkFinish
                });
            }

            function checkFinish(response) {
                if (response) {
                    window.location.href="/quiz/submit"
                } else {
                    if (confirm("You have unanswered question. Do you want to submit?")) {
                        window.location.href="/quiz/submit";
                    }
                }
            }

            function updateTimer() {
                let time = new Date()
                let seconds = ${sessionScope.quizTimeLimit} - Math.floor((time.getTime() - ${sessionScope.quiz.startTime.time})/1000)
                if (seconds <= 0) {
                    clearInterval(interval)
                    alert("Time over")
                    window.location.href="/quiz/submit"
                    return
                }
                let minute = Math.floor(seconds/60)
                let second = seconds % 60
                $("#timer").empty()
                $("#timer").append("Time left: "+minute+":"+second)
            }

            let interval = window.setInterval(updateTimer, 1000)

            function markQuestion() {
                let checkbox = document.getElementById("markCheckbox")
                if (checkbox.checked) {
                    $.ajax({
                        url: "/quiz/mark-question",
                        type: "POST",
                        data: "questionNum=" + ${questionNum},
                        dataType: "json"
                    });
                } else {
                    $.ajax({
                        url: "/quiz/unmark-question",
                        type: "POST",
                        data: "questionNum=" + ${questionNum},
                        dataType: "json"
                    });
                }
            }
        </script>
    </layout:put>
</layout:extends>

