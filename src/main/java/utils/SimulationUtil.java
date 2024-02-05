package utils;

import java.util.List;
import java.util.Random;

import org.hibernate.Session;

import data_source.EquipoDS;
import data_source.PartidoDS;
import entity.Equipo;
import entity.Jugador;
import entity.Partido;
import main.ConsultasCompeticion;
import main.DataSourceToDataBase;

/**
 * Provides utility methods for simulating league competitions and managing data
 * operations.
 * 
 * Usage: Provides methods for simulating league matches, managing player
 * transfers, and performing data operations.
 * 
 * @author Sirpa_Jesus
 */
public class SimulationUtil {
	private static final Random rand = new Random();
	private static List<String> clasificInicial = null;
	private static List<String> clasificMitad = null;
	private static List<String> clasificFinal = null;

	public static void getClasificaion() {
		Integer j = 1;
		for (String s : DaoProvider.getEquipoDAO().getClasificacion()) {
			System.out.println(j + "- " + s);
			int sepIndex = s.indexOf('-');
			Equipo equipo = EquipoDS.getEquipoByName(s.substring(0, sepIndex).trim());
			DaoProvider.getEquipoDAO().updatePosicionActual(equipo, j);
			j++;
		}
	}

	public static void playJornada(int numJornada, Session jornadaSession) {
		List<Partido> partidos = PartidoDS.getPartidosByJornada(numJornada);
		for (Partido p : partidos) {
			Equipo e1 = EquipoDS.getEquipoByName(p.getEquipoLocal());
			Equipo e2 = EquipoDS.getEquipoByName(p.getEquipoVisitante());
			p.setJornada(numJornada);
			playPartido(e1, e2, p, jornadaSession);
		}
	}

	private static void playPartido(Equipo e1, Equipo e2, Partido partido, Session session) {
		if (rand.nextBoolean()) {
			DaoProvider.getPartidoDAO().setMatchResults(e1, e2, partido, session, true);
		} else {
			DaoProvider.getPartidoDAO().setMatchResults(e1, e2, partido, session, false);
		}
	}

	public static Jugador getRandomPlayer(Equipo e1) {
		int size = e1.getJugadores().size();
		int randomIndex = rand.nextInt(size);
		return e1.getJugadores().toArray(new Jugador[size])[randomIndex];
	}

	public static void realizarCargaDeDatos() {
		DataSourceToDataBase.setEntitiesRelations();
		PersistenceMethods.persistAllEntities();
	}

	public static void realizarIncorporaciones(Equipo equipo, List<Jugador> jugadoresIncorporacion) {
		PersistenceMethods.saveIncorporaciones(equipo, jugadoresIncorporacion);
	}

	public static void realizarRetiradas(Equipo equipo, List<Jugador> jugadoresRetirados) {
		PersistenceMethods.deleteJugadores(equipo, jugadoresRetirados);
	}

	public static void realizarTrasapaso(Jugador jugador1, Equipo equipoDestion) {
		PersistenceMethods.updateTraspaso(jugador1, equipoDestion);
	}

	public static void realizarComprobacionDoble(Equipo eq1, Equipo eq2, String tipo) {
		realizarComprobacion(eq1, tipo);
		realizarComprobacion(eq2, tipo);
	}

	public static void realizarComprobacion(Equipo equipo1, String tipo) {
		System.out.println("\n" + tipo + " - " + equipo1.getNombre());
		for (Jugador j : equipo1.getJugadores()) {
			System.out.println(equipo1.getNombre() + " - " + j.getNombre());
		}
	}

	public static void realizarConsultas() {
		ConsultasCompeticion.realizarConsultas();
	}

	public static void saveClasificacion(int i) {
		if (i == 1) {
			clasificInicial = DaoProvider.getEquipoDAO().getClasificacion();
		} else if (i == 5) {
			clasificMitad = DaoProvider.getEquipoDAO().getClasificacion();
		} else if (i == 9) {
			clasificFinal = DaoProvider.getEquipoDAO().getClasificacion();
		}
	}

	public static List<String> getSavedClasiInicial() {
		return clasificInicial;
	}

	public static List<String> getSavedClasiMitad() {
		return clasificMitad;
	}

	public static List<String> getSavedClasiFinal() {
		return clasificFinal;
	}

	public static void printClasificacion(List<String> clasificacion) {
		int j = 1;
		for (String s : clasificacion) {
			System.out.println(j + "- " + s);
			j++;
		}
	}
}
