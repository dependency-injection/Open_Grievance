$(function() {
    $("#gdescription").keyup(function(){
        var desc = $("#gdescription").val();
        
        $.ajax({
                url: "/OpenGrievance/fieldValidator",
                type: "POST",
                data: {
                    field: "gdescription",
                    value: desc,
                },
                cache: false,
                success: function(data, status, xhr) {
                    // Success message
                    var resObj = JSON.parse(data);
                    if(resObj.valid)
                    {
                        
                        $("#desc_msg").attr("class","help-block alert-success").append("<strong>"+ resObj.message +"</strong>");
                        
                    
                    }
                    else
                    {
                        $("#desc_msg").attr("class","help-block alert-danger").append("<strong>"+ resObj.message +"</strong>");
                    }
                    

                    
                },
                error: function() {
                    // Fail message
                    $('#success').html("<div class='alert alert-danger'>");
                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                        .append("</button>");
                    $('#success > .alert-danger').append("<strong>Sorry, it seems that server is not responding. Please try again later!");
                    $('#success > .alert-danger').append('</div>');
                    //clear all fields
                    $('#contactForm').trigger("reset");
                },
            });
        
    });
    
 
    $("input,textarea").jqBootstrapValidation({
        preventSubmit: true,
        submitError: function($form, event, errors) {
            event.preventDefault()
            // additional error messages or events
        },
        submitSuccess: function($form, event) {
            
           
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
    




