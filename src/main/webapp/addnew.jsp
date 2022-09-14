<%--
  Created by IntelliJ IDEA.
  User: Dinusha
  Date: 8/28/2022
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>

<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${student != null}">
            <form action="update" method="get">
                </c:if>
                <c:if test="${student == null}">
                <form action="insert" method="get">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${student != null}">
                                Edit Student
                            </c:if>
                            <c:if test="${student == null}">
                                Add New Student
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${student != null}">
                        <input type="hidden" name="id" value="<c:out value='${student.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Student Name</label> <input type="text" value="<c:out value='${student.name}' />"
                                                           class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Student Age</label> <input type="text" value="<c:out value='${student.age}' />"
                                                          class="form-control" name="age">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Student Gender</label> <input type="text" value="<c:out value='${student.gender}' />"
                                                             class="form-control" name="gender">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>

</body>
</html>
