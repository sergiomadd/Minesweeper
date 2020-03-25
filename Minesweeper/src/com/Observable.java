package com;

import java.util.ArrayList;

import javax.swing.JButton;

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
	
	public void notifyObservers(int x, int y)
	{
		for(Observer o : observers)
		{
			o.update(this, x, y);
		}
	}
	
	public String getMostrar(int x, int y)
	{
		return "";
	}
	
	public void setChanged()
	{
		this.changed = true;
	}
}
