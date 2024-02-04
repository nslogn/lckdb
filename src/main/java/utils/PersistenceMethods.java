package utils;

import java.util.List;

import data_source.CompeticionDS;
import data_source.EquipoDS;
import data_source.JugadorDS;
import data_source.PatrocinadorDS;
import entity.Competicion;
import entity.Equipo;
import entity.Jugador;
import entity.Patrocinador;
import jakarta.persistence.EntityTransaction;

public class PersistenceMethods {

	public static void persistAllEntities() {
		EntityTransaction transaction = JPAUtils.getEntityManager().getTransaction();
		try {
			transaction.begin();
			saveAllPatrocinadores();
			saveAllJugadoresEquipos();
			saveAllCompeticiones();
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public static void saveAllPatrocinadores() {
		for (Patrocinador p : PatrocinadorDS.getPatrocinador())
			DaoProvider.getPatrocinadorDAO().save(p);
	}

	public static void saveAllCompeticiones() {
		for (Competicion p : CompeticionDS.getCompeticiones().values())
			DaoProvider.getCompeticionDAO().save(p);
	}

	public static void saveAllJugadoresEquipos() {
		List<Jugador> jugadores = JugadorDS.getJugadores();
		int indexPlayers = 0;
		int countPlayers = 0;
		for (Equipo equipo : EquipoDS.getEquipos()) {
			DaoProvider.getEquipoDAO().save(equipo);
			countPlayers = 0;
			while (indexPlayers < jugadores.size() && countPlayers < equipo.getNumeroJugadores()) {
				DaoProvider.getJugadorDAO().save(jugadores.get(indexPlayers));
				indexPlayers++;
				countPlayers++;
			}
		}
	}

	public static void saveIncorporaciones(Equipo equipo, List<Jugador> jugadoresIncorporados) {
		EntityTransaction incorporacionesTransaction = JPAUtils.getEntityManager().getTransaction();
		try {
			incorporacionesTransaction.begin();
			int numIncorporaciones = DaoProvider.getEquipoDAO().addNuevosJugadores(equipo, jugadoresIncorporados);
			equipo.setNumeroJugadores(equipo.getNumeroJugadores() + numIncorporaciones);
			DaoProvider.getJugadorDAO().saveAll(jugadoresIncorporados);
			DaoProvider.getEquipoDAO().update(equipo);
			incorporacionesTransaction.commit();
		} catch (Exception e) {
			if (incorporacionesTransaction.isActive()) {
				incorporacionesTransaction.rollback();
			}
			System.err.println("Error al realizar las nuevas incorporaciones :" + e.getMessage());
			System.err.println(jugadoresIncorporados);
			e.printStackTrace();
		}
	}

	public static void deleteJugadores(Equipo equipo, List<Jugador> jugadoresRetirados) {
		EntityTransaction retirosTransaction = JPAUtils.getEntityManager().getTransaction();
		try {
			retirosTransaction.begin();
			int numRetirados = DaoProvider.getEquipoDAO().removerJugadores(equipo, jugadoresRetirados);
			equipo.setNumeroJugadores(equipo.getNumeroJugadores() - numRetirados);
			DaoProvider.getJugadorDAO().deleteAll(jugadoresRetirados);
			DaoProvider.getEquipoDAO().update(equipo);
			retirosTransaction.commit();
		} catch (Exception e) {
			if (retirosTransaction.isActive()) {
				retirosTransaction.rollback();
			}
			System.err.println("Error al realizar las retiradas :" + e.getMessage());
			System.err.println(jugadoresRetirados);
			e.printStackTrace();
		}
	}
	
	public static void updateTraspaso(Jugador jugador, Equipo equipoDestino) {
		EntityTransaction traspasoTransaction = JPAUtils.getEntityManager().getTransaction();
		try {
			traspasoTransaction.begin();
			Equipo oldEquipo = jugador.getEquipo();
			DaoProvider.getJugadorDAO().updateTraspaso(jugador, equipoDestino);
			DaoProvider.getJugadorDAO().update(jugador);
			DaoProvider.getEquipoDAO().update(oldEquipo);
			DaoProvider.getEquipoDAO().update(equipoDestino);
			traspasoTransaction.commit();
		} catch (Exception e) {
			if (traspasoTransaction.isActive()) {
				traspasoTransaction.rollback();
			}
			System.err.println("Error al realizar el traspaso :" + e.getMessage());
			System.err.println(jugador.getNombre() + " -> " + equipoDestino.getNombre());
			e.printStackTrace();
		}
	}
}
