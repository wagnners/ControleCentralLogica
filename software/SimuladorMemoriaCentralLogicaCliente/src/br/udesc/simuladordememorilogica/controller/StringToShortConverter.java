/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.controller;

import br.udesc.simuladordememorilogica.model.ByteUtils;
import br.udesc.simuladordememorilogica.model.TipoRegistrador;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import com.sun.xml.internal.ws.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class StringToShortConverter {

    private TipoRegistrador registrador;

    public StringToShortConverter(TipoRegistrador registrador) {
        this.registrador = registrador;
    }

    public boolean valida(String valor) {
        return true;
    }

    public ArrayList<Short> toShortList(List<String> dados) {
        ArrayList<Short> saida = new ArrayList<>();

        for (String dado : dados) {
            for (Short val : ByteUtils.toShort(dado, registrador.getTamanhoByte())) {
                saida.add(val);
            }
        }

        return saida;
    }


}
