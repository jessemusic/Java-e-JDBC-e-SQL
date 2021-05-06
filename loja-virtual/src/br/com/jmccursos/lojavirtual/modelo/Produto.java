package br.com.jmccursos.lojavirtual.modelo;

public class Produto {
	
	private Integer id;
	private String nome;
	public Produto(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	
	public Produto(Integer id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}


	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	private String descricao;
	
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("O produto é: %d, %s, %s", 
				this.id, this.nome, this.descricao);
	}

}
