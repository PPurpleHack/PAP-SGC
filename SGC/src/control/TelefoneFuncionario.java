package control;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TelefoneFuncionario extends Control{
    
    private int id;
    private int func;
    private String ddd;
    private String numero;
    private boolean celular;

    public TelefoneFuncionario(){
        this.setClass();
    }
    
    //Inicialização da classe para o cadastro de telefone
    public TelefoneFuncionario(String ddd, String numero, Boolean celular){
        this.setClass();
        
        this.id = -1;
        this.numero = numero;
        this.ddd = ddd;
        this.celular = celular;
    }
    
    private void setClass(){
        this.setClassName("funcionario_telefone");
        this.setPrimaryKey("idFuncTelefone");
    }
    
    public void cadastrarTelefone()throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        
        String query = "insert into funcionario_telefone(funcionario, ddd, numero, celular) values(?, ?, ?, ?)";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, func);
        stmt.setString(2, ddd);
        stmt.setString(3, numero);
        stmt.setBoolean(4, celular);
        if(stmt.executeUpdate() == 1){
            rs = stmt.getGeneratedKeys();
            if(rs.next()) this.id = rs.getInt(1);
        }
        Conexao.closeConnection(con, stmt, rs);
    }
    
    public int deleteAll(ArrayList<Integer> ids){//Delete all the phones of one branch
        this.setPrimaryKey("funcionario");//Troca o campo identificador
        Integer rs = this.delete(ids);
        this.setPrimaryKey("idFuncTelefone");//Volta para a primary key
        return rs;
    }
    
    public void update()throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        
        String query = "update funcionario_telefone "
                + "set funcionario = ?, "
                + "ddd = ?, "
                + "numero = ?, "
                + "celular = ? "
                + "where idFuncTelefone = ?";
        stmt = con.prepareStatement(query);
        stmt.setInt(1, func);
        stmt.setString(2, ddd);
        stmt.setString(3, numero);
        stmt.setBoolean(4, celular);
        stmt.setInt(5, id);
        
        stmt.executeUpdate();
        Conexao.closeConnection(con, stmt);
    }
    
//########################################################################################################################################################    
//########################################################################################################################################################
//########################################################################################################################################################        
    
    @Override
    public String toString() {
        return "TelefoneFuncionario{" + "id=" + id + ", funcionario=" + func + ", ddd=" + ddd + ", numero=" + numero + ", celular=" + celular + '}';
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFunc() {
        return func;
    }

    public void setFunc(int func) {
        this.func = func;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isCelular() {
        return celular;
    }

    public void setCelular(boolean celular) {
        this.celular = celular;
    }
}
