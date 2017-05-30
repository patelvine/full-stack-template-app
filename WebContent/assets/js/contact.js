$().ready(function(){

    $('#characterLeft').text('140 characters left');
    $('#message').keydown(function () {
        var max = 140;
        var len = $('#message').val().length;
        if (len >= max) {
            $('#characterLeft').text('You have reached the limit'); 
        } 
        else {
            var ch = max - len;
            $('#characterLeft').text(ch + ' characters left');    
        }
    });   

    $('#message').keydown(function () {
        document.getElementById('err').style.visibility = 'hidden';
    }); 

    $('#btn-contact').on('click',function(e){
    	var name = $('input[id="name"]').val();
        var email=$('input[id="email"]').val();
        var phone =$('input[id="mobile"]').val();
        var company = $('input[id="company"]').val();
        var subject = $('input[id="subject"]').val();
        var message =$('#message').val();

        if($('#message').val().length===0){
            document.getElementById('err').innerHTML="Please add comment before submitting form";
            document.getElementById('err').style.visibility = 'visible';
            return;
        }
        
    	if($("#contactform").valid()===true && $('#message').val().length>0){
    		sendMail(name,email,phone,company,subject,message);
    	}

    });
});

/*get input infos and send it to our email*/
function sendMail(name,email,phone,company,subject,message){
	block();

	$.ajax({
        type:'POST',
        url:'mail',
        dataType:'json',
        data:{'name':name,"email":email, "phone":phone, "company":company,
        "subject":subject,"message":message,"feedback":true},

        success:function(data){
        	alert('Thank you, we will get in touch soon!!!');
        },
        error:function(){
            console.log('email send fail!');
        },
        complete:function(){
             $.unblockUI();
        }
    });
	
}
