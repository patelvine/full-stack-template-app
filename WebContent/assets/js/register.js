$().ready(function(){ 

    $('.form-control').on('click',function(){
        document.getElementById('err').style.visibility = 'hidden';
    });
    //signup
    $('#btn-signup').on('click',function(e){
        e.preventDefault();
        var name = $('input[id="name"]').val();
        var email = $('input[id="email"]').val();
        var pass = CryptoJS.AES.encrypt($('input[id="password"]').val(),"Secret Passphrase").toString();
        var phone = $('input[id="phone"]').val();
        var contactP = $('input[id="contact"]').val();
        var contactT=$('input[id="contactTime"]').val();
        var position =$('input[id="position"]').val();
        var industry = $('input[id="industry"]').val();
        var primaryLoc = $('input[id="primaryLocation"]').val();
        var numOfLoc = $('input[id="numOfLocation"]').val();
        var numOfEmp = $('input[id="numOfEmp"]').val();

        //if all values are not empty then add the user to table
        if($("#signupform").valid()===true){
            block();
            //user can not use login as username
            if(name == "Login"){
                document.getElementById('err').innerHTML="Can't use this username, Please choose another username";
                document.getElementById('err').style.visibility = 'visible';
                $.unblockUI();
                return;
            }

            $.ajax({
                type:'POST',
                url:'addUser',
                dataType:'json',
                data:{"name":name,
                    "email":email,
                    "pass":pass,
                    "phone":phone,
                    "contactP":contactP,
                    "contactT":contactT,
                    "position":position,
                    "industry":industry,
                    "primaryLoc":primaryLoc,
                    "numOfLoc":numOfLoc,
                    "numOfEmp":numOfEmp },

                success:function(data){
                	if(data == "false"){
                		alert("Register failed!");
                	}
                    sendMail(email);
                    alert('Thank you, we will be in touch soon!!!');
                    //redirect to login page
                    location.href = 'login.html';
                },
                error:function(){
                    document.getElementById('err').innerHTML="Email already exist please use a different email";
                    document.getElementById('err').style.visibility = 'visible';
                },
                complete:function(){
                    $.unblockUI();
                }
            });

        }//otherwise leave it to the form validation
    });
});


/*send email to user when they register*/
function sendMail(email){
    $.ajax({
        type:'POST',
        url:'mail',
        dataType:'json',
        data:{"email":email,"feedback":false},

        success:function(data){
            console.log('An email has been send to your email!');
        },
        error:function(){
            console.log('email send fail!');
        }
    });
    
}