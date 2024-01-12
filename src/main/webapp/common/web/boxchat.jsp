
<%@ page import="com.fastfood.utils.SecurityUtils"%>
<div class="floating-chat">
<span class="chat-notification hidden"></span>


	<security:authorize access="isAuthenticated()">
		<input id="chat-userId" type=hidden
			value="<%=SecurityUtils.getPrincipal().getId()%>" />
	</security:authorize>

	<i class="fa fa-comments" aria-hidden="true"></i>
	<div class="chat">
		<div class="header-chat" id="chat-page">
			<span class="title-chat">Admin</span>
			<button>
				<i style="font-size: 22px" class="fa fa-times" aria-hidden="true"></i>
			</button>

		</div>
		<ul class="messages" id="chat-messages">


		</ul>
		<div class="footer-chat" id="messageForm">
			<input style="outline: none; border: none" autocomplete="off"
				type="text"  id="messageInput"
				class="text-box">
			<button id="sendMessage"><svg style="width:24px;height:24px" viewBox="0 0 24 24">
          <path fill="#006ae3" d="M2,21L23,12L2,3V10L17,12L2,14V21Z"></path>
        </svg></button>
		</div>
	</div>
</div>

