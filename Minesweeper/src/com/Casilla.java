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
	
	public void hacerClick(String click)
	{
		if(click.equals("der"))
		{
			estado.clickDerecho(this);
		}
		else if(click.equals("izq"))
		{
			estado.clickIzquierdo(this);
		}
	}
	
	public void print()
	{
		
	}
}
