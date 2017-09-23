/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladormemoriacentrallogica.model;

/**
 *
 * @author Wagner
 */
public enum TipoRegistrador {
    M (1,5000),
    D (16, 8000),
    R (32, 30000);
    private int tamanho;
    private int range;

    private TipoRegistrador(int tamanho, int range) {
        this.tamanho = tamanho;
        this.range = range;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getRange() {
        return range;
    }   
    
    public int getTamanhoByte(){      
        return (int) Math.ceil(tamanho/8);
    }
    
}
