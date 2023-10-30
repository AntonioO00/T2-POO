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


	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	@Override
	public double calculaPrecoFinal() {
		double precof = 0;
		precof = getPrecoBase() + (getPrecoBase() * 0.1);
		return precof;

	}

	@Override
	public String toString() {
		return super.toString() + ", " + plataforma + ", " + categoria;
	}


}




