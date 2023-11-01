package dados;

import java.text.DecimalFormat;

public abstract class Jogo {

	private String nome;
	private int ano;
	private double precobase;


	public Jogo(String nome, int ano, double precobase) {
		this.nome = nome;
		this.ano = ano;
		this.precobase = precobase;
	}

	public double getPrecoBase() {
		return precobase;
	}

	public String getNome() {
		return nome;
	}

	public int getAno() {
		return ano;
	}

	public double getPrecobase() {
		return precobase;
	}

	public abstract double calculaPrecoFinal();

	public double doubleJogo(double valor){
		DecimalFormat decimal = new DecimalFormat("0.00");
		return Double.parseDouble(decimal.format(valor));
	}
	@Override
	public String toString() {
		return nome + ", " + ano + ", " +"R$ "+doubleJogo(precobase);
	}



}
