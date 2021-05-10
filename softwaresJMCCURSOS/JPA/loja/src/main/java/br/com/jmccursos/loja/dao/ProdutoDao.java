package br.com.jmccursos.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.jmccursos.loja.modelo.Produto;

public class ProdutoDao {
	private EntityManager em;
	public ProdutoDao(EntityManager em) {
		this.em=em;
	
	}
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto){
		this.em.merge(produto);
		
	}
	
	public void remover(Produto produto){
		produto =em.merge(produto);
		this.em.remove(produto);		
	}
	
	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome){
		String jpql = "SELECT p FROM Produto p 	WHERE 	p.nome = :x";
		return em.createQuery(jpql, Produto.class)
				.setParameter("x", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeDaCategoria(String nome){
		return em.createNamedQuery("Produto.produtosPorCategoria", Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoDoProdutoComNome(String nome){
		String jpql = "SELECT p.preco FROM Produto p 	WHERE 	p.nome = :x";
		return em.createQuery(jpql, BigDecimal.class)
				.setParameter("x", nome)
				.getSingleResult();
	}
	
	public List<Produto> buscarPorParametros(String nome,BigDecimal preco, LocalDate dataCadastro ){
		String jpql="SELECT p FROM Produto p WHERE 1=1 ";
		if (nome != null && !nome.trim().isEmpty()) {
			jpql ="	WHERE p.nome = :nome ";
		}if (preco != null) {
			jpql = " AND p.preco = :preco ";
		}
		if (dataCadastro != null) {
			jpql = " AND p.dataCadastro = :dataCadastro ";
		}
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}if (preco != null) {
			
			query.setParameter("preco", preco);
		}
		if (dataCadastro != null) {
			
			query.setParameter("dataCadastro", dataCadastro);
		}
		return query.getResultList();

	}
	
	public List<Produto> buscarPorParametrosComCriteria(String nome,BigDecimal preco, LocalDate dataCadastro ){
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		Predicate filtros = builder.and();
		if (nome != null && !nome.trim().isEmpty()) {
			filtros=builder.and(filtros, builder.equal(from.get("nome"), nome));
		}if (preco != null) {
			
			filtros=builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		if (dataCadastro != null) {
			
			filtros=builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}