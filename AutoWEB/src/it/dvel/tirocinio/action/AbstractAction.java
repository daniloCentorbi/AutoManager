package it.dvel.tirocinio.action;

import it.dvel.tirocinio.delegate.BusinessDelegate;
import it.dvel.tirocinio.utils.EditMode;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Classe astratta che rappresenta il punto di ingresso di tutte le action di
 * Struts2 e realizza il pattern Template: tutti i metodi specificati nel file
 * struts.xml fanno riferimento a questa classe e le sottoclassi implementano
 * solamente funzionalità accessorie e specifiche. La classe è studiata per
 * supportare i generics, pertanto le sottocloassi che la estendono dovranno
 * specificare a quale classe del model fanno riferimento.
 * 
 * @author Marco Napolitano
 * 
 * @param <T>
 *            Classe del model specificata nelle sottoclassi, che risolve il
 *            generics
 */
public abstract class AbstractAction<T> extends ActionSupport implements
		ModelDriven<T>, ServletRequestAware {
	private static final long serialVersionUID = -5236987276502015786L;
	private static final Log LOG = LogFactory.getLog(AbstractAction.class);

	/** Nome della lista visualizzata dalla DisplayTag */
	private static final String LIST_ID = "listid";
	/** Numero di record da visualizzare in pagina */
	public static final int PAGE_SIZE = 3;
	/** Business delegate per le chiamate al database */
	protected BusinessDelegate bd = new BusinessDelegate();
	/** Request HTTP */
	protected HttpServletRequest request;
	/** Numero della pagina da visualizzare */
	protected int pageNumber;
	/** Colonna su cui ordinare i risultati */
	protected String sortColumn;
	/** Descending is 2, Ascending is 1 */
	protected int sortOrder;
	/** Lista dei record da visualizzare, legata ai generics */
	protected List<T> mainList;
	/** Modalità di edit del form di inserimento dati */
	protected EditMode editMode;

	/**
	 * Restitusce l'elenco dei record da visualizzre
	 * 
	 * @return Restitusce l'elenco dei record da visualizzre
	 */
	protected abstract List<T> innerList() throws Exception;

	/**
	 * Restituisce il numero totale di record presenti in tabella
	 * 
	 * @return Restituisce il numero totale di record presenti in tabella
	 */
	public abstract int getCount();

	/**
	 * Effettua l'inserimento di un record in tabella
	 * 
	 * @return Restituisce {@link Action#SUCCESS} se tutto ok, altrimenti
	 *         restituisce {@link Action#INPUT}
	 */
	protected abstract String innerInsert();

	/**
	 * Effettua l'aggiornamento di un record in tabella
	 * 
	 * @return Restituisce {@link Action#SUCCESS} se tutto ok, altrimenti
	 *         restituisce {@link Action#INPUT}
	 */
	protected abstract String innerUpdate();

	/**
	 * Prepara il record da visualizzare in pagina per la modifica
	 * 
	 * @return Restituisce {@link Action#SUCCESS} se tutto ok, altrimenti
	 *         restituisce {@link Action#INPUT}
	 */
	protected abstract String innerPreupdate();

	/**
	 * Effettua la cancellazione di un record dalla tabella
	 * 
	 * @return Restituisce {@link Action#SUCCESS} se tutto ok, altrimenti
	 *         restituisce {@link Action#ERROR}
	 */
	public abstract String delete();

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * Restituisce il numero di record da visualizzare in pagina
	 * 
	 * @return Restituisce il numero di record da visualizzare in pagina
	 */
	public int getPageSize() {
		return PAGE_SIZE;
	}

	/**
	 * Recupera l'elenco dei record da visualizzare, paginando i risultati; qui
	 * si implementa il pattern Template
	 * 
	 * @return Restituisce {@link Action#SUCCESS} se tutto ok, altrimenti
	 *         restituisce {@link Action#ERROR} oppure {@link Action#INPUT}
	 */
	public String list() {
		try {
			pageNumber = Integer
					.parseInt(request.getParameter((new ParamEncoder(LIST_ID)
							.encodeParameterName(TableTagParameters.PARAMETER_PAGE))));
		} catch (NumberFormatException e) {
			pageNumber = -1;
		}
		sortColumn = request.getParameter((new ParamEncoder(LIST_ID)
				.encodeParameterName(TableTagParameters.PARAMETER_SORT)));
		try {
			sortOrder = Integer
					.parseInt(request.getParameter((new ParamEncoder(LIST_ID)
							.encodeParameterName(TableTagParameters.PARAMETER_ORDER))));
		} catch (NumberFormatException e) {
			sortOrder = 1;
		}

		try {
			/*
			 * la funzione list è quella principale in cui viene visualizzato
			 * l'elenco e quindi il form deve essere in modalità inserimento
			 */
			setEditMode(EditMode.INSERT);

			/*
			 * tramite l'implementazione del metodo nelle sottoclassi, viene
			 * recuperato l'elenco dei record da visualizzare; pattern Template
			 */
			mainList = innerList();

			return SUCCESS;
		} catch (Exception e) {
			LOG.error(e);
			return ERROR;
		}
	}

	/**
	 * Restituisce la lista dei record paginati
	 * 
	 * @return Restituisce la lista dei record paginati
	 */
	public List<T> getMainList() {
		return mainList;
	}

	/**
	 * Restituisce la modalità di inserimento del form
	 * 
	 * @return Restituisce la modalità di inserimento del form
	 */
	public EditMode getEditMode() {
		return editMode;
	}

	/**
	 * Imposta la modalità di inserimento del form
	 * 
	 * @return Modalità di inserimento del form
	 */
	public void setEditMode(EditMode editMode) {
		this.editMode = editMode;
	}

	/**
	 * Prepara i dati per la modifica del singolo record
	 * 
	 * @return Restituisce {@link Action#SUCCESS} se tutto ok, altrimenti
	 *         restituisce {@link Action#ERROR} oppure {@link Action#INPUT}
	 */
	public String preupdate() {
		try {
			/*
			 * il form di inserimento/aggiornamento è nella stessa pagina della
			 * lista, quindi va caricata la lista
			 */
			if (list() == SUCCESS) {
				/*
				 * la modalità è ora quella di modifica
				 */
				setEditMode(EditMode.EDIT);

				return innerPreupdate();
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			LOG.error(e);
			return ERROR;
		}
	}

	/**
	 * Gestisce l'inserimento/modifica di un record
	 * 
	 * @return Restituisce {@link Action#SUCCESS} se tutto ok, altrimenti
	 *         restituisce {@link Action#ERROR} oppure {@link Action#INPUT}
	 */
	public String insert() {
		switch (editMode) {
		case INSERT:
			return innerInsert();
		case EDIT:
			return innerUpdate();
		}

		return ERROR;
	}
}