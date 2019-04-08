package it.dvel.tirocinio.dao;

import it.dvel.tirocinio.model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ProprietarioDAOImpl implements ProprietarioDAO {
	private static final Log LOG = LogFactory.getLog(ProprietarioDAOImpl.class);

	private static final String SELECT_ALL = "SELECT * FROM proprietari ORDER BY cognome,nome";
	private static final String SELECT_ALL_PAGED = "SELECT * FROM proprietari ORDER BY cognome,nome LIMIT ? OFFSET ?";
	private static final String SELECT_SINGLE = "SELECT * FROM proprietari WHERE id=?";
	private static final String COUNT_ALL = "SELECT COUNT(*) FROM proprietari";
	private static final String UPDATE = "UPDATE proprietari SET nome=?, cognome=? WHERE id=?";
	private static final String DELETE = "DELETE FROM proprietari WHERE id=?";
	private static final String INSERT = "INSERT INTO proprietari (nome, cognome) VALUES (?,?)";

	@Override
	public List<Proprietario> getResults(ResultSet rs) throws SQLException {
		List<Proprietario> elenco = new ArrayList<Proprietario>();
		while (rs.next()) {
			Proprietario proprietario = new Proprietario();
			proprietario.setId(rs.getInt("id"));
			proprietario.setNome(rs.getString("nome"));
			proprietario.setCognome(rs.getString("cognome"));
			elenco.add(proprietario);
		}
		return elenco;
	}

	@Override
	public List<Proprietario> getAll(Connection connection) throws SQLException {
		LOG.info("Invoco la query: " + SELECT_ALL);

		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
		ResultSet res = stmt.executeQuery();
		List<Proprietario> elenco = getResults(res);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		res.close();
		stmt.close();

		return elenco;
	}

	@Override
	public List<Proprietario> getAll(int limit, int offset,
			Connection connection) throws SQLException {
		LOG.info("Invoco la query (L=" + limit + ", O=" + offset + "): "
				+ SELECT_ALL_PAGED);

		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_PAGED);
		int i = 1;
		stmt.setInt(i++, limit);
		stmt.setInt(i++, offset);
		ResultSet res = stmt.executeQuery();
		List<Proprietario> elenco = getResults(res);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		res.close();
		stmt.close();

		return elenco;
	}

	@Override
	public Proprietario get(int id, Connection connection) throws SQLException {
		LOG.info("Invoco la query: " + SELECT_SINGLE);

		PreparedStatement stmt = connection.prepareStatement(SELECT_SINGLE);
		int i = 1;
		stmt.setInt(i++, id);
		ResultSet res = stmt.executeQuery();
		List<Proprietario> elenco = getResults(res);

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
	public Proprietario insert(Proprietario proprietario, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query [" + INSERT + "] con l'oggetto ["
				+ proprietario + "]");

		PreparedStatement stmt = connection.prepareStatement(INSERT,
				Statement.RETURN_GENERATED_KEYS);
		int i = 1;
		stmt.setString(i++, proprietario.getNome());
		stmt.setString(i++, proprietario.getCognome());
		stmt.executeUpdate();

		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		proprietario.setId(rs.getInt(1));

		LOG.debug("Oggetto valorizzato: " + proprietario);

		rs.close();
		stmt.close();

		return proprietario;
	}

	@Override
	public int delete(int id, Connection connection) throws SQLException {
		Proprietario proprietario = new Proprietario(id, "", "");

		return delete(proprietario, connection);
	}

	@Override
	public int delete(Proprietario proprietario, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query [" + DELETE + "] con l'oggetto ["
				+ proprietario + "]");

		PreparedStatement stmt = connection.prepareStatement(DELETE);
		int i = 1;
		stmt.setInt(i++, proprietario.getId());
		int deleted = stmt.executeUpdate();

		LOG.debug("Record effettivamente cancellati: " + deleted);

		stmt.close();

		return deleted;
	}

	@Override
	public Proprietario update(Proprietario proprietario, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query [" + UPDATE + "] sull'oggetto ["
				+ proprietario + "]");

		PreparedStatement stmt = connection.prepareStatement(UPDATE);
		int i = 1;
		stmt.setString(i++, proprietario.getNome());
		stmt.setString(i++, proprietario.getCognome());
		stmt.setInt(i++, proprietario.getId());
		stmt.executeUpdate();

		LOG.debug("Oggetto aggiornato: " + proprietario);

		stmt.close();

		return proprietario;
	}
}