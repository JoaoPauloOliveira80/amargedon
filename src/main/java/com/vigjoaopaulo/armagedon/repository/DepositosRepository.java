package com.vigjoaopaulo.armagedon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vigjoaopaulo.armagedon.model.Depositos;

@Repository
public interface DepositosRepository extends JpaRepository<Depositos, Long> {
	
	/*@Query("SELECT SUM(d.valorDep) FROM Depositos d WHERE MONTH(STR_TO_DATE(d.dt_deposito, '%d/%m/%Y')) = ?1")
	Double findTotalDepositosByMonth(int month);
	*/
}
