<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- NAVBAR -->
<nav>
	<i class='bx bx-menu'></i> <a href="admin-home" class="nav-link">HOME</a>
	<form action="admin-search">
		<div class="form-input">
			<input id="searchMode" type="hidden" value="" name="searchMode">
			<input name="searchQ" type="search" placeholder="Search...">
			<button type="submit" class="search-btn">
				<i class='bx bx-search'></i>
			</button>
		</div>
	</form>
	<input type="checkbox" id="switch-mode" hidden> <label
		for="switch-mode" class="switch-mode"></label> <a href="#"
		class="notification"> <i class='bx bxs-bell'></i> <span
		class="num">8</span>
	</a> <a href="#" class="profile"> <img src="img/people.png">
	</a>


</nav>
<!-- NAVBAR -->