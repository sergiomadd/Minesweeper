package com;

public abstract class Casilla 
{
	private Estado state;
	
	public Casilla()
	{
		this.visible = false;
	}
	
	public boolean setVisible(String p)
	{
		return true;
		//cambiar2
	}
	
	public void setEstado(Estado pState)
	{
		this.state = pState;
	}
	
	public void print()
	{
		
	}
}
