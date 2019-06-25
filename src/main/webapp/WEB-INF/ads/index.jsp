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
        height: 200px;
        width: 200px;
        border:1px solid black;
        border-radius: 2px;
    }

    .ad-date{
        text-indent: 5%;
    }

    .card{
        overflow-y: auto;
    }

    button{
        border: none;
        border-radius: 50%;
    }
    button:focus{
        outline: 0;
    }
</style>

    <h1 style="text-align: center">Here are all the ads!</h1>
    <div class="container-fluid">
        <div class="row h-100">
        <c:forEach var="ad" items="${ads}">
            <div class="col-4">
                <div class="card mb-3" style="max-width: 540px;">
                    <div class="row no-gutters">
                        <div class="col-4">
                            <form action="/thisAd?id=${ad.id}" method="GET">
                                <button><img src="${ad.imageUrl}" class="card-img" alt="..."></button>
                            </form>
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">${ad.title}</h5>
                                <p class="card-text">${ad.description}</p>
                                <p class="card-text">
                                <form action="/ads" method="post" class="row">
                                    <c:forEach var="category" items="${ad.categories}">
                                        <div class="col-3">
                                            <input type="hidden" name="category" value="${category}">
                                            <button><small class="text-muted">${category}</small></button>
                                        </div>
                                    </c:forEach>
                                </form>
                                </p>
                                <%--<p class="card-text ad-date"><small class="text-muted">Posted on: ${ad.create_date} By: <strong>${ad.username}</strong></small></p>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
