package control;

import view.CadProduto;
import java.sql.Connection;
import java.sql.SQLException;
import connection.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Produto extends Control{
    
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
        this.setClassName("estoque");
        this.setPrimaryKey("idEstoque");
    }
    
    public Produto(int id)throws SQLException{
        
        this.setClassName("estoque");
        this.setPrimaryKey("idEstoque");

        ResultSet rs = null;
        
        rs = this.load(id);
        while(rs.next()){
            this.id = rs.getInt("idEstoque");
            this.codProduto = rs.getString("codProduto");
            this.estabelecimento = rs.getInt("estabelecimento");
            this.nome = rs.getString("nome");
            this.qtdProduto = rs.getInt("qtdProduto");
            this.precoVarejo = rs.getDouble("precoVarejo");
            this.descontoAtacado = rs.getDouble("descontoAtacado");
            this.qtdAtacado = rs.getInt("qtdAtacado");
            this.setor = rs.getInt("setor");
        }
        Conexao.closeConnection(rs);
    }
    
    public void save()throws SQLException{//INSERE UM NOVO PRODUTO
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        String query;
        
        query = "insert into estoque(codProduto, estabelecimento, nome, qtdProduto, precoVarejo, descontoAtacado, qtdAtacado, setor, situacao) " 
               +"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.codProduto);
        stmt.setInt(2, this.estabelecimento);
        stmt.setString(3, this.nome);
        stmt.setInt(4, this.qtdProduto);
        stmt.setDouble(5, this.precoVarejo);
        stmt.setDouble(6, this.descontoAtacado);
        stmt.setInt(7, this.qtdAtacado);
        stmt.setInt(8, this.setor);
        stmt.setString(9, "ATIVO");
        stmt.execute();
        rs = stmt.getGeneratedKeys();
        if(rs.next()) this.id = rs.getInt(1);
    }
    
    public boolean update()throws SQLException{//Retorna um numero inteiro pra fazer o controle de erros no sql
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        String query;
        
        query = "update estoque "
              + "set    codProduto = '"+this.codProduto+"', "
              + "       estabelecimento = "+this.estabelecimento+","
                + "     nome = '"+this.nome+"',"
                + "     qtdProduto = '"+this.qtdProduto+"',"
                + "     precoVarejo = '"+this.precoVarejo+"',"
                + "     descontoAtacado = '"+this.descontoAtacado+"',"
                + "     qtdAtacado = '"+this.qtdAtacado+"',"
                + "     setor = "+this.setor+" "
              + "where  idEstoque = "+this.id;
        stmt = con.prepareStatement(query);
        if(stmt.executeUpdate() == 1){
            Conexao.closeConnection(con, stmt);
            return true;
        }
        Conexao.closeConnection(con, stmt);
        return false;
    }

    public ArrayList<Integer> deleteProduto(ArrayList<Integer> ids){
        ArrayList<Integer> cont = new ArrayList();
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        String query;
        for(int id: ids){
            try{
                query = "update estoque set situacao = 'EXCLUIDO' where idEstoque = "+id+" and qtdProduto = 0";
                stmt = con.prepareStatement(query);
                if(stmt.executeUpdate() > 0){
                    cont.add(Integer.valueOf(id));
                }
            }catch(SQLException ex){}
        }
        return cont;
    }
    
    public void updateQuantidade(){
        ResultSet rs;
        String query;
        query = "UPDATE estoque " +
                "SET	qtdProduto = "+this.qtdProduto+" "+
                "WHERE 	idEstoque = "+this.id;
        this.run(query);
    }
    
    public ArrayList<Map> listaEstoque(Map<String, String> filtros)throws SQLException{
        ArrayList<Map> estoque = new ArrayList();
        Map<String, String> linha;
        ResultSet rs = null;
        String query = null;
        
        query = "SELECT *, "
                + "ROUND(precoVarejo - (precoVarejo * (descontoAtacado/100)), 2) \"precoComDesconto\" FROM estoque "
                + "WHERE 1 = 1 AND ifnull(situacao, 'ATIVO') <> 'EXCLUIDO' ";
        //System.out.println(query);
        //FILTROS
        if(filtros.containsKey("codProduto")) query = query + "AND codProduto = '"+ filtros.get("codProduto") +"' ";
        if(filtros.containsKey("nome")) query = query + "AND nome = '"+ filtros.get("nome") +"' ";
        if(filtros.containsKey("estabelecimento")) query = query + "AND estabelecimento = '"+ filtros.get("estabelecimento") +"' ";
        
        rs = this.run(query);
        while(rs.next()){
            linha = new HashMap<String, String>();
            linha.put("id", Integer.toString(rs.getInt("idEstoque")));
            linha.put("codigo", rs.getString("codProduto"));
            linha.put("estabelecimento", Integer.toString(rs.getInt("estabelecimento")));
            linha.put("nome", rs.getString("nome"));
            linha.put("quantidade", Integer.toString(rs.getInt("qtdProduto")));
            linha.put("preco", Double.toString(rs.getDouble("precoVarejo")));
            linha.put("precoComDesconto", Double.toString(rs.getDouble("precoComDesconto")));
            linha.put("QuantidadePraDesconto", Integer.toString(rs.getInt("qtdAtacado")));
            linha.put("setor", Integer.toString(rs.getInt("setor")));
            estoque.add(linha);
        }
        return estoque;
    }
    
    public int checkProduto(CadProduto cadProduto)throws SQLException{
        ResultSet rs = null;
        String query;
        int retorno = 0;
        
        query = "SELECT idEstoque, COUNT(idEstoque)\"tem\", ifnull(situacao, 'ATIVO')\"situacao\" FROM estoque "
              + "WHERE codProduto = '"+this.codProduto+"' AND estabelecimento = "+this.estabelecimento+";";
        
        rs = this.run(query);
        while(rs.next()){
            if(rs.getInt("tem") == 1){
                retorno = rs.getInt("idEstoque");
                if(rs.getString("situacao").equals("EXCLUIDO")){
                    query = "update estoque set situacao = 'ATIVO' where idEstoque = "+retorno;
                    this.run(query);
                    cadProduto.setStatusProduto("newProduto");
                }
            }
        }
        Conexao.closeConnection(rs);
        return retorno;
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

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", codProduto=" + codProduto + ", estabelecimento=" + estabelecimento + ", nome=" + nome + ", qtdProduto=" + qtdProduto + ", precoVarejo=" + precoVarejo + ", descontoAtacado=" + descontoAtacado + ", qtdAtacado=" + qtdAtacado + ", setor=" + setor + '}';
    }
}
