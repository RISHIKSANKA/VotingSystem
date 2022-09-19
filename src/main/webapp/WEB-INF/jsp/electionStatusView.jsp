<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
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
        <a class="navbar-brand" href="adminhome">Online Voting System</a>
        <button class="navbar-toggler navbar-toggler-right" type="button"
                data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link active" href="adminhome">Home</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item"><a class="nav-link" href="#">Contact</a>
                </li>
                <li class="nav-item dropdown">
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                        <a class="dropdown-item" href="adminlogout">Logout</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <center>
        <h1 class="display-4"> VOTING CONTROL</h1>
        <h1 class="display-4"><a href="election-status" class="btn btn-primary">RELOAD ELECTION STATUS</a></h1>
    </center>
    <hr class="my-4">

    <div class="table-responsive">
        <table class="table table-striped table-bordered table-sm" id="dtBasicExample" cellspacing="0" width="100%">
            <thead class="thead-dark">
            <tr>
                <th>NAME</th>
                <th>CURRENT STATUS</th>
                <th colspan="3">
                    <center>MODIFY STATUS</center>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${applicationPropertiesList}" var="applicationProperty" varStatus="loop">
                <c:choose>
                    <c:when test="${applicationProperty.name == VOTING_STATUS_QUALIFIER}">
                        <tr>
                            <td>VOTING STATUS</td>
                            <td>${applicationProperty.value}</td>
                            <td>
                                <form action="update-voting-status" method="post">
                                    <input type="hidden" name="votingStatus" value="NOT_STARTED">
                                    <input type="submit" value="DO NOT START" class="btn btn-danger">
                                </form>
                            </td>
                            <td>
                                <form action="update-voting-status" method="post">
                                    <input type="hidden" name="votingStatus" value="IN_PROGRESS">
                                    <input type="submit" value="START NOW" class="btn btn-danger">
                                </form>
                            </td>
                            <td>
                                <form action="update-voting-status" method="post">
                                    <input type="hidden" name="votingStatus" value="COMPLETED">
                                    <input type="submit" value="END NOW" class="btn btn-danger">
                                </form>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="table-responsive" style="padding-top: 50px">
        <table class="table table-striped table-bordered table-sm" id="dtBasicExample" cellspacing="0" width="100%">
            <thead class="thead-dark">
            <tr>
                <th>NAME</th>
                <th>CURRENT STATUS</th>
                <th colspan="2">
                    <center>MODIFY STATUS</center>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${applicationPropertiesList}" var="applicationProperty" varStatus="loop">
                <c:choose>
                    <c:when test="${applicationProperty.name == VOTING_RESULT_STATUS_QUALIFIER}">
                        <tr>
                            <td>VOTING RESULT STATUS</td>
                            <td>${applicationProperty.value}</td>
                            <td>
                                <form action="update-voting-result-status" method="post">
                                    <input type="hidden" name="votingResultStatus" value="DECLARED">
                                    <input type="submit" value="DECLARE" class="btn btn-danger">
                                </form>
                            </td>
                            <td>
                                <form action="update-voting-result-status" method="post">
                                    <input type="hidden" name="votingResultStatus" value="NOT_DECLARED">
                                    <input type="submit" value="DO NOT DECLARE" class="btn btn-danger">
                                </form>
                            </td>
                        </tr>
                    </c:when>
                </c:choose>
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