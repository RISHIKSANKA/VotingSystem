<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>HOME</title>
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="resources/vendor/fontawesome-free/css/all.min.css"
          rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">

    <center>
        <h1 class="my-4" style=" padding-top: 35px">Welcome to the Online Voting System</h1>
    </center>
    <div class="row">

        <div class="col-4 md-3 ">
            <div class="card h-100">
                <center>
                    <h5 class="card-header">Candidate SignIn</h5>
                </center>
                <div class="card-body">
                    <img src="resources/images/login_pic.jpg" class="card-img-top">
                    <p class="card-text"></p>
                </div>
                <div class="card-footer">
                    <center>
                        <a href="login" class="btn btn-primary">HIT ME!</a>
                    </center>
                </div>
            </div>
        </div>
        <div class="col-4  md-3">
            <div class="card h-100">
                <center>
                    <h5 class="card-header">Candidate SignUp</h5>
                </center>
                <div class="card-body">
                    <img src="resources/images/signUp.jpg" class="card-img-top">
                    <p class="card-text"></p>
                </div>
                <div class="card-footer">
                    <center>
                        <a href="register" class="btn btn-primary">HIT ME!</a>
                    </center>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
