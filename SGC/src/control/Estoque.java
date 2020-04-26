package control;

import java.sql.Connection;
import java.sql.SQLException;
import connection.Conexao;
import java.util.ArrayList;
import java.util.Map;

public class Estoque {
    
    private int id;
    private String codProduto;
    private int estabelecimento;
    private String nome;
    private int qtdProduto;
    private Double precoVarejo;
    private Double descontoAtacado;
    private int qtdAtacado;
    private int setor;
    
    public Estoque(){
        
    }
    
    public Estoque(int id)throws SQLException{
        Connection con = Conexao.getConexao();
        //Carrega um item do estoque
    }
    
    public ArrayList<Map> listaEstoque(Map<String, String> filtros)throws SQLException{
        ArrayList<Map> lista = new ArrayList();
        
        
        return lista;
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
