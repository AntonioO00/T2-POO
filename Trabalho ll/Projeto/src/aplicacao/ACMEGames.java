package aplicacao;

import dados.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ACMEGames {
	private Ludoteca ludoteca = new Ludoteca();
	Scanner entrada = new Scanner(System.in);
	public ACMEGames() {
		executa();
	}


	public void executa(){
		 cadastraEletronico();
	     cadastrarTabuleiro();
		 mostraDeterminado();
		 mostrarAno();
		 mostrarEletronicos();
		 somatorioFinal();
		 maiorTabuleiro();
		 mostrarMedia();
		 maisAntigo();
	}

		public void cadastraEletronico () {
			System.out.println("Digite o nome do jogo");
			String nome = entrada.nextLine();
			System.out.println("Digite o ano do jogo");
			int ano = entrada.nextInt();
			System.out.println("Digite o preço base do jogo");
			double precobase = entrada.nextDouble();
			entrada.nextLine();
			System.out.println("Digite a plataforma do jogo");
			String plataforma = entrada.nextLine();
			System.out.println("Digite a categoria do jogo");
			String categoria = entrada.nextLine();
            Jogo existe = ludoteca.consultaPorNome(nome);
			if (existe == null) {
				Categoria cat = ludoteca.value(categoria);
				Jogo jogo = new JogoEletronico(nome, ano, precobase, plataforma, cat);
				ludoteca.addJogo(jogo);
				System.out.println(jogo.getNome() + "," + jogo.calculaPrecoFinal());
			}
			else {
				System.out.println("ERRO-jogo com nome repitido :" +existe.getNome());
			}

		}


		public void cadastrarTabuleiro () {
			System.out.println("Digite o nome do jogo");
			String nome = entrada.nextLine();
			System.out.println("Digite o ano do jogo");
			int ano = entrada.nextInt();
			System.out.println("Digite o preço base do jogo");
			double precobase = entrada.nextDouble();
			System.out.println("Digite o numero de peças do jogo");
			int numeropecas = entrada.nextInt();
			entrada.nextLine();
			Jogo existe = ludoteca.consultaPorNome(nome);

			 if (existe == null) {
				JogoTabuleiro jt = new JogoTabuleiro(nome, ano, precobase, numeropecas);
				ludoteca.addJogo(jt);
				System.out.println(jt.getNome() + "," + jt.calculaPrecoFinal());
			}
			 else {
				System.out.println(":Erro-jogo com nome repetido: " + existe.getNome());
			}

		}

		public void mostraDeterminado () {
			System.out.println("Digite o nome do jogo que deseja pesquisar");
			String nome = entrada.nextLine();
			if (ludoteca.consultaPorNome(nome) == null) {
				System.out.println("ERRO - Jogo nao existe");
			} else {
				ArrayList<Jogo> jogos = ludoteca.getJogos();
				for (Jogo jogo : jogos) {
					if (jogo.getNome().equals(nome)) {
						System.out.println(jogo.toString());
					}
				}
			}
		}


	public void mostrarAno () {
		System.out.println("Digite o ano dos jogos");
		int ano = entrada.nextInt();
		ArrayList<Jogo>data = ludoteca.consultaPorAno(ano);
		if (data == null){
		System.out.println("Nenhum jogo encontrado");
		}
		else {
			for (Jogo jogo: data) {
				System.out.println(jogo.toString());
				System.out.println("___________________");
			}
		}
	}



	    public void mostrarEletronicos (){
		  System.out.println("Digite a categoria dos jogos");
		  String categoria = entrada.nextLine();

		  ArrayList<Jogo> array = ludoteca.getJogos();

		   List<JogoEletronico> jogoseletronicos = array
				.stream()
				.filter(jogo -> jogo instanceof JogoEletronico)
				.map(jogo -> (JogoEletronico) jogo)
				.toList();

		   Categoria cate = ludoteca.value(categoria);
		   if (cate != null) {
			for (JogoEletronico jogo : jogoseletronicos) {
				if (jogo.getCategoria().equals(cate)) {
					System.out.println(jogo.toString());
					System.out.println("________________________");
				}
			}
		}
		else { System.out.println("Nao existe jogo dessa categoria");}
	}


	public void somatorioFinal () {
		double somatorio = 0;
	     	for (Jogo jogo : ludoteca.getJogos()) {
			somatorio += jogo.calculaPrecoFinal();}

		if (somatorio == 0) {System.out.println("Erro: Nenhum jogo encontrado");}

		else { System.out.println("O valor do somatorio foi :" + somatorio);}
	}




	public void maiorTabuleiro () {
		JogoTabuleiro maior = new JogoTabuleiro("neutro", 0, 0.0, 0);
		ArrayList<Jogo> array = ludoteca.getJogos();
		List<JogoTabuleiro> jogostabu = array
				.stream()
				.filter(jogo -> jogo instanceof JogoTabuleiro)
				.map(jogo -> (JogoTabuleiro) jogo)
				.toList();

		    for (JogoTabuleiro jogo : jogostabu) {
			if (jogo.calculaPrecoFinal() >= maior.calculaPrecoFinal()) {
				maior = jogo;}}

		if (maior.calculaPrecoFinal() == 0) {
			System.out.println("ERRO: Nenhum jogo encontrado");
		}
		else {System.out.println("O maior jogo de tabuleiro é :" + maior.toString());}
	}

	public void mostrarMedia(){
		double soma = 0;
		Jogo jogoproximo = null;
		double minimo = 9999999999999999999.999999;

		for (Jogo jogo : ludoteca.getJogos()) {
			soma += jogo.getPrecoBase();
		}

		double media = soma / ludoteca.getJogos().size();

		for (Jogo jogo : ludoteca.getJogos()) {
			double diferenca = Math.abs(jogo.getPrecoBase() - soma);
			if (diferenca < minimo) {
				minimo = diferenca;
				jogoproximo = jogo;
			}
		}

		if (jogoproximo == null){
			System.out.println("Nenhum jogo encintrado");
		}

		else {
		System.out.println("media dos preços base: "+ media + " ; " + jogoproximo.toString());
		}

	}


         public void maisAntigo(){
	         Jogo antigo = null;

			 for (Jogo jogo: ludoteca.getJogos()) {
				 if (jogo.getAno() <= antigo.getAno()){
				 antigo = jogo;
				 }
			 }

			 if (antigo == null ){
				 System.out.println("Nenhum jogo encontrado");
			 }
			 else System.out.println(antigo.toString());
		 }





}



















