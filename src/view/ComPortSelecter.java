package view;

import jssc.*;

import java.awt.Dimension;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import model.ComPort;

/*
* Fenster zur Auswahl des ComPorts
*/
@SuppressWarnings("serial")
public class ComPortSelecter extends JDialog {
	
	JComboBox<String> ComPortBox;
	JDialog ComPortFenster;
	
	public ComPortSelecter(ActionListener actionListener){
		
		ComPortFenster = new JDialog();					// Neues Dialogfenster
		ComPortFenster.setTitle("ComPort auswählen");
		ComPortFenster.setSize(300, 110);

		
		JPanel panel1 = new JPanel();
		
		ComPort comportAusgabe = new ComPort();				// Klasse ComPort wird initialisiert
		String boxInhalt[] = comportAusgabe.getComPorts();		// Ausgabe der Methode getComPorts() wird in boxInhalt geschrieben
		ComPortBox = new JComboBox<String>(boxInhalt);			// Die ComPorts werden nun in der ComboBox angezeigt
		
		Dimension sizeBox = new Dimension(280, 25);			// Größe der ComboBox wird implementiert
		ComPortBox.setPreferredSize(sizeBox);
		
		JButton OKButton = new JButton("ComPort wahl");
		
		OKButton.addActionListener(actionListener);//() {
			
//			public void actionPerformed(final ActionEvent e) {
//				
//				//ComPortFenster.setVisible(false);  // Fenster läuft noch im Hintergrund
//				ComPortFenster.dispose(); 			// Fenster wird komplett geschlossen
//			}
//		});
		
		panel1.add(ComPortBox);
		panel1.add(OKButton);
		ComPortFenster.add(panel1);
		ComPortFenster.setModal(true);					
		
	}
	
	/*
	* Sichtbarkeit des Fensters
	*/
	public void setComPortSelecterVisible(){
		ComPortFenster.setVisible(true);
	}
	
	/*
	* Durch die Methode dispose() wird das Dialogfenster komplett geschlossen
	*/
	public void setDispose(){
		ComPortFenster.dispose();
	}
	
	/*
	* Getter Methode, der ausgewählte ComPort wird zurückgegeben
	*/
	public String getComPort(){						// 
		return (String) ComPortBox.getSelectedItem();
	}

}
