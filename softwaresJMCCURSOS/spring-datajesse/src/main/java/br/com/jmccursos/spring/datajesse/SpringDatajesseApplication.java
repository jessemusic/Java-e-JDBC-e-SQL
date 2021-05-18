package br.com.jmccursos.spring.datajesse;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.jmccursos.spring.datajesse.service.CrudCargoService;
import br.com.jmccursos.spring.datajesse.service.CrudFuncionarioService;
import br.com.jmccursos.spring.datajesse.service.CrudUnidadeDeTrabalhoService;
import br.com.jmccursos.spring.datajesse.service.RelatoriosService;
@EnableJpaRepositories
@SpringBootApplication
public class SpringDatajesseApplication implements CommandLineRunner {

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;

	private final CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService;
	private final RelatoriosService relatoriosService;

	private Boolean system = true;


	public SpringDatajesseApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
			CrudUnidadeDeTrabalhoService unidadeDeTrabalhoService, RelatoriosService relatoriosService) {
		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeDeTrabalhoService = unidadeDeTrabalhoService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDatajesseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		while (system) {
			
			System.out.println("Qual ação de cargo deseja quer executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Funcionário");
			System.out.println("3 - Unidade de Trabalho");
			System.out.println("4 - Relatórios");
			String action = sc.next();
			if(isNumeric(action)==false) {
				System.out.println("não é número o que você escolheu!");
				system = true;
			}else {
				switch (Integer.parseInt(action)) {
				case 1:
					cargoService.inicial(sc);
					break;
				case 2:
					funcionarioService.inicial(sc);
					break;
				case 3:
					unidadeDeTrabalhoService.inicial(sc);
					break;
				case 4:
					relatoriosService.inicial(sc);
					break;

				default:
					if(Integer.parseInt(action) != 0 ) {
						System.out.println("não tem essa opção");
						system=true;
					}else {
					System.out.println("Obrigado por utilizar nosso sistema de cadastro. até a próxima!");
					system = false;
					break;
					}
				}
			}
		}		
	}

	private static boolean isNumeric(String str) {
		
		return str != null && str.matches("[0-9.]+");
	}

}
