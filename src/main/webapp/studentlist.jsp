<%--
  Created by IntelliJ IDEA.
  User: Dinusha
  Date: 8/28/2022
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h3 class="text-center">Student List</h3>
        <hr>

        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn brn-class">Add New Student</a>
        </div>
        <br>

        <table class="table table-bordered">

            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Student Age</th>
                <th>Student Gender</th>
                <th>Actions</th>
            </tr>
            <tbody>
                <c:forEach var="student" items="${liststudent}">
                <tr>
                    <td>
                        <c:out value="${student.id}" />
                    </td>
                    <td>
                        <c:out value="${student.name}" />
                    </td>
                    <td>
                        <c:out value="${student.age}" />
                    </td>
                    <td>
                        <c:out value="${student.gender}" />
                    </td>
                    <td>
                        <a href="edit?id=<c:out value='${student.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${student.id}' />">Delete</a>                    </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>


    </div>

</body>
</html>
