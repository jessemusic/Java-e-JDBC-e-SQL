package br.com.jmccursos.lojavirtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		
		String nome;
		String descricao;
		Scanner sc = new Scanner(System.in);
		System.out.println("Cadastro de produtos");
		System.out.print("Digite o nome do produto: ");
		nome=sc.next();
		System.out.print("Digite a descrição do produto: ");
		descricao=sc.next();

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperaConexao();
		
		
		PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome,descricao)VALUES (?,?)"
				,Statement.RETURN_GENERATED_KEYS);
		adicionarVariavel(nome, descricao, stm);
		
		sc.close();
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		stm.execute();
		ResultSet rset=stm.getGeneratedKeys();
		while(rset.next()) {
			Integer id =rset.getInt(1);
			System.out.println("O id criado foi: "+ id);
			
		}
		rset.close();
	}
}
