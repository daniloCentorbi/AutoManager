package it.dvel.tirocinio.delegate;

import it.dvel.tirocinio.exception.MarcaException;
import it.dvel.tirocinio.exception.ModelloException;
import it.dvel.tirocinio.exception.ProprietarioException;
import it.dvel.tirocinio.exception.StoricoException;
import it.dvel.tirocinio.manager.MarcaManager;
import it.dvel.tirocinio.manager.ModelloManager;
import it.dvel.tirocinio.manager.ProprietarioManager;
import it.dvel.tirocinio.manager.StoricoManager;
import it.dvel.tirocinio.model.Marca;
import it.dvel.tirocinio.model.Modello;
import it.dvel.tirocinio.model.Proprietario;
import it.dvel.tirocinio.model.Storico;

import java.util.List;

public class BusinessDelegate {
	/*
	 * **********************************************************************
	 * *********************** INIZIO SEZIONE MARCHE ************************
	 * **********************************************************************
	 */
	public synchronized List<Marca> getAllMarche() throws MarcaException {
		return MarcaManager.getAll();
	}

	public synchronized List<Marca> getAllMarche(int limit, int offset)
			throws MarcaException {
		return MarcaManager.getAll(limit, offset);
	}

	public synchronized Marca getMarca(int id) throws MarcaException {
		return MarcaManager.get(id);
	}

	public synchronized int countAllMarche() throws MarcaException {
		return MarcaManager.countAll();
	}

	public synchronized Marca insert(Marca marca) throws MarcaException {
		return MarcaManager.insert(marca);
	}

	public synchronized Marca update(Marca marca) throws MarcaException {
		return MarcaManager.update(marca);
	}

	public synchronized int delete(Marca marca) throws MarcaException {
		return MarcaManager.delete(marca);
	}

	public synchronized int deleteMarca(int id) throws MarcaException {
		return MarcaManager.delete(id);
	}

	/*
	 * **********************************************************************
	 * ************************ FINE SEZIONE MARCHE *************************
	 * **********************************************************************
	 */

	/*
	 * **********************************************************************
	 * *********************** INIZIO SEZIONE MODELLI ***********************
	 * **********************************************************************
	 */
	public synchronized List<Modello> getAllModelli() throws ModelloException {
		return ModelloManager.getAll();
	}

	public synchronized List<Modello> getAllModelli(int limit, int offset)
			throws ModelloException {
		return ModelloManager.getAll(limit, offset);
	}

	public synchronized Modello getModello(int id) throws ModelloException {
		return ModelloManager.get(id);
	}

	public synchronized int countAllModelli() throws ModelloException {
		return ModelloManager.countAll();
	}

	public synchronized Modello insert(Modello modello) throws ModelloException {
		return ModelloManager.insert(modello);
	}

	public synchronized Modello update(Modello modello) throws ModelloException {
		return ModelloManager.update(modello);
	}

	public synchronized int delete(Modello modello) throws ModelloException {
		return ModelloManager.delete(modello);
	}

	public synchronized int deleteModello(int id) throws ModelloException {
		return ModelloManager.delete(id);
	}

	/*
	 * **********************************************************************
	 * ************************ FINE SEZIONE MODELLI ************************
	 * **********************************************************************
	 */

	/*
	 * **********************************************************************
	 * ********************* INIZIO SEZIONE PROPRIETARI *********************
	 * **********************************************************************
	 */
	public synchronized List<Proprietario> getAllProprietari()
			throws ProprietarioException {
		return ProprietarioManager.getAll();
	}

	public synchronized List<Proprietario> getAllProprietari(int limit,
			int offset) throws ProprietarioException {
		return ProprietarioManager.getAll(limit, offset);
	}

	public synchronized Proprietario getProprietario(int id)
			throws ProprietarioException {
		return ProprietarioManager.get(id);
	}

	public synchronized int countAllProprietari() throws ProprietarioException {
		return ProprietarioManager.countAll();
	}

	public synchronized Proprietario insert(Proprietario proprietario)
			throws ProprietarioException {
		return ProprietarioManager.insert(proprietario);
	}

	public synchronized Proprietario update(Proprietario proprietario)
			throws ProprietarioException {
		return ProprietarioManager.update(proprietario);
	}

	public synchronized int delete(Proprietario proprietario)
			throws ProprietarioException {
		return ProprietarioManager.delete(proprietario);
	}

	public synchronized int deleteProprietario(int id)
			throws ProprietarioException {
		return ProprietarioManager.delete(id);
	}

	/*
	 * **********************************************************************
	 * ********************** FINE SEZIONE PROPRIETARI **********************
	 * **********************************************************************
	 */

	/*
	 * **********************************************************************
	 * *********************** INIZIO SEZIONE STORICO ***********************
	 * **********************************************************************
	 */
	public synchronized List<Storico> getAllStorico() throws StoricoException {
		return StoricoManager.getAll();
	}

	public synchronized List<Storico> getAllStorico(int limit, int offset)
			throws StoricoException {
		return StoricoManager.getAll(limit, offset);
	}

	public synchronized Storico getStorico(Storico storico)
			throws StoricoException {
		return StoricoManager.get(storico);
	}

	public synchronized int countAllStorico() throws StoricoException {
		return StoricoManager.countAll();
	}

	public synchronized Storico insert(Storico storico) throws StoricoException {
		return StoricoManager.insert(storico);
	}

	public synchronized Storico update(Storico storicoOld, Storico storicoNew)
			throws StoricoException {
		return StoricoManager.update(storicoOld, storicoNew);
	}

	public synchronized int delete(Storico storico) throws StoricoException {
		return StoricoManager.delete(storico);
	}
	/*
	 * **********************************************************************
	 * ************************ FINE SEZIONE STORICO ************************
	 * **********************************************************************
	 */
}