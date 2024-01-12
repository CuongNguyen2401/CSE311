<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="logreg-forms">

		<c:if test="${param.incorrectAccount != null}">
			<div class="alert alert-danger">Username or password incorrect
			</div>
		</c:if>
		<c:if test="${param.accessDenied != null}">
			<div class="alert alert-danger">you Not authorize</div>
		</c:if>
		<form class="form-signin" action="j_spring_security_check"
			method="POST">
			<input type="hidden" name="mode" value="login">

			<h1 class="h3 mb-3 font-weight-normal" style="text-align: center">
				Sign in</h1>
			<div class="social-login" style="display: flex">
				<a style="margin-right: 2px;" class="btn facebook-btn social-btn"
					href="https://www.facebook.com/dialog/oauth?client_id=3549214752035027&redirect_uri=https://localhost:8443/AccessFacebook/login-facebook"><span><i
						class="fab fa-facebook-f"></i> Sign in with Facebook</span> </a> 
						
						
						<a
					class="btn google-btn social-btn" style="margin-left: 2px;"
					href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/login-google&response_type=code
    &client_id=203434021313-q6ukgr4gjiuc1e0l9a17v6vuk2d1fgt1.apps.googleusercontent.com&approval_prompt=force"><span><i
						class="fab fa-google-plus-g"></i> Sign in with Google+</span> </a>



			</div>
			<p style="text-align: center">OR</p>
			<span class="errorMessage">${msgError}</span> <input
				value="${usernameC}" type="text" id="inputEmail"
				class="form-control" placeholder="Account" required="" autofocus=""
				name="j_username"> <input value="${passwordC}"
				type="password" id="inputPassword" name="j_password"
				class="form-control" placeholder="Password" required="">
			<div class="form-check mb-0 mt-1">
				<input name="remember" style="margin-top: 6px"
					class="form-check-input me-2 align-self-lg-center" type="checkbox"
					value="" id="form2Example3" /> <label class="form-check-label"
					for="form2Example3"> Remember me </label>
			</div>
			<button class="btn btn-success btn-block" type="submit">
				<i class="fas fa-sign-in-alt"></i> Sign in
			</button>
			<a href="#" id="forgot_pswd">Forgot password?</a>
			<hr>
			<!-- <p>Don't have an account!</p>  -->
			<button class="btn btn-primary btn-block" type="button"
				id="btn-signup">
				<i class="fas fa-user-plus"></i> Sign up New Account
			</button>

		</form>

		<form action="reset-password" class="form-reset" method="POST">
			<input name="reset_email" type="email" id="resetEmail"
				class="form-control" placeholder="Email address" required=""
				autofocus=""> <input name="reset_account" type="text"
				class="form-control" placeholder="Account" required="" autofocus="">
			<button class="btn btn-primary btn-block" type="submit">Reset
				Password</button>
			<a href="#" id="cancel_reset"><i class="fas fa-angle-left"></i>
				Back</a>
		</form>


		<form class="form-signup" id="signUpForm">

			<h1 class="h3 mb-3 font-weight-normal" style="text-align: center">
				Sign up</h1>
			<div class="wrapcol">
				<input name="username" type="text" id="username"
					class="form-control" placeholder="UserName" required=""
					autofocus="" onkeyup="validateUsername()"> <span
					id="username-error"></span> <span id="username-valid"></span>
			</div>

			<div class="wrapcol">
				<input name="fullName" type="text" id="fullName"
					class="form-control" placeholder="Full Name" required autofocus=""
					onkeyup="validateFullName()"> <span id="fullname-error"></span>
				<span id="fullname-valid"></span>
			</div>

			<div class="wrapcol">
				<input name="password" type="password" id="password"
					class="form-control" placeholder="Password" required autofocus=""
					onkeyup="validatePassword()"> <span id="password-error"></span>
				<span id="password-valid"></span>

			</div>
			<div class="wrapcol">
				<input name="rePassword" type="password" id="rePassword"
					class="form-control" placeholder="Repeat Password" required
					autofocus="" onkeyup="validateRePassword()"> <span
					id="repassword-error"> </span> <span id="repassword-valid"></span>
			</div>


			<button id="signUpAccount" class="btn btn-primary btn-block"
				type="button">
				<i class="fas fa-user-plus"></i> Sign Up
			</button>
			<span id="submit-error"></span> <a href="#" id="cancel_signup"><i
				class="fas fa-angle-left"></i> Back</a>
		</form>
		<br>

	</div>

	<div id="toasts"></div>
	<c:if test="${param.msg != null}">
		<input id="message" type="hidden" value="${param.msg}">
	</c:if>



</body>
</html>