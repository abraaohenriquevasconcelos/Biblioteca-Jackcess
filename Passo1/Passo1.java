
package br.inpe.lac.testes;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.util.Collections;


public class Passo1 {
    public static void main(String[] args) {
        try{
            Database banco = DatabaseBuilder.open(new File("Academxp.mdb"));
            Table DOCENTE = banco.getTable("DOCENTE");
            
            /*
             *****************************************************************************************************
             *****************************************************************************************************
             *****************************************************************************************************
            */
            
            /*
             While a Cursor is the best way to interact with the data in a Table, for the sake of simplicity when 
             just getting started, we will use the simplified iteration provided by the Table class itself. When 
             reading row data, it is generally provided as a Row where the keys are the column names.
            */
            for(Row linha : DOCENTE){
                for(Column coluna : DOCENTE.getColumns()){
                    String nomeColuna = coluna.getName();
                    Object valor;
                    if(linha.get(nomeColuna) == null)
                        valor = "null";
                    else
                        valor = linha.get(nomeColuna);
                    
                    System.out.println("Coluna: "+nomeColuna);
                    System.out.println("Tipo-Coluna: "+coluna.getType());
                    System.out.println("Valor: "+valor);
                    System.out.println("Tipo-Valor: "+valor.getClass());
                    System.out.println("\n\n\n\n");
                }
            }
            
            /*
             *****************************************************************************************************
             *****************************************************************************************************
             *****************************************************************************************************
            */
            
            /*
             The main hurdle to writing data is figuring out how to get the data in the right columns. The primary
             method for adding a row to a table is the addRow(Object...) method. This method should be called with
             the appropriate, strongly typed Java object values in the order of the columns of the table. The order
             of the columns on the table instance may not be the same as the display order of the columns in Access.
             Additionally , when adding rows, we never provide a value for any "auto" columns. You can provide a value
             but it will be ignored(in the example below, we use a useful constant which makes the intent clear).
             
             So, assuming that the order of the columns on the table instance is "ID", "Name", "Salary", and "StartDate",
             this is how we would add a row 
            
             String name = "bob";
             BigDecimal salary = new BigDecimal("1000.00");
             Date startDate = new Date();
             table.addRow(Column.AUTO_NUMBER, name, salary, startDate);
            */
            
            
            /*
             *****************************************************************************************************
             *****************************************************************************************************
             *****************************************************************************************************
            */
            
            
            /*
             Finding Stuff
             Cursors, what are they good for?
             
             As mentioned earlier, Cursors are the best way to interact with data in a Table. A cursor is, in essence,
             the combination of a table and a "bookmark"(marcador) pointing(apontando) to a Row of data in the table.
             The various Cursor operations either move the bookmark around within the table or provide read/write ope
             rations on the current row. 
            
             The simplest case involves a normal, un-indexed cursor for a given table. The cursor will traverse the 
             table in no particular row order, but it can still be used to find rows where column(s) match specified
             values.
            */
            
            Cursor cursor = CursorBuilder.createCursor(DOCENTE);
            boolean encontrado = cursor.findFirstRow(Collections.singletonMap("REG_DOCENT", 2.0)); //singletonMap(K key, V value):Map<K,V> 
            
            if(encontrado)
                System.out.println(String.format("Linha encontrada: '%s'.", cursor.getCurrentRowValue(DOCENTE.getColumn("NOME_DOCENTE"))));
            else
                System.out.println ("Nenhuma linha correspondente foi encontrada.");
            
            /*
             ...will search for the row where "REG_DOCENT" = 2.0. Since(já que/como) the cursor doesn't use an index
             it will perform the equivalent of a "table scan" while searching.
             */
            
            
            /*
             *****************************************************************************************************
             *****************************************************************************************************
             *****************************************************************************************************
            */
            
            //https://translate.googleusercontent.com/translate_c?depth=1&hl=pt-BR&prev=search&rurl=translate.google.com.br&sl=en&u=http://jackcess.sourceforge.net/cookbook.html&usg=ALkJrhg1dyLt2pPKkNtFnm-NE47-1-bRnA
            
        }catch(IOException | NullPointerException e){
            e.printStackTrace();
        }
                
            
          
    }
}
