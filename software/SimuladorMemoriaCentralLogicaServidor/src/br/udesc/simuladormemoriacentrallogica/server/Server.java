/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladormemoriacentrallogica.server;

import br.udesc.simuladormemoriacentrallogica.model.Memoria;
import br.udesc.simuladormemoriacentrallogica.model.Mensagem;
import br.udesc.simuladormemoriacentrallogica.model.Registrador;
import br.udesc.simuladormemoriacentrallogica.model.TipoRegistrador;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class Server  {

    private ServerSocket serverSocket;
    private Socket socket;
    private Memoria memoria;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            memoria = new Memoria();
            memoria.add(new Registrador(TipoRegistrador.M));
            memoria.add(new Registrador(TipoRegistrador.D));
            memoria.add(new Registrador(TipoRegistrador.R));

            System.out.println(String.format("Servidor on! (%s)", port));

            while(true) {
                System.out.println("Esperando accept!");
                socket = serverSocket.accept();

                (new Thread(new ServerListener(this))).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public boolean receive(Mensagem mensagem, ObjectOutputStream output) {
        memoria.realiza(mensagem);
        send(mensagem, output);
        System.out.println("deolvido: " + mensagem);
        return true;
    }

    public void send(Mensagem mensagem, ObjectOutputStream output) {
        try {
            output.writeObject(mensagem.monta());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
