/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzwkapoc;
import java.util.*;
import jdk.nashorn.internal.objects.NativeArray;


import presentation.lzwform;

/**
 *
 * @author cristian-gil
 */
public class Lzwkapoc {

    public Map<String,Integer> dictionary = new HashMap<String,Integer>();
    public Map<Integer, String> dictionaryd = new HashMap<Integer,String>();
    
    public boolean setDictionary(String letra, int codigo){
        boolean kapoc =  false;
        
        if (this.dictionary.get(letra) ==  null){
            dictionary.put(letra, codigo);
            dictionaryd.put(codigo, letra);
            kapoc = true;
        }
        
        return kapoc;
    }
    
    public void cleanDictionary(){
        this.dictionary.clear();
        this.dictionaryd.clear();
    }
     
    public List<Integer> compress(String uncompressed) throws Exception {
        int dictSize = dictionary.size();
        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        Map<String,Integer> dictionarytemp = new HashMap<String,Integer>(dictionary);;
        
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionarytemp.containsKey(wc))
                w = wc;
            else {
                if (dictionarytemp.get(""+c) != null){
                    int kapoc = dictionarytemp.get(w); 
                    result.add(kapoc);
                    dictionarytemp.put(wc,dictSize++);
                    w = "" + c;
                }else{
                    throw new Exception("La letra |"+ c + "| no se encuentra en el diccionario");
                }   
            }
        }
 
        if (!w.equals(""))
            result.add(dictionarytemp.get(w));
        return result;
    }
    
     public String decompress(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = dictionaryd.size();
        String w = "" + getCharByCode((int)compressed.remove(0));
        StringBuffer result = new StringBuffer(w);
        Map<Integer, String> dictionarytemp =  new HashMap<Integer,String>(dictionaryd);
        
        for (int k : compressed) {
            String entry;
            
            if (dictionarytemp.containsKey(k)){
                entry = dictionarytemp.get(k);
            }   
            else if (k == dictSize){
                entry = w + w.charAt(0);
            }
            else{
                throw new IllegalArgumentException("No se encuentra la letra: " + k);
            }
                
            result.append(entry);
            dictionarytemp.put(dictSize++, w + ""+entry.charAt(0));
 
            w = entry;
        }
        return result.toString();
    }
    
     private char getCharByCode(int code){
        return dictionaryd.get(code).charAt(0);
     } 
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(lzwform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(lzwform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(lzwform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lzwform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lzwform().setVisible(true);
            }
        });
    }
    
}
