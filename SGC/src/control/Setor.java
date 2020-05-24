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
public class Setor extends Control{
    //Atributos
    private int id;
    private String nome;
    private int estabelecimento;
    
    public Setor(){
        this.id = 0;
        this.setClass();
    }
    
    public Setor(int id) throws SQLException{
        this.setClass();
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
    
    private void setClass(){
        this.setClassName("setor");
        this.setPrimaryKey("idSetor");
    }
    
    public boolean salvar() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        query = "INSERT INTO setor(nome, estabelecimento) " +
                "VALUES(?, ?)";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.nome);
        stmt.setInt(2, this.estabelecimento);
        if(stmt.executeUpdate() == 1){
            rs = stmt.getGeneratedKeys();
            if(rs.next()) this.id = rs.getInt(1);
            Conexao.closeConnection(con, stmt, rs);
            return true;
        }
        return false;
    }
    
    public boolean update() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        query = "UPDATE	setor " +
                "SET 	nome = '"+this.nome+"', " +
                "	estabelecimento = " + this.estabelecimento +" "+
                "WHERE	idSetor = "+this.id+";";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        if(stmt.executeUpdate() == 1){
            Conexao.closeConnection(con, stmt, rs);
            return true;
        }
        return false;
    }
    
    public ArrayList<Map> lista(Map<String, String> filtros) throws SQLException{
        ArrayList<Map> setores = new ArrayList();
        Map<String, String> linha;
        ResultSet rs = null;
        String query = null;
        
        query = "SELECT s.idSetor idSetor,"
                + "     s.nome nome,"
                + "     e.nome estabelecimento"
                + " FROM setor s"
                + " inner join estabelecimento e on s.estabelecimento = e.idEstabelecimento "
                + "WHERE 1 = 1 ";
        
        //FILTROS
        if(filtros.containsKey("estabelecimento")) query = query + "AND estabelecimento = '"+ filtros.get("estabelecimento") +"' ";
        
        rs = this.run(query);
        while(rs.next()){
            linha = new HashMap<String, String>();
            linha.put("id", Integer.toString(rs.getInt("idSetor")));
            linha.put("nome", rs.getString("nome"));
            linha.put("estabelecimento", rs.getString("estabelecimento"));
            setores.add(linha);
        }
        Conexao.closeConnection(rs);
        return setores;
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
    
    public String getEstabelecimento(int id) throws SQLException{
        String estabelecimento = "";
        String query = "select e.nome estabelecimento from setor s "
                + "inner join estabelecimento e on e.idEstabelecimento = s.estabelecimento "
                + "where idSetor = "+id;
        System.out.println(query);
        ResultSet rs = this.run(query);
        if(rs.next()){
            estabelecimento = rs.getString("estabelecimento");
        }
        Conexao.closeConnection(rs);
        return estabelecimento;
    }

    public void setEstabelecimento(int estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
}
