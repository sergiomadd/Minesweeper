package com;

import com.Graphics.VistaJuego;

public class Main {

	public static void main(String[] args) {
		Controlador.getControlador().iniciarPartida();
		VistaJuego vistaJuego = new VistaJuego(7, 10, Buscaminas.getBuscaminas().getTab());
	}
}
