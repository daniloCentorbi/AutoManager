package it.dvel.tirocinio.action.modelli;

import it.dvel.tirocinio.action.AbstractAction;
import it.dvel.tirocinio.exception.ModelloException;
import it.dvel.tirocinio.model.Marca;
import it.dvel.tirocinio.model.Modello;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ModelliAction extends AbstractAction<Modello> {
	private static final long serialVersionUID = -563474821072205849L;
	private static final Log LOG = LogFactory.getLog(ModelliAction.class);

	private Modello modello = new Modello();
	private List<Marca> marche;

	public List<Marca> getMarche() {
		return marche;
	}

	@Override
	protected List<Modello> innerList() throws Exception {
		LOG.debug("Recupero elenco marche");
		marche = bd.getAllMarche();

		LOG.debug("Recupero elenco modelli");

		if (pageNumber == -1) {
			return bd.getAllModelli();
		} else {
			return bd.getAllModelli(getPageSize(), pageNumber);
		}
	}

	@Override
	public int getCount() {
		try {
			return bd.countAllModelli();
		} catch (ModelloException e) {
			LOG.error(e);
			return 0;
		}
	}

	@Override
	public Modello getModel() {
		return modello;
	}

	@Override
	public String innerInsert() {
		try {
			bd.insert(modello);
			return SUCCESS;
		} catch (ModelloException e) {
			LOG.error(e);
			return ERROR;
		}
	}

	@Override
	public String innerUpdate() {
		try {
			bd.update(modello);
			return SUCCESS;
		} catch (ModelloException e) {
			LOG.error(e);
			return ERROR;
		}
	}

	@Override
	public String innerPreupdate() {
		try {
			Modello temp = bd.getModello(modello.getId());
			getModel().setId(temp.getId());
			getModel().setIdmarca(temp.getIdmarca());
			getModel().setModello(temp.getModello());
			getModel().setCilindrata(temp.getCilindrata());
			getModel().setAnno(temp.getAnno());
			return SUCCESS;
		} catch (ModelloException e) {
			LOG.error(e);
			return ERROR;
		}
	}

	@Override
	public String delete() {
		try {
			bd.deleteModello(modello.getId());
			return SUCCESS;
		} catch (ModelloException e) {
			LOG.error(e);
			return ERROR;
		}
	}
}