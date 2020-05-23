package model;

public class ComboBox {
    
    private int index;
    private String value;
    
    public ComboBox(int a, String b){
        this.index = a;
        this.value = b;
    }

    @Override
    public String toString() {
        return value;
    }
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
