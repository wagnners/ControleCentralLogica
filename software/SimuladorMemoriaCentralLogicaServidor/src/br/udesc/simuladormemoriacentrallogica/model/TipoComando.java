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
public enum TipoComando {
    LER (1, (short)0x30),
    ESCREVER (2, (short)031);
    private Short valor;
    private int indice;

    private TipoComando(int indice, Short valor) {
        this.valor = valor;
    }

    public Short getValor() {
        return valor;
    }
    
    
}
