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
public class Estabelecimento extends Control{
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
    private ArrayList<TelefoneEstabelecimento> telefone;
    
    public Estabelecimento(){
        this.telefone = new ArrayList();
        this.setClassName("estabelecimento");
        this.setPrimaryKey("idEstabelecimento");
        
        
    }
                
    public Estabelecimento(int id) throws SQLException{
        //Padrão
        this.telefone = new ArrayList();
        this.setClassName("estabelecimento");
        this.setPrimaryKey("idEstabelecimento");
        
        //Carrega um estabelecimento
        ResultSet rs = this.load(id);
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
        this.carregaTelefones();
        Conexao.closeConnection(rs);
    }
    
    private void carregaTelefones() throws SQLException{
        //Instancia o objeto
        TelefoneEstabelecimento tel = new TelefoneEstabelecimento();
        
        //Troca o campo identificador
        tel.setPrimaryKey("estabelecimento");
        ResultSet rs = tel.load(this.id);
        while(rs.next()){
            tel = new TelefoneEstabelecimento();
            tel.setId(rs.getInt("idEstabTelefone"));
            tel.setEstab(rs.getInt("estabelecimento"));
            tel.setDdd(rs.getString("ddd"));
            tel.setNumero(rs.getString("numero"));
            tel.setCelular(rs.getBoolean("celular"));
            this.telefone.add(tel);
        }
        
        //Fecha ResultSet
        Conexao.closeConnection(rs);
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
        if(stmt.executeUpdate() == 1){
            //Pega a primary key gerada
            rs = stmt.getGeneratedKeys();
            if(rs.next())this.id = rs.getInt(1);
            
            //Fecha a conexão
            Conexao.closeConnection(con, stmt, rs);
            for(int x = 0; x < this.telefone.size(); x++){
                //Preenche o id do estabelecimento que foi gerado durante o cadastro do estabelecimento
                this.telefone.get(x).setEstab(this.id);
                this.telefone.get(x).cadastrarTelefone();
            }
            return true;
        }
        return false;
    }
    
    public boolean atualizarEstabelecimento() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        String query;
        
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
        if(stmt.executeUpdate() == 1){
           for(TelefoneEstabelecimento tel: telefone){
               if(tel.getId() == -1){
                   tel.setEstab(id);
                   tel.cadastrarTelefone();
               } else if(tel.getEstab() == 0){
                   ArrayList<Integer> id = new ArrayList();
                   id.add(tel.getId());
                   System.out.println("Telefone a ser excluido: "+id);
                   tel.delete(id);
               } else {
                   tel.updateTelefone();
               }
           }
           return true;
        } 
        return false;
    }
    
    public int excluirEstabelecimento(ArrayList<Integer> ids){
        TelefoneEstabelecimento tel = new TelefoneEstabelecimento();
        tel.deleteAll(ids);
        return this.delete(ids);
    }
    
    //METODOS DE LISTA
    public ArrayList<ArrayList> listaEstabComboBox()throws SQLException{
        ArrayList<ArrayList> listaEstab = new ArrayList();
        ArrayList<String> line;
        
        String query = "select idEstabelecimento \"index\", nome from estabelecimento";
        ResultSet rs = this.run(query);
        
        if(rs != null){
            while(rs.next()){
                line = new ArrayList();
                line.add(Integer.toString(rs.getInt("index")));
                line.add(rs.getString("nome"));
                listaEstab.add(line);
            }
        }
        
        return listaEstab;
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

    public ArrayList<TelefoneEstabelecimento> getTelefone() {
        return telefone;
    }

    public void setTelefone(ArrayList<TelefoneEstabelecimento> telefone) {
        this.telefone = telefone;
    }
    
}
