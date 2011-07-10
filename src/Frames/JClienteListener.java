/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import Entidades.ManejadorCliente;
import Entidades.ModeloCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JClienteListener implements ActionListener, MouseListener, KeyListener {

    private ManejadorCliente manejador;
    private ModeloCliente modelo;
    private JTextField[] cajas;
    private JButton[] btnEstado1;
    private JButton[] btnEstado2;
    private JTable jTableCliente;
    private JTextField txtBusqueda;
    private int idEdicion;

    public JClienteListener(JTextField[] temp, JButton[] estado1, JButton[] estado2, JTextField txtBusqueda, JTable jTableCliente) {
        this.cajas = temp;
        this.btnEstado1 = estado1;
        this.btnEstado2 = estado2;
        this.manejador = new ManejadorCliente();
        this.modelo = new ModeloCliente(new String[]{"Id", "Nombre", "Apellido", "Limite Credito"});
        this.jTableCliente = jTableCliente;
        this.jTableCliente.setModel(modelo);
        setClientes();
        this.txtBusqueda = txtBusqueda;
        this.idEdicion = 0;
    }

    private void setClientes() {
        modelo.setClientes(manejador.listaClientes());
    }

    private void borrarCajas() {
        for (JTextField temp : cajas) {
            temp.setText("");
        }
    }

    public void setEstado(boolean estado1, boolean estado2) {
        for (JButton temp : btnEstado1) {
            temp.setEnabled(estado1);
        }
        for (JButton temp : btnEstado2) {
            temp.setEnabled(estado2);
        }
        for (JTextField temp : cajas) {
            temp.setEnabled(estado2);
        }
        txtBusqueda.setEnabled(estado1);
        jTableCliente.setEnabled(estado1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            borrarCajas();
            setEstado(false, true);
            idEdicion = 0;
        }
        if (e.getActionCommand().equals("Upd")) {
            if (idEdicion == 0) {
                JOptionPane.showMessageDialog(null, "Debe Tener Un Cliente Cargado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            } else {
                setEstado(false, true);
            }
        }

        if (e.getActionCommand().equals("Rmv")) {
            if (idEdicion == 0) {
                JOptionPane.showMessageDialog(null, "Debe Tener Un Cliente Cargado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            } else {
                manejador.borrarClienteById(idEdicion);
                idEdicion = 0;
                borrarCajas();
                setClientes();
                txtBusqueda.setText("");
                JOptionPane.showMessageDialog(null, "Ha Sido Borrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getActionCommand().equals("Sv")) {
            for (int i = 0; i < cajas.length; i++) {
                if (cajas[i].getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Existe Campos Vacios","Aviso",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (idEdicion == 0) {
                idEdicion = manejador.agregarCliente(cajas[0].getText(), cajas[1].getText(), Long.valueOf(cajas[2].getText()));
            } else {
                manejador.modificarCliente(idEdicion, cajas[0].getText(), cajas[1].getText(), Long.valueOf(cajas[2].getText()));
            }
            setClientes();
            JOptionPane.showMessageDialog(null, "Transacion Realizada", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            setEstado(true, false);
        }

        if (e.getActionCommand().equals("Cn")) {
            setEstado(true, false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila = jTableCliente.rowAtPoint(e.getPoint());
        if (fila > -1) {
            idEdicion = (int) jTableCliente.getValueAt(fila, 0);
            for (int i = 0; i < cajas.length; i++) {
                cajas[i].setText(String.valueOf(jTableCliente.getValueAt(fila, i + 1)));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource().equals(txtBusqueda)) {
            if (txtBusqueda.getText().equals("")) {
                setClientes();
            } else {
                modelo.setClientes(manejador.buscarClienteByNombre("%" + txtBusqueda.getText().toLowerCase() + "%"));
            }
        }
    }
}
