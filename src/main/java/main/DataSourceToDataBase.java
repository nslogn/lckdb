package main;

import java.util.Random;

import data_source.CompeticionDS;
import data_source.EntrenadorDS;
import data_source.EquipoDS;
import data_source.JugadorDS;
import data_source.PatrocinadorDS;
import entity.Competicion;
import entity.Entrenador;
import entity.Equipo;
import entity.Jugador;
import entity.Patrocinador;

public class DataSourceToDataBase {
	private static final Random rand = new Random();
	private static final String COMPETITION_NAME = "LCK";
	private static Integer playerIndexFromDS;

	public static void setEntitiesRelations() {
		playerIndexFromDS = 0;
		for (Equipo equipo : EquipoDS.getEquipos()) {
			setEquipoCompeticion(equipo, COMPETITION_NAME);
			setEquipoEntrenador(equipo);
			setEquipoPatrocinadores(equipo);
			setEquipoJugadores(equipo);
		}
	}

	private static void setEquipoJugadores(Equipo equipo) {
		int countPlayers = 0;
		while (playerIndexFromDS < JugadorDS.getJugadores().size() && countPlayers < equipo.getNumeroJugadores()) {
			Jugador jugador = JugadorDS.getJugadores().get(playerIndexFromDS);
			jugador.setEquipo(equipo);
			equipo.getJugadores().add(jugador);
			countPlayers++;
			playerIndexFromDS++;
		}
	}

	private static void setEquipoPatrocinadores(Equipo equipo) {
		for (Patrocinador patrocinador : PatrocinadorDS.getPatrocinador()) {// TODO: Randomized sponsors
			if (rand.nextBoolean()) {
				equipo.getPatrocinadores().add(patrocinador);
				if (!patrocinador.getEquipos().contains(equipo))
					patrocinador.getEquipos().add(equipo);
			}
		}
	}

	private static void setEquipoEntrenador(Equipo equipo) {
		Entrenador entrenador = EntrenadorDS.getEntrenadorByTeamName(equipo.getNombre());
		entrenador.setEquipo(equipo);
		equipo.setEntrenador(entrenador);
	}

	private static void setEquipoCompeticion(Equipo equipo, String competitionName) {
		Competicion competicion = CompeticionDS.getCompeticionByName(competitionName);
		equipo.getCompeticiones().add(competicion);
		competicion.getEquipos().add(equipo);
	}
}
