<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700,900&display=swap&subset=cyrillic"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        <%@include file='css/base.css' %>
        <%@include file='css/createpost.css' %>
    </style>
    <title>Create AD</title>
    <script src="script.js" type="text/javascript"></script>
</head>

<body>
<div class="container">
    <div class="jumbotron jumbotron-fluid">
        <c:if test="${user.name != null}">
            <div class="alert alert-success info" role="alert">
                <div>
                    <a class="nav-link" href="<%=request.getContextPath()%>/exit.do">
                        Текущий пользователь: <c:out value="${user.name}"/> | Выйти</a>
                </div>
            </div>
        </c:if>

        <div class="container, jumbotron__head">
            <h1 class="display-6">Добавление фото для нового объявления</h1>
            <p class="lead">Фото повышает шансы на успешную продажу, но добавлять его не
                обязательно</p>
        </div>
    </div>
    <form action="<c:url value='/upload.do'/>" method="post" enctype="multipart/form-data">

        <div class="input-photo">
            <h4>Загрузите фото</h4>
            <div class="input-group-prepend">
                <div class="checkbox">
                    <input type="file" name="file" id="photo">
                </div>
            </div>
        </div>
        <div class="btn-submit">
            <button type="submit" class="btn btn-primary" style="width: 134px">Сохранить</button>
        </div>
    </form>
</div>
</body>

</html>