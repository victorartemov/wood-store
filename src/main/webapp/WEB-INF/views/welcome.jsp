<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <!-- Header navigation bar -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">

            <!-- Logo -->
            <div class="navbar-header">
                <a href="" class="navbar-brand">WOODSTORE</a>
            </div>

            <!-- Menu items -->
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="">Склад</a></li>
                    <li><a href="">Рабочий день</a></li>
                    <li><a href="">Приход</a></li>
                    <li><a href="">Расход</a></li>
                </ul>
            </div>

            <!-- Worker info/logout -->
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <a href="#"><span class="glyphicon glyphicon-user"></span>
                                ${pageContext.request.userPrincipal.name}</a>
                        </c:if>
                    </li>
                    <li>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <a onclick="document.forms['logoutForm'].submit()">Выход</a>
                    </li>
                </ul>
            </form>

        </div>
    </nav>
    <!-- Header navigation bar ends -->

    <!-- Date logo -->
    <h1 align="center">На складе
        <c:out value="${currentTime}"/>
    </h1>


    <!-- Tables of products per each category -->
    <c:forEach items="${categories}" var="category">
        <h2>
            <c:out value="${category.title}"/>
        </h2>

        <!-- Check the type of a category -->
        <c:choose>

            <c:when test="${category.simple == true}">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Название</th>
                            <th>Количество, шт</th>
                            <th>Цена, р</th>
                            <th>Стоимость, р</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${productsByCategories}" var="entry">
                                <c:if test="${entry.key == category.title}">
                                    <c:forEach items="${entry.value}" var="product">
                                        <tr>
                                            <td>${product.title}</td>
                                            <td>${product.amount}</td>
                                            <td>${product.price}</td>
                                            <td>${product.price*product.amount}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>


            <c:when test="${category.simple == false}">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Название</th>
                            <th>Длина, м</th>
                            <th>Количество, шт</th>
                            <th>м. кв</th>
                            <th>Цена, р</th>
                            <th>Стоимость, р</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${productsByCategories}" var="entry">
                            <c:if test="${entry.key == category.title}">
                                <c:forEach items="${entry.value}" var="product">
                                    <tr>
                                        <td>${product.title}</td>
                                        <td>${product.length}</td>
                                        <td>${product.amount}</td>
                                        <td>${product.length*product.amount*0.096}</td>
                                        <td>${product.price}</td>
                                        <td>${product.length*product.amount*0.096*product.price}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>


            <c:otherwise>
                Upss.. No data in the database!
            </c:otherwise>
        </c:choose>
    </c:forEach>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>