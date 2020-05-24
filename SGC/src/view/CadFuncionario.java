package view;

import control.Funcionario;
import control.TelefoneFuncionario;
import control.Estabelecimento;
import control.Funcao;
import model.ComboBox;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Vector;
import java.util.ArrayList;

public class CadFuncionario extends javax.swing.JFrame {

    private boolean errorOfValues;
    private Funcionarios telaFuncionarios;
    private Funcionario funcionario;
    
    public CadFuncionario() {
        this.errorOfValues = false;
        
        this.setUndecorated(true);
        initComponents();
        tMatricula.setEditable(false);
    }
    
    //QUANDO FOR UM CADASTRO
    public CadFuncionario(Funcionarios telaFuncionarios){
        this.errorOfValues = false;
        this.telaFuncionarios = telaFuncionarios;
        this.funcionario = new Funcionario();
        
        this.setUndecorated(true);
        initComponents();
        
        //Edita campo da matricula
        tMatricula.setEditable(false);
        
        //Cria o combo box referente aos tipos do contato
        this.createComboBoxTipoCont();
    }
    
    //QUANDO FOR UMA EDIÇÃO
    public CadFuncionario(Funcionarios telaFuncionarios, int id)throws SQLException{
        this.errorOfValues = false;
        this.telaFuncionarios = telaFuncionarios;
        this.funcionario = new Funcionario(id);
        
        this.setUndecorated(true);
        initComponents();
        
        //Edita campo da matricula
        tMatricula.setEditable(false);
        
        //Cria o combo box referente aos tipos do contato
        this.createComboBoxTipoCont();
        
        //Carrega campos
        this.carregaCampos();
    }
    
    private void carregaCampos(){
        String matricula = "000"+Integer.toString(this.funcionario.getMatricula());
        tMatricula.setText(matricula.substring(matricula.length() - 4));//ARRUMA O FORMATO DA MATRICULA
        
        tNome.setText(this.funcionario.getNome());
        tSobrenome.setText(this.funcionario.getSobrenome());
        tCPF.setText(this.funcionario.getCpf());
        tCEP.setText(this.funcionario.getCep());
        tCidade.setText(this.funcionario.getCidade());
        tNumero.setText(Integer.toString(this.funcionario.getNumero()));
        tBairro.setText(this.funcionario.getBairro());
        tPais.setText(this.funcionario.getPais());
        tEstado.setText(this.funcionario.getEstado());
        tEmail.setText(this.funcionario.getEmail());
        
        System.out.println(this.funcionario.getTelefone());
        //PREENCHE OS TELEFONES
        try{
            //PRIMEIRO NÚMERO DE CONTATO
            tDDD1.setText("("+this.funcionario.getTelefone().get(0).getDdd()+")");
            String cont1 = this.funcionario.getTelefone().get(0).getNumero();
            //VERIFICA SE TEM 9 OU 8 DIGITOS
            if(cont1.length() == 9){
                tCont1.setText(cont1.substring(0, 1)+" "+cont1.substring(1, 5)+"-"+cont1.substring(5));
            } else {
                tCont1.setText(cont1.substring(0, 4)+"-"+cont1.substring(4));
            }
            //MARCA QUAL O TIPO DO TELEFONE
            cbTipoCont1.setSelectedIndex(1);
            if(this.funcionario.getTelefone().get(0).isCelular())cbTipoCont1.setSelectedIndex(2);
        }catch(Exception ex){
            System.out.println(cbTipoCont1.getSelectedIndex());
            System.out.println("Ignorar: "+ex);
        }
        try{
            //SEGUNDO NÚMERO DE CONTATO
            tDDD2.setText("("+this.funcionario.getTelefone().get(1).getDdd()+")");
            String cont2 = this.funcionario.getTelefone().get(1).getNumero();
            //VERIFICA SE TEM 9 OU 8 DIGITOS
            if(cont2.length() == 9){
                tCont2.setText(cont2.substring(0, 1)+" "+cont2.substring(1, 5)+"-"+cont2.substring(5));
            } else {
                tCont2.setText(cont2.substring(0, 4)+"-"+cont2.substring(4));
            }
            //MARCA QUAL O TIPO DO TELEFONE
            cbTipoCont2.setSelectedIndex(1);
            if(this.funcionario.getTelefone().get(1).isCelular())cbTipoCont2.setSelectedIndex(2);
        }catch(Exception ex){
            System.out.println("Ignorar: "+ex);
        }
        
        //PREENCHE OS CAMPOS QUE SÃO EM COMBO BOX
        try{
            Funcao funcao = new Funcao(this.funcionario.getFuncao());
            cbFuncao.getModel().setSelectedItem(new ComboBox(funcao.getIdFuncao(), funcao.getDescricao()));
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        try{
            Estabelecimento estab = new Estabelecimento(this.funcionario.getEstabelecimento());
            cbEstabelecimento.getModel().setSelectedItem(new ComboBox(estab.getId(), estab.getNome()));
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane, ex, "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void createComboBoxTipoCont(){
        cbTipoCont1.addItem("<TIPO>");
        cbTipoCont1.addItem("Tel");
        cbTipoCont1.addItem("Cel");
        
        cbTipoCont2.addItem("<TIPO>");
        cbTipoCont2.addItem("Tel");
        cbTipoCont2.addItem("Cel");
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
    
    private Vector createComboBoxFuncao(){
        Vector model = new Vector();
        model.add(new ComboBox(0, "<Funções>"));
        Funcao fun = new Funcao();
        
        try{
            ArrayList<ArrayList> lista = fun.listaFuncaoComboBox();
            for(ArrayList list: lista){
                model.addElement(new ComboBox(Integer.parseInt((String)list.get(0)), (String)list.get(1)));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane,
                                        "Ocorreu um erro. Por favor contate o suporte tecnico.",
                                        "Erro ao lista ComboBox",
                                        JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(model);
        return model;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tMatricula = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tSobrenome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tCEP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tCidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tBairro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tPais = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tEstado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tCPF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbFuncao = new javax.swing.JComboBox<>(this.createComboBoxFuncao());
        jLabel14 = new javax.swing.JLabel();
        cbEstabelecimento = new javax.swing.JComboBox<>(this.createComboBoxEstabelecimento());
        jLabel15 = new javax.swing.JLabel();
        tDDD1 = new javax.swing.JTextField();
        tCont1 = new javax.swing.JTextField();
        tCont2 = new javax.swing.JTextField();
        tDDD2 = new javax.swing.JTextField();
        bSalvar = new javax.swing.JButton();
        bCancelar = new javax.swing.JButton();
        cbTipoCont1 = new javax.swing.JComboBox<>();
        cbTipoCont2 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        tNumero = new javax.swing.JTextField();

        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(310, 545));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tMatricula.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/funcionarios_16px.png"))); // NOI18N
        jLabel1.setText("Funcionario");

        jLabel2.setText("Matricula:");

        tNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tNomeFocusLost(evt);
            }
        });

        jLabel3.setText("Nome: ");

        jLabel4.setText("Sobrenome:");

        tSobrenome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tSobrenomeFocusLost(evt);
            }
        });

        jLabel5.setText("CEP:");

        tCEP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tCEPFocusLost(evt);
            }
        });

        jLabel6.setText("Cidade:");

        tCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tCidadeFocusLost(evt);
            }
        });

        jLabel7.setText("Bairro:");

        tBairro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tBairroFocusLost(evt);
            }
        });

        jLabel8.setText("Pais:");

        tPais.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tPaisFocusLost(evt);
            }
        });

        jLabel9.setText("Estado:");

        tEstado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tEstadoFocusLost(evt);
            }
        });

        jLabel11.setText("Email:");

        tEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tEmailFocusLost(evt);
            }
        });
        tEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tEmailActionPerformed(evt);
            }
        });

        jLabel12.setText("CPF:");

        tCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tCPFFocusLost(evt);
            }
        });

        jLabel13.setText("Função:");

        cbFuncao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbFuncaoFocusLost(evt);
            }
        });

        jLabel14.setText("Estabelecimento:");

        cbEstabelecimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbEstabelecimentoFocusLost(evt);
            }
        });

        jLabel15.setText("Número de Contato:");

        tDDD1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tDDD1FocusLost(evt);
            }
        });

        tCont1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tCont1FocusLost(evt);
            }
        });

        tCont2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tCont2FocusLost(evt);
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

        cbTipoCont1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        cbTipoCont2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        jLabel16.setText("Número:");

        tNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tNumeroFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tCPF)
                    .addComponent(tNome)
                    .addComponent(tSobrenome)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(cbEstabelecimento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tEmail)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bCancelar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tMatricula))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tDDD2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                    .addComponent(tDDD1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tCont1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(tCont2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTipoCont1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTipoCont2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15)
                            .addComponent(jLabel11)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel14)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(tCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(tCidade))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(86, 86, 86)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tPais, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(96, 96, 96)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tEstado)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel9))
                                .addGap(0, 5, Short.MAX_VALUE))
                            .addComponent(tNumero, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(tMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tDDD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCont1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoCont1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tDDD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tCont2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoCont2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEstabelecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar)
                    .addComponent(bCancelar))
                .addGap(523, 523, 523))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 620));

        setSize(new java.awt.Dimension(311, 609));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tEmailActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_bCancelarActionPerformed

    private void tNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tNomeFocusLost
        try{
            if(!"".equals(tNome.getText()))this.funcionario.setNome(tNome.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tNomeFocusLost

    private void tSobrenomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tSobrenomeFocusLost
        try{
            if(!"".equals(tSobrenome.getText()))this.funcionario.setSobrenome(tSobrenome.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tSobrenomeFocusLost

    private void tDDD1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDDD1FocusLost
        try{
            tDDD1.setText((tDDD1.getText().replace("(", "")).replace(")", ""));
            if(!"".equals(tDDD1.getText())){
                switch(tDDD1.getText().length()){
                    case 3:
                        tDDD1.setText("("+tDDD1.getText()+")");
                        break;
                    case 2:
                        tDDD1.setText("(0"+tDDD1.getText()+")");
                        break;
                    default:
                        JOptionPane.showMessageDialog(rootPane,
                                                      "Digite um ddd válido",
                                                      "DDD inválido",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tDDD1FocusLost

    private void tCont1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tCont1FocusLost
        try{
            tCont1.setText((tCont1.getText().replace("-", "")).replace(" ", ""));
            if(!"".equals(tCont1.getText())){
                switch(tCont1.getText().length()){
                    case 8:
                        tCont1.setText(tCont1.getText().substring(0, 4)+"-"+tCont1.getText().substring(4));
                        break;
                    case 9:
                        tCont1.setText(tCont1.getText().substring(0, 1)+" "+tCont1.getText().substring(1, 5)+"-"+tCont1.getText().substring(5));
                        break;
                    default:
                        JOptionPane.showMessageDialog(rootPane,
                                                      "Digite um número válido",
                                                      "Numero inválido",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tCont1FocusLost

    private void tDDD2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tDDD2FocusLost
        try{
            tDDD2.setText((tDDD2.getText().replace("(", "")).replace(")", ""));
            if(!"".equals(tDDD2.getText())){
                switch(tDDD2.getText().length()){
                    case 3:
                        tDDD2.setText("("+tDDD2.getText()+")");
                        break;
                    case 2:
                        tDDD2.setText("(0"+tDDD2.getText()+")");
                        break;
                    default:
                        JOptionPane.showMessageDialog(rootPane,
                                                      "Digite um ddd válido",
                                                      "DDD inválido",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tDDD2FocusLost

    private void tCont2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tCont2FocusLost
        try{
            tCont2.setText((tCont2.getText().replace("-", "")).replace(" ", ""));
            if(!"".equals(tCont2.getText())){
                switch(tCont2.getText().length()){
                    case 8:
                        tCont2.setText(tCont2.getText().substring(0, 4)+"-"+tCont2.getText().substring(4));
                        break;
                    case 9:
                        tCont2.setText(tCont2.getText().substring(0, 1)+" "+tCont2.getText().substring(1, 5)+"-"+tCont2.getText().substring(5));
                        break;
                    default:
                        JOptionPane.showMessageDialog(rootPane,
                                                      "Digite um número válido",
                                                      "Numero inválido",
                                                      JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            }
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tCont2FocusLost

    private void tCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tCPFFocusLost
        try{
            if(!"".equals(tCPF.getText()))this.funcionario.setCpf(tCPF.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tCPFFocusLost

    private void tCEPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tCEPFocusLost
        try{
            if(!"".equals(tCEP.getText()))this.funcionario.setCep(tCEP.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tCEPFocusLost

    private void tCidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tCidadeFocusLost
        try{
            if(!"".equals(tCidade.getText()))this.funcionario.setCidade(tCidade.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tCidadeFocusLost

    private void tBairroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tBairroFocusLost
        try{
            if(!"".equals(tBairro.getText()))this.funcionario.setBairro(tBairro.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tBairroFocusLost

    private void tPaisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tPaisFocusLost
        try{
            if(!"".equals(tPais.getText()))this.funcionario.setPais(tPais.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tPaisFocusLost

    private void tEstadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tEstadoFocusLost
        try{
            if(!"".equals(tEstado.getText()))this.funcionario.setEstado(tEstado.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tEstadoFocusLost

    private void tEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tEmailFocusLost
        try{
            if(!"".equals(tEmail.getText()))this.funcionario.setEmail(tEmail.getText());
        }catch(Exception ex){
            System.out.println("Erro: "+ex);
        }
    }//GEN-LAST:event_tEmailFocusLost

    private void cbEstabelecimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbEstabelecimentoFocusLost
        ComboBox cb = (ComboBox)cbEstabelecimento.getSelectedItem();
        this.funcionario.setEstabelecimento(cb.getIndex());
    }//GEN-LAST:event_cbEstabelecimentoFocusLost

    private void cbFuncaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbFuncaoFocusLost
        ComboBox cb = (ComboBox)cbFuncao.getSelectedItem();
        this.funcionario.setFuncao(cb.getIndex());
    }//GEN-LAST:event_cbFuncaoFocusLost

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        try{
            //VERIFICA SE É CADASTRO OU EDIÇÃO
            if(this.funcionario.getMatricula() == 0){
                //QUANDO ENTRAR NO IF É UM CADASTRO
                //PREENCHE OS TELEFONES DO FUNCIONARIO
                boolean contato = false;//PARA VERIFICAR SE PELO MENOS UM CONTATO FOI PREENCHIDO
                ArrayList<TelefoneFuncionario> tel = new ArrayList();//PARA SETAR OS TELEFONES PREENCHIDOS
                //TENTA PREENCHER O PRIMEIRO CONTATO
                if(!"".equals(tDDD1.getText()) && !"".equals(tCont1.getText()) && cbTipoCont1.getSelectedIndex() != 0){
                    boolean tipo1;
                    tipo1 = cbTipoCont1.getSelectedIndex() != 1;
                    String ddd1 = (tDDD1.getText().replace("(", "")).replace(")", "");
                    String cont1 = (tCont1.getText().replace("-", "")).replace(" ", "");
                    tel.add(new TelefoneFuncionario(ddd1, cont1, tipo1));
                    contato = true;
                }
                //TENTA PREENCHER O SEGUNDO CONTATO
                if(!"".equals(tDDD2.getText()) && !"".equals(tCont2.getText()) && cbTipoCont2.getSelectedIndex() != 0){
                    boolean tipo2;
                    tipo2 = cbTipoCont2.getSelectedIndex() != 1;
                    String ddd2 = (tDDD2.getText().replace("(", "")).replace(")", "");
                    String cont2 = (tCont2.getText().replace("-", "")).replace(" ", "");
                    tel.add(new TelefoneFuncionario(ddd2, cont2, tipo2));
                    contato = true;
                }
                //VERIFICA SE FOI PREENCHIDO PELO MENOS UM NÚMERO COMO CONTATO
                if(contato){
                    this.funcionario.setTelefone(tel);//SETA O TELEFONE
                    //SE FOI PREENCHIDO CONTINUA O PROCESSO DE CADASTRO
                    if(this.funcionario.cadastrarFuncionario()){
                        JOptionPane.showMessageDialog(rootPane,
                                                "Funcionario "+this.funcionario.getMatricula()+" cadastrado com sucesso",
                                                "SUCESSO",
                                                JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                        this.telaFuncionarios.addFuncionario(this.funcionario);
                    } else {
                        JOptionPane.showMessageDialog(rootPane,
                                                "Algo errado aconteceu!",
                                                "ERRO",
                                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    //CASO NÃO TENHA SIDO PREENCHIDO NENHUM CONTATO INFORMA O USUARIO
                    JOptionPane.showMessageDialog(rootPane,
                                                "Informe pelo menos um contato",
                                                "Nenhum contato informado",
                                                JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                //QUANDO ENTRAR NO ELSE É UMA EDIÇÃO
                ArrayList<TelefoneFuncionario> tel = this.funcionario.getTelefone();
                try{
                    if(!"".equals(tDDD1.getText()) && !"".equals(tCont1.getText()) && cbTipoCont1.getSelectedIndex() != 0){
                        System.out.println("Entrou na edição");
                        try{
                            tel.get(0).setDdd((tDDD1.getText().replace("(", "")).replace(")", ""));
                            tel.get(0).setNumero((tCont1.getText().replace("-", "")).replace(" ", ""));
                            boolean isCelular1;
                            isCelular1 = cbTipoCont1.getSelectedIndex() != 1;
                            tel.get(0).setCelular(isCelular1);
                        }catch(Exception ex){
                            String ddd1 = (tDDD1.getText().replace("(", "")).replace(")", "");
                            String number1 = (tCont1.getText().replace("-", "")).replace(" ", "");
                            boolean isCelular1;
                            isCelular1 = cbTipoCont1.getSelectedIndex() != 1;
                            tel.add(new TelefoneFuncionario(ddd1, number1, isCelular1));
                        }
                    } else if(this.funcionario.getTelefone().get(0).getId() != 0){
                        System.out.println("Entrou na exclusão");
                        //SETA COM EXCLUSÃO
                        this.funcionario.getTelefone().get(0).setFunc(0);
                    }
                }catch(Exception ex){
                    System.out.println("Quando não tiver nenhum telefone pra ser cadastrado, editado ou excluido");
                }
                try{
                    if(!"".equals(tDDD2.getText()) && !"".equals(tCont2.getText()) && cbTipoCont2.getSelectedIndex() != 0){
                        System.out.println("Entrou na edição");
                        try{
                            tel.get(1).setDdd((tDDD2.getText().replace("(", "")).replace(")", ""));
                            tel.get(1).setNumero((tCont2.getText().replace("-", "")).replace(" ", ""));
                            boolean isCelular2;
                            isCelular2 = cbTipoCont2.getSelectedIndex() != 1;
                            tel.get(1).setCelular(isCelular2);
                        }catch(Exception ex){
                            String ddd2 = (tDDD2.getText().replace("(", "")).replace(")", "");
                            String number2 = (tCont2.getText().replace("-", "")).replace(" ", "");
                            boolean isCelular2;
                            isCelular2 = cbTipoCont2.getSelectedIndex() != 1;
                            tel.add(new TelefoneFuncionario(ddd2, number2, isCelular2));
                        }
                    } else if(this.funcionario.getTelefone().get(1).getId() != 0){
                        System.out.println("Entrou na exclusão");
                        //SETA COM EXCLUSÃO
                        this.funcionario.getTelefone().get(1).setFunc(0);
                    }
                }catch(Exception ex){
                    System.out.println("Quando não tiver nenhum telefone pra ser cadastrado, editado ou excluido");
                }
                if(this.funcionario.atualizar()){
                    JOptionPane.showMessageDialog(rootPane,
                                            "Funcionario "+this.funcionario.getMatricula()+" atualizado com sucesso",
                                            "SUCESSO",
                                            JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);
                    this.telaFuncionarios.updateFuncInTheTable(this.funcionario);
                } else {
                    JOptionPane.showMessageDialog(rootPane,
                                            "Algo errado aconteceu!",
                                            "ERRO",
                                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch(SQLException ex){
            //CASO NÃO TENHA SIDO PREENCHIDO NENHUM CONTATO INFORMA O USUARIO
            JOptionPane.showMessageDialog(rootPane,
                                        ex,
                                        "ERRO",
                                        JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_bSalvarActionPerformed

    private void tNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tNumeroFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tNumeroFocusLost
    
    
    
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
            java.util.logging.Logger.getLogger(CadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bSalvar;
    private javax.swing.JComboBox<String> cbEstabelecimento;
    private javax.swing.JComboBox<String> cbFuncao;
    private javax.swing.JComboBox<String> cbTipoCont1;
    private javax.swing.JComboBox<String> cbTipoCont2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextField tCEP;
    private javax.swing.JTextField tCPF;
    private javax.swing.JTextField tCidade;
    private javax.swing.JTextField tCont1;
    private javax.swing.JTextField tCont2;
    private javax.swing.JTextField tDDD1;
    private javax.swing.JTextField tDDD2;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tEstado;
    private javax.swing.JTextField tMatricula;
    private javax.swing.JTextField tNome;
    private javax.swing.JTextField tNumero;
    private javax.swing.JTextField tPais;
    private javax.swing.JTextField tSobrenome;
    // End of variables declaration//GEN-END:variables
}
