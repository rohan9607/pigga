<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>

<%
String error = request.getParameter("error");
if (Objects.equals(error, "invalid_password")){
    error = "Invalid Password!";
}
else if (Objects.equals(error, "invalid_username")){
    error = "Invalid Email Address!";
}
else {
    error = "";
}
%>
<!doctype html>
<html lang="en">
<head>
    <title>Login to pigga</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="css/style.css">

</head>
<body class="img js-fullheight" style="background-image: url(images/bg.jpg);">
<section class="ftco-section">

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center mb-5">
                <h2 class="heading-section">Login to Pigga</h2>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-4">
                <div class="login-wrap p-0">
                    <h3 class="mb-4 text-center">Get Ready to be served</h3>
                    <span style="color: #fcfcfc; font-size: 1.2rem; border-radius: 5px;display: flex; width: 70%" class="text-center"><%=error%></span>
                    <form action="../validate" class="signin-form" method="post">
                        <div class="form-group">
                            <input type="email" class="form-control" placeholder="Email" name="user_mail" required>
                        </div>
                        <div class="form-group">
                            <input id="password-field" type="password" class="form-control" placeholder="Password" name="password" required>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="form-control btn btn-primary submit px-3">Sign In</button>
                        </div>
                    </form>
                    <div id="login_link">
                        <a href="../sign_up/signup.jsp" id="login">Sign Up</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="js/jquery.min.js"></script>
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>

