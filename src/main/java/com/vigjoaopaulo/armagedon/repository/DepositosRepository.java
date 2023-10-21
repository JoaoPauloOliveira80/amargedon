package com.vigjoaopaulo.armagedon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vigjoaopaulo.armagedon.model.Depositos;

@Repository
public interface DepositosRepository extends JpaRepository<Depositos, Long> {
	
	@Query("SELECT COALESCE(SUM(d.valorDep), 0) FROM Depositos d")
	Double sumValorDep();

}
