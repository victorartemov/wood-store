﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form action="/${path}" method="post" accept-charset="utf-8" align="center">
    <label style="font-size:18px">Категория: </label>

    <select class="btn btn-default" name="selectCategory" id="selectCategory">
        <option selected disabled hidden>Выберите категорию</option>
        <c:forEach items="${allCategories}" var="category">
            <option value="${category.id}">${category.title}</option>
        </c:forEach>
    </select>

    <button type="button" class="btn btn-success" id="createNewCategoryButton"
            data-toggle="modal" data-target="#createCategoryModal">New</button>

    <label style="font-size:18px; margin-left:20px">Название: </label>
    <select class="btn btn-default" id="productSelect" name="selectProduct">
        <option selected disabled hidden>Название товара</option>
        <!-- Automatically generates with javascript function -->
    </select>

    <button type="button" class="btn btn-success" id="createNewProductButton"
            data-toggle="modal" data-target="#createProductModal">New</button>

    <label style="font-size:20px; margin-left:25px">Количество: </label>
    <input type="text" id="inputQuantity" style="margin-right:2px; width: 45px" name="quantity"
           placeholder="0">
    <input type="submit" class="btn btn-info" value="Добавить">
</form>
