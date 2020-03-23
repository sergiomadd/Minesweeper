package com;

import java.util.ArrayList;

import com.Graphics.Coordenada;

public class Observable 
{	
	private ArrayList<Observer> observers;
	private boolean changed;
	
	public Observable()
	{
		this.observers = new ArrayList<Observer>();
		this.changed = false;
	}
	//
	public void addObserver(Observer o)
	{
		observers.add(o);
	}
	
	public void deleteObserver(Observer o)
	{
		observers.remove(o);
	}
	
	public void notifyObservers(Coordenada coord)
	{
		
	}
	
	public void setChanged()
	{
		this.changed = true;
	}
}
