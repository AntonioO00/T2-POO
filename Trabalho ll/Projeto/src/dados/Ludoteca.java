package dados;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Ludoteca implements Iterador {

	private JogoEletronico jogoEletronico;
	private JogoTabuleiro jogoTabuleiro;

	ArrayList<Jogo> jogos = new ArrayList<Jogo>();

	private int contador;


	    public ArrayList<Jogo> getJogos () {
		return jogos;
	}

		public boolean addJogo (Jogo jogo){
			jogos.add(jogo);
			if (jogo == null) {
				return false;
			}
			return true;
		}

		public Jogo consultaPorNome(String nome) {
		for (Jogo jogo : jogos) {
			if (nome.equals(jogo.getNome())) {
				return jogo;
			}
		}
		return null;
	}



		public ArrayList<Jogo> consultaPorAno ( int ano){
			ArrayList<Jogo> jogodata = new ArrayList<>();
			for (Jogo j : jogos) {
				if (j.getAno() == ano) {
					jogodata.add(j);
				}
			}
			if (jogodata.isEmpty()) {
				return null;
			}
			return jogodata;

		}

		public Categoria value(String categoria){
			Categoria value = null;
			if (categoria.equals(Categoria.ACT.getNome())){
				value = Categoria.ACT;
				return value;
			}
			if (categoria.equals(Categoria.SIM.getNome())){
				value = Categoria.SIM;
				return value;
			}
			if (categoria.equals(Categoria.STR.getNome())){
				value = Categoria.STR;
				return value;
			}
			else {return value;}

		}

		public double Double(double valor){
			DecimalFormat decimal = new DecimalFormat("0.00");
			return Double.parseDouble(decimal.format(valor));
		}


		/**
		 * @see dados.Iterador#reset()
		 */
		public void reset () {
			contador = 0;

		}

		/**
		 * @see dados.Iterador#hasNext()
		 */
		public boolean hasNext () {
			if (contador >= jogos.size() || jogos.get(contador) == null) {
				return false;
			} else {
				return true;
			}
		}

		/**
		 * @see dados.Iterador#next()
		 */
		public Object next () {
			Jogo next = jogos.get(contador);
			contador++;
			return next;

	}
}
