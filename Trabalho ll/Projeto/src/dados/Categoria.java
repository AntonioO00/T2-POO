package dados;

public enum Categoria {

	ACT("Acao"),

	STR("Estrategia"),

	SIM("Simulacao");

	private final String nome;

	private Categoria(final String nome) {
		this.nome = nome;
	}

    public String getNome() {
		return nome;
		}

}
