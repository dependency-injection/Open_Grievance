
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.*;


@WebServlet(urlPatterns = {"/OfficerRegistrationServlet"})
public class OfficerRegistrationServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String address = request.getParameter("address");
            String pincode = request.getParameter("pincode");
            String phno = request.getParameter("phno");
            String department = request.getParameter("department");
            String designation = request.getParameter("designation");
            String domain = request.getParameter("domain");
            String keywords = request.getParameter("keywords");
            String gender = request.getParameter("gender");
            User user;
            if(firstname == null || lastname == null ||address == null || pincode == null || phno == null ||department == null || designation == null || domain == null || keywords == null || gender == null || 
                    firstname.equals("") || lastname.equals("") || address.equals("") || pincode.equals("") || phno.equals("") || department.equals("") || designation.equals("") || domain.equals("") || keywords.equals("") || gender.equals(""))
            {
                request.getRequestDispatcher("officerregistration.jsp").forward(request, response);
            }
            else
            {
                user = (User) request.getSession().getAttribute("officerobj");
                int uid = User.getUserId(user.getEmail());
                User user2 = (User) request.getSession().getAttribute("userobj");
                int orgid = Organization.getLatestOrgId(user2.getEmail());
                if(GrievanceOfficer.insertOfficer(address,department, designation, domain, firstname, gender, keywords, lastname, phno, pincode,uid, orgid))
                {
                    request.getRequestDispatcher("organizationhome.jsp").forward(request, response);
                }
                else
                {
                    request.getRequestDispatcher("officerregistration.jsp").forward(request, response);
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
