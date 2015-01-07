package controller;

import java.util.Observable;

import javafx.beans.InvalidationListener;
import model.ComPort;

public class Ausgabe extends Observable{
	ComPort model = new ComPort();
	String ausgabe = new String();

	public void run() {
		//for(int i=0; i<10; i++)
		while(true){
			setChanged();
			ausgabe = model.getComData();
			notifyObservers(ausgabe);
			
		}
	}

}
