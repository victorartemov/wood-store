<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Logo -->
        <div class="navbar-header">
            <span class="navbar-brand">WOODSTORE</span>
        </div>

        <!-- Menu items -->
        <div>
            <ul class="nav navbar-nav">
                <li><a href="welcome">Склад</a></li>
                <li><a href="workday">Рабочий день</a></li>
                <li><a href="shipment-in">Приход</a></li>
                <li><a href="shipment-out">Расход</a></li>
                <li><a href="journal">Журнал</a></li>
            </ul>
        </div>

        <!-- Worker info/logout -->
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#"><span class="glyphicon glyphicon-user"></span> ${pageContext.request.userPrincipal.name}</a>
                </li>
                <li>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <a onclick="document.forms['logoutForm'].submit()">Выход</a>
                </li>
            </ul>
        </form>
    </div>
</nav>
