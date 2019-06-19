<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<body>


<div class="container">
    <h1>Update your Ad:</h1>
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
                    <p class="card-text"><small class="text-muted">***categories will go here***</small></p>
                </div>
            </div>
        </div>
    </div>

    <form action="/update-ad" method="post">
        <div class="form-group">
            <label for="title">Title</label>
            <input id="title" name="title" value="${ad.title}" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input id="description" size="200" name="description" value="${ad.description}" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="url">Image</label>
            <input id="url" name="url" value="${ad.imageUrl}" class="form-control" type="text">
        </div>
        <div class="form-group form-check">
            <input name="delete" type="checkbox" class="form-check-input" id="deleteAd">
            <label class="form-check-label" for="deleteAd">Check to delete ad (warning: this action cannot be undone)</label>
        </div>
        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>
</c:forEach>


</body>
</html>
