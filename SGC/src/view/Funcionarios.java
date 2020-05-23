package view;

//IMPORTS
import control.Funcionario;
import control.Estabelecimento;
import model.ComboBox;
import model.TableModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Funcionarios extends javax.swing.JInternalFrame {

    private final Main main;
    private final TableModel tModel;
    
    //Componentes da tela
    private JComboBox cbEstabelecimento;
    
    public Funcionarios(Main main) {
        this.main = main;
        
        initComponents();
        
        //****Constroi tabela
        String[] colunas = {"","MATRICULA", "Nome", "Sobrenome", "CPF", "Email"};
        this.tModel = new TableModel(colunas);
        //****Carrega tabela
        this.loadTable();
        //****Seta modelo
        tableFuncionarios.setModel(this.tModel);
        
        //Configurações de aparecencia
        //Monta a adiciona dados a ComboBox de estabelecimento
        this.createComboBoxEstab();
        
        //Adiciona os itens a combo box função
        for(int x = 1; x <= 5; x++) cbFuncao.addItem("Funcao "+x);
        
        
    }
    
    public void createComboBoxEstab(){
        Vector model = new Vector();
        model.addElement(new ComboBox(0, "<Estabelecimentos>"));
        
        Estabelecimento estab = new Estabelecimento();
        try{
            ArrayList<ArrayList> lista = estab.listaEstabComboBox();
            for(ArrayList list: lista){
                model.addElement(new ComboBox(Integer.parseInt((String)list.get(0)), (String)list.get(1)));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane,
                                        "Desculpe, mas ocorreu um erro. Por favor contate o suporte tecnico.",
                                        "Erro ao lista ComboBox",
                                        JOptionPane.ERROR_MESSAGE);
        }
        
        
        this.cbEstabelecimento = new JComboBox(model);
        getContentPane().add(this.cbEstabelecimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));
        
    }
    
    public void loadTable(){
        //Carregamento da tabela
        Funcionario func = new Funcionario();
        Map<String, String> filtros = new HashMap<>();
        //Preencher os filtros
        try{
            ArrayList<Map> listaEstab = func.listaFuncionarios(filtros);
            listaEstab.forEach((Map e) -> {
                Funcionarios.this.tModel.addRow(new Object[]{
                    Boolean.FALSE,
                    e.get("matricula"),
                    e.get("nome"),
                    e.get("sobrenome"),
                    e.get("cpf"),
                    e.get("email")
                });
            });
        }catch(SQLException ex){
            System.out.println("Erro ao listar tabelas: "+ex);
        }
    }
    
    public void addFuncionario(Funcionario f){
        //Atualizar tabela quando for feito o cadastro de um novo funcionario
        this.tModel.addRow(new Object[]{Boolean.FALSE,f.getMatricula(),f.getNome(),f.getSobrenome(),f.getCpf(),f.getEmail()});
    }
    
    public void updateFuncInTheTable(Funcionario f){
        //Atualizar tabela quando for atualizado um funcionario ta lista
        Integer index = this.tModel.getIndexById(f.getMatricula());
        this.tModel.updateRow(index, new Object[]{
            Boolean.FALSE,
            Integer.toString(f.getMatricula()),//Na hora de pegar a matricula tem que tornar ela um String
            f.getNome(),
            f.getSobrenome(),
            f.getCpf(),
            f.getEmail()
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableFuncionarios = new javax.swing.JTable();
        bAddFuncionario = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        cbFuncao = new javax.swing.JComboBox<>();
        bPesquisar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        bEditFunc = new javax.swing.JButton();
        bTelaFuncao = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setBorder(null);
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tableFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableFuncionarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 690, 340));

        bAddFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/new_24px.png"))); // NOI18N
        bAddFuncionario.setText("Novo Funcionario");
        bAddFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddFuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(bAddFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 150, 70));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 610, -1));

        cbFuncao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Função>" }));
        getContentPane().add(cbFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 150, -1));

        bPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/procurar_24px.png"))); // NOI18N
        bPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPesquisarMouseClicked(evt);
            }
        });
        getContentPane().add(bPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 35, -1, -1));

        jLabel1.setText("Nome:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 42, -1, -1));

        bEditFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/edit_24px.png"))); // NOI18N
        bEditFunc.setText("Editar");
        bEditFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditFuncActionPerformed(evt);
            }
        });
        getContentPane().add(bEditFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 150, 31));

        bTelaFuncao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configuracao_32px.png"))); // NOI18N
        bTelaFuncao.setText("Tela Funções");
        bTelaFuncao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTelaFuncaoActionPerformed(evt);
            }
        });
        getContentPane().add(bTelaFuncao, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 350, 52));

        bExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        bExcluir.setText("Excluir");
        bExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(bExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 458, 150, 31));

        setBounds(0, 0, 730, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void bPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPesquisarMouseClicked
        System.out.println("Vai relizar a pesquisa");
    }//GEN-LAST:event_bPesquisarMouseClicked

    private void bAddFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddFuncionarioActionPerformed
        CadFuncionario cadFunc = new CadFuncionario(this);
        cadFunc.setVisible(true);
    }//GEN-LAST:event_bAddFuncionarioActionPerformed

    private void bEditFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditFuncActionPerformed
        CadFuncionario cadFunc;
        ArrayList<Integer> selected = this.tModel.getIdSelected();
        switch(selected.size()){
            case 0:
                JOptionPane.showMessageDialog(rootPane,"Selecione um item para edição","",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1:
                try{
                    cadFunc = new CadFuncionario(this, selected.get(0));
                    cadFunc.setVisible(true);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane,"Ocorreu um erro, por favor contate o superte tecnico.\n"+ex,"Erro",JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                JOptionPane.showMessageDialog(rootPane,"Só é possível editar até 1 itens","",JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        this.tModel.setAllUnselect();
    }//GEN-LAST:event_bEditFuncActionPerformed

    private void bTelaFuncaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTelaFuncaoActionPerformed
        //ABRE TELA DE FUNÇÕES
        this.main.fechaTelas();
        this.main.getFuncoes().setVisible(true);
    }//GEN-LAST:event_bTelaFuncaoActionPerformed

    private void bExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcluirActionPerformed
        Funcionario func = new Funcionario();
        Integer rs;
        ArrayList<Integer> selected = this.tModel.getIdSelected();
        
        rs = func.excluir(selected);
        switch(rs){
            case 1:
                this.tModel.removeRow();
                JOptionPane.showMessageDialog(rootPane,"Funcionario excluido com sucesso","Sucesso",JOptionPane.INFORMATION_MESSAGE);
                break;
            case 1451:
                JOptionPane.showMessageDialog(rootPane,"Não foi possível excluir esse funcionario, existem informações dependeste dele no sistema","Error",JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(rootPane,"Algo de arrado aconteceu. \nCódigo de erro: "+rs,"Error",JOptionPane.ERROR_MESSAGE);
                break;
        }
        this.tModel.setAllUnselect();
    }//GEN-LAST:event_bExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAddFuncionario;
    private javax.swing.JButton bEditFunc;
    private javax.swing.JButton bExcluir;
    private javax.swing.JLabel bPesquisar;
    private javax.swing.JButton bTelaFuncao;
    private javax.swing.JComboBox<String> cbFuncao;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableFuncionarios;
    // End of variables declaration//GEN-END:variables
}
