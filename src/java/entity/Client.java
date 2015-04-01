

package entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;



public class Client 
{
    
    public static boolean insertClient(String address, String city, String firstname, String gender, String lastname, String phone, String pincode, String state, int cl_user_id)
    {
        DbHelper db = new DbHelper();
        String query = "insert into CLIENT(ADDRESS, CITY, FIRSTNAME, GENDER, LASTNAME, PHONE, PINCODE, STATE, CL_USERID_ID) values(?,?,?,?,?,?,?,?,?)";
        Object[] values = {new String(address), new String(city), new String(firstname), new String(gender), new String(lastname), new String(phone), new String(pincode), new String(state), new Integer(cl_user_id)};
        if(db.insertValues(query, values))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static int getLatestClientId(String email)
    {
        int finalid = -1;
        try
        {
            int ids = User.getUserId(email);
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select max(ID) from CLIENT where CL_USERID_ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, ids);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                finalid = rs.getInt("max(ID)");
                return finalid;
            }
             
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return finalid;
    }
    
    public static int getClientIdFromGrievance(int id)
    {
        int cid = -1;
        try
        {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select GR_CLIENT_ID from GRIEVANCEREGISTRATION where ID=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                cid = rs.getInt("GR_CLIENT_ID");
                return cid;
            }
            return -1;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static String[] getClientDetailsForRedressal(int cid)
    {
        String[] details = new String[8];
        try
        {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ADDRESS, CITY, FIRSTNAME, GENDER, LASTNAME, PHONE, PINCODE, STATE from CLIENT where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
               details[0] = rs.getString("ADDRESS");
               details[1] = rs.getString("CITY");
               details[2] = rs.getString("FIRSTNAME");
               details[3] = rs.getString("GENDER");
               details[4] = rs.getString("LASTNAME");
               details[5] = rs.getString("PHONE");
               details[6] = rs.getString("PINCODE");
               details[7] = rs.getString("STATE");
               return details;
            }
            return null;
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    

    
    
}
