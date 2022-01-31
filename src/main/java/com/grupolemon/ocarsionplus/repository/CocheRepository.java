package com.grupolemon.ocarsionplus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.grupolemon.ocarsionplus.model.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Long>,JpaSpecificationExecutor<Coche> {

	
}
