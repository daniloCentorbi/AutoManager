package it.dvel.tirocinio.manager;

import it.dvel.tirocinio.dao.StoricoDAO;
import it.dvel.tirocinio.dao.StoricoDAOImpl;
import it.dvel.tirocinio.exception.StoricoException;
import it.dvel.tirocinio.model.Storico;
import it.dvel.tirocinio.utils.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StoricoManager {
	private static final Log LOG = LogFactory.getLog(StoricoManager.class);
	private static StoricoDAO dao = new StoricoDAOImpl();

	public static List<Storico> getAll() throws StoricoException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			List<Storico> elenco = dao.getAll(connection);
			connection.close();

			return elenco;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new StoricoException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new StoricoException(e);
		}
	}

	public static List<Storico> getAll(int limit, int offset)
			throws StoricoException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			List<Storico> elenco = dao.getAll(limit, offset, connection);
			connection.close();

			return elenco;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new StoricoException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new StoricoException(e);
		}
	}

	public static Storico get(Storico storico) throws StoricoException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			storico = dao.get(storico, connection);
			connection.close();

			return storico;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new StoricoException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new StoricoException(e);
		}
	}

	public static int countAll() throws StoricoException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int count = dao.countAll(connection);
			connection.close();

			return count;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new StoricoException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new StoricoException(e);
		}
	}

	public static Storico insert(Storico storico) throws StoricoException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			storico = dao.insert(storico, connection);
			connection.close();
			return storico;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new StoricoException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new StoricoException(e);
		}
	}

	public static Storico update(Storico storicoOld, Storico storicoNew)
			throws StoricoException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			Storico storico = dao.update(storicoOld, storicoNew, connection);
			connection.close();
			return storico;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new StoricoException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new StoricoException(e);
		}
	}

	public static int delete(Storico storico) throws StoricoException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int i = dao.delete(storico, connection);
			connection.close();
			return i;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new StoricoException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new StoricoException(e);
		}
	}
}