<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="utf-8" />
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<nav class = "navbar navbar-default">
    <div class="container-fluid">

        <!-- Logo -->
        <div clas="navbar-header">
            <a href="" class="navbar-brand">WOODSTORE</a>
        </div>

        <!-- Menu items -->
        <div>
            <ul class = "nav navbar-nav">
                <li><a href="">Склад</a></li>
                <li><a href="">Рабочий день</a></li>
                <li><a href="">Приход</a></li>
                <li><a href="">Расход</a></li>
            </ul>
        </div>

    </div>
</nav>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>

</html>