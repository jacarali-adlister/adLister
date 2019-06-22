<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<style>
    img{
        height: 160px;
        border:1px solid black;
        border-radius: 2px;
    }

    .ad-date{
        text-indent: 5%;
    }
</style>

<div class="container">
    <h1 style="text-align: center">Here are all the ads!</h1>

    <c:forEach var="ad" items="${ads}">
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
                <p class="card-text ad-date"><small class="text-muted">Posted on: ${ad.create_date}</small></p>
            </div>
        </div>
    </div>

    </c:forEach>
</div>

</body>
</html>
