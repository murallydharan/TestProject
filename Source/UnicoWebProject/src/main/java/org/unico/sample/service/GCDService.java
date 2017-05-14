package org.unico.sample.service;

import java.util.List;

import org.unico.sample.business.GCDBO;
import org.unico.sample.exception.DataNotFoundException;
import org.unico.sample.model.GcdModel;

public class GCDService {
	
	public List<GcdModel> getGcdList(){
		GCDBO gcdBO = null;
		List<GcdModel> gcdList = null;
		try{
			gcdBO  = new GCDBO();
			gcdList = gcdBO.getGcdList();
			if(gcdList == null  || gcdList.isEmpty()){
				throw new DataNotFoundException("No GCD Calculations Happened.");
			}
		}catch(DataNotFoundException ex){
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return gcdList;
	}
	
	
	public List<GcdModel> getGcdListStub(){
		GCDBO gcdBO = null;
		List<GcdModel> gcdList = null;
		try{
			gcdBO  = new GCDBO();
			gcdList = gcdBO.getGcdListStub();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return gcdList;
	}
	
	public String pushGcdList(GcdModel gcd){
		GCDBO gcdBo = null;
		String status = null;
		try{
			gcdBo = new GCDBO();
			status = gcdBo.pushGcdList(gcd);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return status;
	}

	public String pushGcdListStub(GcdModel gcd){
		GCDBO gcdBo = null;
		String status = null;
		try{
			gcdBo = new GCDBO();
			status = gcdBo.pushGcdListStub(gcd);
		}catch(Exception ex){
			ex.printStackTrace();
			status = "Technical Exception Occured in Business";
		}
		return status;
	}
	
}
