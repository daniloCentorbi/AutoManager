package it.dvel.tirocinio.dao;

import it.dvel.tirocinio.model.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MarcaDAOImpl implements MarcaDAO {
	private static final Log LOG = LogFactory.getLog(MarcaDAOImpl.class);

	private static final String SELECT_ALL = "SELECT * FROM marche ORDER BY nome";
	private static final String SELECT_ALL_PAGED = "SELECT * FROM marche ORDER BY nome LIMIT ? OFFSET ?";
	private static final String SELECT_SINGLE = "SELECT * FROM marche WHERE id=?";
	private static final String COUNT_ALL = "SELECT COUNT(*) FROM marche";
	private static final String UPDATE = "UPDATE marche SET nome=? WHERE id=?";
	private static final String DELETE = "DELETE FROM marche WHERE id=?";
	private static final String INSERT = "INSERT INTO marche (nome) VALUES (?)";

	@Override
	public List<Marca> getResults(ResultSet rs) throws SQLException {
		List<Marca> elenco = new ArrayList<Marca>();
		while (rs.next()) {
			Marca marca = new Marca();
			marca.setId(rs.getInt("id"));
			marca.setNome(rs.getString("nome"));
			elenco.add(marca);
		}
		return elenco;
	}

	@Override
	public List<Marca> getAll(Connection connection) throws SQLException {
		/*
		LOG.info("Invoco la query: " + SELECT_ALL);

		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
		ResultSet res = stmt.executeQuery();
		List<Marca> elenco = getResults(res);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		res.close();
		stmt.close();

*/
		return null;
		
	}

	@Override
	public List<Marca> getAll(int limit, int offset, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query (L=" + limit + ", O=" + offset + "): "
				+ SELECT_ALL_PAGED);

		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_PAGED);
		int i = 1;
		stmt.setInt(i++, limit);
		stmt.setInt(i++, offset);
		ResultSet res = stmt.executeQuery();
		List<Marca> elenco = getResults(res);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		res.close();
		stmt.close();

		return elenco;
	}

	@Override
	public Marca get(int id, Connection connection) throws SQLException {
		LOG.info("Invoco la query: " + SELECT_SINGLE);

		PreparedStatement stmt = connection.prepareStatement(SELECT_SINGLE);
		int i = 1;
		stmt.setInt(i++, id);
		ResultSet res = stmt.executeQuery();
		List<Marca> elenco = getResults(res);

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
	public Marca insert(Marca marca, Connection connection) throws SQLException {
		LOG.info("Invoco la query [" + INSERT + "] con l'oggetto [" + marca
				+ "]");

		PreparedStatement stmt = connection.prepareStatement(INSERT,
				Statement.RETURN_GENERATED_KEYS);
		int i = 1;
		stmt.setString(i++, marca.getNome());
		stmt.executeUpdate();

		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		marca.setId(rs.getInt(1));

		LOG.debug("Oggetto valorizzato: " + marca);

		rs.close();
		stmt.close();

		return marca;
	}

	@Override
	public int delete(int id, Connection connection) throws SQLException {
		Marca marca = new Marca(id, "");

		return delete(marca, connection);
	}

	@Override
	public int delete(Marca marca, Connection connection) throws SQLException {
		LOG.info("Invoco la query [" + DELETE + "] con l'oggetto [" + marca
				+ "]");

		PreparedStatement stmt = connection.prepareStatement(DELETE);
		int i = 1;
		stmt.setInt(i++, marca.getId());
		int deleted = stmt.executeUpdate();

		LOG.debug("Record effettivamente cancellati: " + deleted);

		stmt.close();

		return deleted;
	}

	@Override
	public Marca update(Marca marca, Connection connection) throws SQLException {
		LOG.info("Invoco la query [" + UPDATE + "] sull'oggetto [" + marca
				+ "]");

		PreparedStatement stmt = connection.prepareStatement(UPDATE);
		int i = 1;
		stmt.setString(i++, marca.getNome());
		stmt.setInt(i++, marca.getId());
		stmt.executeUpdate();

		LOG.debug("Oggetto aggiornato: " + marca);

		stmt.close();

		return marca;
	}
}