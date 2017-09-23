/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladormemoriacentrallogica.model;

import java.util.ArrayList;

/**
 *
 * @author Wagner
 */
public class Registrador {

    private TipoRegistrador tipo;
    private ArrayList<Short> registradores;

    public Registrador(TipoRegistrador tipo) {
        this.tipo = tipo;
        registradores = new ArrayList<>();
    }

    public TipoRegistrador getTipo() {
        return tipo;
    }

    public void grava(int indice, Short valor) {
        registradores.add(indice, valor);
    }

    public Short ler(int indice) {
        return registradores.get(indice);
    }
}
