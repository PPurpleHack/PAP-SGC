package view;
import control.Produto;
import control.Estabelecimento;
import model.TableModel;
import model.ComboBox;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Estoque extends javax.swing.JInternalFrame {
    
    private final Main main;
    private final TableModel tModel;
    
    public Estoque(Main main){
        this.main = main;
        
        initComponents();
        
        //****Constroi tabela
        String[] colunas = {"","ID","Código","Nome","Estabalecimento","Setor","Quantidade","Preço","Preço com Desconto","Quantidade para o Desconto"};
        this.tModel = new TableModel(colunas);
        //****Carrega tabela
        this.loadTable();
        //****Seta modelo
        tableEstoque.setModel(this.tModel);
    }
    
    public void loadTable(){
        //Carregamento da tabela
        Produto prod = new Produto();
        Map<String, String> filtros = new HashMap<>();
        //Preencher os filtros
        
        try{
            ArrayList<Map> listaEstab = prod.listaEstoque(filtros);
            System.out.println(listaEstab);
            listaEstab.forEach((Map p) -> {
                Estoque.this.tModel.addRow(new Object[]{
                    Boolean.FALSE,
                    p.get("id"),
                    p.get("codigo"),
                    p.get("nome"),
                    p.get("estabelecimento"),
                    p.get("setor"),
                    p.get("quantidade"),
                    p.get("preco"),
                    p.get("precoComDesconto"),
                    p.get("QuantidadePraDesconto")
                });
            });
        }catch(SQLException ex){
            System.out.println("Erro ao listar tabelas: "+ex);
        }
    }
    
    public void addProdutoInTheTable(Produto p){
        //Atualizar tabela quando for feito o cadastro de um novo produto
        this.tModel.addRow(new Object[]{Boolean.FALSE,
                                        Integer.toString(p.getId()),//Na hora de pegar o id é feito um casting pra String
                                        p.getCodProduto(),
                                        p.getNome(),
                                        Integer.toString(p.getEstabelecimento()),
                                        Integer.toString(p.getSetor()),
                                        Integer.toString(p.getQtdProduto()),
                                        Double.toString(p.getPrecoVarejo()),
                                        Double.toString(p.getPrecoVarejo() - (p.getPrecoVarejo() * (p.getDescontoAtacado()/100))),
                                        Integer.toString(p.getQtdAtacado())});
    }
    
    public void updateTable(Produto p){
        System.out.println("Entrou update table");
        System.out.println(p);
        //Atualizar tabela
        Integer index = this.tModel.getIndexById(p.getId());
        this.tModel.updateRow(index, new Object[]{
            Boolean.FALSE,
            Integer.toString(p.getId()),//Na hora de pegar o id é feito um casting pra String
            p.getCodProduto(),
            p.getNome(),
            Integer.toString(p.getEstabelecimento()),
            Integer.toString(p.getSetor()),
            Integer.toString(p.getQtdProduto()),
            Double.toString(p.getPrecoVarejo()),
            Double.toString(p.getPrecoVarejo() - (p.getPrecoVarejo() * (p.getDescontoAtacado()/100))),
            Integer.toString(p.getQtdAtacado())
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEstoque = new javax.swing.JTable();
        bExcluir = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        bPesquisar = new javax.swing.JLabel();
        bEntradaEstoque = new javax.swing.JButton();
        bSaida = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setBorder(null);
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tableEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableEstoque);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 690, 340));

        bExcluir.setText("Excluir");
        bExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(bExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 460, 140, 30));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 650, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 150, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        bPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/procurar_24px.png"))); // NOI18N
        bPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPesquisarMouseClicked(evt);
            }
        });
        getContentPane().add(bPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 35, -1, -1));

        bEntradaEstoque.setText("Entrada");
        bEntradaEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEntradaEstoqueActionPerformed(evt);
            }
        });
        getContentPane().add(bEntradaEstoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 150, 70));

        bSaida.setText("Saída");
        bSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaidaActionPerformed(evt);
            }
        });
        getContentPane().add(bSaida, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 150, 70));

        jButton4.setText("Tela Lotes");
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 140, 70));

        bEditar.setText("Editar");
        bEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarActionPerformed(evt);
            }
        });
        getContentPane().add(bEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, 140, 30));

        setBounds(0, 0, 730, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void bPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPesquisarMouseClicked
        System.out.println("Vai relizar a pesquisa");
    }//GEN-LAST:event_bPesquisarMouseClicked

    private void bEntradaEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEntradaEstoqueActionPerformed
        CadProduto cadProd = new CadProduto(this);
        cadProd.setVisible(true);
    }//GEN-LAST:event_bEntradaEstoqueActionPerformed

    private void bEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarActionPerformed
        EditProdutos eProduto;
        ArrayList<Integer> selected = this.tModel.getIdSelected();
        switch(selected.size()){
            case 0:
                JOptionPane.showMessageDialog(rootPane,"Selecione um item para edição","",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                try{
                    eProduto = new EditProdutos(this, selected.get(0));
                    eProduto.setVisible(true);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane,"Ocorreu um erro, por favor contate o superte tecnico.\n"+ex,"Erro",JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                JOptionPane.showMessageDialog(rootPane,"Só é possível editar até 1 itens","",JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        this.tModel.setAllUnselect();
    }//GEN-LAST:event_bEditarActionPerformed

    private void bExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcluirActionPerformed
        Produto estab = new Produto();
        ArrayList rs;
        ArrayList<Integer> selected = this.tModel.getIdSelected();
        
        rs = estab.deleteProduto(selected);
        this.tModel.removeRow(rs);
        JOptionPane.showMessageDialog(rootPane,
                                      rs.size()+" produtos foram excluidos de "+selected.size()+"\n"
                                    + "É permetido a exclusão só dos produtos que não tem mais no estoque",
                                    "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
        this.tModel.setAllUnselect();
    }//GEN-LAST:event_bExcluirActionPerformed

    private void bSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaidaActionPerformed
        try{
            Estabelecimento estabelecimento = new Estabelecimento();
            ArrayList<ComboBox> cbEstab = new ArrayList();
            cbEstab.add(new ComboBox(0, "<Estabelecimento>"));
            for(ArrayList listaEstab: estabelecimento.listaEstabComboBox()){
                cbEstab.add(new ComboBox(Integer.parseInt((String)listaEstab.get(0)), (String)listaEstab.get(1)));
            }
            try{
                ComboBox setRes = (ComboBox)JOptionPane.showInputDialog(null,
                                                "Selecione o estabelecimento",
                                                "Estabelecimento",
                                                JOptionPane.PLAIN_MESSAGE,
                                                null,
                                                cbEstab.toArray(),
                                                "");
                switch(setRes.getIndex()){
                    case 0:
                        JOptionPane.showMessageDialog(rootPane, "Nenhum estabelecimento selecionado");
                        break;
                    default:
                        new Venda(setRes.getIndex()).setVisible(true);
                        break;
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(rootPane, ex);
            }
        }catch(SQLException ex){
            System.out.println(ex);    
        }
    }//GEN-LAST:event_bSaidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEditar;
    private javax.swing.JButton bEntradaEstoque;
    private javax.swing.JButton bExcluir;
    private javax.swing.JLabel bPesquisar;
    private javax.swing.JButton bSaida;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableEstoque;
    // End of variables declaration//GEN-END:variables
}
