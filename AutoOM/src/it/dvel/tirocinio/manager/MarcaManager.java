package it.dvel.tirocinio.manager;

import it.dvel.tirocinio.dao.MarcaDAO;
import it.dvel.tirocinio.dao.MarcaDAOImpl;
import it.dvel.tirocinio.exception.MarcaException;
import it.dvel.tirocinio.model.Marca;
import it.dvel.tirocinio.utils.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MarcaManager {
	private static final Log LOG = LogFactory.getLog(MarcaManager.class);
	private static MarcaDAO dao = new MarcaDAOImpl();

	public static List<Marca> getAll() throws MarcaException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			List<Marca> elenco = dao.getAll(connection);
			connection.close();

			return elenco;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new MarcaException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new MarcaException(e);
		}
	}

	public static List<Marca> getAll(int limit, int offset)
			throws MarcaException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			List<Marca> elenco = dao.getAll(limit, offset, connection);
			connection.close();

			return elenco;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new MarcaException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new MarcaException(e);
		}
	}

	public static Marca get(int id) throws MarcaException {
		try {
			Marca marca = new Marca();
			Connection connection = DBConnector.getInstance().getConnection();
			marca = dao.get(id, connection);
			connection.close();

			return marca;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new MarcaException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new MarcaException(e);
		}
	}

	public static int countAll() throws MarcaException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int count = dao.countAll(connection);
			connection.close();

			return count;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new MarcaException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new MarcaException(e);
		}
	}

	public static Marca insert(Marca marca) throws MarcaException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			marca = dao.insert(marca, connection);
			connection.close();
			return marca;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new MarcaException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new MarcaException(e);
		}
	}

	public static Marca update(Marca marca) throws MarcaException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			marca = dao.update(marca, connection);
			connection.close();
			return marca;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new MarcaException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new MarcaException(e);
		}
	}

	public static int delete(int id) throws MarcaException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int i = dao.delete(id, connection);
			connection.close();
			return i;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new MarcaException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new MarcaException(e);
		}
	}

	public static int delete(Marca marca) throws MarcaException {
		try {
			Connection connection = DBConnector.getInstance().getConnection();
			int i = dao.delete(marca, connection);
			connection.close();
			return i;
		} catch (NamingException e) {
			LOG.fatal(e);
			throw new MarcaException(e);
		} catch (SQLException e) {
			LOG.error(e);
			throw new MarcaException(e);
		}
	}
}