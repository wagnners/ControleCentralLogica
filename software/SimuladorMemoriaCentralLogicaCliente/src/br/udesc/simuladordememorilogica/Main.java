/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica;

import br.udesc.simuladordememorilogica.controller.ControllerTela;
import br.udesc.simuladordememorilogica.model.ByteUtils;
import java.util.Arrays;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(ByteUtils.toShort("ab", 4)));
//        System.out.println(ByteUtils.toString(ByteUtils.toShort("ab", 4)));
//        System.out.println(Arrays.toString(ByteUtils.toShort(12345, 4)));
//        System.out.println(ByteUtils.toString(ByteUtils.toShort(12345, 4)));
        (new ControllerTela()).inicia();
    }
}
