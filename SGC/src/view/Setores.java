package view;

import control.Estabelecimento;
import control.Setor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;


public class Setores extends javax.swing.JInternalFrame {

    private Main main;
    
    public Setores(Main main){
        initComponents();
        this.main = main;
        
        //this.loadTable();
    }
    
    public void loadTable(){
        //Construção da tabela
        Setor setor = new Setor();
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"","ID", "Nome", "Estabelecimento"}, 0){
            //Torna editavel ou não
            @Override
            public boolean isCellEditable(int row, int column){
                return column == 0;
            }
            //Para o check box
            @Override
            public Class<?> getColumnClass(int columnIndex){
                return getValueAt(0, columnIndex).getClass();
            }
        };
        Map<String, String> filtros = new HashMap<>();
        
        try{
            ArrayList<Map> listaEstab = setor.lista(filtros);
            listaEstab.forEach((var e) -> {
                modelo.addRow(new Object[]{
                    Boolean.FALSE,
                    e.get("id"),
                    e.get("nome"),
                    e.get("estabelecimento")
                });
                //System.out.println(e);
            });
            tableSetor.setModel(modelo);
        }catch(SQLException ex){
            System.out.println("Erro ao listar tabelas: "+ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSetor = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        bPesquisar = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bTurnBack = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setBorder(null);
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tableSetor.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableSetor);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 690, 330));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 650, -1));

        bPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/procurar_24px.png"))); // NOI18N
        bPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPesquisarMouseClicked(evt);
            }
        });
        getContentPane().add(bPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 35, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new_24px.png"))); // NOI18N
        jButton1.setText("Novo");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 690, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit_24px.png"))); // NOI18N
        jButton2.setText("Editar");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 690, -1));

        bTurnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/turnback_24px.png"))); // NOI18N
        bTurnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTurnBackActionPerformed(evt);
            }
        });
        getContentPane().add(bTurnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        setBounds(0, 0, 730, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void bPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPesquisarMouseClicked
        
    }//GEN-LAST:event_bPesquisarMouseClicked

    private void bTurnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTurnBackActionPerformed
        this.main.fechaTelas();
        this.main.getEstabelecimentos().setVisible(true);
    }//GEN-LAST:event_bTurnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bPesquisar;
    private javax.swing.JButton bTurnBack;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableSetor;
    // End of variables declaration//GEN-END:variables
}
