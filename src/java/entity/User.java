

package entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



public class User 
{
    private String username;
    private String password;
    private String email;
    private String usertype;
    private Client client;
    private Organization organization;
    private GrievanceOfficer grievanceOfficer;

    public User() {
    }

    public User(String email, String password, String username, String usertype) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    
    
    public static boolean insertUser(String email, String password, String username, String usertype)
    {
            System.out.println("==============>insertuser");
            DbHelper db = new DbHelper();
            String query = "insert into USER(EMAIL,PASSWORD,USERNAME,USERTYPE) values(?,?,?,?)";
            String[] values = {new String(email), new String(password), new String(username), new String(usertype)};
            if(db.insertValues(query, values))
            {
                return true;
            }
            else
            {    
                return false;
            }    
    }
    
    public static int getLastId()
    {
        DbHelper db = new DbHelper();
        int id = db.lastInsertId("USER");
        return id;
    }
    
   public static User getUser(String email, String password)
   {
       try
       {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select * from USER where EMAIL=? and PASSWORD=? ;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.first() == false || rs == null)
            {
                System.out.println("============> rs is empty");
                return null;
            }
            else
            {
                int ids = rs.getInt("ID");
                String emails = rs.getString("EMAIL");
                String passwords = rs.getString("PASSWORD");
                String usernames = rs.getString("USERNAME");
                String usertypes = rs.getString("USERTYPE");
                User u = new User(emails, passwords, usernames, usertypes);
                return u;
            }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return null;
   }
   
   public static int getUserId(String email)
   {
       try
       {
            int id = -1;
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ID from USER where EMAIL=?;";
            PreparedStatement ps = conn.prepareStatement(query);    
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.first() == false || rs == null)
            {
                System.out.println("========> rs is empty");
                return id;
            }
            else
            {
                id = rs.getInt("ID");
                return id;
            }
            
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       return -1;
   }

   
    
}
