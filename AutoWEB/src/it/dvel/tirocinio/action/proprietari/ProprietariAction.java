package it.dvel.tirocinio.action.proprietari;

import it.dvel.tirocinio.action.AbstractAction;
import it.dvel.tirocinio.exception.ProprietarioException;
import it.dvel.tirocinio.model.Proprietario;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProprietariAction extends AbstractAction<Proprietario> {
	private static final long serialVersionUID = 7362498122754325629L;

	private static final Log LOG = LogFactory.getLog(ProprietariAction.class);

	private Proprietario proprietario = new Proprietario();

	@Override
	protected List<Proprietario> innerList() throws Exception {
		LOG.debug("Recupero elenco proprietari");

		if (pageNumber == -1) {
			return bd.getAllProprietari();
		} else {
			return bd.getAllProprietari(getPageSize(), pageNumber);
		}
	}

	@Override
	public int getCount() {
		try {
			return bd.countAllProprietari();
		} catch (ProprietarioException e) {
			LOG.error(e);
			return 0;
		}
	}

	@Override
	public Proprietario getModel() {
		return proprietario;
	}

	@Override
	public String innerInsert() {
		try {
			bd.insert(proprietario);
			return SUCCESS;
		} catch (ProprietarioException e) {
			LOG.error(e);
			return ERROR;
		}
	}

	@Override
	public String innerUpdate() {
		try {
			bd.update(proprietario);
			return SUCCESS;
		} catch (ProprietarioException e) {
			LOG.error(e);
			return ERROR;
		}
	}

	@Override
	public String innerPreupdate() {
		try {
			Proprietario temp = bd.getProprietario(proprietario.getId());
			getModel().setNome(temp.getNome());
			getModel().setCognome(temp.getCognome());
			return SUCCESS;
		} catch (ProprietarioException e) {
			LOG.error(e);
			return ERROR;
		}
	}

	@Override
	public String delete() {
		try {
			bd.deleteProprietario(proprietario.getId());
			return SUCCESS;
		} catch (ProprietarioException e) {
			LOG.error(e);
			return ERROR;
		}
	}
}