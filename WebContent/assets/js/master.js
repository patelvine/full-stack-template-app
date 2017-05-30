$().ready(function(){
	
	/*==========master page=======*/
	$('head').prepend('<meta charset="utf-8">'
        +'<meta http-equiv="X-UA-Compatible" content="IE=edge">'
        +'<meta name="viewport" content="width=device-width, initial-scale=1">'
        +'<link rel="icon" type="image/png" href="assets/img/favicon.ico">'
        );
	$('head').append('<!-- CSS====================================-->'
        +'<!-- Bootstrap Core CSS -->'
        +'<link href="assets/css/main.css" rel="stylesheet">'
        +'<link href="assets/css/bootstrap.min.css" rel="stylesheet">'
        +'<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css" rel="stylesheet">'
        +'<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">'
        +'<link media="all" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">'
        +'<!--JAVASCRIPT===========================-->'
        +'<script src="assets/js/lock.js"></script>'
        //+'<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>'
        +'<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>'
        +'<script src="assets/js/jquery.validate.js"></script>'
        +'<script src="assets/js/validation-additional-methods.js"></script>'
        +'<script src="assets/js/form.js"></script>'
        +'<script src="assets/js/jquery.blockUI.js"></script>'
        +'<script type="text/javascript" src="assets/js/login.js"></script>'
        +'<script type="text/javascript" src="assets/js/list.min.js"></script>'
        );
	
	//body fixed contents
	$('body').prepend('<!-- PRELOADER=====================================-->'
        +'<div id="preloader">'
            +'<div id="status"></div>'
        +'</div>'
        +'<!-- header=========================================-->'
        +'<header id="header">'
            +'<!-- NAVBAR===================-->'
            +'<div class="container">'
                +'<nav class="navbar navbar-default" role="navigation">'
                    +'<div class="container-fluid">'
                        +'<!-- Brand and toggle get grouped for better mobile display -->'
                        +'<div class="navbar-header">'
                            +'<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-1">'
                                +'<span class="sr-only">Toggle navigation</span>'
                                +'<span class="icon-bar"></span>'
                                +'<span class="icon-bar"></span>'
                                +'<span class="icon-bar"></span>'
                            +'</button>'
                            +'<a class="navbar-brand" href="index.html"><img src = "assets/img/home.png"/></a>'
                        +'</div>'
                        +'<!-- Collect the nav links, forms, and other content for toggling -->'
                        +'<div class="collapse navbar-collapse" id="navbar-collapse-1">'
                           /* +'<ul class="nav navbar-nav">'
                                +'<li class ="active"><a href="#">LEFT</a></li>'
                            +'</ul>'*/
                            +'<ul class="nav navbar-nav navbar-right">'
                                +'<li><a href="index.html">Home</a></li>'
                                +'<li><a href="about.html">About</a></li>'
                                +'<li class="dropdown">'
                                +'<a href="#" >Manager <b class="caret"></b></a>'
                                    +'<ul class="dropdown-menu">'
                                        +'<li><a href="getPromo.html">Get Promotion</a></li>'
                                        +'<li><a href="DIY.html">DIY</a></li>'
                                        +'<li><a href="result.html">Result</a></li>'
                                        +'<li class="divider"></li>'
                                        +'<li><a href="profile.html">Settings</a></li>'
                                    +'</ul>'
                                +'</li>'
                                +'<li><a href="contact.html">Contact Us</a></li>'
                                +'<li id="signUp"><a href="register.html" id="signUpBtn"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>'
                                +'<li class="dropdown" id="loginDropdown"><a href="#" id="nav-login" >'
                                    +'<span class="glyphicon glyphicon-log-in"></span> Login</a>'
                                +'</li>'
                            +'</ul>'
                        +'</div>'
                        +'<!-- /.navbar-collapse -->'
                    +'</div>'
                    +'<!-- /.container-fluid -->'
                +'</nav>'
            +'</div>'
        +'</header>');

	//footer fixed contents	
	$('footer').prepend('<div class="footer-top">'
                +'<div class="container">'
                    +'<div class="row">'
                        +'<div class="footer-widget col-sm-12 col-md-4">'
                            +'<h3>About Us</h3>'
                            +'<p><a href="about.html">template</a>, is tellus ac cursus commodo, mauesris condime ntum nibh, ut fermentum mas justo sitters.</p>'
                            +'<ul class="list-unstyled">'
                                +'<li><i class="fa fa-map-marker"></i> 123 Street, Kelburn, Wellington 6012 <br>New Zealand</li>'
                                +'<li class="number"><i class="fa fa-phone"></i> (+64) 123 4567</li>'
                                +'<li><i class="fa fa-envelope"></i> email@email.com</li>'
                                +'<li><i class="fa fa-skype"></i> skype - template</li>'
                            +'</ul>'
                            +'<div class="visible-xs-block visible-sm-block pt20"></div>'
                        +'</div>'
                        +'<div class="footer-widget col-sm-6 col-md-4">'
                            +'<h3>Manager - Useful Links</h3>'
                            +'<p>'
                                +'<ul class="footer-ul">'
                                    +'<li><a href="getPromo.html">Get Promotion</a></li>'
                                    +'<li><a href="DIY.html">DIY</a></li>'
                                    +'<li><a href="result.html">Result</a></li>'
                                    +'<li><a href="contact.html">Feedback</a></li>'
                                +'</ul>'
                            +'</p>'
                            +'<div class="visible-xs-block visible-sm-block pt20"></div>'
                        +'</div>'
                        +'<div class="footer-widget col-sm-6 col-md-4">'
                            +'<h3>Newsletter Registration</h3>'
                            +'<p>Subscribe today to receive the latest <strong>template</strong> news via email. You may unsubscribe from this service at any time.</p>'
                            +'<!-- Newsletter Form-->'
                            +'<form method="get">'
                                +'<div class="input-group newsletter">'
                                    +'<label class="sr-only" for="subscribe-email">Enter your email...</label>'
                                    +'<input type="email" class="form-control" id="subscribe-email" placeholder="Enter your email...">'
                                    +'<span class="input-group-btn">'
                                        +'<button type="submit" class="btn">Send</button>'
                                    +'</span>'
                                +'</div>'
                            +'</form>'
                            +'<p class="newsletter-desc"><strong>No spam:</strong> consectetur adipisicing elit lorem ipsum.</p>'
                        +'</div>'
                    +'</div>'
                +'</div>'
            +'</div>'
            +'<div class="footer-bottom">'
                +'<div class="container">'
                    +'<div class="row">'
                        +'<div class="col-sm-6 social">'
                            +'<ul class="list-inline social">'
                                +'<li>'
                                    +'<a href="" target="_blank">'
                                        +'<img class="icon facebook-icon" src="assets/img/facebook-logo.png">'
                                    +'</a>'
                                +'</li>'
                                +'<li>'
                                    +'<a href="" target="_blank">'
                                        +'<img class="icon twitter-icon" src="assets/img/twitter-logo-on-black-background.png">'
                                    +'</a>'
                                +'</li>'
                                +'<li>'
                                    +'<a href="" target="_blank">'
                                        +'<img class="icon instagram-icon" src="assets/img/instagram-logo.png">'
                                    +'</a>'
                                +'</li>'
                                +'<li>'
                                    +'<a href="" target="_blank">'
                                       +'<img class="icon youtube-icon" src="assets/img/youtube-logotype.png">'
                                    +'</a>'
                                +'</li>'
                            +'</ul>'
                        +'</div>'
                        +'<div class="col-sm-6 credits">'
                            +'<p>&copy; Copyright 2017. All Rights Reserved. </p>'
                            +'<p class="small">'
                                +'<i class="fa fa-angle-right"></i>'
                                +'NZ Developed, Owned and operated by <a href="index.html">Jason Chen</a>'
                            +'</p>'
                        +'</div>'
                    +'</div>'
                +'</div>'
            +'</div>'
            +'<a id="back-to-top" href="#" class="btn btn-primary btn-lg back-to-top" role="button" title="Click to return on the top page" data-toggle="tooltip" data-placement="left">'
            	+'<span class="glyphicon glyphicon-chevron-up"></span>'
        	+'</a>');

	//loading screen
	$(window).load(function(){
		$("#status").fadeOut();
		$("#preloader").delay(350).fadeOut("slow");
		$('body').delay(350).css({'overflow':'visible'});
        $('body').show();
	});

    // scroll body to 0px on click
	$(window).scroll(function () {
        if ($(this).scrollTop() > 50) {
            $('#back-to-top').fadeIn();
        } else {
            $('#back-to-top').fadeOut();
        }
    });

    $('#back-to-top').click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 250);
        return false;
    });
	/*==============================*/  
    

});