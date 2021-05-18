package br.com.jmccursos.spring.datajesse.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.jmccursos.spring.datajesse.orm.Cargo;
import br.com.jmccursos.spring.datajesse.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private Boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	
	}
	
	public void inicial(Scanner sc) {
		while(system) {
			System.out.println("Qual ação de cargo deseja quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - deletar");
			String action = sc.next();
			if(isNumeric(action)==false) {
				System.out.println("não é número o que você escolheu!");
				system = true;
			}else {
				switch (Integer.parseInt(action)) {
				case 1:
					salvar(sc);
					break;
				case 2:
					atualizar(sc);
					break;
				case 3:
					visualizar();
					break;
				case 4:
					apagar(sc);
					break;

				default:
					if(Integer.parseInt(action) != 0 ) {
						System.out.println("não tem essa opção");
						system=true;
					}else {
					System.out.println("Saiu!");
					system = false;
					break;
					}
				}
			}
		}
		
	}
	
	public void salvar(Scanner sc) {
		System.out.println("Descrição do cargo:");
		String descricao = sc.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Salvo");
		
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Id");
		int id= sc.nextInt();
		System.out.println("Nova descrição do cargo!");
		String descricao =sc.next();
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Atualização Salva com sucesso");
		
	}
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(cargo -> System.out.println(cargo));
	}
	
	private void apagar(Scanner sc) {
		System.out.println("Id");
		int id= sc.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("Deletado com sucesso");
		
	}
	
	
	
private static boolean isNumeric(String str) {
		
		return str != null && str.matches("[0-9.]+");
	}


}
