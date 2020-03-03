package com;

public class Buscaminas 
{
	private int dificultad;
	private Tablero tablero;
	private static Buscaminas miBuscaminas;
	
	private Buscaminas()
	{
		
	}
	
	public static synchronized Buscaminas getBuscaminas()
	{
		if(miBuscaminas == null)
		{
			miBuscaminas = new Buscaminas();
		}
		return miBuscaminas;
	}
	
	public void crearTablero()
	{
		
	}
	
	public void acabar() 
	{
		//Popup de has ganado
		//Mostrar todas las minas -> recorrerlas y mostrat()
	}
	
	
	
}
