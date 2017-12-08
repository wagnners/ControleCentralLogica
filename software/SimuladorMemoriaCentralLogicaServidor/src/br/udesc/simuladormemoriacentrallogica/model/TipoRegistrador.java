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

    private final int tamanho;
    private final int range;

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
        return (int) Math.ceil(tamanho/8.0);
    }

    static TipoRegistrador getTipoRegistrador(Short valor) {
        switch (valor) {
            case 0x4D:
                return TipoRegistrador.M;
            case 0x44:
                return TipoRegistrador.D;
            case 0x52:
                return TipoRegistrador.R;
        }

        return null;
    }

}
