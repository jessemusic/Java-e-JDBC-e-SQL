package br.com.jmccursos.jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;

	public ConnectionFactory() {

		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("senha");

		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperarConexao(){
		try {
		return this.dataSource.getConnection();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
