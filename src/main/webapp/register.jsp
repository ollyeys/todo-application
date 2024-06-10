<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>


<!DOCTYPE html>
<html>

<head>

    <title>Sign Up</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Подключение Bootstrap 5 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

</head>

<body>
<jsp:include page="./header.jsp"></jsp:include>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<div class="container mt-4 mb-5">

    <h2 class="mb-4">User Register Form</h2>
    <form action="<%=request.getContextPath()%>/register" method="post">
    <div class="col-md-6">
        <c:if test="${not empty NOTIFICATION}">
            <div class="alert alert-success" id="alert" role="alert">
                    <p>${NOTIFICATION}</p>
                </div>
            </c:if>

            <div class="mb-3">
                <label for="name" class="form-label">First Name:</label>
                <input type="text" class="form-control" id="name" placeholder="First Name" name="name" required>
            </div>

            <div class="mb-3">
                <label for="surname" class="form-label">Last Name:</label>
                <input type="text" class="form-control" id="surname" placeholder="Last Name" name="surname" required>
            </div>

            <div class="mb-3">
                <label for="username" class="form-label">User Name:</label>
                <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
            </div>

            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
    </div>
</div>
<jsp:include page="./footer.jsp"></jsp:include>
</body>

</html>
