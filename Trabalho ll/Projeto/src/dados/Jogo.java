package dados;

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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public void setPrecobase(double precobase) {
		this.precobase = precobase;
	}

	public abstract double calculaPrecoFinal();

	@Override
	public String toString() {
		return nome + ", " + ano + ", "+precobase;
	}
}
