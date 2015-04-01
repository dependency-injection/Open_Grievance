

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;


@WebServlet(urlPatterns = {"/FindOfficer"})
public class FindOfficer extends HttpServlet {
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            User u = (User)request.getSession().getAttribute("userobj");
            String email = u.getEmail();
            int cl_id = Client.getLatestClientId(email);
            String state = request.getParameter("gstate");
            String city = request.getParameter("gcity");
            String pincode = request.getParameter("gpincode");
            String location = request.getParameter("glocation");
            String description = request.getParameter("gdescription");
            String sector = request.getParameter("gsector");
            
            if(!(state == null || city == null || pincode == null || location == null || description == null || sector == null ||
                    state.equals("") || city.equals("") || pincode.equals("") || location.equals("") || description.equals("") || sector.equals("") || 
                    cl_id == -1))
            {    
                if(sector.equals("Public") || sector.equals("Private"))
                {    
                    if(GrievanceRegistration.insertGrievance(city,description, location, pincode, sector, state, cl_id))
                    {
          
            String lg = description.toLowerCase();
            String[] grievsplit = lg.split("[\\W]+");
            Set<String> uniquegriev = new HashSet<String>();
            for(String word : grievsplit)
            {
                 uniquegriev.add(word);
            }
            String[] finalgrievance = new String[uniquegriev.size()];
            finalgrievance = uniquegriev.toArray(finalgrievance);
            int counter = 0;
            int counter2 = 0;
            int[] officerids;
            int[] orgids = Assign.getOrgBySector(sector);
            if(orgids[0] != -1)
            {    
                for(int i = 0;i<orgids.length;i++)
                {
                    if(Assign.checkOfficers(orgids[i]))
                    {
                        counter ++ ;
                    }
                }
                int[] lengtharray =  new int[counter];
                
                for(int i=0;i<lengtharray.length;i++)
                {
                     officerids = Assign.getOfficerIds(orgids[i]);
                    if(officerids[0] == -1)
                    {
                        lengtharray[i] = 0;
                    }
                    else
                    {
                        lengtharray[i] = officerids.length;
                    }
                }
                int[][] mat = new int[lengtharray.length][];
                for(int i=0;i<lengtharray.length;i++)
                {
                    mat[i] = new int[lengtharray[i]];
                }
                
                for(int i=0;i<lengtharray.length;i++)
                {
                    officerids = Assign.getOfficerIds(orgids[i]);
                    int olength;
                    if(officerids[0] == -1)
                    {
                        olength = 0;
                    }
                    else
                    {
                        olength = officerids.length;
                    }
                    for(int j=0;j<olength;j++)
                    {
                        mat[i][j] = officerids[j];
                        
                    }
                    
                }
                int[] allids = new int[lengtharray.length];
                int[] compatible = new int[lengtharray.length];
                ArrayList<Integer> mylist = new ArrayList<>();
                String offkeys = "";
                int c = 0;
                for(int i=0;i<lengtharray.length;i++)
                {
                    for(int j =0;j<mat[i].length;j++)
                    {
                        offkeys += " " + Assign.getKeywords(mat[i][j]);
                    }
                    String lo = offkeys.toLowerCase();
                    
                    String[] offkeyssplit = lo.split("[\\W]+");
                    Set<String> uniquewords = new HashSet<String>();
                    for(String word : offkeyssplit)
                    {
                         uniquewords.add(word);
                    }
                    String[] finalkeywords = new String[uniquewords.size()];
                    finalkeywords = uniquewords.toArray(finalkeywords);
                    for(int x = 0; x<finalgrievance.length;x++)
                    {
                        for(int y=0;y<finalkeywords.length;y++)
                        {
                            if(finalgrievance[x].equals(finalkeywords[y]))
                            {
                                counter2 ++;
                            }
                        }
                    }
                    if( counter2 >= 3)
                    {
                        compatible[c] = counter2;
                        allids[c] = orgids[i];
                        mylist.add(orgids[i]);
                        
                        c ++;
                    }
                    
                    counter2 = 0;
                    offkeys = "";
                    
                } 
                int[] newcompatible = new int[c];
                for(int la = 0;la< c;la++)
                {
                     newcompatible[la] =  compatible[la];
                     
                }
                int[] newallids = new int[c];
                for(int la = 0;la<c;la++)
                {
                    newallids[la] = allids[la];
                }
                
                for(int x = 0; x < newcompatible.length; x++)
                {
                    for(int y = 0; y < newcompatible.length - x - 1; y++)
                    {
                        if(newcompatible[y] < newcompatible[y+1])
                        {
                            int temp1 = newcompatible[y];
                             newcompatible[y] = newcompatible[y+1];
                             newcompatible[y+1] = temp1;
                             int temp2 = newallids[y];
                             newallids[y] = newallids[y+1];
                             newallids[y+1] = temp2;
                             
                        }
                    }
                }
                for(int i = 0; i<newallids.length;i++)
                {
                    System.out.println("allnew ids" + newallids[i]);
                }
                int[] finalanswer = new int[newallids.length];
                int lastcounter = 0;
                for(int i=0;i<newallids.length;i++)
                {
                    if(state.toLowerCase().equals(Assign.getState(newallids[i]).toLowerCase()))
                    {
                        if(city.toLowerCase().equals(Assign.getCity(newallids[i]).toLowerCase()))
                        {
                            finalanswer[lastcounter] = newallids[i];
                            lastcounter ++;
                        }
                    }
                    
                }
                int[] idobj = new int[lastcounter];
                for(int i=0;i<lastcounter;i++)
                {
                    System.out.println("final answer = " + finalanswer[i]);
                    idobj[i] = finalanswer[i];
                }
                for(int i=0;i<newallids.length;i++)
                {
                    System.out.println(newallids[i]);
                }
                for(int i = 0; i<idobj.length;i++)
                {
                    System.out.println(idobj[i]);
                }
                request.getSession().setAttribute("orgids", idobj);
                request.getRequestDispatcher("userchoose.jsp").forward(request, response);
                
                    
                
                
            }
            else
            {
                // Redirect to sorry page!!
            }
                        
          }
           else
                    {
                        request.getRequestDispatcher("lodgegrievance.jsp").forward(request, response);
                    }
                }
                else
                {
                    request.getRequestDispatcher("lodgegrievance.jsp").forward(request, response);
                }
            }
            else
            {
                request.getRequestDispatcher("lodgegrievance.jsp").forward(request, response);
            }
            

            
            
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
