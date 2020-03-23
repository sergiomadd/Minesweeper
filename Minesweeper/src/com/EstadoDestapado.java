package com;

public class EstadoDestapado implements Estado
{
	public boolean clickDerecho(Casilla cas)
	{
		//no hacer nada, ya esta destapada
		return false;
	}
	
	public boolean clickIzquierdo(Casilla cas)
	{
		//no hacer nada, ya esta destapada
		return false;
	}
	
	public boolean esVisible()
	{
		return true;
	}
	
	public boolean esMarcada()
	{
		return false;
	}
}
