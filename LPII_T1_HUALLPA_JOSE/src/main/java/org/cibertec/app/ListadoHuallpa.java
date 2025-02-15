package org.cibertec.app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.cibertec.controlador.CompraJpaController;
import org.cibertec.model.Compra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import java.util.List;

public class ListadoHuallpa extends JFrame {

	private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoHuallpa frame = new ListadoHuallpa();
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
	public ListadoHuallpa() {
		setTitle("Listado de Compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Número de Compra", "Fecha", "Nombre del Producto", "ID Proveedor", "Total"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        cargarDatos();
	}
	
	private void cargarDatos() {
		
		tableModel.setRowCount(0);
        CompraJpaController compraController = new CompraJpaController();
        List<Compra> compras = compraController.findAllCompras();

        for (Compra compra : compras) {
            Object[] row = {
                compra.getNroCompra(), 
                compra.getFecha(), 
                compra.getProducto(), 
                compra.getProveedor(), 
                compra.getTotal() 
            };
            tableModel.addRow(row);
        }
    }

}
