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
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <center>
        <h1 class="display-4"> VOTING RESULT INFO</h1>
        <h1 class="display-4"><a href="candidate-view-result" class="btn btn-primary">RELOAD VOTING RESULT</a></h1>
    </center>
    <hr class="my-4">
    <div style="padding-bottom: 50px">
        <center>
            <h1 class="display-4">"${winner}"</h1>
        </center>
    </div>

    <div>
        <center>
            <h3 class="display-4">Candidate vote count</h3>
        </center>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-bordered table-sm" id="dtBasicExample" cellspacing="0" width="100%">
            <thead class="thead-dark">
            <tr>
                <th>CANDIDATE ID</th>
                <th>FIRST NAME</th>
                <th>LAST NAME</th>
                <th>EMAIL</th>
                <th>DL NUM</th>
                <th>MOBILE NUM</th>
                <th>VOTES COUNT</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${candidateVoteCountDetailsList}" var="candidateVoteCountDetails" varStatus="loop">
                <tr>
                    <td>${candidateVoteCountDetails.candidate.loginId }</td>
                    <td>${candidateVoteCountDetails.candidate.name }</td>
                    <td>${candidateVoteCountDetails.candidate.surname }</td>
                    <td>${candidateVoteCountDetails.candidate.emailId }</td>
                    <td>${candidateVoteCountDetails.candidate.dLNum }</td>
                    <td>${candidateVoteCountDetails.candidate.mobileNum }</td>
                    <td>${candidateVoteCountDetails.count }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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