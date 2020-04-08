package com;

import java.util.Random;

@SuppressWarnings("deprecation")
public class Tablero extends java.util.Observable
{
	private Casilla[][] matriz;
	private int numBanderas;
	private int contadorCasillas;
	
	public Tablero(Integer tamX, Integer tamY, Integer numBombas)
	{
		this.numBanderas = numBombas;
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
	{
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
	
	@SuppressWarnings("deprecation")
	public void mostrarCasilla(Integer x, Integer y, String click)
	{
		if(inBounds(x,y))
		{
			if(click.equals("izq"))
			{
				if(this.matriz[x][y] instanceof CasillaVacia && (this.matriz[x][y].getEstado2() instanceof EstadoTapadoNB))
				{
					if(matriz[x][y].hacerClick(click)) 
					{
						contadorCasillas--;
					}
					String[] datos = {Integer.toString(x), Integer.toString(y), getMostrar(x,y), Integer.toString(getNum(x,y)),Integer.toString(this.numBanderas)};
					setChanged();
					notifyObservers(datos);
					mostrarVecinos(x, y, click);
				}
				else
				{	
					if(this.matriz[x][y].getEstado2() instanceof EstadoTapadoNB)
					{
						if(matriz[x][y].hacerClick(click)) {contadorCasillas--;}
						String[] datos = {Integer.toString(x), Integer.toString(y), getMostrar(x,y), Integer.toString(getNum(x,y)),Integer.toString(this.numBanderas)};
						setChanged();
						notifyObservers(datos);
					}
				}
				
				if(this.matriz[x][y] instanceof CasillaMina)
				{
					//Pregunta: Acceder a este metodo asi, o llamarlo desde la propia casillamina pasando por buscaminas.
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
			else //If click == "der"
			{
				if(matriz[x][y].hacerClick(click))
				{
					if(numBanderas>0)
					{
						this.numBanderas--;
						System.out.println(getMostrar(x,y));
						String[] datos = {Integer.toString(x), Integer.toString(y), getMostrar(x,y), Integer.toString(getNum(x,y)), Integer.toString(this.numBanderas)};
						setChanged();
						notifyObservers(datos);
						System.out.println("Marcar 1");
					}
				}
				else
				{
					this.numBanderas++;
					System.out.println(getMostrar(x,y));
					String[] datos = {Integer.toString(x), Integer.toString(y), getMostrar(x,y), Integer.toString(getNum(x,y)), Integer.toString(this.numBanderas)};
					setChanged();
					notifyObservers(datos);
					System.out.println("Marcar 2");
				}
				System.out.println(numBanderas);
			}
		}
	}
	

	
	public void printTablero()
	{
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz[i].length; j++)
			{
				matriz[i][j].print();
			}
			System.out.println();
		}
	}
	
	private String getMostrar(int x, int y)
	{
		String estado;
		estado = matriz[x][y].getEstado();
		return estado;
	}
	
	public Casilla[][] getMatriz()
	{
		return matriz;
	}
	
	@SuppressWarnings("deprecation")
	public void finalizarPartida(boolean ganado) 
	{
		if(ganado)
		{
			System.out.println("Has ganado :)");
		}
		else
		{
			System.out.println("Has perdido :(");
		}
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz[i].length; j++)
			{
				Casilla casilla = matriz[i][j];
				if(casilla instanceof CasillaMina)
				{
					casilla.hacerClick("izq");
					String[] datos = {Integer.toString(i), Integer.toString(j), getMostrar(i,j), Integer.toString(getNum(i,j)), "0"};
					setChanged();
					notifyObservers(datos);
				}
			}
		}
		String[] datos = {"0", "0", "acabada", ""};
		setChanged();
		notifyObservers(datos);
	}
	
	private int getNum(int x, int y)
	{
		return matriz[x][y].getNum();
	}
}