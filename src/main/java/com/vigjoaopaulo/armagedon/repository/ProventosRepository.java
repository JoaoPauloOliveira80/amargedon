package com.vigjoaopaulo.armagedon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vigjoaopaulo.armagedon.model.Proventos;

@Repository
public interface ProventosRepository extends JpaRepository<Proventos, Long>{
	
	
	
	/*@Query("SELECT SUM(p.valorDep) FROM Proventos p WHERE MONTH(STR_TO_DATE(p.dt_recebimeto, '%d/%m/%Y')) = ?1")
	Double findTotalDepositosByMonth(int month);*/
}
