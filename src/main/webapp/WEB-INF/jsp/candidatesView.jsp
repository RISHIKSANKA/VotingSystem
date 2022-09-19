<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <h1 class="display-4"> CANDIDATE LIST</h1>
		 <h1 class="display-4"><a href="view-all-candidates" class="btn btn-primary">RELOAD CANDIDATE LIST</a></h1>
        </center>
		 <hr class="my-4">
		 <div class="table-responsive">
		 <table class="table table-striped table-bordered table-sm" id="dtBasicExample" cellspacing="0" width="100%">
			 <thead class="thead-dark">
	            <tr>
	            	<th>S.no</th>
	                <th>NAME</th>
	                <th>SURNAME</th>
	                <th>EMAIL ID</th>
                    <th>DOB</th>
                    <%--<th>LOGIN ID</th>--%>
                    <th>VOTER ID</th>
                    <th>APPROVAL STATUS</th>
	                <th>DELETE</th>
                    <th>APPROVE</th>
                    <th>REJECT</th>
	            </tr>
	       	</thead>
	       	<tbody>
	        	<c:forEach items="${candidateList}" var="candidate" varStatus="loop">
	        		<tr>
	        			<th scope="row">${loop.index+1}</th>
	        			<td>${candidate.name }</td>
	        			<td>${candidate.surname }</td>
	        			<td>${candidate.emailId }</td>
                        <td>${candidate.dob }</td>
	        			<%--<td>${candidate.loginId }</td>--%>
                        <td>${candidate.dLNum }</td>
                        <%--<td>${candidate.approvalStatus }</td>--%>
                        <td><c:choose>
                            <c:when test="${candidate.approvalStatus == true}">
                                APPROVED
                            </c:when>
                            <c:otherwise>
                                NOT APPROVED
                            </c:otherwise>
                        </c:choose></td>
	        			<td><form action="delete-candidate" method="post">
	        				<input type="hidden" name="loginId" value="${candidate.loginId }">
	        				<input type="submit" value="Delete" class="btn btn-danger">
	        				</form>
	        			</td>
                        <td><form action="approve-candidate" method="post">
                            <input type="hidden" name="loginId" value="${candidate.loginId }">
                            <input type="submit" value="Approve" class="btn btn-primary">
                        </form>
                        </td>
                        <td><form action="reject-candidate" method="post">
                            <input type="hidden" name="loginId" value="${candidate.loginId }">
                            <input type="submit" value="Reject" class="btn btn-danger">
                        </form>
                        </td>
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