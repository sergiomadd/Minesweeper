package com.Graphics;

public class Coordenada {
	private int x;
	private int y;
	public Coordenada(int pX, int pY) {
		x= pX;
		y=pY;
	}

	public void displayCoord() {
		System.out.println(x+","+y);
	}
	
}
