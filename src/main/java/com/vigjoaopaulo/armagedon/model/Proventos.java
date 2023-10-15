package com.vigjoaopaulo.armagedon.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Proventos {
	
	
    @Id 
    private int id;     
    @Column(name = "dt_recebimeto")
    private java.sql.Date dt_recebimeto;
    private String ativo;
    private Double valorDep;
    
    public Proventos() {
		// TODO Auto-generated constructor stub
	}

    public Proventos(int id, Date dt_recebimeto, String ativo, Double valorDep) {
        this.id = id;
        this.dt_recebimeto = dt_recebimeto;
        this.ativo = ativo;
        this.valorDep = valorDep;
    }

    public int getId() { 
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDt_recebimeto() {
        return dt_recebimeto;
    }
    
    
    public void setDt_recebimeto(String dataStr) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); // ajuste o formato de acordo com seus dados
            java.util.Date data = formato.parse(dataStr);
            java.sql.Date dataSql = new java.sql.Date(data.getTime());

            this.dt_recebimeto = dataSql; // chama o método original setDt_recebimeto()
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Double getValorDep() {
        return valorDep;
    }

    public void setValorDep(Double valorDep) {
        this.valorDep = valorDep;
    }
    
    

	@Override
	public String toString() {
		return " Codigo deposito = " + id + "\n Data pagamento = " + dt_recebimeto + "\n Ativo=" + ativo + "\n ValorDep="
				+ valorDep;
	}
	

    
}
