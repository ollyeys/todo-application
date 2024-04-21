<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>To Do App</title>
    <!-- Подключение Bootstrap 5 CSS -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand">To Do App</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/list" class="nav-link">Todos</a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<div class="container mt-4">
    <h3 class="text-center">Your To-Do List</h3>
    <hr>
    <div class="container text-left">
        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add To-Do</a>
    </div>
    <br>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Target Date</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>


        <c:forEach var="task" items="${listTask}">
            <tr>
                <td><c:out value="${task.title}"/></td>
                <td><c:out value="${task.description}"/></td>
                <td><c:out value="${task.targetDate}"/></td>
                <td>
                    <a href="<%=request.getContextPath()%>/edit?id=<c:out value='${task.id}' />"
                       class="btn btn-primary">Edit</a>
                    <a href="<%=request.getContextPath()%>/delete?id=<c:out value='${task.id}' />"
                       class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>


        </tbody>
    </table>
</div>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>
