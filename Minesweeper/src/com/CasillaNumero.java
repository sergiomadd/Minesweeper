package com;

public class CasillaNumero extends Casilla 
{
	private int num;
	
	public CasillaNumero(Integer pNum)
	{
		this.num = pNum;
	}
	
	public void print()
	{
		System.out.print(num);
	}
}