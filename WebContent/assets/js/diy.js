$().ready(function(){

	var useremail = getData('useremail');
	checkUserLogin(useremail);
	
	/*when card get click open a pop up window for push promotions*/
	$('#btn-push').on('click',function(e){
		e.preventDefault();
	    var name = $('.name').val();
	    var price = $('.price').val();
	    var time = $('.time').val();
	    var location = $('.location').val();
	    var score = $('.score').val();


	    if($('#cardForm').valid()===true){
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
			
		});
		$('.btn-instagram').on('click',function(e){
			console.log('instagram');
		});
	});
});
