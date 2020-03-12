package com;

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
		observable = new Observable();
		crearTablero();
		vistaInicial = new VistaInicial();

	}
	
	public void crearTablero()
	{
		Tablero t;
		switch(dificultad)
		{
		case 1:
			t = new Tablero(7, 10, 7*1);
			break;
		case 2:
			t = new Tablero(10, 15, 15*2);
			break;
		case 3:
			t = new Tablero(12, 25, 25*3);
			break;
		default:
			System.out.println("Error creacion de tablero (buscaminas)");
			break;
		}
	}
	
	public void acabarPartida()
	{
		//VistaJuego.dispose();
	}
	
	public Tablero getTablero()
	{
		return this.tablero;
	}
	
	//Ganar
	//al acabar ovolver a llamar a iniciar partida.
	//clases de estado de casillas -> Sergio G
	//vista inicial
}
