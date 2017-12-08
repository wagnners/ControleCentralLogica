/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladormemoriacentrallogica.model;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class TamanhoInvalido extends Exception {

    /**
     * Creates a new instance of <code>TamanhoInvalido</code> without detail message.
     */
    public TamanhoInvalido() {
    }

    /**
     * Constructs an instance of <code>TamanhoInvalido</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public TamanhoInvalido(String msg) {
        super(msg);
    }


}
