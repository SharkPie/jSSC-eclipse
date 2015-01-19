package main;

import model.SerialPortControll;
import view.DrawBoard;
import controller.*;

public class Main {
	
	static ViewControl viewControl;

		public static void main(String[] args) {
			System.out.println("Start");
			viewControl = new ViewControl();
			
			SerialPortControll serialPort = new SerialPortControll();
			serialPort.addObserver(viewControl);
			serialPort.getEventPort();
			viewControl.setLabelTestView();
			viewControl.setDrawBoardView();

			

			

	}

}

