<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700,900&display=swap&subset=cyrillic"
          rel="stylesheet">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/img/favicon.ico"
          type="image/x-icon">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        <%@include file='css/base.css' %>
        <%@include file='css/main.css' %>
    </style>
    <title>Auto Market</title>
    <script src="script.js" type="text/javascript"></script>
</head>

<body>
<div class="container">

    <div class="wrapper">
        <div class="header">
            <div class="jumbotron jumbotron-fluid">
                <c:if test="${user.name != null}">
                    <div class="alert alert-primary info" role="alert">
                        <div>На этой странице можно поменять статус объявления
                        </div>
                        <div>
                            <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">
                                На главную</a>
                        </div>
                        <div class="row-cols-md-1">
                            <a href="<%=request.getContextPath()%>/login.jsp" class="alert-link">
                                Выйти </a>
                        </div>
                        <div>
                            <a href="upload.jsp" class="btn btn-outline-primary">Добавить
                                объявление</a>
                        </div>
                    </div>
                </c:if>
                <div class="container, jumbotron__head">
                    <h1 class="display-6">Объявления о продаже автомобилей</h1>
                    <p class="lead">Выбери свой автомобиль мечты по реальным ценам!</p>
                </div>
            </div>
        </div>
        <div class="wrapper__content">
            <div class="row row-cols-1 row-cols-md-4 card-group">
                <c:forEach items="${posts}" var="post">
                    <div class="col-sm-3 col-width">
                        <div class="card">
                            <div class="card-header">
                                <h5 id="status">
                                    <c:if test="${post.status == true}">
                                        Продается
                                    </c:if>
                                    <c:if test="${post.status == false}">
                                        Снято с продажи
                                    </c:if>
                                </h5>
                            </div>

                            <c:if test="${post.photo == null}">
                                <img src="img/car.png" class="card-img-top" alt="car image">
                            </c:if>
                            <c:if test="${post.photo != null}">
                                <img src="<c:url value='/download?name=${post.photo.name}'/>"
                                     class="card-img-top"
                                     alt="car image">
                            </c:if>
                            <ul class="list-group">
                                <li class="list-group-item">Марка:
                                    <c:out value="${post.mark}"/>
                                </li>
                                <li class="list-group-item">Модель: <c:out
                                        value="${post.model}"/>
                                </li>
                                <li class="list-group-item">категория:
                                    <c:out value="${post.type}"/>
                                </li>
                                <li class="list-group-item">тип кузова:
                                    <c:out value="${post.bodyType}"/>
                                </li>
                                <li class="list-group-item">топливо:
                                    <c:out value="${post.fuel}"/>
                                </li>
                                <li class="list-group-item">год выпуска:
                                    <c:out value="${post.manufacturedYear}"/> г.
                                </li>
                                <li class="list-group-item">пробег:
                                    <c:out value="${post.mileage}"/> км
                                </li>
                                <li class="list-group-item">цена:
                                    <c:out value="${post.price}"/> р.
                                </li>
                                <li class="list-group-item">email:
                                    <c:out value="${post.user.email}"/>
                                </li>
                            </ul>
                            <div class="alert alert-warning" role="alert">
                                <div class="form-check">
                                    <form action="<%=request.getContextPath()%>/status"
                                          method="GET">
                                        <c:set var="postId" scope="session" value="${post.id}"/>
                                        <c:set var="status" scope="session" value="${post.status}"/>
                                        <label class="form-check-label" for="reverse">
                                            <input type="hidden"
                                                   name="id" value="${postId}"
                                                   id="reverse">
                                            <input type="hidden" name="status" value="${status}">
                                            <c:if test="${status}">
                                                <button type="submit" class="btn btn-info">Cнять с
                                                    продажи
                                                </button>
                                            </c:if>
                                            <c:if test="${!status}">
                                                <button type="submit"
                                                        class="btn btn-info">Объявить в продажу
                                                </button>
                                            </c:if>
                                        </label>
                                    </form>
                                </div>
                            </div>

                            <div class="card-footer">
                                <small class="text-muted">
                                    Дата публикации
                                    <span title="${post.data}">
                                        <fmt:formatDate value="${post.data}"
                                                        pattern="dd.MM.yyyy HH:mm"/>
                                    </span>
                                </small>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>

</html>