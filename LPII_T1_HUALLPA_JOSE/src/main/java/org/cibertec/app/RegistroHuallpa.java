package org.cibertec.app;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.cibertec.controlador.CompraJpaController;
import org.cibertec.model.Compra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RegistroHuallpa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField idProveedorField;
    private JTextField totalField;
    private JButton registrarButton;
    private JLabel mensajeLabel;
    private JTextField textField;
    private JTextField txtIdProveedor;
    private JTextField txtTotal;
    private JTextField txtIdInsumo;
    private JTextField txtCantidad;
    private JButton btnGuardar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuallpa frame = new RegistroHuallpa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistroHuallpa() {
        JFrame frame = new JFrame("Registro de Compras");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel lblIdProveedor = new JLabel("ID Proveedor:");
        lblIdProveedor.setBounds(20, 20, 100, 25);
        frame.add(lblIdProveedor);

        txtIdProveedor = new JTextField();
        txtIdProveedor.setBounds(150, 20, 200, 25);
        frame.add(txtIdProveedor);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(20, 60, 100, 25);
        frame.add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(150, 60, 200, 25);
        frame.add(txtTotal);

        JLabel lblIdInsumo = new JLabel("ID Insumo:");
        lblIdInsumo.setBounds(20, 100, 100, 25);
        frame.add(lblIdInsumo);

        txtIdInsumo = new JTextField();
        txtIdInsumo.setBounds(150, 100, 200, 25);
        frame.add(txtIdInsumo);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 140, 100, 25);
        frame.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(150, 140, 200, 25);
        frame.add(txtCantidad);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 200, 100, 30);
        frame.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCompra();
            }
        });
	}
	
	private void guardarCompra() {
        int idProveedor = Integer.parseInt(txtIdProveedor.getText());
        double total = Double.parseDouble(txtTotal.getText());
        int idInsumo = Integer.parseInt(txtIdInsumo.getText());
        int cantidad = Integer.parseInt(txtCantidad.getText());

        CompraJpaController compraController = new CompraJpaController();
        compraController.insertarCompra(idProveedor, total, idInsumo, cantidad);

        JOptionPane.showMessageDialog(null, "Compra registrada exitosamente.");
    }
}
