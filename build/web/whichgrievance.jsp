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
                       int userid = GrievanceOfficer.getUserIdFromEmail(u.getEmail());
                       int offid = GrievanceOfficer.getOfficerIdFromUserId(userid);
                       System.out.println("======> Offid : " + offid);
                       int[] grievids = GrievanceOfficer.getGrievIds(offid);
                       
                    %>
                    
                </h2> 
                </div>
                    <h3> Grievances For Redressal </h3>
                <form name="grievance"  novalidate action="officergrievancedisplay.jsp" method="POST">
                    <h3> 
                        <%
                        if(grievids.length != 0)
                        {    
                        %>
                        <select style="color:#565454" name="gids">
                            
                            <option value="nothing" > Choose Your Grievance </option>
                            <%
                            for(int i=0;i<grievids.length;i++)
                            {    
                                
                            %>
                            <option value= <%= grievids[i] %> > <%= GrievanceOfficer.getGrievanceDate(grievids[i]) %> </option>
                            <%
                            } %>
                            </select>  
                            </h3>
                    
                 <button type="submit" class="btn btn-xl">Redress</button>
                       <% }
                        else
                        { %>
                            <h3> No Grievances Assigned </h3>
                        <%}    
                            %>
                                
                        

                 </form> 
               </div> 
            
        </div>
    </header>

    <!-- Services Section -->
   
    
    
<%@include file="footer.jsp" %>