
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
    private DrawBoard drawBoard;
    private LabelTest labelTest;
    
    public ViewControl() {
        this._view = new MainWindow();

        addListener();
    }
    
    
    public void showView() {
        this._view.setVisible(true);
        this._view.validate();
        
    }
    
    private void addListener() {
    	this._view.setExitButtonListener(new ExitButtonListener());
    	this._view.setRefreshButtonListener(new RefreshButtonListener());
    }
    
    
    class ExitButtonListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
    	
    }
    
    class RefreshButtonListener implements ActionListener {

    	public void actionPerformed(ActionEvent e) {
    	}
    	
    }
    
    public void setDrawBoardView(){
    	this.drawBoard = new DrawBoard();
    	this.drawBoard.showView();
    	
    }
    
    public void setLabelTestView(){
    	labelTest = new LabelTest(3);
    }

	public void update(Observable arg0, Object arg1) {
		labelTest.setSensorTextFieldInhalt(arg1.toString());
	}
    
}
