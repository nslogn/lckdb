package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.TypedQuery;
import entity.Competicion;
import entity.Equipo;
import entity.Jugador;

/**
 * Represents a Data Access Object (DAO) for managing operations related to
 * Equipo entity.
 * 
 * This class extends the GenericDAOImpl and provides additional methods
 * specific to Equipo entities.
 * 
 * @author Sirpa_Jesus
 */
public class EquipoDAO extends GenericDAOImpl<Equipo> {

	public EquipoDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public List<String> getClasificacion() {
		String jpql = "SELECT UPPER(e.nombre), e.puntos FROM Equipo e ORDER BY e.puntos DESC";
		TypedQuery<Object[]> query = session.createQuery(jpql, Object[].class);

		List<String> clasificacion = new ArrayList<>();
		for (Object[] row : query.getResultList()) {
			String nombre = (String) row[0];
			int puntos = (int) row[1];
			String equipoString = String.format("%s - Puntos: %d", nombre, puntos);
			clasificacion.add(equipoString);
		}
		return clasificacion;
	}

	public void updatePosicionActual(Equipo equipo, Integer nuevaPosicion) {
		equipo.setPosicionActual(nuevaPosicion);
//		update(equipo);
	}

	public void updatePuntos(Equipo equipo, Integer nuevosPuntos) {
		equipo.setPuntos(nuevosPuntos);
		update(equipo);
	}

	public int addNuevosJugadores(Equipo equipo, List<Jugador> jugadores) {
		int count = 0;
		for (Jugador j : jugadores) {
			j.setEquipo(equipo);
			equipo.getJugadores().add(j);
			count++;
		}
		return count;
	}

	public int removerJugadores(Equipo equipo, List<Jugador> jugadores) {
		int count = 0;
		for (Jugador j : jugadores) {
//			session.detach(j);
			equipo.getJugadores().remove(j);
			count++;
		}
		return count;
	}

	public List<Equipo> getEquiposByCompeticion(Competicion competicion) {
		String hql = "SELECT e FROM Competicion c JOIN c.equipos e WHERE c = :competicion";
		Query<Equipo> query = session.createQuery(hql, Equipo.class);
		query.setParameter("competicion", competicion);
		return query.getResultList();
	}

	public List<Jugador> getJugadoresByEquipo(Equipo equipo) {
		String hql = "SELECT j FROM Equipo e JOIN e.jugadores j WHERE e = :equipo";
		Query<Jugador> query = session.createQuery(hql, Jugador.class);
		query.setParameter("equipo", equipo);
		return query.getResultList();
	}

	public String getEquipoByPosicion(int posicion) {
		String hql = "SELECT e.nombre FROM Equipo e WHERE e.posicionActual = :posicion";
		Query<String> query = session.createQuery(hql, String.class);
		query.setParameter("posicion", posicion);
		return query.getSingleResult();
	}

	public List<String> getClasificados() {
		String hqlTop = "SELECT e.nombre FROM Equipo e ORDER BY e.puntos DESC";
		Query<String> queryTop = session.createQuery(hqlTop, String.class);
		queryTop.setMaxResults(3);
		return queryTop.getResultList();
	}

	public List<String> getDescenso() {
		String hqlBottom = "SELECT e.nombre FROM Equipo e ORDER BY e.puntos ASC";
		Query<String> queryBottom = session.createQuery(hqlBottom, String.class);
		queryBottom.setMaxResults(3);
		return queryBottom.getResultList();
	}

	public Long contarDeportistasEnCompeticion(Competicion competicion) {
		String hql = "SELECT COUNT(j) FROM Jugador j JOIN j.equipo e WHERE :competicion MEMBER OF e.competiciones";
		Query<Long> query = session.createQuery(hql, Long.class);
		query.setParameter("competicion", competicion);
		return query.getSingleResult();
	}

	public List<String> ordenarPorContribucion(Equipo equipo) {
		String hql = "SELECT p.nombre " + "FROM Patrocinador p " + "JOIN p.equipos e " + "WHERE e = :equipo "
				+ "ORDER BY p.dineroOfrecido DESC";
		Query<String> query = session.createQuery(hql, String.class);
		query.setParameter("equipo", equipo);
		return query.getResultList();
	}
}
