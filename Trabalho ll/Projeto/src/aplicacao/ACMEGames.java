package aplicacao;


import dados.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;



public class ACMEGames {
	private Scanner entrada = null;
	private PrintStream saidaPadrao = System.out;
	private Ludoteca ludoteca = new Ludoteca();

	public ACMEGames() {
		try {
			BufferedReader streamEntrada = new BufferedReader(new FileReader("dadosin.txt"));
			entrada = new Scanner(streamEntrada);
			PrintStream streamSaida = new PrintStream(new File("dadosout.txt"), Charset.forName("UTF-8"));
			System.setOut(streamSaida);
		} catch (Exception e) {
			System.out.println(e);
		}
		Locale.setDefault(Locale.ENGLISH);
		entrada.useLocale(Locale.ENGLISH);
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
		int ano = 0 ;
		double precobase= 0.0;
		String linha = entrada.nextLine();
		while (!linha.equals("-1")) {
			String[] info = linha.split(";");
			if (info.length >= 4) {
				String nome = info[0];
				ano = Integer.parseInt(info[1]);
				precobase = Double.parseDouble(info[2]);
				String plataforma = info[3];
				String categoria = info[4];

				Jogo existe = ludoteca.consultaPorNome(nome);
				if (existe == null) {
					Categoria cat = ludoteca.value(categoria);
					Jogo jogo = new JogoEletronico(nome, ano, precobase, plataforma, cat);
					ludoteca.addJogo(jogo);
					System.out.println("1: "+jogo.getNome() + "," +"R$ "+ ludoteca.Double(jogo.calculaPrecoFinal()));
				} else {
					System.out.println("ERRO-jogo com nome repitido :" + existe.getNome());

				}
			}
			linha =entrada.nextLine();
		}

	}


		public void cadastrarTabuleiro () {
             String linha = entrada.nextLine();
			 int ano = 0;
			 double precobase= 0;
			 int numeropecas=0;

			while (!linha.equals("-1")) {
				String[] info = linha.split(";");
				if (info.length >= 3) {

					String nome = info[0];
					ano = Integer.parseInt(info[1]);
					precobase = Double.parseDouble(info[2]);
					numeropecas = Integer.parseInt(info[3]);
					Jogo existe = ludoteca.consultaPorNome(nome);

					if (existe == null) {
						JogoTabuleiro jt = new JogoTabuleiro(nome, ano, precobase, numeropecas);
						ludoteca.addJogo(jt);
						System.out.println("2:"+jt.getNome() + "," +"R$ "+ ludoteca.Double(jt.calculaPrecoFinal()));
					} else {
						System.out.println(":Erro-jogo com nome repetido: " + existe.getNome());
					}
				}

                   linha = entrada.nextLine();
			}

		}

		public void mostraDeterminado () {
			String nome = entrada.nextLine();
			if (ludoteca.consultaPorNome(nome) == null) {
				System.out.println("ERRO - Jogo nao existe");
			} else {
				ArrayList<Jogo> jogos = ludoteca.getJogos();
				for (Jogo jogo : jogos) {
					if (jogo.getNome().equals(nome)) {
						System.out.println("3:"+jogo.toString());
					}
				}
			}
		}


	public void mostrarAno () {
		String converte = entrada.nextLine();
		int ano = Integer.parseInt(converte);
		ArrayList<Jogo>data = ludoteca.consultaPorAno(ano);
		if (data == null){
		System.out.println("Nenhum jogo encontrado");
		}
		else {
			for (Jogo jogo: data) {
				System.out.println("4:"+jogo.toString());
			}
		}
	}



	    public void mostrarEletronicos (){
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
					System.out.println("5:"+jogo.toString());
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

		else { System.out.println("6:" +"R$ "+ ludoteca.Double(somatorio));}
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
		else {System.out.println("7:" + maior + "," + "R$ "+ ludoteca.Double(maior.calculaPrecoFinal()));}
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
			System.out.println("Nenhum jogo encontrado");
		}

		else {
		System.out.println("8:"+ ludoteca.Double(media) + " ; " + jogoproximo.toString());
		}

	}


         public void maisAntigo(){
		Jogo antigo = new JogoTabuleiro("neutro", 99999, 0.0, 0);

			 for (Jogo jogo: ludoteca.getJogos()) {
				 if (jogo.getAno() <= antigo.getAno()){
				 antigo = jogo;
				 }
			 }

			 if (antigo == null ){
				 System.out.println("Nenhum jogo encontrado");
			 }
			 else System.out.println("9:"+antigo.getNome() +","+ antigo.getAno());
		 }





}



















