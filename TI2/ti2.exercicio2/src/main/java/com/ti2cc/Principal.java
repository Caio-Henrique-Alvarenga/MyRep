package com.ti2cc;

import java.util.*;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();
		
		int opcao = 0;
		Scanner input = new Scanner(System.in);
		do {
			System.out
					.println("\n\n### Menu ###");
			System.out.println("\n                  ========================");
			System.out.println("                  |     1 - Listar          |");
			System.out.println("                  |     2 - Inserir         |");
			System.out.println("                  |     3 - Excluir         |");
			System.out.println("                  |     4 - Atualizar       |");
			System.out.println("                  |     5 - Sair            |");
			System.out.println("                  =========================\n");

			opcao = input.nextInt();
			System.out.print("\n");
			switch (opcao) {
			case 1:
				//Mostrar filmes
				Filme[] filmes = dao.getFilmes();
				System.out.println("==== Mostrar filmes === ");		
				for(int i = 0; i < filmes.length; i++) {
					System.out.println(filmes[i].toString());
				}
				break;
			case 2:
				//Inserir um elemento na tabela
				System.out.println("Digite o codigo do filme");
			    int id = input.nextInt();
			    System.out.println("Digite o nome do filme");
			    input.nextLine();
			    String nome = input.nextLine();
			    System.out.println("Digite o ano de estreia do filme");
			    int ano =  input.nextInt();
			    input.nextLine();
			    System.out.println("Digite o nome do diretor do filme");
			    String diretor = input.nextLine();
			    System.out.println("Digite a nota do filme");
			    int nota = input.nextInt();
				Filme filme = new Filme(id, nome, ano, diretor, nota);
				if(dao.inserirFilme(filme) == true) {
					System.out.println("Inserção com sucesso -> " + filme.toString());
				}
				break;
			case 3:
				System.out.println("Digite o codigo do filme que sera excluido");
			    int excluir = input.nextInt();
				dao.excluirFilme(excluir);
				break;
			case 4:
				//Atualizar filme
				System.out.println("Digite o codigo do filme que sera atualizado");
			    int idatualizar = input.nextInt();
				Filme atualizar = dao.getFilme(idatualizar);
				System.out.println("Digite a nova nota do filme");
			    int novanota = input.nextInt();
				atualizar.setNota(novanota);
				dao.atualizarFilme(atualizar);
				break;
			case 5:
				break;
			default:
				System.out.println("Opção Inválida!");
				break;
			}
		} while (opcao != 5);
		input.close();
		dao.close();
	}
	}