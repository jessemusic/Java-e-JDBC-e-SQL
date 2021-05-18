package br.com.jmccursos.spring.datajesse.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.jmccursos.spring.datajesse.orm.Cargo;
import br.com.jmccursos.spring.datajesse.orm.Funcionario;
import br.com.jmccursos.spring.datajesse.orm.UnidadeDeTrabalho;
import br.com.jmccursos.spring.datajesse.repository.CargoRepository;
import br.com.jmccursos.spring.datajesse.repository.FuncionarioRepository;
import br.com.jmccursos.spring.datajesse.repository.UnidadeDeTrabalhoRepository;

@Service
public class CrudFuncionarioService {
	
	private Boolean system = true;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository;
	
	
	
	public CrudFuncionarioService(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository,
			UnidadeDeTrabalhoRepository unidadeDeTrabalhoRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
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
					visualizar(sc);
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
		System.out.println("Digite o nome");
        String nome = sc.next();

        System.out.println("Digite o cpf");
        String cpf = sc.next();

        System.out.println("Digite o salario");
        Double salario = sc.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = sc.next();

        System.out.println("Digite o cargoId");
        
        String valorId =sc.next();
        if(isNumeric(valorId)==false) {
			System.out.println("não é número o que você escolheu!");
			System.out.println("Digite o cargoId");
	        
	        valorId =sc.next();
        }
        int cargoId=Integer.parseInt(valorId);
      sc.nextLine();

        List<UnidadeDeTrabalho> unidades = unidade(sc);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeDeTrabalhos(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
	}
	
	private List<UnidadeDeTrabalho> unidade(Scanner sc) {
        Boolean isTrue = true;
        List<UnidadeDeTrabalho> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = sc.nextInt();

            if(unidadeId != 0) {
                Optional<UnidadeDeTrabalho> unidade = unidadeDeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }
	
	private void atualizar(Scanner sc) {
		System.out.println("Digite o id");
        Integer id = sc.nextInt();

        System.out.println("Digite o nome");
        String nome = sc.next();

        System.out.println("Digite o cpf");
        String cpf = sc.next();

        System.out.println("Digite o salario");
        Double salario = sc.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = sc.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = sc.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
	}
	
	private void visualizar(Scanner sc) {
		System.out.println("Qual página você deseja vizualizar");
		Integer page=sc.nextInt();
		
		Pageable pageable = PageRequest.of(page, 5, Sort.unsorted());
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Pagina atual "+ funcionarios.getNumber());
		System.out.println("Total elemento " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void apagar(Scanner sc) {
		System.out.println("Id");
		int id = sc.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	
	
private static boolean isNumeric(String str) {
		
		return str != null && str.matches("[0-9.]+");
	}

}
