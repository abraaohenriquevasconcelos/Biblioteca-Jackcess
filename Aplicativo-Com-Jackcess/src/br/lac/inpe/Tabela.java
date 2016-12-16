
package br.lac.inpe;

import com.healthmarketscience.jackcess.Column;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tabela extends JFrame{
    public Tabela(String nomeTabela){
        super(nomeTabela);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1,1));
        JTable jTable = new JTable();
        ArrayList linhas = new ArrayList();
        String[] colunas= null;
        
        
        /***************************************************************************************************
         ***************************************************************************************************
         ***************************************************************************************************
         *************************************************************************************************** */
        
        try {
            Table tabela = DatabaseBuilder.open(new File("Academxp.mdb")).getTable(nomeTabela);
            for(Row linha : tabela)
                linhas.add(linha.values().toArray());
            
            colunas = new String[tabela.getColumnCount()];
            List<? extends Column> todasColunas = tabela.getColumns();
            int contadorColunas=0;
            for(Column coluna : todasColunas)
                colunas[contadorColunas++] = coluna.getName();
            
        }catch (IOException e) {
        }
        
        /***************************************************************************************************
         ***************************************************************************************************
         ***************************************************************************************************
         *************************************************************************************************** */

        ModeloTabela modeloTabela = new ModeloTabela(linhas, colunas);
        jTable.setModel(modeloTabela);
        //jTable.getColumnModel().getColumn(0).setPreferredWidth(89);
        //jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
       
        
        /***************************************************************************************************
         ***************************************************************************************************
         ***************************************************************************************************
         *************************************************************************************************** */
        
        JScrollPane jScrollPane = new JScrollPane(jTable);
        jPanel.add(jScrollPane);
        
        /***************************************************************************************************
         ***************************************************************************************************
         ***************************************************************************************************
         *************************************************************************************************** */
       
        getContentPane().add(jPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    

}
