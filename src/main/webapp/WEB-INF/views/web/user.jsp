
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>


</head>
<body>


	<main>
	<div class="container">
		<form:form modelAttribute="user" ccsClass="needs-validation mt-5"
			action="/user/" method="POST">

			<input id="id" type="hidden" path="id"
				value="<%=SecurityUtils.getPrincipal().getId()%>">
			<div class="form-row">
				<div class="col-md-6 mb-3">
					<label for="fullName">Name</label>
					<form:input  path="fullName" type="text"
						cssClass="form-control" id="fullName"
						placeholder="full name" />
					<div class="valid-feedback">Looks good!</div>
					<div class="invalid-feedback">Please choose a name.</div>
				</div>
				<div class="col-md-6 mb-3">
					<label for="validationCustom02">Last name</label> <input
						type="text" cssClass="form-control" id="validationCustom02"
						placeholder="last name" />
					<div class="valid-feedback">Looks good!</div>
					<div class="invalid-feedback">Please choose a last name.</div>
				</div>
				<div class="col-md-6 mb-3">
					<label for="phoneNumber">Phone Number</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupPrepend"><i
								class="fa fa-phone" aria-hidden="true"></i></span>
						</div>
						<form:input path="phoneNumber" type="number"
							cssClass="form-control" id="phoneNumber"
							placeholder="Phone Number" />
						<div class="invalid-feedback">Please choose a phone number.
						</div>
					</div>
				</div>
				<div class="col-md-6 mb-3">
					<label for="email">Email</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text" id="inputGroupPrepend">@</span>
						</div>
						<form:input path="email" type="email" cssClass="form-control"
							id="email" placeholder="Your Email" />
						<div class="invalid-feedback">Please choose an email
							address.</div>
					</div>
				</div>
				<div class="col-md-12 mb-3">

					<div class="row">
						<div class="col-md-4">
							<label class="mr-sm-2" for="inlineFormCustomSelect">City</label>
							<select class="custom-select mr-sm-2" id="city" required="">
								<option value="" selected>Choose province</option>
							</select>

						</div>
						<div class="col-md-4">
							<label class="mr-sm-2" for="inlineFormCustomSelect">District</label>
							<select class="custom-select mr-sm-2" id="district" required="">
								<option value="" selected>Choose District</option>
							</select>
						</div>
						<div class="col-md-4">
							<label class="mr-sm-2" for="inlineFormCustomSelect">Ward</label>
							<select class="custom-select mr-sm-2" id="ward" required="">
								<option value="" selected>Choose Ward</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-12 mb-3">
					<form:input path="address" id="result" type="text"
						cssClass="form-control" placeholder="Your Street" />
				</div>
			</div>


			<button id="changeUserProfile" class="btn btn-primary btn-sm" type="submit">Submit
				form</button>
		</form:form>
	</div>

	</main>
	<script>
		$('#changeUserProfile').click(
				function(e) {
					e.preventDefault();
					
						var data = {};
						var id = $('#id').val().trim();
						var fullName = $('#fullName').val().trim();
						var email = $('#email').val().trim();
						var address = $('#result').val().trim();
						var phoneNumber = $('#phoneNumber').val().trim();
						data["id"] = id
						data["fullName"] = fullName
						data["email"] = email;
						data["address"] = address;
						data["phoneNumber"] = phoneNumber;

						
							changeUserData(data)
						

					
				});
		function changeUserData(data) {
			$.ajax({
				url : '/api/user',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "/user/"
						+ result.id + "?msg=success_change";
				},
				error : function(error) {
					window.location.href = "/user/"
						+ result.id + "?msg=error_change";
				}
			});
		}
	</script>

</body>
</html>
