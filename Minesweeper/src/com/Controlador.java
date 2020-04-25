package com;

import com.Graphics.Coordenada;
import com.Graphics.VistaJuego;

public class Controlador {
	private static Controlador miControlador;
	
	private Controlador()
	{
		//Vacio
	}
	
	public static synchronized Controlador getControlador()
	{
		if(miControlador == null)
		{
			miControlador = new Controlador();
		}
		return miControlador;
	}
	
	public void clicarCasilla(Coordenada coord, String click) 
	{
		Buscaminas.getBuscaminas().clicarCasilla(coord, click);
	}
	
	public void iniciarPartida()
	{
		Buscaminas.getBuscaminas(); //
	}
	
	public void crearPartida()
	{
		Buscaminas.getBuscaminas().iniciarPartida();
	}
	
	public Tablero getTablero()
	{
		return Buscaminas.getBuscaminas().getTab();
	}
	
	public void getDatosIniciales(int dif, String nom)
	{
		Buscaminas.getBuscaminas().setDif(dif);
		Buscaminas.getBuscaminas().setUser(nom);
	}
	
	public void finalizarPartida()
	{
		
	}
}
