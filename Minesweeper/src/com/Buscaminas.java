package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import com.Graphics.Coordenada;

public class Buscaminas 
{
	private int dificultad = 1;
	private String user = "";
	private Tablero tablero;
	private static Buscaminas miBuscaminas;
	private HashMap<Integer, String> datos;
	
	private Buscaminas()
	{
		
	}
	
	public static synchronized Buscaminas getBuscaminas()
	{
		if(miBuscaminas == null)
		{
			miBuscaminas = new Buscaminas();
		}
		return miBuscaminas;
	}
	
	public void iniciarPartida()
	{	
		crearTablero();
		tablero.printTablero();
		System.out.println();
	}
	
	public void crearTablero()
	{
		switch(dificultad)
		{
		case 1:
			tablero = new Tablero(7, 10, 10*1);
			break;
		case 2:
			tablero = new Tablero(10, 15, 15*2);
			break;
		case 3:
			tablero = new Tablero(12, 25, 25*3);
			break;
		default:
			System.out.println("Error creacion de tablero (buscaminas)");
			break;
		}
	}
	
	public void anadirPuntuacion(Integer puntos)
	{
	    try {
            FileWriter writer = new FileWriter("datos.txt", true);
            writer.write(this.user + " " + puntos);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	    ordenarPuntuacion();
	}
	
	private void ordenarPuntuacion()
	{
		ArrayList<String> info = new ArrayList<String>();
		 try{
			 //Leemos el archivo de puntuaciones
			 BufferedReader entrada = new BufferedReader(new FileReader("datos.txt"));
			 String linea = entrada.readLine();
			 String ultimo = "";
			 while (linea != null) 
			 {
			 	 linea = entrada.readLine();
			 	 if(linea != null)
			 	 {
					 info.add(linea);
					 ultimo = linea;
					 System.out.println("Linea: " + linea);
			 	 }
			 }
			 entrada.close();
			 //Iteramos sobre la lista de informacion del archivo
			 
			 Iterator<String> itr = info.iterator();
			 String actual = itr.next();
			 int pos = 0;
			 //ultimo es los puntos de la partida jugada
			 //actual es el string de la partida que se esta recorriendo
			 //pos es la posicion en el array de actual
			 while(itr.hasNext())
			 {//Introducimos la partida recien jugada en el ArrayList en la posicion correspondiente
				 if(Integer.parseInt(ultimo.split(" ")[1]) < Integer.parseInt(actual.split(" ")[1]))
				 {
					 info.add(pos, ultimo);
					 info.remove(-1);
				 }
				 actual = itr.next();
				 pos++;
			 }
			 entrada.close();
		 }catch(Exception e)
		 {
			 System.out.println("No se puede crear fichero");
			 e.printStackTrace();
		 }
		
		 System.out.println("Arraylist: ");
		 System.out.println(info);
		 
		 try {
            FileWriter writer = new FileWriter("datos.txt", false);
            Iterator<String> itr = info.iterator();
            String actual;
			 while(itr.hasNext())
			 {
				 actual = itr.next();
				 writer.write(actual +"\n");
			 }
            writer.close();
         } catch (IOException e) {
            e.printStackTrace();
	        }
	}
	
	public void clicarCasilla(Coordenada coord, String click) 
	{
		tablero.mostrarCasilla(coord.getX(), coord.getY(), click);
	}
	
	public Tablero getTab()
	{
		return tablero;
	}
	
	public void setUser(String pUser)
	{
		this.user = pUser;
	}
	
	public void setDif(int pDif)
	{
		this.dificultad = pDif;
	}
	
}
