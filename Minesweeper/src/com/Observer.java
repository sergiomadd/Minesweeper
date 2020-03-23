package com;

import com.Graphics.Coordenada;

public interface Observer 
{
	public void update(Observer tab, Coordenada[] cambios);
}
