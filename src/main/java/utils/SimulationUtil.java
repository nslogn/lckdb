package utils;

import java.util.List;
import java.util.Random;

import data_source.EquipoDS;
import data_source.PartidoDS;
import entity.Equipo;
import entity.Jugador;
import entity.Partido;
import main.DataSourceToDataBase;

public class SimulationUtil {
	private static final Random rand = new Random();

	public static void getClasificaion() {
		int j = 1;
		for (String s : DaoProvider.getEquipoDAO().getClasificacion()) {
			System.out.println(j + "- " + s);
			j++;
		}
	}

	public static void playJornada(int numJornada) {
		List<Partido> partidos = PartidoDS.getPartidosByJornada(numJornada);
		for (Partido p : partidos) {
			Equipo e1 = EquipoDS.getEquipoByName(p.getLocal());
			Equipo e2 = EquipoDS.getEquipoByName(p.getVisitante());
			playPartido(e1, e2);
		}
	}

	private static void playPartido(Equipo e1, Equipo e2) {
		if (rand.nextBoolean()) {
			DaoProvider.getEquipoDAO().updatePuntos(e1, e1.getPuntos() + 3);
		} else {
			DaoProvider.getEquipoDAO().updatePuntos(e2, e2.getPuntos() + 3);
		}
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
}
