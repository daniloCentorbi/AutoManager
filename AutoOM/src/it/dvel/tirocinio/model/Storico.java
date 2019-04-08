package it.dvel.tirocinio.model;

public class Storico {
	private int idproprietario;
	private int idmodello;
	private String targa;

	private Proprietario proprietario;
	private Modello modello;

	public Storico() {
		this(0, 0, "", null, null);
	}

	public Storico(int idproprietario, int idmodello, String targa) {
		this(idproprietario, idmodello, targa, null, null);
	}

	public Storico(int idproprietario, int idmodello, String targa,
			Modello modello, Proprietario proprietario) {
		this.idproprietario = idproprietario;
		this.idmodello = idmodello;
		this.targa = targa;
		this.modello = modello;
		this.proprietario = proprietario;
	}

	@Override
	public boolean equals(Object obj) {
		Storico other = (Storico) obj;

		return idproprietario == other.idproprietario
				&& idmodello == other.idmodello && targa.equals(other.targa);
	}

	@Override
	public int hashCode() {
		return ("" + idproprietario + "-" + idmodello + "-" + targa).hashCode();
	}

	@Override
	public String toString() {
		return idproprietario + " - " + idmodello + " - " + targa;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Modello getModello() {
		return modello;
	}

	public void setModello(Modello modello) {
		this.modello = modello;
	}

	public int getIdproprietario() {
		return idproprietario;
	}

	public void setIdproprietario(int idproprietario) {
		this.idproprietario = idproprietario;
	}

	public int getIdmodello() {
		return idmodello;
	}

	public void setIdmodello(int idmodello) {
		this.idmodello = idmodello;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}
}