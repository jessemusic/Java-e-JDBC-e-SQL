package br.com.jmccursos.lojavirtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestaInsercaoComParametro2 {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection connection = connectionFactory.recuperaConexao()) {
			connection.setAutoCommit(false);
			try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome,descricao)VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);) {
				adicionarVariavel("SmartTV", "45 polegadas", stm);
				adicionarVariavel("Radio", "A pilhas de 1930", stm);
				adicionarVariavel("Bola", "De couro", stm);
				adicionarVariavel("Helicoptero", "Modelo Drone", stm);

				connection.commit();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				connection.rollback();
			}
		}

	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
//		if(nome.equals("Radio")) {
//			throw new RuntimeException("Não foi possível adicionar o produto");
//		}

		stm.execute();
		try (ResultSet rset = stm.getGeneratedKeys()) {
			while (rset.next()) {
				Integer id = rset.getInt(1);
				System.out.println("O id criado foi: " + id);

			}
		}
	}
}
