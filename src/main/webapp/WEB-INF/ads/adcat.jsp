<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: carsonbelew
  Date: 2019-06-20
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<jsp:include page="/WEB-INF/partials/head.jsp"/>
    <jsp:include page="/WEB-INF/partials/navbar.jsp"/>


    <title>Title</title>
</head>
<body>
<div class="container">

    <c:forEach var="ad" items="${ads}">
        <h1>Here are all the ads for ${category}!</h1>
        <div class="card mb-3" style="max-width: 540px;">
            <div class="row no-gutters">
                <div class="col-md-4">
                    <img src="${ad.imageUrl}" class="card-img" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">${ad.title}</h5>
                        <p class="card-text">${ad.description}</p>
                        <p class="card-text">
                            <c:forEach var="category" items="${ad.categories}"><a href="#">
                                <small class="text-muted">${category} </small></a>
                            </c:forEach>
                        </p>
                    </div>
                </div>
            </div>
        </div>

    </c:forEach>
</div>

</body>
</html>
