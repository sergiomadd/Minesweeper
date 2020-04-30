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
		path = "resources"+ File.separator + "datos.txt";
		ArrayList<String> info = new ArrayList<String>();
		 try{
			 //Leemos el archivo de puntuaciones
			 File file = new File(path); 
		     Scanner entrada = new Scanner(file); 
		     
		     while (entrada.hasNextLine()) 
		     { 
		    	 String linea = entrada.nextLine();
		    	 System.out.println(linea);
		    	 info.add(linea);
			 } 
			 entrada.close();
			 
			 //Iteramos sobre la lista de informacion del archivo
			 
			 Iterator<String> itr = info.iterator();
			 String actual = "";
			 int pos = 0;
			 
			 //ultimo es los puntos de la partida jugada
			 //actual es el string de la partida que se esta recorriendo
			 //pos es la posicion en el array de actual
			 if(!itr.hasNext())
			 {
				 info.add(pos, this.user + " " + puntos);
			 }
			 else
			 {
				 while(itr.hasNext())
				 {
					 actual = itr.next();
					 //Introducimos la partida recien jugada en el ArrayList en la posicion correspondiente
					 if(puntos < Integer.parseInt(actual.split(" ")[1]))
					 {
						 info.add(pos, this.user + " " + puntos);
					 }
					 pos++;
				 }
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
            FileWriter writer = new FileWriter("resources"+ File.separator + "datos.txt", false);
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
