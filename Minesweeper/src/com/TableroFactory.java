package com;

public class TableroFactory 
{
	private static TableroFactory mFactory;
	
	
	private TableroFactory()
	{
		
	}
	
	public static synchronized TableroFactory getTableroFactory() 
	{
		if(mFactory == null)
		{
			mFactory = new TableroFactory();
		}
		return mFactory;
	}
	
	public Tablero crearProducto(int dificultad)
	{
		Tablero tab = new Tablero();
		
		
		return tab;
	}
}
