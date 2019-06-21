
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads"/>
    </jsp:include>
    <link rel="stylesheet" href="/css/card.css">
    <style>
        img{
            height: 200px;
            border:5px solid black;
            border-radius: 2px;
        }
        img:hover{
            box-shadow: 0 0 8px 3px rgba(0,0,0,.15);
            transform: scale(1.025);
            opacity: 1;
            filter: grayscale(0);
            transition: .35s all ease;
        }
        .card-title {
            font: 1.8rem 'Bree Serif', serif;
            padding: 0;
            margin: 0 0 10px;
            letter-spacing: -.075rem;
            text-align: center;
        }

        button{
            border: none;
        }

        .container-fluid{
            padding: 5%;
        }


    </style>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>
<br>
    <h1 style="text-align: center">Categories</h1>
    <section>
        <%--<div class="container">--%>
        <div class="container-fluid">
            <div class="row h-100">
                <c:forEach var="category" items="${categories}">
                    <div class='col-2 category'>
                        <div class="row">
                            <div class="col-12">
                                <form action="/categories" method="post">
                                <input type="hidden" value="${category.title}" name="category">
                                <button><img class="card-img" src="${category.imgUrl}" alt="..."></button>
                                </form>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <h5 class="card-title">${category.title}</h5>
                            </div>
                        </div>
                    </div>
            </c:forEach>
            </div>
            </div>
        <%--</div>--%>
    </section>
</div>
</body>
</html>
