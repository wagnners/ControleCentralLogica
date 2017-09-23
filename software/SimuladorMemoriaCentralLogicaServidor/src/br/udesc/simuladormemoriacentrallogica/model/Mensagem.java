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
public class Mensagem {

    private TipoComando tipoComando;
    private TipoRegistrador tipoRegistrador;
    private int endereco;
    private int tamanho;
    private ArrayList<Short> dados;
    

    public Short[] monta() {
        Short[] valor = new Short[getTamanho()];
        valor[0] = 0x02;
        valor[1] = 0;
        return new Short[0];
    }

    public void desmonta(Short[] valor) {

    }

    public int getTamanho() {
        return 6 + dados.size() + tipoRegistrador.getTamanhoByte();
    }
}
