/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.controller;

import br.udesc.simuladordememorilogica.model.Comando;
import br.udesc.simuladordememorilogica.model.Mensagem;
import br.udesc.simuladordememorilogica.model.TipoRegistrador;
import br.udesc.simuladordememorilogica.socket.ClienteListener;
import br.udesc.simuladordememorilogica.socket.SocketListener;
import br.udesc.simuladordememorilogica.view.Tela;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
/**
 *
 * @author Ricardo Augusto Küstner
 */
public class ControllerTela implements ClienteListener {

    private Tela tela;
    private ModeloTabela modelTabela;
    private SocketListener listener;

    public ControllerTela() {
        this.tela = new Tela();
    }

    public void inicia() {
        iniciaComponentes();
        tela.setVisible(true);
    }

    private void iniciaComponentes() {
        tela.cbComando.setModel(new DefaultComboBoxModel<String>(Comando.asList()));
        tela.cbRegistrador.setModel(new DefaultComboBoxModel<String>(TipoRegistrador.asList()));
        tela.edTamanho.setValue(10);

        modelTabela = new ModeloTabela();
        tela.tabela.setModel(modelTabela);
        tela.tabela.setDefaultRenderer(Object.class, new RenderizadorTabela(modelTabela));
        modelTabela.setCells((Integer) tela.edTamanho.getValue());

        tela.edTamanho.addChangeListener((ChangeEvent e) -> {
            modelTabela.setCells((Integer) tela.edTamanho.getValue());
        });

        tela.btnConectar.addActionListener((ActionEvent e) -> {
            trataConexao();
        });

        tela.btnExecutar.addActionListener((ActionEvent e) -> {
            envia();
        });

        atualizaComponentes(tela.btnConectar.isSelected());
    }

    private void trataConexao() {
        if (tela.btnConectar.isSelected()) {
            abreConexao();
        } else {
            fechaConexao();
        }
    }

    private void abreConexao() {
        int porta = Integer.parseInt(tela.edPorta.getText());
        try {
            listener = new SocketListener(this, tela.edIp.getText(), porta);
            atualizaComponentes(true);
        } catch (IOException ex) {
            System.out.println("Servidor não encontrado!");
            atualizaComponentes(false);
        }
    }

    @Override
    public void desconecta() {
        fechaConexao();
    }



    private void fechaConexao() {
        listener.desconecta();
        listener = null;
        atualizaComponentes(false);
    }

    private void envia() {
        Comando comando = Comando.getTipoComando((String) tela.cbComando.getSelectedItem());
        TipoRegistrador registrador = TipoRegistrador.valueOf((String) tela.cbRegistrador.getSelectedItem());
        int endereco = (Integer) tela.edEndereco.getValue();
        int tamanho  = (Integer) tela.edTamanho.getValue();
        ArrayList<String> dados = modelTabela.getDados();
        StringToShortConverter converter = new StringToShortConverter(registrador);

        Mensagem mensagem = new Mensagem(comando, registrador, endereco, tamanho, converter.toShortList(dados));
        System.out.println("enviar: " + mensagem);
        System.out.println("enviar: " + Arrays.toString(mensagem.monta()));
        listener.envia(mensagem);
    }

    @Override
    public void notifica(Mensagem mensagem) {
        System.out.println("recebeu mensagem: " + mensagem);
    }

    private void atualizaComponentes(boolean conectado) {
        tela.edIp.setEnabled(!conectado);
        tela.edPorta.setEnabled(!conectado);
        tela.btnConectar.setSelected(conectado);
        tela.btnConectar.setText(conectado ? "Desconectar" : "Conectar");

        tela.edEndereco.setEnabled(conectado);
        tela.edTamanho.setEnabled(conectado);
        tela.cbComando.setEnabled(conectado);
        tela.cbRegistrador.setEnabled(conectado);
        tela.btnExecutar.setEnabled(conectado);
    }


}
