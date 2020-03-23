package com;

public class CasillaMina extends Casilla 
{
	public String getEstado()
	{
		String state = "tapada";
		if (this.estado.esVisible())
		{
			state = "mina";
		}
		if (this.estado.esMarcada())
		{
			state = "bandera";
		}
	}
	
	public void print()
	{
		System.out.print("B");
	}
}