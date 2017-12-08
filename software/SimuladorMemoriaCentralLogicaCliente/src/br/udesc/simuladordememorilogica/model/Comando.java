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
public enum Comando {

    LER      ((short) 0x30),
    ESCREVER ((short) 0x31);

    private final Short valor;

    private Comando(Short valor) {
        this.valor  = valor;
    }

    public Short getValor() {
        return valor;
    }

    public static Comando getTipoComando(short valor) {
        switch ((int) valor) {
            case 0x30:
                return Comando.LER;
            case 0x31:
                return Comando.ESCREVER;
        }

        return null;
    }

    public static Comando getTipoComando(String valor) {
        for (Comando value : values()) {
            if (valor.equals(value.toString())) {
                return value;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        switch (this) {
            case LER:
                return "Ler";
            case ESCREVER:
                return "Escrever";
        }
        return null;
    }

    public static String[] asList() {
        String[] saida = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            saida[i] = values()[i].toString();
        }
        return saida;
    }

}
