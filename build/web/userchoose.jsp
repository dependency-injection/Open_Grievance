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
                <a class="navbar-brand page-scroll" href="#page-top">Word Grievance</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
             <ul class="nav navbar-nav navbar-right">
                    <li class="hidden">
                        <a href="#page-top"></a>
                    </li>
                    <li>
                        <a class="btn btn-xl" href="SignoutServlet">Sign Out</a>
                    </li>
                </ul>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

    <!-- Header -->
    <header>
        <div class="container">
            <div class="intro-text"> 
                <div class="intro-lead-in">
                <h2> Hello
                <%    
                       User u =  (User) request.getSession().getAttribute("userobj");
                       if(u != null)
                       {    
                           out.println(u.getUsername());
                       }
                       int[] orgids = (int[]) request.getSession().getAttribute("orgids");
                    %>
                    these are the best options for you 
                </h2> 
                </div>
                    <h3> In descending order </h3>
                <form name="sentMessage" id="contactForm" novalidate action="AssignOfficerServlet" method="POST">
                    <h3> 
                        <%
                        if(orgids.length != 0)
                        {    
                        %>
                        <select style="color:#565454" name="orgid">
                            
                            <option value="nothing" > Choose An Organization </option>
                            <%
                            for(int i=0;i<orgids.length;i++)
                            {    
                                String[] options = Assign.getOrgDetails(orgids[i]);
                            %>
                            <option value= <%= orgids[i] %> > <%= options[0] %> </option>
                            <%
                            } %>
                            </select>  
                            </h3>
                    
                 <button type="submit" class="btn btn-xl">Choose</button>
                       <% }
                        else
                        { %>
                            <h3> Sorry No Organization matches your description </h3>
                        <%}    
                            %>
                                
                        

                 </form> 
               </div> 
            
        </div>
    </header>

    <!-- Services Section -->
   
    
    
<%@include file="footer.jsp" %>