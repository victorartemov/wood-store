<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Расход</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <!-- Header -->
    <jsp:include page="header.jsp"/>

    <c:if test="${currentShipment == null}">
        <h1 align="center">Расход</h1>
        <br>

        <div class="panel panel-warning">
            <div class="panel-heading">Расход не создан</div>
            <div class="panel-body">
                <form action="/createNewShipmentOut">
                    <button type="submit" class="btn btn-default">Создать расход</button>
                </form>
            </div>
        </div>
    </c:if>

    <c:if test="${currentShipment != null}">
        <h1 align="center">Расход</h1>
        <br>

        <c:forEach items="${categories}" var="category">
            <h2>
                <c:out value="${category.title}"/>
            </h2>
            <!-- Check the type of a category -->
            <c:choose>

                <c:when test="${category.simple == true}">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover">
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
                                            <td>
                                                <fmt:formatNumber pattern="0.#"
                                                                  value="${product.price*product.amount}"/>
                                            </td>
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
                        <table class="table table-bordered table-hover">
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
                                            <td>
                                                <fmt:formatNumber pattern="0.##"
                                                                  value="${product.length*product.amount*0.096}"/>
                                            </td>
                                            <td>${product.price}</td>
                                            <td>
                                                <fmt:formatNumber pattern="0.#"
                                                                  value="${product.length*product.amount*0.096*product.price}"/>
                                            </td>
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

        <c:if test="${formInputError == null}">
            <div class="panel panel-info">
                <div class="panel-heading">Оформление расхода товара</div>
                <div class="panel-body">

                    <!-- Choose a product to sell-->
                    <form action="/createNewShipmentOutProduct" method="post" accept-charset="utf-8" align="center">
                        <label style="font-size:20px">Категория: </label>

                        <select class="btn btn-default" name="selectCategory"
                                onchange="selectProductsForCategory(this)">
                            <c:forEach items="${allCategories}" var="category">
                                <option value="${category.id}">${category.title}</option>
                            </c:forEach>
                        </select>

                        <label style="font-size:20px; margin-left:25px">Название: </label>
                        <select class="btn btn-default" id="productSelect" name="selectProduct">
                            <!-- Automatically generates with javascript function -->
                        </select>

                        <label style="font-size:20px; margin-left:25px">Количество: </label>
                        <input type="text" style="margin-right:25px; width: 50px" name="quantity">
                        <input type="submit" class="btn btn-info" value="Добавить">
                    </form>

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
                    <form action="/createNewShipmentOutProduct" method="post" accept-charset="utf-8" align="center">
                        <label style="font-size:20px">Категория: </label>

                        <select class="btn btn-default" name="selectCategory"
                                onchange="selectProductsForCategory(this)">
                            <c:forEach items="${allCategories}" var="category">
                                <option value="${category.id}">${category.title}</option>
                            </c:forEach>
                        </select>

                        <label style="font-size:20px; margin-left:25px">Название: </label>
                        <select class="btn btn-default" id="productSelect" name="selectProduct">
                            <!-- Automatically generates with javascript function -->
                        </select>

                        <label style="font-size:20px; margin-left:25px">Количество: </label>
                        <input type="text" style="margin-right:25px; width: 50px" name="quantity">
                        <input type="submit" class="btn btn-info" value="Добавить">
                    </form>

                </div>
            </div>
        </c:if>

        <div class="well">
            <div align="center">
                <h3>Отправка товара на
                    <c:out value="${totalSum}"/>
                    рублей.
                </h3>
                <form action="/closeCurrentShipmentOut">
                    <button type="submit" class="btn btn-success">Сохранить расход</button>
                </form>
            </div>
        </div>

    </c:if>


</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
     function selectProductsForCategory(selectObject) {
        var value = selectObject.value;

        var request = '/getProducts?id=' + value;

        $.ajax(request, {
            method : 'get',
            success: function(data) {

                var size = data.length;
                var result = "";

                for (var i=0; i!=size; ++i) {
                     result += "<option>";
                     result += data[i].title;
                     result += "</option>";
                }
                $('#productSelect').html(result);
            }
        });
    }
</script>
</body>
</html>