package com;

public class CasillaVacia extends Casilla 
{
	public String getEstado()
	{
		String state = "tapada";
		if (this.estado.esVisible())
		{
			state = "vacio";
		}
		if (this.estado.esMarcada())
		{
			state = "bandera";
		}
	}
	
	public void mostrarCasilla()
	{
		public void mostrarCasilla(Integer x, Integer y, String click)
	}
	
	private void mostrarVecinos()
	{
		
	}
	
	
	public void print()
	{
		System.out.print("0");
	}
}
