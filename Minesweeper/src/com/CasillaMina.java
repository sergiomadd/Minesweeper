package com;

public class CasillaMina extends Casilla 
{
	public CasillaMina()
	{
		this.estado = new EstadoTapadoB();
	}
	
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
		
		return state;
	}
	
	public void print()
	{
		System.out.print("B");
	}
}