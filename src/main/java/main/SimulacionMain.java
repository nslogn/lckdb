package main;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import data_source.EquipoDS;
import data_source.JugadorDS;
import entity.Equipo;
import entity.Jugador;
import utils.HibernateUtils;
import utils.SimulationUtil;

/**
 * Class for executing the simulation. This class tries to recreate the
 * simulation of the matches for the season 2024
 * 
 * @author Sirpa_Jesus
 */
public class SimulacionMain {
	public static void main(String[] args) {
		SimulationUtil.realizarCargaDeDatos();

		// LCK Simulation START
		SimulationUtil.getClasificaion();
		try (Session jornadasSession = HibernateUtils.getSessionFactory().openSession()) {
			for (int i = 1; i <= 9; i++) {
				try {
					Transaction jornadasTransaction = jornadasSession.beginTransaction();
					SimulationUtil.playJornada(i, jornadasSession);
					jornadasTransaction.commit();
				} catch (Exception e) {
					System.err.println("Error durante la Jornada " + i + ": " + e.getMessage());
					e.printStackTrace();
					break;
				}
				System.out.println("\nJornada - " + i);
				SimulationUtil.getClasificaion();
				SimulationUtil.saveClasificacion(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

		SimulationUtil.realizarConsultas();

		HibernateUtils.shutdown();
	}
}
