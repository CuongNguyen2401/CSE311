<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<main>
	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>Add Product</h3>
			</div>


			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="detail-product">

							<div class="table-responsive ps">




								<div class="container">
									<form:form modelAttribute="product"
										enctype="multipart/form-data">
										<div class="form-row">
											<div class="form-group col-md-6">
												<label for="productName">Product Name</label>
												<form:input path="productName" id="productName"
													cssClass="form-control" type="text" />
											</div>
											<div class="form-group col-md-6">
												<label for="id">ID</label>
												<form:input id="id" path="id" cssClass="form-control"
													type="text" readOnly="true" />
											</div>
										</div>

										<div class="form-row">
											<div class="form-group col-md-4">
												<label for="price">Price</label>
												<form:input id="price" path="price" cssClass="form-control"
													type="number" />
											</div>
											<div class="form-group col-md-4">
												<label for="salePrice">Sale Price</label>
												<form:input path="salePrice" id="salePrice"
													cssClass="form-control" type="number" />
											</div>
											<div class="form-group col-md-4">
												<label for="category">Category</label>
												<form:select path="categoryDTO" id="category"
													cssClass="form-control">
													<form:option value="" label="-- Choose type of product --" />
													<form:options items="${categories}" />
												</form:select>
											</div>
										</div>

										<div class="form-row">
											<div class="form-group col-md-12">
												<label for="description">Description</label>

												<form:textarea path="description" rows="20" cols="10"
													cssClass="form-control" id="description" />
											</div>


										</div>

										<div class="form-row">
											<c:forEach items="${listImg}" var="img">
												<div class="form-group col-md-4 file">
													<label for="exampleFormControlFile1">Picture</label> <input
														type="file" class="form-control-file" accept="image/*"
														onchange="loadFile(this)" name="files"> <img
														class="output" src="http://localhost:8080/${img}" alt=""
														style="width: 300px; margin-top: 10px">
												</div>
											</c:forEach>
											<c:if test="${listImg==null}">
												<c:forEach var="i" begin="1" end="3">
													<div class="form-group col-md-4 file">
														<label for="exampleFormControlFile1">Picture ${i}</label>
														<input type="file" class="form-control-file"
															accept="image/*" onchange="loadFile(this)" name="files">
														<img class="output" src="" alt=""
															style="width: 300px; margin-top: 10px">
													</div>
												</c:forEach>
											</c:if>



										</div>



										<input id="btnAddOrUpdateNew" type="submit"
											class="btn btn-primary" value="Submit">
									</form:form>
								</div>








							</div>

						</div>

					</div>

				</div>


			</div>
		</div>
	</main>

	<script>
		var formData = new FormData();
		var loadFile = function(obj) {
			var obj = obj.parentElement.getElementsByClassName("output")
			var output = obj[0]
			output.src = URL.createObjectURL(event.target.files[0]);
			formData.append("files", event.target.files[0])
			output.onload = function() {
				URL.revokeObjectURL(output.src) // free memory

			}
		};

		$('#btnAddOrUpdateNew').click(function(e) {
			e.preventDefault();

			formData.append("productName", $("#productName").val())
			formData.append("price", $("#price").val())
			formData.append("salePrice", $("#salePrice").val())
			formData.append("category", $("#category").val())
			formData.append("description", $("#description").val())
			
			var id = $('#id').val();

			if (id === "") {
				addProduct(formData);
			} else {
				updateProduct(formData);
			}
		});

		function addProduct(formData) {

			$.ajax({
				url : '/admin/api/v1/products',
				type : 'POST',
				data : formData,
				enctype : 'multipart/form-data',
				cache : false,
				contentType : false,
				processData : false,
				success : function(result) {
					window.location.href = "/admin/product/edit?id="
							+ result.id + "&msg=add_success";
				},
				error : function(error) {
					window.location.href = "/admin/product/edit?msg=add_error";
				}
			});
		}

		function updateProduct(formData) {

			$.ajax({
						url : '/admin/api/v1/products',
						type : 'PUT',
						data : formData,
						enctype : 'multipart/form-data',
						cache : false,
						contentType : false,
						processData : false,
						success : function(result) {
							window.location.href = "/admin/product/edit?id="
									+ result.id + "&msg=update_success";
						},
						error : function(result) {
							window.location.href = "/admin/product/edit?msg=update_error";
						}
					});
		}
		
		 ClassicEditor
         .create( document.querySelector( '#description' ) )
         .then( editor => {
             console.log( editor );
         })
         .catch( error => {
             console.error( error );
         });

     CKEDITOR.replace( 'editor', {
         extraAllowedContent: 'style;*[id,rel](*){*}'
     });
	</script>
</body>
</html>