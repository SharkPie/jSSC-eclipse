
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import view.*;
import model.*;

public class ViewControl {
    
    private MainWindow _view;
    private ComPort _model;
    
    public ViewControl() {
        this._view = new MainWindow();
        this._model = new ComPort();
        
        addListener();
    }
    
    
    public void showView() {
        this._view.setVisible(true);
        //this._model.getComData(); //zum testen
    }
    
    private void addListener() {
    	this._view.setExitButtonListener(new ExitButtonListener());
    	//this._view.setComEventListener(new ComEventlistener());
    }
    
    
    class ExitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
    	
    }
  
    class ComEventListener implements EventListener {

		
    	
    }
    
    
}
