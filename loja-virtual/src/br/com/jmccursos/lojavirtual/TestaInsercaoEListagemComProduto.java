package br.com.jmccursos.lojavirtual;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.jmccursos.lojavirtual.dao.ProdutoDAO;
import br.com.jmccursos.lojavirtual.modelo.Produto;

public class TestaInsercaoEListagemComProduto {

	public static void main(String[] args) throws SQLException {
		
		Produto comoda= new Produto("Jaqueta","Couro de body");
		
		try(Connection connection = new ConnectionFactory().recuperaConexao()){
			ProdutoDAO produtoDAO = new ProdutoDAO(connection);
			produtoDAO.salvar(comoda);
			 List<Produto> listaDeProdutos = produtoDAO.listar();
			 listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
		}
	}
}
