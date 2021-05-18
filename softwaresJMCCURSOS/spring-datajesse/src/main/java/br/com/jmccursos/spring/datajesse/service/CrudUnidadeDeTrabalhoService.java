package br.com.jmccursos.spring.datajesse.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.jmccursos.spring.datajesse.orm.UnidadeDeTrabalho;
import br.com.jmccursos.spring.datajesse.repository.UnidadeDeTrabalhoRepository;
@Service
public class CrudUnidadeDeTrabalhoService {
	 
	private boolean system =true;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
	public CrudUnidadeDeTrabalhoService(UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.unidadeDeTrabalhoRepository = unidadeDeTrabalhoRepository;
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
					System.out.println("Saiu!");
					system = false;
					break;
				}
			}
		}
		
	}
	
	private void salvar(Scanner sc) {
		System.out.println("Digite o nome da unidade");
        String nome = sc.next();

        System.out.println("Digite o endereco");
        String endereco = sc.nextLine();

        UnidadeDeTrabalho unidadeTrabalho = new UnidadeDeTrabalho();
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);

        unidadeDeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo");
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Digite o id");
        Integer id = sc.nextInt();

        System.out.println("Digite o nome da unidade de trabalho");
        String nome = sc.next();
        sc.nextLine();
        System.out.println("Digite o endereco");
        String endereco = sc.nextLine();

        UnidadeDeTrabalho unidadeTrabalho = new UnidadeDeTrabalho();
        unidadeTrabalho.setId(id);
        unidadeTrabalho.setDescricao(nome);
        unidadeTrabalho.setEndereco(endereco);

        unidadeDeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Alterado");
	}
	
	private void visualizar() {
		Iterable<UnidadeDeTrabalho> unidades = unidadeDeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}
	
	private void apagar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		unidadeDeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado");
	}
	

	
private static boolean isNumeric(String str) {
		
		return str != null && str.matches("[0-9.]+");
	}

}
