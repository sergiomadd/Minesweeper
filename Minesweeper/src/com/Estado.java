package com;

public interface Estado {
	
	public void clickDerecho(Casilla cas);
	public void clickIzquierdo(Casilla cas);
	public boolean esVisible();
	public boolean esMarcada();
}
