/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.socket;

import br.udesc.simuladordememorilogica.model.Mensagem;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public interface ClienteListener {

    void notifica(Mensagem mensagem);

    void desconecta();

}
