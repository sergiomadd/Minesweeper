package com.Graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.Buscaminas;
import com.Tablero;

@SuppressWarnings("deprecation")
public class VistaJuego extends JFrame implements java.util.Observer{
	private JButton[][] posMinas = null;
	
	private int auxY;
	private int tiempo;

	private JPanel contentPane;
	private JPanel Grid;
	JLabel lblTiempo = new JLabel();
	
	JLabel lblBanderas = new JLabel();
	
	Timer timer;
	
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
					//VistaJuego frame = new VistaJuego(7,10);
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void update(Observable tab, Object datos2) {
			int x,y;
			String[] datos = (String[]) datos2;
			/*Datos:
			1)Coordenada x de la casilla pulsada
			2)Coordenada y de la casilla pulsada
			3)Estado de la casilla pulsada
			4)Numero de la casilla pulsada
			5)Cantidad de banderas restantes
			*/
			x = Integer.parseInt(datos[0]);
			y = Integer.parseInt(datos[1]);
			JButton boton = posMinas[x][y];
			String mostrar = datos[2];
			lblBanderas.setText("Banderas:" + datos[4]);
			if (mostrar.equals("vacio")){
				//Muestra una casilla vacia
				boton.setBackground(gris);
				boton.setBorder(new LineBorder(grisBorde));
			}
			else if(mostrar.equals("numero")) {
				//Muestra una casilla vacia con un numero de la cantidad de minas adyacentes
				boton.setBackground(gris);
				boton.setBorder(new LineBorder(grisBorde));
				boton.setText(datos[3]);
			}
			else if(mostrar.equals("bandera")) {
				//Muestra un icono de una bandera
				boton.setIcon(null);	
				System.getProperties();
				boton.setIcon(new ImageIcon("resources"+ File.separator + "flag_trasp.png"));
			}
			else if(mostrar.equals("bomba")) {
				//Muesta una bomba
				boton.setIcon(null);	
				boton.setIcon(new ImageIcon("resources"+ File.separator + "bomba_trasp.png"));
				boton.setBackground(gris);
			}
			else if(mostrar.equals("rojo")) {
				//Muestra la primera bomba pero roja
				boton.setIcon(null);	
				boton.setIcon(new ImageIcon("resources"+ File.separator + "bomba_trasp.png"));
				boton.setBackground(new Color(255,0,0));	
			}		
			else if(mostrar.equals("tapada")) {
				//Muestra una casilla sin modificar
				boton.setIcon(null);
				boton.setIcon(null);	
			}		
			else if(mostrar.equals("perdida")) {
				System.out.println("Efectivamente has acabado y has perdido :(");
				pararTimer();
			}
			else if(mostrar.equals("ganada")) {
				System.out.println("Efectivamente has acabado y has ganado :)");
				pararTimer();
				Buscaminas.getBuscaminas().actualizarPuntuaciones(tiempo);
			}
	}

	public void reset() {
		for(int i=0; i < posMinas.length ; i++) {
			for(int j=0; j < posMinas[0].length; j++){
				Color sinmarcar = new Color(220,220,220);
				posMinas[i][j].setBackground(sinmarcar);
				posMinas[i][j].setBorder(new LineBorder(Color.WHITE));
				posMinas[i][j].setText("");
				posMinas[i][j].setIcon(null);	
			}
		}		
		Buscaminas.getBuscaminas().iniciarPartida();
		Buscaminas.getBuscaminas().getTab().addObserver(this);
		iniciarTimer(lblTiempo);
		if(auxY==10)
		{
			lblBanderas.setText("Banderas: 10");
		}
		else if(auxY==15) 
		{
			lblBanderas.setText("Banderas: 30");
		}
		else
		{
			lblBanderas.setText("Banderas: 75");
		}
		
	}

	/**
	 * Create the frame.
	 */
	public VistaJuego(int x, int y, Observable tab) {
		tab.addObserver(this);
		auxY=y;
		posMinas = new JButton[x][y];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		if(x==7 && y==10)
		{
			setBounds(100, 100, 420, 415);
		}
		else if(x==10 && y==15)
		{
			setBounds(100, 100, 60*x, 45*y);
		}
		else if(x==12 && y==25)
		{

			setBounds(100, 100, 80*x, 30*y);
		}
		else
		{
			System.out.println("Tama�o no valido");
		}
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		
		Grid = new JPanel();
		contentPane.add(Grid, BorderLayout.CENTER);
		Grid.setLayout(new GridLayout(x,y, 0, 0));
		
		JPanel Menu = new JPanel();
		contentPane.add(Menu, BorderLayout.NORTH);
		Menu.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnReset = new JButton();
		btnReset.setBackground(new Color(189,189,189));
		
		//inicializa el timer
		
		timer = new Timer(1000, new ActionListener() {
	    	SimpleDateFormat f = new SimpleDateFormat("mm:ss");
	    	long start = System.currentTimeMillis();
	    	
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	lblTiempo.setText(String.valueOf(f.format((System.currentTimeMillis()-start))));
	        	System.out.println("Arriba: "+String.valueOf(f.format((System.currentTimeMillis()-start))));
	        }
	      });

		btnReset.setIcon(new ImageIcon("resources"+ File.separator + "reset_trasp.png"));

		
		
		btnReset.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				reset();
			}
		});

		Menu.add(lblBanderas);
		if(y==10)
		{
			lblBanderas.setText("Banderas: 10");
		}
		else if(y==15) 
		{
			lblBanderas.setText("Banderas: 30");
		}
		else
		{
			lblBanderas.setText("Banderas: 75");
		}

	
		Menu.add(btnReset);
		
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		Menu.add(lblTiempo);
		
		for(int i=0; i < x ; i++) {
			for(int j=0; j < y; j++){
				JButton btnCasilla = new JButton("");
				btnCasilla.setActionCommand("Action" + i+","+j);
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
						
						if (SwingUtilities.isLeftMouseButton(arg0)) {
							Buscaminas.getBuscaminas().clicarCasilla(coord, "izq");
						}
						
						else {
							Buscaminas.getBuscaminas().clicarCasilla(coord, "der");
						}	
					}
				});
			}
		}
		
	    iniciarTimer(lblTiempo);
	    
	    }
	
		private void iniciarTimer(JLabel lblTiempo) {
			System.out.println("Timer iniciado");
			lblTiempo.setText("00:00");
			timer.removeActionListener(timer.getActionListeners()[0]);
			timer = new Timer(1000, new ActionListener() {
		    	SimpleDateFormat f = new SimpleDateFormat("mm:ss");
		    	long start = System.currentTimeMillis();
		    	
		        @Override
		        public void actionPerformed(ActionEvent e) {
		          lblTiempo.setText(String.valueOf(f.format((System.currentTimeMillis()-start))));
		          tiempo=(int) (System.currentTimeMillis()-start);
		        }
		        
				
		      });
			timer.start();
		}
		
		private void pararTimer()
		{
			timer.stop();
		}
		
		@Override
		public void dispose() {
			System.out.println("FINALIZAR SESION");
		    VistaPuntuaciones vista = new VistaPuntuaciones();
		    super.dispose();
		}
		
		
}
