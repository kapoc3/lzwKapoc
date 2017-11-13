/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unipiloto.edu.co.controller;

import com.unipiloto.edu.co.model.LzwModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kapoc_laptop
 */
public class Controller {
    LzwModel model = new LzwModel();
    
    
    public String decompress(List<Integer> compressed) {
        return model.decompress(compressed);
    }
    
    public List<Integer> compress(String uncompressed) throws Exception {
        return model.compress(uncompressed);
    }
    
    public boolean setDictionary(String letra, int codigo){
        return model.setDictionary(letra, codigo);
    }
    
    public void cleanDictionary(){
        model.cleanDictionary();
    }
    
    public LzwModel getModel(){
        return model;
    }
    
    
}
