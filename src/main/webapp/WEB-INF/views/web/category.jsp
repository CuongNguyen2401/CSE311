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


	<div style="margin-top: 100px" class="container " id="section_food">
		<nav class="mt-4" aria-label="breadcrumb">
			<ol style="background-color: white; padding-left: 0px;"
				class="breadcrumb">
				<li class="breadcrumb-item"><a href="/home">Home</a></li>
				<li class="breadcrumb-item"><a href="#">Category</a></li>
				<li id="breadcrumb-data" class="breadcrumb-item active"
					aria-current="page">Data</li>
			</ol>
		</nav>

	



		<div class="col-3" style="padding-left: 0px; padding-right: 0px;">
			<label class="mr-sm-2" for="inlineFormCustomSelect">Sort By</label> <select
				id="sorts" class="custom-select mr-sm-2">

				<option value="1" data-sort-by="original-order" selected>Default
				</option>
				<option value="2" data-sort-by="name">Name</option>
				<option value="2" data-sort-by="price">Price</option>
			</select>
		</div>



		<h3 class="mt-2 title foodTitle">FOOD</h3>
		<div class="grid" style="position: static !important">
			<div class="row mt-2 d-flex align-items-stretch" id="rowFood">



			</div>
		</div>




	</div>
	<script src="<c:url value='/template/js/category.js'/>"></script>



</body>
</html>