package com;

public class CasillaNumero extends Casilla 
{
	private int num;
	
	public CasillaNumero(Integer pNum)
	{
		this.num = pNum;
		this.estado = new EstadoTapadoNB();
	}
	
	public String getEstado()
	{
		String state = "tapada";
		if (this.estado.esVisible())
		{
			state = "numero";
		}
		if (this.estado.esMarcada())
		{
			state = "bandera";
		}
		return state;
	}
	
	public void print()
	{
		System.out.print(num);
	}
}