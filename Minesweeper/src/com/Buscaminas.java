package com;

public class Buscaminas 
{
	private int dificultad;
	private Tablero tablero;
	private Buscaminas miBuscaminas;
	
	private Buscaminas()
	{
		
	}
	
	public Buscaminas getBuscaminas()
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
