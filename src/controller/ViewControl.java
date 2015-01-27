/*
* Datei: ViewControl.java
*
*
*
*
*
*/

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import view.*;
import model.*;

public class ViewControl implements Observer, ActionListener{
    
    private MainWindow view;
    private DrawBoard drawBoard;
    private Hauptfenster labelTest;
    private SerialPortControll serialPort;
    private AnzahlSensorenPopUp popUp;
    
    public ViewControl() {

    	serialPort = new SerialPortControll();
    	serialPort.getEventPort();
    	popUp = new AnzahlSensorenPopUp(this);
    }
    
    
    public void setDrawBoardView(){
    	this.drawBoard = new DrawBoard();
    	this.drawBoard.showView();
    	
    }
    
    public void setHauptfensterView(){
    	labelTest = new Hauptfenster(1, this);
    	}

	public void update(Observable arg0, Object ausgelesenerString) {
		String[] sensorWerte = ausgelesenerString.toString().split(Pattern.quote(":"));
		labelTest.setSensorTextFieldInhalt(sensorWerte[1],new Integer(sensorWerte[0]));
		labelTest.setLabelGitterTooltip(sensorWerte[1], labelTest.getSensorFields(new Integer(sensorWerte[0])));
	}


	@Override
	public void actionPerformed(ActionEvent event) {//actionListener für die MenuButtons und PopUp Fenster Button
		String cmd = event.getActionCommand();
		switch(cmd){
		
		case "Speichern":
			System.out.println("test");
			labelTest.test();
			break;
			
		case "Laden":
			break;
			
		case "About":
			break;
		
		case "Exit":
			break;
		
		case "ComPort auswählen":
			//ComPortSelecter select = new ComPortSelecter();
			break;
		
		case "ComPort starten":
			serialPort.deleteObservers();
			serialPort.addObserver(this);
			break;
			
		case "ComPort stoppen":
			serialPort.deleteObservers();
			break;
			
		case "Anzahl Sensoren":
			popUp.setVisible(labelTest.getAnzahlSensor());
			break;
			
		case "OK":
			labelTest.setSensorLabels(popUp.getAnzahlSensoren());
			popUp.dispose();
			break;
			
		}
		
	}
    
}
