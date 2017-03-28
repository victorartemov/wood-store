<form action="/createnewproduct" method="post" accept-charset="utf-8" align="center">
    <label style="font-size:20px">Категория: </label>

    <select class="btn btn-default" name="selectCategory"
            onchange="selectProductsForCategory(this)">
        <c:forEach items="${categories}" var="category">
            <option value="${category.title}">${category.title}</option>
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
