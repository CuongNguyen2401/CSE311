function toggleResetPswd(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle() // display:block or none
    $('#logreg-forms .form-reset').toggle() // display:block or none
}

function toggleSignUp(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle(); // display:block or none
    $('#logreg-forms .form-signup').toggle(); // display:block or none
}

$(()=>{
    // Login Register Form
    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
    $('#logreg-forms #btn-signup').click(toggleSignUp);
    $('#logreg-forms #cancel_signup').click(toggleSignUp);
})

$('#signUpAccount').click(function (e) {
	    e.preventDefault();
	    var password = $('#password').val().trim();
	     var rePassword = $('#rePassword').val().trim();
	    if(password===rePassword){
	    	var data = {};
		    var userName = $('#username').val().trim();
		    var fullName = $('#fullName').val().trim();  
		    data["username"] = userName
		    data["fullName"] = fullName;
		    data["password"] = password;
		  
		   
		    if(validateSignUpForm()){
		    	signUp(data)
		    }
		   
		    
	    }else{
	    	var repasswordError = document.getElementById("repassword-error")
	    	repasswordError.innerHTML = "Wrong repeated password"
	    		 var repasswordValid = document.getElementById("repassword-valid")
	    		 repasswordValid.innerHTML = ""
	    }
	    
	  
	});

function signUp(data) {
	$.ajax({
        url: '/api/user',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (result) {
        	window.location.href = "/login?msg=success_signUp";
        },
        error: function (error) {
        	window.location.href = "/login?msg=error_signUp";
        }
    });
}


var usernameError = document.getElementById("username-error")
var fullNameError = document.getElementById("fullname-error")
var passwordError = document.getElementById("password-error")
var repasswordError = document.getElementById("repassword-error")
var submitError = document.getElementById("submit-error")

function validateUsername() {
	 var userName = $('#username').val();
	 var userNameValid = document.getElementById("username-valid")
	 
	 if(userName.length==0){
		 usernameError.innerHTML = "Username is required"
			 userNameValid.innerHTML="";
			 return false;
	 }
	 if(!userName.match(/^[0-9A-Za-z]{6,16}$/)){
		 usernameError.innerHTML = "Enter your username"
			 userNameValid.innerHTML="";
			 return false;
	 }
	 usernameError.innerHTML=""
	 
	 userNameValid.innerHTML = `<i class="fa fa-check-circle" aria-hidden="true"></i>`
		 return true;
	 
	 
	 
}

function validateFullName() {
	  var fullName = $('#fullName').val()  
	   var fullNameValid = document.getElementById("fullname-valid")
	 
	 if(fullName.length==0){
		 fullNameError.innerHTML = "Full Name is required"
			 fullNameValid.innerHTML =""
			 return false;
	 }
	  
	  
	 if(!fullName.match(/^[a-zA-Z_ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\ ]*\s{1}[a-zA-Z_ÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\ ]*$/)){
		 fullNameError.innerHTML = "Enter your full name"
			 fullNameValid.innerHTML =""
			 return false;
	 }
	 
	 fullNameError.innerHTML="";
	 fullNameValid.innerHTML = `<i class="fa fa-check-circle" aria-hidden="true"></i>`
		 return true;
	 
}

function validatePassword() {
	 var password = $('#password').val()
	  var passwordValid = document.getElementById("password-valid")
	 
	 if(password.length==0){
		 passwordError.innerHTML = "Password is required"
			 passwordValid.innerHTML =""
			 return false;
	 }
	 if(!password.match(/^[0-9A-Za-z]{6,16}$/)){
		 passwordError.innerHTML = "Enter your password"
			 passwordValid.innerHTML =""
			 return false;
	 }
	 passwordError.innerHTML=""
	 passwordValid.innerHTML = `<i class="fa fa-check-circle" aria-hidden="true"></i>`
		 return true;
	 
	 
	 
}

function validateRePassword() {
	  var rePassword = $('#rePassword').val()
	   var repasswordValid = document.getElementById("repassword-valid")
	   
	 
	 if(rePassword.length==0){
		 repasswordError.innerHTML = "Repeated password is required"
			 repasswordValid.innerHTML = ""
			 return false;
	 }
	 if(!rePassword.match(/^[0-9A-Za-z]{6,16}$/)){
		 repasswordError.innerHTML = "Enter your repeated password"
			 repasswordValid.innerHTML = ""
			 return false;
	 }
	 repasswordError.innerHTML=""
	
	 repasswordValid.innerHTML = `<i class="fa fa-check-circle" aria-hidden="true"></i>`
		 return true;
	 
}
function validateSignUpForm(){
	if(!validateUsername() || !validateFullName()||!validatePassword()||!validateRePassword()){
		
		submitError.style.display = "block";
		submitError.innerHTML = "Please fix error to submit";
			 setTimeout(() => {
				 submitError.style.display = "none"
			}, 3000);
			 return false;
	}
	return true;
}

