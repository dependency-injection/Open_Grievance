

package entity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GrievanceRegistration 
{
    
    public static boolean insertGrievance(String city,String description, String location, String pincode, String sector, String state, int cl_id)
    {
        System.out.println("insertgriev");
        DbHelper db = new DbHelper();
        String query = "insert into GRIEVANCEREGISTRATION(GR_CITY, GR_DESCRIPTION, GR_LOCATION, GR_PINCODE, GR_SECTOR, GR_STATE, GR_CLIENT_ID) values(?,?,?,?,?,?,?)";
        Object[] values = {new String(city), new String(description), new String(location), new String(pincode), new String(sector), new String(state), new Integer(cl_id)};
        if(db.insertValues(query, values))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static void assignOrganization(int id)
    {
        DbHelper db = new DbHelper();
    }
    
    public static int getLatestGrievanceId(int id)
    {
        int finalid = -1;
        try
        {    
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select max(ID) from GRIEVANCEREGISTRATION where GR_CLIENT_ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                finalid = rs.getInt("MAX(ID)");
                return finalid;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
        return finalid;
    }
    
    public static String getGrievanceDesc(int id)
    {
        String keywords = "";
        try
        {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select GR_DESCRIPTION from GRIEVANCEREGISTRATION where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                keywords = rs.getString("GR_DESCRIPTION");
                return keywords;
            }
            else
            {
                return keywords;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return keywords;
        }
    }
    
    public static int[] getGrievanceIds(int id)
    {
        int grievcount;
        grievcount = getGrievanceCount(id);
        if(grievcount != -1)
        {
             int gids[] = new int[grievcount];
             int counter = 0;
             try
             {
                DbHelper db = new DbHelper();
                Connection conn = db.getConn();
                String query = "select ID from GRIEVANCEREGISTRATION where GR_CLIENT_ID=?;";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    gids[counter] = rs.getInt("ID");
                    counter ++;
                }
                return gids;
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
    
    
    public static int getGrievanceCount(int id)
    {
        int count = 0;
        try
        {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ID from GRIEVANCEREGISTRATION where GR_CLIENT_ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                count ++;
            }
            return count;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static String[] getDates(int id)
    {
        int count = getGrievanceCount(id);
        if(count != -1)
        {
            String[] alldates = new String[count];
            int counter = 0;
            try
            {
                DbHelper db = new DbHelper();
                Connection conn = db.getConn();
                String query = "select GR_DATE from GRIEVANCEREGISTRATION where GR_CLIENT_ID=?;";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    alldates[counter] = rs.getString("GR_DATE");
                    counter ++;
                }
                return alldates;
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
    
    public static String[] getGrievanceDetails(int id)
    {
        String[] details = new String[13];
        try
        {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select * from GRIEVANCEREGISTRATION where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
              details[0] = rs.getString("ID");
              details[1] = rs.getString("GR_CITY");
              details[2] = rs.getString("GR_DATE");
              details[3] = rs.getString("GR_DESCRIPTION");
              details[4] = rs.getString("GR_LOCATION");
              details[5] = rs.getString("GR_PICS");
              details[6] = rs.getString("GR_PINCODE");
              details[7] = rs.getString("GR_SECTOR");
              details[8] = rs.getString("GR_STATE");
              details[9] = rs.getString("GR_CLIENT_ID");
              details[10] = rs.getString("GR_ORGANIZATION_ID");
              details[11] = rs.getString("GR_OFFICER_ID");
              details[12] = rs.getString("GR_STATUS");
            }
            return details;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getGrievanceDate(int orgid)
    {
        String date = "";
        try
        {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select GR_DATE from GRIEVANCEREGISTRATION where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orgid);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                date = rs.getString("GR_DATE");
                return date;
            }
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public static String[] getOfficerDetailsForGrievance(int oid)
    {
        try
        {    
            String[] details = new String[6];
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select OFF_DEPARTMENT, OFF_DESIGNATION, OFF_DOMAIN, OFF_FIRSTNAME, OFF_LASTNAME, OFF_PHONE from OFFICER where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, oid);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                details[0] = rs.getString("OFF_DEPARTMENT");
                details[1] = rs.getString("OFF_DESIGNATION");
                details[2] = rs.getString("OFF_DOMAIN");
                details[3] = rs.getString("OFF_FIRSTNAME");
                details[4] = rs.getString("OFF_LASTNAME");
                details[5] = rs.getString("OFF_PHONE");
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
        
        public static String getStatus(int id)
        {
            String status;
            try
            {
                DbHelper db = new DbHelper();
                Connection conn = db.getConn();
                String query = "select GR_STATUS from GRIEVANCEREGISTRATION where ID=?;";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    status = rs.getString("GR_STATUS");
                    return status;
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
    

