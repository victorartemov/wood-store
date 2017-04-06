<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Выбор расхода</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
</head>
<body>

<div class="container">

    <!-- Header -->
    <jsp:include page="header.jsp"/>

    <div class="page-header">
        <h1>Выбор расхода</h1>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">Выберите дату</div>
        <div class="panel-body">
            <input id="datePicker" name="date" placeholder="дд/мм/гггг" type="text"
                   style="width:150px"/>
            <button>Найти</button>
        </div>
    </div>

    <h1 id="countOfFoundRecords"></h1>

    <div id="dynamicTables"></div>

    <h2 id="noResultsText"></h2>
</div>
<script type="text/javascript">
    var path = "/getShipmentOuts?date=";
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="http://jquery-ui.googlecode.com/svn-history/r3004/trunk/ui/i18n/ui.datepicker-ru.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<script src="${contextPath}/resources/js/datepickerWork.js"></script>
<script src="${contextPath}/resources/js/findShipments.js"></script>
</body>
</html>