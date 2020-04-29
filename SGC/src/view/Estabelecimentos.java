package view;

//IMPORTS
import control.Estabelecimento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Estabelecimentos extends javax.swing.JInternalFrame {

    public Estabelecimentos() {
        initComponents();
        
        //****Carrega tabela
        this.loadTable();
    }
    
    public void loadTable(){
        //Construção da tabela
        ArrayList<Estabelecimento> estabList;
        Estabelecimento estab = new Estabelecimento();
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"","ID", "Nome", "CNPJ", "Estado", "Cidade"}, 0){
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
        Map<String, String> filtros = new HashMap<String, String>();
        
        try{
            ArrayList<Map> listaEstab = estab.listaEstabelecimentos(filtros);
            listaEstab.forEach((var e) -> {
                boolean checkBox = false;
                modelo.addRow(new Object[]{
                    Boolean.FALSE,
                    e.get("id"),
                    e.get("nome"),
                    e.get("cnpj"),
                    e.get("estado"),
                    e.get("cidade")
                });
                //System.out.println(e);
            });
            tableEstabelecimento.setModel(modelo);
        }catch(SQLException ex){
            System.out.println("Erro ao listar tabelas: "+ex);
        }
    }
    
    public ArrayList<?> getAllSelected(){
        ArrayList<String> selected = new ArrayList<String>();
        for(int x = 0; x < tableEstabelecimento.getRowCount(); x++){
            try{
                if((Boolean)tableEstabelecimento.getValueAt(x, 0)) selected.add((String)tableEstabelecimento.getValueAt(x, 1));
            }catch(Exception e){
                System.out.println(e);
            }
        }
        return selected;
    }
    
    public int getSelected(){
        int selected = -1;
        for(int x = 0; x < tableEstabelecimento.getRowCount(); x++){
            try{
                if((Boolean)tableEstabelecimento.getValueAt(x, 0)){
                    selected = Integer.parseInt((String)tableEstabelecimento.getValueAt(x, 1));
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
        return selected;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEstabelecimento = new javax.swing.JTable();
        bEditar = new javax.swing.JButton();
        bExcluir = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        bNovo = new javax.swing.JButton();
        bPesquisar = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jInternalFrame1.setBorder(null);
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tableEstabelecimento.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableEstabelecimento);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 71, 690, 340));

        bEditar.setText("Editar");
        bEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarActionPerformed(evt);
            }
        });
        getContentPane().add(bEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, 150, -1));

        bExcluir.setText("Excluir");
        bExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(bExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 150, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 650, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 150, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, -1));

        bNovo.setText("Novo");
        bNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNovoActionPerformed(evt);
            }
        });
        getContentPane().add(bNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 150, -1));

        bPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/procurar_24px.png"))); // NOI18N
        bPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bPesquisarMouseClicked(evt);
            }
        });
        getContentPane().add(bPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 35, -1, -1));

        setBounds(0, 0, 730, 520);
    }// </editor-fold>//GEN-END:initComponents

    private void bExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcluirActionPerformed
        /****
        #####################
        MUDAR AVISOS DE ERROS
        #####################
        ****/
        Estabelecimento estab;
        ArrayList<?> selected = this.getAllSelected();
        boolean allExcluded = true;
        for(int x = 0; x < selected.size(); x++){
            try{
                estab = new Estabelecimento(Integer.parseInt((String)selected.get(x)));
                int error = estab.excluirEstabelecimento();
                switch(error){                
                    case 0:
                        System.out.println("Error: "+0);
                        allExcluded = false;
                        break;
                    case 1:
                        System.out.println("Excluido com sucesso");
                        break;
                    case -1:
                        System.out.println("Erro inexperado");
                        allExcluded = false;
                        break;
                    default:
                        System.out.println("Erro inexperado não mapeado");
                        allExcluded = false;
                        break;
                          
                }
            }catch(SQLException ex){
                allExcluded = false;
                System.out.println(ex);
            }
        }
        this.loadTable();
        if(allExcluded){
            JOptionPane.showMessageDialog(rootPane,
                                          "Dados excluidos com sucesos",
                                          "Sucesso",
                                          JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(rootPane,
                                          "Não foi possível excluir todos os dados",
                                          "ERRO",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bExcluirActionPerformed

    private void bPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bPesquisarMouseClicked
        System.out.println("Vai relizar a pesquisa");
    }//GEN-LAST:event_bPesquisarMouseClicked

    private void bNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNovoActionPerformed
        CadEstabelecimento cadEstab = new CadEstabelecimento(this);
        cadEstab.setVisible(true);
    }//GEN-LAST:event_bNovoActionPerformed

    private void bEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarActionPerformed
        int id = this.getSelected();
        switch(id){
            case -1:
                JOptionPane.showMessageDialog(rootPane,
                                          "Selecione o registro resgistrado para poder edita-lo",
                                          "Nenhum registro selecionado",
                                          JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                {
                    try{
                    CadEstabelecimento cadEstabelecimento = new CadEstabelecimento(this, id);
                        cadEstabelecimento.setVisible(true);
                    }catch(SQLException ex){
                        JOptionPane.showMessageDialog(rootPane,
                                          "Occoreu um erro ao tentar abrir a tela de edição. \nPor favor contate o superte!",
                                          "ERRO",
                                          JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
        }
    }//GEN-LAST:event_bEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEditar;
    private javax.swing.JButton bExcluir;
    private javax.swing.JButton bNovo;
    private javax.swing.JLabel bPesquisar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableEstabelecimento;
    // End of variables declaration//GEN-END:variables
}
