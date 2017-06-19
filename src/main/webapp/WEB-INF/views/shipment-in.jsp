<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="path" value="create-new-shipment-in-product" scope="request"/>
<c:set var="createNewCategoryPath" value="create-new-category" scope="request"/>
<c:set var="createNewProductPath" value="create-new-product-from-modal" scope="request"/>
<c:set var="categoriesToIterate" value="${categories}" scope="request"/>
<c:set var="deleteItemsPath" value="delete-product-from-shipment-in" scope="request"/>


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

    <form:errors path="product.*"/>

    <c:if test="${currentShipment == null}">
        <h1 align="center">Приход</h1>
        <br>

        <div class="panel panel-warning">
            <div class="panel-heading">Приход не создан</div>
            <div class="panel-body">
                <form action="/create-new-shipment-in">
                    <button type="submit" class="btn btn-default">Создать приход</button>
                </form>
            </div>
        </div>
    </c:if>

    <c:if test="${currentShipment != null}">
        <h1 align="center">Приход</h1>
        <br>

        <!-- Tables of products per each category -->
        <jsp:include page="display-products-per-category-with-deleting.jsp"/>

        <c:if test="${formInputError == null}">
            <div class="panel panel-info">
                <div class="panel-heading">Оформление прихода товара</div>
                <div class="panel-body">

                    <!-- Choose a product to sell-->
                    <jsp:include page="add-completely-new-product.jsp"/>

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
                    <jsp:include page="add-completely-new-product.jsp"/>

                </div>
            </div>
        </c:if>

        <div class="well">
            <div align="center">
                <h3>Приход товара на
                    <c:out value="${totalSum}"/>
                    рублей.
                </h3>
                <form action="/close-current-shipment-in">
                    <button type="submit" class="btn btn-success">Сохранить приход</button>
                </form>
            </div>
        </div>

    </c:if>

    <jsp:include page="category-creation-modal-window.jsp"/>
    <jsp:include page="product-creation-modal-window.jsp"/>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/getProductsForCategory.js"></script>
<script src="${contextPath}/resources/js/categoryCreationModalWindow.js"></script>
<script src="${contextPath}/resources/js/productCreationModalWindow.js"></script>
<script src="${contextPath}/resources/js/getCategories.js"></script>
<script>

</script>
</body>
</html>