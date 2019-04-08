package it.dvel.tirocinio.dao;

import it.dvel.tirocinio.model.Storico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO con le funzionalità specifiche dello storico; tutti i metodi della
 * superclasse sono ereditati e specificati dal generics. In aggiunta ci sono
 * altri metodi di comodo, specifici dello storico.
 * 
 * @author Marco Napolitano
 */
public interface StoricoDAO extends AbstractDAO<Storico> {
	/**
	 * Restituisco un singolo record dalla tabella; è necessario perchè la
	 * classe {@link Storico} ha una chiave primaria multipla e non un semplice
	 * "id" singolo
	 * 
	 * @param storico
	 *            Oggetto da cui recuperare le informazioni per il recupero
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce l'oggetto recuperato
	 * @throws SQLException
	 *             In caso di errori
	 */
	public Storico get(Storico storico, Connection connection)
			throws SQLException;

	/**
	 * Restituisce tutti gli oggetti {@link Storico} che appartengono ad un
	 * determinato modello
	 * 
	 * @param idmodello
	 *            Identificativo del modello da filtrare
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce una lista di oggetti {@link Storico}
	 * @throws SQLException
	 *             In caso di errori
	 */
	public List<Storico> getAllByModello(int idmodello, Connection connection)
			throws SQLException;

	/**
	 * Restituisce tutti gli oggetti {@link Storico} che appartengono ad un
	 * determinato proprietario
	 * 
	 * @param idproprietario
	 *            Identificativo del proprietario da filtrare
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce una lista di oggetti {@link Storico}
	 * @throws SQLException
	 *             In caso di errori
	 */
	public List<Storico> getAllByProprietario(int idproprietario,
			Connection connection) throws SQLException;

	/**
	 * Aggiorna un oggetto di tipo {@link Storico}; è necessario perchè la
	 * classe {@link Storico} ha una chiave primaria multipla e non un semplice
	 * "id" singolo, quindi bisogna gestire 2 oggetti separatamente
	 * 
	 * @param storicoOld
	 *            Vecchio oggetto che contiene le informazioni per identificare
	 *            l'oggetto da aggiornare
	 * @param storicoNew
	 *            Nuovo oggetto che contiene le nuove informazioni da aggiornare
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce l'oggetto con i dati aggiornati
	 * @throws SQLException
	 *             In caso di errori
	 */
	public Storico update(Storico storicoOld, Storico storicoNew,
			Connection connection) throws SQLException;
}