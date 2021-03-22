package maven.demo2;

import java.util.Scanner;

public class Principal {

	// variavel de leitura
	public static Scanner sc = new Scanner(System.in);

public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		//menu de escolha
		int opcao = 0;
		System.out.println("Escolha uma opção: \n 1 - Listar \n 2 - Inserir \n "
				+ "3 - Excluir \n 4 - Atualizar \n 5 - Sair \n");

		Ator ator;
		int j = 0;
		
		while(opcao != 5) {
			Ator [] ators = dao.getAtor();
			int tamanhoLista = ators.length+1+j;
			opcao = sc.nextInt();
			//listar atores
			if(opcao == 1) {
				System.out.println("\n==== Mostrar Atores === ");		
				for(int i = 0; i < ators.length; i++) {
					System.out.println(ators[i].toString());
				}
			}
			//insirerir atores
			if(opcao == 2) {
				System.out.println("Informe o nome do ator:");
				sc.nextLine();
				String nm = sc.nextLine();
				System.out.println("Informe o nome do filme:");
				String filme = sc.nextLine();
				System.out.println("Informe o sexo do ator:");
				String aux = sc.nextLine();
				char sexo = aux.charAt(0);
				ator = new Ator(tamanhoLista, nm, filme, sexo);
				if(dao.inserirAtor(ator) == true) {
					System.out.println("Inserção com sucesso -> " + ator.toString());				
				}
			}
			//excluir ator
			if(opcao == 3) {
				System.out.println("Informe o codigo do ator que deseja excluir:");
				int cod = sc.nextInt();
				dao.excluirAtor(cod);
				j++; //contar quantos foram excluidos
			}
			//atualizar ator
			if(opcao == 4) {
				System.out.println("Informe o nome do ator:");
				sc.nextLine();
				String nome = sc.nextLine();
				ators[0].setNome(nome);
				dao.atualizarAtor(ators[0]);
			}	
		}//fim opcoes menu
		System.out.println("Programa encerrado!");
		
		dao.close();
	}
}
