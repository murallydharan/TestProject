package org.unico.sample.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.unico.sample.business.GCDBO;
import org.unico.sample.model.GcdCalculatedVO;
import org.unico.sample.service.GcdCalcService;

@WebService(endpointInterface = "org.unico.sample.service.GcdCalcService")
public class GcdCalcServiceImpl implements GcdCalcService {

	@Override
	//@WebMethod
	public int gcd() {
		GCDBO objBo = new GCDBO();
		int gcdVal = objBo.calcGcd();
		return gcdVal;
	}

	@Override
	//@WebMethod
	public GcdCalculatedVO gcdList() {
		GCDBO objBo = new GCDBO();
		GcdCalculatedVO objVo = new GcdCalculatedVO();
		objVo = objBo.getGcdCompList(objVo);
		return objVo;
	}

	@Override
	//@WebMethod
	public int gcdSum() {
		GCDBO objBo = new GCDBO();
		int gcdSum = objBo.getGcdSum();
		return gcdSum;
	}

}
