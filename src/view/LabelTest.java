/*
* Datei: LabelTest.java
*
* Die Klasse stellt das Hauptfenster zur Verfuegung, in welchem die Rohrleitungen visualisiert werden
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
	
	int anzahlSensor; //Anzahl der zu ueberwachenden Sensoren
	
	JPanel borderLayout; //Hauptfenster
	JPanel gridSouth; //im JPanel "borderLayout" eingebettet, Sensordaten
	JPanel gridHaupt; //im JPanel "borderLayout" eingebettet, nimmt "gridRechts" und "gridLinks" auf
	JPanel gridRechts; //im JPanel "gridHaupt" eingebettet, Darstellung des Rohrsystems
	JPanel gridLinks; //im JPanel "gridHaupt" eingebettet, Darstellung der Rohrstuecke
	
	JLabel[] sensorLabel;
	JTextField[] sensorTextField;
	
	JLabel gewaehltesBildText; //Darstellung der derzeitigen Auswahl
	JLabel gewaehltesBild; //Darstellung der derzeitigen Auswahl
	
	JButton sensorPlus; // Button zur Auswahl ob Sensor 1, 2, etc. dargestellt werden soll
	JButton sensorMinus;
	
	final ImageIcon kreuz;
	final ImageIcon leer;
	final ImageIcon horizontal;
	final ImageIcon vertical;
	final ImageIcon lStueckObenLinks;
	final ImageIcon lStueckObenRechts;
	final ImageIcon lStueckUntenLinks;
	final ImageIcon lStueckUntenRechts;
	final ImageIcon tStueckOben;
	final ImageIcon tStueckUnten;
	final ImageIcon tStueckLinks;
	final ImageIcon tStueckRechts;
	final ImageIcon ventilHorizontal;
	final ImageIcon sensorHorizontal;
	final ImageIcon sensorVertikal;
	final ImageIcon pumpe;
	
	JRadioButton kreuzButton; //Die einzelnen Bilder sind als Radiobuttons realisiert, einer fuer jedes Bild
	JRadioButton leerButton;
	JRadioButton horizontalButton;
	JRadioButton vertikalButton;
	JRadioButton lStueckObenLinksButton;
	JRadioButton lStueckObenRechtsButton;
	JRadioButton lStueckUntenLinksButton;
	JRadioButton lStueckUntenRechtsButton;
	JRadioButton tStueckObenButton;
	JRadioButton tStueckUntenButton;
	JRadioButton tStueckLinksButton;
	JRadioButton tStueckRechtsButton;
	JRadioButton sensorVertikalButton;
	JRadioButton sensorHorizontalButton;
	JRadioButton ventilHorizontalButton;
	JRadioButton pumpeButton;
	ButtonGroup buttonGroup;
	
	JLabel[][] labelGitter; //2-D Array zum anordnen der Rohrstuecke
	
	public LabelTest(int anzahlSensor){
		// TODO Auto-generated constructor stub
		this.anzahlSensor = anzahlSensor;
		
		kreuz = new ImageIcon("pics/Kreuz.png");
		leer = new ImageIcon("pics/Leer.png");
		horizontal = new ImageIcon("pics/Horizontal.png");
		vertical = new ImageIcon("pics/Vertikal.png");
		lStueckObenLinks = new ImageIcon("pics/LStueckObenLinks.png");
		lStueckObenRechts = new ImageIcon("pics/LStueckObenRechts.png");
		lStueckUntenLinks = new ImageIcon("pics/LStueckUntenLinks.png");
		lStueckUntenRechts = new ImageIcon("pics/LStueckUntenRechts.png");
		tStueckOben = new ImageIcon("pics/TStueckOben.png");
		tStueckUnten = new ImageIcon("pics/TStueckUnten.png");
		tStueckLinks = new ImageIcon("pics/TStueckLinks.png");
		tStueckRechts = new ImageIcon("pics/TStueckRechts.png");
		ventilHorizontal = new ImageIcon("pics/VentilHorizontal.png");
		sensorHorizontal = new ImageIcon("pics/SensorHorizontal.png");
		sensorVertikal = new ImageIcon("pics/SensorVertikal.png");
		pumpe = new ImageIcon("pics/Pumpe.png");

		initLayout(); //initialisieren der einzelen Teile, die das Hauptfenster darstellen
		
		initMenuBar();
		
		initSensorLabels();
		
		initRadioButtons();
		
		initAusgewähltBild();
		
		initSensorErh�henButton();
		
		initZeichenfläche();
		
		initWindow();
	}

	private void initSensorErh�henButton() { //Buttons zur Sensor Manipulation erstellen
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
		gewaehltesBildText = new JLabel("Ausgewaehltes Teil:");
		gewaehltesBild = new JLabel(kreuz);
		
		gridLinks.add(gewaehltesBildText);
		gridLinks.add(gewaehltesBild);
	}

	private void initZeichenfläche() { //erstellen der Zeichenfläche, 2D Feld aus einzelnen JLabels, Hinzufuegen des Mouseevents
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
						else if(lStueckObenLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lStueckObenLinks);
						else if(lStueckObenRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lStueckObenRechts);
						else if(lStueckUntenLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lStueckUntenLinks);
						else if(lStueckUntenRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(lStueckUntenRechts);
						else if(tStueckLinksButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tStueckLinks);
						else if(tStueckObenButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tStueckOben);
						else if(tStueckRechtsButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tStueckRechts);
						else if(tStueckUntenButton.isSelected())
							labelGitter[xKoordinate][yKoordinate].setIcon(tStueckUnten);
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

	private void initLayout() { //Zusammenfuegen der einzelnen Teile fuer das Hauptfenster
		
		borderLayout = new JPanel();
		borderLayout.setLayout(new BorderLayout());
		add(borderLayout);
		
		gridSouth = new JPanel(); //Sensordaten
		gridSouth.setLayout(new GridLayout(2,3));
		borderLayout.add(gridSouth,BorderLayout.SOUTH);
		
		gridHaupt = new JPanel(); //nimmt "gridLinks" und "gridRechts" auf
		gridHaupt.setLayout(new GridLayout(1,2));
		borderLayout.add(gridHaupt,BorderLayout.CENTER);
		
		gridLinks = new JPanel(); //Rohrstuecke
		gridLinks.setLayout(new GridLayout(5,4));
		gridHaupt.add(gridLinks);
		
		gridRechts = new JPanel(); //Rohrsystem
		gridRechts.setLayout(new GridLayout(10,10));
		gridHaupt.add(gridRechts);
	}

	private void initRadioButtons() {
		
		kreuzButton =new JRadioButton(kreuz); //Kreuzrohrstueck
		kreuzButton.setSelected(true);
		leerButton = new JRadioButton(leer);
		
		horizontalButton = new JRadioButton(horizontal);
		vertikalButton = new JRadioButton(vertical);
		
		lStueckObenLinksButton = new JRadioButton(lStueckObenLinks); //L-Rohrstuecke
		lStueckObenRechtsButton = new JRadioButton(lStueckObenRechts);
		lStueckUntenLinksButton = new JRadioButton(lStueckUntenLinks);
		lStueckUntenRechtsButton = new JRadioButton(lStueckUntenRechts);
		
		tStueckLinksButton = new JRadioButton(tStueckLinks); //T-Rohrstuecke
		tStueckObenButton = new JRadioButton(tStueckOben);
		tStueckRechtsButton = new JRadioButton(tStueckRechts);
		tStueckUntenButton = new JRadioButton(tStueckUnten);
		
		sensorHorizontalButton = new JRadioButton(sensorHorizontal); //Sensoren
		sensorVertikalButton = new JRadioButton(sensorVertikal);
		
		ventilHorizontalButton = new JRadioButton(ventilHorizontal); //Ventil
		pumpeButton = new JRadioButton(pumpe); //Pumpe
		
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(kreuzButton);
		buttonGroup.add(leerButton);
		
		buttonGroup.add(horizontalButton);
		buttonGroup.add(vertikalButton);
		
		buttonGroup.add(lStueckObenLinksButton);
		buttonGroup.add(lStueckObenRechtsButton);
		buttonGroup.add(lStueckUntenLinksButton);
		buttonGroup.add(lStueckUntenRechtsButton);
		
		buttonGroup.add(tStueckLinksButton);
		buttonGroup.add(tStueckObenButton);
		buttonGroup.add(tStueckRechtsButton);
		buttonGroup.add(tStueckUntenButton);
		
		buttonGroup.add(sensorHorizontalButton);
		buttonGroup.add(sensorVertikalButton);
		
		buttonGroup.add(ventilHorizontalButton);
		buttonGroup.add(pumpeButton);
		
		gridLinks.add(kreuzButton);
		gridLinks.add(leerButton);
		gridLinks.add(horizontalButton);
		gridLinks.add(vertikalButton);
		gridLinks.add(lStueckObenLinksButton);
		gridLinks.add(lStueckObenRechtsButton);
		gridLinks.add(lStueckUntenLinksButton);
		gridLinks.add(lStueckUntenRechtsButton);
		gridLinks.add(tStueckLinksButton);
		gridLinks.add(tStueckObenButton);
		gridLinks.add(tStueckRechtsButton);
		gridLinks.add(tStueckUntenButton);
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
