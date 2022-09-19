<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View Result</title>
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="resources/vendor/fontawesome-free/css/all.min.css"
          rel="stylesheet" type="text/css">
</head>
<body style="background-color: #e9ecef">
<nav
        class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="voter-home">Online Voting System</a>
        <button class="navbar-toggler navbar-toggler-right" type="button"
                data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link active" href="voter-home">Home</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#">Contact</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <center>
        <h1 class="display-4"> VOTE INFO</h1>
        <h1 class="display-4"><a href="view-cast-vote" class="btn btn-primary">RELOAD VOTE INFO</a></h1>
    </center>
    <hr class="my-4">
    <div>
        <center>
            <h1 class="display-4">"${voteInfo}"</h1>
        </center>
    </div>

</div>

<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.js "></script>
<script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="resources/vendor/animsition/js/animsition.min.js"></script>
<script src="resources/vendor/bootstrap/js/popper.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/vendor/materialdesign/js/mdb.min.js"></script>
</body>
</html>