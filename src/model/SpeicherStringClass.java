package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SpeicherStringClass {
	
	@XmlElement
	String speicherString;
	
	public void setSpeicherString(String speicherString) {
		this.speicherString = speicherString;
	}
	
	public String getSpeicherString() {
		return speicherString;
	}
}
