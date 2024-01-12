$('#comments-container')
		.comments(
				{
					profilePictureURL : 'https://cdn-icons-png.flaticon.com/512/9131/9131529.png',

					textareaPlaceholderText : 'Leave a comment',

					refresh : function() {
						$('#comments-container').addClass('rendered');
					},
					getComments : function(success, error) {
						$.ajax({
							type : 'get',
							url : '/api/v1/comments',
							success : function(commentsArray) {

								success(commentsArray)
							},
							error : function(error) {
								console.log(error)
							}
						});
					},
					
					postComment: function(commentJSON, success, error) {
						
						commentJSON = JSON.stringify(commentJSON)
						
						console.log(commentJSON)
					    $.ajax({
					      type: 'post',
					      url: '/api/v1/comment',
					      data: commentJSON,
				      contentType:'application/json',
					      success: function(comment) {
					        success(comment)
					        console.log(comment)
					      },
					      error: error
					    });
					  }
				}

				
				
				
				
		);