package com;

public class EstadoTapadoB implements Estado
{
	
	public void clickDerecho(Casilla cas)
	{
		cas.setEstado(new EstadoMarcado(this));
	}
	
	public void clickIzquierdo(Casilla cas)
	{
		Buscaminas.getBuscaminas().acabarPartida();
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
