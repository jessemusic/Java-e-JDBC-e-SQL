import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.jmccursos.lojavirtual.ConnectionFactory;
import br.com.jmccursos.lojavirtual.dao.CategoriaDAO;
import br.com.jmccursos.lojavirtual.modelo.Categoria;
import br.com.jmccursos.lojavirtual.modelo.Produto;

public class TestaListagemDeCategorias {

	public static void main(String[] args) throws SQLException {
	
		try(Connection connection = new ConnectionFactory().recuperaConexao()){
		CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
		List<Categoria> listaDeCategorias = categoriaDAO.listarComProdutos();
		listaDeCategorias.stream().forEach(ct -> {
			System.out.println(ct.getNome());			
				for (Produto produto : ct.getProdutos()){
					System.out.println(ct.getNome() + "-" + produto.getNome());					
				}			
		});
		}		
	}
}
