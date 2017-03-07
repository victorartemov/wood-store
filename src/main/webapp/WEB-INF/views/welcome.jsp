﻿<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <h1 align="center">На складе 24.05.17</h1>

    <!-- Table of products 1 -->
    <h2>Название категории 1</h2>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Firstname</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Anna</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Debbie</td>
            </tr>
            <tr>
                <td>3</td>
                <td>John</td>
            </tr>
            </tbody>
        </table>
    </div>
    <!-- Table of products 1 ends -->

    <!-- Table of products 2 -->
    <h2>Название категории 2</h2>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>#</th>
                <th>Firstname</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Anna</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Debbie</td>
            </tr>
            <tr>
                <td>3</td>
                <td>John</td>
            </tr>
            </tbody>
        </table>
    </div>
    <!-- Table of products 2 ends -->

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>