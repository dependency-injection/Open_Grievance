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

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="page-scroll" href="#services">Redressal</a>
                    </li>
                    
                    <li>
                        <a class="btn btn-xl" href="SignoutServlet">Sign Out</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="intro-text">
                <div class="intro-lead-in">Thanks for signing up</div>
                <div class="intro-heading">We need you</div>
                <a href="#services" class="page-scroll btn btn-xl">Redressal</a>
            </div>
        </div>
    </header>

    <!-- Services Section -->
    <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Redressal Organization</h2>
                    <h3 class="section-subheading text-muted">Three steps to redressal</h3>
                </div>
            </div>
            <div class="row text-center">
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <img src="common/img/1.png" class="img-responsive img-circle" alt=""/>
                    </span>
                    <h4 class="service-heading">Register Yourself</h4>
                    <p class="text-muted"><a href="organizationregistration.jsp" ><h3>Click</h3></a></p>
                </div>
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <img src="common/img/2.png" class="img-responsive img-circle" alt=""/>
                    </span>
                    <h4 class="service-heading">Register Officer</h4>
                    <p class="text-muted"><a href="officersignup.jsp"><h3>Click</h3></a></p>
                </div>
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <img src="common/img/3.png" class="img-responsive img-circle" alt=""/>
                    </span>
                    <h4 class="service-heading">All Officers</h4>
                    <p class="text-muted"><a href="allofficers.jsp"><h3>Click</h3></a></p>
                </div>
            </div>
        </div>
    </section>

   
   
    
<%@include file="footer.jsp" %>