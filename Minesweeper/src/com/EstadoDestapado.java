package com;

public class EstadoDestapado implements Estado
{
	public boolean clickDerecho(Casilla cas)
	{
		//no hacer nada, ya esta destapada
	}
	
	public boolean clickIzquierdo(Casilla cas)
	{
		//no hacer nada, ya esta destapada
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
