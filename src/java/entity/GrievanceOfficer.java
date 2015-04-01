

package entity;

import static entity.GrievanceRegistration.getGrievanceCount;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


public class GrievanceOfficer 
{
   public static boolean insertOfficer(String address,String department, String designation,String domain, String firstname, String gender, String keywords, String lastname, String phno, String pincode,int off_userid, int org_id)
   {
       DbHelper db = new DbHelper();
       String query = "insert into OFFICER(OFF_ADDRESS, OFF_DEPARTMENT, OFF_DESIGNATION, OFF_DOMAIN, OFF_FIRSTNAME, OFF_GENDER, OFF_KEYWORDS, OFF_LASTNAME, OFF_PHONE, OFF_PINCODE, OFF_USERID_ID, OFF_ORG_ID) values(?,?,?,?,?,?,?,?,?,?,?,?)";
       Object[] values = {new String(address),new String(department), new String(designation), new String(domain), new String(firstname), new String(gender), new String(keywords), new String(lastname), new String(phno), new String(pincode),new Integer(off_userid), new Integer(org_id)};
       if(db.insertValues(query, values))
       {
           return true;
       }
       else
       {
           return false;
       }
   }
   
   public static String[] getOfficer(int id)
   {
       String[] details = new String[11];
       try
       {    
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select * from OFFICER where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                details[0] = rs.getString("OFF_FIRSTNAME");
                details[1] = rs.getString("OFF_LASTNAME");
                details[2] = rs.getString("OFF_DEPARTMENT");
                details[3] = rs.getString("OFF_DESIGNATION");
                details[4] = rs.getString("OFF_ADDRESS");
                details[5] = rs.getString("OFF_DOMAIN");
                details[6] = rs.getString("OFF_GENDER");
                details[7] = rs.getString("OFF_KEYWORDS");
                details[8] = rs.getString("OFF_PHONE");
                details[9] = rs.getString("OFF_PINCODE");
                details[10] = rs.getString("ID");
            }
            return details;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
       
   }
   
   public static int getGrievanceNumber(int offid)
   {
       int count = 0;
       try
       {
           DbHelper db = new DbHelper();
           Connection conn = db.getConn();
           String query = "select count(ID) as NUMBERS from GRIEVANCEREGISTRATION where GR_OFFICER_ID=?;";
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setInt(1, offid);
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           { 
               count = rs.getInt("NUMBERS");
           }
           System.out.println("Count === " + count);
           return count;
           
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return -1;
       }
   }
   
   public boolean updateOfficer(int id, String[] details)
   {
       
       try
       {    
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "UPDATE OFFICER SET OFF_FIRSTNAME=?, OFF_LASTNAME=?, OFF_DEPARTMENT=?, OFF_DESIGNATION=?, OFF_ADDRESS=?, OFF_DOMAIN=?, OFF_GENDER=?, OFF_KEYWORDS=?, OFF_PHONE=?, OFF_PINCODE=? WHERE ID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,details[0]);
            ps.setString(2,details[1]);
            ps.setString(3,details[2]);
            ps.setString(4,details[3]);
            ps.setString(5,details[4]);
            ps.setString(6,details[5]);
            ps.setString(7,details[6]);
            ps.setString(8,details[7]);
            ps.setString(9,details[8]);
            ps.setString(10,details[9]);
            ps.setString(11,details[10]);
            
            int c = ps.executeUpdate();
            if(c == 1)
                return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        return false;
       
   }
   
   public static int[] getGrievIds(int id)
   {
       int count = 0;
       int counter = getGrievanceNumber(id);
       int[] grievids;
       if(counter != -1)
       {
           try
           {
               grievids = new int[counter];
                DbHelper db = new DbHelper();
                Connection conn = db.getConn();
                String query = "select ID from GRIEVANCEREGISTRATION where GR_OFFICER_ID=?;";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    grievids[count] = rs.getInt("ID");
                    count ++;
                }
                 System.out.println("Method worked fine");
                return grievids;
           }
           catch(Exception e)
           {
               e.printStackTrace();
               return null;
           }
       }
       else
       {
           return null;
       }
      
   }
   
   public static String getGrievanceDate(int id)
   {
       String grdate = "";
       try
       {
           DbHelper db = new DbHelper();
           Connection conn = db.getConn();
           String query = "select GR_DATE from GRIEVANCEREGISTRATION where ID=?;";
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           {
               grdate = rs.getString("GR_DATE");
               return grdate;
           }
           return null;
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }
       
   }
   
   public static int getOfficerIdFromUserId(int userid)
   {
       int offid = -1;
       try
       {
           DbHelper db = new DbHelper();
           Connection conn = db.getConn();
           String query = "select ID from OFFICER where OFF_USERID_ID=?;";
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setInt(1, userid);
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           {
               offid = rs.getInt("ID");
               return offid;
           }
           return -1;
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return -1;
       }
   }
   
   public static int getUserIdFromEmail(String email)
   {
       int offid = -1;
       try
       {
           DbHelper db= new DbHelper();
           Connection conn = db.getConn();
           String query = "select ID from USER where EMAIL=?;";
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setString(1, email);
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           {
               offid = rs.getInt("ID");
               return offid;
           }
           return -1;
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return -1;
       }
   }
   
   
   
   
    
}
