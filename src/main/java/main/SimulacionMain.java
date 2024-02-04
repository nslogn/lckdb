package main;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import data_source.CompeticionDS;
import data_source.EquipoDS;
import data_source.JugadorDS;
import entity.Competicion;
import entity.Equipo;
import entity.Jugador;
import jakarta.persistence.EntityTransaction;
import utils.DaoProvider;
import utils.JPAUtils;
import utils.SimulationUtil;

public class SimulacionMain {
	public static void main(String[] args) {
		SimulationUtil.realizarCargaDeDatos();
		
		// LCK Simulation START
		Optional<Competicion> optional = DaoProvider.getCompeticionDAO()
				.findById(CompeticionDS.getCompeticionByName("LCK").getId());
		System.out.println(optional.get().toString().toUpperCase() + "\n");

		SimulationUtil.getClasificaion();
		EntityTransaction jornadasTransaction = JPAUtils.getEntityManager().getTransaction();
		for (int i = 1; i <= 9; i++) {
			try {
				jornadasTransaction.begin();
				SimulationUtil.playJornada(i);
				jornadasTransaction.commit();
			} catch (Exception e) {
				if (jornadasTransaction.isActive()) {
					jornadasTransaction.rollback();
				}
				System.err.println("Error durante la Jornada " + i + ": " + e.getMessage());
				e.printStackTrace();
				break;
			}
			System.out.println("\nJornada - " + i);
			SimulationUtil.getClasificaion();
		}

		// CAMBIOS FIN DE TEMPORADA
		Equipo t1 = EquipoDS.getEquipoByName("T1");
		Equipo brion = EquipoDS.getEquipoByName("OK BRION");
		// INCORPORACIONES
		System.out.println("\n================== INCORPORACIONES ==================");
		SimulationUtil.realizarComprobacion(t1, "PRE INCORPORACIONES");

		List<Jugador> jugadoresIncorporacion = Arrays.asList(
				new Jugador("Kevin", "Francia", LocalDate.of(2001, 1, 1), 0, LocalDate.of(2025, 1, 1),
						LocalDate.of(2026, 1, 1)),
				new Jugador("Nashor", "España", LocalDate.of(1990, 1, 1), 0, LocalDate.of(2025, 1, 1),
						LocalDate.of(2026, 1, 1)));
		SimulationUtil.realizarIncorporaciones(t1, jugadoresIncorporacion);

		SimulationUtil.realizarComprobacion(t1, "POST INCORPORACIONES");

		// RETIRADAS
		System.out.println("\n================== RETIRADAS ==================");
		SimulationUtil.realizarComprobacion(brion, "PRE RETIRADAS");

		List<Jugador> jugadoresRetirados = Arrays.asList(brion.getJugadores().toArray(new Jugador[1])[0],
				brion.getJugadores().toArray(new Jugador[1])[1]);
		for (Jugador j : jugadoresRetirados) {
			System.out.println("Jugador Retirado: " + j.getNombre() + ", Equipo: " + j.getEquipo().getNombre());
		}
		SimulationUtil.realizarRetiradas(brion, jugadoresRetirados);

		SimulationUtil.realizarComprobacion(brion, "POST RETIRADAS");

		// CAMBIOS ENTRE EQUIPOS
		System.out.println("\n================== CAMBIOS ==================");
		SimulationUtil.realizarComprobacionDoble(t1, brion, "PRE CAMBIOS");

		Jugador traspaso = JugadorDS.getJugadorByName("Faker");
		SimulationUtil.realizarTrasapaso(traspaso, brion);

		SimulationUtil.realizarComprobacionDoble(t1, brion, "POST CAMBIOS");

		JPAUtils.close();
	}
}
