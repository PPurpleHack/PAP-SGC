package view;

import control.Estabelecimento;
import control.Setor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import model.ComboBox;

public class CadSetor extends javax.swing.JFrame {
    
    private Setor setor;
    private Setores telaSetores;
    
    public CadSetor() {
        this.setUndecorated(true);
        initComponents();
    }
    
    //QUANDO FOR CADASTRO
    public CadSetor(Setores telaSetores){
        this.telaSetores = telaSetores;
        this.setor = new Setor();
        
        this.setUndecorated(true);
        initComponents();
    }
    
    //QUANDO FOR EDIÇÃO
    public CadSetor(Setores telaSetores, int id)throws SQLException{
        this.telaSetores = telaSetores;
        this.setor = new Setor(id);
        
        this.setUndecorated(true);
        initComponents();
        this.carregaCampos();
    }
    
    public void carregaCampos(){
        tSetor.setText(this.setor.getNome());
        try{
            Estabelecimento estab = new Estabelecimento(this.setor.getEstabelecimento());
            cbEstabelecimento.getModel().setSelectedItem(new ComboBox(estab.getId(), estab.getNome()));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Vector createComboBoxEstabelecimento(){
        Vector model = new Vector();
        model.add(new ComboBox(0, "<Estabelecimentos>"));
        Estabelecimento estab = new Estabelecimento();
        
        try{
            ArrayList<ArrayList> lista = estab.listaEstabComboBox();
            for(ArrayList list: lista){
                model.addElement(new ComboBox(Integer.parseInt((String)list.get(0)), (String)list.get(1)));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane,
                                        "Ocorreu um erro. Por favor contate o suporte tecnico.",
                                        "Erro ao listar ComboBox",
                                        JOptionPane.ERROR_MESSAGE);
        }
        
        return model;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tSetor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbEstabelecimento = new javax.swing.JComboBox<>(this.createComboBoxEstabelecimento());
        bSalvar = new javax.swing.JToggleButton();
        bCacelar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 9));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/setor_24px.png"))); // NOI18N
        jLabel1.setText("Setor");

        jLabel2.setText("Setor:");

        tSetor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tSetorFocusLost(evt);
            }
        });

        jLabel3.setText("Estabelecimento:");

        cbEstabelecimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbEstabelecimentoFocusLost(evt);
            }
        });

        bSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save_16px.png"))); // NOI18N
        bSalvar.setText("Salvar");
        bSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarActionPerformed(evt);
            }
        });

        bCacelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bCacelar.setText("Calcelar");
        bCacelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCacelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tSetor)
                    .addComponent(cbEstabelecimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(bCacelar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEstabelecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bCacelar))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 280));

        setSize(new java.awt.Dimension(310, 280));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bCacelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCacelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bCacelarActionPerformed

    private void tSetorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tSetorFocusLost
        this.setor.setNome(tSetor.getText());
    }//GEN-LAST:event_tSetorFocusLost

    private void cbEstabelecimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbEstabelecimentoFocusLost
        ComboBox cb = (ComboBox)cbEstabelecimento.getSelectedItem();
        this.setor.setEstabelecimento(cb.getIndex());
    }//GEN-LAST:event_cbEstabelecimentoFocusLost

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        if(this.setor.getId() == 0){
            //QUANDO FOR UM NOVO SETOR
            if("".equals(this.setor.getNome())){
                JOptionPane.showMessageDialog(rootPane, "Para salvar selecione digite o nome do setor",
                                                "Setor não informado", JOptionPane.WARNING_MESSAGE);
                tSetor.requestFocus();
            }else if(this.setor.getEstabelecimento() == 0){
                JOptionPane.showMessageDialog(rootPane, "Para salvar selecione um estabelecimento",
                                                "Estabelecimento não informado", JOptionPane.WARNING_MESSAGE);
                cbEstabelecimento.requestFocus();
            }else{
                try{
                    //PASSOU EM TODAS AS VERIFICAÇÕES
                    if(this.setor.salvar()){
                        JOptionPane.showMessageDialog(rootPane, "Cadastrado com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        this.telaSetores.addInTheTable(this.setor);
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro no processo.", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            //QUANDO FOR UMA EDIÇÃO
            if("".equals(this.setor.getNome())){
                JOptionPane.showMessageDialog(rootPane, "Para salvar selecione digite o nome do setor",
                                                "Setor não informado", JOptionPane.WARNING_MESSAGE);
                tSetor.requestFocus();
            }else if(this.setor.getEstabelecimento() == 0){
                JOptionPane.showMessageDialog(rootPane, "Para salvar selecione um estabelecimento",
                                                "Estabelecimento não informado", JOptionPane.WARNING_MESSAGE);
                cbEstabelecimento.requestFocus();
            }else{
                try{
                    //PASSOU EM TODAS AS VERIFICAÇÕES
                    if(this.setor.update()){
                        JOptionPane.showMessageDialog(rootPane, "Atualizado com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        this.telaSetores.updateTable(this.setor);
                    }else{
                        JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro no processo.", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                } catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_bSalvarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadSetor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadSetor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadSetor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadSetor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadSetor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton bCacelar;
    private javax.swing.JToggleButton bSalvar;
    private javax.swing.JComboBox<String> cbEstabelecimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tSetor;
    // End of variables declaration//GEN-END:variables
}
