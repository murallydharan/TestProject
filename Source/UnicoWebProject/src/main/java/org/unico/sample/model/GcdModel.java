package org.unico.sample.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GcdModel {

	int number1;
	int number2;
	
	public GcdModel(){}
	
	public GcdModel(int no1, int no2){
		this.number1 = no1;
		this.number2 = no2;
	}

	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int numer1) {
		this.number1 = numer1;
	}
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
	}

}
