package dao;

import org.hibernate.Session;

import entity.PartidoResults;

/**
 * Represents a Data Access Object (DAO) for managing operations related to
 * PartidoResults entity.
 * 
 * This class extends the GenericDAOImpl and provides additional methods
 * specific to partidoResults entities.
 * 
 * @author Sirpa_Jesus
 */
public class PartidoResultsDAO extends GenericDAOImpl<PartidoResults> {

	public PartidoResultsDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

}
