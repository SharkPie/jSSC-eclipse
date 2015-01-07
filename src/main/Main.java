package main;

import controller.*;

public class Main {
	
	static ViewControl viewControl;

		public static void main(String[] args) {
			System.out.println("Start");
			viewControl = new ViewControl();
			Ausgabe ausgabe = new Ausgabe();
			ausgabe.addObserver(viewControl);
			viewControl.showView();
			ausgabe.run();
			

			

	}

}

