
package br.inpe.lac.testes;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Passo2 {
    private static  Database banco;
    
    public Passo2(){
        try{
           banco = DatabaseBuilder.open(new File("Academxp.mdb")); 
        }catch(IOException | NullPointerException e){
            e.printStackTrace();
        }
    }
    
    
    public void pegaAlunosPeloRegistroDoDocente(String registroDocente){
        List<String> registrosAlunos = new ArrayList<>();
        TreeMap<String, String> alunos = new TreeMap<>();
        try {
            Table CURSO_AL = banco.getTable("CURSO_AL");
            Cursor cursor1  = CursorBuilder.createCursor(CURSO_AL);
            Cursor cursor2  = CursorBuilder.createCursor(CURSO_AL);
            Cursor cursor3  = CursorBuilder.createCursor(CURSO_AL);
            Cursor cursor4  = CursorBuilder.createCursor(CURSO_AL);
            
            System.out.println("Coluna O_PESQ1 - Cursor 1\n");
            while(cursor1.findNextRow(Collections.singletonMap("O_PESQ1", registroDocente)) ) {
                Object o = cursor1.getCurrentRowValue(CURSO_AL.getColumn("REG_ALUNO"));
                String registroAluno = formataRegistroAluno(String.valueOf(o));
                System.out.println("Registro do aluno: "+registroAluno);
                registrosAlunos.add(registroAluno);
            }
            
            System.out.println("\n\n***********************************************************************\n\nColuna O_PESQ2 - Cursor 2\n");
            while(cursor2.findNextRow(Collections.singletonMap("O_PESQ2", registroDocente))){
               Object o = cursor2.getCurrentRowValue(CURSO_AL.getColumn("REG_ALUNO"));
               String registroAluno = formataRegistroAluno(String.valueOf(o));
               System.out.println("Registro do aluno: "+registroAluno);
               registrosAlunos.add(registroAluno);
            }
            
            System.out.println("\n\n***********************************************************************\n\nColuna O_PESQ3 - Cursor 3\n");
            while(cursor3.findNextRow(Collections.singletonMap("O_PESQ3", registroDocente))){
                Object o = cursor3.getCurrentRowValue(CURSO_AL.getColumn("REG_ALUNO"));
                String registroAluno = formataRegistroAluno(String.valueOf(o));
                System.out.println("Registro do aluno: "+registroAluno);
                registrosAlunos.add(registroAluno);
            }
            
            System.out.println("\n\n***********************************************************************\n\nColuna O_PESQ4 - Cursor 4\n");
            while(cursor4.findNextRow(Collections.singletonMap("O_PESQ4", registroDocente))){
                Object o = cursor4.getCurrentRowValue(CURSO_AL.getColumn("REG_ALUNO"));
                String registroAluno = formataRegistroAluno(String.valueOf(o));
                System.out.println("Registro do aluno: "+registroAluno);
                registrosAlunos.add(registroAluno);
            }
            
            /*
             *********************************************************************************************************
             *********************************************************************************************************
             *********************************************************************************************************
            */
            
            Table GDRPESS = banco.getTable("GDRPESS");
          
            for(String registro : registrosAlunos){
                for(Row linha : GDRPESS){
                    if(registro.equals(formataRegistroAluno(String.valueOf(linha.get("REG_ALUNO"))))){
                        alunos.put(String.valueOf(linha.get("NOME")), registro);
                    }
                }
            }
            System.out.println("\n\nResultados\n");
            System.out.println("Lista de Registros: "+registrosAlunos.toString());
            System.out.println("\nMapa Final: "+alunos.toString());
        }catch(IOException e) {
            Logger.getLogger(Passo2.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
    
    
    
    public String formataRegistroAluno(String s){
        double d = Double.parseDouble(s);
        StringBuilder sb = new StringBuilder(String.format("%11.0f", d).trim());
        
        while(sb.length() < 5)
            sb.insert(0, '0');
        //sb.insert(sb.length()-4,"/");
        
        return sb.toString();
    }
    
    
}
