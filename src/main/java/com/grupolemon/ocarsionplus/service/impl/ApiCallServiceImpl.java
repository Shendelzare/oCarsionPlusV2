package com.grupolemon.ocarsionplus.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupolemon.ocarsionplus.model.ApiCall;
import com.grupolemon.ocarsionplus.model.ServicioEnum;
import com.grupolemon.ocarsionplus.repository.ApiCallRepository;
import com.grupolemon.ocarsionplus.service.ApiCallService;

@Service
public class ApiCallServiceImpl implements ApiCallService {

	@Autowired
	private ApiCallRepository apiCallRepository;

	@Override
	public void logCall(ServicioEnum service, String ip) {

		LocalDateTime currentTime = LocalDateTime.now();

		ApiCall apiCall = new ApiCall(service, currentTime, ip);

		apiCallRepository.save(apiCall);

	}

}
