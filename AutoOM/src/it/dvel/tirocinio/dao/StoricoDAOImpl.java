package it.dvel.tirocinio.dao;

import it.dvel.tirocinio.manager.ModelloManager;
import it.dvel.tirocinio.manager.ProprietarioManager;
import it.dvel.tirocinio.model.Storico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StoricoDAOImpl implements StoricoDAO {
	private static final Log LOG = LogFactory.getLog(StoricoDAOImpl.class);

	private static final String SELECT_ALL = "SELECT * FROM storico ORDER BY idproprietario,idmodello";
	private static final String SELECT_ALL_PAGED = "SELECT * FROM storico ORDER BY idproprietario,idmodello LIMIT ? OFFSET ?";
	private static final String SELECT_SINGLE = "SELECT * FROM storico WHERE idproprietario=? AND idmodello=? AND targa=?";
	private static final String COUNT_ALL = "SELECT COUNT(*) FROM storico";
	private static final String SELECT_ALL_BY_MODELLO = "SELECT * FROM storico WHERE idmodello=? ORDER BY idproprietario";
	private static final String SELECT_ALL_BY_PROPRIETARIO = "SELECT * FROM storico WHERE idproprietario=? ORDER BY idmodello";
	private static final String UPDATE = "UPDATE storico SET idproprietario=?, idmodello=?, targa=? WHERE idproprietario=? AND idmodello=? AND targa=?";
	private static final String DELETE = "DELETE FROM storico WHERE idproprietario=? AND idmodello=? AND targa=?";
	private static final String INSERT = "INSERT INTO storico (idproprietario, idmodello, targa) VALUES (?,?,?)";

	@Override
	public List<Storico> getResults(ResultSet rs) throws SQLException {
		List<Storico> elenco = new ArrayList<Storico>();
		while (rs.next()) {
			Storico storico = new Storico();
			storico.setIdproprietario(rs.getInt("idproprietario"));
			storico.setIdmodello(rs.getInt("idmodello"));
			storico.setTarga(rs.getString("targa"));

			try {
				storico.setProprietario(ProprietarioManager.get(storico
						.getIdproprietario()));
				storico.setModello(ModelloManager.get(storico.getIdmodello()));
			} catch (Exception e) {
				throw new SQLException(e);
			}

			elenco.add(storico);
		}
		return elenco;
	}

	@Override
	public List<Storico> getAll(Connection connection) throws SQLException {
		LOG.info("Invoco la query: " + SELECT_ALL);

		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
		ResultSet rs = stmt.executeQuery();
		List<Storico> elenco = getResults(rs);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		rs.close();
		stmt.close();

		return elenco;
	}

	@Override
	public List<Storico> getAll(int limit, int offset, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query (L=" + limit + ", O=" + offset + "): "
				+ SELECT_ALL_PAGED);

		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_PAGED);
		int i = 1;
		stmt.setInt(i++, limit);
		stmt.setInt(i++, offset);
		ResultSet res = stmt.executeQuery();
		List<Storico> elenco = getResults(res);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		res.close();
		stmt.close();

		return elenco;
	}

	@Override
	public Storico get(int id, Connection connection) throws SQLException {
		LOG.error("Metodo non applicabile per la classe Storico");
		throw new UnsupportedOperationException(
				"Metodo non applicabile per la classe Storico");
	}

	@Override
	public int countAll(Connection connection) throws SQLException {
		LOG.info("Invoco la query: " + COUNT_ALL);

		PreparedStatement stmt = connection.prepareStatement(COUNT_ALL);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		LOG.debug("Valore recuperato: " + count);

		rs.close();
		stmt.close();

		return count;
	}

	@Override
	public Storico insert(Storico storico, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query [" + INSERT + "] con l'oggetto [" + storico
				+ "]");

		PreparedStatement stmt = connection.prepareStatement(INSERT);
		int i = 1;
		stmt.setInt(i++, storico.getIdproprietario());
		stmt.setInt(i++, storico.getIdmodello());
		stmt.setString(i++, storico.getTarga());
		stmt.executeUpdate();

		LOG.debug("Oggetto valorizzato: " + storico);

		stmt.close();

		return storico;
	}

	@Override
	public int delete(int id, Connection connection) throws SQLException {
		LOG.error("Metodo non applicabile per la classe Storico");
		throw new UnsupportedOperationException(
				"Metodo non applicabile per la classe Storico");
	}

	@Override
	public int delete(Storico storico, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query [" + DELETE + "] con l'oggetto [" + storico
				+ "]");

		PreparedStatement stmt = connection.prepareStatement(DELETE);
		int i = 1;
		stmt.setInt(i++, storico.getIdproprietario());
		stmt.setInt(i++, storico.getIdmodello());
		stmt.setString(i++, storico.getTarga());
		int deleted = stmt.executeUpdate();

		LOG.debug("Record effettivamente cancellati: " + deleted);

		stmt.close();

		return deleted;
	}

	@Override
	public Storico update(Storico storico, Connection connection)
			throws SQLException {
		LOG.error("Metodo non applicabile per la classe Storico");
		throw new UnsupportedOperationException(
				"Metodo non applicabile per la classe Storico");
	}

	@Override
	public Storico get(Storico storico, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query: " + SELECT_SINGLE);

		PreparedStatement stmt = connection.prepareStatement(SELECT_SINGLE);
		int i = 1;
		stmt.setInt(i++, storico.getIdproprietario());
		stmt.setInt(i++, storico.getIdmodello());
		stmt.setString(i++, storico.getTarga());
		ResultSet res = stmt.executeQuery();
		List<Storico> elenco = getResults(res);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		res.close();
		stmt.close();

		if (elenco.size() == 1)
			return elenco.get(0);
		else {
			LOG.error("Nessun record trovato");
			throw new SQLException("Nessun record trovato");
		}
	}

	@Override
	public List<Storico> getAllByModello(int idmodello, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query: " + SELECT_ALL_BY_MODELLO);

		PreparedStatement stmt = connection
				.prepareStatement(SELECT_ALL_BY_MODELLO);
		ResultSet rs = stmt.executeQuery();
		List<Storico> elenco = getResults(rs);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		rs.close();
		stmt.close();

		return elenco;
	}

	@Override
	public List<Storico> getAllByProprietario(int idproprietario,
			Connection connection) throws SQLException {
		LOG.info("Invoco la query: " + SELECT_ALL_BY_PROPRIETARIO);

		PreparedStatement stmt = connection
				.prepareStatement(SELECT_ALL_BY_PROPRIETARIO);
		ResultSet rs = stmt.executeQuery();
		List<Storico> elenco = getResults(rs);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		rs.close();
		stmt.close();

		return elenco;
	}

	@Override
	public Storico update(Storico storicoOld, Storico storicoNew,
			Connection connection) throws SQLException {
		LOG.info("Invoco la query [" + UPDATE + "] sull'oggetto vecchio ["
				+ storicoOld + "] e nuovo [" + storicoNew + "]");

		PreparedStatement stmt = connection.prepareStatement(UPDATE);
		int i = 1;
		stmt.setInt(i++, storicoNew.getIdproprietario());
		stmt.setInt(i++, storicoNew.getIdmodello());
		stmt.setString(i++, storicoNew.getTarga());
		stmt.setInt(i++, storicoOld.getIdproprietario());
		stmt.setInt(i++, storicoOld.getIdmodello());
		stmt.setString(i++, storicoOld.getTarga());
		stmt.executeUpdate();

		LOG.debug("Oggetto aggiornato: " + storicoNew);

		stmt.close();

		return storicoNew;
	}
}