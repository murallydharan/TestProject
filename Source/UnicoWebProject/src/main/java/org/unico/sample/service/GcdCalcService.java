package org.unico.sample.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.unico.sample.model.GcdCalculatedVO;

@WebService
public interface GcdCalcService {
	
	@WebMethod
	public int gcd();

	@WebMethod
	public GcdCalculatedVO gcdList();

	@WebMethod
	public int gcdSum();
	
}
