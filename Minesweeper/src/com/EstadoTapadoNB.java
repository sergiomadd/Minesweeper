package com;

public class EstadoTapadoNB implements Estado
{
	public boolean clickDerecho(Casilla cas)
	{
		cas.setEstado(new EstadoMarcado(this));
	}
	
	public boolean clickIzquierdo(Casilla cas)
	{
		cas.setEstado(new EstadoDestapado());
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
