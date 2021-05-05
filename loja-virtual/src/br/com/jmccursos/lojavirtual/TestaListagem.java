package br.com.jmccursos.lojavirtual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection= connectionFactory.recuperaConexao();
		
		
		PreparedStatement stm = connection.prepareStatement("SELECT ID,NOME,DESCRICAO FROM PRODUTO");
		stm.execute();
		
		ResultSet rset = stm.getResultSet();
		
		
		while(rset.next()) {
			Integer id =rset.getInt("ID");
			String nome=rset.getString("NOME");
			String descricao =rset.getString("DESCRICAO");
			System.out.println("Id: "+ id + ", Nome: "+ nome + ", Descrição: "+ descricao);
			
		}

		
	}

}
