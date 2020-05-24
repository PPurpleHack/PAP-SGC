package control;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Map;
import java.util.HashMap;

public class Funcao extends Control{
    
    private int idFuncao;
    private String descricao;
    
    public Funcao(){
        this.setClass();
    }
    
    //Carrega uma função
    public Funcao(int idFuncao)throws SQLException{
        //Padrão
        this.setClass();
        
        //Carrega uma funcao
        ResultSet rs = this.load(idFuncao);
        while(rs.next()){
            this.idFuncao = rs.getInt("idFuncao");
            this.descricao = rs.getString("descricao");
        }
        Conexao.closeConnection(rs);
    }
    
    private void setClass(){
        this.setClassName("funcao");
        this.setPrimaryKey("idFuncao");
    }
    
    public boolean save() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs;
        String query;
        
        query = "INSERT INTO funcao(descricao) " +
                "VALUES(?);";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.descricao);
        
        if(stmt.executeUpdate() == 1){
            //Pega o id gerado
            rs = stmt.getGeneratedKeys();
            if(rs.next()) this.idFuncao = rs.getInt(1);
            return true;
        }
        return false;
    }
    
    public boolean update() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs;
        String query;
        
        query = "UPDATE funcao "
                + "set descricao = '"+this.descricao+"' "
                + "where idFuncao = "+this.idFuncao;
        stmt = con.prepareStatement(query);
        if(stmt.executeUpdate() > 0) return true; 
        return false;
    }
    
    public int excluir(ArrayList<Integer> ids){
        return this.delete(ids);
    }
    
    public ArrayList<ArrayList> listaFuncaoComboBox()throws SQLException{
        ArrayList<ArrayList> lista = new ArrayList();
        ArrayList<String> line;
        
        String query = "select * from funcao";
        ResultSet rs = this.run(query);
        if(rs != null){
            while(rs.next()){
                line = new ArrayList();
                line.add(Integer.toString(rs.getInt("idFuncao")));
                line.add(rs.getString("descricao"));
                lista.add(line);
            }
        }
        
        Conexao.closeConnection(rs);
        return lista;
    }
    
    public ArrayList<Map> lista(Map<String, String> filtros)throws SQLException{
        ArrayList<Map> lista = new ArrayList();
        Map<String, String> line;
        
        String query = "select * from funcao";
        ResultSet rs = this.run(query);
        if(rs != null){
            while(rs.next()){
                line = new HashMap<String,String>();
                line.put("id", Integer.toString(rs.getInt("idFuncao")));
                line.put("funcao", rs.getString("descricao"));
                lista.add(line);
            }
        }
        Conexao.closeConnection(rs);
        return lista;
    }
//########################################################################################################################################################    
//########################################################################################################################################################
//########################################################################################################################################################        
    @Override
    public String toString() {
        return "Funcao{" + "idFuncao=" + idFuncao + ", descricao=" + descricao + '}';
    }
    
    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
