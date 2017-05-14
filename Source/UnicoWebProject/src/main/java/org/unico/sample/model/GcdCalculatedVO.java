package org.unico.sample.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GcdCalculatedVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int calculatedGcd;
	private int sumOfGcd;
	private List<Integer> calcGcdList = new ArrayList<Integer>();
	
	public GcdCalculatedVO(){}
	
	public int getCalculatedGcd() {
		return calculatedGcd;
	}
	public void setCalculatedGcd(int calculatedGcd) {
		this.calculatedGcd = calculatedGcd;
	}
	public int getSumOfGcd() {
		return sumOfGcd;
	}
	public void setSumOfGcd(int sumOfGcd) {
		this.sumOfGcd = sumOfGcd;
	}
	public List<Integer> getCalcGcdList() {
		return calcGcdList;
	}
	public void setCalcGcdList(List<Integer> calcGcdList) {
		this.calcGcdList = calcGcdList;
	}

}
