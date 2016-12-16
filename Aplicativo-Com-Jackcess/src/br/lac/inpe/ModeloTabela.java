
package br.lac.inpe;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ModeloTabela extends AbstractTableModel{
    private ArrayList linhas;
    private String[] colunas;
    
    public ModeloTabela(ArrayList linhas, String[] colunas){
        this.linhas = linhas;
        this.colunas = colunas;
    }

    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }
    
    public int getColumnCount(){
        return colunas.length;
    }
    
    public int getRowCount(){
        return linhas.size();
    }
    
    public String getColumnName(int numColuna){
        return colunas[numColuna];
    }
    
    public Object getValueAt(int numLinha, int numColuna){
        Object[] linha = (Object[]) getLinhas().get(numLinha);
        return linha[numColuna];
    }
}
