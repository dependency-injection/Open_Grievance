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
                <form name="sentMessage" id="contactForm" novalidate action="FindOfficer" method="POST">
                        <div class="row">
                            <div  class="col-md-6">
                                <div class="form-group">
                                    * fields are required
                                    <p class="help-block alert-danger"></p>
                                </div>
                                
                                <div class="form-group">
                                    <input name="gstate" type="text" class="form-control" placeholder="State *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="gcity" type="text" class="form-control" placeholder="City *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                
                                <div class="form-group">
                                    <input name="gpincode" type="number" maxlength="6" class="form-control" placeholder="Pincode *"  required />
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <textarea name="glocation" class="form-control" placeholder="Location *"  required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <textarea id="gdescription" name="gdescription" class="form-control" placeholder="Description *"  required data-validation-required-message="Please enter a message." data-validation-ajax-ajax="/OpenGrievance/fieldValidator"></textarea>
                                    <p id="desc_msg" class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    
                                    <select  name="gsector"  class="selectpicker" class="form-control" style="color:#848484;font-size: 16px">
                                        <option disabled="true">In which sector did the grievance occur ? </option>
                                        <option>Public</option> 
                                        <option>Private</option>
                                        
                                          
                                    </select>
                                    <p class="help-block alert-danger"></p>
                                </div>
                                
                               
                            </div>
                             
                            
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">Lodge</button>
                            </div>
                        </div>
                    </form>  
                
            </div>
        </div>
    </header>

    <!-- Services Section -->
   
    
    
<%@include file="footer.jsp" %>