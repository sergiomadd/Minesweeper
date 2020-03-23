package com;

public class EstadoMarcado implements Estado
{
	
	private Estado previo;
	
	public EstadoMarcado(Estado pPrevio)
	{
		previo = pPrevio;
	}
	
	public boolean clickDerecho(Casilla cas)
	{
		cas.setEstado(previo);
	}
	
	public boolean clickIzquierdo(Casilla cas)
	{
		//no hacer nada, ya esta destapada
	}
	
	public boolean esVisible()
	{
		return false;
	}
	
	public boolean esMarcada()
	{
		return true;
	}
}
