package com;

import java.util.ArrayList;

public class Observable 
{	
	private ArrayList<Observer> observers;
	private boolean changed;
	
	public Observable()
	{
		this.observers = new ArrayList<Observer>();
		this.changed = false;
	}
	
	public void addObserver(Observer o)
	{
		observers.add(o);
	}
	
	public void deleteObserver(Observer o)
	{
		observers.remove(o);
	}
	
	public void notifyObservers()
	{
		
	}
	
	public void setChanged()
	{
		this.changed = true;
	}
}
