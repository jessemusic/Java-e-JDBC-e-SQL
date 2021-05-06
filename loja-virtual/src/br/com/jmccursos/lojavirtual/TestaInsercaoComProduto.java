package br.com.jmccursos.lojavirtual;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.jmccursos.lojavirtual.dao.ProdutoDAO;
import br.com.jmccursos.lojavirtual.modelo.Produto;

public class TestaInsercaoComProduto {

	public static void main(String[] args) throws SQLException {
		
		Produto comoda= new Produto("Cômoda","Cômoda Vertical");
		
		try(Connection connection = new ConnectionFactory().recuperaConexao()){
			ProdutoDAO produtoDAO = new ProdutoDAO(connection);
			produtoDAO.salvar(comoda);
			// LIst = persintencai.lista();
		}
	}
}
