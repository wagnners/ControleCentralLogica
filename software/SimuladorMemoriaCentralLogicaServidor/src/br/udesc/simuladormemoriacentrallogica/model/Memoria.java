/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladormemoriacentrallogica.model;

import java.util.HashMap;

/**
 *
 * @author Wagner
 */
public class Memoria {

    HashMap<TipoRegistrador, Registrador> registradores = new HashMap<>();

    public void add(Registrador r) {
        registradores.put(r.getTipo(), r);
    }

    public void realiza(Mensagem mensagem) {
        Registrador registrador = registradores.get(mensagem.getTipoRegistrador());

        int posicao = mensagem.getEndereco() * registrador.getTipo().getTamanhoByte();
        int tamanho = mensagem.getTamanho() * registrador.getTipo().getTamanhoByte();

        switch (mensagem.getTipoComando()) {
            case LER:
                mensagem.setDados(registrador.le(posicao, tamanho));
            case ESCREVER:
                registrador.grava(posicao, mensagem.getDadosAsArray());
        }
    }
}
