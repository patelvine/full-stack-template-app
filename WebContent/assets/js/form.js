$().ready(function() {

	$('#loginform').validate({
	    rules:{
		    email:{
		        required:true,
		        email:true
		    },
			password:{
		        required:true,
		        minlength:3,
		        maxlength:10
		    },
		},
	    highlight: function(element) {
	        $(element).closest('.form-group').addClass('has-error');
	    },
	    unhighlight: function(element) {
	        $(element).closest('.form-group').removeClass('has-error');
	    },
	    errorElement: 'span',
	    errorClass: 'help-block',
	    errorPlacement: function(error, element) {
	        if(element.parent('.input-group').length) {
	            error.insertAfter(element.parent());
	        } else {
	            error.insertAfter(element);
	        }
	    }
	});

	$('#signupform').validate({
	    rules:{
	    	name:{
	    		required:true,
	    		alphanumeric:true,
	    		minlength:2,
	    		maxlength:25
	    	},
		    email:{
		        required:true,
		        email:true
		    },
		    phone:{
		    	required:true,
		    	phone:true
		    },
			password:{
		        required:true,
		        minlength:6,
		        maxlength:15
		    },
			confirm_password:{
				required:true,
				minlength: 6,
				maxlength: 15,
				equalTo: "#password"
			},
		    contact:{
		        required:true,
		        alphanumeric:true,
		        minlength:2,
		        maxlength:25
		    },
		    contactTime:{
		        required:true,
		        time:true
		    },
		    position:{
		    	required:true,
		    	alphanumeric:true,
		    	minlength:2,
		        maxlength:25
		    },
		    industry:{
		    	required:true,
		    	alphanumeric:true,
		    	minlength:2,
		        maxlength:25
		    },
		    primaryLocation:{
		    	required:true,
		    	alphanumeric:true,
		    	minlength:2,
		        maxlength:25
		    },
		    numOfLocation:{
		    	required:true,
		    	positiveNum:true
		    },
		    numOfEmp:{
				required:true,
		    	positiveNum:true
		    },
		},
	    highlight: function(element) {
	        $(element).closest('.form-group').addClass('has-error');
	    },
	    unhighlight: function(element) {
	        $(element).closest('.form-group').removeClass('has-error');
	    },
	    errorElement: 'span',
	    errorClass: 'help-block',
	    errorPlacement: function(error, element) {
	        if(element.parent('.input-group').length) {
	            error.insertAfter(element.parent());
	        } else {
	            error.insertAfter(element);
	        }
	    }
	});

	$('#contactform').validate({
	    rules:{
	    	name:{
	    		required:true,
	    		alphanumeric:true,
	    		minlength:2,
	    		maxlength:25
	    	},
		    email:{
		        required:true,
		        email:true
		    },
			mobile:{
				required:false,
		    	phone:true
		    },
		    company:{
		    	required:false,
		    	alphanumeric:true,
		        minlength:2,
		        maxlength:25
		    },
		    subject:{
		    	required:true,
		    	alphanumeric:true,
	    		minlength:2,
	    		maxlength:25
		    },
		},
	    highlight: function(element) {
	        $(element).closest('.form-group').addClass('has-error');
	    },
	    unhighlight: function(element) {
	        $(element).closest('.form-group').removeClass('has-error');
	    },
	    errorElement: 'span',
	    errorClass: 'help-block',
	    errorPlacement: function(error, element) {
	        if(element.parent('.input-group').length) {
	            error.insertAfter(element.parent());
	        } else {
	            error.insertAfter(element);
	        }
	    }
	});

	$('#editForm').validate({
	    rules:{
	    	name:{
	    		required:true,
	    		alphanumeric:true,
	    		minlength:2,
	    		maxlength:25
	    	},
		    phone:{
		    	required:true,
		    	phone:true
		    },
		    industry:{
		    	required:true,
		    	alphanumeric:true,
		    	minlength:2,
		        maxlength:25
		    },
		    position:{
		    	required:true,
		    	alphanumeric:true,
		    	minlength:2,
		        maxlength:25
		    },
		    location:{
		    	required:true,
		    	alphanumeric:true,
		    	minlength:2,
		        maxlength:25
		    },
		},
	    highlight: function(element) {
	        $(element).closest('.form-group').addClass('has-error');
	    },
	    unhighlight: function(element) {
	        $(element).closest('.form-group').removeClass('has-error');
	    },
	    errorElement: 'span',
	    errorClass: 'help-block',
	    errorPlacement: function(error, element) {
	        if(element.parent('.input-group').length) {
	            error.insertAfter(element.parent());
	        } else {
	            error.insertAfter(element);
	        }
	    }
	});
	
	$('#editPassForm').validate({
	    rules:{
	    	c_password:{
				required:true,
		        minlength:6,
		        maxlength:15
	    	},
		    password:{
		        required:true,
		        minlength:6,
		        maxlength:15,
		        notEqual:"#c_password"
		    },
			confirm_password:{
				required:true,
				minlength: 6,
				maxlength: 15,
				equalTo: "#password"
			},
		},
	    highlight: function(element) {
	        $(element).closest('.form-group').addClass('has-error');
	    },
	    unhighlight: function(element) {
	        $(element).closest('.form-group').removeClass('has-error');
	    },
	    errorElement: 'span',
	    errorClass: 'help-block',
	    errorPlacement: function(error, element) {
	        if(element.parent('.input-group').length) {
	            error.insertAfter(element.parent());
	        } else {
	            error.insertAfter(element);
	        }
	    }
	});


	$('#cardForm').validate({
	    rules:{
	    	name:{
	    		required:true,
	    		alphanumeric:true,
	    		minlength:2,
	    		maxlength:25
	    	},
		    price:{
		    	required:true,
		    	positiveNum:true
		    },
		    time:{
		    	required:true,
		    	time:true
		    },
		    location:{
		    	required:true,
		    	alphanumeric:true,
		    	minlength:2,
		        maxlength:25
		    },
		},
	    highlight: function(element) {
	        $(element).closest('.form-group').addClass('has-error');
	    },
	    unhighlight: function(element) {
	        $(element).closest('.form-group').removeClass('has-error');
	    },
	    errorElement: 'span',
	    errorClass: 'help-block',
	    errorPlacement: function(error, element) {
	        if(element.parent('.input-group').length) {
	            error.insertAfter(element.parent());
	        } else {
	            error.insertAfter(element);
	        }
	    }
	});

});
