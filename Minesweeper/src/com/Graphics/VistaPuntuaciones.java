package com.Graphics;

import java.awt.BorderLayout;
import java.util.Scanner;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaPuntuaciones extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_1 = new JPanel();
	private JTextField puntuaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPuntuaciones frame = new VistaPuntuaciones();
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
	public VistaPuntuaciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		contentPane.add(panel_1);
		
		puntuaciones = new JTextField();
		panel_1.add(puntuaciones);
		puntuaciones.setColumns(10);
		puntuaciones.setEditable(false);
		
		Scanner scan;
	    static String Name, Surname;

	    public void open() {

	        try {
	            scan = new Scanner(new File("C:/team1.txt"));
	            System.out.println("it is working");
	        } catch (FileNotFoundException e) {
	            System.out.println("it is not working");
	        }
	    }

	    public void read() {
	        do {
	            Name = scan.next();

	            if (scan.hasNext())
	                Surname = scan.next();

	        } while (scan.hasNext());
	        System.out.println(Name + Surname);

	        scan.close();
	    }
	

		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JButton btnAceptar = new JButton("Aceptar");
		panel_2.add(btnAceptar);
	}

}
