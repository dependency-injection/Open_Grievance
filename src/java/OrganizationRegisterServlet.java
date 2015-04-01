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

@WebServlet(urlPatterns = {"/OrganizationRegisterServlet"})
public class OrganizationRegisterServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String regno = request.getParameter("regno");
            String state = request.getParameter("state");
            String city = request.getParameter("city");
            String address = request.getParameter("address");
            String pincode = request.getParameter("pincode");
            String phno = request.getParameter("phno");
            String altphno = request.getParameter("altphno");
            String sector = request.getParameter("sector");
            User user = (User) request.getSession().getAttribute("userobj");
            int ids = User.getUserId(user.getEmail());
            if(!(Organization.insertOrganization(address, altphno, city,name, phno, pincode, regno, state, ids, sector)))
            {
                // error msg
                request.getRequestDispatcher("organizationregistration.jsp").forward(request, response);
            }
            else
            {
                request.getRequestDispatcher("organizationhome.jsp").forward(request, response);
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
