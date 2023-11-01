package dados;

public class JogoTabuleiro extends Jogo {

	private int numeropecas;

	public JogoTabuleiro(String nome, int ano, double precobase, int numeropecas) {
		super(nome, ano, precobase);
		this.numeropecas = numeropecas;
	}

	@Override
	public String toString() {
		return super.toString() + ", " +numeropecas;
	}

	public int getNumeropecas() {
		return numeropecas;
	}

	@Override
	public double calculaPrecoFinal() {
		return getPrecoBase() + ((getPrecoBase() * 0.01) * getNumeropecas());
	}
}
