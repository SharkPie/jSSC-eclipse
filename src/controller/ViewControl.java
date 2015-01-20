/*
* Datei: ViewControl.java
*
*
*
*
*
*/

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import view.*;
import model.*;

public class ViewControl implements Observer{
    
    private MainWindow view;
    private DrawBoard drawBoard;
    private LabelTest labelTest;
    
    public ViewControl() {
        this.view = new MainWindow();

        addListener();
    }
    
    
    public void showView() {
        this.view.setVisible(true);
        this.view.validate();
        
    }
    
    private void addListener() {
    	this.view.setExitButtonListener(new ExitButtonListener());
    	this.view.setRefreshButtonListener(new RefreshButtonListener());
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
		String[] sensorWerte = arg1.toString().split(Pattern.quote(":"));
		labelTest.setSensorTextFieldInhalt(sensorWerte[1],new Integer(sensorWerte[0]));
	}
    
}
