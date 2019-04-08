package it.dvel.tirocinio.model;

public class Marca {
	private int id;
	private String nome;

	/**
	 * Costruttore di default
	 */
	public Marca() {
		this(0, "");
	}

	/**
	 * Costruttore di istanza
	 * 
	 * @param id
	 *            Chiave primaria
	 * @param nome
	 *            Nome della marca
	 */
	public Marca(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		return nome.equals(((Marca) obj).nome);
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(id).hashCode();
	}

	@Override
	public String toString() {
		return id + " - " + nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}