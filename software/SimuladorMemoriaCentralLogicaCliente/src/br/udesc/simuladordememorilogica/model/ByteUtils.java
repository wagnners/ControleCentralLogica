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
public class ByteUtils {

    public static Short[] toShort(String valor, int bytes) {
        if (valor != null && valor.matches("^\\d+$")) {
            return toShort(Integer.parseInt(valor), bytes);
        }

        Short[] saida = new Short[bytes];
        if (valor != null && valor.length() >= 2 && bytes == 4) {
            saida[3] = (short) valor.charAt(1);
        }
        if (valor != null && valor.length() >= 1 && bytes >= 2) {
            saida[1] = (short) valor.charAt(0);
        }
        return saida;
    }

    public static Short[] toShort(Integer valor, int bytes) {
        Short[] saida = new Short[bytes];
        for (int i = 0; i < bytes; i++) {
            saida[i] = (short) ((valor >> (i * 8)) & 0xFF);
        }
        return saida;
    }

    public static String toString(Short[] valor) {
        String saida = null;
        if (valor.length == 1) {
            saida = valor.toString();
        }
        if (valor[0] == null) {
            if (valor[1] != null) {
                saida = Character.toString((char) (int) valor[1]);
            }
            if (valor[3] != null) {
                saida += Character.toString((char) (int) valor[3]);
            }
        } else {
            int total = 0;
            for (int i = 0; i < valor.length; i++) {
                total |= valor[i] << (8 * i);
            }

            saida = Integer.toString(total);
        }
        return saida;
    }

}
