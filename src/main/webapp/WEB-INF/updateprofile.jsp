<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Update Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="card text-white bg-info" style="margin: 0 auto; max-width: 60rem; border-radius: 1em;">
        <div class="card-header text-center" style="padding: 1em;">
            <h4>Update Profile</h4>
        </div>
        <div class="card-body" style="padding: 0 10em 2em 10em;">
            <form action="/updateprofile" method="POST">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input id="username" name="username" class="form-control" type="text" value="${user.username}">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input id="password" name="password" class="form-control" type="password" value="${user.password}">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" name="email" class="form-control" type="text" value="${user.email}">
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Update">
            </form>
        </div>
    </div>
</body>
</html>