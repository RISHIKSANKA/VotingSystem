<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Voter Register</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css" />">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/animate/animate.css" />">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/vendor/css-hamburgers/hamburgers.min.css" />">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/vendor/animsition/css/animsition.min.css" />">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/vendor/select2/select2.min.css" />">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/vendor/daterangepicker/daterangepicker.css" />">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/vendor/datepicker/css/jquery-uiv1.12.1.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/util.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css" />">
    <style type="text/css">
        body {
            background-color: #ebeeef;
        }
    </style>
</head>
<body class="">

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-form-title"
                 style="background-image: url(<c:url value="/resources/images/voter_reg.jpg"/>);">
                <span class="login100-form-title-1"> VOTER REGISTRATION FORM </span>
            </div>

            <form class="login100-form validate-form" action="voter-registration" method="post">
                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="Firstname is required">
                    <span class="label-input100">First Name</span> <input
                        class="input100" type="text" name="name"
                        placeholder="Enter firstname"> <span
                        class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="Surname is required">
                    <span class="label-input100">Surname</span> <input
                        class="input100" type="text" name="surname"
                        placeholder="Enter surname"> <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="DOB is required">
                    <span class="label-input100">DOB</span>
                    <p>
                        <input type="text" id="datepicker" size="30" pattern="[0-9]{2}-[0-9]{2}-[0-9]{4}"
                               name="dateOfBirth">
                    </p>
                    <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="DL Number is required">
                    <span class="label-input100">DL Number</span> <input
                        class="input100" type="text" name="dLNum"
                        placeholder="Enter DL Number"> <span class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="EMAILID is required">
                    <span class="label-input100">EMAILID</span> <input
                        class="input100" type="text" name="emailId"
                        placeholder="Enter EMAILID" pattern="[a-zA-Z0-9]+[@]{1}[a-zA-Z]+[.]{1}[a-zA-z]{2,4}"> <span
                        class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="Mobile Number is required">
                    <span class="label-input100">Mobile Number</span> <input
                        class="input100" type="text" name="Mobile_number"
                        placeholder="Enter Mobile Number"> <span
                        class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="Login is required">
                    <span class="label-input100">LOGIN ID</span> <input
                        class="input100" type="text" name="loginId"
                        placeholder="Enter Login Id"> <span
                        class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-18"
                     data-validate="Password is required">
                    <span class="label-input100">Password</span> <input
                        class="input100" type="password" name="password"
                        placeholder="Enter password"> <span
                        class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-18"
                     data-validate="Password is required">
                    <span class="label-input100">Confirm Password</span> <input
                        class="input100" type="password" name="pass"
                        placeholder="Confirm password"> <span
                        class="focus-input100"></span>
                </div>
                <div class="wrap-input100">
                    <span class="label-input100">Hint Question</span> <select
                        class="form-control" id="sel1" name="hintQuestion">
                    <option>Select..</option>
                    <option value="Who is your childhood friend?">Who is your childhood friend?</option>
                    <option value="Who is your favourite hero?">Who is your favourite hero?</option>
                    <option value="What is your pet's name?">What is your pet's name?</option>
                </select>
                </div>
                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="Answer is required">
                    <span class="label-input100">Hint Answer</span> <input
                        class="input100" type="text" name="hintAnswer"
                        placeholder="Enter Hint Answer"> <span
                        class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-26">
                    <span class="label-input100">Declaration</span>
                    <a href="declaration" target="_blank" class="btn btn-link">View Declaration</a>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">SignUp</button>
                </div>
                <div class="${errorcss}" role="alert">${errormessage}</div>
            </form>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/vendor/jquery/jquery-3.2.1.min.js" />"></script>
<script src="<c:url value="/resources/vendor/animsition/js/animsition.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/popper.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/vendor/select2/select2.min.js" />"></script>
<script src="<c:url value="/resources/vendor/daterangepicker/moment.min.js" />"></script>
<script src="<c:url value="/resources/vendor/daterangepicker/daterangepicker.js" />"></script>
<script src="<c:url value="/resources/vendor/countdowntime/countdowntime.js" />"></script>
<script src="<c:url value="/resources/vendor/datepicker/js/jquery-1.12.4.js" />"></script>
<script src="<c:url value="/resources/vendor/datepicker/js/jquery-ui.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>

</body>
</html>