
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;

import view.*;
import model.*;

public class ViewControl implements Observer{
    
    private MainWindow _view;
//    private SerialPortControll _model;
//    private ComPort _model02;
    private DrawBoard drawBoard;
    private LabelTest labelTest;
    
    public ViewControl() {
        this._view = new MainWindow();
//        this._model = new SerialPortControll();
//        this._model02 = new ComPort();

        addListener();
    }
    
    
    public void showView() {
        this._view.setVisible(true);
        this._view.validate();
        
    }
    
    private void addListener() {
    	this._view.setExitButtonListener(new ExitButtonListener());
    	this._view.setRefreshButtonListener(new RefreshButtonListener());
    	//this._view.setComEventListener(new ComEventlistener());
    }
    
    
    class ExitButtonListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
    	
    }
    
    class RefreshButtonListener implements ActionListener {

    	public void actionPerformed(ActionEvent e) {
    		//this._view.setjTMesswert(this._model02.getComData());
//    		DrawBoard newDrawBoard = new DrawBoard();
//    		Ausgabe ausgabe = new Ausgabe();
//    		ausgabe.addObserver(newDrawBoard);
//    		newDrawBoard.showView();
//    		ausgabe.run();
    		//setDrawBoardView();
    	}
    	
    }
    
    public void setDrawBoardView(){
    	this.drawBoard = new DrawBoard();
    	this.drawBoard.showView();
    	
    }
    
    public void setLabelTestView(){
    	labelTest = new LabelTest(4);
    }

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
        //drawBoard.setComportTextField1(arg1.toString());
//		System.out.println("test :" + arg1);
		//_view.setjTMesswert(arg1.toString());;
		labelTest.setSensorTextFieldInhalt(arg1.toString());
	}
    
    
}
