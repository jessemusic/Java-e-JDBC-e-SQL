package br.com.jmccursos.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.jmccursos.loja.dao.ProdutoDao;
import br.com.jmccursos.loja.modelo.Categoria;
import br.com.jmccursos.loja.modelo.Produto;
import br.com.jmccursos.loja.util.JPAUtil;

public class CadastroProdutos {

	public static void main(String[] args) {
		
		Produto celular = new Produto("Motora Z10","Com tecnologia de g6",new BigDecimal("1500"),Categoria.CELULARES);
//		celular.setNome();
//		celular.setDescricao();
//		celular.setPreco();
		
		EntityManager em=JPAUtil.getEntityManager();
				
		ProdutoDao dao = new ProdutoDao(em);
		em.getTransaction().begin();
		dao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
		

	}

}
