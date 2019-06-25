<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="" />
    </jsp:include>


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
        .col{
            flex-grow: unset;
        }
        h1{
            text-align: center;
        }
        p{
             text-align: center;
         }
        .row{
            padding-top: 10%;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container-fluid">
        <h1>Here is more information about '${ad.title}' ad!</h1>

        <div class="row d-flex justify-content-center">
            <div class="col">
                <img src="${ad.imageUrl}" alt="">
                    <h1>${ad.title}</h1>
                    <p>${ad.description}</p>
                    <p class="card-text ad-date"><small class="text-muted">Posted on: ${ad.create_date} </small></p>
                    <p class="card-text ad-date"><small class="text-muted">By: <strong>${ad.username}</strong></small></p>
                    <p>Contact: <a href="mailto:${user.email}?subject=${ad.title}" rel="noopener">${user.email}</a></p>
                    <c:if test="${sessionScope.user.id == ad.userId}">
                        <div class="row">
                            <a href="/update-ad?id=${ad.id}" class="btn btn-primary m-2">Edit</a>
                        </div>
                    </c:if>
                </div>
                <h1>${ad.title}</h1>
                <p>${ad.description}</p>
                <%--<p><a href="mailto:${ad.email}?subject=${ad.title}" rel="noopener">${user.email}</a></p>--%>
                <c:if test="${sessionScope.user.id == ad.userId}">
                    <div class="row">
                        <a href="/update-ad?update-id=${ad.id}" class="btn btn-primary m-2">Edit</a>
                    </div>
                </c:if>
            </div>
            <div class="col"></div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/partials/bootstrapScript.jsp" />
</body>
</html>
