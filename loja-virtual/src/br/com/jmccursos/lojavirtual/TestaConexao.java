package br.com.jmccursos.lojavirtual;


import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperaConexao();
		System.out.println("Fechando conexão");
		connection.close();
	}

}
