package view;

import control.Estabelecimento;
import control.TelefoneEstabelecimento;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CadEstabelecimento extends javax.swing.JFrame {

    private boolean errorOfValues;
    private Estabelecimentos telaEstabelecimentos;
    private Estabelecimento estabelecimento;
    
    
    public CadEstabelecimento(){
        this.errorOfValues = false;
        
        this.setUndecorated(true);
        initComponents();
        
    }
    
    //QUANDO FOR CADASTRO
    public CadEstabelecimento(Estabelecimentos telaEstabelecimentos){
        this.errorOfValues = false;
        this.telaEstabelecimentos = telaEstabelecimentos;
        
        this.setUndecorated(true);
        initComponents();
    }
    
    //QUANDO FOR UMA EDIÇÃO
    public CadEstabelecimento(Estabelecimentos telaEstabelecimentos, int id)throws SQLException{
        this.errorOfValues = false;
        this.telaEstabelecimentos = telaEstabelecimentos;
        this.estabelecimento = new Estabelecimento(id);
        
        this.setUndecorated(true);
        initComponents();

        this.carregaCampos();
    }
    
    private void carregaCampos(){
        tNome.setText(this.estabelecimento.getNome());
        tCnpj.setText(this.estabelecimento.getCnpj());
        tCep.setText(this.estabelecimento.getCep());
        tNumero.setText(Integer.toString(this.estabelecimento.getNumero()));
        tCidade.setText(this.estabelecimento.getCidade());
        tBairro.setText(this.estabelecimento.getBairro());
        tEstado.setText(this.estabelecimento.getEstado());
        tPais.setText(this.estabelecimento.getPais());
        
        ArrayList<TelefoneEstabelecimento> tel = this.estabelecimento.getTelefone();
        
        //Preenche os campos dos contatos
        try{
            tDDD1.setText(tel.get(0).getDdd());
            tCont1.setText(tel.get(0).getNumero());
            bDeleteCont1.setVisible(true);
        }catch(Exception ex){
            System.out.println("Ignorar: "+ex);
        }
        try{
            tDDD2.setText(tel.get(1).getDdd());
            tCont2.setText(tel.get(1).getNumero());
            bDeleteCont2.setVisible(true);
        }catch(Exception ex){
            System.out.println("Ignorar: "+ex);
        }
        try{
            tDDD3.setText(tel.get(2).getDdd());
            tCont3.setText(tel.get(2).getNumero());
            bDeleteCont3.setVisible(true);
        }catch(Exception ex){
            System.out.println("Ignorar: "+ex);
        }
        try{
            tDDD4.setText(tel.get(3).getDdd());
            tCont4.setText(tel.get(3).getNumero());
            bDeleteCont4.setVisible(true);
        }catch(Exception ex){
            System.out.println("Ignorar: "+ex);
        }
    }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tCnpj = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tCep = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tCidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tBairro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tPais = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tEstado = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tDDD1 = new javax.swing.JTextField();
        tCont1 = new javax.swing.JTextField();
        tCont2 = new javax.swing.JTextField();
        tDDD2 = new javax.swing.JTextField();
        bSalvar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tNumero = new javax.swing.JTextField();
        tDDD3 = new javax.swing.JTextField();
        tDDD4 = new javax.swing.JTextField();
        tCont3 = new javax.swing.JTextField();
        tCont4 = new javax.swing.JTextField();
        bDeleteCont1 = new javax.swing.JButton();
        bDeleteCont2 = new javax.swing.JButton();
        bDeleteCont3 = new javax.swing.JButton();
        bDeleteCont4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(310, 545));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/estabelecimento_24px.png"))); // NOI18N
        jLabel1.setText("Estabelecimento");

        jLabel3.setText("Nome: ");

        jLabel4.setText("CNPJ:");

        jLabel5.setText("CEP:");

        jLabel6.setText("Cidade:");

        jLabel7.setText("Bairro:");

        jLabel8.setText("Pais:");

        jLabel9.setText("Estado:");

        jLabel15.setText("Número de Contato:");

        tDDD1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tDDD1FocusLost(evt);
            }
        });

        tDDD2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tDDD2FocusLost(evt);
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

        jLabel10.setText("Numero:");

        tNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tNumeroFocusLost(evt);
            }
        });

        tDDD3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tDDD3FocusLost(evt);
            }
        });

        tDDD4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tDDD4FocusLost(evt);
            }
        });

        bDeleteCont1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bDeleteCont1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteCont1ActionPerformed(evt);
            }
        });

        bDeleteCont2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bDeleteCont2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteCont2ActionPerformed(evt);
            }
        });

        bDeleteCont3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bDeleteCont3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteCont3ActionPerformed(evt);
            }
        });

        bDeleteCont4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bDeleteCont4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteCont4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tNome)
                    .addComponent(tCnpj)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tPais, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tEstado)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tCep, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tNumero))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCancelar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tDDD4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tCont4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tDDD3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tCont3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tDDD2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tCont2))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(96, 96, 96)
                                .addComponent(jLabel6))
                            .addComponent(jLabel15)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tDDD1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tCont1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bDeleteCont1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bDeleteCont2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bDeleteCont3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bDeleteCont4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bDeleteCont1)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tDDD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tCont1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tDDD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tCont2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bDeleteCont2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tDDD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tCont3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bDeleteCont3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tDDD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tCont4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bDeleteCont4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bCancelar))
                .addGap(371, 371, 371))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        //Processo de cadastro
        if(!this.errorOfValues){
            if(this.estabelecimento == null){
                //CASO SEJA UM CADASTRO
                try{
                    ArrayList<TelefoneEstabelecimento> tel = new ArrayList();
                    Estabelecimento estab = new Estabelecimento();
                    estab.setNome(tNome.getText());
                    estab.setCnpj(tCnpj.getText());
                    estab.setCep(tCep.getText());
                    estab.setNumero(Integer.parseInt(tNumero.getText()));
                    estab.setCidade(tCidade.getText());
                    estab.setBairro(tBairro.getText());
                    estab.setEstado(tEstado.getText());
                    estab.setPais(tPais.getText());
                    if(!"".equals(tDDD1.getText()) && !"".equals(tCont1.getText())) tel.add(new TelefoneEstabelecimento(tDDD1.getText(), tCont1.getText(), false));
                    if(!"".equals(tDDD2.getText()) && !"".equals(tCont2.getText())) tel.add(new TelefoneEstabelecimento(tDDD2.getText(), tCont2.getText(), false));
                    if(!"".equals(tDDD3.getText()) && !"".equals(tCont3.getText())) tel.add(new TelefoneEstabelecimento(tDDD3.getText(),tCont3.getText(),false));
                    if(!"".equals(tDDD4.getText()) && !"".equals(tCont4.getText()))tel.add(new TelefoneEstabelecimento(tDDD4.getText(),tCont4.getText(), false));
                    
                    estab.setTelefone(tel);
                    if(estab.cadastrarEstabalecimento()){
                        JOptionPane.showMessageDialog(rootPane,
                                                      "Estabelecimento "+tNome.getText()+" cadastrado com sucesso",
                                                      "Cadastrado com sucesso",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        this.telaEstabelecimentos.addEstab(estab);
                    } else {
                        JOptionPane.showMessageDialog(rootPane,
                                                      "Não foi possível cadastrar o estabelecimento "+tNome.getText()
                                                      +". Por favor contate o suporte.",
                                                      "Erro",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane,
                                                  "Não foi possível concluir o processamento. Por favor contate o suporte \n"+ex,
                                                  "Erro",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            } else {
                
                //CASO SEJA UMA EDIÇÃO
                this.estabelecimento.setNome(tNome.getText());
                this.estabelecimento.setCnpj(tCnpj.getText());
                this.estabelecimento.setCep(tCep.getText());
                this.estabelecimento.setNumero(Integer.parseInt(tNumero.getText()));
                this.estabelecimento.setCidade(tCidade.getText());
                this.estabelecimento.setBairro(tBairro.getText());
                this.estabelecimento.setEstado(tEstado.getText());
                this.estabelecimento.setPais(tPais.getText());
                
                ArrayList<TelefoneEstabelecimento> tel = this.estabelecimento.getTelefone();
                if(!"".equals(tDDD1.getText()) && !"".equals(tCont1.getText())){
                    try{
                        tel.get(0).setDdd(tDDD1.getText());
                        tel.get(0).setNumero(tCont1.getText());
                        tel.get(0).setCelular(false);
                    } catch(Exception ex){
                        tel.add(new TelefoneEstabelecimento(tDDD1.getText(), tCont1.getText(), false));
                    }
                }
                if(!"".equals(tDDD2.getText()) && !"".equals(tCont2.getText())){
                    try{
                        tel.get(1).setDdd(tDDD2.getText());
                        tel.get(1).setNumero(tCont2.getText());
                        tel.get(1).setCelular(false);
                    } catch(Exception ex){
                        tel.add(new TelefoneEstabelecimento(tDDD2.getText(), tCont2.getText(), false));
                    }
                }
                if(!"".equals(tDDD3.getText()) && !"".equals(tCont3.getText())){
                    try{
                        tel.get(2).setDdd(tDDD3.getText());
                        tel.get(2).setNumero(tCont3.getText());
                        tel.get(2).setCelular(false);
                    } catch(Exception ex){
                        tel.add(new TelefoneEstabelecimento(tDDD3.getText(), tCont3.getText(), false));
                    }
                }
                if(!"".equals(tDDD4.getText()) && !"".equals(tCont4.getText())){
                    try{
                        tel.get(3).setDdd(tDDD4.getText());
                        tel.get(3).setNumero(tCont4.getText());
                        tel.get(3).setCelular(false);
                    } catch(Exception ex){
                        tel.add(new TelefoneEstabelecimento(tDDD4.getText(), tCont4.getText(), false));
                    }
                }
                try{
                    if(this.estabelecimento.atualizarEstabelecimento()){
                        JOptionPane.showMessageDialog(rootPane,
                                                      "Estabelecimento "+tNome.getText()+" atualizado com sucesso",
                                                      "Atualizado com sucesso",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        this.telaEstabelecimentos.updateEstabInTheTable(this.estabelecimento);
                    } else {
                        JOptionPane.showMessageDialog(rootPane,
                                                      "Não foi possível cadastrar o estabelecimento "+tNome.getText()
                                                      +". Por favor contate o suporte.",
                                                      "Erro",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane,
                                                  "Não foi possível concluir o processamento. Por favor contate o suporte \n"+ex,
                                                  "Erro",
                                                  JOptionPane.ERROR_MESSAGE);
                }
           }
        } else {
            JOptionPane.showMessageDialog(rootPane,
                                          "Preencha valores válidos para poder salvar",
                                          "Valores invalidos",
                                          JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_bSalvarActionPerformed

    private void tNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tNumeroFocusLost
        //Verifica se foi digitado um número
        try{
            int numero = Integer.parseInt(tNumero.getText());
            this.errorOfValues = false;
        }catch(NumberFormatException ex){
            this.errorOfValues = true;
            JOptionPane.showMessageDialog(rootPane,
                                          "Digite por favor um número",
                                          "Texto indevido",
                                          JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tNumeroFocusLost

    private void tDDD1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDDD1FocusLost
        try {
            if(tDDD1.getText().length() != 3){
                JOptionPane.showMessageDialog(rootPane,"Digite um ddd válido","Texto indevido",JOptionPane.INFORMATION_MESSAGE);
                this.errorOfValues = true;
            } else{
                int numero = Integer.parseInt(tDDD1.getText());
                this.errorOfValues = false;
            }     
        }catch(NumberFormatException ex){
            this.errorOfValues = true;
            JOptionPane.showMessageDialog(rootPane,"Digite um ddd válido","Texto indevido",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tDDD1FocusLost

    private void tDDD2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDDD2FocusLost
        try {
            if(tDDD2.getText().length() != 3){
                JOptionPane.showMessageDialog(rootPane,"Digite um ddd válido","Texto indevido",JOptionPane.INFORMATION_MESSAGE);
                this.errorOfValues = true;
            } else{
                int numero = Integer.parseInt(tDDD2.getText());
                this.errorOfValues = false;
            }     
        }catch(NumberFormatException ex){
            this.errorOfValues = true;
            JOptionPane.showMessageDialog(rootPane,"Digite um ddd válido","Texto indevido",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tDDD2FocusLost

    private void tDDD3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDDD3FocusLost
        try {
            if(tDDD3.getText().length() != 3){
                JOptionPane.showMessageDialog(rootPane,"Digite um ddd válido","Texto indevido",JOptionPane.INFORMATION_MESSAGE);
                this.errorOfValues = true;
            } else{
                int numero = Integer.parseInt(tDDD3.getText());
                this.errorOfValues = false;
            }     
        }catch(NumberFormatException ex){
            this.errorOfValues = true;
            JOptionPane.showMessageDialog(rootPane,"Digite um ddd válido","Texto indevido",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tDDD3FocusLost

    private void tDDD4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDDD4FocusLost
        try {
            if(tDDD4.getText().length() != 3){
                JOptionPane.showMessageDialog(rootPane,"Digite um ddd válido","Texto indevido",JOptionPane.INFORMATION_MESSAGE);
                this.errorOfValues = true;
            } else{
                int numero = Integer.parseInt(tDDD4.getText());
                this.errorOfValues = false;
            }     
        }catch(NumberFormatException ex){
            this.errorOfValues = true;
            JOptionPane.showMessageDialog(rootPane,"Digite um ddd válido","Texto indevido",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tDDD4FocusLost

    private void bDeleteCont1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteCont1ActionPerformed
        
        //Vai excluir o contato 1
        try{
            //LIMPA OBJETO
            this.estabelecimento.getTelefone().get(0).setDdd(null);
            this.estabelecimento.getTelefone().get(0).setNumero(null);
            this.estabelecimento.getTelefone().get(0).setEstab(0);
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
        //LIMPA CAMPOS DE TEXTO
        tDDD1.setText("");
        tCont1.setText("");
        
    }//GEN-LAST:event_bDeleteCont1ActionPerformed

    private void bDeleteCont2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteCont2ActionPerformed
        
        //Vai excluir o contato 2
        try{
            //LIMPA OBJETO
            this.estabelecimento.getTelefone().get(1).setDdd(null);
            this.estabelecimento.getTelefone().get(1).setNumero(null);
            this.estabelecimento.getTelefone().get(1).setEstab(0);
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
        //LIMPA CAMPOS DE TEXTO
        tDDD2.setText("");
        tCont2.setText("");
        
    }//GEN-LAST:event_bDeleteCont2ActionPerformed

    private void bDeleteCont3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteCont3ActionPerformed
        
        //Vai excluir o contato 3
        try{
            //LIMPA OBJETO
            this.estabelecimento.getTelefone().get(2).setDdd(null);
            this.estabelecimento.getTelefone().get(2).setNumero(null);
            this.estabelecimento.getTelefone().get(2).setEstab(0);
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
        //LIMPA CAMPOS DE TEXTO
        tDDD3.setText("");
        tCont3.setText("");
        
    }//GEN-LAST:event_bDeleteCont3ActionPerformed

    private void bDeleteCont4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteCont4ActionPerformed
        
        //Vai excluir o contato 4
        try{
            //LIMPA OBJETO
            this.estabelecimento.getTelefone().get(3).setDdd(null);
            this.estabelecimento.getTelefone().get(3).setNumero(null);
            this.estabelecimento.getTelefone().get(3).setEstab(0);
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
        //LIMPA CAMPOS DE TEXTO
        tDDD4.setText("");
        tCont4.setText("");
        
    }//GEN-LAST:event_bDeleteCont4ActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(CadEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadEstabelecimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadEstabelecimento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bDeleteCont1;
    private javax.swing.JButton bDeleteCont2;
    private javax.swing.JButton bDeleteCont3;
    private javax.swing.JButton bDeleteCont4;
    private javax.swing.JButton bSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tBairro;
    private javax.swing.JTextField tCep;
    private javax.swing.JTextField tCidade;
    private javax.swing.JTextField tCnpj;
    private javax.swing.JTextField tCont1;
    private javax.swing.JTextField tCont2;
    private javax.swing.JTextField tCont3;
    private javax.swing.JTextField tCont4;
    private javax.swing.JTextField tDDD1;
    private javax.swing.JTextField tDDD2;
    private javax.swing.JTextField tDDD3;
    private javax.swing.JTextField tDDD4;
    private javax.swing.JTextField tEstado;
    private javax.swing.JTextField tNome;
    private javax.swing.JTextField tNumero;
    private javax.swing.JTextField tPais;
    // End of variables declaration//GEN-END:variables
}
