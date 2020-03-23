package com;

import com.Graphics.Coordenada;
import com.Graphics.VistaInicial;
import com.Graphics.VistaJuego;

public class Buscaminas 
{
	private int dificultad;
	private Tablero tablero;
	private static Buscaminas miBuscaminas;
	private VistaJuego vistaJuego;
	private VistaInicial vistaInicial;
	private Observable observable;
	
	private Buscaminas()
	{
		//VACIO
	}
	
	public static synchronized Buscaminas getBuscaminas()
	{
		if(miBuscaminas == null)
		{
			miBuscaminas = new Buscaminas();
		}
		return miBuscaminas;
	}
	
	public void iniciarPartida()
	{	
		this.dificultad = 1;
		observable = new Observable();
		//inicializar vistaInicial como hacer new de un JFrame
		crearTablero();
		vistaInicial = new VistaInicial();
		observable = new Observable();
		
	}
	
	public void crearTablero()
	{
		switch(dificultad)
		{
		case 1:
			tablero = new Tablero(7, 10, 7*1);
			break;
		case 2:
			tablero = new Tablero(10, 15, 15*2);
			break;
		case 3:
			tablero = new Tablero(12, 25, 25*3);
			break;
		default:
			System.out.println("Error creacion de tablero (buscaminas)");
			break;
		}
	}
	
	public void clicarCasilla(Coordenada coord, String click) 
	{
		tablero.mostrarCasilla(coord.getX(), coord.getY(), click);
		tablero.notifyObservers(coord);
	}
	
	public VistaJuego getVistaJuego()
	{
		return vistaJuego;
	}
	
	
}
