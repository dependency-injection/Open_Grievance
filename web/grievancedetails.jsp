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
                <a class="navbar-brand page-scroll" href="#page-top">Open Grievance</a>
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
                <form method="POST" action="grievancestatus.jsp">
                    <%
                       int grievid = Integer.parseInt(request.getParameter("grievids")); 
                       String[] details = GrievanceRegistration.getGrievanceDetails(grievid);
                       String[] offdetails = GrievanceRegistration.getOfficerDetailsForGrievance(Integer.parseInt(details[11]));
                    %>   
                    <input type="hidden" name="grievid" value="<%= grievid %>"/>
                    <table border="1">
                    <tr>
                         <td colspan="2" style="font-size: 30px;padding: 10px;color: #FFFFFF">Grievance Details</td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">State </td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= details[8] %> </td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">City</td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= details[1] %> </td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Pincode</td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= details[6] %> </td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Date</td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= details[2] %> </td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Description</td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= details[3] %> </td>
                    </tr>
                    
                </table>
                    <br/>
                    <br/>
                <table border="1">
                    <tr>
                         <td colspan="2" style="font-size: 30px;padding: 10px;color: #FFFFFF">Officer Details</td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Name </td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= offdetails[3] + " " + offdetails[4] %> </td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Department</td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= offdetails[0] %> </td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Designation</td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= offdetails[1] %> </td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Domain</td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= offdetails[2] %> </td>
                    </tr>
                    <tr>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">Phone</td>
                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"><%= offdetails[5] %> </td>
                    </tr>
                    
                </table>
                 <div class="col-lg-12 text-center">
                                <div id="success"></div>
                                <button type="submit" class="btn btn-xl">Track Grievance</button>
                 </div>
                 
             </form>    
                
            </div>
        </div>
    </header>

    
   
 <%@include file="footer.jsp" %>