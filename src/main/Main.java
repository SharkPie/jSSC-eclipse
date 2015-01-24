/*
* Datei: Main.java
*
* Die Klasse wird nur zum starten der eigentlichen Anwendung genutzt
*
*
*
*/

package main;

import controller.*;

public class Main {
	
	static ViewControl viewControl;

		public static void main(String[] args) {
			System.out.println("Start");
			viewControl = new ViewControl();
			
			//SerialPortControll serialPort = new SerialPortControll();
			//serialPort.addObserver(viewControl);
			//serialPort.getEventPort();
			viewControl.setHauptfensterView();
		

	}

}

