/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.controller;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class RenderizadorTabela extends DefaultTableCellRenderer {

    private ModeloTabela model;

    public RenderizadorTabela(ModeloTabela model) {
        this.model = model;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!model.isCellEditable(row, column)) {
            c.setBackground(Color.gray);
        } else {
            c.setBackground(Color.white);
        }

        return c;
    }
}
