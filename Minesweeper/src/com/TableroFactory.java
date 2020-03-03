package com;

public class TableroFactory 
{
	private TableroFactory mFactory;
	
	
	private TableroFactory()
	{
		
	}
	
	public TableroFactory getTableroFactory() 
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
