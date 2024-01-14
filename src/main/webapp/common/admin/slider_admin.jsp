<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- SIDEBAR -->
<section id="sidebar">
    <a href="home" class="brand">
        <i class='bx bxs-smile'></i>
        <span class="text">AdminPage</span>
    </a>
    <ul class="side-menu top">
        <li>
            <a href="/admin/home">
                <i class='bx bxs-shopping-bag-alt'></i>
                <span class="text">My Store</span>
            </a>
        </li>
         <li>
            <a href="/admin/category">
                <i class='bx bxs-calculator'></i>
                <span class="text">Categories</span>
            </a>
        </li>
        <li>
            <a href="/admin/news">
                <i class='bx bxs-calculator'></i>
                <span class="text">News</span>
            </a>
        </li>
        <li>
            <a href="view-admin-bills">
                <i class='bx bxs-calculator'></i>
                <span class="text">Bills</span>
            </a>
        </li>
        <li>
            <a href="view-admin-users">
                <i class='bx bxs-user'></i>
                <span class="text">Users</span>
            </a>
        </li>
        
        <li>
            <a href="/admin/chart">
                <i class='bx bxs-user'></i>
                <span class="text">Chart</span>
            </a>
        </li>
    </ul>
    <ul class="side-menu">

        <li>

            
            <a href="admin-login?mode=logout"  class="logout">
                <i class='bx bxs-log-out-circle'></i>
                <span class="text">Logout</span>
            </a>
          

        </li>
    </ul>
</section>
<!-- SIDEBAR -->