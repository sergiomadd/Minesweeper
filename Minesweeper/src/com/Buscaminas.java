package com;

import com.Graphics.Coordenada;

public class Buscaminas 
{
	private int dificultad = 1;
	private String user = "";
	private Tablero tablero;
	private static Buscaminas miBuscaminas;
	
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
		crearTablero();
		tablero.printTablero();
		System.out.println();
	}
	
	public void crearTablero()
	{
		switch(dificultad)
		{
		case 1:
			tablero = new Tablero(7, 10, 10*1);
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
	}
	
	public Tablero getTab()
	{
		return tablero;
	}
	
	public void setUser(String pUser)
	{
		this.user = pUser;
	}
	
	public void setDif(int pDif)
	{
		this.dificultad = pDif;
	}
	
}
