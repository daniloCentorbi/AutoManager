package it.dvel.tirocinio.action.storico;

import it.dvel.tirocinio.action.AbstractAction;
import it.dvel.tirocinio.exception.StoricoException;
import it.dvel.tirocinio.model.Modello;
import it.dvel.tirocinio.model.Proprietario;
import it.dvel.tirocinio.model.Storico;
import it.dvel.tirocinio.model.StoricoWrapper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StoricoAction extends AbstractAction<Storico> {
	private static final long serialVersionUID = -1382512053440165818L;

	private static final Log LOG = LogFactory.getLog(StoricoAction.class);

	private StoricoWrapper storico = new StoricoWrapper();

	private List<Proprietario> proprietari;
	private List<Modello> modelli;

	@Override
	protected List<Storico> innerList() throws Exception {
		LOG.debug("Recupero elenco storico");

		proprietari = bd.getAllProprietari();
		modelli = bd.getAllModelli();

		if (pageNumber == -1) {
			return bd.getAllStorico();
		} else {
			return bd.getAllStorico(getPageSize(), pageNumber);
		}
	}

	@Override
	public int getCount() {
		try {
			return bd.countAllStorico();
		} catch (StoricoException e) {
			LOG.error(e);
			return 0;
		}
	}

	@Override
	public Storico getModel() {
		return storico;
	}

	@Override
	protected String innerInsert() {
		try {
			bd.insert(storico);
			return SUCCESS;
		} catch (StoricoException e) {
			LOG.error(e);
			return INPUT;
		}
	}

	@Override
	protected String innerUpdate() {
		try {
			bd.update(storico.getStoricoOld(), storico);
			return SUCCESS;
		} catch (StoricoException e) {
			LOG.error(e);
			return INPUT;
		}
	}

	@Override
	protected String innerPreupdate() {
		try {
			Storico temp = bd.getStorico(storico);
			getModel().setIdproprietario(temp.getIdproprietario());
			getModel().setIdmodello(temp.getIdmodello());
			getModel().setTarga(temp.getTarga());
			return SUCCESS;
		} catch (StoricoException e) {
			LOG.error(e);
			return INPUT;
		}
	}

	@Override
	public String delete() {
		try {
			bd.delete(storico);
			return SUCCESS;
		} catch (StoricoException e) {
			LOG.error(e);
			return ERROR;
		}
	}

	public List<Proprietario> getProprietari() {
		return proprietari;
	}

	public List<Modello> getModelli() {
		return modelli;
	}
}