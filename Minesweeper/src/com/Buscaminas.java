package com;

import java.io.BufferedReader;
import java.io.File;
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
	private String path;
	
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
	
	public void actualizarPuntuaciones(int puntos)
	{
		//System.getProperty("user.dir")
		path = "resources"+ File.separator + "datos.txt";
		ArrayList<String> puntuaciones = new ArrayList<String>();
		 try
		 {
			 //Leemos el archivo de puntuaciones y metemos en un vector
		     Scanner entrada = new Scanner(new File(path)); 
		     
		     while (entrada.hasNextLine()) 
		     { 
		    	 puntuaciones.add(entrada.nextLine());
			 } 
			 entrada.close();
		 
			 System.out.println("VECTOR DE PUNTUACIONES");
			 System.out.println(puntuaciones);
			 System.out.println("-----------------------");
			 
			 Iterator<String> itr = puntuaciones.iterator();
			 //itr es un string de tipo:
			 //nombre puntos
			 int pos = 0;
			 boolean added= false;
			 
			 if(puntuaciones.isEmpty()) 
			 { //Si no hay puntuaciones previas añadimos sin comprobar
				 puntuaciones.add(pos, this.user + " " + puntos);
			 }
			 else
			 { //Si hay puntuaciones previas comprobamos la posicion a añadir
				 int puntosActual = Integer.parseInt((itr.next()).split(" ")[1]);
				 while(itr.hasNext() && !added)
				 { 
					 //Introducimos nueva puntuacion en la posicion correspondiente
					 if(puntos < puntosActual)
					 {
						 puntuaciones.add(pos, this.user + " " + puntos);
						 added = true;
					 }
					 else
					 {
						 puntosActual = Integer.parseInt((itr.next()).split(" ")[1]);
						 pos++;
					 } 
				 }
			 }
			 
			 
		 }
		 catch(Exception e) 
		 {
			 System.out.println("Error al leer el archivo");
			 e.printStackTrace();
		 }
		 try
		 {
		 FileWriter writer = new FileWriter("resources"+ File.separator + "datos.txt", false);
         Iterator<String> itr = puntuaciones.iterator();
         String actual;
		 while(itr.hasNext())
		 {
			 actual = itr.next();
			 writer.write(actual +"\n");
		 }
         writer.close();
		 } 
		 catch (IOException e) 
		 {
			 System.out.println("Error al escribir en el archivo");
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
