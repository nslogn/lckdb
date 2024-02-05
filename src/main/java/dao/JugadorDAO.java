package dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.Equipo;
import entity.Jugador;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

/**
 * Represents a Data Access Object (DAO) for managing operations related to
 * Jugador entity.
 * 
 * This class extends the GenericDAOImpl and provides additional methods
 * specific to jugador entities.
 * 
 * @author Sirpa_Jesus
 */
public class JugadorDAO extends GenericDAOImpl<Jugador> {

	public JugadorDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public void updateTraspaso(Jugador jugador, Equipo equipoDestino) {
		Equipo oldTeam = jugador.getEquipo();
		oldTeam.getJugadores().remove(jugador);
		oldTeam.setNumeroJugadores(oldTeam.getNumeroJugadores() - 1);
		jugador.setEquipo(equipoDestino);
		equipoDestino.getJugadores().add(jugador);
		equipoDestino.setNumeroJugadores(equipoDestino.getNumeroJugadores() + 1);
	}

	public List<Object[]> getJugadoresMayores() {
		String hql = "SELECT j.nacionalidad, COUNT(j) FROM Jugador j "
				+ "WHERE YEAR(CURRENT_DATE) - YEAR(j.fechaDeNacimiento) > 23 " + "GROUP BY j.nacionalidad";
		Query<Object[]> query = session.createQuery(hql, Object[].class);
		return query.getResultList();
	}

	public Double getEdadPromedio(Equipo equipo) {
		String hql = "SELECT AVG(YEAR(CURRENT_DATE) - YEAR(j.fechaDeNacimiento)) "
				+ "FROM Jugador j WHERE j.equipo = :equipo";
		Query<Double> query = session.createQuery(hql, Double.class);
		query.setParameter("equipo", equipo);
		return query.uniqueResult();
	}

	public List<Jugador> findPlayersSignedAfter(LocalDate startDate) {
		TypedQuery<Jugador> query = session.createNamedQuery("Jugador.findPlayersSignedAfter", Jugador.class);
		query.setParameter("startDate", startDate);
		List<Jugador> players = query.getResultList();
		return players;
	}

	public List<Jugador> getJugadorPorEdad(int desiredAge) {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Jugador> criteriaQuery = criteriaBuilder.createQuery(Jugador.class);
		Root<Jugador> root = criteriaQuery.from(Jugador.class);
		LocalDate desiredBirthdate = LocalDate.now().minusYears(desiredAge);
		Date desiredBirthdateAsDate = Date
				.from(desiredBirthdate.atStartOfDay(TimeZone.getDefault().toZoneId()).toInstant());

		Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("fechaDeNacimiento"), desiredBirthdateAsDate);
		criteriaQuery.select(root).where(predicate);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nombre")));
		return session.createQuery(criteriaQuery).getResultList();
	}

	public List<Jugador> getJugadorPorEquipo(Equipo desiredTeam) {
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Jugador> criteriaQuery = criteriaBuilder.createQuery(Jugador.class);
		Root<Jugador> root = criteriaQuery.from(Jugador.class);

		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("equipo"), desiredTeam))
				.orderBy(criteriaBuilder.asc(root.get("fechaDeNacimiento")));
		return session.createQuery(criteriaQuery).getResultList();
	}

	public Jugador getJugadoresMVP() {
		String hql = "SELECT j FROM Jugador j ORDER BY j.cantidadMVP DESC";

		Query<Jugador> query = session.createQuery(hql, Jugador.class);
		query.setMaxResults(1);
		return query.getSingleResult();
	}

}
