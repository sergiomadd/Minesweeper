package com;

public class EstadoTapadoNB implements Estado
{
	
	public void clickDerecho(Casilla cas)
	{
		cas.setEstado(new EstadoMarcado(this));
	}
	
	public void clickIzquierdo(Casilla cas)
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
