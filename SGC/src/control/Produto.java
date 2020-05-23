package control;

import java.sql.Connection;
import java.sql.SQLException;
import connection.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Produto {
    
    private int id;
    private String codProduto;
    private int estabelecimento;
    private String nome;
    private int qtdProduto;
    private Double precoVarejo;
    private Double descontoAtacado;
    private int qtdAtacado;
    private int setor;
    
    public Produto(){
        
    }
    
    public Produto(int id)throws SQLException{
        //Carrega um funcionario
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = con.prepareStatement("SELECT 	* " +
                                    "FROM 	estoque " +
                                    "WHERE      matricula = " + id + ";");
        rs = stmt.executeQuery();
        while(rs.next()){
            this.id = rs.getInt("id");
            this.codProduto = rs.getString("codProduto");
            this.estabelecimento = rs.getInt("estbalecimento");
            this.nome = rs.getString("nome");
            this.qtdProduto = rs.getInt("qtdProduto");
            this.precoVarejo = rs.getDouble("precoVarejo");
            this.descontoAtacado = rs.getDouble("descontoAtacado");
            this.qtdAtacado = rs.getInt("qtdAtacado");
            this.setor = rs.getInt("setor");
        }
        Conexao.closeConnection(con, stmt, rs);
    }
    
    public ArrayList<Map> listaEstoque(Map<String, String> filtros)throws SQLException{
        ArrayList<Map> estoque = new ArrayList();
        Map<String, String> linha;
        Connection con = Conexao.getConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = null;
        
        query = "SELECT * FROM estoque "
                + "WHERE 1 = 1 ";
        
        //FILTROS
        if(filtros.containsKey("codProduto")) query = query + "AND codProduto = '"+ filtros.get("codProduto") +"' ";
        if(filtros.containsKey("nome")) query = query + "AND nome = '"+ filtros.get("nome") +"' ";
        if(filtros.containsKey("estabelecimento")) query = query + "AND estabelecimento = '"+ filtros.get("estabelecimento") +"' ";
        
        stmt = con.prepareStatement(query);
        rs = stmt.executeQuery();
        while(rs.next()){
            linha = new HashMap<String, String>();
            linha.put("id", Integer.toString(rs.getInt("idEstabelecimento")));
            estoque.add(linha);
        }
        return estoque;
    }
    
    public int inserirItem(){//Retorna um numero inteiro pra fazer o controle de erros no sql
        
        return 1;//Apagar dpois
    }
    
    public int excluirItem(){//Retorna um numero inteiro pra fazer o controle de erros no sql
        
        return 1;
    }
    
    public int editarItem(){//Retorna um numero inteiro pra fazer o controle de erros no sql
        
        return 1;
    }
    
    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public int getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(int estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Double getPrecoVarejo() {
        return precoVarejo;
    }

    public void setPrecoVarejo(Double precoVarejo) {
        this.precoVarejo = precoVarejo;
    }

    public Double getDescontoAtacado() {
        return descontoAtacado;
    }

    public void setDescontoAtacado(Double descontoAtacado) {
        this.descontoAtacado = descontoAtacado;
    }

    public int getQtdAtacado() {
        return qtdAtacado;
    }

    public void setQtdAtacado(int qtdAtacado) {
        this.qtdAtacado = qtdAtacado;
    }

    public int getSetor() {
        return setor;
    }

    public void setSetor(int setor) {
        this.setor = setor;
    }
    
}
