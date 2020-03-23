package com;

public class CasillaNumero extends Casilla 
{
	private int num;
	
	public CasillaNumero(Integer pNum)
	{
		this.num = pNum;
		this.state = new EstadoTapadoNB();
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
	}
	
	public void print()
	{
		System.out.print(num);
	}
}