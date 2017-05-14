package org.unico.sample.business;

import java.util.List;

import org.unico.sample.business.jms.JMSPoster;
import org.unico.sample.database.GCDDAO;
import org.unico.sample.model.GcdCalculatedVO;
import org.unico.sample.model.GcdModel;

public class GCDBO {
	
	public List<GcdModel> getGcdList(){
		GCDDAO gcdDao = null;
		List<GcdModel> gcdList = null;
		try{
			gcdDao = new GCDDAO();
			gcdList = gcdDao.getGCDList();
		}catch(Exception ex){
			// Handle Exception
		}
		return gcdList;
	}

	public List<GcdModel> getGcdListStub(){
		GCDDAO gcdDao = null;
		List<GcdModel> gcdList = null;
		try{
			gcdDao = new GCDDAO();
			gcdList = gcdDao.getGCDListStub();
		}catch(Exception ex){
			// Handle Exception
		}
		return gcdList;
	}
	
	
	public String pushGcdListStub(GcdModel gcd){
		GCDDAO gcdDao = null;
		String status = null;
		try{
			gcdDao = new GCDDAO();
			if(gcdDao.pushGcdListStub(gcd))
				status = "Successfully Pushed";
			else
				status = "Data Push Failed";
		}catch(Exception ex){
			// Handle Exception
			status = "Exception Occured in Pushing Data";
		}
		return status;
	}

	public String pushGcdList(GcdModel gcd) throws Exception{
		JMSPoster jmsPoster = null;
		String status = null;
		try{
			jmsPoster = new JMSPoster();
			status = jmsPoster.QueuePoster(gcd);
		}catch(Exception ex){
			status = "Exception Occured in Pushing Data";
			throw ex;
		}
		return status;
	}

	public int calcGcd(){
		GCDDAO gcdDao = null;
		int gcdValue = 0;
		try{
			gcdDao = new GCDDAO();
			gcdValue = gcdDao.computeUpdateGcd();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return gcdValue;
	}

	public int getGcdSum(){
		GCDDAO gcdDao = null;
		int gcdValue = 0;
		try{
			gcdDao = new GCDDAO();
			gcdValue = gcdDao.getSumComputedGcd();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return gcdValue;
	}

	public GcdCalculatedVO getGcdCompList(GcdCalculatedVO objVo){
		GCDDAO gcdDao = null;
		List<Integer> gcdList = null;
		try{
			gcdDao = new GCDDAO();
			gcdList = gcdDao.getComputedGcd();
			objVo.setCalcGcdList(gcdList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return objVo;
	}
	
	
}
