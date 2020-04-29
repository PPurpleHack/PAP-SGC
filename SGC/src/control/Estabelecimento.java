package control;

import connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private ArrayList<String> telefone;
    
    public Estabelecimento(){
        this.telefone = new ArrayList();
    }
    
    public Estabelecimento(int id) throws SQLException{
        //Carrega um estabelecimento
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        this.telefone = new ArrayList();
        
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
            this.carregaTelefones(con);
        }
        Conexao.closeConnection(con, stmt, rs);
    }
    
    private void carregaTelefones(Connection con) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = con.prepareStatement("SELECT CONCAT(\"0\", numero) telefone FROM estabelecimento_telefone " +
                                    "WHERE estabelecimento = " + this.id + ";");
        rs = stmt.executeQuery();
        while(rs.next()){
            this.telefone.add(rs.getString("telefone"));
        }
        
        Conexao.closeConnection(con, stmt, rs);
    }
    
    public boolean cadastrarEstabalecimento() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null; 
        ResultSet rs = null;
        Integer rodou = null;
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
            
            if(this.telefone.size() != 0) cadastraTelefone(con);
            Conexao.closeConnection(con, stmt, rs);
            return true;
        }
        return false;
    }
    
    public void cadastraTelefone(Connection con) throws SQLException{
        String query = null;
        PreparedStatement stmt = null;
        boolean primeiro = true;
        
        System.out.println("Entrou cadastra telefone");
        
        query = "INSERT INTO estabelecimento_telefone(estabelecimento, numero) ";
        for(int x = 0; x < this.telefone.size()-1; x++){
            if(primeiro) query = query + "VALUES";
            primeiro = false;
            query = query + "("+this.id + ", '" + this.telefone.get(x) + "'), ";
                
        }
        if(primeiro) query = query + "VALUES(" + this.id + ", '" + this.telefone.get(this.telefone.size()-1) + "');";
        else query = query + "(" + this.id + ", '" + this.telefone.get(this.telefone.size()-1) + "');";
        
        stmt = con.prepareStatement(query);
        stmt.executeUpdate();
        Conexao.closeConnection(con, stmt);
    }
    
    public boolean atualizarEstabelecimento() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rodou = null;
        String query = null;
        
        query = "UPDATE estabelecimento "
              + "SET    cnpj = '"+this.cnpj+"', "
              + "       nome = '"+this.nome+"', "
              + "       cep = '"+this.cep+"', "
              + "       numero = "+this.numero+", "
              + "       cidade = '"+this.cidade+"', "
              + "       bairro = '"+this.bairro+"', "
              + "       estado = '"+this.estado+"', "
              + "       pais = '"+this.pais+"' "
              + "WHERE  idEstabelecimento = "+this.id;
        stmt = con.prepareStatement(query);
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            //Atualizar telefones
            this.excluirTelefone(con, true);
            this.cadastraTelefone(con);
            return true;
        } 
        return false;
    }
    
    public int excluirEstabelecimento(){
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        Integer rodou = null;
        String query = null;
        
        try {
            //Antes precisa excluir todos os telefones desse estabelecimento
            this.excluirTelefone(con, true);
        } catch (SQLException ex) {
            Logger.getLogger(Estabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "DELETE FROM estabelecimento WHERE idEstabelecimento = "+ this.id +";";
        try {
            stmt = con.prepareStatement(query);
            try {
                rodou = stmt.executeUpdate();
                if(rodou == 1)/*Conseguiu excluir com sucesso*/return 1;
            } catch (SQLException ex) {
                System.out.println("Erro SQL: "+ex.getErrorCode());
                System.out.println(ex);
                return ex.getErrorCode();
                //Logger.getLogger(Estabelecimento.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            return ex.getErrorCode();
            //Logger.getLogger(Estabelecimento.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Ocorreu um erro enexperado
        return -1;
    }
    
    public void excluirTelefone(Connection con, String telefone) throws SQLException{
        PreparedStatement stmt = null;
        String query;
        
        query = "DELETE FROM estabelecimento_telefone " +
                "WHERE estabelecimento = "+this.id+" " +
                "AND	numero = '"+telefone+"'";
        stmt = con.prepareStatement(query);
        stmt.executeUpdate();
    }
    
    public void excluirTelefone(String telefone) throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        String query;
        
        query = "DELETE FROM estabelecimento_telefone " +
                "WHERE estabelecimento = "+this.id+" " +
                "AND	numero = '"+telefone+"'";
        stmt = con.prepareStatement(query);
        stmt.executeUpdate();
        Conexao.closeConnection(con, stmt);
    }
    
    public void excluirTelefone(Connection con, boolean todos) throws SQLException{
        //Função para excluir todos os telefones de uma estabelecimento
        PreparedStatement stmt = null;
        String query;
        
        query = "DELETE FROM estabelecimento_telefone " +
                "WHERE estabelecimento = "+this.id+";";
        stmt = con.prepareStatement(query);
        stmt.executeUpdate();
    }
    
    public ArrayList<Map> listaEstabelecimentos(Map<String, String> filtros) throws SQLException{
        ArrayList<Map> estabelecimentos = new ArrayList();
        Map<String, String> linha;
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        query = "SELECT * FROM estabelecimento "
                + "WHERE 1 = 1 ";
        
        //FILTROS
        if(filtros.containsKey("cnpj")) query = query + "AND cnpj = '"+ filtros.get("cnpj") +"' ";
        if(filtros.containsKey("cep")) query = query + "AND cep = '"+ filtros.get("cep") +"' ";
        if(filtros.containsKey("estado")) query = query + "AND estado = '"+ filtros.get("estado") +"' ";
        
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            linha = new HashMap<String, String>();
            linha.put("id", Integer.toString(rs.getInt("idEstabelecimento")));
            linha.put("cnpj", rs.getString("cnpj"));
            linha.put("nome", rs.getString("nome"));
            linha.put("cep", rs.getString("cep"));
            linha.put("numero", Integer.toString(rs.getInt("numero")));
            linha.put("cidade", rs.getString("cidade"));
            linha.put("bairro", rs.getString("bairro"));
            linha.put("estado", rs.getString("estado"));
            linha.put("pais", rs.getString("pais"));
            estabelecimentos.add(linha);
        }
        return estabelecimentos;
    }
//########################################################################################################################################################    
//########################################################################################################################################################
//########################################################################################################################################################    
    @Override
    public String toString() {
        return "Estabelecimento{" + "id=" + id + ", cnpj=" + cnpj + ", nome=" + nome + ", cep=" + cep + ", numero=" + numero + ", cidade=" + cidade + ", bairro=" + bairro + ", estado=" + estado + ", pais=" + pais + ", telefone=" + telefone + '}';
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

    public ArrayList<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(ArrayList<String> telefone) {
        this.telefone = telefone;
    }
}
