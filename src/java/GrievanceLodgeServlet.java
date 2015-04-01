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
import java.util.Date;

@WebServlet(urlPatterns = {"/GrievanceLodgeServlet"})
public class GrievanceLodgeServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            User u = (User)request.getSession().getAttribute("userobj");
            String email = u.getEmail();
            int cl_id = Client.getLatestClientId(email);
            String state = request.getParameter("gcity");
            String city = request.getParameter("gcity");
            String pincode = request.getParameter("gpincode");
            String location = request.getParameter("glocation");
            String description = request.getParameter("gdescription");
            String sector = request.getParameter("gsector");
            Date d = new Date();
            String date = d.toString();
            if(!(state == null || city == null || pincode == null || location == null || description == null || sector == null ||
                    state.equals("") || city.equals("") || pincode.equals("") || location.equals("") || description.equals("") || sector.equals("") || 
                    cl_id == -1))
            {    
                if(sector.equals("Public") || sector.equals("Private"))
                {    
                    if(GrievanceRegistration.insertGrievance(city,description, location, pincode, sector, state, cl_id))
                    {
                        request.getRequestDispatcher("registeredhome.jsp").forward(request, response);
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
