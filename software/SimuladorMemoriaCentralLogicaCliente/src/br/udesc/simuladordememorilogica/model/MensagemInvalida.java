/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.model;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class MensagemInvalida extends Exception {

    /**
     * Creates a new instance of <code>MensagemInvalida</code> without detail message.
     */
    public MensagemInvalida() {
    }

    /**
     * Constructs an instance of <code>MensagemInvalida</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MensagemInvalida(String msg) {
        super(msg);
    }
}
