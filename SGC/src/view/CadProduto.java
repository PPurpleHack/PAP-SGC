package view;

import control.Lote;
import control.Estabelecimento;
import control.Produto;
import control.Setor;
import model.ComboBox;
import java.sql.SQLException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class CadProduto extends javax.swing.JFrame {
    
    private Estoque estoque;
    private Lote lote;
    private String statusProduto;
    
    public CadProduto(){
        this.setUndecorated(true);
        initComponents();
        
        this.lote = new Lote();
        this.statusProduto = "oldProduto";
    }
    
    public CadProduto(Estoque estoque){
        this.setUndecorated(true);
        initComponents();
        
        this.lote = new Lote();
        this.estoque = estoque;
        this.statusProduto = "oldProduto";
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tLote = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbEstabelecimento = new javax.swing.JComboBox<>(this.createComboBoxEstabelecimento());
        bSalvar = new javax.swing.JButton();
        bClose = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tQuantidade = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tValorPago = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tDataVencimento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 9));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/produto_24px.png"))); // NOI18N
        jLabel1.setText("Entrada Estoque");

        jLabel2.setText("Código:");

        tCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tCodigoFocusLost(evt);
            }
        });

        jLabel3.setText("Lote:");

        tLote.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tLoteFocusLost(evt);
            }
        });

        jLabel4.setText("Estabelecimento:");

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

        bClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bClose.setText("Fechar");
        bClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCloseActionPerformed(evt);
            }
        });

        jLabel5.setText("Quantidade:");

        tQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tQuantidadeFocusLost(evt);
            }
        });

        jLabel6.setText("Valor Pago:");

        tValorPago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tValorPagoFocusLost(evt);
            }
        });

        jLabel7.setText("Data de Vencimento:");

        tDataVencimento.setText("dd/mm/yyyy");
        tDataVencimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tDataVencimentoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tDataVencimentoFocusLost(evt);
            }
        });
        tDataVencimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tDataVencimentoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tCodigo)
                    .addComponent(tLote)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bClose))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEstabelecimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tDataVencimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tQuantidade, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(tValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbEstabelecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bClose))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 420));

        setSize(new java.awt.Dimension(310, 420));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCloseActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bCloseActionPerformed

    private void tCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tCodigoFocusLost
        //CÓDIGO MORTO
    }//GEN-LAST:event_tCodigoFocusLost

    private void tDataVencimentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDataVencimentoFocusGained
        if(tDataVencimento.getText().equals("dd/mm/yyyy")) tDataVencimento.setText("");
    }//GEN-LAST:event_tDataVencimentoFocusGained

    private void tDataVencimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDataVencimentoFocusLost
        if(tDataVencimento.getText().equals("")){
            tDataVencimento.setText("dd/mm/yyyy");
        }else{
            this.lote.setDtmVencimento(tDataVencimento.getText());
        }
    }//GEN-LAST:event_tDataVencimentoFocusLost

    private void tLoteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tLoteFocusLost
        this.lote.setCodLote(tLote.getText());
    }//GEN-LAST:event_tLoteFocusLost

    private void tQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tQuantidadeFocusLost
        this.lote.setQtdProduto(Integer.parseInt(tQuantidade.getText()));
    }//GEN-LAST:event_tQuantidadeFocusLost

    private void tValorPagoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tValorPagoFocusLost
        this.lote.setVlrPago(Double.parseDouble(tValorPago.getText()));
    }//GEN-LAST:event_tValorPagoFocusLost

    private void tDataVencimentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tDataVencimentoKeyReleased
        int tamanho = tDataVencimento.getText().length();
        String data = tDataVencimento.getText().replace("/", "");
        switch(tamanho){
            case 3:
                tDataVencimento.setText(data.substring(0, 2)+"/"+data.substring(2));
                break;
            case 6:
                tDataVencimento.setText(data.substring(0, 2)+"/"+data.substring(2, 4)+"/"+data.substring(4));
                break;
        }
    }//GEN-LAST:event_tDataVencimentoKeyReleased

    private void cbEstabelecimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbEstabelecimentoFocusLost
        //CÓDIGO MORTO
    }//GEN-LAST:event_cbEstabelecimentoFocusLost

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        try{
            int estabelecimento = ((ComboBox)cbEstabelecimento.getSelectedItem()).getIndex();
            this.lote.setSituacao("ESTOQUE");
            //VERIFICAÇÕES PADRÕES
            if(tCodigo.getText().length() > 14){
                //VERIFICA SE E UM CODIGO VALIDO
                JOptionPane.showMessageDialog(rootPane, "Código invalido", "Código Invalido", JOptionPane.WARNING_MESSAGE);
            }else if(estabelecimento == 0){
                //VERIFICA SE O ESTABELECIMENTO É VÁLIDO
                JOptionPane.showMessageDialog(rootPane, "Selecione um estabelecimento", "Estabelecimento inexistente", JOptionPane.WARNING_MESSAGE);
            }else{
                //VERIFICA SE O CODIGO JA EXISTE NO ESTOQUE COM ESSE ESTABELECIMENTO
                Produto produto = new Produto();
                produto.setEstabelecimento(estabelecimento);
                produto.setCodProduto(tCodigo.getText());
                int estoque = produto.checkProduto(this);
                switch(estoque){
                    case 0:
                        //AINDA NÃO EXISTE REGISTRO DESSE PRODUTO NESSE ESTABELECIMENTO
                        int resposta = JOptionPane.showConfirmDialog(  rootPane,
                                                                        "Esse produto ainda não foi registrado para esta empresa.\n"
                                                                      + "É necessário fazer o cadastro do produto antes de registrar um lote.\n"
                                                                      + "(YES) -> Continua processo de cadastro\n"
                                                                      + "(NO) -> Desiste do processo",
                                                                        "Produto não registrado",
                                                                        JOptionPane.OK_OPTION,
                                                                        JOptionPane.INFORMATION_MESSAGE);
                        switch(resposta){
                            case 0:
                                //MUDA PRA QUANDO TERMINAR O CADASTRO INSERIR NA TABELA DO ESTOQUE
                                this.statusProduto = "newProduto";
                                boolean flagKeepGoing = true;
                                //COMEÇA O PROCESSO DE CADASTRO DO PRODUTO
                                JOptionPane.showMessageDialog(rootPane, "Vamos começar o cadastro.\nTodos os valores poderão ser alterados mais tarde.", "Inicio", JOptionPane.INFORMATION_MESSAGE);
                                while(true && flagKeepGoing){
                                    //PEGA O NOME DO PRODUTO
                                    String nomeProd = JOptionPane.showInputDialog(rootPane, "Digite o nome do produto");
                                    if(!nomeProd.equals("")){
                                        produto.setNome(nomeProd);
                                        break;
                                    }else{
                                        resposta = JOptionPane.showConfirmDialog(rootPane,
                                                                                "Esse campo é obrigatorio!\n"
                                                                              + "Para continuar o cadadastro aperte (YES).\n"
                                                                              + "Para cancelar apert (NO).",
                                                                                 "Nome não digitado",
                                                                                 JOptionPane.OK_OPTION,
                                                                                 JOptionPane.INFORMATION_MESSAGE);
                                        if(resposta == 1){
                                            this.setVisible(false);
                                            flagKeepGoing = false;
                                            break;
                                        }
                                    }
                                }
                                while(true && flagKeepGoing){
                                    //PEGA O PREÇO DO PRODUTO
                                    String precoProd = JOptionPane.showInputDialog(rootPane, "Digite o preço do produto");
                                    try{
                                        if(!precoProd.equals("")){
                                            produto.setPrecoVarejo(Double.parseDouble(precoProd));
                                            break;
                                        }else{
                                            resposta = JOptionPane.showConfirmDialog(rootPane,
                                                                                    "Esse campo é obrigatorio!\n"
                                                                                  + "Para continuar o cadadastro aperte (YES).\n"
                                                                                  + "Para cancelar apert (NO).",
                                                                                     "Preço não digitado",
                                                                                     JOptionPane.OK_OPTION,
                                                                                     JOptionPane.INFORMATION_MESSAGE);
                                            if(resposta == 1){
                                                this.setVisible(false);
                                                flagKeepGoing = true;
                                                break;
                                            }
                                        }
                                    }catch(NumberFormatException ex){
                                        resposta = JOptionPane.showConfirmDialog(rootPane,
                                                                                "Por favor digite um preço valido!\n"
                                                                              + "Para continuar o cadadastro aperte (YES).\n"
                                                                              + "Para cancelar apert (NO).",
                                                                                 "Valor invalido",
                                                                                 JOptionPane.OK_OPTION,
                                                                                 JOptionPane.INFORMATION_MESSAGE);
                                        if(resposta == 1){
                                            this.setVisible(false);
                                            flagKeepGoing = false;
                                            break;
                                        }
                                    }
                                }
                                while(true && flagKeepGoing){
                                    //PEGA O DESCONTO DO PRODUTO
                                    String descontoAtacado = JOptionPane.showInputDialog(rootPane, "Digite o desconto(%) do produto caso seja comprado em atacado. (Caso não tenha desconto digite 0)");
                                    try{
                                        if(!descontoAtacado.equals("")){
                                            produto.setDescontoAtacado(Double.parseDouble(descontoAtacado));
                                            break;
                                        }else{
                                            produto.setDescontoAtacado(0.00);
                                            break;
                                        }
                                    }catch(NumberFormatException ex){
                                        resposta = JOptionPane.showConfirmDialog(rootPane,
                                                                                "Por favor digite um desconto valido!\n"
                                                                              + "Para continuar o cadadastro aperte (YES).\n"
                                                                              + "Para cancelar apert (NO).",
                                                                                 "Valor invalido",
                                                                                 JOptionPane.OK_OPTION,
                                                                                 JOptionPane.INFORMATION_MESSAGE);
                                        if(resposta == 1){
                                            this.setVisible(false);
                                            flagKeepGoing = false;
                                            break;
                                        }
                                    }
                                }
                                while(true && flagKeepGoing){
                                    //PEGA QUANTIDADE PARA SER CONSIDERADO ATACADO
                                    String qtdAtacado = JOptionPane.showInputDialog(rootPane, "Digite a quantidade de produtos para ser considerado atacado.\n(Caso não tenha desconto digite 0)");
                                    try{
                                        if(!qtdAtacado.equals("")){
                                            produto.setQtdAtacado(Integer.parseInt(qtdAtacado));
                                            break;
                                        }else{
                                            produto.setQtdAtacado(0);
                                            break;
                                        }
                                    }catch(NumberFormatException ex){
                                        resposta = JOptionPane.showConfirmDialog(rootPane,
                                                                                "Por favor digite uma quantidade valida!\n"
                                                                              + "Para continuar o cadadastro aperte (YES).\n"
                                                                              + "Para cancelar apert (NO).",
                                                                                 "Valor invalido",
                                                                                 JOptionPane.OK_OPTION,
                                                                                 JOptionPane.INFORMATION_MESSAGE);
                                        if(resposta == 1){
                                            this.setVisible(false);
                                            flagKeepGoing = false;
                                            break;
                                        }
                                    }
                                }
                                //MONTA COMBO BOX DO SETOR
                                Map<String, String> filtros = new HashMap();
                                filtros.put("estabelecimento", Integer.toString(estabelecimento));
                                Setor setor = new Setor();
                                ArrayList<ComboBox> cbSetor = new ArrayList();
                                cbSetor.add(new ComboBox(0, "<Setores>"));
                                for(Map listaSetor: setor.lista(filtros)){
                                    cbSetor.add(new ComboBox(Integer.parseInt((String)listaSetor.get("id")), (String)listaSetor.get("nome")));
                                }
                                cbSetor.add(new ComboBox(-1, "<Cadastrar Novo>"));
                                boolean breakWhile = false;
                                while(!breakWhile && flagKeepGoing){
                                    ComboBox setRes = (ComboBox)JOptionPane.showInputDialog(null,
                                                                        "Selecione o setor do item\n"
                                                                      + "",
                                                                        "Setor",
                                                                        JOptionPane.PLAIN_MESSAGE,
                                                                        null,
                                                                        cbSetor.toArray(),
                                                                        "");
                                    switch(setRes.getIndex()){
                                        case 0:
                                            resposta = JOptionPane.showConfirmDialog(rootPane,
                                                                                    "Precisa ser informado um setor para continuar o cadastro!\n"
                                                                                  + "Para continuar o cadadastro aperte (YES).\n"
                                                                                  + "Para cancelar apert (NO).",
                                                                                     "Setor não informado",
                                                                                     JOptionPane.OK_OPTION,
                                                                                     JOptionPane.INFORMATION_MESSAGE);
                                            if(resposta == 1){
                                                this.setVisible(false);
                                                flagKeepGoing = false;
                                                breakWhile = true;
                                            }
                                            break;
                                        case -1:
                                            breakWhile = true;
                                            while(true){
                                                String nomeSetor = JOptionPane.showInputDialog(rootPane, "Digite o nome do setor que será cadastrado para o estabelecimento "+estabelecimento);
                                                if(!nomeSetor.equals("")){
                                                    Setor newSetor = new Setor();
                                                    newSetor.setNome(nomeSetor);
                                                    newSetor.setEstabelecimento(estabelecimento);
                                                    if(newSetor.salvar()){
                                                        JOptionPane.showMessageDialog(rootPane, "Setor "+nomeSetor+" cadastrado com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                                                        produto.setSetor(newSetor.getId());
                                                    }
                                                    break;
                                                }else{
                                                    resposta = JOptionPane.showConfirmDialog(rootPane,
                                                                                "Nenhum nome foi digitado!\n"
                                                                              + "Para cadastrar um novo setor aperte (YES).\n"
                                                                              + "Para voltar para a seleção de setores já cadastrados aperte (NO).",
                                                                                 "Setor não informado",
                                                                                 JOptionPane.OK_OPTION,
                                                                                 JOptionPane.INFORMATION_MESSAGE);
                                                    if(resposta == 1){
                                                        breakWhile = false;
                                                        break;
                                                    }
                                                }
                                            }
                                            
                                            break;
                                        default:
                                            produto.setSetor(setRes.getIndex());
                                            breakWhile = true;
                                            break;
                                            
                                    }
                                }
                                if(flagKeepGoing){
                                    JOptionPane.showMessageDialog(rootPane, "Tudo pronto para o cadastro do produto e registro do lote");
                                    produto.save();
                                    JOptionPane.showMessageDialog(rootPane, "Produto registrado com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                                    bSalvar.doClick();
                                }
                                break;
                            case 1:
                                //FECHA A TELA
                                this.setVisible(false);
                                break;
                        }
                        //JOptionPane.showInputDialog("");
                        break;
                    default:
                        //JÁ EXISTE REGISTRO DESSE PRODUTO NO ESTABELECIMENTO, CONTINUA O PROCESSO
                        this.lote.setEstoque(estoque);
                        if(this.lote.save() == 1){
                            JOptionPane.showMessageDialog(rootPane, "Lote "+this.lote.getCodLote()+" registrado com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            this.setVisible(false);
                            switch(this.statusProduto){
                                case "newProduto":
                                    this.estoque.addProdutoInTheTable(new Produto(this.lote.getEstoque()));
                                    break;
                                default:
                                    this.estoque.updateTable(new Produto(this.lote.getEstoque()));
                                    break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Ocorreu um erro durante o processo", "ERRO", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane, ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bSalvarActionPerformed

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClose;
    private javax.swing.JButton bSalvar;
    private javax.swing.JComboBox<String> cbEstabelecimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tCodigo;
    private javax.swing.JTextField tDataVencimento;
    private javax.swing.JTextField tLote;
    private javax.swing.JTextField tQuantidade;
    private javax.swing.JTextField tValorPago;
    // End of variables declaration//GEN-END:variables

    public void setStatusProduto(String statusProduto){
        this.statusProduto = statusProduto;
    }
}
