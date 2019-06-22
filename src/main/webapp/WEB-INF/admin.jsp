<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

        <table class="table table-dark table-striped">
            <thead class="thead-dark">
                <th>User Id</th>
                <th>Username</th>
                <th>Email</th>
                <th>Ban User</th>
            </thead>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>

                    <td>
                        <form action="/admin" method="post">
                            <input name="id" value="${user.id}" type="hidden">
                            <input type="submit" class="btn btn-block btn-primary">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>





</body>
</html>
