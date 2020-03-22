package com;

public class EstadoTapadoB implements Estado
{
	
	public void clickDerecho(Casilla cas)
	{
		//TODO
	}
	
	public void clickIzquierdo(Casilla cas)
	{
		Buscaminas.getBuscaminas().acabarPartida();
	}
}
