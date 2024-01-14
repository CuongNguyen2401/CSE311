


var element = $('.floating-chat');

setTimeout(function() {
    element.addClass('enter');
}, 1000);

element.click(openElement);

function openElement() {
    var messages = element.find('.messages');
    var textInput = element.find('.text-box');
    element.find('>i').hide();
    element.addClass('expand');
    element.find('.chat').addClass('enter');
    var strLength = textInput.val().length * 2;
    textInput.keydown(onMetaAndEnter).prop("disabled", false).focus();
    element.off('click', openElement);
    element.find('.header-chat button').click(closeElement);
    fetchAndDisplayUserChat().then();
 element.find('#sendMessage').click(sendMessage);
    messages.scrollTop(messages.prop("scrollHeight"));
    const notification = document.querySelector(`.chat-notification`);
    notification.classList.add("hidden")
}

function closeElement() {
    element.find('.chat').removeClass('enter').hide();
    element.find('>i').show();
    element.removeClass('expand');
    element.find('.header-chat button').off('click', closeElement);
    element.find('#sendMessage').off('click', sendMessage);
    element.find('.text-box').off('keydown', onMetaAndEnter).prop("disabled", true).blur();
    setTimeout(function() {
        element.find('.chat').removeClass('enter').show()
        element.click(openElement);
    }, 500);
  
}

//
// function sendNewMessage() {
// 
// var newMessage = userInput.html().replace(/\<div\>|\<br.*?\>/ig,
// '\n').replace(/\<\/div\>/g, '').trim().replace(/\n/g, '<br>');
//
// if (!newMessage) return;
//
//
//
// messagesContainer.append([
// '<li class="self">',
// newMessage,
// '</li>'
// ].join(''));
//
// // clean out old message
// userInput.html('');
// // focus on input
// 
//

// }

 function onMetaAndEnter(event) {
 if ( event.keyCode === 13) {
	 
	 sendMessage();
 }
 }


'use strict';


const chatPage = document.querySelector('#chat-page');

const messageForm = document.querySelector('#sendMessage');
const messageInput = document.querySelector('#messageInput');
const chatArea = document.querySelector('#chat-messages');


let stompClient = null;
let userId = null;
let fullname = null;


function connect(event) {
	userId = document.querySelector('#chat-userId').value.trim();
	fullname = document.querySelector('#username').textContent.trim();
	
   

    if (userId!==""&&fullname!=="") {

        const socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}
window.addEventListener("load",connect)

function onConnected() {
    stompClient.subscribe(`/user/${userId}/queue/messages`, onMessageReceived);
    stompClient.subscribe(`/user/public`, onMessageReceived);

    // register the connected user
    stompClient.send("/app/user.addUser",
        {},
        JSON.stringify({id: userId, fullName: fullname, status: 'ONLINE'})
    );
  
   
}


function displayMessage(senderId, content) {
    const messageContainer = document.createElement('li');
   
    if (senderId === userId) {
        messageContainer.classList.add('self');
    } else {
        messageContainer.classList.add('other');
    }
  if(content!=null){
	  messageContainer.textContent = content;
	  
	    chatArea.appendChild(messageContainer);
  }
    
}

async function fetchAndDisplayUserChat() {
    const userChatResponse = await fetch(`/messages/${userId}/admin`);
    const userChat = await userChatResponse.json();
    chatArea.innerHTML = '';
    userChat.forEach(chat => {
        displayMessage(chat.senderId, chat.content);
    });
    chatArea.scrollTop = chatArea.scrollHeight;
    
};


function onError() {
    console.log("chat-error")
}



function sendMessage(event) {
    const messageContent = messageInput.value.trim();
 
   
    if (messageContent && stompClient) {
        const chatMessage = {
            senderId: userId,
            recipientId: 'admin',
            content:messageInput.value.trim(),
            timestamp: new Date()
        };
       
       
        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
        displayMessage(userId, messageInput.value.trim());
        messageInput.value = '';
    }
    var userInput = $('.text-box');
    userInput.focus();
    var messagesContainer = $('.messages');
 messagesContainer.finish().animate({
 scrollTop: messagesContainer.prop("scrollHeight")
  }, 250);
   
    chatArea.scrollTop = chatArea.scrollHeight;
    
}


function onMessageReceived(payload) {
   
    console.log('Message received', payload);
    const message = JSON.parse(payload.body);
   
	displayMessage(message.senderId, message.content);
	chatArea.scrollTop = chatArea.scrollHeight;
 

  if(message.content!=null){
	  const notification = document.querySelector(`.chat-notification`);
	  notification.classList.remove("hidden")
  }



}

function onLogout() {
    stompClient.send("/app/user.disconnectUser",
        {},
        JSON.stringify({id: userId, fullName: fullname, status: 'OFFLINE'})
    );
   
}


messageForm.addEventListener('click', sendMessage, true);
window.onbeforeunload = () => onLogout();
