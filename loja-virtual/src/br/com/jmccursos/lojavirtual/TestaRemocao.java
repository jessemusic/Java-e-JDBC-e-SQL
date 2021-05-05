package br.com.jmccursos.lojavirtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory=new ConnectionFactory();
		Connection connection=connectionFactory.recuperaConexao();
		
		PreparedStatement stm = connection.prepareStatement("DELETE FROM PRODUTO WHERE ID>?");
		stm.setInt(1, 9);
		stm.execute();
		Integer qtlinhaDeletadas= stm.getUpdateCount();
		System.out.println("Quantidade de linhas apagadas: "+qtlinhaDeletadas );
		
	}
}
