package dados;

import java.util.ArrayList;

public class JogoEletronico extends Jogo {


	private String plataforma;
	private Categoria categoria;


	public JogoEletronico(String nome, int ano, double precobase, String plataforma, Categoria categoria) {
		super(nome, ano, precobase);
		this.plataforma = plataforma;
		this.categoria = categoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public String getPlataforma() {return plataforma;}

	@Override
	public double calculaPrecoFinal() {
		double precof = 0;
		if (categoria.equals(Categoria.STR)) {
			precof = getPrecoBase() + (getPrecoBase() * 0.7);
			return precof;
		}
		if (categoria.equals(Categoria.SIM)) {
			precof = getPrecoBase() + (getPrecoBase() * 0.3);
			return precof;
		}
		else {
			precof = getPrecoBase() + (getPrecoBase() * 0.1);
			return precof;
		}
	}

	@Override
	public String toString() {
		return super.toString() + ", " + plataforma + ", " + categoria.getNome()+ "," + "R$ " +calculaPrecoFinal();
	}


}




