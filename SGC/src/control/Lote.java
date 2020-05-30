package control;

import connection.Conexao;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Lote extends Control{
    
    private int id;
    private String codLote;
    private int estoque;
    private String dtmVencimento;
    private int qtdProduto;
    private String situacao;
    private Double vlrPago;
    
    public Lote(){
        this.setClassName("lote");
        this.setPrimaryKey("idLote");
    }
    
    public Lote(int id){
        this.setClassName("lote");
        this.setPrimaryKey("idLote");
    }
    
    public int save()throws SQLException{
        Connection con = Conexao.getConexao();
        PreparedStatement stmt;
        ResultSet rs = null;
        
        String query = "INSERT INTO lote(codLote, estoque, dtmVencimento, qtdProduto, situacao, vlrPago) " +
                        "VALUES(?, ?, STR_TO_DATE('"+this.dtmVencimento.replace("/", ",")+"', '%d,%m,%Y'), ?, ?, ?)";
        stmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, this.codLote);
        stmt.setInt(2, this.estoque);;
        stmt.setInt(3, this.qtdProduto);
        stmt.setString(4, this.situacao);
        stmt.setDouble(5, this.vlrPago);
        while(stmt.executeUpdate() == 1){
            rs = stmt.getGeneratedKeys();
            if(rs.next()) this.id = rs.getInt(1);
            Conexao.closeConnection(con, stmt, rs);
            
            //ATUALIZA QUANTIDADE DE ITENS QUE TEM NO ESTOQUE
            Produto produto = new Produto(this.estoque);
            produto.setQtdProduto(produto.getQtdProduto()+this.qtdProduto);
            produto.updateQuantidade();
            
            //FAZ O REGISTRO DE ENTRADA NO CAIXA
            Caixa caixa = new Caixa();
            caixa.setTipo("OUT");
            caixa.setDescricao("COMPRA");
            caixa.setVlrDinheiro(vlrPago);
            caixa.registrarCompra(id);
            
            return 1;
        }
        Conexao.closeConnection(con, stmt, rs);
        return 0;
    }
    
    public int changeStatus(){//Retorna um valor int para fazer o controle de erros do SQL
        
        return 1;
    }
    
    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodLote() {
        return codLote;
    }

    public void setCodLote(String codLote) {
        this.codLote = codLote;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getDtmVencimento() {
        return dtmVencimento;
    }

    public void setDtmVencimento(String dtmVencimento) {
        this.dtmVencimento = dtmVencimento;
    }

    public int getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(int qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Double getVlrPago() {
        return vlrPago;
    }

    public void setVlrPago(Double vlrPago) {
        this.vlrPago = vlrPago;
    }

    @Override
    public String toString() {
        return "Lote{" + "id=" + id + ", codLote=" + codLote + ", estoque=" + estoque + ", dtmVencimento=" + dtmVencimento + ", qtdProduto=" + qtdProduto + ", situacao=" + situacao + ", vlrPago=" + vlrPago + '}';
    }
}
