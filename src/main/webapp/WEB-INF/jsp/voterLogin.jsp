<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Voter Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/png" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css"
          href="resources/vendor/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="resources/css/util.css">
    <link rel="stylesheet" type="text/css" href="resources/css/main.css">
    <style type="text/css">
        body {
            background-color: #ebeeef;
        }
    </style>
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-form-title"
                 style="background-image: url(<c:url value="/resources/images/voter_reg.jpg"/>);">
                <span class="login100-form-title-1"> Voter Sign In </span>
            </div>

            <form class="login100-form validate-form" action="voter-login" method="POST">
                <div class="wrap-input100 validate-input m-b-26"
                     data-validate="Username is required">
                    <span class="label-input100">Username</span> <input
                        class="input100" type="text" name="username"
                        placeholder="Enter username" id="username"> <span
                        class="focus-input100"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-18"
                     data-validate="Password is required">
                    <span class="label-input100">Password</span> <input
                        class="input100" type="password" name="password"
                        placeholder="Enter password" id="pass"> <span
                        class="focus-input100"></span>
                </div>

                <div class="flex-sb-m w-full p-b-30">
                    <div class="contact100-form-checkbox">
                        <input class="input-checkbox100" id="remember_me" type="checkbox"
                               name="remember-me"> <label class="label-checkbox100"
                                                          for="remember_me"> Remember me </label>
                    </div>

                    <div>
                        <a href="forgotpassword" class="txt1"> Forgot Password? </a>
                    </div>
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">Login</button>
                </div>
                <div class="${errorcss}" role="alert">${errormessage}</div>
            </form>
        </div>
    </div>
</div>

<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="resources/vendor/animsition/js/animsition.min.js"></script>
<script src="resources/vendor/bootstrap/js/popper.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/vendor/select2/select2.min.js"></script>
<script src="resources/vendor/daterangepicker/moment.min.js"></script>
<script src="resources/vendor/daterangepicker/daterangepicker.js"></script>
<script src="resources/vendor/countdowntime/countdowntime.js"></script>
<script src="resources/js/main.js"></script>
<script>
    $(function () {

        if (localStorage.chkbx && localStorage.chkbx != '') {
            $('#remember_me').attr('checked', 'checked');
            $('#username').val(localStorage.usrname);
            $('#pass').val(localStorage.pass);
        } else {
            $('#remember_me').removeAttr('checked');
            $('#username').val('');
            $('#pass').val('');
        }

        $('#remember_me').click(function () {

            if ($('#remember_me').is(':checked')) {
                // save username and password
                localStorage.usrname = $('#username').val();
                localStorage.pass = $('#pass').val();
                localStorage.chkbx = $('#remember_me').val();
            } else {
                localStorage.usrname = '';
                localStorage.pass = '';
                localStorage.chkbx = '';
            }
        });
    });

</script>
</body>
</html>