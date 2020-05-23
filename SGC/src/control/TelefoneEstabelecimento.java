package control;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TelefoneEstabelecimento extends Control{
    
    private int id;
    private int estab;
    private String ddd;
    private String numero;
    private boolean celular;
    
    public TelefoneEstabelecimento(){
        this.setClass();
    }
    
    //Inicialização da classe para o cadastro de telefone
    public TelefoneEstabelecimento(String ddd, String numero, Boolean celular){
        this.setClass();
        
        this.id = -1;
        this.numero = numero;
        this.ddd = ddd;
        this.celular = celular;
    }
    
    private void setClass(){
        this.setClassName("estabelecimento_telefone");
        this.setPrimaryKey("idEstabTelefone");
    }
    
    public void cadastrarTelefone()throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        
        String query = "insert into estabelecimento_telefone(estabelecimento, ddd, numero, celular) values(?, ?, ?, ?)";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, estab);
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
        this.setPrimaryKey("estabelecimento");//Troca o campo identificador
        Integer rs = this.delete(ids);
        this.setPrimaryKey("idEstabTelefone");//Volta para a primary key
        return rs;
    }
    
    public void updateTelefone()throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        
        String query = "update estabelecimento_telefone "
                + "set estabelecimento = ?, "
                + "ddd = ?, "
                + "numero = ?, "
                + "celular = ? "
                + "where idEstabTelefone = ?";
        stmt = con.prepareStatement(query);
        stmt.setInt(1, estab);
        stmt.setString(2, ddd);
        stmt.setString(3, numero);
        stmt.setBoolean(4, celular);
        stmt.setInt(5, id);
        
        stmt.executeUpdate();
        
        Conexao.closeConnection(con, stmt);
    }
    
    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstab() {
        return estab;
    }

    public void setEstab(int estab) {
        this.estab = estab;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public boolean isCelular() {
        return celular;
    }

    public void setCelular(boolean celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "TelefoneEstabelecimento{" + "id=" + id + ", estab=" + estab + ", ddd=" + ddd + ", numero=" + numero + ", celular=" + celular + '}';
    }
}
