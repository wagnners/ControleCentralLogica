/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.socket;

import br.udesc.simuladordememorilogica.model.Mensagem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class Cliente {

    private Socket             socket;
    private ObjectOutputStream output;

    private String address;
    private int    port;


    public Cliente(String address, int port) {
        this.address = address;
        this.port    = port;
    }

    public Socket connect() throws IOException {
        this.socket = new Socket(address, port);
        this.output = new ObjectOutputStream(socket.getOutputStream());

        return socket;
    }

    public void send(Mensagem mensagem) {
        try {
            output.writeObject(mensagem.monta());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
