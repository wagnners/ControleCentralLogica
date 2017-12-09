/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladormemoriacentrallogica.model;

import java.util.Arrays;

/**
 *
 * @author Wagner
 */
public class Registrador {

    private final TipoRegistrador tipo;

    private final Short[] valor;

    public Registrador(TipoRegistrador tipo) {
        this.tipo = tipo;
        valor = new Short[tipo.getRange()];
    }

    public TipoRegistrador getTipo() {
        return tipo;
    }

    public void grava(int indice, Short valor) {
        this.valor[indice] = valor;
    }

    public void grava(int indice, Short[] valor) throws ValorInvalido {
        if (tipo == TipoRegistrador.M) {
            for (int i = 0; i < valor.length; i++) {
                if (valor[i] != null && valor[i] > 1) {
                    throw new ValorInvalido();
                }
            }
        }
        System.arraycopy(valor, 0, this.valor, indice, valor.length);
    }

    public Short le(int indice) {
        return valor[indice];
    }

    public Short[] le(int indice, int tamanho) {
        return Arrays.copyOfRange(valor, indice, tamanho + indice);
    }
}
