

package entity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Assign 
{
    public static  int[] getOrgBySector(String sector)
    {
        int[] def = {-1}; 
        int size = 0;
        int counter = 0;
        int ids[];
        try
        {
            
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ID from ORGANIZATION where ORG_SECTOR=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, sector);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                size ++ ;
                System.out.println("=========>" + rs.getInt("ID"));
            }
            if(size == 0)
            {
                return def;
            }    
            ids = new int[size];
            rs.beforeFirst();
            while(rs.next())
            {
                System.out.println("====>" + rs.getInt("ID"));
                ids[counter] = rs.getInt("ID");
                counter ++;
            }
            return ids;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }
         return def;   
        
    }
    
    public static int[] getOfficerIds(int orgid)
    {
        int[] def = {-1};
        
        int offids[];
        int counter = 0;
        int size = 0;
        try
        {   
            
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ID from OFFICER where OFF_ORG_ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, orgid);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                size ++;
            }
            if(size == 0)
            {
                return def;
            }
            offids = new int[size];
            rs.beforeFirst();
            while(rs.next())
            {
                offids[counter] = rs.getInt("ID");
                counter ++;
            }
            return offids;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return def;
    }
    
    public static boolean checkOfficers(int id)
    {
        try
        {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ID from OFFICER where OFF_ORG_ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
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
    
    public static String getKeywords(int id)
    {
        String keywords = "";
        try
        {
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select OFF_KEYWORDS from OFFICER where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                keywords = rs.getString("OFF_KEYWORDS");
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
    
    public static String getState(int id)
    {
        try
        {    
            String state = "";
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ORG_STATE from ORGANIZATION where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                state = rs.getString("ORG_STATE");
            }
            return state;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getCity(int id)
    {
        try
        {    
            String city = "";
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ORG_CITY from ORGANIZATION where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                city = rs.getString("ORG_CITY");
            }
            return city;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String[] getOrgDetails(int id)
    {
        try
        {
            String details[] = new String[3];
            DbHelper db = new DbHelper();
            Connection conn = db.getConn();
            String query = "select ORG_NAME, ORG_STATE, ORG_CITY from ORGANIZATION where ID=?;";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                details[0] = rs.getString("ORG_NAME");
                details[1] = rs.getString("ORG_STATE");
                details[2] = rs.getString("ORG_CITY");
            }
            return details;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean assignOrgGriev(int orgid, int gid)
    {
        DbHelper db = new DbHelper();
        String query = "update GRIEVANCEREGISTRATION set GR_ORGANIZATION_ID=? where ID=?;";
        Object values[] = {new Integer(orgid), new Integer(gid)};
        if(db.insertValues(query, values))
        {
            return true;
        }   
        else
        {
            return false;
        }
         
    }
    
    public static boolean assignGrievOfficer(int gid, int offid)
    {
        DbHelper db = new DbHelper();
        String query = "update GRIEVANCEREGISTRATION set GR_OFFICER_ID=? where ID=?;";
        Object values[] = {new Integer(offid), new Integer(gid)};
        if(db.insertValues(query, values))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
