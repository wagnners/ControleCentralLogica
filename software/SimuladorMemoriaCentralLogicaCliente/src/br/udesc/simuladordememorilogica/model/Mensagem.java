/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.model;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Wagner
 */
public class Mensagem {

    public static final short START_BYTE = 0x02;
    public static final short END_BYTE = 0x03;
    public static final short FULL_BYTE = 0xFF;

    private Comando tipoComando;
    private TipoRegistrador tipoRegistrador;
    private int endereco;
    private int tamanho;
    private ArrayList<Short> dados;

    public int getEndereco() {
        return endereco;
    }

    public ArrayList<Short> getDados() {
        return dados;
    }

    public Comando getTipoComando() {
        return tipoComando;
    }

    public TipoRegistrador getTipoRegistrador() {
        return tipoRegistrador;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Mensagem(Comando tipoComando, TipoRegistrador tipoRegistrador, int endereco, int tamanho, ArrayList<Short> dados) {
        this.tipoComando = tipoComando;
        this.tipoRegistrador = tipoRegistrador;
        this.endereco = endereco;
        this.tamanho = tamanho;
        this.dados = dados;
    }

    public Mensagem(Short[] valor) throws MensagemInvalida {
        validaInicio(valor);
        validaFinal(valor);

        if (valor.length > 3) {
            tipoComando = Comando.getTipoComando(valor[1]);
            tipoRegistrador = TipoRegistrador.getTipoRegistrador(valor[2]);
            endereco = valor[3] | valor[4] << 8;
            tamanho = valor[5] | valor[6] << 8;
            int posicoes = tamanho * tipoRegistrador.getTamanhoByte();
            Short[] conteudo = new Short[posicoes];
            System.arraycopy(valor, 7, conteudo, 0, posicoes);
            dados = new ArrayList<>(Arrays.asList(conteudo));
        } else {
            tipoComando = Comando.ESCREVER;
            dados = new ArrayList<>();
            dados.add(valor[1]);
        }
    }

    public Short[] monta() {
        Short[] valor = new Short[getTamanhoTotal()];
        valor[0] = START_BYTE;
        valor[valor.length - 1] = END_BYTE;
        valor[1] = tipoComando.getValor();
        valor[2] = (short) tipoRegistrador.toString().charAt(0);
        valor[3] = (short) (endereco & FULL_BYTE);
        valor[4] = (short) ((endereco >> 8) & FULL_BYTE);
        valor[5] = (short) (tamanho & FULL_BYTE);
        valor[6] = (short) ((tamanho >> 8) & FULL_BYTE);

        if (tipoComando != Comando.LER) {
            System.arraycopy(dados.toArray(), 0, valor, 7, dados.size());
        }

        return valor;
    }

    public int getTamanhoTotal() {
        // start + operação + tipo + 2x endereço + 2x tamanho + end
        if (tipoComando != Comando.ESCREVER) {
            return 8;
        }
        return 8 + dados.size();
    }

    private void validaInicio(Short[] valor) throws MensagemInvalida {
        if (valor[0] != START_BYTE) {
            throw new MensagemInvalida();
        }
    }

    private void validaFinal(Short[] valor) throws MensagemInvalida {
        if (valor[valor.length -1] != END_BYTE) {
            throw new MensagemInvalida();
        }
    }

    public Short[] getDadosAsArray() {
        Short[] saida = new Short[dados.size()];
        for (int i = 0; i < dados.size(); i++) {
            saida[i] = dados.get(i);
        }
        return saida;
    }

    void setDados(Short[] valores) {
        dados = new ArrayList<>(Arrays.asList(valores));
    }

    @Override
    public String toString() {
        StringBuilder saida = new StringBuilder();

        if (tipoRegistrador != null) {
            saida.append(tipoComando.toString());
            saida.append(" ");
            saida.append(tipoRegistrador.toString());
            saida.append(" ");
            saida.append(endereco);
            saida.append(" ");
            saida.append(tamanho);
            saida.append(" ");
            saida.append(Arrays.toString(getDados().toArray()));
        }

        return saida.toString();
    }


}
