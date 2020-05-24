package view;

import control.Funcao;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CadFuncao extends javax.swing.JFrame {

    private Funcoes telaFuncoes;
    private Funcao funcao;
    
    
    public CadFuncao(){
        
        this.setUndecorated(true);
        initComponents();
        
    }
    
    //QUANDO FOR CADASTRO
    public CadFuncao(Funcoes telaFuncoes){
        this.telaFuncoes = telaFuncoes;
        this.funcao = new Funcao();
        
        this.setUndecorated(true);
        initComponents();
    }
    
    //QUANDO FOR UMA EDIÇÃO
    public CadFuncao(Funcoes telaFuncoes, int id)throws SQLException{
        this.telaFuncoes = telaFuncoes;
        this.funcao = new Funcao(id);
        
        this.setUndecorated(true);
        initComponents();

        this.carregaCampos();
    }
    
    private void carregaCampos(){
        tFuncao.setText(this.funcao.getDescricao());
    }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tFuncao = new javax.swing.JTextField();
        bSalvar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 9));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/funcao_24px.png"))); // NOI18N
        jLabel1.setText("Função");

        jLabel2.setText("Função:");

        tFuncao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tFuncaoFocusLost(evt);
            }
        });

        bSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save_16px.png"))); // NOI18N
        bSalvar.setText("Salvar");
        bSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarActionPerformed(evt);
            }
        });

        bCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tFuncao)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                        .addComponent(bCancelar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bCancelar))
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 240));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        if(this.funcao.getIdFuncao() > 0 && !"".equals(this.funcao.getDescricao())){
            //QUANDO FOR EDIÇÃO
            try{
                if(this.funcao.update()){
                    JOptionPane.showMessageDialog(rootPane, "Atualizado com sucesso",
                                                    "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    this.telaFuncoes.updateFuncaoInTheTable(this.funcao);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Algo de errado aconteceu, por favor contate o suporte",
                                                "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(rootPane, ex, "ERRO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if(!"".equals(this.funcao.getDescricao())){
            //QUANDO FOR PRA NOVA FUNÇÃO
            try{
                if(this.funcao.save()){
                    JOptionPane.showMessageDialog(rootPane, "Função "+this.funcao.getDescricao()+" cadastrada com sucesso",
                                                    "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    this.telaFuncoes.addFuncao(this.funcao);
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Algo de errado aconteceu, por favor contate o suporte",
                                                "ERRO", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch(SQLException ex){
                JOptionPane.showMessageDialog(rootPane, ex, "ERRO", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Função não informada", "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_bSalvarActionPerformed

    private void tFuncaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tFuncaoFocusLost
        if(!"".equals(tFuncao.getText())) this.funcao.setDescricao(tFuncao.getText());
    }//GEN-LAST:event_tFuncaoFocusLost
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadFuncao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField tFuncao;
    // End of variables declaration//GEN-END:variables
}
