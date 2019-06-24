<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container-fluid">
        <div class="row d-flex justify-content-center align-items-center my-5">
            <div class="col"></div>
            <div class="col">
                <img src="${ad.imageUrl}" alt="">
                <h1>${ad.title}</h1>
                <p>${ad.description}</p>
                <%--<p><a href="mailto:${ad.email}?subject=${ad.title}" rel="noopener">${user.email}</a></p>--%>
                <c:if test="${sessionScope.user.id == ad.userId}">
                    <div class="row">
                        <a href="/update-ad?id=${ad.id}" class="btn btn-primary m-2">Edit</a>
                    </div>
                </c:if>
            </div>
            <div class="col"></div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/partials/bootstrapScript.jsp" />
</body>
</html>
