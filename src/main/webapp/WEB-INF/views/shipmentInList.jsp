<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Выбор прихода</title>

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
        <h1>Выбор прихода</h1>
    </div>

    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>

    <div class="panel panel-default">
        <div class="panel-heading">Выберите дату</div>
        <div class="panel-body">
            <form>
                <input class="form-control" id="datePicker" name="date" placeholder="дд/мм/гггг" type="text"
                       style="width:200px"/>
                <input type="submit" class="btn btn-primary" id="findClickButton" value="Найти"
                       onclick="findShipments()">
            </form>
        </div>
    </div>


</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<script src="${contextPath}/resources/js/datepickerWork.js"></script>
<script src="${contextPath}/resources/js/findShipments.js"></script>
</body>
</html>