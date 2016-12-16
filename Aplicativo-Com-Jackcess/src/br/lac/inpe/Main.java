
package br.lac.inpe;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Main extends JFrame{
    public Main(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JComboBox jComboBox;
        ItemHandler itemHandler = new ItemHandler();
        String[] tabelas = null; 
        ImageIcon icon = new ImageIcon("inpe.png");
        JLabel jLabel1 = new JLabel(icon);
        JLabel jLabel2 = new JLabel("Escolha uma Tabela:");
        
        
        /***************************************************************************************************
         ***************************************************************************************************
         ***************************************************************************************************
         *************************************************************************************************** */
        
        try {
            Database banco = DatabaseBuilder.open(new File("Academxp.mdb"));
            Set<String> todasTabelas = banco.getTableNames();
            /********************************************************************************
             ********************************************************************************/
            tabelas = new String[todasTabelas.size()];
            int contador = 0;    
            for(String tabela : todasTabelas)
                tabelas[contador++] = tabela;
            /********************************************************************************
             ********************************************************************************/
        }catch (IOException e) {
        }
        
        /***************************************************************************************************
         ***************************************************************************************************
         ***************************************************************************************************
         *************************************************************************************************** */
        if(tabelas != null)
            jComboBox = new JComboBox(tabelas);
        else
            jComboBox = new JComboBox();
        
        jComboBox.setBounds(25, 40, 400, 50);
        jComboBox.addItemListener(itemHandler);
        jLabel1.setBounds(30, 120, 400, 200);
        jLabel2.setBounds(25, 20, 200, 10);
        jPanel.add(jLabel1); 
        jPanel.add(jLabel2);
        jPanel.add(jComboBox);
        jPanel.setBackground(Color.WHITE);
        add(jPanel);
        this.setBounds(450, 450, 450, 400);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /***************************************************************************************************
     ***************************************************************************************************
     ***************************************************************************************************
     *************************************************************************************************** */
    
    public static void main(String[] args) {
        Main telaPrincipal = new Main();
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run(){
               telaPrincipal.setVisible(true);
            }
        });
    } 
    
    /***************************************************************************************************
    ***************************************************************************************************
    ***************************************************************************************************
    *************************************************************************************************** */
    
    private class ItemHandler implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent event){//getSource(): Object
            if(event.getStateChange() == ItemEvent.SELECTED){
                Object nomeTabela = event.getItem();
                new Tabela(String.valueOf(nomeTabela));
            }

        }
    }
    
     /***************************************************************************************************
     ***************************************************************************************************
     ***************************************************************************************************
     *************************************************************************************************** */
}

