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
    }
     
    public List<Integer> compress(String uncompressed) {
        int dictSize = dictionary.size();
        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }
 
        if (!w.equals(""))
            result.add(dictionary.get(w));
        return result;
    }
    
     public String decompress(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = dictionary.size();
        String w = "" + (char)(int)compressed.remove(0);
        StringBuffer result = new StringBuffer(w);
        for (int k : compressed) {
            String entry;
            if (dictionaryd.containsKey(k))
                entry = dictionaryd.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
 
            result.append(entry);
 
            dictionaryd.put(dictSize++, w + entry.charAt(0));
 
            w = entry;
        }
        return result.toString();
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
