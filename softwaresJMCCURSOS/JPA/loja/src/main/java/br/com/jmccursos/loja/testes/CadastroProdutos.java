package br.com.jmccursos.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.jmccursos.loja.dao.CategoriaDao;
import br.com.jmccursos.loja.dao.ProdutoDao;
import br.com.jmccursos.loja.modelo.Categoria;
import br.com.jmccursos.loja.modelo.CategoriaId;
import br.com.jmccursos.loja.modelo.Produto;
import br.com.jmccursos.loja.util.JPAUtil;

public class CadastroProdutos {

	public static void main(String[] args) {
		
		cadastrarProduto();
		EntityManager em=JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p2.getNome()));
		
		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Motora Z10");
		System.out.println(precoDoProduto);

	}

	private static void cadastrarProduto() {
		
		Categoria celulares = new Categoria("CELULARES");		
		Produto celular = new Produto("Motora Z10","Com tecnologia de g6",new BigDecimal("1500"),celulares );
		
				
		EntityManager em=JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.find(Categoria.class, new CategoriaId("CELULARES", "XPTO"));
		em.close();
		
		
		
		
	}

}
