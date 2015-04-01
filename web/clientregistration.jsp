<%@include file="header.jsp" %>

<body id="page-top" class="index">

    
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            
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
            
        </div>
        
    </nav>

    
    <header>
        <div class="container">
            <div class="intro-text">
                <form name="sentMessage" id="contactForm" novalidate action="ClientRegisterServlet" method="POST">
                        <div class="row">
                            <div  class="col-md-6">
                                <div class="form-group">
                                    * fields are required
                                    <p class="help-block alert-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="firstname" maxlength="50" type="text" class="form-control" placeholder="First Name *"  required />
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="lastname" maxlength="50" type="text" class="form-control" placeholder="Last Name *"  required />
                                    <p class="help-block text-danger"></p>
                                </div>
                                
                                <div class="form-group">
                                    <textarea name="address" maxlength="255" class="form-control" placeholder="Address *"  required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block text-danger"></p>
                                </div>
                                
                                <div class="form-group">
                                    <input name="state" type="text" class="form-control" maxlength="50" placeholder="State *"  required />
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="city" type="text" class="form-control" maxlength="50" placeholder="City *"  required />
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="pincode" type="number" class="form-control" maxlength="6" placeholder="Pincode *"  required />
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <input name="phno" type="number" class="form-control" maxlength="15" placeholder="Phone Number *"  required />
                                    <p class="help-block text-danger"></p>
                                </div>
                                <div class="form-group">
                                    <select  name="gender"  class="selectpicker" class="form-control" style="color:#848484;font-size: 16px">
                                
                                        
                                        <%
                                            List<Gender> l = Arrays.asList(Gender.values());
                                            for(Gender g : l)
                                            {
                                        %>
                                        <option> <%= (String) g.name()  %>    </option> 
                                        <%
                                                    
                                            }
                                        %>    
                                    </select>
                                    <p class="help-block text-danger"></p>
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

    
   
    
    
<%@include file="footer.jsp" %>