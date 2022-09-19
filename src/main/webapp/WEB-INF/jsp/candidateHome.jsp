<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CANDIDATE HOME</title>
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="resources/vendor/fontawesome-free/css/all.min.css"
          rel="stylesheet" type="text/css">
</head>
<body>
<nav
        class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="candidate-home">Online Voting System</a>
        <button class="navbar-toggler navbar-toggler-right" type="button"
                data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link active" href="candidate-home">Home</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#">Contact</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownBlog" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Profile
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                        <a class="dropdown-item" href="edit-candidate-profile">Edit</a>
                        <a class="dropdown-item" href="logout">Logout</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <center>
        <h1 class="my-4" style=" padding-top: 50px;padding-bottom: 50px">CANDIDATE PORTAL</h1>
    </center>

    <div class="row">

        <div class="col-4 md-3 ">
            <div class="card h-100">
                <center>
                    <h5 class="card-header">EDIT PROFILE</h5>
                </center>
                <div class="card-body">
                    <img src="resources/images/profile.jpg" class="card-img-top">
                    <p class="card-text"></p>
                </div>
                <div class="card-footer">
                    <center>
                        <a href="edit-candidate-profile" class="btn btn-primary">HIT ME!</a>
                    </center>
                </div>
            </div>
        </div>

        <div class="col-4  md-3">
            <div class="card h-100">
                <center>
                    <h5 class="card-header">RESULT</h5>
                </center>
                <div class="card-body">
                    <img src="resources/images/result.jpg" class="card-img-top">
                    <p class="card-text"></p>
                </div>
                <div class="card-footer">
                    <center>
                        <a href="candidate-view-result" class="btn btn-primary ">HIT ME!</a>
                    </center>
                </div>
            </div>
        </div>
        <div class="col-4 md-3">
            <div class="card h-100">
                <center>
                    <h5 class="card-header">DOCUMENTS REQUIRED</h5>
                </center>
                <div class="card-body">
                    <img src="resources/images/todo.jpg" class="card-img-top">
                    <p class="card-text"></p>
                </div>
                <div class="card-footer">
                    <center>
                        <a href="document" target="_blank" class="btn btn-primary">HIT ME!</a>
                    </center>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Information</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="alert alert-success" role="alert">
                        <h3 class="">${modelbodycontent}</h3>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="modelcss" value="${modelscript}">

    <script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!--===============================================================================================-->
    <script src="resources/vendor/animsition/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script src="resources/vendor/bootstrap/js/popper.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $('#exampleModal').modal($("#modelcss").val());
    </script>
</body>
</html>