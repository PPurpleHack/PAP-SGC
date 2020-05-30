package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel{
    
    private List<Object[]> dados = new ArrayList<>();
    private String[] colunas;
    
    public TableModel(String[] colunas){
        this.colunas = colunas;
    }
    
    @Override
    public String getColumnName(int column){
        return colunas[column];//Get te column name for show this in the window
    }
    
    @Override
    public int getRowCount() {
        //Retorna o tamanho da lista
        return this.dados.size();
    }

    @Override
    public int getColumnCount() {
        //Retorna o tamanho das colunas
        return this.colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dados.get(rowIndex)[columnIndex];
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        this.dados.get(rowIndex)[columnIndex] = value;
    }
    
    @Override
    //Torna editavel ou não
    public boolean isCellEditable(int row, int column){
        return column == 0;
    }
    
    //Para o check box
    @Override
    public Class<?> getColumnClass(int columnIndex){
        return dados.get(0)[columnIndex].getClass();
    }
    
    public void addRow(Object[] row){
        this.dados.add(row);
        this.fireTableDataChanged();
    }
    
    public void removeRow(){
        int index = 0;
        while(true){
            index = this.getFirstSelected();
            if(index == -1) break;
            this.dados.remove(index);
        }
        this.fireTableDataChanged();
    }
    
    public void removeRow(ArrayList<Integer> ids){
        int index = 0;
        boolean flag = false;
        while(true){
            index = this.getFirstSelected();
            if(index == -1) break;
            else{
                for(Integer id: ids){
                    if(this.getValueAt(index, 1).equals(Integer.toString(id))){
                        flag = true;
                        this.dados.remove(index);
                    }
                }
                if(!flag)this.setValueAt(Boolean.FALSE, index, 0);
                flag = false;
            }
        }
        this.fireTableDataChanged();
    }
    
    public void updateRow(Integer x, Object[] dado){
        System.out.println("Entrou aq");
        this.dados.set(x, dado);
        this.fireTableDataChanged();
    }
    
    public ArrayList<Integer> getIdSelected(){
        ArrayList<Integer> selected = new ArrayList<>();
        for(int x = 0; x < this.getRowCount(); x++){
            try{
                if((Boolean)this.getValueAt(x, 0)) selected.add(Integer.parseInt((String)this.getValueAt(x, 1)));
            } catch(NumberFormatException e){
                System.out.println(e);
            }
        }
        return selected;
    }
    
    public int getFirstSelected(){
        for(int x = 0; x < this.getRowCount(); x++) if((Boolean)this.getValueAt(x, 0)) return x;
        return -1;
    }
    
    public int getIndexById(int id){
        for(int x = 0; x < this.getRowCount(); x++) if(Integer.parseInt((String)this.getValueAt(x, 1)) == id) return x;
        return -1;
    }
    
    public void setAllUnselect(){
        for(int x = 0; x < this.getRowCount(); x++) this.setValueAt(Boolean.FALSE, x, 0);
        this.fireTableDataChanged();
    }
}
