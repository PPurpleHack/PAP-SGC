package view;

import control.Funcionario;
import java.sql.SQLException;

public class Main extends javax.swing.JFrame {
    
    private static Funcionario user;
    private static Estoque estoque;
    private static Funcionarios funcionarios;
    private static Estabelecimentos estabelecimentos;
    private static Setores setores;
    private static Funcoes funcoes;
    private static Configuracoes configuracoes;
    
    public Main(Funcionario usr) throws SQLException{
        //Usuario que se logou
        System.out.println(usr);
        Main.user = usr;
        
        initComponents();
        
        tUser.setText(Main.user.getNome()+" "+Main.user.getSobrenome());
        
        Main.estoque = new Estoque(this);
        pConteudo.add(Main.estoque);
        Main.funcionarios = new Funcionarios(this);
        pConteudo.add(Main.funcionarios);
        Main.estabelecimentos = new Estabelecimentos(this);
        pConteudo.add(Main.estabelecimentos);
        Main.setores = new Setores(this);
        pConteudo.add(Main.setores);
        Main.funcoes = new Funcoes(this);
        pConteudo.add(Main.funcoes);
        Main.configuracoes = new Configuracoes(usr);
        pConteudo.add(Main.configuracoes);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        bEstabelecimentos = new javax.swing.JButton();
        bFuncionarios = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tUser = new javax.swing.JLabel();
        pConteudo = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(226, 188, 123));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(900, 560));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 20));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 910, 20));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(172, 20, -1, 520));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/estoque_24px.png"))); // NOI18N
        jButton1.setText("Estoque");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 171, 150, 40));

        bEstabelecimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/estabelecimento_24px.png"))); // NOI18N
        bEstabelecimentos.setText("Estabelecimentos");
        bEstabelecimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEstabelecimentosActionPerformed(evt);
            }
        });
        jPanel3.add(bEstabelecimentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 287, 150, 40));

        bFuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/funcionarios_24px.png"))); // NOI18N
        bFuncionarios.setText("Funcionarios");
        bFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFuncionariosActionPerformed(evt);
            }
        });
        jPanel3.add(bFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 229, 150, 40));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configuracao_128px.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 396, 150, 113));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/painel-de-controle_32px.png"))); // NOI18N
        jButton5.setText("Estatisticas");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 345, 150, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/usuario_128px.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 0, -1, 137));

        tUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tUser.setText("FUNCIONARIO");
        jPanel3.add(tUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 170, 520));

        pConteudo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoPAP.jpg"))); // NOI18N

        pConteudo.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pConteudoLayout = new javax.swing.GroupLayout(pConteudo);
        pConteudo.setLayout(pConteudoLayout);
        pConteudoLayout.setHorizontalGroup(
            pConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pConteudoLayout.setVerticalGroup(
            pConteudoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pConteudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 730, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.fechaTelas();
        Main.estoque.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFuncionariosActionPerformed
        this.fechaTelas();
        Main.funcionarios.setVisible(true);
    }//GEN-LAST:event_bFuncionariosActionPerformed

    private void bEstabelecimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEstabelecimentosActionPerformed
        this.fechaTelas();
        Main.estabelecimentos.setVisible(true);
    }//GEN-LAST:event_bEstabelecimentosActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.fechaTelas();
        Main.configuracoes.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed
    
    //Verifica e fecha telas
    public void fechaTelas(){
        
        if(Main.estoque.isVisible()) Main.estoque.setVisible(false);
        else if(Main.funcionarios.isVisible()) Main.funcionarios.setVisible(false);
        else if(Main.estabelecimentos.isVisible()) Main.estabelecimentos.setVisible(false);
        else if(Main.setores.isVisible()) Main.setores.setVisible(false);
        else if(Main.funcoes.isVisible()) Main.funcoes.setVisible(false);
        else if(Main.configuracoes.isVisible()) Main.configuracoes.setVisible(false);
        else System.out.println("Entrou em um nehuma tela visivel");
        
    }
    
    //getter and setters
    public static Setores getSetores() {
        return setores;
    }

    public static Estabelecimentos getEstabelecimentos() {
        return estabelecimentos;
    }
    
    public static Funcoes getFuncoes(){
        return funcoes;
    }
    
    public static Funcionarios getFuncionarios(){
        return funcionarios;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEstabelecimentos;
    private javax.swing.JButton bFuncionarios;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JDesktopPane pConteudo;
    private javax.swing.JLabel tUser;
    // End of variables declaration//GEN-END:variables
}
