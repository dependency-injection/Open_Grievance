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
                       User user =  (User) request.getSession().getAttribute("userobj");
                       if(user != null)
                       {    
                           out.println(user.getUsername());
                       }
                    %>
                    
                </a>
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
                       int grievid = Integer.parseInt(request.getParameter("grievid"));
                       System.out.println("mygid : " + grievid);
                       String status = GrievanceRegistration.getStatus(grievid);
                       if(status != null)
                       {        }
                    %>
                    
                </h2> 
                </div>
                    <h3> Your Grievance Status </h3>
                <form name="grievance"  novalidate action="registeredhome.jsp" method="POST">
                    <h3> 
                        <%
                        if(status.equals("N"))
                        {    
                        %>
                            <h3> Your Grievance has not been redressed. </h3>
                    
                 <button type="submit" class="btn btn-xl">Choose</button>
                       <% }
                        else
                        { %>
                            <h3> Your grievance has been redressed !! </h3>
                            <%
                                String[] details = GrievanceRedressed.getRedressalDetails(grievid);
                            %>
                            <table border="1">
                                <tr>
                                <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Date Of Redressal </td>
                                <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= details[0] %> </td>
                                </tr>
                                <tr>
                                <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Officer Message </td>
                                <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= details[1] %> </td>
                                </tr>
                                <tr>
                                <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Time (in hours) </td>
                                <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= details[2] %> </td>
                                </tr>
                            </table>
                                <button type="submit" class="btn btn-xl">Okay</button>
                        <%}    
                            %>
                                
                        

                 </form> 
               </div> 
            
        </div>
    </header>

    <!-- Services Section -->
   
    
    
<%@include file="footer.jsp" %>
