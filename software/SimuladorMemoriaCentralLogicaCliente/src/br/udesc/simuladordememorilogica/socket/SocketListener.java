/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.socket;

import br.udesc.simuladordememorilogica.model.Mensagem;
import br.udesc.simuladordememorilogica.model.MensagemInvalida;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class SocketListener {

    private Socket   socket;
    private Cliente  cliente;

    private ClienteListener listener;

    private String address;
    private int    port;


    public SocketListener(ClienteListener listener, String address, int port) throws IOException {
        this.listener = listener;
        this.address  = address;
        this.port     = port;

        inicia();
    }

    public void desconecta() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void inicia() throws IOException {
        this.cliente = new Cliente(this.address, this.port);
        this.socket = this.cliente.connect();

        new Thread(new SocketListener.InnerListener(this.socket)).start();
    }

    public void envia(Mensagem mensagem) {
        cliente.send(mensagem);
    }

    private class InnerListener implements Runnable {

        private ObjectInputStream input;

        public InnerListener(Socket socket) {
            try {
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            try {
                Short[] dados;
                System.out.println("aguardando!");
                while ((dados = (Short[]) input.readObject()) != null) {
                    System.out.println("recebeu!" + Arrays.toString(dados));
                    Mensagem msg = new Mensagem(dados);
                    listener.notifica(msg);
                }
            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("desconectouz");
                listener.desconecta();
            } catch (MensagemInvalida ex) {
                Logger.getLogger(SocketListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
