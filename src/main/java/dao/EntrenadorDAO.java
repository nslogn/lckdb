package dao;

import org.hibernate.Session;

import entity.Entrenador;

/**
 * Represents a Data Access Object (DAO) for managing operations related to
 * Entrenador entity.
 * 
 * This class extends the GenericDAOImpl and provides additional methods
 * specific to Entrenador entities.
 * 
 * @author Sirpa_Jesus
 */
public class EntrenadorDAO extends GenericDAOImpl<Entrenador> {

	public EntrenadorDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}
}
