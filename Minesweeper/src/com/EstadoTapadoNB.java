package com;

public class EstadoTapadoNB implements Estado
{
	
	public void clickDerecho(Casilla cas)
	{
		//TODO
	}
	
	public void clickIzquierdo(Casilla cas)
	{
		cas.setEstado(new EstadoDestapado());
	}
}
