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
public class Funcionario extends Control{
    //Atributos
    private int matricula;
    private String nome;
    private String sobrenome;
    private String cpf;
    private int numero;
    private String cep;
    private String cidade;
    private String bairro;
    private String estado;
    private String pais;
    private String email;
    private int funcao;
    private int estabelecimento;
    private boolean logado;
    private ArrayList<TelefoneFuncionario> telefone;
    
    public Funcionario(){
        this.logado = false;
        this.telefone = new ArrayList();
        
        this.setClassName("funcionario");
        this.setPrimaryKey("matricula");
    }
    
    public Funcionario(int matricula) throws SQLException{
        this.telefone = new ArrayList();
        
        this.setClassName("funcionario");
        this.setPrimaryKey("matricula");
        
        ResultSet rs = this.load(matricula);
        while(rs.next()){
            this.matricula = rs.getInt("matricula");
            this.nome = rs.getString("nome");
            this.sobrenome = rs.getString("sobrenome");
            this.cpf = rs.getString("cpf");
            this.numero = rs.getInt("numero");
            this.cep = rs.getString("cep");
            this.cidade = rs.getString("cidade");
            this.bairro = rs.getString("bairro");
            this.estado = rs.getString("estado");
            this.pais = rs.getString("pais");
            this.email = rs.getString("email");
            this.funcao = rs.getInt("funcao");
            this.estabelecimento = rs.getInt("estabelecimento");
            this.logado = false;
        }
        this.carregaTelefones();
        Conexao.closeConnection(rs);
        
    }
    
    private void carregaTelefones() throws SQLException{
        //Instancia o objeto
        TelefoneFuncionario tel = new TelefoneFuncionario();
        
        //Troca o campo identificador
        tel.setPrimaryKey("funcionario");
        ResultSet rs = tel.load(this.matricula);
        while(rs.next()){
            tel = new TelefoneFuncionario();
            tel.setId(rs.getInt("idFuncTelefone"));
            tel.setFunc(rs.getInt("funcionario"));
            tel.setDdd(rs.getString("ddd"));
            tel.setNumero(rs.getString("numero"));
            tel.setCelular(rs.getBoolean("celular"));
            this.telefone.add(tel);
        }
        
        //Fecha ResultSet
        Conexao.closeConnection(rs);
    }
    
    public boolean cadastrarFuncionario() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs;
        String query;
        
        query = "INSERT INTO funcionario(nome, sobrenome, cep, numero, cidade, bairro, estado, pais, email, funcao, estabelecimento, cpf) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.nome);
        stmt.setString(2, this.sobrenome);
        stmt.setString(3, this.cep);
        stmt.setInt(4, this.numero);
        stmt.setString(5, this.cidade);
        stmt.setString(6, this.bairro);
        stmt.setString(7, this.estado);
        stmt.setString(8, this.pais);
        stmt.setString(9, this.email);
        stmt.setInt(10, this.funcao);
        stmt.setInt(11, this.estabelecimento);
        stmt.setString(12, this.cpf);
        
        if(stmt.executeUpdate() == 1){
            //Pega matricula gerada
            rs = stmt.getGeneratedKeys();
            if(rs.next()) this.matricula = rs.getInt(1);
            //Fecha conexão
            Conexao.closeConnection(con, stmt, rs);
            for(int x = 0; x < this.telefone.size(); x++){
                //Preenche o id funcionario que acabou de ser cadastrado para serem cadastrado os telefones
                this.telefone.get(x).setFunc(this.matricula);
                this.telefone.get(x).cadastrarTelefone();
            }
            return true;
        }
        return false;
    }
    
    public boolean atualizar() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        String query;
        
        query = "UPDATE funcionario "
                + "SET  nome = '"+this.nome+"', "
                + "     sobrenome = '"+this.sobrenome+"', "
                + "     cep = '"+this.cep+"', "
                + "     numero = '"+this.numero+"', "
                + "     cidade = '"+this.cidade+"', "
                + "     bairro = '"+this.bairro+"', "
                + "     estado = '"+this.estado+"', "
                + "     pais = '"+this.pais+"', "
                + "     email = '"+this.email+"', "
                + "     funcao = '"+this.funcao+"', "
                + "     estabelecimento = '"+this.estabelecimento+"', "
                + "     cpf = '"+this.cpf+"' " +
                "WHERE  matricula = "+this.matricula;
        stmt = con.prepareStatement(query);
        if(stmt.executeUpdate() == 1){
           for(TelefoneFuncionario tel: telefone){
               if(tel.getId() == -1){
                   tel.setFunc(matricula);
                   tel.cadastrarTelefone();
               } else if(tel.getFunc() == 0){
                   ArrayList<Integer> id = new ArrayList();
                   id.add(tel.getId());
                   System.out.println("Telefone a ser excluido: "+id);
                   tel.delete(id);
               } else {
                   tel.update();
               }
           }
           return true;
        } 
        return false;
    }
    
    public int excluir(ArrayList<Integer> ids){
        TelefoneFuncionario tel = new TelefoneFuncionario();
        tel.deleteAll(ids);
        return this.delete(ids);
    }
    
    public boolean cadastrarConta(String senha) throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rodou = null;
        String query;
        
        query = "UPDATE 	funcionario " +
                "SET		login = matricula, " +
                "		senha = MD5('123') " +
                "WHERE		matricula = ?;";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, this.matricula);
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            return true;
        }
        return false;
    }
    
    public boolean logar(String login, String senha) throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = con.prepareStatement("SELECT 	matricula," +
                                    "		nome" +
                                    "		sobrenome," +
                                    "		cpf," +
                                    "		numero," +
                                    "           cep," +
                                    "		cidade," +
                                    "		bairro," +
                                    "		estado," +
                                    "		pais," +
                                    "		email," +
                                    "		funcao," +
                                    "		estabelecimento " +
                                    "FROM 	funcionario " +
                                    "WHERE      login = " + login + " " +
                                    "AND        senha = md5('" + senha + "')");
        rs = stmt.executeQuery();
        if(rs.next()){
            this.matricula = rs.getInt("matricula");
            this.nome = rs.getNString("nome");
            this.sobrenome = rs.getNString("sobrenome");
            this.cpf = rs.getNString("cpf");
            this.numero = rs.getInt("numero");
            this.cep = rs.getNString("cep");
            this.cidade = rs.getNString("cidade");
            this.bairro = rs.getNString("bairro");
            this.pais = rs.getNString("pais");
            this.email = rs.getNString("email");
            this.funcao = rs.getInt("funcao");
            this.estabelecimento = rs.getInt("estabelecimento");
            this.logado = true;
            return true;
        }
        Conexao.closeConnection(con, stmt, rs);
        return false;
    }
    
    public ArrayList<Map> listaFuncionarios(Map<String, String> filtros) throws SQLException{
        ArrayList<Map> funcionarios = new ArrayList();
        Map<String, String> linha;
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs;
        String query;
        
        query = "SELECT * FROM funcionario "
                + "WHERE 1 = 1 ";
        
        //FILTROS
        if(filtros.containsKey("cnpj")) query = query + "AND cnpj = '"+ filtros.get("cnpj") +"' ";
        if(filtros.containsKey("cep")) query = query + "AND cep = '"+ filtros.get("cep") +"' ";
        if(filtros.containsKey("estado")) query = query + "AND estado = '"+ filtros.get("estado") +"' ";
        
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            linha = new HashMap<String, String>();
            linha.put("matricula", Integer.toString(rs.getInt("matricula")));
            linha.put("nome", rs.getString("nome"));
            linha.put("sobrenome", rs.getString("sobrenome"));
            linha.put("cep", rs.getString("cep"));
            linha.put("numero", Integer.toString(rs.getInt("numero")));
            linha.put("cidade", rs.getString("cidade"));
            linha.put("bairro", rs.getString("bairro"));
            linha.put("estado", rs.getString("estado"));
            linha.put("pais", rs.getString("pais"));
            linha.put("email", rs.getString("email"));
            linha.put("cpf", rs.getString("cpf"));
            funcionarios.add(linha);
        }
        return funcionarios;
    }
    
//########################################################################################################################################################    
//########################################################################################################################################################
//########################################################################################################################################################        

    @Override
    public String toString() {
        return "Funcionario{" + "matricula=" + matricula + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + ", numero=" + numero + ", cep=" + cep + ", cidade=" + cidade + ", bairro=" + bairro + ", estado=" + estado + ", pais=" + pais + ", email=" + email + ", funcao=" + funcao + ", estabelecimento=" + estabelecimento + ", logado=" + logado + ", telefone=" + telefone + '}';
    }

    //Setters e Getters
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public void setCep(String cep){
        this.cep = cep;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFuncao(int funcao) {
        this.funcao = funcao;
    }

    public void setEstabelecimento(int estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
    
    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getNumero() {
        return numero;
    }
    
    public String getCep(){
        return cep;
    }
    
    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getEstado() {
        return estado;
    }

    public String getPais() {
        return pais;
    }

    public String getEmail() {
        return email;
    }

    public int getFuncao() {
        return funcao;
    }

    public int getEstabelecimento() {
        return estabelecimento;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public ArrayList<TelefoneFuncionario> getTelefone() {
        return telefone;
    }

    public void setTelefone(ArrayList<TelefoneFuncionario> telefone) {
        this.telefone = telefone;
    }
}
