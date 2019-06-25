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
    <style>
        img{
            height: 180px;
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
            display: inline-block;
        }
        button:focus{
            outline: 0;
        }

    </style>



    <title>Title</title>
</head>
<body>
    <h1 style="text-align: center">Here are all the ads for ${category}!</h1>
    <div class="container-fluid">
        <div class="row h-90">
    <c:forEach var="ad" items="${ads}">
            <div class="col-4">
                <div class="card mb-3" style="max-width: 540px;">
                    <div class="row no-gutters">
                        <div class="col-4">
                            <img src="${ad.imageUrl}" class="card-img" alt="...">
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
