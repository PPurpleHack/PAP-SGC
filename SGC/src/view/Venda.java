package view;
import model.TableModel;
import control.Produto;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Venda extends javax.swing.JFrame {

    private int estabelecimento;
    private ArrayList<Map> listaProduto;
    private Map produto;
    private TableModel tModel;
    
    public Venda(int estabelecimento) {
        initComponents();
        
        this.estabelecimento = estabelecimento;
        this.listaProduto = new ArrayList();
        this.produto = new HashMap();
        
        //****Constroi tabela
        String[] colunas = {"Codigo", "Nome", "Quantidade disponível", "Preço", "Preço Atacado", "Quantidade Atacado"};
        this.tModel = new TableModel(colunas);
        //****Seta modelo
        tableItem.setModel(this.tModel);
    }
    
    private void alteraTabela(Map i){
        try{
            this.tModel.removeRow(0);
        }catch(Exception ex){
            System.out.println(ex);
        }
        System.out.println("Chegou aqui");
        this.tModel.addRow(new Object[]{
            i.get("codigo"),
            i.get("nome"),
            i.get("quantidade"),
            i.get("precoVarejo"),
            i.get("precoAtacado"),
            i.get("atacado")
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tItens = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tTotal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tCod = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bOk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableItem = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        bAdd = new javax.swing.JButton();
        close = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 4));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(224, 217, 187));

        tItens.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Itens");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tItens, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(tItens, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 260, 450));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("TOTAL:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(574, 474, 100, 50));

        tTotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tTotal.setText("00,00");
        jPanel1.add(tTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 485, 110, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("R$ ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 485, -1, -1));
        jPanel1.add(tCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 410, -1));

        jLabel3.setText("Item:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        bOk.setText("Ok");
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });
        jPanel1.add(bOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, -1, -1));

        tableItem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableItem);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 540, 140));

        jButton2.setText("Finalizar");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 473, 510, 40));

        bAdd.setText("Adicionar");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });
        jPanel1.add(bAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 540, 40));

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close_16px.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        close.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                closeKeyPressed(evt);
            }
        });
        jPanel1.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 535));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void closeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_closeKeyPressed
        //Codigo morto
    }//GEN-LAST:event_closeKeyPressed

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        this.dispose();
    }//GEN-LAST:event_closeMouseClicked

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkActionPerformed
        String item = tCod.getText();
        try{
            if(!item.equals("")){
                Produto p = new Produto();
                Map i = p.listItem(this.estabelecimento, item);
                this.produto = i;
                this.alteraTabela(i);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_bOkActionPerformed

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        try{
            int qtd = Integer.parseInt(JOptionPane.showInputDialog(rootPane, "Quantidade"));
            this.produto.put("qtdSelecionado", qtd);
            if(qtd >= (Integer)this.produto.get("atacado")){
                this.produto.put("precoAPagar", qtd * (Double)this.produto.get("precoVarejo"));
            }else{
                this.produto.put("precoAPagar", qtd * (Double)this.produto.get("precoVarejo"));
            }
            this.listaProduto.add(this.produto);
            String[] listaRemover = new String[]{   
                "<html>",
                "<body>",
                "<div style=\"overflow-y: scroll;width:240;border:1px solid red;\">",
                "</div>",
                "</body>",
                "</html>"};
            String lista = tItens.getText();
            for(String r: listaRemover){
                lista = lista.replace(r, "");
            }
            String insere = lista+produto.get("codigo")+" "+produto.get("nome")+" R$"+this.produto.get("precoAPagar")+"<br>";
            tItens.setText(
                    "<html>"
                  + "<body>"
                      + "<div style=\"overflow-y: scroll;width:240;border:1px solid red;\">"+insere+"</div>"
                      + "</body>"
                  + "</html>");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_bAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bOk;
    private javax.swing.JLabel close;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tCod;
    private javax.swing.JLabel tItens;
    private javax.swing.JLabel tTotal;
    private javax.swing.JTable tableItem;
    // End of variables declaration//GEN-END:variables
}
