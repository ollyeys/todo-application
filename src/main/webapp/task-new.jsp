<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Task</title>
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

<div class="container col-md-5">
    <div class="card mt-4">
        <div class="card-body">
            <h2 class="mt-4">Create New Task</h2>
            <form action="<%= request.getContextPath() %>/insert" method="post">
                <div class="mb-3">
                    <label for="title" class="form-label">Task Title</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="Enter task title"
                           required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Task Description</label>
                    <input type="text" class="form-control" id="description" name="description"
                           placeholder="Enter task description">
                </div>
                <div class="mb-3">
                    <label for="isDone" class="form-label">Task Status</label>
                    <select class="form-select" id="isDone" name="isDone">
                        <option value="false">In Progress</option>
                        <option value="true">Complete</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="targetDate" class="form-label">Task Target Date</label>
                    <input type="date" class="form-control" id="targetDate" name="targetDate" required>
                </div>
                <button type="submit" class="btn btn-success">Create Task</button>
            </form>
        </div>
    </div>
</div>
<!-- Включите футер или другие элементы, если необходимо -->
</body>
</html>
