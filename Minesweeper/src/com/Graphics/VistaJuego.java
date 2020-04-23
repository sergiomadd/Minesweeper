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
import com.Controlador;
import com.Tablero;

@SuppressWarnings("deprecation")
public class VistaJuego extends JFrame implements java.util.Observer{
	private JButton[][] posMinas = null;

	private JPanel contentPane;
	private int cont = 0;
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
			//System.out.println("Banderas:" + datos[4]);
			x = Integer.parseInt(datos[0]);
			y = Integer.parseInt(datos[1]);
			//System.out.println(x+""+y);
			JButton boton = posMinas[x][y];
			String mostrar = datos[2];
			lblBanderas.setText("Banderas:" + datos[4]);
			if (mostrar.equals("vacio")){
				boton.setBackground(gris);
				boton.setBorder(new LineBorder(grisBorde));
			}
			else if(mostrar.equals("numero")) {
				boton.setBackground(gris);
				boton.setBorder(new LineBorder(grisBorde));
				boton.setText(datos[3]);
			}
			else if(mostrar.equals("bandera")) {
				boton.setIcon(null);	
				boton.setIcon(new ImageIcon(getClass().getResource("flag_trasp.png").getPath()));
			}
			else if(mostrar.equals("bomba")) {
				//muestra bomba en x,y fondo rojo
				if(cont==0)
				{
					boton.setIcon(null);	
					boton.setIcon(new ImageIcon(getClass().getResource("bomba_trasp.png").getPath()));
					boton.setBackground(new Color(255,0,0));
					cont++;
				}
				else 
				{
					//muestra resto de bombas fondo gris
					boton.setIcon(null);	
					boton.setIcon(new ImageIcon(getClass().getResource("bomba_trasp.png").getPath()));
					boton.setBackground(gris);
				}
			}
			else if(mostrar.equals("tapado")) {
				boton.setIcon(null);	
			}		
			else if(mostrar.equals("acabada")) {
				System.out.println("Efectivamente has acabado");
				//contentPane.removeAll();
				for (Component component : contentPane.getComponents()) {
					   component.setEnabled(false); 
					}
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
		Controlador.getControlador().iniciarPartida();
		Controlador.getControlador().getTablero().addObserver(this);
		iniciarTimer(lblTiempo);
		
		
	}

	/**
	 * Create the frame.
	 */
	public VistaJuego(int x, int y, Observable tab) {
		tab.addObserver(this);
		posMinas = new JButton[x][y];
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			setBounds(100, 100, 60*x, 45	*y);
		}
		else
		{
			System.out.println("Tamaño no valido");
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
	        }
	      });

		btnReset.setIcon(new ImageIcon(getClass().getResource("reset_trasp.png").getPath()));

		
		
		btnReset.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				reset();
			}
		});
		
		JPanel panel = new JPanel();
		Menu.add(panel);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		panel.add(lblBanderas);
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

		
		JLabel lblNewLabel = new JLabel("Puntos");
		panel.add(lblNewLabel);

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
							Controlador.getControlador().clicarCasilla(coord, "izq");
						}
						
						else {
							btnCasilla.setIcon(new ImageIcon(("C://Users//innib//Pictures//flag_trasp.png")));
							Controlador.getControlador().clicarCasilla(coord, "der");
						}	
					}
				});
			}
		}
		
	    iniciarTimer(lblTiempo);
	    
	    }
	
		private void iniciarTimer(JLabel lblTiempo) {
			lblTiempo.setText("00:00");
			timer.removeActionListener(timer.getActionListeners()[0]);
			timer = new Timer(1000, new ActionListener() {
		    	SimpleDateFormat f = new SimpleDateFormat("mm:ss");
		    	long start = System.currentTimeMillis();
		    	
		        @Override
		        public void actionPerformed(ActionEvent e) {
		          lblTiempo.setText(String.valueOf(f.format((System.currentTimeMillis()-start))));
		        }
		      });
			timer.start();
		}
		
}
