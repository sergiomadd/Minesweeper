package com;

public abstract class Casilla 
{
	protected Estado estado;
	
	public Casilla()
	{
		Estado inicial = new EstadoTapado();
		setEstado(inicial);
	}
	
	public void setEstado(Estado pState)
	{
		this.estado = pState;
	}
	
	public String getEstado()
	{
		return "";
	}
	
	public Estado getEstado2()
	{
		return estado;
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
	
	public boolean esMina()
	{
		return false;
	}
	
	public boolean esVisible()
	{
		return estado.esVisible();
	}
	
	public boolean esMarcada()
	{
		return estado.esMarcada();
	}
	
	public int getNum() 
	{
		return 0;
	}
	
	
	public void print()
	{
		
	}
}
