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
                    <jsp:include page="add_completely_new_product.jsp"/>

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
                    <jsp:include page="add_completely_new_product.jsp"/>

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

    <div id="createCategoryModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-sm">

            <!-- Modal content-->
            <div class="modal-content">
                <form>
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Создание категории</h4>
                    </div>
                    <div class="modal-body">

                        <div class="form-group">
                            <label for="categoryTitle">Название категории</label>
                            <input type="text" placeholder="название" style="width:115px;" id="categoryTitle">
                        </div>
                        <div class="form-group">
                            <label for="categoryType">Тип категории</label>
                            <select class="btn btn-default" name="categoryType" id="categoryType">
                                <option value="0">Штучные товары</option>
                                <option value="1">Квадратура</option>
                            </select>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Создать</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Закрыть</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/getProductsForCategory.js"></script>

<script>
    $(function(){
    $('#createCategoryModal').on('submit', function(e){
        e.preventDefault();
        alert('works!');
        $('#createCategoryModal').modal('hide');
    });
});
</script>

</body>
</html>