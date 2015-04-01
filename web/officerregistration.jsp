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
                <a class="navbar-brand page-scroll" href="#page-top">
                    <%    
                       User u =  (User) request.getSession().getAttribute("userobj");
                       if(u != null)
                       {    
                           out.println(u.getUsername());
                       }
                    %>
                </a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="btn btn-xl" href="SignoutServlet">Sign Out</a>
                    </li>
                    
                </ul>
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
                <form name="sentMessage" id="contactForm" novalidate action="OfficerRegistrationServlet" method="POST">
                        <div class="row">
                            <div  class="col-md-6">
                                <div class="form-group">
                                    * All fields are required
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="firstname" type="text" maxlength="50" class="form-control" placeholder="First Name *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="lastname" type="text" maxlength="50" class="form-control" placeholder="Last Name *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                 <div class="form-group">
                                    <textarea name="address" maxlength="255" class="form-control" placeholder="Address *"  required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="pincode" type="number" maxlength="6" class="form-control" placeholder="Pincode *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="phno" type="number" maxlength="15" class="form-control" placeholder="Phone Number *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                
                                <div class="form-group">
                                    <input name="department" type="text" maxlength="50" class="form-control" placeholder="Department *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="designation" type="text" maxlength="50" class="form-control" placeholder="Designation *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="domain" type="text" maxlength="50" class="form-control" placeholder="Domain *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <textarea name="keywords" class="form-control" placeholder="Keywords (space seperated)  *"  required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <select  name="gender"  class="selectpicker" class="form-control" style="color:#848484;font-size: 16px">
                                
                                        <option> MALE </option>
                                        <option> FEMALE </option>
                                    </select>
                                    <p class="help-block alert-danger"></p>
                                </div>
                                
                               
                            </div>
                             
                            
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">Register</button>
                            </div>
                        </div>
                    </form>  
                
            </div>
        </div>
    </header>

    <!-- Services Section -->
   
    
   <%@include file="footer.jsp" %>