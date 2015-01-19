/*
* Datei: ComPort.java
*
* Die Klasse ist für die Kommunikation mit den ComPorts Zuständig
* TEST KLASSE. KANN NOCH GELÖSCHT WERDEN.
*
*
*/

package model;

import jssc.*;

public class ComPort {

	String stringBuffer = new String();

	//listet alle verfügbaren ComPorts auf und gibt ein String-Array mit allen ComPorts zurück.
	//Die ComPorts müssen nicht zwingend von dem Programm auslesbar/darstellbar sein.
	public String[] getComPorts() { 
		String[] portNames = SerialPortList.getPortNames();
		for (int i = 0; i < portNames.length; i++) {
			System.out.println(portNames[i]);
		}
		return portNames;
	}

	// gibt ComPort Daten einmal aus, ist zu Testzwecken eingebaut, Com Port Daten sind vorgegeben
	public String getComData() { 
		//SerialPort = new SerialPort(getComPorts());
		SerialPort serialPort = new SerialPort("COM5");
		try {
			serialPort.openPort();// Open serial port
			serialPort.setParams(38400, 8, 1, 0);// Set params.
			serialPort.setFlowControlMode(0);
			byte[] buffer = serialPort.readBytes(10);// Read 10 bytes from serial port

			stringBuffer = new String(buffer); // Write data to stringBuffer
			
			stringBuffer = stringBuffer.replace("\n\r", "");
			
			//System.out.println(stringBuffer);

			serialPort.closePort();// Close serial port
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
		return stringBuffer;
	}

}