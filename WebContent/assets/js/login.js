$().ready(function(){
    changeLoginName();

    //user logout when logout button clicked
    $('#out').on('click',function(){
        var logoutbtn = document.getElementById('out').text;
        if(logoutbtn =='Logout'){
            logout();
        }
    });

    //login button
    $('#nav-login').on('click',function(){
        var loginName = document.getElementById('nav-login').text;
        if(loginName ==' Login'){
            location.href = 'login.html';
        }
        else if(getData('useremail')){
            location.href = 'profile.html';
        }
    });

    $('.form-control').on('click',function(){
        document.getElementById('err').style.visibility = 'hidden';
    });

    $('#btn-login').on('click',function(e){
        e.preventDefault();
        var email = $('input[id="email"]').val();
        var passW = CryptoJS.AES.encrypt($('input[id="password"]').val(),"Secret Passphrase").toString();

        if($("#loginform").valid()===true && $('#nav-login').text()===' Login'){
            block();

            $.ajax({
                type:'POST',
                url:'getUser',
                dataType:'json',
                data:{'email': email},

                success: function(data){
                    //if email doesnt exist in database then add user
                    if(data.email == 'undefined' || data.email == null){
                        alert("Please enter a valid email address");
                        document.getElementById('err').innerHTML="Email doesn't exists";
                        document.getElementById('err').style.visibility = 'visible';
                    }
                    else if(data.email === email){
                        if(CryptoJS.AES.decrypt(data.pass,"Secret Passphrase").toString(CryptoJS.enc.Utf8)
                            ===CryptoJS.AES.decrypt(passW,"Secret Passphrase").toString(CryptoJS.enc.Utf8)){
                            displayUser(data.email,data.name);
                            //reset form
                            $('#loginform').trigger('reset');
                            return;
                        }
                        else{
                            document.getElementById('err').innerHTML="Incorrect password";
                            document.getElementById('err').style.visibility = 'visible';
                            return;
                        }
                    }
                },
                error:function(){
                    alert("Please enter a valid email address!");
                },
                complete:function(){
                    $.unblockUI(); 
                }
            });
        }else if($('#nav-login').text()!==' Login'){
            alert("Please Logout first!");
        }
    });


    /*auto logout after user idle for 30mins 
    only check idle time when user is login*/
    if(getData('useremail')){
        var idletime = 0;
        var idleInterval = setInterval(idleTimer,60000);//1 minute

        $(this).mousemove(function(e){
            idletime = 0;
        });
        $(this).keypress(function(e){
            idletime = 0;
        });

        //timer for login
        function idleTimer(){
            idletime++;
            //if user has been idle for 30 minutes auto logout
            if(idletime > 29){
                logout();
            }
        }
    }
});

function displayUser(email,name){
    //set cookies for 1 day
    setCookie('email', email, 1);
    setCookie('name', name, 1);

    saveData('useremail', email);
    saveData('username', name);
    changeLoginName();
    $.unblockUI(); 
    location.href = 'profile.html';
}


//logout function
function logout(){
    clearAllCookies();
    saveData('useremail', "");
    saveData('username', "");
    changeLoginName();
    location.href = 'login.html';
}

//user name display
function changeLoginName(){
    //if sessionstorage is not empty
    if(getData('useremail')){
        document.getElementById('nav-login').innerHTML=getData('username');
        $('#nav-login').append(
            " <b class='caret'></b>"
        );
        //add extra dropdown buttons
        $('#loginDropdown').append(
            '<ul class="dropdown-menu">'
                +'<li><a href="profile.html">Profile</a></li>'
                +'<li><a href="#" id="out">Logout</a></li>'
            +'</ul>'
        );
        
        $('#signUpBtn').hide();
    }
    else{
        document.getElementById('nav-login').innerHTML='<span class="glyphicon glyphicon-log-in"></span> Login';
        $('#signUpBtn').show();
    }
}

function block(){
    $.blockUI({ message: "<img src='assets/img/busy.gif'width=150px;height=150px;/>",
        css: { 
            border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        }
    });
}

function validateUser(){
    var currentEmail = getCookie("email");
    var currentName = getCookie("name");
    if (currentEmail != sessionStorage.useremail || currentName != sessionStorage.username){
        sessionStorage.clear();
        sessionStorage.useremail = currentEmail;
        sessionStorage.username = currentName;  //reset
    }
}

function saveData(key, value){
    validateUser();
    sessionStorage[key] = value;
}

function getData(key){
    validateUser();
    return sessionStorage[key];
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function clearAllCookies() {
    setCookie('email', null, -1);
    setCookie('name', null, -1);
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function getPromotions(email,history){
    block();
    return $.ajax({
        type:'POST',
        url:'getPromotions',
        dataType:'json',
        data:{'email': email,'history':history},

        complete:function(){
            $.unblockUI(); 
        }
    });
}

function checkUserLogin(useremail){
    if(!useremail){
        alert("please login first!");
        window.location.href='login.html'
    }
}
