/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModeloCliente extends AbstractTableModel {

    List<Cliente> clientes = null;
    private String[] encabezados = null;

    public ModeloCliente(String[] encabezados) {
        this.encabezados = encabezados;
        //this.clientes = clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        this.fireTableDataChanged();
    }
    
    
    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return encabezados.length;
    }

    @Override
    public String getColumnName(int x) {
        return encabezados[x];
    }

    public Object getValueAt(int x, int y) {
        String retorno = "";
        Cliente cl = clientes.get(x);
        switch (y) {
            case 0:
                return cl.getId();
            case 1:
                retorno = cl.getNombre();
                break;
            case 2:
                retorno = cl.getApellido();
                break;
            case 3:
                retorno = String.valueOf(cl.getLimiteCredito());
                break;
        }

        return retorno;
    }
}
