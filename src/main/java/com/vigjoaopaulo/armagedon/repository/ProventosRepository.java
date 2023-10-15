package com.vigjoaopaulo.armagedon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vigjoaopaulo.armagedon.model.Proventos;

@Repository
public interface ProventosRepository extends JpaRepository<Proventos, Long>{
	
}
