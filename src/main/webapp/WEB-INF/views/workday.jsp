<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="path" value="sell-the-product" scope="request"/>
<c:set var="deleteItemsPath" value="delete-product-from-workday" scope="request"/>
<c:set var="categoriesToIterate" value="${soldCategories}" scope="request"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Рабочий день</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <!-- Header -->
    <jsp:include page="header.jsp"/>

    <!-- Current workday was not created yet -->
    <c:if test="${currentWorkDay == null}">
        <h1 align="center">Рабочий день</h1>
        <br>

        <div class="panel panel-warning">
            <div class="panel-heading">Новый день не создан</div>
            <div class="panel-body">
                <form action="/create-new-day">
                    <button type="submit" class="btn btn-default">Создать новый день</button>
                </form>
            </div>
        </div>
    </c:if>

    <!-- Current workday already exists -->
    <c:if test="${currentWorkDay != null}">

        <c:choose>
            <c:when test="${dayIsOpen == true}">
                <h1 align="center">Рабочий день
                    <c:out value="${currentWorkDay.date}"/>
                </h1>

                <br>
                <c:if test="${fn:length(currentWorkDay.products) == 0}">
                    <c:if test="${formInputError == null}">
                        <!-- Creating first product in this day-->
                        <div class="panel panel-info">
                            <div class="panel-heading">Сегодня еще нет продаж</div>
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
                </c:if>

                <c:if test="${fn:length(currentWorkDay.products) != 0}">

                    <!-- Tables of products per each category -->
                    <jsp:include page="display_products_per_category_with_deleting.jsp"/>

                    <c:if test="${formInputError == null}">
                        <div class="panel panel-info">
                            <div class="panel-heading">Оформление продажи товара</div>
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
                            <h3>Касса:
                                <c:out value="${totalSum}"/>
                                рублей.
                            </h3>
                            <form action="/close-the-day">
                                <button type="submit" class="btn btn-success">Сохранить рабочий день</button>
                            </form>
                        </div>
                    </div>
                </c:if>
            </c:when>

            <c:when test="${dayIsOpen == false}">
                <div class="panel panel-warning">
                    <div class="panel-heading">День закрыт</div>
                    <div class="panel-body">
                        <form action="/open-the-day">
                            <button type="submit" class="btn btn-default">Открыть день</button>
                        </form>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </c:if>


</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/getProductsForCategory.js"></script>
</body>
</html>