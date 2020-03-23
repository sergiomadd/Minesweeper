package com;

public class CasillaVacia extends Casilla 
{
	public CasillaVacia()
	{
		this.estado = new EstadoTapadoNB();
	}
	
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
	
	public void print()
	{
		System.out.print("0");
	}
}
