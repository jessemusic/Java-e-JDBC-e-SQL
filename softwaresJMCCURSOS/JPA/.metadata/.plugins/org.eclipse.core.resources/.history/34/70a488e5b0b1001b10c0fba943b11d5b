package br.com.jmccursos.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.jmccursos.loja.dao.CategoriaDao;
import br.com.jmccursos.loja.dao.ClienteDao;
import br.com.jmccursos.loja.dao.PedidoDao;
import br.com.jmccursos.loja.dao.ProdutoDao;
import br.com.jmccursos.loja.modelo.Categoria;
import br.com.jmccursos.loja.modelo.Cliente;
import br.com.jmccursos.loja.modelo.ItemPedido;
import br.com.jmccursos.loja.modelo.Pedido;
import br.com.jmccursos.loja.modelo.Produto;
import br.com.jmccursos.loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em=JPAUtil.getEntityManager();
		Pedido pedido = em.find(Pedido.class, 1l);
		System.out.println(pedido.getItens().size());

	}
private static void popularBancoDeDados() {
		
		Categoria celulares = new Categoria("CELULARES");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Categoria informatica = new Categoria("INFORMATICA");
		
		Produto celular = new Produto("Motora Z10","Com tecnologia de g6",new BigDecimal("1500"),celulares );
		Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
		Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);
		
		Cliente cliente = new Cliente("JesseMar","123456");
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(40, pedido, videogame));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(2, pedido2, macbook));
		
		
		EntityManager em=JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(videogame);
		produtoDao.cadastrar(macbook);
		
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		clienteDao.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
		
		
		
		
	}

}
