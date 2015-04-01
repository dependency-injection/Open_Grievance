

package entity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



public class Organization 
{
    
    
    public Organization() {
    }

    public static boolean insertOrganization(String address, String altphno, String city,String name, String phno, String pincode, String regno, String state, int org_userid, String sector)
    {
        DbHelper db = new DbHelper();
        String query = "insert into ORGANIZATION(ORG_ADDRESS, ORG_ALTPHONE, ORG_CITY,ORG_NAME, ORG_PHONE, ORG_PINCODE, ORG_REGNO, ORG_STATE, ORG_USERID_ID, ORG_SECTOR) values(?,?,?,?,?,?,?,?,?,?)";
        Object[] values = {new String(address), new String(altphno), new String(city), new String(name), new String(phno), new String(pincode), new String(regno), new String(state), new Integer(org_userid), new String(sector)};
        if(db.insertValues(query, values))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static int getLatestOrgId(String email)
    {
        int finalid = -1;
        try
        {
            int ids = User.getUserId(email);
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select max(ID) from ORGANIZATION where ORG_USERID_ID=?;";
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

   
}
