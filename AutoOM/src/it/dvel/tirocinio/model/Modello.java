package it.dvel.tirocinio.model;

import it.dvel.tirocinio.annotation.ForeignKey;
import it.dvel.tirocinio.annotation.Loading;

public class Modello {
	private int id;
	private int idmarca;
	private String modello;
	private String cilindrata;
	private int anno;
	@ForeignKey(field = "idmarca", loading = Loading.LAZY)
	private Marca marca;

	public Modello() {
		this(0, 0, "", "", 0);
	}

	public Modello(int id, int idmarca, String modello, String cilindrata,
			int anno) {
		this.id = id;
		this.idmarca = idmarca;
		this.modello = modello;
		this.cilindrata = cilindrata;
		this.anno = anno;
		this.marca = null;
	}

	@Override
	public boolean equals(Object obj) {
		Modello other = (Modello) obj;

		if (idmarca == other.idmarca) {
			return modello.equals(other.modello)
					&& cilindrata.equals(other.cilindrata)
					&& anno == other.anno;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Integer.valueOf(id).hashCode();
	}

	@Override
	public String toString() {
		return id + " - " + modello + " - " + cilindrata + " - " + anno;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdmarca() {
		return idmarca;
	}

	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getCilindrata() {
		return cilindrata;
	}

	public void setCilindrata(String cilindrata) {
		this.cilindrata = cilindrata;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}
}