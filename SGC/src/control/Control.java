package control;

import connection.Conexao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Control {
    private static final String SALT = "DD58eS$@ss";
    private static final String SENHAPADRAO = "123456";
    
    private String className = "";
    private String primaryKey = "";

    public static String getSALT() {
        return SALT;
    }

    public static String getSENHAPADRAO() {
        return SENHAPADRAO;
    }
    
    public ResultSet load(int id) throws SQLException{
        if(!this.checkClassName())return null;
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        String query;
        
        stmt = con.prepareStatement("select * from `"+this.className+"` where `"+this.primaryKey+"` = "+id);
        rs = stmt.executeQuery();
        Conexao.closeConnection(con, stmt);
        return rs;
    }
    
    public int delete(ArrayList<Integer> list){
        if(!this.checkClassName())return -10;
        
        //BD
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        
        String query;
        query = "delete from `"+this.className+"` where `"+this.primaryKey+"` in("+list+")";
        query = query.replace("[", "").replace("]", "");
        
        try{
            stmt = con.prepareStatement(query);
            stmt.executeUpdate();
            
            Conexao.closeConnection(con, stmt);
            return 1;
        }catch(SQLException ex){
            System.out.println(ex);
            return ex.getErrorCode();
        }
    }
    
    public ResultSet run(String query){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        
        try{
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();
            Conexao.closeConnection(con, stmt);
        } catch(Exception ex){
            System.out.println("Erro SQL: "+ex);
        }
        
        return rs;
    }
    
    private boolean checkClassName(){
        return !"".equals(this.className); //Error, className not declared
    }
    
    public String capitalize(String str){
        if(str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    //Getter and Setter
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getPrimaryKey(){
        return primaryKey;
    }
    
    public void setPrimaryKey(String primaryKey){
        this.primaryKey = primaryKey;
    }
}
