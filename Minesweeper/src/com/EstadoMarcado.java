package com;

public class EstadoMarcado implements Estado
{
	
	public boolean clickDerecho(Casilla cas)
	{
		cas.setEstado(new EstadoTapado());
		return true;
	}
	
	public boolean clickIzquierdo(Casilla cas)
	{
		return false;
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
