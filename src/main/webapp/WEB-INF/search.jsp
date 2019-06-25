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
    <div class="container-fluid">
        <h1 class="d-flex justify-content-center my-4 display-4">Searched Ads</h1>
        <div class="row">
            <c:forEach var="userAd" items="${userAds}">
                <div class="col-12 col-md-3 my-2">
                    <div class="card" style="width: 18rem;">
                        <img src="${userAd.imageUrl}" class="card-img-top" alt="..." style="height: 12rem">
                        <div class="card-body">
                            <h5 class="card-title"><a>${userAd.title}</a></h5>
                            <p class="card-text">${userAd.description}</p>
                            <a href="/thisAd?id=${userAd.id}" class="btn btn-primary">View Ad</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</body>
</html>
