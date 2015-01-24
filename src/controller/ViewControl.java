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
    private LabelTest labelTest;
    private SerialPortControll serialPort;
    
    public ViewControl() {

    	serialPort = new SerialPortControll();
    	serialPort.getEventPort();
    }
    
    
    public void setDrawBoardView(){
    	this.drawBoard = new DrawBoard();
    	this.drawBoard.showView();
    	
    }
    
    public void setLabelTestView(){
    	labelTest = new LabelTest(1, this);
    	}

	public void update(Observable arg0, Object ausgelesenerString) {
		String[] sensorWerte = ausgelesenerString.toString().split(Pattern.quote(":"));
		labelTest.setSensorTextFieldInhalt(sensorWerte[1],new Integer(sensorWerte[0]));
		labelTest.setLabelGitterTooltip(sensorWerte[1], labelTest.getSensorFields(new Integer(sensorWerte[0])));
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		System.out.println(cmd);
		switch(cmd){
		
		case "Speichern":
			break;
			
		case "Laden":
			break;
			
		case "About":
			break;
		
		case "Exit":
			break;
		
		case "ComPort auswählen":
			break;
		
		case "ComPort starten":
			serialPort.deleteObservers();
			serialPort.addObserver(this);
			break;
			
		case "ComPort stoppen":
			serialPort.deleteObservers();
			break;
			
		case "Anzahl Sensoren":
			labelTest.initSensorLabels(3);
			//labelTest.setSensorTextFieldInhalt("test", 2);
			break;
			
		}
		
	}
    
}
