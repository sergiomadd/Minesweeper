package com;

public abstract class Casilla 
{
	private boolean visible;
	
	public Casilla()
	{
		this.visible = false;
	}
	
	public void setVisible()
	{
		this.visible = true;
	}
}
