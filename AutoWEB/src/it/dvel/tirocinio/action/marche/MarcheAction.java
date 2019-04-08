package it.dvel.tirocinio.action.marche;

import it.dvel.tirocinio.action.AbstractAction;
import it.dvel.tirocinio.exception.MarcaException;
import it.dvel.tirocinio.model.Marca;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MarcheAction extends AbstractAction<Marca> {
	private static final long serialVersionUID = -2345643083552712905L;
	private static final Log LOG = LogFactory.getLog(MarcheAction.class);

	private Marca marca = new Marca();

	@Override
	protected List<Marca> innerList() throws Exception {
		LOG.debug("Recupero elenco marche");

		if (pageNumber == -1) {
			return bd.getAllMarche();
		} else {
			return bd.getAllMarche(getPageSize(), pageNumber);
		}
	}

	@Override
	public int getCount() {
		try {
			return bd.countAllMarche();
		} catch (MarcaException e) {
			LOG.error(e);
			return 0;
		}
	}

	@Override
	public Marca getModel() {
		return marca;
	}

	@Override
	protected String innerInsert() {
		try {
			bd.insert(marca);
			return SUCCESS;
		} catch (MarcaException e) {
			LOG.error(e);
			return INPUT;
		}
	}

	@Override
	protected String innerUpdate() {
		try {
			bd.update(marca);
			return SUCCESS;
		} catch (MarcaException e) {
			LOG.error(e);
			return INPUT;
		}
	}

	@Override
	protected String innerPreupdate() {
		try {
			Marca temp = bd.getMarca(marca.getId());
			getModel().setNome(temp.getNome());
			return SUCCESS;
		} catch (MarcaException e) {
			LOG.error(e);
			return INPUT;
		}
	}

	@Override
	public String delete() {
		try {
			bd.deleteMarca(marca.getId());
			return SUCCESS;
		} catch (MarcaException e) {
			LOG.error(e);
			return ERROR;
		}
	}
}