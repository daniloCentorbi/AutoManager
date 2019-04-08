package it.dvel.tirocinio.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia di riferimento per tutti i DAO che dovranno essere utilizzati.
 * Sfrutta i generics per gestire tutte le operazioni standard che un DAO deve
 * fornire all'esterno.
 * 
 * @author Marco Napolitano
 * 
 * @param <T>
 *            Classe del model a cui fa riferimento il singolo DAO
 */
public interface AbstractDAO<T> {
	/**
	 * Metodo di utilità che converte il resultset restituito dallo statement
	 * JDBC in una lista di oggetti del model specificato nel generics
	 * 
	 * @param rs
	 *            Resultset restituito dallo statement JDBC
	 * @return Restituisce una lista di model, specifici della sottoclasse
	 * @throws SQLException
	 *             In caso di errori
	 */
	public List<T> getResults(ResultSet rs) throws SQLException;

	/**
	 * Recupera tutti i record dalla tabella
	 * 
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce la lista degli oggetti del model
	 * @throws SQLException
	 *             In caso di errori
	 */
	public List<T> getAll(Connection connection) throws SQLException;

	/**
	 * Recupera tutti i record dalla tabella, paginandoli
	 * 
	 * @param limit
	 *            Numero di record da visualizzare
	 * @param offset
	 *            Numero della pagina da visualizzare
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce la lista degli oggetti del model
	 * @throws SQLException
	 *             In caso di errori
	 */
	public List<T> getAll(int limit, int offset, Connection connection)
			throws SQLException;

	/**
	 * Conta tutti i record presenti in tabella; serve alla DisplayTag
	 * 
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce il numero dei record
	 * @throws SQLException
	 *             In caso di errori
	 */
	public int countAll(Connection connection) throws SQLException;

	/**
	 * Restituisce un singolo record della tabella
	 * 
	 * @param id
	 *            Chiave primaria del record da recuperare
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce il record recuperato
	 * @throws SQLException
	 *             In caso di errori
	 */
	public T get(int id, Connection connection) throws SQLException;

	/**
	 * Inserimento un record in tabella
	 * 
	 * @param model
	 *            Oggetto del model da inserire
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce il record inserito, con la chiave primaria
	 *         valorizzata
	 * @throws SQLException
	 *             In caso di errori
	 */
	public T insert(T model, Connection connection) throws SQLException;

	/**
	 * Cancella un singolo record della tabella
	 * 
	 * @param id
	 *            Chiave primaria del record da cancellare
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce il numero di record effettivamente cancellati
	 * @throws SQLException
	 *             In caso di errori
	 */
	public int delete(int id, Connection connection) throws SQLException;

	/**
	 * Cancella un singolo record della tabella
	 * 
	 * @param model
	 *            Oggetto del model da cancellare
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce il numero di record effettivamente cancellati
	 * @throws SQLException
	 *             In caso di errori
	 */
	public int delete(T model, Connection connection) throws SQLException;

	/**
	 * Aggiorna un singolo record della tabella
	 * 
	 * @param model
	 *            Oggetto del model da aggiornare
	 * @param connection
	 *            Connessione al database
	 * @return Restituisce il numero di record effettivamente aggiornati
	 * @throws SQLException
	 *             In caso di errori
	 */
	public T update(T model, Connection connection) throws SQLException;
}