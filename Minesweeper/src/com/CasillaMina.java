package com;

public class CasillaMina extends Casilla 
{
	public CasillaMina()
	{
		this.estado = new EstadoTapadoB();
	}
	
	@Override
	public String getEstado()
	{
		String state = "tapada";
		if (this.estado.esVisible())
		{
			state = "bomba";
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