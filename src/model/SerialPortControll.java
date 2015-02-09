/*
* Datei: SerialPortControll.java
*
* Die Klasse ist für die Kommunikation mit den ComPorts Zuständig
*
*
*
*/

package model;

import java.util.Observable;



//import view.*;
import jssc.*;

public class SerialPortControll extends Observable{

	static SerialPort serialPort;
	String stringBuffer = new String();
	
	public SerialPortControll() {
		
	}


	/*
	*listet alle verfügbaren ComPorts auf und gibt ein String-Array mit allen ComPorts zurück.
	*Die ComPorts müssen nicht zwingend von dem Programm auslesbar/darstellbar sein.
	*/
	public String[] getComPorts() {
		String[] portNames = SerialPortList.getPortNames();
		return portNames;
	}

	/*
	* festlegen der Allgmeinen Parameter auf dem ausgewählten ComPorts
	*/
	public void getEventPort(String comPort) {

		serialPort = new SerialPort(comPort); 
		try {
			serialPort.openPort();//Open port
			serialPort.setParams(38400, 8, 1, 0); //ComPort Paramter festlegen, Baud, Datenbits, Stopbits, Parität
			serialPort.setFlowControlMode(0);
			int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR; //Über die MAske werden die Ereignisse zum auslösen ausgewählt
			serialPort.setEventsMask(mask);//Set mask
			serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
		}
		catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}


	class SerialPortReader implements SerialPortEventListener {

		public void serialEvent(SerialPortEvent event) {
			
			if(event.isRXCHAR()){ //prüft ob Daten am ComPort anliegen
				if(event.getEventValue() % 47 == 0){ //Überprüft Anzahl Bytes im Input Buffer
					try {
						
						byte buffer[] = serialPort.readBytes(47);
						stringBuffer = new String(buffer); //schreibt Daten in den stringBuffer

						setChanged();
						stringBuffer = stringBuffer.replace("\n\r", "");
						notifyObservers(stringBuffer);		
					}
					catch (SerialPortException ex) {
						System.out.print(ex);
					}
				}
			}
			else if(event.isCTS()){
				if(event.getEventValue() == 1){
					System.out.println("CTS - ON"); 
				}
				else {
					System.out.println("CTS - OFF");
				}
			}
			else if(event.isDSR()){
				if(event.getEventValue() == 1){
					System.out.println("DSR - ON");
				}
				else {
					System.out.println("DSR - OFF");
				}
			}
		}
	}
	
	public void close(){
		try {
			serialPort.closePort();
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	* Getter Funktion um die Daten auszugeben
	*/
	public String getStringBuffer() {
		return stringBuffer;
	}
}
