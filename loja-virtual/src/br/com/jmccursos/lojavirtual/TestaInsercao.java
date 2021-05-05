package br.com.jmccursos.lojavirtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperaConexao();
		
		PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome,descricao)VALUES ('Mousre','Mouse sem fio')",Statement.RETURN_GENERATED_KEYS);
		stm.execute();
		ResultSet rset=stm.getGeneratedKeys();
		while(rset.next()) {
			Integer id =rset.getInt(1);
			System.out.println("O id criado foi: "+ id);
		}		
	}
}
