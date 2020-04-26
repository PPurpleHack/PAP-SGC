package control;

public class Caixa {
    
    private int id;
    private String tipo;
    private String descricao;
    private Double vlrDinheiro;
    
    public Caixa(){

    }

    public Caixa(int id){

    }

    public int registrarCompra(){
        return 1;
    }

    public int registrarVenda(){
        return 1;
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
