package com.grupolemon.ocarsionplus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grupolemon.ocarsionplus.model.ApiCall;

public interface ApiCallRepository extends MongoRepository<ApiCall, String>{

}
