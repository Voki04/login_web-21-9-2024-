<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
</head>
<body>
    <h2>Quên Mật Khẩu?</h2>

    <form action="forgot_password" method="post">
        <!-- Display alert message if present -->
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>

        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
                </div>
            </label>
        </section>

        <section>
            <label class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                    <input type="email" placeholder="Email" name="email" class="form-control" required>
                </div>
            </label>
        </section>

        <button type="submit" class="btn btn-primary">Hoàn thành</button>
    </form>

    <c:if test="${password != null}">
        <h3>Your password is: ${password}</h3>
    </c:if>

    <a href="${pageContext.request.contextPath}/login">Already have an account? Login here</a>
</body>
</html>
