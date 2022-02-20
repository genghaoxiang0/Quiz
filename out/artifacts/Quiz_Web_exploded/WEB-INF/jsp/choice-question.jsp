<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/12
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/admin-base.jsp">
    <layout:put block="title">Choice Question</layout:put>
    <layout:put block="head">
        <script type="text/javascript" src="/js/choiceQuestion.js"></script>
    </layout:put>
    <layout:put block="contents">
        <h2>Multiple Choice Questions</h2>
        <br>
        <c:if test="${success != null}">
            <div class="alert alert-success" role="alert">
                    ${success}
            </div>
        </c:if>
        <c:if test="${error != null}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
        <div id="error"></div>
        <button class="btn btn-primary" onclick='document.getElementById("upload-dialog").classList.add("visible");'>Upload Questions</button>
        <button class="btn btn-primary" onclick='document.getElementById("add-dialog").classList.add("visible");'>Add Question</button>
        <br><br>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Category</th>
                <th scope="col">Description</th>
                <th scope="col">Choice A</th>
                <th scope="col">Choice B</th>
                <th scope="col">Choice C</th>
                <th scope="col">Choice D</th>
                <th scope="col">Correct Answer</th>
                <th scope="col">Operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${choiceQuestions}" var="question">
                <tr>
                    <td id="category-${question.id}">${question.category}</td>
                    <td id="description-${question.id}">${question.description}</td>
                    <td id="choiceA-${question.id}">${question.choiceA}</td>
                    <td id="choiceB-${question.id}">${question.choiceB}</td>
                    <td id="choiceC-${question.id}">${question.choiceC}</td>
                    <td id="choiceD-${question.id}">${question.choiceD}</td>
                    <td id="answer-${question.id}">${question.answer}</td>
                    <td>
                        <button onclick="openEditPage(${question.id})">
                            Edit
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="text-align: center">
            <c:if test="${currentPage>1}">
                <a href="/admin/choice-question/${currentPage-1}" style="text-decoration: none">
                    <button class="btn btn-link">Prev</button>
                </a>
            </c:if>
            <c:forEach end="${totalPages}" var="te" step="1" begin="1" varStatus="name">
                <c:choose>
                    <c:when test="${currentPage==te}">
                        <button class="btn btn-primary" disabled="disabled">${te}</button>
                    </c:when>
                    <c:otherwise>
                        <a href="/admin/choice-question/${te}" style="text-decoration: none">
                            <button class="btn btn-link">${te}</button>
                        </a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage<totalPages}">
                <a href="/admin/choice-question/${currentPage+1}" style="text-decoration: none">
                    <button class="btn btn-link">Next</button>
                </a>
            </c:if>
        </div>

        <div class="dialog" id="upload-dialog">
            <h5>Upload questions from a template</h5>
            <br>
            <button>
                <a href="../../csv/choice_question_template.csv" download="choice_question_template.csv">Download template</a>
            </button>
            <br><br>
            <form method="post" enctype="multipart/form-data" action="/admin/upload-choice-question">
                <input type="file" name="file"/><br><br>
                <button type="submit" onclick='document.getElementById("upload-dialog").classList.remove("visible");'>Upload</button>
                <button type="button" onclick='document.getElementById("upload-dialog").classList.remove("visible");'>Cancel</button>
            </form>
        </div>

        <div class="dialog" id="add-dialog">
            <h5>Add multiple choice question</h5>
            <form method="post" action="/admin/add-choice-question">
                <div class="form-group">
                    <label>Category</label><br>
                    <select class="form-select" aria-label="Default select example" name="category">
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.name}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" class="form-control" name="description" placeholder="Description">
                </div>
                <div class="form-group">
                    <label>Choice A</label>
                    <input type="text" class="form-control" name="choiceA" placeholder="Choice A">
                </div>
                <div class="form-group">
                    <label>Choice B</label>
                    <input type="text" class="form-control" name="choiceB" placeholder="Choice B">
                </div>
                <div class="form-group">
                    <label>Choice C</label>
                    <input type="text" class="form-control" name="choiceC" placeholder="Choice C">
                </div>
                <div class="form-group">
                    <label>Choice D</label>
                    <input type="text" class="form-control" name="choiceD" placeholder="Choice D">
                </div>
                <div class="form-group">
                    <label>Correct Answer</label><br>
                    <select class="form-select" aria-label="Default select example" name="answer">
                        <option value="A">A</option>
                        <option value="B">B</option>
                        <option value="C">C</option>
                        <option value="D">D</option>
                    </select>
                </div>
                <button onclick='document.getElementById("add-dialog").classList.remove("visible");'>Add</button>
                <button type="button" onclick='document.getElementById("add-dialog").classList.remove("visible");'>Cancel</button>
            </form>
        </div>

        <div class="dialog" id="edit-dialog">
            <h5>Edit multiple choice question</h5>
            <div class="form-group">
                <label>Category</label><br>
                <select class="form-select" aria-label="Default select example" id="edit-category" name="category">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.name}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>Description</label>
                <input type="text" class="form-control" id="edit-description" name="description" onchange="this.setAttribute('value', $('#edit-description').val())">
            </div>
            <div class="form-group">
                <label>Choice A</label>
                <input type="text" class="form-control" id="edit-choiceA" name="choiceA" onchange="this.setAttribute('value', $('#edit-choiceA').val())">
            </div>
            <div class="form-group">
                <label>Choice B</label>
                <input type="text" class="form-control" id="edit-choiceB" name="choiceB" onchange="this.setAttribute('value', $('#edit-choiceB').val())">
            </div>
            <div class="form-group">
                <label>Choice C</label>
                <input type="text" class="form-control" id="edit-choiceC" name="choiceC" onchange="this.setAttribute('value', $('#edit-choiceC').val())">
            </div>
            <div class="form-group">
                <label>Choice D</label>
                <input type="text" class="form-control" id="edit-choiceD" name="choiceD" onchange="this.setAttribute('value', $('#edit-choiceD').val())">
            </div>
            <div class="form-group">
                <label>Correct Answer</label><br>
                <select class="form-select" aria-label="Default select example" id="edit-answer" name="answer">
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                </select>
            </div>
            <button onclick='editQuestion()'>Edit</button>
            <button type="button" onclick='document.getElementById("edit-dialog").classList.remove("visible");'>Cancel</button>
        </div>
    </layout:put>
</layout:extends>
