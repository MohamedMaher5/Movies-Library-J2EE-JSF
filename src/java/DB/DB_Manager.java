
package DB;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DB_Manager {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    String URL;

    public DB_Manager() 
    {
        URL ="jdbc:sqlite:D:\\IT\\3rd Year\\1st Term\\CS 312\\MyWebApp\\moviesDatabase.sqlite";
        
    }
    public void createConnection() throws Exception
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(URL);
    }
    public void releaseResources()throws Exception
    {
        if(stmt != null) stmt.close();
        if(conn != null ) conn.close();
        if(rs!= null) rs.close();
    }
    public ResultSet select (String Q)throws Exception
    {
            createConnection();
            stmt = conn.createStatement();
            rs=stmt.executeQuery(Q);
            return rs;
    }
    public void InsertUpdateDelete(String Q)throws Exception
    {
            createConnection();
            stmt = conn.createStatement();
            stmt.execute(Q);
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        
    }

    public void InsertUpdateDelete(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
