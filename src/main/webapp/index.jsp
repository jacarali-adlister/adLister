
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>
    <link rel="stylesheet" href="css/card.css">
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1>Categories!!!</h1>
    <section>
        <div class="card-container">
            <c:forEach var="category" items="${categories}">
                <div class="card">
                    <img src="${categories.imgUrl}"/>
                    <div class="content">
                        <h3>${categories.title}</h3>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>
</div>
</body>
</html>