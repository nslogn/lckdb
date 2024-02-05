package utils;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import data_source.CompeticionDS;
import data_source.EquipoDS;
import data_source.JugadorDS;
import data_source.PatrocinadorDS;
import entity.Competicion;
import entity.Equipo;
import entity.Jugador;
import entity.Patrocinador;

/**
 * Provides static methods for persisting entities into the database.
 * 
 * Usage: Provides methods for persisting various entity types into the
 * database.
 * 
 * Note: Each use cases and managed by a single transaction. "A single unit of work"
 * 
 * @author Sirpa_Jesus
 */
public class PersistenceMethods {

	public static void persistAllEntities() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtils.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			saveAllPatrocinadores(session);
			saveAllJugadoresEquipos(session);
			saveAllCompeticiones(session);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public static void saveAllPatrocinadores(Session session) {
		for (Patrocinador p : PatrocinadorDS.getPatrocinador()) {
			DaoProvider.getPatrocinadorDAO(session).save(p);
		}
	}

	public static void saveAllCompeticiones(Session session) {
		for (Competicion p : CompeticionDS.getCompeticiones().values()) {
			DaoProvider.getCompeticionDAO(session).save(p);
			;
		}
	}

	public static void saveAllPartidos(Session session) {

	}

	public static void saveAllJugadoresEquipos(Session session) {
		List<Jugador> jugadores = JugadorDS.getJugadores();
		int indexPlayers = 0;
		int countPlayers = 0;
		for (Equipo equipo : EquipoDS.getEquipos()) {
			DaoProvider.getEquipoDAO(session).save(equipo);
			countPlayers = 0;
			while (indexPlayers < jugadores.size() && countPlayers < equipo.getNumeroJugadores()) {
				DaoProvider.getJugadorDAO(session).save(jugadores.get(indexPlayers));
				indexPlayers++;
				countPlayers++;
			}
		}
	}

	public static void saveIncorporaciones(Equipo equipo, List<Jugador> jugadoresIncorporados) {
		Session incorporacionesTransaction = null;
		Transaction transaction = null;
		try {
			incorporacionesTransaction = HibernateUtils.getSessionFactory().openSession();
			transaction = incorporacionesTransaction.beginTransaction();
			int numIncorporaciones = DaoProvider.getEquipoDAO(incorporacionesTransaction).addNuevosJugadores(equipo,
					jugadoresIncorporados);
			equipo.setNumeroJugadores(equipo.getNumeroJugadores() + numIncorporaciones);
			DaoProvider.getJugadorDAO(incorporacionesTransaction).saveAll(jugadoresIncorporados);
			DaoProvider.getEquipoDAO(incorporacionesTransaction).update(equipo);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			System.err.println("Error al realizar las nuevas incorporaciones :" + e.getMessage());
			System.err.println(jugadoresIncorporados);
			e.printStackTrace();
		} finally {
			if (incorporacionesTransaction != null && incorporacionesTransaction.isOpen()) {
				incorporacionesTransaction.close();
			}
		}
	}

	public static void deleteJugadores(Equipo equipo, List<Jugador> jugadoresRetirados) {
		Session retirosSession = null;
		Transaction retirosTransaction = null;
		try {
			retirosSession = HibernateUtils.getSessionFactory().openSession();
			retirosTransaction = retirosSession.beginTransaction();
			int numRetirados = DaoProvider.getEquipoDAO(retirosSession).removerJugadores(equipo, jugadoresRetirados);
			equipo.setNumeroJugadores(equipo.getNumeroJugadores() - numRetirados);
			DaoProvider.getJugadorDAO(retirosSession).deleteAll(jugadoresRetirados);
			DaoProvider.getEquipoDAO(retirosSession).update(equipo);

			retirosTransaction.commit();
		} catch (Exception e) {
			if (retirosTransaction != null && retirosTransaction.isActive()) {
				retirosTransaction.rollback();
			}
			System.err.println("Error al realizar las retiradas :" + e.getMessage());
			System.err.println(jugadoresRetirados);
			e.printStackTrace();
		} finally {
			if (retirosSession != null && retirosSession.isOpen()) {
				retirosSession.close();
			}
		}
	}

	public static void updateTraspaso(Jugador jugador, Equipo equipoDestino) {
		Session traspasoSession = null;
		Transaction traspasoTransaction = null;
		try {
			traspasoSession = HibernateUtils.getSessionFactory().openSession();
			traspasoTransaction = traspasoSession.beginTransaction();
			Equipo oldEquipo = jugador.getEquipo();
			DaoProvider.getJugadorDAO(traspasoSession).updateTraspaso(jugador, equipoDestino);
			DaoProvider.getEquipoDAO(traspasoSession).update(oldEquipo);
			DaoProvider.getJugadorDAO(traspasoSession).update(jugador);
//			DaoProvider.getEquipoDAO(traspasoSession).update(equipoDestino);

			traspasoTransaction.commit();
		} catch (Exception e) {
			if (traspasoTransaction != null && traspasoTransaction.isActive()) {
				traspasoTransaction.rollback();
			}
			System.err.println("Error al realizar el traspaso :" + e.getMessage());
			System.err.println(jugador.getNombre() + " -> " + equipoDestino.getNombre());
			e.printStackTrace();
		} finally {
			if (traspasoSession != null && traspasoSession.isOpen()) {
				traspasoSession.close();
			}
		}
	}
}
