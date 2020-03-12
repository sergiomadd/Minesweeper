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
	
	public Casilla crearCasilla(int tipo)
	{
		Casilla casilla = null;
		
		if(tipo < 0)
		{
			casilla = new CasillaMina();
		}
		if(tipo == 0)
		{
			casilla = new CasillaVacia();
		}
		if(tipo > 0)
		{
			casilla = new CasillaNumero(tipo);
		}
		
		return casilla;
	}
}

