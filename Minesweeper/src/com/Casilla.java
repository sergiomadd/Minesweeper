package com;

public abstract class Casilla 
{
	private boolean visible;
	private Estado estado;
	
	public Casilla()
	{
		this.visible = false;
	}
	
	public boolean setVisible(String p)
	{
		this.visible = true;
		return true;
		//cambiar2
	}
	
	public void setEstado(Estado pEstado)
	{
		this.estado = pEstado;
	}
	
	public void print()
	{
		
	}
}
