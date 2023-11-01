package dados;

public class JogoTabuleiro extends Jogo {

	private int numeropecas;

	public JogoTabuleiro(String nome, int ano, double precobase, int numeropecas) {
		super(nome, ano, precobase);
		this.numeropecas = numeropecas;
	}

	public int getNumeropecas() {
		return numeropecas;
	}

	@Override
	public double calculaPrecoFinal() {
		return getPrecoBase() + ((getPrecoBase() * 0.01) * getNumeropecas());
	}
}
