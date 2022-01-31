package com.grupolemon.ocarsionplus.service.impl;


import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.grupolemon.ocarsionplus.model.ApiCall;
import com.grupolemon.ocarsionplus.model.ServicioEnum;
import com.grupolemon.ocarsionplus.repository.ApiCallRepository;

@SpringBootTest
class ApiCallServiceImplTest {
	@Spy
	private ApiCallRepository apiCallRepository;
	@InjectMocks
	private ApiCallServiceImpl apiCallServiceImpl;

	@Test
	void testLogCall() {
		ApiCall entity = new ApiCall(ServicioEnum.EXPORT_CARS,LocalDateTime.now(),"198.168.1.2");
		Mockito.when(apiCallRepository.save(Mockito.any(ApiCall.class))).thenReturn(entity);
		apiCallServiceImpl.logCall(ServicioEnum.EXPORT_CARS,"198.168.1.2");
		
	}

}
