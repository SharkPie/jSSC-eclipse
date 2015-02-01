package view;

import jssc.*;

import java.awt.Dimension;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import model.ComPort;

@SuppressWarnings("serial")
public class ComPortSelecter extends JDialog {
	
	JComboBox<String> ComPortBox;
	
	public ComPortSelecter(ActionListener actionListener){
		
		JDialog ComPortFenster = new JDialog();
		ComPortFenster.setTitle("ComPort auswählen");
		ComPortFenster.setSize(300, 110);

		
		JPanel panel1 = new JPanel();
		
		ComPort comportAusgabe = new ComPort();
		String boxInhalt[] = comportAusgabe.getComPorts();
			ComPortBox = new JComboBox<String>(boxInhalt);
		
		Dimension sizeBox = new Dimension(280, 25);
		ComPortBox.setPreferredSize(sizeBox);
		
		JButton OKButton = new JButton("ComPort wahl");
		
		OKButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(final ActionEvent e) {
				
				//ComPortFenster.setVisible(false);  // Fenster läuft noch im Hintergrund
				ComPortFenster.dispose(); 			// Fenster wird komplett geschlossen
			}
		});
		
		panel1.add(ComPortBox);
		panel1.add(OKButton);
		ComPortFenster.add(panel1);
		ComPortFenster.setModal(true);
		ComPortFenster.setVisible(true);
	}
	
	public String getComPort(){
		//return (String) ComPortBox.getSelectedItem();
		return "test";
		
	}

}
