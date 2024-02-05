package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import data_source.CompeticionDS;
import data_source.EquipoDS;
import entity.Competicion;
import entity.Equipo;
import entity.Jugador;
import entity.Partido;
import entity.Patrocinador;
import utils.DaoProvider;
import utils.HibernateUtils;
import utils.SimulationUtil;

/**
 * Wrapper class for making the necessary queries to the database
 * 
 * @author Sirpa_Jesus
 */
public class ConsultasCompeticion {
	public static void realizarConsultas() {
		// CONSULTAS
		Session consSession = HibernateUtils.getSessionFactory().openSession();

		Equipo drx = EquipoDS.getEquipoByName("DRX");
		Equipo t1 = EquipoDS.getEquipoByName("T1");

//		1.Utiliza una consulta nativa(NativeQuery) para obtener las características de la competición.
		System.out.println("\n==  Obtener las características de la competición ==");
		Competicion lckData = DaoProvider.getCompeticionDAO(consSession)
				.getCompetitionNativeQuery(CompeticionDS.getCompeticionByName("LCK").getId());
		System.out.println(lckData.toString().toUpperCase());
		System.out.println("\n========== FIN CONSULTA 1 ==========");
		
//		2. Consulta y recupera todos los equipos participantes en la competición.
		System.out.println("\n==  recupera todos los equipos participantes en la competición ==");
		List<Equipo> equiposLCK = DaoProvider.getEquipoDAO(consSession).getEquiposByCompeticion(lckData);
		for (Equipo e : equiposLCK)
			System.out.println(e.getNombre());
		System.out.println("\n========== FIN CONSULTA 2 ==========");
		
//		3. Obtén la lista de todos los deportistas de un equipo específico.
		System.out.println("\n==  Obtén la lista de todos los deportistas de un equipo específico (DRX) ==");
		List<Jugador> jugadoresDRX = DaoProvider.getEquipoDAO(consSession).getJugadoresByEquipo(drx);
		for (Jugador j : jugadoresDRX)
			System.out.println(j.getNombre());
		System.out.println("\n========== FIN CONSULTA 3 ==========");
		
//		4. Identifica y lista todos los patrocinadores asociados a un equipo concreto.
		System.out.println("\n==  Identifica y lista todos los patrocinadores asociados a un equipo concreto (DRX) ==");
		List<Patrocinador> patrocinadoresDRX = DaoProvider.getPatrocinadorDAO(consSession)
				.getPatrocinadoresByEquipo(drx);
		for (Patrocinador p : patrocinadoresDRX)
			System.out.println(p.getNombre());
		System.out.println("\n========== FIN CONSULTA 4 ==========");
		
//		5.Genera una lista de deportistas y patrocinadores vinculados a un equipo específico.
		System.out.println(
				"\n==  Genera una lista de deportistas y patrocinadores vinculados a un equipo específico (DRX) ==");
		List<Object> listPatrocinadoresJugador = new ArrayList<>();
		listPatrocinadoresJugador.addAll(jugadoresDRX);
		listPatrocinadoresJugador.addAll(patrocinadoresDRX);
		for (Object o : listPatrocinadoresJugador) {
			if (o instanceof Jugador)
				System.out.print("- Jugador : " + ((Jugador) o).getNombre());
			else if (o instanceof Patrocinador)
				System.out.print("- Patrocinador :" + ((Patrocinador) o).getNombre());
		}
		System.out.println("\n========== FIN CONSULTA 5 ==========");
		
//		6.Calcula y presenta la edad promedio de los deportistas de un equipo determinado.
		System.out.println("\n==  Calcula y presenta la edad promedio de los deportistas de un equipo determinado (DRX) ==");
		Double edadPromedioDRX = DaoProvider.getJugadorDAO(consSession).getEdadPromedio(drx);
		System.out.println(edadPromedioDRX);
		System.out.println("\n========== JUGADORES EDAD PROMEDIO (T1) ==========");
		Double edadPromedioT1 = DaoProvider.getJugadorDAO(consSession).getEdadPromedio(drx);
		System.out.println(edadPromedioT1);
		System.out.println("\n========== FIN CONSULTA 6 ==========");
		
//		7.Cuenta cuantos deportistas tienen más de veintitrés años en la competición agrupados por nacionalidad.
		System.out.println(
				"\n==  Cuenta cuantos deportistas tienen más de veintitrés años en la competición agrupados por nacionalidad  ==");
		List<Object[]> playerCountData = DaoProvider.getJugadorDAO(consSession).getJugadoresMayores();
		for (Object[] row : playerCountData) {
			String nationality = (String) row[0];
			Long count = (Long) row[1];
			System.out.println(nationality + ": " + count);
		}
		System.out.println("\n========== FIN CONSULTA 7 ==========");
		
//		8.Visualiza la clasificación al inicio, a mitad de temporada y al final de esta.
		System.out.println("\n========== JORNADAS 1 ==========");
		SimulationUtil.printClasificacion(SimulationUtil.getSavedClasiInicial());
		System.out.println("\n========== JORNADAS 5 ==========");
		SimulationUtil.printClasificacion(SimulationUtil.getSavedClasiMitad());
		System.out.println("\n========== JORNADAS 9 ==========");
		SimulationUtil.printClasificacion(SimulationUtil.getSavedClasiFinal());
		System.out.println("\n========== FIN CONSULTA 8 ==========");
		
//		9.Determina y muestra los tres equipos con más puntos y los tres con menos.
		System.out.println("\n==  tres equipos con más puntos  ==");
		for (String s : DaoProvider.getEquipoDAO().getClasificados())
			System.out.println(s);
		System.out.println("\n========== tres equipos con menos puntos ==========");
		for (String s : DaoProvider.getEquipoDAO().getDescenso())
			System.out.println(s);
		System.out.println("\n========== FIN CONSULTA 9 ==========");
		
//		10.Muestra las nuevas incorporaciones a la competición(utiliza una NamedQuery).
		System.out.println("\n========== INCORPORACIONES PARA TEMPORADA 2025 ==========");
		List<Jugador> incorporaciones = DaoProvider.getJugadorDAO(consSession)
				.findPlayersSignedAfter(LocalDate.of(2024, 1, 12));
		for (Jugador j : incorporaciones)
			System.out.println(j.getNombre());
		System.out.println("\n========== FIN CONSULTA 10 ==========");
		
//		11.Enumera todos los fichajes realizados entre los diferentes equipos.
		System.out.println("\n========== FIN CONSULTA 11 ==========");
		
//		12.Realiza un recuento del total de deportistas que participan en la competición.
		System.out.println("\n==  recuento del total de deportistas que participan en la competición  ==");
		System.out.println("Total: " + DaoProvider.getEquipoDAO().contarDeportistasEnCompeticion(lckData));
		System.out.println("\n========== FIN CONSULTA 12 ==========");
		
//		13.Dado dos equipos muestra sus patrocinadores comunes.
		System.out.println("\n========== PATROCINADORES COMUNES DE T1 Y DRX ==========");
		/**
		 * Equipo t1 = EquipoDS.getEquipoByName("T1"); Equipo drx =
		 * EquipoDS.getEquipoByName("DRX"); for (Patrocinador p :
		 * DaoProvider.getEquipoDAO(consSession).getPatrocinadoresComunes(t1, drx))
		 * System.out.println(p.getNombre());
		 **/
//		14.Utiliza CriteriaQuery para poder filtrar por todos los atributos de los deportistas, edad, nombre, equipo, 
//		etc ordenados por un criterio. Lanza tres ejemplos distintos con diferentes atributos, uno debe incluir todos 
//		los atributos y el resto solo una parte de ellos. Ejemplo: Dame la lista de deportistas, que tenga X edad 
//		, pertenezcan al equipo Y ordenados por nombre.
		System.out.println("\n==  JUGADORES FILTRADOS POR EDAD (23) Y ORDENADOS POR NOMBRE  ==");
		List<Jugador> jugadoresEdad = DaoProvider.getJugadorDAO(consSession).getJugadorPorEdad(23);
		for (Jugador j : jugadoresEdad)
			System.out.println(j.getNombre() + ", " + j.getFechaDeNacimiento().toString());
		System.out.println("\n==  JUGADORES FILTRADOS POR EQUIPO (T1) Y ORDENADOS POR EDAD  ==");
		List<Jugador> jugadoresEquipo = DaoProvider.getJugadorDAO(consSession).getJugadorPorEquipo(t1);
		for (Jugador j : jugadoresEquipo)
			System.out.println(j.getNombre() + ", " + j.getEquipo().getNombre() + ", FECHA NACIMIENTO:"
					+ j.getFechaDeNacimiento());
		System.out.println("\n========== FIN CONSULTA 14 ==========");
		
		// 1.Enumera la clasificación final de los equipos y la remuneración obtenida
		// por cada uno según su posición.
		System.out.println("\n==  CLASIFICACIÓN  ==");
		int m = 1;
		for (String s : DaoProvider.getEquipoDAO(consSession).getClasificacion()) {
			System.out.println(m++ + "- " + s);
			Equipo e = EquipoDS.getEquipoByName(s.split(" - ")[0]);
			System.out.println("Budget : " + e.getPresupuesto());
//			DaoProvider.getEquipoDAO(consSession).updateBudgetClasificacion(e, m);
		}
		System.out.println("\n==  REMUNARCIÓN OBTENIDA  ==");
		m = 0;
		for (String s : DaoProvider.getEquipoDAO(consSession).getClasificacion()) {
			System.out.println(m++ + "- " + s + ", Budget : "
					+ DaoProvider.getEquipoDAO(consSession).getBudget(s.split(" - ")[0].toString()));
		}
		System.out.println("\n========== FIN CONSULTA OPCIONAL 1 ==========");
		// 2.Identifica al deportista que más frecuentemente ha sido nombrado BestPlayer
		// en la competición, optando por aquel de un equipo peor clasificado en caso de
		// empate entre varios deportistas.
		System.out.println("\n==  JUGADOR CON MÁS MVPs  ==");
		Jugador jugadorMVP = DaoProvider.getJugadorDAO(consSession).getJugadoresMVP();
		System.out.println(jugadorMVP.getNombre() + ", MVPs: " + jugadorMVP.getCantidadMVP());
		System.out.println("\n========== FIN CONSULTA OPCIONAL 2 ==========");
		// 3.Ordena y presenta los patrocinadores de un equipo según la magnitud de su
		// contribución financiera, de mayor a menor.
		System.out.println("\n========== PATROCINADORES ORDENADOS POR APORTACIÓN A T1 ==========");
		System.out.println(DaoProvider.getEquipoDAO(consSession).ordenarPorContribucion(t1));
		System.out.println("\n========== PATROCINADORES ORDENADOS POR APORTACIÓN A DRX ==========");
		System.out.println(DaoProvider.getEquipoDAO(consSession).ordenarPorContribucion(drx));
		System.out.println("\n========== FIN CONSULTA OPCIONAL 3 ==========");
//		4. Para un equipo específico, reporta el resultado de su partido en la tercera jornada de la competición,
//		incluyendo todas las estadísticas relevantes de ese encuentro.
		System.out.println("\n========== JORNADA 3 DE T1 ==========");
		List<Partido> partidos3 = DaoProvider.getPartidoDAO(consSession).getPartidoJornada3(t1.getNombre());
		for (Partido p : partidos3) {
			System.out.println(p.getJornada() + "- " + p.getEquipoLocal() + " VS " + p.getEquipoVisitante());
			System.out.println(p.getPartidoResult().getGolesLocal() + " : " + p.getPartidoResult().getGolesVisitante());
		}
		System.out.println("\n========== FIN CONSULTA OPCIONAL 4 ==========");
//		5. Determina qué equipo ha generado la mayor cantidad de ingresos y calcula el total acumulado de dichos ingresos.
//		System.out.println("\n========== FIN CONSULTA OPCIONAL 5 ==========");
//		6. Muestra al mejor jugador de cada enfrentamiento junto a qué equipo pertenece y contra 
//		que equipo se enfrentó, de cada jornada de la competición.
		System.out.println("\n========== PARTIDOS Y MEJOR JUGADOR ==========");
		List<Partido> partidosAll = DaoProvider.getPartidoDAO(consSession).findAll();
		for (Partido p : partidosAll) {
			System.out.println("MVP : " + p.getMejorJugadorNombre() + ", " + p.getJornada() + " - "
					+ p.getEquipoLocalEntity().getNombre() + " VS " + p.getEquipoVisitanteEntity().getNombre());
		}
		System.out.println("\n========== FIN CONSULTA OPCIONAL 6 ==========");
	}
}
