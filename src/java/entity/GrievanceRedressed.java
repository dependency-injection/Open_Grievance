

package entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;



public class GrievanceRedressed 
{
   public static boolean insertGrievanceRedressed(String date, String message, float timetaken, int gid)
   {
      try
      {
          DbHelper db = new DbHelper();
          String query = "insert into GRIEVANCEREDRESSED(RG_DATE, RG_MESSAGE, RG_TIMETAKEN, RG_GRIEVANCE_ID) values(?,?,?,?)";
          Object[] values = {new String(date), new String(message), new Float(timetaken), new Integer(gid)};
          if(db.insertValues(query, values))
          {
              return true;
          }
          else
          {
              return false;
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
          return false;
      }
   }
   
   
   public static void updateStatus(int gid)
   {
       try
       {
           DbHelper db = new DbHelper();
           Connection conn = db.getConn();
           String query = "update GRIEVANCEREGISTRATION set GR_STATUS='R' where ID=?;";
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setInt(1, gid);
           ps.executeUpdate();
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
   }
   
   public static String[] getRedressalDetails(int id)
   {
       String[] details = new String[3];
       try
       {
           DbHelper db = new DbHelper();
           Connection conn = db.getConn();
           String query = "select RG_DATE, RG_MESSAGE, RG_TIMETAKEN from GRIEVANCEREDRESSED where RG_GRIEVANCE_ID=?;";
           PreparedStatement ps = conn.prepareStatement(query);
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next())
           {
               details[0] = rs.getString("RG_DATE");
               details[1] = rs.getString("RG_MESSAGE");
               details[2] = rs.getString("RG_TIMETAKEN");
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
