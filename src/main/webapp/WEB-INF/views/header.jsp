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
