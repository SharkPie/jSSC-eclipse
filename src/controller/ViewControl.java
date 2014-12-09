
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
    private SerialPortControll _model;
    private ComPort _model02;
    
    public ViewControl() {
        this._view = new MainWindow();
        this._model = new SerialPortControll();
        this._model02 = new ComPort();
        
        addListener();
    }
    
    
    public void showView() {
        this._view.setVisible(true);
        //this._model02.getComData(); //zum testen
        //this._model.getEventPort();
        this._view.setjTMesswert(this._model02.getComData());
        this._view.validate();
    }
    
    private void addListener() {
    	this._view.setExitButtonListener(new ExitButtonListener());
    	this._view.setComEventListener(new ComEventlistener());
    }
    
    
    class ExitButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
    	
    }
  
    class ComEventListener implements EventListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
    	
    }
    
    
}
