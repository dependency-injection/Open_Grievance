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
                <form >
                 <table border="1">
                     <tr> 
                               <th style="font-size: 25px;padding: 10px;color: #FFFFFF"> First Name </th>
                               <th style="font-size: 25px;padding: 10px;color: #FFFFFF"> Last Name </th>
                               <th style="font-size: 25px;padding: 10px;color: #FFFFFF"> Department </th>
                               <th style="font-size: 25px;padding: 10px;color: #FFFFFF"> Designation </th>
                     </tr> 
                     <%
                        User user =  (User) request.getSession().getAttribute("userobj");
                        int orgid = Organization.getLatestOrgId(user.getEmail());
                        int[] alloffs = Assign.getOfficerIds(orgid);
                        for(int i=0; i<alloffs.length;i++)
                        {
                           %>
                             
                           <tr >
                               <% 
                            
                                    String[] details = GrievanceOfficer.getOfficer(alloffs[i]);
                                    if(details != null)
                                    {
                                        %>
                                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF">  <%= details[0] %> </td>
                                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"> <%= details[1] %> </td>
                                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"> <%= details[2] %> </td>
                                        <td style="font-size: 25px;padding: 10px;color: #FFFFFF"> <%= details[3] %> </td>
                                        <td><a style="margin: 15px" href="updateOfficer.jsp?id=<%= details[10] %>" class="btn btn-lg">Update</a> </td>
                                        <td><a style="margin: 15px" href="updateOfficer.jsp?id=<%= details[10] %>" class="btn btn-lg">Delete</a> </td>
                                        <%
                                    }
                                    else
                                    { %>
                                    <td style="font-size: 15px"> No officers registered</td>
                                 <%   
                                    break;
                                    }
                               %>
                               
                           </tr>
                           <%    
                        }
                         
                      %>   
                     
                     
                 </table>
                 
                 
             </form>    
                
            </div>
        </div>
    </header>

    
   
 <%@include file="footer.jsp" %>