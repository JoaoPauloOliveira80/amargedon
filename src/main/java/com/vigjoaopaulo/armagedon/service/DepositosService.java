package com.vigjoaopaulo.armagedon.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vigjoaopaulo.armagedon.model.Depositos;
import com.vigjoaopaulo.armagedon.model.Proventos;
import com.vigjoaopaulo.armagedon.repository.DepositosRepository;
import com.vigjoaopaulo.armagedon.repository.ProventosRepository;

import java.util.regex.Matcher;

@Service
public class DepositosService {
	@Autowired
	DepositosRepository depositosRepository;
	@Autowired
	ProventosRepository proventosRepository;

	static String[] dados = null;
	static ArrayList<Depositos> depositos = new ArrayList<>();
	static ArrayList<Proventos> proventos = new ArrayList<>();

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
				Proventos provento = new Proventos();
				String linha = sc.nextLine();
				System.out.println(linha);

				if (linha != null && !linha.isEmpty()) {
					dados = linha.split("\\;");
					System.out.println(linha);

					if (dados[2].contains("TED")) {
						deposito.setDt_deposito(dados[1]); // Aqui estamos chamando a função que converte a string para
															// java.sql.Date
						deposito.setOperacao("TED");
						deposito.setValorDep(Double.valueOf(dados[3].replace(".", "").replace(",", ".")));
						deposito.setId(Integer.valueOf(dados[6]));
						depositos.add(deposito);
					}

					if (dados[2].contains("Rendimento") || dados[2].contains("Dividendos")
							|| dados[2].contains("Juros")) {

						provento.setDt_recebimeto(dados[1]);

						String active = null;
						Pattern pattern = Pattern.compile(
								"(MXRF11|VGHF11|HABT11|DEVA11|VCRI11|VSLH11|PETR4|FESA4|UNIP3|KISU11|SNFF11|VGIA11|KLBN11|KLBN4|VALE3)");

						Matcher matcher = pattern.matcher(dados[2]);

						if (matcher.find()) {
							active = matcher.group(1);
						}

						if ((active != null)) {
							provento.setAtivo(active);
						}

						provento.setValorDep(Double.valueOf(dados[3].replace(".", "").replace(",", ".")));
						provento.setId(Integer.valueOf(dados[6]));

						proventos.add(provento);
					}

				}

			}

			String msgDep = "";
			for (Depositos dep : depositos) {
				Long id = Long.valueOf(dep.getId());
				if (!depositosRepository.findById(id).isPresent()) {
					depositosRepository.save(dep);
				} else {
					msgDep = "Nao há dados novos depositos";
				}

			}
			

			String msgProv = "";
			for (Proventos prov : proventos) {
				Long id = Long.valueOf(prov.getId());

				if (!proventosRepository.findById(id).isPresent()) {
					proventosRepository.save(prov);
				} else {
					System.out.println();
					msgProv = "Nao há dados novos proventos";
				}
			}  
			System.out.println();
			System.out.println(msgDep);
			
			System.out.println();
			System.out.println(msgProv);
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Depositos> getDepositos() {
		return depositosRepository.findAll();
	}

	public List<Proventos> getProventos() {
		return proventosRepository.findAll();
	}

	public Double getTotalDepositos() {
	    Double total = depositosRepository.sumValorDep();
	    System.out.println("Total de depósitos: " + total);  // linha de depuração
	    return total;
	}


}
