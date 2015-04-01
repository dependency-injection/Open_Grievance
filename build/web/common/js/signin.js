$(function() {
    

 
    $("input,textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
            event.preventDefault(); // prevent default submit behaviour
            // get values from FORM
            
            var email = $("input#email").val();
            var password = $("input#password").val();
            
            $.ajax({
                url: "/OpenGrievance/validator/loginValidator",
                type: "POST",
                data: {
                    field: "loginVerify",
                    email: email,
                    password: password
                },
                cache: false,
                success: function(data, status, xhr) {
                    // Success message
                    var resObj = JSON.parse(data);
                    if(resObj.valid)
                    {
                        $('#success').html("<div class='alert alert-success'>");
                    $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-success')
                        .append("<strong>You are logged in succesfully</strong>");
                    $('#success > .alert-success')
                        .append('</div>');
                    }
                    else
                    {
                        $('#success').html("<div class='alert alert-danger'>");
                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-danger').append("<strong>" + resObj.message + "<strong>");
                    $('#success > .alert-danger').append('</div>');
                    //clear all fields
                    $('#contactForm').trigger("reset");
                    }
                    

                    //clear all fields
                    $('#contactForm').trigger("reset");
                },
                error: function() {
                    // Fail message
                    $('#success').html("<div class='alert alert-danger'>");
                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-danger').append("<strong>Sorry " + firstName + ", it seems that my mail server is not responding. Please try again later!");
                    $('#success > .alert-danger').append('</div>');
                    //clear all fields
                    $('#contactForm').trigger("reset");
                },
            })
        },
        filter: function() {
            return $(this).is(":visible");
        },
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
});

/*When clicking on Full hide fail/success boxes */
$('#email').focus(function() {
    $('#success').html('');
});
    




