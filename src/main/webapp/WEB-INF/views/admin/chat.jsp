<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<div class="chat-template">

		<div class="container">



			<div class="chat-container" id="chat-page">
				<div class="users-list">
					<div class="users-list-container">
						<h2>Online Users</h2>
						<ul id="connectedUsers">
							
						</ul>
					</div>
					<div>
						<p id="connected-user-fullname">Admin</p>

					</div>
				</div>

				<div class="chat-area">
					<div class="chat-area" id="chat-messages"></div>

					<form id="messageForm" name="messageForm" class="hidden">
						<div class="message-input">
							<input autocomplete="off" type="text" id="message"
								placeholder="Type your message...">
							<button>Send</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="<c:url value='/template/js/adminChat.js'/>"></script>



</body>
</html>