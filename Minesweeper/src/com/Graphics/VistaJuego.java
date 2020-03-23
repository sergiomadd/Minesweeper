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
	private JButton[][] posMinas = null;

	private JPanel contentPane;
	
	
	//Fondo destapado gris
	Color gris = new Color(189,189,189);
	//Borde fondo destapado
	Color grisBorde = new Color(123,123,123);

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
	
	public void update(Observer tab, Coordenada cambio) {
			int x=cambio.getX();
			int y=cambio.getY();
			
			//String mostrar = tab.getMostrar(x,y);
			String mostrar = "vacio";
			if (mostrar=="vacio"){
				posMinas[x][y].setBackground(gris);
				posMinas[x][y].setBorder(new LineBorder(grisBorde));
			}
			else if(mostrar=="numero") {
				//tab.getMatriz()[x][y].getNum();
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
		
		posMinas = new JButton[x][y];

		
			
					
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 415);
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
		
		JButton btnReset = new JButton();
		btnReset.setBackground(new Color(189,189,189));

		//btnReset.setIcon(new ImageIcon(("..//Iconos//reset_trasp.png")));
		btnReset.setIcon(new ImageIcon(("C://Users//innib//Pictures//reset_trasp.png")));
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
				JButton btnCasilla = new JButton("");
				Grid.add(btnCasilla);
				Color sinmarcar = new Color(220,220,220);
				btnCasilla.setBackground(sinmarcar);
				btnCasilla.setBorder(new LineBorder(Color.WHITE));
				
				posMinas[i][j] = btnCasilla;
				
				
				btnCasilla.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						Coordenada coord=null;
						for (int i = 0; i < x; i++) {
						    for (int j = 0; j < y; j++) {
						       if (posMinas[i][j] == btnCasilla) {
						    	   coord = new Coordenada(i,j);
						    	   
						       }
						    }	   
						   
						  }
						 coord.displayCoord();
						
						if (SwingUtilities.isLeftMouseButton(arg0)) {
							btnCasilla.setBackground(gris);
							btnCasilla.setBorder(new LineBorder(grisBorde));
							//Buscaminas.getBuscaminas().clicarCasilla(coord,"izq");
							
						}
						
						else {
							btnCasilla.setIcon(new ImageIcon(("C://Users//innib//Pictures//flag_trasp.png")));
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
