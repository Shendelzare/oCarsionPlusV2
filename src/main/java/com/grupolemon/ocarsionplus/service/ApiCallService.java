package com.grupolemon.ocarsionplus.service;

import com.grupolemon.ocarsionplus.model.ServicioEnum;

public interface ApiCallService {
	
	public void logCall(ServicioEnum service, String ip);

}
