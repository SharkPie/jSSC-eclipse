package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ViewControl;

@SuppressWarnings("serial")
public class AnzahlSensorenPopUp extends JDialog{
	
	private JComboBox<Integer> comboBox;
	private JLabel label;
	private JPanel panel;
	private JButton ok;
	private JButton abbrechen;
	int derzeitigeAnzahl;
	
	/*
	* Fenster zur Auswahl der Sensoren Anzahl
	*/
	public AnzahlSensorenPopUp(ViewControl actionListener) {
		
		panel = new JPanel();					//Layout des Fensters
		panel.setLayout(new GridLayout(2,2));
		add(panel);
		
		label = new JLabel("Anzahl der Sensoren");		//label
		panel.add(label);
		
		Integer[] inhaltComboBox = new Integer[5];		//ComboBox
		for(int i=0;i<inhaltComboBox.length;i++)
			inhaltComboBox[i]=i+1;
		
		comboBox = new JComboBox<Integer>(inhaltComboBox);
		panel.add(comboBox);
		
		ok = new JButton("Sensor auswählen");							//Buttons
		ok.addActionListener(actionListener);
		abbrechen = new JButton("Abbrechen");
		panel.add(ok);
		panel.add(abbrechen);
		
		abbrechen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
		
		initWindow();
	}
	/*
	* 
	*/
	public void setVisible(int derzeitigeAnzahl){
		setVisible(true);
		comboBox.setSelectedIndex(derzeitigeAnzahl-1);
	}

	/*
	* Optionen zur Sichtbarkeit des Fensters
	*/
	private void initWindow() {
		setTitle("Anzahl der Sensoren");
		setSize(300,100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
	}
	
	/*
	* Getter Methode
	*/
	public int getAnzahlSensoren(){			//gibt das ausgewählte Element zurück
		return (int) comboBox.getSelectedItem();
	}

	
}
