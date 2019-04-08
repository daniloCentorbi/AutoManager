package it.dvel.tirocinio.dao;

import it.dvel.tirocinio.exception.MarcaException;
import it.dvel.tirocinio.manager.MarcaManager;
import it.dvel.tirocinio.model.Marca;
import it.dvel.tirocinio.model.Modello;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ModelloDAOImpl implements ModelloDAO {
	private static final Log LOG = LogFactory.getLog(ModelloDAOImpl.class);

	private static final String SELECT_ALL = "SELECT mo.*,ma.nome as marca FROM modelli mo, marche ma WHERE mo.idmarca=ma.id ORDER BY marca,mo.modello";
	private static final String SELECT_ALL_PAGED = "SELECT mo.*,ma.nome as marca FROM modelli mo, marche ma WHERE mo.idmarca=ma.id ORDER BY marca,mo.modello LIMIT ? OFFSET ?";
	private static final String SELECT_SINGLE = "SELECT mo.*,ma.nome as marca FROM modelli mo, marche ma WHERE mo.id=? AND mo.idmarca=ma.id";
	private static final String COUNT_ALL = "SELECT COUNT(*) FROM modelli";
	private static final String UPDATE = "UPDATE modelli SET idmarca=?, modello=?, cilindrata=?, anno=? WHERE id=?";
	private static final String DELETE = "DELETE FROM modelli WHERE id=?";
	private static final String INSERT = "INSERT INTO modelli (idmarca,modello,cilindrata,anno) VALUES (?,?,?,?)";

	@Override
	public List<Modello> getResults(ResultSet rs) throws SQLException {
		List<Modello> elenco = new ArrayList<Modello>();
		while (rs.next()) {
			Modello modello = new Modello();
			modello.setId(rs.getInt("id"));
			modello.setIdmarca(rs.getInt("idmarca"));
			modello.setModello(rs.getString("modello"));
			modello.setCilindrata(rs.getString("cilindrata"));
			modello.setAnno(rs.getInt("anno"));

			Marca marca = new Marca();
			marca.setId(rs.getInt("idmarca"));
			marca.setNome(rs.getString("marca"));
			modello.setMarca(marca);

			elenco.add(modello);
		}
		return elenco;
	}

	@Override
	public List<Modello> getAll(Connection connection) throws SQLException {
		LOG.info("Invoco la query: " + SELECT_ALL);

		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL);
		ResultSet rs = stmt.executeQuery();
		List<Modello> elenco = getResults(rs);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		rs.close();
		stmt.close();

		return elenco;
	}

	@Override
	public List<Modello> getAll(int limit, int offset, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query (L=" + limit + ", O=" + offset + "): "
				+ SELECT_ALL_PAGED);

		PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_PAGED);
		int i = 1;
		stmt.setInt(i++, limit);
		stmt.setInt(i++, offset);
		ResultSet res = stmt.executeQuery();
		List<Modello> elenco = getResults(res);

		LOG.debug("Recuperati " + elenco.size() + " elementi");

		res.close();
		stmt.close();

		return elenco;
	}

	@Override
	public Modello get(int id, Connection connection) throws SQLException {
		LOG.info("Invoco la query: " + SELECT_SINGLE);

		PreparedStatement stmt = connection.prepareStatement(SELECT_SINGLE);
		int i = 1;
		stmt.setInt(i++, id);
		ResultSet res = stmt.executeQuery();
		List<Modello> elenco = getResults(res);

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
	public Modello insert(Modello modello, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query [" + INSERT + "] con l'oggetto [" + modello
				+ "]");

		PreparedStatement stmt = connection.prepareStatement(INSERT,
				Statement.RETURN_GENERATED_KEYS);
		int i = 1;
		stmt.setInt(i++, modello.getIdmarca());
		stmt.setString(i++, modello.getModello());
		stmt.setString(i++, modello.getCilindrata());
		stmt.setInt(i++, modello.getAnno());
		stmt.executeUpdate();

		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		modello.setId(rs.getInt(1));

		LOG.debug("Oggetto valorizzato: " + modello);

		rs.close();
		stmt.close();

		return modello;
	}

	@Override
	public int delete(int id, Connection connection) throws SQLException {
		Modello modello = new Modello(id, 0, "", "", 0);

		return delete(modello, connection);
	}

	@Override
	public int delete(Modello modello, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query [" + DELETE + "] con l'oggetto [" + modello
				+ "]");

		PreparedStatement stmt = connection.prepareStatement(DELETE);
		int i = 1;
		stmt.setInt(i++, modello.getId());
		int deleted = stmt.executeUpdate();

		LOG.debug("Record effettivamente cancellati: " + deleted);

		stmt.close();

		return deleted;
	}

	@Override
	public Modello update(Modello modello, Connection connection)
			throws SQLException {
		LOG.info("Invoco la query [" + UPDATE + "] sull'oggetto [" + modello
				+ "]");

		PreparedStatement stmt = connection.prepareStatement(UPDATE);
		int i = 1;
		stmt.setInt(i++, modello.getIdmarca());
		stmt.setString(i++, modello.getModello());
		stmt.setString(i++, modello.getCilindrata());
		stmt.setInt(i++, modello.getAnno());
		stmt.setInt(i++, modello.getId());
		stmt.executeUpdate();
		stmt.close();

		try {
			Marca marca = MarcaManager.get(modello.getIdmarca());
			modello.setMarca(marca);

			LOG.debug("Oggetto aggiornato: " + modello);
		} catch (MarcaException e) {
			LOG.error(e);
			throw new SQLException(e);
		}

		return modello;
	}
}