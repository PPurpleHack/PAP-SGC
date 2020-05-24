package view;

import control.Setor;
import model.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;


public class Setores extends javax.swing.JInternalFrame {

    private Main main;
    private TableModel tModel;
    
    public Setores(Main main){
        initComponents();
        this.main = main;
        
        //****Constroi tabela
        String[] colunas = {"", "id", "Nome", "Estabelecimento"};
        this.tModel = new TableModel(colunas);
        //****Carrega tabela
        this.loadTable();
        //****Seta modelo
        tableSetores.setModel(this.tModel);
    }
    
    public void loadTable(){
        //Carregamento da tabela
        Setor setor = new Setor();
        Map<String, String> filtros = new HashMap<>();
        //Preencher os filtros
        try{
            ArrayList<Map> listaSetor = setor.lista(filtros);
            listaSetor.forEach((Map e) -> {
                Setores.this.tModel.addRow(new Object[]{
                    Boolean.FALSE,
                    e.get("id"),
                    e.get("nome"),
                    e.get("estabelecimento")
                });
            });
        }catch(SQLException ex){
            System.out.println("Erro ao listar tabelas: "+ex);
        }
    }
    
    public void addInTheTable(Setor s)throws SQLException{
        //Atualizar tabela quando for feito o cadastro de um novo setor
        this.tModel.addRow(new Object[]{Boolean.FALSE,s.getId(),s.getNome(),s.getEstabelecimento(s.getId())});
    }
    
    public void updateTable(Setor s)throws SQLException{
        //Atualizar tabela quando for atualizado um funcionario ta lista
        Integer index = this.tModel.getIndexById(s.getId());
        this.tModel.updateRow(index, new Object[]{
            Boolean.FALSE,
            Integer.toString(s.getId()),//Na hora de pegar a matricula tem que tornar ela um String
            s.getNome(),
            s.getEstabelecimento(s.getId())
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSetores = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        bPesquisar = new javax.swing.JLabel();
        bNovo = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        bTurnBack = new javax.swing.JButton();
        bEditar = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setBorder(null);
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tableSetores.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableSetores);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 690, 330));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 650, -1));

        bPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/procurar_24px.png"))); // NOI18N
        bPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPesquisarMouseClicked(evt);
            }
        });
        getContentPane().add(bPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 35, -1, -1));

        bNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new_24px.png"))); // NOI18N
        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });
        getContentPane().add(bNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 160, 70));

        bExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bExcluir.setText("Excluir");
        bExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(bExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 410, 160, 70));

        bTurnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/turnback_24px.png"))); // NOI18N
        bTurnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTurnBackActionPerformed(evt);
            }
        });
        getContentPane().add(bTurnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        bEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit_24px.png"))); // NOI18N
        bEditar.setText("Editar");
        bEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarActionPerformed(evt);
            }
        });
        getContentPane().add(bEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 160, 70));

        setBounds(0, 0, 730, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void bPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPesquisarMouseClicked
        
    }//GEN-LAST:event_bPesquisarMouseClicked

    private void bTurnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTurnBackActionPerformed
        this.main.fechaTelas();
        this.main.getEstabelecimentos().setVisible(true);
    }//GEN-LAST:event_bTurnBackActionPerformed

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        new CadSetor(this).setVisible(true);
    }//GEN-LAST:event_bNovoActionPerformed

    private void bEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarActionPerformed
        ArrayList<Integer> selected = this.tModel.getIdSelected();
        switch(selected.size()){
            case 0:
                JOptionPane.showMessageDialog(rootPane,"Selecione um item para edição","",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                try{
                    new CadSetor(this, selected.get(0)).setVisible(true);
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
        int rs = new Setor().delete(this.tModel.getIdSelected());
        switch(rs){
            case 1:
                this.tModel.removeRow();
                JOptionPane.showMessageDialog(rootPane,"Excluido com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1451:
                JOptionPane.showMessageDialog(rootPane,"Não foi possível excluir esse setor, existem informações dependeste dele no sistema","Error",JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(rootPane,"Algo de arrado aconteceu. \nCódigo de erro: "+rs,"Error",JOptionPane.ERROR_MESSAGE);
                break;
        }
    }//GEN-LAST:event_bExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEditar;
    private javax.swing.JButton bExcluir;
    private javax.swing.JButton bNovo;
    private javax.swing.JLabel bPesquisar;
    private javax.swing.JButton bTurnBack;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableSetores;
    // End of variables declaration//GEN-END:variables
}
