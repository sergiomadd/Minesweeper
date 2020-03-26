package com;

public class TableroFactory 
{
	private static TableroFactory mFactory;
	
	
	private TableroFactory()
	{
		//Vacio
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
			Estado inicialB = new EstadoTapadoB();
			casilla.setEstado(inicialB);
		}
		if(tipo == 0)
		{
			casilla = new CasillaVacia();
			Estado inicial = new EstadoTapadoNB();
			casilla.setEstado(inicial);
		}
		if(tipo > 0)
		{
			casilla = new CasillaNumero(tipo);
		}
		
		return casilla;
	}
}

