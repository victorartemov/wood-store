<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${categoriesToIterate}" var="category">
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
                        <th>Удаление</th>
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
                                        <c:if test="${product.length == 0}">
                                            <fmt:formatNumber pattern="0.#"
                                                              value="${product.price*product.amount}"/>
                                        </c:if>
                                        <c:if test="${product.length != 0}">
                                            <fmt:formatNumber pattern="0.#"
                                                              value="${product.price*product.amount*product.length}"/>
                                        </c:if>
                                    </td>
                                    <td>
                                        <form action="/${deleteItemsPath}/${product.id}" method="post" accept-charset="utf-8">
                                            <button type="submit" class="btn btn-danger glyphicon glyphicon-trash"></button>
                                        </form>
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
                        <th>Удаление</th>
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
                                        <fmt:formatNumber pattern="0.#"
                                                          value="${product.length*product.amount*0.096}"/>
                                    </td>
                                    <td>${product.price}</td>
                                    <td>
                                        <fmt:formatNumber pattern="0.#"
                                                          value="${product.length*product.amount*0.096*product.price}"/>
                                    </td>
                                    <td>
                                        <!-- Delete sold product from workday -->
                                        <form action="/${deleteItemsPath}/${product.id}" method="post" accept-charset="utf-8">
                                            <button type="submit" class="btn btn-danger glyphicon glyphicon-trash"></button>
                                        </form>
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