package com;

public abstract class Casilla 
{
	private boolean visible;
	
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
	
	public void print()
	{
		
	}
}
