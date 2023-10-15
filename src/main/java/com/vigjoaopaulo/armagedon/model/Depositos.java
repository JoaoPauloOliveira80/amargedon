package com.vigjoaopaulo.armagedon.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Depositos {

    @Id
    private int id;
    private java.sql.Date dt_deposito;
	private String operacao;
	private Double valorDep;
	private String depositante;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Date getDt_deposito() {
		return dt_deposito;
	}

	public void setDt_deposito(java.sql.Date dt_deposito) {
	    this.dt_deposito = dt_deposito;
	}

	public void setDt_deposito(String dataStr) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); // ajuste o formato de acordo com seus dados
            java.util.Date data = formato.parse(dataStr);
            java.sql.Date dataSql = new java.sql.Date(data.getTime());

            this.dt_deposito = dataSql; // chama o método original setDt_deposito()
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Double getValorDep() {
		return valorDep;
	}

	public void setValorDep(Double valorDep) {
		this.valorDep = valorDep;
	}

	public String getDepositante() {
		return depositante;
	}

	public void setDepositante(String depositante) {
		this.depositante = depositante;
	}
	
	@Override
	public String toString() {
		return "Id = " + id + 
				"\nData deposito = " + dt_deposito + 
				"\nTipo de deposito = " + operacao + 
				"\nDepositos = " +String.format("%.2f", valorDep)+
				"\nNome do depositante: " + depositante;
	}
}
