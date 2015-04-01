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
                <form  novalidate action="OfficerRedressServlet" method="POST">
                        <div class="row">
                            <div  class="col-md-6">
                                <input type="hidden" name="grievid" value="<%= request.getParameter("grievid") %>"/>  
                                <div class="form-group">
                                    <textarea style="height: 100px;" name="message" maxlength="255" class="form-control" placeholder="Comments * "  required data-validation-required-message="Please enter a message."></textarea>
                                    <p class="help-block text-danger"></p>
                                </div>
                                
                               
                            </div>
                             
                            
                            <div class="clearfix"></div>
                            <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">Redress</button>
                            </div>
                        </div>
                    </form>  
                
            </div>
        </div>
    </header>

    
   
    
    
<%@include file="footer.jsp" %>