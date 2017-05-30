$(document).ready(function()
{   
    var useremail = getData('useremail');
    if(!useremail){
        alert("please login first!");
        window.location.href='login.html'
    }

    //left menu bar
    var navItems = $('.admin-menu li > a');
    var navListItems = $('.admin-menu li');
    var allWells = $('.admin-content');
    var allWellsExceptFirst = $('.admin-content:not(:first)');
    
    allWellsExceptFirst.hide();
    navItems.click(function(e){
        e.preventDefault();
        navListItems.removeClass('active');
        $(this).closest('li').addClass('active');
        
        allWells.hide();
        var target = $(this).attr('data-target-id');
        $('#' + target).show();
    });

    /*home tap*/
    getUserInfo(useremail,"","");
    $('#profileClick').on('click', function(){
        $('#home').hide();
        $('#hom').removeClass('active');
        $('#Profile').show();
        $('#pro').addClass('active');
    });

    /*prifle tap*/
    $('#settingsClick').on('click', function(){
        $('#home').hide();
        $('#hom').removeClass('active');
        $('#Settings').show();
        $('#set').addClass('active');
    });

    $('#btn-saveChanges').on('click',function(e){
        if($("#editForm").valid()===true){
            e.preventDefault();
            var name = $('#name').val();
            var email = $('#email').val();
            var phone = $('#phone').val();
            var position =$('#position').val();
            var industry = $('#industry').val();
            var primaryLoc = $('#location').val();
            updateUser(name,email,phone,position,industry,primaryLoc);
        }
    });

    $('#btn-cancelChanges').on('click',function(){
        getUserInfo(useremail,"","");
    });
    
    /*settings tap*/
    getLocations(useremail);
    getProducts(useremail);
    $('#btn-changePass').on('click',function(){
        if($("#editPassForm").valid()===true){
            var current_pass = CryptoJS.AES.encrypt($('#c_password').val(),"Secret Passphrase").toString();
            var new_pass = CryptoJS.AES.encrypt($('#password').val(),"Secret Passphrase").toString();
            getUserInfo(useremail,current_pass,new_pass);
        }
    });

    /*LOCATION parse list of location to backend*/
    $('#btn-addLoc').on('click',function(){
        if($("#addLoc").val()){
            var location = $("#addLoc").tagsinput('items');
            var re =new RegExp('^\w+$');
            
            for(i=0; i<location.length; i++){
                while(!/^\w+$/i.test(location[i])){
                    $('#addLoc').tagsinput('remove',location[i]);
                }           
            }
            addLocations(useremail,location);
        }
    });

    /*remove location*/
    $('#btn-RemoveLoc').on('click',function(){
        if($(".location-select").val()){
            var locList = $(".location-select").select2('val');
            removeLocations(useremail,locList)
        }
    });

    /*PRODUCT parse list of products to backend*/
    $('#btn-addprod').on('click',function(){
        if($("#addprod").val()){
            var products = $("#addprod").tagsinput('items');
            var re =new RegExp('^\w+$');
            
            for(i=0; i<products.length; i++){
                while(!/^\w+$/i.test(products[i])){
                    $('#addprod').tagsinput('remove',products[i]);
                }           
            }
            addProducts(useremail,products);
        }
    });

    /*remove product*/
    $('#btn-RemovePro').on('click',function(){
        if($(".product-select").val()){
            var prodList = $(".product-select").select2('val');
            removeProducts(useremail,prodList)
        }
    });



});

function removeLocations(email,locList){
    block();
    $.ajax({
        type:'POST',
        url:'removeLocations',
        dataType:'json',
        data:{'email': email,'locList': locList},

        success: function(data){
            alert('Locations removed');
        },
        error:function(){
            
        },
        complete:function(){
            $.unblockUI(); 
            $("#loc-select").val(null).trigger("change");
            getLocations(email);
        }
    });
}

function removeProducts(email,prodList){
    block();
    $.ajax({
        type:'POST',
        url:'removeProducts',
        dataType:'json',
        data:{'email': email,'prodList': prodList},

        success: function(data){
            alert('Product removed');
        },
        error:function(){
            
        },
        complete:function(){
            $.unblockUI(); 
            $("#pro-select").val(null).trigger("change");
            getProducts(email);
        }
    });
}

function getProducts(email){
    $.ajax({
        type:'POST',
        url:'getProducts',
        dataType:'json',
        data:{'email': email},

        success: function(data){
            $("#pro-select").empty();
            for(i = 0; i<data.length; i++){
                    var product = data[i].product;
                    $('.product-select').append("<option >"+product+"</option>");
                }
            $(".product-select").select2();
        },
        error:function(){
            
        },
        complete:function(){
            $.unblockUI(); 
        }
    });
}

function getLocations(email){
    $.ajax({
        type:'POST',
        url:'getLocations',
        dataType:'json',
        data:{'email': email},

        success: function(data){
            $("#loc-select").empty();
            for(i = 0; i<data.length; i++){
                    var location = data[i].location;
                    $('.location-select').append("<option >"+location+"</option>");
                }
            $(".location-select").select2();
        },
        error:function(){
            
        },
        complete:function(){
            $.unblockUI(); 
        }
    });
}

function addProducts(email,products){
    block();
    $.ajax({
        type:'POST',
        url:'addProducts',
        dataType:'json',
        data:{"email":email,"products": products },

        success:function(data){
            alert('products added');      
        },
        error:function(){
            alert('products already exists!');
        },
        complete:function(){
            $.unblockUI();
            $('#addprod').tagsinput('removeAll');
            getProducts(email);
        }
    });
}

function addLocations(email,location){
    block();
    $.ajax({
        type:'POST',
        url:'addLocations',
        dataType:'json',
        data:{"email":email,"location": location },

        success:function(data){
            alert('stores added');      
        },
        error:function(){
            alert('location already exists!');
        },
        complete:function(){
            $.unblockUI();
            $('#addLoc').tagsinput('removeAll');
            getLocations(email);
        }
    });
}

function getUserInfo(email,pass,new_pass){
    block();
    $.ajax({
        type:'POST',
        url:'getUser',
        dataType:'json',
        data:{'email': email},

        success: function(data){
            $('.username').append(data.name);
            $('#name').val(data.name);
            $('.email').val(data.email);
            $('#phone').val(data.phone);
            $('#industry').val(data.industry);
            $('#position').val(data.position);
            $('#location').val(data.primaryLoc);
            $('.email').prop('disabled', true);
            
            if(pass){
                if(CryptoJS.AES.decrypt(data.pass,"Secret Passphrase").toString(CryptoJS.enc.Utf8)
                    ===CryptoJS.AES.decrypt(pass,"Secret Passphrase").toString(CryptoJS.enc.Utf8)){
                    //reset form
                    $('#editPassForm').trigger('reset');
                    //change pass
                    updateUser("",email,"","","","",new_pass);
                }
                else{
                    document.getElementById('err').innerHTML="Incorrect password";
                    document.getElementById('err').style.visibility = 'visible';
                    return;
                }
            }
            $.unblockUI();
        },
        error:function(){
            
        },
        complete:function(){
            $.unblockUI(); 
        }
    });
}

function updateUser(name,email,phone,position,industry,primaryLoc,new_pass){
    block();
    $.ajax({
        type:'POST',
        url:'updateUser',
        dataType:'json',
        data:{'name':name,"email":email,"phone":phone, "position":position,
         "industry":industry, "primaryLoc":primaryLoc, "new_pass":new_pass},

        success:function(data){
            if(new_pass){
                logout();
            }
            else if(email&&name){
                displayUser(email,name);
            }
        },
        error:function(){
            alert("Error: Update user failed: "+email);
        },
        complete:function(){
            $.unblockUI(); 
        }
    });
}

