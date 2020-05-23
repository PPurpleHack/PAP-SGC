package view;

//IMPORTS
import control.Estabelecimento;
import model.TableModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public final class Estabelecimentos extends javax.swing.JInternalFrame {
    
    private final Main main;
    private final TableModel tModel;
    
    public Estabelecimentos(Main main) {
        //Carrega atributos
        this.main = main;
        
        initComponents();
        
        //****Constroi tabela
        String[] colunas = {"","ID", "Nome", "CNPJ", "Estado", "Cidade"};
        this.tModel = new TableModel(colunas);
        //****Carrega tabela
        this.loadTable();
        //****Seta modelo
        tableEstabelecimento.setModel(this.tModel);
        
    }
    
    public void loadTable(){
        //Carregamento da tabela
        Estabelecimento estab = new Estabelecimento();
        Map<String, String> filtros = new HashMap<>();
        //Preencher os filtros
        try{
            ArrayList<Map> listaEstab = estab.listaEstabelecimentos(filtros);
            listaEstab.forEach((Map e) -> {
                Estabelecimentos.this.tModel.addRow(new Object[]{
                    Boolean.FALSE,
                    e.get("id"),
                    e.get("nome"),
                    e.get("cnpj"),
                    e.get("estado"),
                    e.get("cidade")
                });
            });
        }catch(SQLException ex){
            System.out.println("Erro ao listar tabelas: "+ex);
        }
    }
    
    public void addEstab(Estabelecimento e){
        //Atualizar tabela quando for feito o cadastro de um novo estabelecimento
        this.tModel.addRow(new Object[]{Boolean.FALSE,e.getId(),e.getNome(),e.getCnpj(),e.getEstado(),e.getCidade()});
    }
    
    public void updateEstabInTheTable(Estabelecimento e){
        //Atualizar tabela quando for atualizado um estabelecimento ta lista
        Integer index = this.tModel.getIndexById(e.getId());
        this.tModel.updateRow(index, new Object[]{
            Boolean.FALSE,
            Integer.toString(e.getId()),//Na hora de pegar o id é feito um casting pra String
            e.getNome(),
            e.getCnpj(),
            e.getEstado(),
            e.getCidade()
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEstabelecimento = new javax.swing.JTable();
        bEditar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        bNovo = new javax.swing.JButton();
        bPesquisar = new javax.swing.JLabel();
        bSetores = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setBorder(null);
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tableEstabelecimento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "CNPJ", "Estado", "Cidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableEstabelecimento);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 690, 340));

        bEditar.setText("Editar");
        bEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarActionPerformed(evt);
            }
        });
        getContentPane().add(bEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 150, -1));

        bExcluir.setText("Excluir");
        bExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(bExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 150, 63));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 650, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 150, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });
        getContentPane().add(bNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 150, -1));

        bPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/procurar_24px.png"))); // NOI18N
        bPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPesquisarMouseClicked(evt);
            }
        });
        getContentPane().add(bPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 35, -1, -1));

        bSetores.setText("Setores");
        bSetores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSetoresActionPerformed(evt);
            }
        });
        getContentPane().add(bSetores, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 360, 63));

        setBounds(0, 0, 730, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void bExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcluirActionPerformed
        Estabelecimento estab = new Estabelecimento();
        Integer rs;
        ArrayList<Integer> selected = this.tModel.getIdSelected();
        
        rs = estab.excluirEstabelecimento(selected);
        switch(rs){
            case 1:
                this.tModel.removeRow();
                JOptionPane.showMessageDialog(rootPane,"The branchs has been excluded successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1451:
                JOptionPane.showMessageDialog(rootPane,"It's not possible delete this branch, there are some informations depending this!","Error",JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(rootPane,"Sorry, something went wrong!","Error",JOptionPane.ERROR_MESSAGE);
                System.out.println("Código de erro: "+rs);
                break;
        }
        this.tModel.setAllUnselect();
    }//GEN-LAST:event_bExcluirActionPerformed

    private void bPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPesquisarMouseClicked
        System.out.println("Vai relizar a pesquisa");
    }//GEN-LAST:event_bPesquisarMouseClicked

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        CadEstabelecimento cadEstab = new CadEstabelecimento(this);
        cadEstab.setVisible(true);
    }//GEN-LAST:event_bNovoActionPerformed

    private void bEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarActionPerformed
        CadEstabelecimento cadEstab;
        ArrayList<Integer> selected = this.tModel.getIdSelected();
        switch(selected.size()){
            case 0:
                JOptionPane.showMessageDialog(rootPane,"Selecione um item para edição","",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                try{
                    cadEstab = new CadEstabelecimento(this, selected.get(0));
                    cadEstab.setVisible(true);
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

    private void bSetoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSetoresActionPerformed
        this.main.fechaTelas();
        this.main.getSetores().setVisible(true);
    }//GEN-LAST:event_bSetoresActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEditar;
    private javax.swing.JButton bExcluir;
    private javax.swing.JButton bNovo;
    private javax.swing.JLabel bPesquisar;
    private javax.swing.JButton bSetores;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableEstabelecimento;
    // End of variables declaration//GEN-END:variables
}
