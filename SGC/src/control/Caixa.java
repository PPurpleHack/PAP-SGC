package control;

import connection.Conexao;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

public class Caixa extends Control{
    
    private int id;
    private String tipo;
    private String descricao;
    private Double vlrDinheiro;
    
    public Caixa(){
        
    }

    public void registrarCompra(int idLote)throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        String query;
        
        query = "insert into caixa(tipo, descricao, vlrDinheiro, dtmRegistro) "
              + "values('"+tipo+"', '"+descricao+"', "+vlrDinheiro+", now())";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.execute();
        rs = stmt.getGeneratedKeys();
        if(rs.next()) this.id = rs.getInt(1);
        System.out.println(id);
        
        query = "insert into compra(idCaixa, idLote) "
              + "values(?, ?)";
        stmt = con.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.setInt(2, idLote);
        stmt.execute();
    }

    public void registrarVendaCaixa()throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        String query;
        
        query = "insert into caixa(tipo, descricao, vlrDinheiro, dtmRegistro)"
                + " values(?,?,?,now())";
        stmt = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, tipo);
        stmt.setString(2, descricao);
        stmt.setDouble(3, vlrDinheiro);
        stmt.execute();
        rs = stmt.getGeneratedKeys();
        if(rs.next()) this.id = rs.getInt(1);
        Conexao.closeConnection(con, stmt, rs);
    }
    
    public void registraVendaLote(Map i)throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        int idLote = 0;
        
        stmt = con.prepareStatement("select idLote from lote where estoque = "+i.get("id")+" limit 1");
        rs = stmt.executeQuery();
        if(rs.next()) idLote = rs.getInt("idLote");
        Conexao.closeConnection(con, stmt, rs);
        String query = "insert into venda(idCaixa, idLote) values("+id+","+idLote+")";
        this.run(query);
        
        Produto p = new Produto();
        p.setId((Integer)i.get("id"));
        p.setQtdProduto(Integer.parseInt((String)i.get("quantidade")) - (Integer)i.get("qtdSelecionado"));
        p.updateQuantidade();
    }
    
    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getVlrDinheiro() {
        return vlrDinheiro;
    }

    public void setVlrDinheiro(Double vlrDinheiro) {
        this.vlrDinheiro = vlrDinheiro;
    }
}
