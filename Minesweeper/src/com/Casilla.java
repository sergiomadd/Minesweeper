package com;

public abstract class Casilla 
{
	protected Estado estado;
	
	public Casilla()
	{
		
	}
	
	public void setEstado(Estado pState)
	{
		this.estado = pState;
	}
	
	public boolean hacerClick(String click)
	{
		boolean devolver = false;
		if(click.equals("der"))
		{
			devolver = estado.clickDerecho(this);
		}
		else if(click.equals("izq"))
		{
			devolver = estado.clickIzquierdo(this);
		}
		return devolver;
	}
	
	public void print()
	{
		
	}
}
