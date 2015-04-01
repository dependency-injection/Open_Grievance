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


@WebServlet(urlPatterns = {"/ClientRegisterServlet"})
public class ClientRegisterServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String address = request.getParameter("address");
            String state = request.getParameter("state");
            String city = request.getParameter("city");
            String pincode = request.getParameter("pincode");
            String phno = request.getParameter("phno");
            String gender = request.getParameter("gender");
            User u = (User) request.getSession().getAttribute("userobj");
            int ids = u.getUserId(u.getEmail());
            if(!(firstname == null || lastname == null || address == null || state == null || city == null || pincode == null || phno == null || gender == null || 
                    firstname.equals("") || lastname.equals("") || address.equals("") || state.equals("") || city.equals("") || pincode.equals("") || gender.equals("") ||
                    ids == -1))
            {
                if(!(Client.insertClient(address, city, firstname, gender, lastname, phno, pincode, state, ids)))
                {
                    // error message
                    request.getRequestDispatcher("clientregistration.jsp").forward(request, response);
                }
                else
                {
                    request.getRequestDispatcher("registeredhome.jsp").forward(request, response);
                }
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
