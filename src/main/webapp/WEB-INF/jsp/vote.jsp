<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>VOTER HOME</title>
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

<header>
    <%--<img src="resources/images/passport-world-map-hero-1400x500.jpg" class="img-fluid" alt="Responsive image">--%>
    <%--<img src="resources/images/voting_bg.jpg" class="img-fluid" alt="Responsive image" style="height: 90px;width: auto">--%>
</header>

<!-- Page Content -->
<div class="container">

    <center>
        <h1 class="my-4" style=" padding-top: 50px;padding-bottom: 50px">VOTER PORTAL</h1>
    </center>

    <center>
        <h1 class="display-4"><a href="cast-vote" class="btn btn-primary">RELOAD CANDIDATES</a></h1>
    </center>

    <div class="row m-3" style="margin-top: 70px!important;">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <b>Vote now</b>
                </div>
                <div class="card-body">
                    <form action="cast-vote" method="post">
                        <div class="form-group row">
                            <label for="candidateId" class="col-sm-4 col-form-label">Select Candidate</label>
                            <div class="col-sm-8">
                                <select name="candidateId" id="candidateId" class="form-control">
                                    <c:forEach var="candidate" items="${candidateList}">
                                        <option value="${candidate.loginId}">${candidate.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <input type="submit" class="btn btn-primary mb-2 float-right" value="CAST VOTE">
                    </form>
                </div>
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
<script src="resources/vendor/animsition/js/animsition.min.js"></script>
<script src="resources/vendor/bootstrap/js/popper.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    $('#exampleModal').modal($("#modelcss").val());
</script>
</body>
</html>