package com.vigjoaopaulo.armagedon.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vigjoaopaulo.armagedon.model.Depositos;
import com.vigjoaopaulo.armagedon.repository.DepositosRepository;


@Service
public class DepositosService {
	@Autowired
	DepositosRepository depositosRepository;
	
	static String[] dados = null;
	static ArrayList<Depositos> depositos = new ArrayList<>();
	
	static String path = "C:\\Users\\vigjo\\Downloads/Extrato_2023-10-15.csv";
	
	public void processFile() {
		FileReader arquivo;
		try {
		   
			arquivo = new FileReader(new File(path));
			
			Scanner sc = new Scanner(arquivo);
			sc.nextLine();
			sc.nextLine();
			
			while (sc.hasNextLine()) { 
				Depositos deposito = new Depositos();
				String linha = sc.nextLine();
	            System.out.println(linha);
				
				if (linha != null && !linha.isEmpty()) {
					dados = linha.split("\\;");
					 System.out.println(linha);
					 
					 if (dados[2].contains("TED")) {
		                    deposito.setDt_deposito(dados[1]); // Aqui estamos chamando a função que converte a string para java.sql.Date
		                    deposito.setOperacao("TED");
		                    deposito.setValorDep(Double.valueOf(dados[3].replace(".", "").replace(",", ".")));
		                    deposito.setId(Integer.valueOf(dados[6]));
		                    depositos.add(deposito);
		                }
				}
				
			}
			
			String msg = "";
			for(Depositos dep : depositos) {
			    Long id = Long.valueOf(dep.getId());
			    if(!depositosRepository.findById(id).isPresent()) {
			        depositosRepository.save(dep);
			        System.out.println(dep);
			        System.out.println();
			    }else {
			    	msg = "Nao há dados novos";
			    }    
			     
			} 
			System.out.println();
		    System.out.println(msg);


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
