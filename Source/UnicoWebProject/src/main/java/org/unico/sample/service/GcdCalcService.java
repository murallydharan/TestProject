package org.unico.sample.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface GcdCalcService {
	
	@WebMethod
	public int gcd();

	@WebMethod
	public List<Integer> gcdList();

	@WebMethod
	public int gcdSum();
	
}
