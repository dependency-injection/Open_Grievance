/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.*;
import java.util.HashSet;
import java.util.Set;

@WebServlet(urlPatterns = {"/AssignOfficerServlet"})
public class AssignOfficerServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int orgid = Integer.parseInt(request.getParameter("orgid"));
            User u = (User) request.getSession().getAttribute("userobj");
            int latestcid = Client.getLatestClientId(u.getEmail());
            int latestdid = GrievanceRegistration.getLatestGrievanceId(latestcid);
            if((Assign.assignOrgGriev(orgid, latestdid)))
            {
                int[] offids = Assign.getOfficerIds(orgid);
                int[] compat = new int[offids.length];
                int counter = 0;
                for(int i = 0;i<offids.length;i++)
                {
                    String keywords = Assign.getKeywords(offids[i]).toLowerCase();
                    String desc = GrievanceRegistration.getGrievanceDesc(latestdid).toLowerCase();
                    String[] keywordssplit = keywords.split("[\\W]+");
                    Set<String> uniquekeywords = new HashSet<String>();
                    for(String word : keywordssplit)
                    {
                        uniquekeywords.add(word);
                    }
                    String[] finalkeywords = new String[uniquekeywords.size()];
                    finalkeywords = uniquekeywords.toArray(finalkeywords);
                    
                    String[] descsplit = desc.split("[\\W]+");
                    Set<String> uniquedesc = new HashSet<String>();
                    for(String word : descsplit)
                    {
                        uniquedesc.add(word);
                    }
                    String[] finaldesc = new String[uniquedesc.size()];
                    finaldesc = uniquedesc.toArray(finaldesc);
                    for(int x = 0; x < finaldesc.length; x++)
                    {
                        for(int y=0; y< finalkeywords.length;y++)
                        {
                            if(finaldesc[x].equals(finalkeywords[y]))
                            {
                                counter ++;
                            }
                        }
                    }
                    compat[i] = counter;
                    counter = 0;
                }
                
                for(int x = 0; x< compat.length;x++)
                {
                    for(int y = 0; y<compat.length - x - 1; y++)
                    {
                        if(compat[y] < compat[y+1])
                        {
                            int temp1 = compat[y];
                            compat[y] = compat[y+1];
                            compat[y+1] = temp1;
                            int temp2 = offids[y];
                            offids[y] = offids[y+1];
                            offids[y+1] = temp2;
                        }
                    }
                }
                
                int maxvalue = compat[0];
                int counter2 = 0;
                for(int i=0;i<compat.length;i++)
                {
                    if(compat[i] == maxvalue)
                    {
                        counter2 ++;
                    }
                }
                if(counter2 == 1)
                {
                    if(Assign.assignGrievOfficer(latestdid, offids[0]))
                    {
                        request.getRequestDispatcher("registeredhome.jsp").forward(request, response);
                    }
                    else
                    {
                        // error page
                    }
                }
                else
                {
                    int min = 0;
                    int max = counter2 -1;
                    int rno = min + (int) (Math.random() * ((max - min )+1));
                    if(Assign.assignGrievOfficer(latestdid, offids[rno]))
                    {
                        request.getRequestDispatcher("registeredhome.jsp").forward(request, response);
                    }
                    else
                    {
                        // error page
                    }
                }

            } // if
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
