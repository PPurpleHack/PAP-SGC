package control;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nicolas
 */
public class Setor {
    //Atributos
    private int id;
    private String nome;
    private int estabelecimento;
    
    public Setor(){}
    
    public Setor(int id) throws SQLException{
        //Carrega um setor
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = con.prepareStatement("SELECT     * " +
                                    "FROM       setor " +
                                    "WHERE      idSetor = " + id + ";");
        rs = stmt.executeQuery();
        while(rs.next()){
            this.id = rs.getInt("idSetor");
            this.nome = rs.getString("nome");
            this.estabelecimento = rs.getInt("estabelecimento");
        }
        Conexao.closeConnection(con, stmt, rs);
    }
    
    public boolean cadastrarSetor() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rodou = null;
        String query = null;
        
        query = "INSERT INTO setor(nome, estabelecimento)" +
                "VALUES(?, ?)";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.nome);
        stmt.setInt(2, this.estabelecimento);
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            rs = stmt.getGeneratedKeys();
            while(rs.next()){
                this.id = rs.getInt(1);
                return true;
            }
        }
        return false;
    }
    
    public boolean atualizarSetor() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rodou = null;
        String query = null;
        
        query = "UPDATE	setor " +
                "SET 	nome = '"+this.nome+"', " +
                "	estabelecimento = " + this.estabelecimento +" "+
                "WHERE	idSetor = "+this.id+";";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            return true;
        }
        return false;
    }
    
    public boolean excluirSetor() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rodou = null;
        String query = null;
        
        query = "DELETE FROM setor "
              + "WHERE idSetor = "+this.id;
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            return true;
        }
        return false;
    }
    
    public ArrayList<Map> lista(Map<String, String> filtros) throws SQLException{
        ArrayList<Map> estabelecimentos = new ArrayList();
        Map<String, String> linha;
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        query = "SELECT * FROM setor "
                + "WHERE 1 = 1 ";
        
        //FILTROS
        if(filtros.containsKey("estabelecimento")) query = query + "AND estabelecimento = '"+ filtros.get("estabelecimento") +"' ";
        if(filtros.containsKey("nome")) query = query + "AND nome = '"+ filtros.get("nome") +"' ";
        
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            linha = new HashMap<String, String>();
            linha.put("id", Integer.toString(rs.getInt("idSetor")));
            linha.put("nome", rs.getString("nome"));
            linha.put("cnpj", Integer.toString(rs.getInt("estabelecimento")));
            estabelecimentos.add(linha);
        }
        Conexao.closeConnection(con, stmt, rs);
        return estabelecimentos;
    }

    @Override
    public String toString() {
        return "Setor{" + "id=" + id + ", nome=" + nome + ", estabelecimento=" + estabelecimento + '}';
    }
    
    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(int estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
}
