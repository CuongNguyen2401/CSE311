
<%@ page import="com.fastfood.utils.SecurityUtils"%>

<header>
	<div class="nav-header">
		<div class="container">
			<div class="row ">
				<div class="col-md-5 text-center">
					<nav class="top-nav">
						<ul>
							<li class=""><a title="Home" href="/home">Home</a></li>
							<li class=""><a href="tel:+0929234798" title="Booking">Booking</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> Categories </a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdown">
									<a class="dropdown-item" onclick="loadByCategory(2)">Drink</a>
									<a class="dropdown-item" onclick="loadByCategory(1)">Food</a>
								</div></li>
						</ul>
					</nav>
				</div>
				<div class="col-md-3 text-center">
					<h1 class="logo">
						<a title="CrispTrek" href="/home"> <img
							src="<c:url value='/inc/FoodTakeAwayRestaurantLogo.png'/>"
							alt="CrispTrek">
						</a>
					</h1>
				</div>
				<div class="col-md-4">

					<div class="row h-100">

						<div class="col-md-9 align-self-lg-center">
							<div class="input-group">
								<input oninput="searchByName(this)" type="text"
									class="form-control" placeholder=""
									aria-label="Recipient's username"
									aria-describedby="basic-addon2">
								<div class="input-group-append">
									<i class="fa fa-search btn btn-outline-success" type="submit"></i>
								</div>

								<security:authorize access="isAuthenticated()">
									<div id="username" class="userName"><%=SecurityUtils.getPrincipal().getFullName()%></div>
								</security:authorize>



							</div>
						</div>

						<div class="col-md-3 align-self-lg-center">
							<div href="" class="user">
								<i class="fa fa-2x fa-user"></i>
								<div class="userMenu">
									<security:authorize access="isAuthenticated()">

										<ul>
											<li><a href="">View Cart</a></li>
											<li><a href="/user/<%=SecurityUtils.getPrincipal().getId()%>">Edit profile</a></li>
											<li><a href="view-bill">View bills</a></li>
											<li><a href="/logout">Logout</a></li>
										</ul>

									</security:authorize>

									<security:authorize access="isAnonymous()">
										<ul>
											<li><a href="login">Login</a></li>
										</ul>
									</security:authorize>


								</div>
							</div>
							<!--  -->
							<a href="cart" class="cart"> <span class="cc_cart_count">
									<c:if test="${sessionScope.cart !=null }">
										<span id="count_in_cart" class="cc_cart_count_child">${sessionScope.cart.items.size()}</span>
										<i class="fa fa-2x fa-shopping-cart"></i>
							</span> </c:if> <c:if test="${sessionScope.cart ==null }">
									<span id="count_in_cart" class="cc_cart_count_child">0</span>
									<i class="fa fa-2x fa-shopping-cart"></i>
									</span>
								</c:if>
							</a>
						</div>

					</div>

				</div>

			</div>
		</div>
	</div>
	<div class="nav-mobile">
		<div class="container">
			<div class="row">
				<div class="col-4">
					<div class="menu-mobile-icon">
						<i class="fa fa-bars fa-3x" aria-hidden="true"></i>
					</div>
				</div>
				<div class="col-4">
					<div class="logo-mobile">
						<a title="CrispTrek" href="home"> <img
							src="${pageContext.servletContext.contextPath}/inc/FoodTakeAwayRestaurantLogo.png"
							alt="CrispTrek">
						</a>
					</div>
				</div>
				<div style="text-align: end" class="col-4">








					<a href="cart-view" class="cart"> <i
						class="fa fa-3x fa-shopping-cart"></i>
					</a>
				</div>
			</div>
		</div>
	</div>

	<div class="slider">
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="img-fluid d-block  "
						src="${pageContext.servletContext.contextPath}/inc/Delicious Fast Food Banner Design.jpg"
						alt="First slide">
				</div>
				<div class="carousel-item">
					<img class="img-fluid d-block"
						src="${pageContext.servletContext.contextPath}/inc/Your paragraph text.png"
						alt="Second slide">
				</div>

			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>

	</div>
</header>