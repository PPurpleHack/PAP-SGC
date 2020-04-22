package control;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author Nicolas
 */
public class Estabelecimento {
   //Atributos
    private int id;
    private String cnpj;
    private String nome;
    private String cep;
    private int numero;
    private String cidade;
    private String bairro;
    private String estado;
    private String pais;
    
    public Estabelecimento(){}
    
    public Estabelecimento(int id) throws SQLException{
        //Carrega um estabelecimento
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = con.prepareStatement("SELECT     * " +
                                    "FROM       estabelecimento " +
                                    "WHERE      idEstabelecimento = " + id + ";");
        rs = stmt.executeQuery();
        while(rs.next()){
            this.id = rs.getInt("idEstabelecimento");
            this.cnpj = rs.getString("cnpj");
            this.nome = rs.getString("nome");
            this.cep = rs.getString("cep");
            this.numero = rs.getInt("numero");
            this.cidade = rs.getString("cidade");
            this.bairro = rs.getString("bairro");
            this.estado = rs.getString("estado");
            this.pais = rs.getString("pais");
        }
        Conexao.closeConnection(con, stmt, rs);
    }
    
    public boolean cadastrarEstabalecimento() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null; 
        ResultSet rs = null;
        Integer rodou;
        String query = null;
        
        query = "INSERT INTO estabelecimento(cnpj, nome, cep, numero, cidade, bairro, estado, pais) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.cnpj);
        stmt.setString(2, this.nome);
        stmt.setString(3, this.cep);
        stmt.setInt(4, this.numero);
        stmt.setString(5, this.cidade);
        stmt.setString(6, this.bairro);
        stmt.setString(7, this.estado);
        stmt.setString(8, this.pais);
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            rs = stmt.getGeneratedKeys();
            while(rs.next()){
                this.id = rs.getInt(1);
            }
            Conexao.closeConnection(con, stmt, rs);
            return true;
        }
        return false;
    }
    
    public boolean atualizarEstabelecimento() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rodou = null;
        String query = null;
        
        query = "UPDATE estabelecimento"
              + "SET    cnpj = '"+this.cnpj+"', "
              + "       nome = '"+this.nome+"', "
              + "       cep = '"+this.cep+"', "
              + "       numero = "+this.numero+", "
              + "       cidade = '"+this.cidade+"', "
              + "       bairro = '"+this.bairro+"', "
              + "       estado = '"+this.estado+"', "
              + "       pais = '"+this.pais+"' "
              + "WHERE  idEstabelecimento = "+this.id;
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            return true;
        }
        return false;
    }
    
    public boolean exluirEstabelecimento() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rodou = null;
        String query = null;
        
        query = "";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Estabelecimento{" + "id=" + id + ", cnpj=" + cnpj + ", nome=" + nome + ", cep=" + cep + ", numero=" + numero + ", cidade=" + cidade + ", bairro=" + bairro + ", estado=" + estado + ", pais=" + pais + '}';
    }
    
    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    
    
}
