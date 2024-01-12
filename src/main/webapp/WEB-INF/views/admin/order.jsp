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
	<main>
	<div class="head-title">
		<div class="left">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="#">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="#">Bill</a></li>
			</ul>
		</div>
		<a href="/admin/api/v1/orders/excel" class="btn-download" id="exportExcelLink"> <i
			class='bx bxs-cloud-download'></i> <span class="text">Export
				to excel</span>
		</a>
	</div>



	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>Product list</h3>
				<i class='bx bx-search'></i>

				<div class="col-auto my-1">

					<select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
						<c:forEach items="${listMonthAndYear}" var="item">
							<option>${item}</option>
						</c:forEach>
					</select>
				</div>
				<!-- add Product -->
				<a href="#" id="add"> <i class="fa fa-plus-circle fa-2x mr-2"></i>
					<span>Add new product</span>
				</a>
			</div>





			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Customer Name</th>
						<th>Address</th>
						<th>Email</th>
						<th>PhoneNumber</th>
						<th>Total Pay</th>
						<th>Functions</th>
					</tr>
				</thead>
				<tbody id="contentTable">






				</tbody>

			</table>
			<ul id="pagination" class="pagination"></ul>






		</div>

	</div>

	</main>
	<script src="/template/js/adminRenderOrder.js"></script>

	<script>
		function deleteProduct(id) {

			$.confirm({
				title : 'Confirm!',
				content : 'Simple confirm!',
				buttons : {
					confirm : function() {
						$.alert('Confirmed!');
						doDeleteById(id)
					},
					cancel : function() {
						$.alert('Canceled!');
					},
					somethingElse : {
						text : 'Something else',
						btnClass : 'btn-blue',
						keys : [ 'enter', 'shift' ],
						action : function() {
							$.alert('Something else?');
						}
					}
				}
			});

		}
		function doDeleteById(id) {
			$.ajax({
				url : '/admin/api/v1/products/' + id,
				type : 'DELETE',
				contentType : 'application/json',
				success : function(result) {
					document.getElementById("message").value = "success_delete"
					window.onload = renderProducts();
				},
				error : function(error) {
					document.getElementById("message").value = "error_delete"
					let msg = document.getElementById("message");
					createToast(msg.value);
					msg.value = "";
				}
			});
		}
		
		
	</script>

	

</body>
</html>