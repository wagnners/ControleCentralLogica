/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladormemoriacentrallogica;

import br.udesc.simuladormemoriacentrallogica.model.Mensagem;
import static br.udesc.simuladormemoriacentrallogica.model.Mensagem.END_BYTE;
import static br.udesc.simuladormemoriacentrallogica.model.Mensagem.FULL_BYTE;
import static br.udesc.simuladormemoriacentrallogica.model.Mensagem.START_BYTE;
import br.udesc.simuladormemoriacentrallogica.model.MensagemInvalida;
import br.udesc.simuladormemoriacentrallogica.model.Comando;
import br.udesc.simuladormemoriacentrallogica.model.TipoRegistrador;
import br.udesc.simuladormemoriacentrallogica.server.Server;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class Main {

    public static void main(String[] args) {
        new Server(5000);

//        Mensagem msg;
//        try {
//            msg = new Mensagem(criaMensagem());
//            System.out.println(msg);
//        } catch (MensagemInvalida ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    private static Short[] criaMensagem() {
        int endereco = 0;
        int tamanho = 1;
        TipoRegistrador reg = TipoRegistrador.R;

        Short[] dados = new Short[(tamanho * reg.getTamanhoByte())];
        for (int i = 0; i < dados.length; i++) {
            dados[i] = 0x43;
        }

        Short[] valor = new Short[8 + (tamanho * reg.getTamanhoByte())];
        valor[0] = START_BYTE;
        valor[valor.length - 1] = END_BYTE;

        valor[1] = Comando.ESCREVER.getValor();
        valor[2] = (short) reg.toString().charAt(0);
        valor[3] = (short) (endereco & FULL_BYTE);
        valor[4] = (short) ((endereco >> 8) & FULL_BYTE);
        valor[5] = (short) (tamanho & FULL_BYTE);
        valor[6] = (short) ((tamanho >> 8) & FULL_BYTE);
        System.arraycopy(dados, 0, valor, 7, (tamanho * reg.getTamanhoByte()));

        return valor;
    }

}
