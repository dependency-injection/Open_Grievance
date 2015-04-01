

package entity;
import java.sql.*;


public class DbHelper 
{
    private Connection conn = null;
    private static String db_name = "open_grievance";
    public static String tb_user = "USER";
    private static String username = "root";
    private static String password  = "knightmare14";
    private static String host = "localhost";
    private static String port = "3306";
    private static String url = "jdbc:mysql://" + host + ":" + port + "/" + db_name;

    public DbHelper() 
    {
        init();
    }
 
    public boolean init()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
     public boolean insertValues(String query, Object[] values)
    {
        try
        {
            
            PreparedStatement pst = conn.prepareStatement(query);
            for(int i = 0; i < values.length; i++)
            {
                System.out.println("=======>>> " + values[i]);
                pst.setObject(i+1, values[i]);
            
            }
            
            pst.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
     
     
     
    public int lastInsertId(String table)
    {
        int lid = -1;
        try
        {
            String query = "select max(ID) from " + table;
            ResultSet rs = getValues(query);
            if(rs.next())
            {
                lid = rs.getInt("max(ID)");
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return lid;
                
    }
    
    public ResultSet getValues(String query)
    {
        ResultSet rs = null;
        try
        {
            Statement st = conn.createStatement();
            st.execute(query);
            rs = st.getResultSet();
            return rs;
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public Connection getConn()
    {
        return conn;
    }
     

}


