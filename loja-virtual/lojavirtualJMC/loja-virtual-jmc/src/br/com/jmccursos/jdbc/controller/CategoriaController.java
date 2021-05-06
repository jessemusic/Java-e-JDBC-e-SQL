package br.com.jmccursos.jdbc.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.jmccursos.jdbc.dao.CategoriaDAO;
import br.com.jmccursos.jdbc.factory.ConnectionFactory;
import br.com.jmccursos.jdbc.modelo.Categoria;

public class CategoriaController {
	
	private CategoriaDAO categoriaDAO;
	
	public CategoriaController() {
		Connection connection =
				new ConnectionFactory().recuperarConexao();
		 this.categoriaDAO = new CategoriaDAO(connection);
	}

	public List<Categoria> listar() {
		return this.categoriaDAO.listar();
	}
}
