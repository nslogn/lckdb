package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Equipo;
import entity.Patrocinador;

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
