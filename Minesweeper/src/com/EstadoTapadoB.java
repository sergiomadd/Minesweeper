package com;

public class EstadoTapadoB implements Estado
{
	public boolean clickDerecho(Casilla cas)
	{
		cas.setEstado(new EstadoMarcado(this));
		return true;
	}
	
	public boolean clickIzquierdo(Casilla cas)
	{
		cas.setEstado(new EstadoDestapado());
		return true;
	}
	
	public boolean esVisible()
	{
		return false;
	}
	
	public boolean esMarcada()
	{
		return false;
	}
}
