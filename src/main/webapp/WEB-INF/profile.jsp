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

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username}!</h1>
    </div>

    <div class="container">
        <h1>Here Are all the ads!</h1>

        <c:forEach var="ad" items="${ads}">
            <%--<div class="col-md-6">--%>
                <%--<h2>${ad.title}</h2>--%>
                <%--<p>${ad.description}</p>--%>
            <%--</div>--%>

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
                            <form action="/profile" method="post">
                                <input name="id" value="${ad.id}"type="hidden">
                                <button type="submit">Update this ad</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</body>
</html>
