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
                <div class="nav-link">
                    <a href="<%=request.getContextPath()%>/WEB-INF/index.jsp" class="nav-pills">На
                        главную</a>
                </div>
            </div>
        </c:if>

        <div class="container, jumbotron__head">
            <h1 class="display-6">Добавление нового объявления</h1>
            <p class="lead">Для публикации объявления необходимо заполнить все поля</p>
        </div>
    </div>

    <form action="<c:url value='/create.do'/>" method="POST">
        <div class="form__content">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="mark">Марка автомобиля</span>
                </div>
                <input type="text" class="form-control" name="mark" placeholder="Введите марку">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="model" >Модель автомобиля</span>
                </div>
                <input type="text" class="form-control" placeholder="Введите модель" name="model">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="color">Цвет автомобиля</span>
                </div>
                <input type="text" class="form-control" name="color" placeholder="Введите цвет">
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="type">Тип автомобиля</label>
                </div>
                <select class="custom-select" id="type" name="type">
                    <option selected>Выберите тип автомобиля</option>
                    <option value="Легковой">Легковой</option>
                    <option value="Грузовой">Грузовой</option>
                    <option value="Автобус">Автобус</option>
                </select>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="manufacturedYear">Год выпуска</span>
                </div>
                <input type="text" class="form-control" name="year" placeholder="Год выпуска">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="mileage">Пробег</span>
                </div>
                <input type="text" class="form-control" name="mileage" placeholder="пробег, в км">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Тип
                        кузова</label>
                </div>
                <select class="custom-select" name="bodyType" id="bodyType">
                    <option selected>Выберите тип кузова</option>
                    <option value="Седан">Седан</option>
                    <option value="Купе">Купе</option>
                    <option value="Универсал">Универсал</option>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Тип двигателя</label>
                </div>
                <select class="custom-select" name="engine" id="inputGroupSelect01">
                    <option selected>Выберите тип двигателя</option>
                    <option value="Бензин">Бензин</option>
                    <option value="Дизель">Дизель</option>
                    <option value="Электрический">Электрический</option>
                </select>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="price">Цена</span>
                </div>
                <input type="text" class="form-control" name="price" placeholder="Цена в рублях">
            </div>
        </div>

        <div class="btn-submit">
            <button type="submit" class="btn btn-primary" style="width: 134px">Сохранить</button>
        </div>
    </form>
</div>
</body>

</html>