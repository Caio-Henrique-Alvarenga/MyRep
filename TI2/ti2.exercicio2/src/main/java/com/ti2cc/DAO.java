package com.ti2cc;


import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirFilme(Filme filme) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO filme (codigo, nome, ano, diretor, nota) "
					       + "VALUES ("+filme.getCodigo()+ ", '" + filme.getNome() + "', '"  
					       + filme.getAno() + "', '" + filme.getDiretor() + "', '" + filme.getNota() +"');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarFilme(Filme filme) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE filme SET nome = '" + filme.getNome() + "', ano = '"  
				       + filme.getAno() + "', diretor = '" + filme.getDiretor() +"', nota = '" + filme.getNota() + "'"
					   + " WHERE codigo = " + filme.getCodigo();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirFilme(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM filme WHERE codigo = " + codigo);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Filme getFilme(int codigo) {
		Filme filme = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM filme WHERE codigo = " + codigo);		
	         if(rs.next()){
	             rs.last();
	             filme = new Filme(rs.getInt("codigo"), rs.getString("nome"),rs.getInt("ano"), 
	            		 rs.getString("diretor"), rs.getInt("nota"));
	             rs.beforeFirst();
	             }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return filme;
	}
	
	
	public Filme[] getFilmes() {
		Filme[] filmes = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM filme");		
	         if(rs.next()){
	             rs.last();
	             filmes = new Filme[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                filmes[i] = new Filme(rs.getInt("codigo"), rs.getString("nome"), 
	                		                  rs.getInt("ano"), rs.getString("diretor"), rs.getInt("nota"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return filmes;
	}
}