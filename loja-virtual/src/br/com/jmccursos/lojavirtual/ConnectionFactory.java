package br.com.jmccursos.lojavirtual;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o uruario:");
		String root=sc.next();
		System.out.println("Digite a senha:");
		String senha=sc.next();
		sc.close();
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser(root);
		comboPooledDataSource.setPassword(senha);		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection recuperaConexao() throws SQLException {		
		return this.dataSource.getConnection();
		
	}

}
