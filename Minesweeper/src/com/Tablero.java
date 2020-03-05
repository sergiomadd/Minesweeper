package com;

public class Tablero extends Observable
{
	private Casilla[][] matriz;
	private int dificultad;
	private int numBombas;
	
	private int contadorCasillas;
	
	public Tablero(Integer tamX, Integer tamY)
	{
		
	}
	
	public void mostrarCasilla(Integer x, Integer y)
	{
		if(this.matriz[x][y] instanceof CasillaMina)
		{
			this.matriz[x][y].setVisible();
			finalizarPartida(false);
		}
		else if(this.matriz[x][y] instanceof CasillaVacia) 
		{
			this.matriz[x][y].setVisible();
		}
		else if(this.matriz[x][y] instanceof CasillaNumero)
		{
			this.matriz[x][y].setVisible();
		}
		//Comprueba si has ganado, mira si la cantidad 
		//de bombas + casillas destapadas es igual al 
		//numero total de casilas
		if(contadorCasillas + numBombas == x*y)
		{
			finalizarPartida(true);
		}
		
		
	}
	
	
	
	public void finalizarPartida(boolean ganado)
	{
		if(ganado == false)
		{
			//destapar bombas
		}	
	}
}
