<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
    <div class="nav-header">
        <div class="container">
            <div class="row ">
                <div class="col-md-5 text-center">
                    <nav class="top-nav">
                        <ul>
                            <li class=""><a title="Home" href="home">Home</a></li>
                            <li class=""><a href="tel:+0929234798" title="Booking">Contact</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Categories
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="Category?cid=2">Drink</a>
                                    <a class="dropdown-item" href="Category?cid=1" >Food</a>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col-md-3 text-center">
                    <h1 class="logo">
                        <a title="CrispTrek" href="home">
                            <img src="${pageContext.servletContext.contextPath}/inc/FoodTakeAwayRestaurantLogo.png" alt="CrispTrek">
                        </a>
                    </h1>
                </div>
                <div class="col-md-4">

                    <div class="row h-100">

                        <div class="col-md-9 align-self-lg-center">
                            <div class="input-group">
                                <form style="display: flex" action="searchS" method="POST">
                                    <input style="width: 80%" name="txt" type="text" class="form-control" placeholder="" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button style="border:none;outline:none" type="submit" ><i  class="fa fa-search btn btn-outline-success"></i></button>
                                    </div>
                                    <c:if test="${sessionScope.logedUser!=null}">
                                        <div  class="userName">${sessionScope.logedUser.username}</div>
                                    </c:if>
                                </form>




                            </div>
                        </div>

                        <div class="col-md-3 align-self-lg-center">
                            <div href="" class="user">
                                <i class="fa fa-2x fa-user"></i>
                                <div class="userMenu">
                                    <c:if test="${sessionScope.logedUser!=null}">
                                        <ul>
                                            <li><a style="text-decoration: none; color: black" href="cart-view">View Cart</a></li>
                                            <li><a style="text-decoration: none; color: black" href="user-view?mode=view">Edit profile</a></li>
                                            <li><a style="text-decoration: none; color: black" href="view-bill">View Bill</a></li>
                                            <li>
                                                <form method="POST" action="login">
                                                    <input type="hidden" name="mode" value="logout">
                                                    <button class="logout-btn" type="submit">Logout</button>
                                                </form>
                                            </li>
                                        </ul>
                                    </c:if>
                                    <c:if test="${sessionScope.logedUser==null}">
                                        <ul>
                                            <li><a href="login">Login</a></li>
                                        </ul>
                                    </c:if>


                                </div>
                            </div>
                            <a href="cart-view" class="cart">
                                <span class="cc_cart_count">
                                    <span id="count_in_cart" class="cc_cart_count_child">${count}</span><i class="fa fa-2x fa-shopping-cart"></i></span> 
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
                        <a title="CrispTrek" href="home">
                            <img src="${pageContext.servletContext.contextPath}/inc/FoodTakeAwayRestaurantLogo.png" alt="CrispTrek">
                        </a>
                    </div>
                </div>
                <div style="text-align: end" class="col-4">




                    <a href="cart-view" class="cart">
                        <i class="fa fa-3x fa-shopping-cart"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>


</header>