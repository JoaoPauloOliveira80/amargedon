package com.vigjoaopaulo.armagedon.model;

import java.sql.Date;
import java.util.Calendar;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Proventos {
	
	
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Column(name = "dt_recebimeto")
    private Date dt_recebimeto;
    private String ativo;
    private Double valorDep;
    private Date data;
    
    public Proventos() {
		// TODO Auto-generated constructor stub
	}

    public Proventos(Long id, Date dt_recebimeto, String ativo, Double valorDep) {
        this.id = id;
        this.dt_recebimeto = dt_recebimeto;
        this.ativo = ativo;
        this.valorDep = valorDep;
        this.data = new Date(Calendar.getInstance().getTime().getTime()); // Adiciona a data atual
    }

    public Long getId() { 
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDt_recebimeto() {
        return dt_recebimeto;
    }

    public void setDt_recebimeto(Date dt_recebimeto) {
        this.dt_recebimeto = dt_recebimeto;
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
    
    

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void saveData() {
        this.data = new Date(Calendar.getInstance().getTime().getTime()); 
    }

	@Override
	public String toString() {
		return " Codigo deposito = " + id + "\n Data pagamento = " + dt_recebimeto + "\n Ativo=" + ativo + "\n ValorDep="
				+ valorDep;
	}
	
	
	public void salvarBanco() {
		
		
		
		
		
	}
    
    
}
