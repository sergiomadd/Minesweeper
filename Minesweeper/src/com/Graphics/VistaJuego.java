package com.Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.Buscaminas;
import com.Observer;
import com.Tablero;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.EventListener;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class VistaJuego extends JFrame implements Observer{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaJuego frame = new VistaJuego(7,10);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void update(Observer tab, Coordenada[] cambios) {
		for( Coordenada c: cambios) {
			int x=c.getX();
			int y=c.getY(); 	
			mostrar = tab.getMostrar(x,y);
			if (mostrar=="vacio"){
				posMinas[x][y].setColor();
					
			}
			else if(mostrar=="numero") {
				tab.getMatriz()[x][y].getNum();
			}
			else if(mostrar=="bandera") {
				//muestra imagen bandera
			}
			else if(mostrar=="bomba") {
				//muestra bomba en x,y fondo rojo
				//muestra resto de bombas fondo gris
			}
			else if(mostrar=="tapado") {
				
			}
			
		}
		
	}

	
	
	public void reset() {
		contentPane.removeAll();
		dispose();
		//Como hacer para que no haya que abrir una ventana nueva entera cada vez
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaJuego frame = new VistaJuego(7,10);
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
	public VistaJuego(int x, int y) {
		
		JButton[][] posMinas = new JButton[x][y];
			
					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel Grid = new JPanel();
		contentPane.add(Grid, BorderLayout.CENTER);
		Grid.setLayout(new GridLayout(7,10, 0, 0));
		
		JPanel Menu = new JPanel();
		contentPane.add(Menu, BorderLayout.NORTH);
		Menu.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblPuntos = new JLabel("puntos");
		lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		Menu.add(lblPuntos);
		
		JButton btnReset = new JButton("RESET");

		//btnReset.setIcon(new ImageIcon(VistaJuego.class.getResource("/org/eclipse/jface/dialogs/images/help.png")));

		btnReset.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				reset();
				
			}
		});

		Menu.add(btnReset);
		
		JLabel lblTiempo = new JLabel("00:00");
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Menu.add(lblTiempo);
		for(int i=0; i < x ; i++) {
			for(int j=0; j < y; j++){
				//a
				JButton btnNewButton = new JButton("");
				Grid.add(btnNewButton);
				Color sinmarcar = new Color(220,220,220);
				btnNewButton.setBackground(sinmarcar);
				btnNewButton.setBorder(new LineBorder(Color.WHITE));
				
				posMinas[i][j] = btnNewButton;
				
				
				btnNewButton.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						Coordenada coord=null;
						for (int i = 0; i < x; i++) {
						    for (int j = 0; j < y; j++) {
						       if (posMinas[i][j] == btnNewButton) {
						    	   coord = new Coordenada(i,j);
						    	   
						       }
						    }	   
						   
						  }
						 coord.displayCoord();
						
						if (SwingUtilities.isLeftMouseButton(arg0)) {
							Color marcado = new Color(169,169,169);
							btnNewButton.setBackground(marcado);
							btnNewButton.setBorder(new LineBorder(Color.DARK_GRAY));
							//Buscaminas.getBuscaminas().clicarCasilla(coord,"izq");
							
						}
						
						else {
							Color marcado = new Color(0,0,0);
							btnNewButton.setBackground(marcado);
							btnNewButton.setBorder(new LineBorder(Color.RED));
							//Buscaminas.getBuscaminas().clicarCasilla(coord,"der") ;
						}
						
					}

					

					
				});
				
			}
		}
		
	    new Timer(1000, new ActionListener() {
	    	SimpleDateFormat f = new SimpleDateFormat("mm:ss");
	    	long start = System.currentTimeMillis();
	    	
	    	

	        @Override
	        public void actionPerformed(ActionEvent e) {
	          lblTiempo.setText(String.valueOf(f.format((System.currentTimeMillis()-start))));
	        }
	      }).start();
	    }
		
	

}
