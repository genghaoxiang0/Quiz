<%--
  Created by IntelliJ IDEA.
  User: genghaoxiang
  Date: 2022/2/15
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="/WEB-INF/jsp/admin-base.jsp">
    <layout:put block="head">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </layout:put>
    <layout:put block="title">User Feedback</layout:put>
    <layout:put block="contents">
        <canvas id="barChart"></canvas>
        <c:forEach items="${feedbacks}" var="feedback">
            <hr>
            Anonymous User ${feedback.userId}<br>
            Rate: ${feedback.rate}<br>
            Comment: ${feedback.comment}
        </c:forEach>

        <script>
            //bar
            var ctxB = document.getElementById("barChart").getContext('2d');
            var myBarChart = new Chart(ctxB, {
                type: 'bar',
                data: {
                    labels: ["1", "2", "3", "4", "5"],
                    datasets: [{
                        label: '# of Rates                          Average Rate: ${avgRate}',
                        data: [${rate1}, ${rate2}, ${rate3}, ${rate4}, ${rate5}],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255,99,132,1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        </script>
    </layout:put>
</layout:extends>