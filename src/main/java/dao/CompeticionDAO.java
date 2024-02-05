package dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import entity.Competicion;

public class CompeticionDAO extends GenericDAOImpl<Competicion> {

	public CompeticionDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public Competicion getCompetitionNativeQuery(Long competicionId) {
		String nativeQuery = "SELECT * FROM competition WHERE id = :competicionId";
        NativeQuery<Competicion> query = session.createNativeQuery(nativeQuery, Competicion.class);
        query.setParameter("competicionId", competicionId);

        return query.uniqueResult();
	}
}
