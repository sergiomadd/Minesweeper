package com;

import java.util.Random;

@SuppressWarnings("deprecation")
public class Tablero extends java.util.Observable
{
	//a
	private boolean acabado;
	private Casilla[][] matriz;
	private int numBanderas;
	private int contadorCasillas;
	private Integer numBombas;
	private Integer tamX; 
	private Integer tamY;
	private boolean primerClick = true;
	
	public Tablero(Integer tamX, Integer tamY, Integer numBombas)
	{
		this.tamX=tamX;
		this.tamY=tamY;
		this.numBombas=numBombas;
		
		acabado = false;
		System.out.println(numBombas);
		this.numBanderas = numBombas;
		contadorCasillas = tamX * tamY - numBombas;
		matriz = new Casilla[tamX][tamY];
		TableroFactory factory = TableroFactory.getTableroFactory();
		
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz[i].length; j++)
			{
				if(matriz[i][j] == null) {
					matriz[i][j] = factory.crearCasilla(0);
				}
			}
		}
		
	}
	
	private void generarBombas(int notX, int notY)
	{
		TableroFactory factory = TableroFactory.getTableroFactory();
		
		//insertar minas
		int randX;
		int randY;
		Random rand1 = new Random();
		Random rand2 = new Random();
		for (int i = 0; i < numBombas; i++)
		{
			randX = rand1.nextInt(tamX);
			randY = rand2.nextInt(tamY);
			if (randX!=notX && randY !=notY)
			{
				Estado est = matriz[randX][randY].getEstado2();
				matriz[randX][randY] = factory.crearCasilla(-1); //inserta casilla tipo bomba
				matriz[randX][randY].setEstado(est); 
			}
			else 
			{
				i--; //hace otra iteracion
			}
			
		}
		
		//rellenar matriz
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz[i].length; j++)
			{
				if(!(matriz[i][j].esMina())) {
					Estado est = matriz[i][j].getEstado2();
					matriz[i][j] = factory.crearCasilla(comprobarVecinos(i,j));
					matriz[i][j].setEstado(est); 
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
				if (matriz[x[i]][y[i]].esMina())
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
				if(!this.matriz[x[i]][y[i]].esMina() && !this.matriz[x[i]][y[i]].esVisible() && !this.matriz[x[i]][y[i]].esMarcada()) 
				{
					mostrarCasilla(x[i], y[i], click);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void mostrarCasilla(Integer x, Integer y, String click)
	{
		//Si es el primer click, es click izq y la casilla no esta marcada
		if (primerClick && click.equals("izq") && !(matriz[x][y].esMarcada())) //es primer click izquierdo en no bandera
		{
			generarBombas(x,y);
			printTablero();
			primerClick=false;
		}
		//Si la casilla esta dentro del tablero y la partida no esta acabada
		if(inBounds(x,y) && acabado == false)
		{
			//Si es click izq
			if(click.equals("izq"))
			{
				//Si la casilla es vacia, no es visible y no esta marcada
				//If para recursivo
				if(this.matriz[x][y] instanceof CasillaVacia && (!matriz[x][y].esMina() && !matriz[x][y].esVisible() && !matriz[x][y].esMarcada()))
				{
					if(matriz[x][y].hacerClick(click)) 
					{
						contadorCasillas--;
					}
					String[] datos = {Integer.toString(x), Integer.toString(y), getMostrar(x,y), Integer.toString(getNum(x,y)),Integer.toString(this.numBanderas)};
					actualizarVista(datos);
					mostrarVecinos(x, y, click);
				}
				else
				{	
					//Si la casilla tiene numero
					if(!matriz[x][y].esMina() && !matriz[x][y].esVisible() && !matriz[x][y].esMarcada())
					{
						if(matriz[x][y].hacerClick(click)) {contadorCasillas--;}
						String[] datos = {Integer.toString(x), Integer.toString(y), getMostrar(x,y), Integer.toString(getNum(x,y)),Integer.toString(this.numBanderas)};
						actualizarVista(datos);
					}
				}
				//Si es mina y no esta marcada
				if(this.matriz[x][y].esMina() && !(matriz[x][y].esMarcada()))	
				{
					Casilla casilla = matriz[x][y];
					casilla.hacerClick("izq");
					String[] datos = {Integer.toString(x), Integer.toString(y), "rojo", Integer.toString(getNum(x,y)), "0"};
					actualizarVista(datos);
					finalizarPartida(false);
				}
				
				else 
				{
					//Si se acaban las casillas no mina se gana
					if(contadorCasillas <= 0)
					{
						finalizarPartida(true);
					}
				}
			}
			//If click == "der"
			else 
			{
				if(matriz[x][y].hacerClick(click)) //hay cambio de estado
				{
					if(numBanderas>0)
					{
						//Si la casilla esta marcada
						if(matriz[x][y].esMarcada())//poniendo banderas
						{
							this.numBanderas--;
							String[] datos = {Integer.toString(x), Integer.toString(y), getMostrar(x,y), Integer.toString(getNum(x,y)), Integer.toString(this.numBanderas)};
							actualizarVista(datos);
						}
						
					}
					//Si la casilla no es visible y no esta marcada y si es o no mina
					if ((matriz[x][y].esMina() && !matriz[x][y].esVisible() && !matriz[x][y].esMarcada()) || (!matriz[x][y].esMina() && !matriz[x][y].esVisible() && !matriz[x][y].esMarcada()) ) //quitando banderas
					{
						this.numBanderas++;
						String[] datos = {Integer.toString(x), Integer.toString(y), getMostrar(x,y), Integer.toString(getNum(x,y)), Integer.toString(this.numBanderas)};
						actualizarVista(datos);
					}
				}
				else //no hay cambio de estado
				{

				}
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
			String[] datos = {"0", "0", "ganada", "", "0"};
			setChanged();
			notifyObservers(datos);
			acabado = true;
			System.out.println("Has ganado :)");
		}
		else
		{
			String[] datos = {"0", "0", "perdida", "", "0"};
			setChanged();
			notifyObservers(datos);
			acabado = true;
			System.out.println("Has perdido :(");
		}
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz[i].length; j++)
			{
				Casilla casilla = matriz[i][j];
				if(casilla.esMina())
				{
					if(casilla.hacerClick("izq"))
					{
						String[] datos = {Integer.toString(i), Integer.toString(j), getMostrar(i,j), Integer.toString(getNum(i,j)), "0"};
						setChanged();
						notifyObservers(datos);
					}
				}
			}
		}
	}
	
	private void actualizarVista(String[] input)
	{
		setChanged();
		notifyObservers(input);
	}
	
	private int getNum(int x, int y)
	{
		return matriz[x][y].getNum();
	}
}