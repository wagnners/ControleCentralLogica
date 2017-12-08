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
public class ValorInvalido extends Exception {

    /**
     * Creates a new instance of <code>ValorInvalido</code> without detail message.
     */
    public ValorInvalido() {
    }

    /**
     * Constructs an instance of <code>ValorInvalido</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ValorInvalido(String msg) {
        super(msg);
    }
}
