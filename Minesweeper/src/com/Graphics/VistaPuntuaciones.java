package com.Graphics;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.Buscaminas;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VistaPuntuaciones extends JFrame {

	private JPanel contentPane;
	private final JPanel panel_1 = new JPanel();
	private JTextArea puntuaciones;

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
		setBounds(100, 100, 250, 300);
		contentPane = new JPanel();
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel,BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("MEJORES PUNTUACIONES");
		panel.add(lblNewLabel);
		contentPane.add(panel_1,BorderLayout.CENTER);
		
		puntuaciones = new JTextArea();
		puntuaciones.setRows(10);
		puntuaciones.setSize(200,450);
		panel_1.add(puntuaciones);
		puntuaciones.setColumns(10);
		puntuaciones.setEditable(false);
		ArrayList<String> listaPuntuaciones =  Buscaminas.getBuscaminas().getPuntuaciones();
		Iterator<String> itr = listaPuntuaciones.iterator();
		int i=0;
		while(itr.hasNext() && i<10)
		{	
			String wr = itr.next();
			puntuaciones.append(wr+"\n");
			i++;
		}
		
	

		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2,BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Aceptar");
		panel_2.add(btnAceptar);
		
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		setVisible(true);
	}

}
