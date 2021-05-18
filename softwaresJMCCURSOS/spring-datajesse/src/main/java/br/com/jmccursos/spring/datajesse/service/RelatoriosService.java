package br.com.jmccursos.spring.datajesse.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import br.com.jmccursos.spring.datajesse.orm.Funcionario;
import br.com.jmccursos.spring.datajesse.orm.FuncionarioProjecao;
import br.com.jmccursos.spring.datajesse.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	private Boolean system =true;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository= funcionarioRepository;
	}
	
	public void inicial(Scanner sc) {
		while(system) {
			System.out.println("Qual ação de cargo deseja quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario por nome:");
			System.out.println("2 - Busca funcionario por nome, data contratação e maior salário:");
			System.out.println("3 - Busca  data contratação");
			System.out.println("4 - Pesquisa funcionário salário ");
			String action = sc.next();
			if(isNumeric(action)==false) {
				System.out.println("não é número o que você escolheu!");
				system = true;
			}else {
				switch (Integer.parseInt(action)) {
				case 1:
					buscarFuncionarioPorNome(sc);
					break;
				case 2:
					buscaFuncionarioNomeSalarioMaiorData(sc);
					break;
				case 3:
					buscaFuncionarioDataContratacao(sc);
					break;
				case 4:
					pesquisaFuncionarioSalario();
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
	private void buscarFuncionarioPorNome(Scanner sc) {
		System.out.print("Qual o nome dejesa pesquisar: ");
		String nome= sc.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
		
		
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner sc) {
		System.out.print("Qual o nome do funcionario: ");
		String nome= sc.next();
		
		System.out.print("Qual data  contrataçao dejesa pesquisar: ");
		String data= sc.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.print("Qual salário dejesa pesquisar: ");
		Double salario= sc.nextDouble();
		
		List<Funcionario> list = funcionarioRepository
				.findByNomeAndSalarioGreaterThanAndDataContratacao(nome, salario, localDate);
				
				list.forEach(System.out::println);
		
	}
	private void buscaFuncionarioDataContratacao(Scanner sc) {
		System.out.println("Qual data contratacao deseja pesquisar");
		String data = sc.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionário: id: "
		+ f.getId() + " | nome: "+ f.getNome() + " | salário: "+ f.getSalario()));
	}
	
	
	
	
	
	
private static boolean isNumeric(String str) {
		
		return str != null && str.matches("[0-9.]+");
	}
	
}
