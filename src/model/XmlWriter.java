package model;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class XmlWriter {
	
	private JAXBContext jaxbContext;
	private SpeicherStringClass klasse = new SpeicherStringClass();

	public void writeXml(String speichern){
		
		File file = new File("Durchfluss.xml");
		klasse.setSpeicherString(speichern);
		try {
			this.jaxbContext = JAXBContext.newInstance(SpeicherStringClass.class);
			Marshaller jaxbMarshaller;		
			jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(klasse, file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readXml(){
		
		File file = new File("Durchfluss.xml");
		
		String returnString = new String();
		
		try{
			this.jaxbContext = JAXBContext.newInstance(SpeicherStringClass.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			klasse =  (SpeicherStringClass) jaxbUnmarshaller.unmarshal(file);
		}catch (JAXBException e) {
			JOptionPane.showMessageDialog(null, "Es konnte keine Datei gefunden werden!", "Fehler!", JOptionPane.ERROR_MESSAGE);
		}
		returnString = klasse.getSpeicherString();
		return returnString;
	}

}
