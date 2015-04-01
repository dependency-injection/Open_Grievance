/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package validators;


import entity.DbHelper;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.json.simple.JSONObject;



@WebServlet(name = "Validator", urlPatterns = {"/fieldValidator"})
public class Validator extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String field = request.getParameter("field");
            if(field.equals("username"))
            {
                String uname = request.getParameter("value");
                DbHelper db = new DbHelper();
                Connection conn = db.getConn();
                String query = "select username from USER WHERE username=?";
                PreparedStatement ps;
                try {
                    ps = conn.prepareStatement(query);
                    System.out.println(uname);
                    ps.setString(1, uname);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next())
                    {
                        JSONObject j = new JSONObject();
                        j.put("value",uname);
                        j.put("valid",false);
                        j.put("message","Username not unique");
                        
                        String jsonData = j.toString();
                        out.println(jsonData);
                        System.out.println(jsonData);
                    }
                    else
                    {
                        JSONObject j = new JSONObject();
                        j.put("value",uname);
                        j.put("valid",true);
                        j.put("message","Username OK");
                        
                        String jsonData = j.toString();
                        out.println(jsonData);
                        System.out.println(jsonData);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
            if(field.equals("email"))
            {
                String email = request.getParameter("value");
                DbHelper db = new DbHelper();
                Connection conn = db.getConn();
                String query = "select email from USER WHERE email=?";
                PreparedStatement ps;
                try {
                    ps = conn.prepareStatement(query);
                    System.out.println(email);
                    ps.setString(1, email);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next())
                    {
                        JSONObject j = new JSONObject();
                        j.put("value",email);
                        j.put("valid",false);
                        j.put("message","Email not unique");
                        
                        String jsonData = j.toString();
                        out.println(jsonData);
                        System.out.println(jsonData);
                    }
                    else
                    {
                        JSONObject j = new JSONObject();
                        j.put("value",email);
                        j.put("valid",true);
                        j.put("message","Email OK");
                        
                        String jsonData = j.toString();
                        out.println(jsonData);
                        System.out.println(jsonData);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            
            }
            
            if(field.equals("gdescription"))
            {
                String description = request.getParameter("value");
                String lg = description.toLowerCase();
                String[] grievsplit = lg.split("[\\W]+");
                Set<String> uniquegriev = new HashSet<String>();
                Set<String> uniquekeywords = new HashSet<String>();
                for(String word : grievsplit)
                {
                     uniquegriev.add(word);
                }
                DbHelper db = new DbHelper();
                Connection conn = db.getConn();
                String query = "select * from OFFICER";
                PreparedStatement ps;
                String allKeywords_str = "";
                try {
                    ps = conn.prepareStatement(query);
                    
                    ResultSet rs = ps.executeQuery();
                   while(rs.next())
                    {
                        allKeywords_str += rs.getString("OFF_KEYWORDS");
                    }
                    String lo_str = allKeywords_str.toLowerCase();
                    String[] keysplit = lo_str.split("[\\W]+");
                    for(String word : keysplit)
                    {
                         uniquekeywords.add(word);
                    }
                    uniquegriev.retainAll(uniquekeywords);
                    if(uniquegriev.size() < 3)
                    {
                        JSONObject j = new JSONObject();
                        j.put("value","bogus");
                        j.put("valid",false);
                        j.put("message","Please be more specific, use related terminology...");
                        
                        String jsonData = j.toString(); 
                        out.println(jsonData);
                    }
                    else if(uniquegriev.size() >= 3 && uniquegriev.size() < 6)
                    {
                        JSONObject j = new JSONObject();
                        j.put("value","bogus");
                        j.put("valid",true);
                        j.put("message","Description sounds OK");
                        
                        String jsonData = j.toString(); 
                        out.println(jsonData);
                    }
                    else if(uniquegriev.size() >= 6 && uniquegriev.size() < 10)
                    {
                        JSONObject j = new JSONObject();
                        j.put("value","bogus");
                        j.put("valid",true);
                        j.put("message","Description sounds good!");
                        
                        String jsonData = j.toString(); 
                        out.println(jsonData);
                    }
                    else if(uniquegriev.size() >= 10)
                    {
                        JSONObject j = new JSONObject();
                        j.put("value","bogus");
                        j.put("valid",true);
                        j.put("message","Description sounds great!!");
                        
                        String jsonData = j.toString(); 
                        out.println(jsonData);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Validator.class.getName()).log(Level.SEVERE, null, ex);
                    JSONObject j = new JSONObject();
                        j.put("value","bogus");
                        j.put("valid",false);
                        j.put("message","Unknown error has occured!");
                        
                        String jsonData = j.toString(); 
                        out.println(jsonData);
                }
            
                
            }
            
            if(field.equals("loginVerify"))
            {
                
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                if(!(email == null || password == null || email.equals("") || password.equals("")))
                {
                    User user = User.getUser(email, password);
                    if(user != null)
                    {
                        System.out.println("I was here ");
                        
                        JSONObject j = new JSONObject();
                        j.put("value",email);
                        j.put("valid",true);
                        j.put("message","Successfully");
                        
                        String jsonData = j.toString(); 
                        out.println(jsonData);
                        
                            
                        
                    }
                    else
                    {
                        System.out.println("I was here else");
                        JSONObject j = new JSONObject();
                        j.put("value",email);
                        j.put("valid",false);
                        j.put("message","Email or password invalid");
                        
                        String jsonData = j.toString();
                        out.println(jsonData);
                        System.out.println(jsonData);
                    }
                    
                        
                }
                else{
                    System.out.println("I was here else");
                        JSONObject j = new JSONObject();
                        j.put("value",email);
                        j.put("valid",false);
                        j.put("message","Email or password invalid");
                        
                        String jsonData = j.toString();
                        out.println(jsonData);
                        System.out.println(jsonData);
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
