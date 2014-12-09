package model;

import jssc.*;

public class ComPort {

	String stringBuffer = new String();

	public String[] getComPorts() { //listet alle verfügbaren ComPorts auf
		String[] portNames = SerialPortList.getPortNames();
		for (int i = 0; i < portNames.length; i++) {
			System.out.println(portNames[i]);
		}
		return portNames;
	}

	public String getComData() { // gibt ComPort Daten einmal aus
		//SerialPort = new SerialPort(getComPorts());
		SerialPort serialPort = new SerialPort("COM6");
		try {
			serialPort.openPort();// Open serial port
			serialPort.setParams(38400, 8, 1, 0);// Set params.
			serialPort.setFlowControlMode(0);
			byte[] buffer = serialPort.readBytes(10);// Read 10 bytes from serial port

			stringBuffer = new String(buffer); // Write data to stringBuffer
			
			System.out.println(stringBuffer);

			serialPort.closePort();// Close serial port
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
		return stringBuffer;
	}

}