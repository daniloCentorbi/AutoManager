package it.dvel.tirocinio.manager;

import it.dvel.tirocinio.dao.ModelloDAO;
import it.dvel.tirocinio.dao.ModelloDAOImpl;
import it.dvel.tirocinio.exception.ModelloException;
import it.dvel.tirocinio.model.Modello;
import it.dvel.tirocinio.utils.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ModelloManager {
	private static final Log LOG = LogFactory.getLog(ModelloManager.class);
	private static ModelloDAO dao = new ModelloDAOImpl();

	public static List<Modello> getAll() throws ModelloException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			List<Modello> elenco = dao.getAll(connection);
			connection.close();

			return elenco;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ModelloException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ModelloException(e);
		}
	}

	public static List<Modello> getAll(int limit, int offset)
			throws ModelloException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			List<Modello> elenco = dao.getAll(limit, offset, connection);
			connection.close();

			return elenco;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ModelloException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ModelloException(e);
		}
	}

	public static Modello get(int id) throws ModelloException {
		try {
			Modello marca = new Modello();
			Connection connection = DBConnector.getInstance().getConnection();
			marca = dao.get(id, connection);
			connection.close();

			return marca;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ModelloException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ModelloException(e);
		}
	}

	public static int countAll() throws ModelloException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int count = dao.countAll(connection);
			connection.close();

			return count;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ModelloException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ModelloException(e);
		}
	}

	public static Modello insert(Modello modello) throws ModelloException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			modello = dao.insert(modello, connection);
			connection.close();
			return modello;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ModelloException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ModelloException(e);
		}
	}

	public static Modello update(Modello modello) throws ModelloException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			modello = dao.update(modello, connection);
			connection.close();
			return modello;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ModelloException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ModelloException(e);
		}
	}

	public static int delete(int id) throws ModelloException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int i = dao.delete(id, connection);
			connection.close();
			return i;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ModelloException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ModelloException(e);
		}
	}

	public static int delete(Modello modello) throws ModelloException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int i = dao.delete(modello, connection);
			connection.close();
			return i;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ModelloException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ModelloException(e);
		}
	}
}