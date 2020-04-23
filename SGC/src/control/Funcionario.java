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
public class Funcionario {
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
    
    public Funcionario(){
        this.logado = false;
    }
    
    public Funcionario(int matricula) throws SQLException{
        //Carrega um funcionario
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
                                    "WHERE      matricula = " + matricula + ";");
        rs = stmt.executeQuery();
        while(rs.next()){
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
            this.logado = false;
        }
        Conexao.closeConnection(con, stmt, rs);
    }
    
    public boolean cadastrarFuncionario() throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer rodou = null;
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
        rodou = stmt.executeUpdate();
        if(rodou == 1){
            rs = stmt.getGeneratedKeys();
            while(rs.next()){
                this.matricula = rs.getInt(1);
            }
            Conexao.closeConnection(con, stmt, rs);
            return true;
        }
        return false;
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
    
    public boolean logar(int login, String senha) throws SQLException{
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

    @Override
    public String toString() {
        return "Funcionario{" + "matricula=" + matricula + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + ", numero=" + numero + ", cidade=" + cidade + ", bairro=" + bairro + ", estado=" + estado + ", pais=" + pais + ", email=" + email + ", funcao=" + funcao + ", estabelecimento=" + estabelecimento + ", logado=" + logado + '}';
    }

    //Setters
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
    
    //Getters
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
    
}
