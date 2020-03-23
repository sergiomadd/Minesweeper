package com;

public interface Estado {
	
	public boolean clickDerecho(Casilla cas);
	public boolean clickIzquierdo(Casilla cas);
	public boolean esVisible();
	public boolean esMarcada();
}
