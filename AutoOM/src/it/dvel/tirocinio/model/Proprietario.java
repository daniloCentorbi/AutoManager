package it.dvel.tirocinio.model;

public class Proprietario {
	private int id;
	private String nome;
	private String cognome;

	public Proprietario() {
		this(0, "", "");
	}

	public Proprietario(int id, String nome, String cognome) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}

	@Override
	public boolean equals(Object obj) {
		Proprietario other = (Proprietario) obj;

		return nome.equals(other.nome) && cognome.equals(other.cognome);
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(id).hashCode();
	}

	@Override
	public String toString() {
		return id + " - " + nome + " - " + cognome;
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

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
}