<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="path" value="createNewShipmentInProduct" scope="request"/>
<c:set var="categoriesToIterate" value="${categories}" scope="request"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Приход</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <!-- Header -->
    <jsp:include page="header.jsp"/>

    <c:if test="${currentShipment == null}">
        <h1 align="center">Приход</h1>
        <br>

        <div class="panel panel-warning">
            <div class="panel-heading">Приход не создан</div>
            <div class="panel-body">
                <form action="/createnewshipmentin">
                    <button type="submit" class="btn btn-default">Создать приход</button>
                </form>
            </div>
        </div>
    </c:if>

    <c:if test="${currentShipment != null}">
        <h1 align="center">Приход</h1>
        <br>

        <!-- Tables of products per each category -->
        <jsp:include page="display_products_per_category.jsp"/>

        <c:if test="${formInputError == null}">
            <div class="panel panel-info">
                <div class="panel-heading">Оформление прихода товара</div>
                <div class="panel-body">

                    <!-- Choose a product to sell-->
                    <jsp:include page="add_new_product.jsp"/>

                </div>
            </div>
        </c:if>

        <c:if test="${formInputError != null}">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <c:out value="${formInputError}"/>
                </div>
                <div class="panel-body">

                    <!-- Choose a product to sell-->
                    <jsp:include page="add_new_product.jsp"/>

                </div>
            </div>
        </c:if>

        <div class="well">
            <div align="center">
                <h3>Приход товара на
                    <c:out value="${totalSum}"/>
                    рублей.
                </h3>
                <form action="/closeCurrentShipmentIn">
                    <button type="submit" class="btn btn-success">Сохранить приход</button>
                </form>
            </div>
        </div>

    </c:if>


</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/getProductsForCategory.js"></script>
</body>
</html>