package control;

import java.util.Date;


public class Lote {
    
    private int id;
    private String codLote;
    private int estoque;
    private Date dtmVencimento;
    private int qtdProduto;
    private String situacao;
    private Double vlrPago;
    
    public Lote(){
        
    }
    
    public Lote(int id){
    
    }
    
    public int atualizaStatus(){//Retorna um valor int para fazer o controle de erros do SQL
        
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

    public Date getDtmVencimento() {
        return dtmVencimento;
    }

    public void setDtmVencimento(Date dtmVencimento) {
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
}
