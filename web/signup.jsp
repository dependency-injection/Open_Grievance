<%@include file="header.jsp" %>

<body id="page-top" class="index">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand page-scroll" href="#page-top">Open Grievance</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="intro-text">
                <form name="sentMessage" id="signupForm" novalidate action="SignupServlet" method="POST">
                        <div class="row">
                            <div  class="col-md-6">
                                <div class="form-group">
                                    * fields are required
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="username" type="text" maxlength="50" class="form-control" placeholder="User Name *"  required data-validation-ajax-ajax="/OpenGrievance/fieldValidator" />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="email"  type="email" maxlength="60" class="form-control" placeholder="Your Email *"  required data-validation-ajax-ajax="/OpenGrievance/fieldValidator" data-validation-required-message="Please enter your email address.">
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input  id="password" name="password" maxlength="255" type="password" class="form-control" placeholder="Your Password *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input  name="password2" type="password" maxlength="255" class="form-control" placeholder="Repeat Your Password *"  required data-validation-match-match="password"/>
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <select  name="usertype"  class="selectpicker" class="form-control" style="color:#848484;font-size: 16px" required="true">
                                
                                        <option disabled="true"> Who do you represent ?  </option>
                                        <%
                                            List<UserType> l = Arrays.asList(UserType.values());
                                            for(UserType u : l)
                                            {
                                        %>
                                        <option> <%= (String) u.name()  %>    </option> 
                                        <%
                                                    
                                            }
                                        %>    
                                    </select>
                                    <p class="help-block alert-danger"></p>
                                </div>
                            </div>
                             
                            
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">Sign Up</button>
                            </div>
                        </div>
                    </form>  
                
            </div>
        </div>
    </header>

    <!-- Services Section -->
   
    
    

   <%@include file="footer.jsp" %>