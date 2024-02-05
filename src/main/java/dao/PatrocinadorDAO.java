package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Equipo;
import entity.Patrocinador;

/**
 * Represents a Data Access Object (DAO) for managing operations related to
 * Patrocinador entity.
 * 
 * This class extends the GenericDAOImpl and provides additional methods
 * specific to patrocinador entities.
 * 
 * @author Sirpa_Jesus
 */
public class PatrocinadorDAO extends GenericDAOImpl<Patrocinador> {

	public PatrocinadorDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public List<Patrocinador> getPatrocinadoresByEquipo(Equipo equipo) {
		String hql = "SELECT p FROM Equipo e JOIN e.patrocinadores p WHERE e = :equipo";
		Query<Patrocinador> query = session.createQuery(hql, Patrocinador.class);
		query.setParameter("equipo", equipo);
		return query.getResultList();
	}
}
