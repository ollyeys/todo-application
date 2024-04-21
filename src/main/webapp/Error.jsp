<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>

    <title>Error</title>
    <!-- Подключение библиотеки Bootstrap CSS -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="./header.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h1 class="card-title">Error</h1>
                </div>
                <div class="card-body">
                    <p class="card-text">You are not registered. Please click the button below to sign up.</p>
                    <a href="register.jsp" class="btn btn-primary">Sign Up</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Подключение библиотеки Bootstrap JavaScript (необязательно) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>
