package model;

import java.util.Observable;

//import view.*;
import jssc.*;

public class SerialPortControll extends Observable{

	//private MainWindow _view;
	
	static SerialPort serialPort;
	String stringBuffer = new String();
	
	public SerialPortControll() {
		//this._view = new MainWindow();
		
	}

	public void getEventPort() {

		serialPort = new SerialPort("COM3"); 
		try {
			serialPort.openPort();//Open port
			serialPort.setParams(38400, 8, 1, 0);//Set params
			serialPort.setFlowControlMode(0);
			int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
			serialPort.setEventsMask(mask);//Set mask
			serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
		}
		catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}


	class SerialPortReader implements SerialPortEventListener {

		public void serialEvent(SerialPortEvent event) {
			if(event.isRXCHAR()){//If data is available
				if(event.getEventValue() == 24){//Check bytes count in the input buffer
					//Read data, if 10 bytes available 
					try {
						byte buffer[] = serialPort.readBytes(24);
						stringBuffer = new String(buffer); // Write data to stringBuffer

						setChanged();
						stringBuffer = stringBuffer.replace("\n\r", "");
						notifyObservers(stringBuffer);
//						System.out.println(stringBuffer);

//						_view.setjLMesswert(stringBuffer);
//						_view.setjTMesswert(stringBuffer);
//						_view.validate();
						
					}
					catch (SerialPortException ex) {
						System.out.print(ex);
					}
				}
			}
			else if(event.isCTS()){//If CTS line has changed state
				if(event.getEventValue() == 1){//If line is ON
					System.out.println("CTS - ON");
				}
				else {
					System.out.println("CTS - OFF");
				}
			}
			else if(event.isDSR()){///If DSR line has changed state
				if(event.getEventValue() == 1){//If line is ON
					System.out.println("DSR - ON");
				}
				else {
					System.out.println("DSR - OFF");
				}
			}
		}
	}
	
	public String getStringBuffer() {
		return stringBuffer;
	}
}
