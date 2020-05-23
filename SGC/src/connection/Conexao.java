package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexao {
    
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL = "jdbc:mariadb://localhost:3306/pap_aplicacao";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConexao(){
        Connection con = null;
        
        try {
            Class.forName(DRIVER);
            try{
                con = DriverManager.getConnection(URL, USER, PASS);
                //System.out.println("Conectado com sucesso");
            
            } catch(SQLException ex){
                //CASO TENHA ALGUM PROBLEMA NA CONEXAO
                //System.out.println("Erro ao conectar! " + ex);
            }
        } catch (ClassNotFoundException ex){
            //CASO O DRIVER ESTEJA ERRADO
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return con;
    }
    
    public static void closeConnection(Connection con) throws SQLException{
       if(con != null){
           con.close();
       }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) throws SQLException{
        closeConnection(con);
        if(stmt != null){
           stmt.close();
       }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) throws SQLException{
        closeConnection(con, stmt);
        if(rs != null){
           rs.close();
       }
    }
    
    public static void closeConnection(ResultSet rs) throws SQLException{
        if(rs != null){
            rs.close();
        }
    }
}
