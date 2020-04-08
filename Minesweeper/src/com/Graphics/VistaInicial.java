package com.Graphics;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.Controlador;

public class VistaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField tfNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInicial frame = new VistaInicial();
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
	public VistaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblIntroduceTuNombre = new JLabel("Introduce tu nombre");
		panel.add(lblIntroduceTuNombre);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		tfNombre = new JTextField();
		panel_1.add(tfNombre);
		tfNombre.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JLabel lblDificultad = new JLabel("Dificultad");
		panel_2.add(lblDificultad);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		DefaultComboBoxModel<String> elementos = new DefaultComboBoxModel<>();
		elementos.addElement("1");
		elementos.addElement("2");
		elementos.addElement("3");
		
		JComboBox comboBox = new JComboBox(elementos);
		panel_3.add(comboBox);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		
		JButton btnAceptar = new JButton("Aceptar");
		panel_4.add(btnAceptar);
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Controlador.getControlador().getDatosIniciales(Integer.parseInt((String)comboBox.getSelectedItem()),tfNombre.getText());
				dispose();
				VistaJuego vista;
				switch(Integer.parseInt((String)comboBox.getSelectedItem()))
				{
				case 1:
					vista = new VistaJuego(7, 10,Controlador.getControlador().getTablero());
					break;
				case 2:
					vista = new VistaJuego(10, 15,Controlador.getControlador().getTablero());
					break;
				case 3:
					vista = new VistaJuego(12, 25,Controlador.getControlador().getTablero());
					break;
				default:
					System.out.println("ERROR: elige 1, 2 o 3.");
					//Lanzamos excepcion de error de dificultad
					break;
				}
				
			}
		});
		
		
		
		setVisible(true);
	}

}
