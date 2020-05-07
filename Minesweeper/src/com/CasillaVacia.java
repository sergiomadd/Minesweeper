package com;

public class CasillaVacia extends Casilla 
{
	public CasillaVacia()
	{
		this.estado = new EstadoTapado();
	}
	
	@Override
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
		return state;
	}
	
	public void print()
	{
		System.out.print("0");
	}
}
