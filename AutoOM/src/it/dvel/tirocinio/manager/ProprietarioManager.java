package it.dvel.tirocinio.manager;

import it.dvel.tirocinio.dao.ProprietarioDAO;
import it.dvel.tirocinio.dao.ProprietarioDAOImpl;
import it.dvel.tirocinio.exception.ProprietarioException;
import it.dvel.tirocinio.model.Proprietario;
import it.dvel.tirocinio.utils.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProprietarioManager {
	private static final Log LOG = LogFactory.getLog(ProprietarioManager.class);
	private static ProprietarioDAO dao = new ProprietarioDAOImpl();

	public static List<Proprietario> getAll() throws ProprietarioException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			List<Proprietario> elenco = dao.getAll(connection);
			connection.close();

			return elenco;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ProprietarioException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProprietarioException(e);
		}
	}

	public static List<Proprietario> getAll(int limit, int offset)
			throws ProprietarioException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			List<Proprietario> elenco = dao.getAll(limit, offset, connection);
			connection.close();

			return elenco;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ProprietarioException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProprietarioException(e);
		}
	}

	public static Proprietario get(int id) throws ProprietarioException {
		try {
			Proprietario marca = new Proprietario();
			Connection connection = DBConnector.getInstance().getConnection();
			marca = dao.get(id, connection);
			connection.close();

			return marca;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ProprietarioException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProprietarioException(e);
		}
	}

	public static int countAll() throws ProprietarioException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int count = dao.countAll(connection);
			connection.close();

			return count;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ProprietarioException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProprietarioException(e);
		}
	}

	public static Proprietario insert(Proprietario proprietario)
			throws ProprietarioException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			proprietario = dao.insert(proprietario, connection);
			connection.close();
			return proprietario;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ProprietarioException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProprietarioException(e);
		}
	}

	public static Proprietario update(Proprietario proprietario)
			throws ProprietarioException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			proprietario = dao.update(proprietario, connection);
			connection.close();
			return proprietario;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ProprietarioException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProprietarioException(e);
		}
	}

	public static int delete(int id) throws ProprietarioException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int i = dao.delete(id, connection);
			connection.close();
			return i;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ProprietarioException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProprietarioException(e);
		}
	}

	public static int delete(Proprietario proprietario)
			throws ProprietarioException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int i = dao.delete(proprietario, connection);
			connection.close();
			return i;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new ProprietarioException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new ProprietarioException(e);
		}
	}
}