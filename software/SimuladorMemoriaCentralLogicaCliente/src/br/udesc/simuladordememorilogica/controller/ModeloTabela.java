/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.simuladordememorilogica.controller;

import br.udesc.simuladordememorilogica.model.ByteUtils;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ricardo Augusto Küstner
 */
public class ModeloTabela  extends AbstractTableModel {

	private String[][] dados;
	private int cells = 13;

	public ModeloTabela() {
		reset();
	}

    public void reset() {
        dados = new String[getRowCount()][getColumnCount()];
    }

    public void setCells(int cells) {
        this.cells = cells;
        reset();
    }

	@Override
	public String getColumnName(int column) {
		return "Coluna " + (column + 1);
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return (int) Math.ceil(cells / 5.0);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
        return dados[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return ((rowIndex * 5) + (columnIndex + 1)) <= cells;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        System.out.println("escreveu: " + aValue);
		dados[rowIndex][columnIndex] = aValue.toString();
	}

    public ArrayList<String> getDados() {
        ArrayList<String> valores = new ArrayList<>();

        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (getColumnCount() * i + j < cells) {
                    valores.add(dados[i][j]);
                }
            }
        }

        return valores;
    }

    public void setDados(Short[] valores, int tamanho) {
        reset();
        int k = 0;

        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (getColumnCount() * i + j < cells) {
                    Short[] aux = new Short[tamanho];
                    for (int m = 0; m < tamanho; m++) {
                        aux[m] = valores[k++];
                    }

                    dados[i][j] = ByteUtils.toString(aux);
                }
            }
        }
    }

}
