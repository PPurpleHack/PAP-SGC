package view;

import model.ComboBox;
import control.Estabelecimento;
import control.Setor;
import control.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class EditProdutos extends javax.swing.JFrame {

    private Produto produto;
    private Estoque estoque;
    
    public EditProdutos(Estoque estoque, int id)throws SQLException{
        this.estoque = estoque;
        this.produto = new Produto(id);
        
        this.setUndecorated(true);
        initComponents();
        this.carregaCampos();
    }
    
    private Vector createComboBoxEstabelecimento(){
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
                                        "Erro ao lista ComboBox",
                                        JOptionPane.ERROR_MESSAGE);
        }
        
        return model;
    }
    
    private Vector createComboBoxSetor(){
        Vector model = new Vector();
        model.add(new ComboBox(0, "<Funções>"));
        return model;
    }
    
    private void loadComboBoxSetor()throws SQLException{
        System.out.println(((ComboBox)cbEstabelecimento.getSelectedItem()).getValue());
        
        
        Vector model = new Vector();
        Setor setor = new Setor();
        model.addElement(new ComboBox(0, "<Setor>"));
        Map<String, String> filtros = new HashMap();
        filtros.put("estabelecimento", Integer.toString(((ComboBox)cbEstabelecimento.getSelectedItem()).getIndex()));
        ArrayList<Map> lista = setor.lista(filtros);
        for(Map list: lista){
            model.addElement(new ComboBox(Integer.parseInt((String)list.get("id")), (String)list.get("nome")));
        }
        cbSetor.setModel(new DefaultComboBoxModel(model));
    }
    
    public void carregaCampos()throws SQLException{
        tCodigo.setText(this.produto.getCodProduto());
        tNome.setText(this.produto.getNome());
        tQtd.setText(Integer.toString(this.produto.getQtdProduto()));
        tPreco.setText(Double.toString(this.produto.getPrecoVarejo()));
        tDesconto.setText(Double.toString(this.produto.getDescontoAtacado()));
        tQtdDesconto.setText(Integer.toString(this.produto.getQtdAtacado()));
        
        Estabelecimento estabelecimento = new Estabelecimento(this.produto.getEstabelecimento());
        System.out.println(estabelecimento);
        cbEstabelecimento.getModel().setSelectedItem(new ComboBox(estabelecimento.getId(), estabelecimento.getNome()));
        this.loadComboBoxSetor();
        Setor setor = new Setor(this.produto.getSetor());
        cbSetor.getModel().setSelectedItem(new ComboBox(setor.getId(), setor.getNome()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tCodigo = new javax.swing.JTextField();
        tNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tQtd = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tPreco = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tDesconto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tQtdDesconto = new javax.swing.JTextField();
        cbEstabelecimento = new javax.swing.JComboBox<>(this.createComboBoxEstabelecimento());
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbSetor = new javax.swing.JComboBox<>(this.createComboBoxSetor());
        bSalvar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 9));
        jPanel1.setPreferredSize(new java.awt.Dimension(420, 400));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/produto_24px.png"))); // NOI18N
        jLabel1.setText("Produto");

        jLabel2.setText("Código:");

        tCodigo.setEditable(false);
        tCodigo.setBackground(new java.awt.Color(204, 204, 204));

        tNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tNomeFocusLost(evt);
            }
        });
        tNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNomeActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome:");

        jLabel5.setText("Quantidade:");

        tQtd.setEditable(false);
        tQtd.setBackground(new java.awt.Color(204, 204, 204));
        tQtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tQtdActionPerformed(evt);
            }
        });

        jLabel6.setText("Preço:");

        tPreco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tPrecoFocusLost(evt);
            }
        });
        tPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPrecoActionPerformed(evt);
            }
        });

        jLabel7.setText("Quantidade para desconto:");

        tDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tDescontoFocusLost(evt);
            }
        });
        tDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tDescontoActionPerformed(evt);
            }
        });

        jLabel8.setText("Desconto(%):");

        tQtdDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tQtdDescontoFocusLost(evt);
            }
        });
        tQtdDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tQtdDescontoActionPerformed(evt);
            }
        });

        cbEstabelecimento.setEnabled(false);

        jLabel9.setText("Setor:");

        jLabel10.setText("Estabelecimento:");

        cbSetor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbSetorFocusLost(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(122, 122, 122)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tCodigo))
                    .addComponent(tNome, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbEstabelecimento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tQtdDesconto, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))))
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSetor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(tDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCancelar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tQtdDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEstabelecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bCancelar))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNomeActionPerformed
        //CODIGO MORTO
    }//GEN-LAST:event_tNomeActionPerformed

    private void tQtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tQtdActionPerformed
        //CODIGO MORTO
    }//GEN-LAST:event_tQtdActionPerformed

    private void tPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPrecoActionPerformed
        //CODIGO MORTO
    }//GEN-LAST:event_tPrecoActionPerformed

    private void tDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tDescontoActionPerformed
        //CODIGO MORTO
    }//GEN-LAST:event_tDescontoActionPerformed

    private void tQtdDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tQtdDescontoActionPerformed
        //CODIGO MORTO
    }//GEN-LAST:event_tQtdDescontoActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        try{
            if(produto.getNome().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Nome não pode estar vazio", "Nome não informado", JOptionPane.WARNING_MESSAGE);
            }else if(produto.getPrecoVarejo().equals("")){
                JOptionPane.showMessageDialog(rootPane, "Preço não pode estar vazio", "Preço não informado", JOptionPane.WARNING_MESSAGE);
            }else{
                if(produto.update()){
                    JOptionPane.showMessageDialog(rootPane, "Atualizado com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    this.estoque.updateTable(produto);
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Não foi possível concluir o processo", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bSalvarActionPerformed

    private void tNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tNomeFocusLost
        this.produto.setNome(tNome.getText());
    }//GEN-LAST:event_tNomeFocusLost

    private void tPrecoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tPrecoFocusLost
        try{
            if(!tPreco.getText().equals("")){
                this.produto.setPrecoVarejo(Double.parseDouble(tPreco.getText()));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_tPrecoFocusLost

    private void tDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDescontoFocusLost
        try{
            if(!tDesconto.getText().equals("")){
                this.produto.setDescontoAtacado(Double.parseDouble(tDesconto.getText()));
            }else{
                this.produto.setDescontoAtacado(0.00);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_tDescontoFocusLost

    private void tQtdDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tQtdDescontoFocusLost
        try{
            if(!tQtdDesconto.getText().equals("")){
                this.produto.setQtdAtacado(Integer.parseInt(tQtdDesconto.getText()));
            }else{
                this.produto.setQtdAtacado(0);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_tQtdDescontoFocusLost

    private void cbSetorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbSetorFocusLost
        this.produto.setSetor(((ComboBox)cbSetor.getSelectedItem()).getIndex());
    }//GEN-LAST:event_cbSetorFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bSalvar;
    private javax.swing.JComboBox<String> cbEstabelecimento;
    private javax.swing.JComboBox<String> cbSetor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tDesconto;
    private javax.swing.JTextField tNome;
    private javax.swing.JTextField tPreco;
    private javax.swing.JTextField tQtd;
    private javax.swing.JTextField tQtdDesconto;
    // End of variables declaration//GEN-END:variables
}
