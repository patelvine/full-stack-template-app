$().ready(function(){

	var useremail = getData('useremail');
	checkUserLogin(useremail);

    var options = {
		valueNames: [ 'name', 'price', 'time', 'location','score'],
		// Since there are no elements in the list, this will be used as template.
		item: '<li><div class="card"><h3 class="name"></h3>'
			 +'<span class="score" data-tooltip="This is a score." '
			 +'data-tooltip-position="top"></span>'
			 +'<p class="price"></p><p class="time"></p>'
			 +'<p class="location"></p></div></li>'
	};

	var val = getPromotions(useremail,false);
	val.then(function(data){
	    	var userList = new List('users', options, data);
	});
	
	/*when card get click open a pop up window for push promotions*/
	$('#users').on('click',function(event){
		event.preventDefault();
	    var name = $(event.target).closest('li').find('.name').text();
	    var price = $(event.target).closest('li').find('.price').text();
	    var time = $(event.target).closest('li').find('.time').text();
	    var location = $(event.target).closest('li').find('.location').text();
	    var score = $(event.target).closest('li').find('.score').text();
	    
	    if(location&&time&&name&&price){
	    	$('#social-media').modal();

		    console.log("name: "+name);
		    console.log("price: "+price);
		    console.log("time: "+time);
		    console.log("location: "+location);
		    console.log("score: "+score);

		    
		}

		$('.btn-twitter').on('click',function(e){
				console.log('twitter');
		});
		$('.btn-facebook').on('click',function(e){
			console.log('facebook');
			/*FB.ui({
			  method: 'feed',
			  link: 'https://developers.facebook.com/docs/',
			  caption: 'An example caption',
			}, function(response){});*/
			
		});
		$('.btn-instagram').on('click',function(e){
			console.log('instagram');
		});
	});
});


/*
function fb_publish() {
	FB.ui({
	    method: 'stream.publish',
	    message: 'Message here.',
	    attachment: {
	       	name: 'Name here',
	       	caption: 'Caption here.',
	       	description: (
	         	'description here'
	       	),
	       	href: 'url here'
	    },
	    action_links: [
	       	{ text: 'Code', href: 'action url here' }
	    ],
	     	user_prompt_message: 'Personal message here'
		},
		function(response) {
		    if (response && response.post_id) {
		       	alert('Post was published.');
		    } else {
		       	alert('Post was not published.');
		    }
		}
	);  
}*/