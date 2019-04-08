package it.dvel.tirocinio.model;

public class StoricoWrapper extends Storico {
	private Storico storicoOld;

	public StoricoWrapper() {
		super();
		this.storicoOld = new Storico();
	}

	public StoricoWrapper(int idproprietario, int idmodello, String targa,
			Modello modello, Proprietario proprietario) {
		super(idproprietario, idmodello, targa, modello, proprietario);
		this.storicoOld = new Storico(idproprietario, idmodello, targa,
				modello, proprietario);
	}

	public StoricoWrapper(int idproprietario, int idmodello, String targa) {
		super(idproprietario, idmodello, targa);
		this.storicoOld = new Storico(idproprietario, idmodello, targa);
	}

	public Storico getStoricoOld() {
		return storicoOld;
	}

	public void setStoricoOld(Storico storicoOld) {
		this.storicoOld = storicoOld;
	}
}