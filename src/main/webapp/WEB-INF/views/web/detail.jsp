<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main class="detail">
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-12">
				<input type="radio" id="image1" name="image" checked> <input
					type="radio" id="image2" name="image"> <input type="radio"
					id="image3" name="image">

				<div class="container">
					<div class="featured-wrapper">
						<ul class="featured-list">

						</ul>
						<ul class="arrows">

						</ul>
						<ul class="dots">

						</ul>
					</div>
					<ul class="thumb-list">

					</ul>
				</div>
				<input id="listimg" type="hidden"
					value="${productDetail.getListImage().get(0).getImageURL()}">

			</div>
			<div class="col-md-6 col-12">
				<div class="product-detail">
					<h1 class="title-detail">${productDetail.getProductName()}</h1>
					<p class="price">${productDetail.getPrice()}$</p>
					<p class="description">${productDetail.getDescription()}</p>
					<button onClick="addToCart(${productDetail.getId()})"
						class="btn btn-primary addToCart addToCartDetail">Add to
						Cart</button>
				</div>

			</div>
		</div>
		<div style="margin-top: 100px" id="comments-container"></div>
	</div>
	</main>



	<script src="/template/js/comment.js"></script>

</body>
</html>