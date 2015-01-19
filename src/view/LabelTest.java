/*
* Datei: LabelTest.java
*
* Die Klasse stellt das Hauptfenster zur Verfügung, in welchem die Rohrleitungen visualisiert werden
*
*
*
*/

package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class LabelTest extends JFrame{
	
	int anzahlSensor; //Anzahl der zu überwachenden Sensoren
	
	JPanel borderLayout; //Hauptfenster
	JPanel gridSouth; //im JPanel "borderLayout" eingebettet, Sensordaten
	JPanel gridHaupt; //im JPanel "borderLayout" eingebettet, nimmt "gridRechts" und "gridLinks" auf
	JPanel gridRechts; //im JPanel "gridHaupt" eingebettet, Darstellung des Rohrsystems
	JPanel gridLinks; //im JPanel "gridHaupt" eingebettet, Darstellung der Rohrstücke
	
	JLabel[] sensorLabel;
	JTextField[] sensorTextField;
	
	JLabel gewähltesBildText; //Darstellung der derzeitigen Auswahl
	JLabel gewähltesBild; //Darstellung der derzeitigen Auswahl
	
	JButton sensorPlus; // Button zur Auswahl ob Sensor 1, 2, etc. dargestellt werden soll
	JButton sensorMinus;
	
	final ImageIcon kreuz;
	final ImageIcon leer;
	final ImageIcon horizontal;
	final ImageIcon vertical;
	final ImageIcon lStückObenLinks;
	final ImageIcon lStückObenRechts;
	final ImageIcon lStückUntenLinks;
	final ImageIcon lStückUntenRechts;
	final ImageIcon tStückOben;
	final ImageIcon tStückUnten;
	final ImageIcon tStückLinks;
	final ImageIcon tStückRechts;
	final ImageIcon ventilHorizontal;
	final ImageIcon sensorHorizontal;
	final ImageIcon sensorVertikal;
	final ImageIcon pumpe;
	
	JRadioButton kreuzButton; //Die einzelnen Bilder sind als Radiobuttons realisiert, einer für jedes Bild
	JRadioButton leerButton;
	JRadioButton horizontalButton;
	JRadioButton vertikalButton;
	JRadioButton lStückObenLinksButton;
	JRadioButton lStückObenRechtsButton;
	JRadioButton lStückUntenLinksButton;
	JRadioButton lStückUntenRechtsButton;
	JRadioButton tStückObenButton;
	JRadioButton tStückUntenButton;
	JRadioButton tStückLinksButton;
	JRadioButton tStückRechtsButton;
	JRadioButton sensorVertikalButton;
	JRadioButton sensorHorizontalButton;
	JRadioButton ventilHorizontalButton;
	JRadioButton pumpeButton;
	ButtonGroup buttonGroup;
	
	JLabel[][] labelGitter; //2-D Array zum anordnen der Rohrstücke
	
	public LabelTest(int anzahlSensor){
		// TODO Auto-generated constructor stub
		this.anzahlSensor = anzahlSensor;
		
		kreuz = new ImageIcon("pics/Kreuz.png");
		leer = new ImageIcon("pics/Leer.png");
		horizontal = new ImageIcon("pics/Horizontal.png");
		vertical = new ImageIcon("pics/Vertikal.png");
		lStückObenLinks = new ImageIcon("pics/LStückObenLinks.png");
		lStückObenRechts = new ImageIcon("pics/LStückObenRechts.png");
		lStückUntenLinks = new ImageIcon("pics/LStückUntenLinks.png");
		lStückUntenRechts = new ImageIcon("pics/LStückUntenRechts.png");
		tStückOben = new ImageIcon("pics/TStückOben.png");
		tStückUnten = new ImageIcon("pics/TStückUnten.png");
		tStückLinks = new ImageIcon("pics/TStückLinks.png");
		tStückRechts = new ImageIcon("pics/TStückRechts.png");
		ventilHorizontal = new ImageIcon("pics/VentilHorizontal.png");
		sensorHorizontal = new ImageIcon("pics/SensorHorizontal.png");
		sensorVertikal = new ImageIcon("pics/SensorVertikal.png");
		pumpe = new ImageIcon("pics/Pumpe.png");

		initLayout(); //initialisieren der einzelen Teile, die das Hauptfenster darstellen
		
		initMenuBar();
		
		initSensorLabels();
		
		initRadioButtons();
		
		initAusgewähltBild();
		
		initSensorErhöhenButton();
		
		initZeichenfläche();
		
		initWindow();
	}

	private void initSensorErhöhenButton() { //Buttons zur Sensor Manipulation erstellen
		sensorPlus = new JButton("Sensor +");
		sensorMinus = new JButton("Sensor -");
		gridLinks.add(sensorPlus);
		gridLinks.add(sensorMinus);
	}

	private void initSensorLabels() { //Visualisierung der Sensor Daten
		sensorLabel = new JLabel[anzahlSensor];
		sensorTextField = new JTextField[anzahlSensor];
		
		for(int i=0;i<anzahlSensor;i++){
			StringBuilder sb = new StringBuilder();
			sb.append("Sensor ");
			sb.append(i+1);
			sensorLabel[i] = new JLabel(sb.toString());
			gridSouth.add(sensorLabel[i]);
			}
		for(int i=0;i<anzahlSensor;i++){
			sensorTextField[i] = new JTextField("000,000,000");
			sensorTextField[i].setEditable(false);
			gridSouth.add(sensorTextField[i]);
		}
	}

	private void initMenuBar() { //Menu Bar zum steuern der Anwendung
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItemSelect = new JMenuItem("Select ComPort");
		JMenuItem menuItemClose = new JMenuItem("Close ComPort");
		
		menu.add(menuItemSelect);
		menu.add(menuItemClose);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		this.setJMenuBar(menuBar);
	}

	private void initAusgewähltBild() { //das derzeitig ausgewählte Piktogramm wird dargestellt
		gewähltesBildText = new JLabel("Ausgewähltes Teil:");
		gewähltesBild = new JLabel(kreuz);
		
		gridLinks.add(gewähltesBildText);
		gridLinks.add(gewähltesBild);
	}

	private void initZeichenfläche() { //erstellen der Zeichenfläche, 2D Feld aus einzelnen JLabels, Hinzufügen des Mouseevents
		labelGitter = new JLabel[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				labelGitter[i][j] = new JLabel(leer);
				gridRechts.add(labelGitter[i][j]);
				
				class IconMouseListener extends MouseAdapter{
					
					private int xKoordinate;
					private int yKoordinate;
					
					public IconMouseListener(int xKoordinate, int yKoordinate) {
						// TODO Auto-generated constructor stub
						this.xKoordinate = xKoordinate;
						this.yKoordinate = yKoordinate;
					}

					public void mouseClicked(MouseEvent e) {
						
						if(kreuzButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(kreuz);
						else if(leerButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(leer);
						else if(vertikalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(vertical);
						else if(horizontalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(horizontal);
						else if(lStückObenLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lStückObenLinks);
						else if(lStückObenRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lStückObenRechts);
						else if(lStückUntenLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lStückUntenLinks);
						else if(lStückUntenRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lStückUntenRechts);
						else if(tStückLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tStückLinks);
						else if(tStückObenButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tStückOben);
						else if(tStückRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tStückRechts);
						else if(tStückUntenButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tStückUnten);
						else if(sensorHorizontalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(sensorHorizontal);
						else if(sensorVertikalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(sensorVertikal);
						else if(ventilHorizontalButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(ventilHorizontal);
						else
							labelGitter[xKoordinate][yKoordinate].setIcon(pumpe);
					}
				}
				labelGitter[i][j].addMouseListener(new IconMouseListener(i,j));
				
			}
		}
	}

	private void initWindow() { //erstellen des Hauptfenster
		setSize(800,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	private void initLayout() { //Zusammenfügen der einzelnen Teile für das Hauptfenster
		
		borderLayout = new JPanel();
		borderLayout.setLayout(new BorderLayout());
		add(borderLayout);
		
		gridSouth = new JPanel(); //Sensordaten
		gridSouth.setLayout(new GridLayout(2,3));
		borderLayout.add(gridSouth,BorderLayout.SOUTH);
		
		gridHaupt = new JPanel(); //nimmt "gridLinks" und "gridRechts" auf
		gridHaupt.setLayout(new GridLayout(1,2));
		borderLayout.add(gridHaupt,BorderLayout.CENTER);
		
		gridLinks = new JPanel(); //Rohrstücke
		gridLinks.setLayout(new GridLayout(5,4));
		gridHaupt.add(gridLinks);
		
		gridRechts = new JPanel(); //Rohrsystem
		gridRechts.setLayout(new GridLayout(10,10));
		gridHaupt.add(gridRechts);
	}

	private void initRadioButtons() {
		
		kreuzButton =new JRadioButton(kreuz); //Kreuzrohrstück
		kreuzButton.setSelected(true);
		leerButton = new JRadioButton(leer);
		
		horizontalButton = new JRadioButton(horizontal);
		vertikalButton = new JRadioButton(vertical);
		
		lStückObenLinksButton = new JRadioButton(lStückObenLinks); //L-Rohrstücke
		lStückObenRechtsButton = new JRadioButton(lStückObenRechts);
		lStückUntenLinksButton = new JRadioButton(lStückUntenLinks);
		lStückUntenRechtsButton = new JRadioButton(lStückUntenRechts);
		
		tStückLinksButton = new JRadioButton(tStückLinks); //T-Rohrstücke
		tStückObenButton = new JRadioButton(tStückOben);
		tStückRechtsButton = new JRadioButton(tStückRechts);
		tStückUntenButton = new JRadioButton(tStückUnten);
		
		sensorHorizontalButton = new JRadioButton(sensorHorizontal); //Sensoren
		sensorVertikalButton = new JRadioButton(sensorVertikal);
		
		ventilHorizontalButton = new JRadioButton(ventilHorizontal); //Ventil
		pumpeButton = new JRadioButton(pumpe); //Pumpe
		
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(kreuzButton);
		buttonGroup.add(leerButton);
		
		buttonGroup.add(horizontalButton);
		buttonGroup.add(vertikalButton);
		
		buttonGroup.add(lStückObenLinksButton);
		buttonGroup.add(lStückObenRechtsButton);
		buttonGroup.add(lStückUntenLinksButton);
		buttonGroup.add(lStückUntenRechtsButton);
		
		buttonGroup.add(tStückLinksButton);
		buttonGroup.add(tStückObenButton);
		buttonGroup.add(tStückRechtsButton);
		buttonGroup.add(tStückUntenButton);
		
		buttonGroup.add(sensorHorizontalButton);
		buttonGroup.add(sensorVertikalButton);
		
		buttonGroup.add(ventilHorizontalButton);
		buttonGroup.add(pumpeButton);
		
		gridLinks.add(kreuzButton);
		gridLinks.add(leerButton);
		gridLinks.add(horizontalButton);
		gridLinks.add(vertikalButton);
		gridLinks.add(lStückObenLinksButton);
		gridLinks.add(lStückObenRechtsButton);
		gridLinks.add(lStückUntenLinksButton);
		gridLinks.add(lStückUntenRechtsButton);
		gridLinks.add(tStückLinksButton);
		gridLinks.add(tStückObenButton);
		gridLinks.add(tStückRechtsButton);
		gridLinks.add(tStückUntenButton);
		gridLinks.add(sensorHorizontalButton);
		gridLinks.add(sensorVertikalButton);
		gridLinks.add(ventilHorizontalButton);
		gridLinks.add(pumpeButton);
	}
	
	public void setSensorTextFieldInhalt(String sensorTextField) {
		this.sensorTextField[0].setText(sensorTextField);
		//sensorTextField[0]
	}

}
