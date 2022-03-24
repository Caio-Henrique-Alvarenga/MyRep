package com.ti2cc;


public class Filme {
	private int codigo;
	private String nome;
	private int ano;
	private String diretor;
	private int nota;
	
	public Filme() {
		this.codigo = -1;
		this.nome = "";
		this.ano = 0;
		this.diretor = "";
		this.nota = 0;
	}
	
	public Filme(int codigo, String nome, int ano, String diretor, int nota) {
		this.codigo = codigo;
		this.nome = nome;
		this.ano = ano;
		this.diretor = diretor;
		this.nota = nota;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	
	public int getNota() {
		return nota;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Filme [codigo=" + codigo + ", nome=" + nome + ", ano=" + ano + ", diretor=" + diretor + ", nota="+nota+"]";
	}	
}
