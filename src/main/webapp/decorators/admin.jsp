<%@ include file="/common/taglib.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.css">

<!-- Boxicons -->
<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css'
	rel='stylesheet'>
<!-- My CSS -->
<link rel="stylesheet"
	href="<c:url value='/template/css/admin_home.css'/>">
	<link rel="stylesheet"
	href="<c:url value='/template/css/chat.css'/>">
<link rel="stylesheet"
	href="<c:url value='/template/css/toastmsg.css'/>">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<script
	src="<c:url value='/template/ckeditor5-build-classic/build/ckeditor.js'/>"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	integrity="sha512-3P8rXCuGJdNZOnUx/03c1jOTnMn3rP63nBip5gOP2qmUh5YAdVAvFZ1E+QLZZbC1rtMrQb+mah3AfYW11RUrWA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>




<title>AdminPage</title>



</head>

<body>
	<jsp:include page="/common/admin/slider_admin.jsp"></jsp:include>




	<!-- CONTENT -->
	<section id="content">
		<jsp:include page="/common/admin/navbar_admin.jsp"></jsp:include>

		<!-- MAIN -->
		<dec:body />














		<!-- MAIN -->

	</section>
	<!-- CONTENT -->



	<div id="toasts">
		<input id="message" type="hidden" value="${msg}">
	</div>
	


	<script src="<c:url value='/template/js/toastmessage.js'/>"></script>

	<script
		src="<c:url value='/template/paging/jquery.twbsPagination.js'/>"></script>
	<script src="<c:url value='/template/js/admin_home.js'/>"></script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
	<script src="<c:url value='/template/js/toastmessage.js'/>"></script>
	<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>
</body>

</html>