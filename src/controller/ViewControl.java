/*
* Datei: ViewControl.java
*Kontrolliert den Programmfluss
*
*
*
*
*/

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import view.*;
import model.*;

public class ViewControl implements Observer, ActionListener{
    
    private Hauptfenster hauptfenster;
    private SerialPortControll serialPort;
    private AnzahlSensorenPopUp popUp;
    private String speichernString;
    private ComPortSelecter selectComPort;
    private XmlWriter xml;
    private String comPortString;
    

    /*
    *Konstruktor: initialisiert die popUp Fenster
    */
    public ViewControl() {

    	serialPort = new SerialPortControll();
    	popUp = new AnzahlSensorenPopUp(this);
    	selectComPort = new ComPortSelecter(this);
    	xml = new XmlWriter();
    	comPortString = null;
    }
    
    
    /*
	* Startet das Hauptfenster
	*/
    public void setHauptfensterView(){
    	hauptfenster = new Hauptfenster(1, this); //übergeben Anzahl der erwarteten Sensoren bei Programmstart, ActionListener
    	}
/*
 * (non-Javadoc)
 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
 * wird ausgeführt, wenn SerialPortControll einen Wert am ComPort anliegt
 * Datenstruktur: x:y x=Nummer des Sensors, y = Sensordaten
 * Setzen des Textfield Inhaltes und des Tooltips für die passende SensorNummer
 */
	public void update(Observable arg0, Object ausgelesenerString) {
		String[] sensorWerte = ausgelesenerString.toString().split(Pattern.quote(":"));
		String[] einzelneSensorWerte = sensorWerte[1].split(Pattern.quote(","));
		hauptfenster.setSensorTextFieldInhalt(einzelneSensorWerte[1],new Integer(sensorWerte[0]));
		StringBuilder sb = new StringBuilder();
		sb.append("Overall: ");
		sb.append(einzelneSensorWerte[0]);
		sb.append("LPM: ");
		sb.append(einzelneSensorWerte[1]);
		sb.append("LPH: ");
		sb.append(einzelneSensorWerte[2]);
		hauptfenster.setLabelGitterTooltip(sb.toString(), hauptfenster.getSensorFields(new Integer(sensorWerte[0])));
	}


	/*
	* actionListener für die MenuButtons des Hauptfensters und PopUp Fenster Button
	*/
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
		switch(cmd){
		
		case "Speichern":
			this.speichernString = hauptfenster.speichern();
			this.xml.writeXml(speichernString);
			break;
			
		case "Laden":
			try{
			speichernString = xml.readXml();
			hauptfenster.laden(speichernString);
			}catch (Exception e){
				System.err.println("Fehler in XML");
			}break;
			
		case "About":
			About newAbout = new About();
			break;
		
		case "Exit":
			System.exit(0);
			break;
		
		case "ComPort auswählen":
			selectComPort.setComPortSelecterVisible();
			break;
		
		case "ComPort starten": 
			if(comPortString == null){
				JOptionPane.showMessageDialog(null, "Es wurde kein Comport ausgewählt", "Fehler!", JOptionPane.ERROR_MESSAGE);
			}else{
				serialPort.getEventPort(comPortString);
				serialPort.deleteObservers();
				serialPort.addObserver(this);
			}
			
			break;
			
		case "ComPort stoppen":
			try{
			serialPort.close();
			serialPort.deleteObservers();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Comport wurde nicht gestartet!", "Fehler!", JOptionPane.ERROR_MESSAGE);
			}
			break;
			
		case "Anzahl Sensoren":
			popUp.setVisible(hauptfenster.getAnzahlSensor());
			break;
			
		case "Sensor auswählen":
			hauptfenster.setSensorLabels(popUp.getAnzahlSensoren());
			popUp.dispose();
			break;
		case "ComPort wahl":
			comPortString = selectComPort.getComPort();
			//serialPort.getEventPort(selectComPort.getComPort());
			selectComPort.setDispose();
			break;
			
		}
		
	}
    
}
