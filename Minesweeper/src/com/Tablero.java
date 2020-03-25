package com;

import java.util.Random;

import com.Graphics.Coordenada;

public class Tablero extends Observable
{
	private Casilla[][] matriz;
	private int dificultad;
	
	private int contadorCasillas;
	
	public Tablero(Integer tamX, Integer tamY, Integer numBombas)
	{
		TableroFactory factory = TableroFactory.getTableroFactory();
		contadorCasillas = tamX * tamY - numBombas;

		matriz = new Casilla[tamX][tamY];
		//insertar minas
		int randX;
		int randY;
		Random rand1 = new Random();
		Random rand2 = new Random();
		for (int i = 0; i < numBombas; i++)
		{
			randX = rand1.nextInt(tamX);
			randY = rand2.nextInt(tamY);
			matriz[randX][randY] = factory.crearCasilla(-1);
		}
		
		//rellenar matriz
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz[i].length; j++)
			{
				Casilla casilla = matriz[i][j];
				if(matriz[i][j] == null) {
					matriz[i][j] = factory.crearCasilla(comprobarVecinos(i,j));
				}
			}
		}
	}
	
	
	private int comprobarVecinos(int posX, int posY)
	{//
		int count = 0;
		
		int[] x = new int[8];
		x[0] = posX;
		x[1] = posX;
		x[2] = posX+1;
		x[3] = posX-1;
		x[4] = posX+1;
		x[5] = posX+1;
		x[6] = posX-1;
		x[7] = posX-1;
		
		int[] y = new int[8];
		y[0] = posY+1;
		y[1] = posY-1;
		y[2] = posY;
		y[3] = posY;
		y[4] = posY+1;
		y[5] = posY-1;
		y[6] = posY+1;
		y[7] = posY-1;
		
		for (int i = 0; i < x.length; i++)
		{
			if(inBounds(x[i],y[i]))
			{
				if (matriz[x[i]][y[i]] instanceof CasillaMina)
				{
					count++;
				}
			}
		}
		return count;
	}
	
	private boolean inBounds(int posX, int posY) {
		boolean inBounds = false;
		if( ((posX >= 0) && (posX < matriz.length)) && ((posY >= 0) && (posY < matriz[0].length)))
		{
			inBounds = true;
		}
		return inBounds;
	}
	
	private void mostrarVecinos(int posX, int posY, String click)
	{
		System.out.println("Mostrando vecinos");
		int count = 0;
		
		int[] x = new int[8];
		x[0] = posX;
		x[1] = posX;
		x[2] = posX+1;
		x[3] = posX-1;
		x[4] = posX+1;
		x[5] = posX+1;
		x[6] = posX-1;
		x[7] = posX-1;
		
		int[] y = new int[8];
		y[0] = posY+1;
		y[1] = posY-1;
		y[2] = posY;
		y[3] = posY;
		y[4] = posY+1;
		y[5] = posY-1;
		y[6] = posY+1;
		y[7] = posY-1;
		
		for (int i = 0; i < x.length; i++)
		{
			if(inBounds(x[i],y[i]))
			{
				if(this.matriz[x[i]][y[i]].getEstado2() instanceof EstadoTapadoNB) 
				{
					mostrarCasilla(x[i], y[i], click);
				}
			}
		}
	}
	
	public void mostrarCasilla(Integer x, Integer y, String click)
	{
		if(inBounds(x,y))
		{
			if(this.matriz[x][y] instanceof CasillaVacia && (this.matriz[x][y].getEstado2() instanceof EstadoTapadoNB))
			{
				if(matriz[x][y].hacerClick(click)) {contadorCasillas--;}
				mostrarVecinos(x, y, click);
			}
			else
			{	
				if(this.matriz[x][y].getEstado2() instanceof EstadoTapadoNB)
				{
					System.out.println("Tapado");
				}

				if(matriz[x][y].hacerClick(click)) {contadorCasillas--;}
				
				notifyObservers(x, y);
				
				if(this.matriz[x][y].getEstado2() instanceof EstadoDestapado)
				{
					System.out.println("Destapado");
				}
			}
		}
		
		if(this.matriz[x][y] instanceof CasillaMina)
		{
			finalizarPartida(false);
		}
		
		else 
		{
			if(contadorCasillas <= 0)
			{
				finalizarPartida(true);
			}
		}
	}
	
	@Override
	public void notifyObservers(int x, int y)
	{
		Buscaminas.getBuscaminas().getVistaJuego().update(this, x, y);
	}
	
	public void printTablero()
	{
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz[i].length; j++)
			{
				matriz[i][j].print(); //arreglar
			}
			System.out.println();
		}
	}
	
	@Override
	public String getMostrar(int x, int y)
	{
		String estado;
		estado = matriz[x][y].getEstado();
		
		//vacio
		//numero
		//bandera
		//bomba
		//tapado
		
		return estado;
	}
	
	public Casilla[][] getMatriz()
	{
		return matriz;
	}
	
	public void finalizarPartida(boolean ganado)
	{
		if(ganado == false)
		{
			for (int i = 0; i < matriz.length; i++)
			{
				for (int j = 0; j < matriz[i].length; j++)
				{
					Casilla casilla = matriz[i][j];
					if(casilla instanceof CasillaMina)
					{
						notifyObservers(i, j);
					}
				}
			}
			System.out.println("Has perdido :(");
		}
		else
		{
			//mostrar puntuacion.
			System.out.println("Has ganado :D");
		}
		//Buscaminas.getBuscaminas().getVistaJuego().reset();
	}
}