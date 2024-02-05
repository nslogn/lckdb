package main;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import data_source.CompeticionDS;
import data_source.EquipoDS;
import entity.Competicion;
import entity.Equipo;
import entity.Jugador;
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
//		1.Utiliza una consulta nativa(NativeQuery) para obtener las características de la competición.
		System.out.println("\n========== DATOS COMPETICION ==========");
		Competicion lckData = DaoProvider.getCompeticionDAO(consSession)
				.getCompetitionNativeQuery(CompeticionDS.getCompeticionByName("LCK").getId());
		System.out.println(lckData.toString().toUpperCase());
//		2. Consulta y recupera todos los equipos participantes en la competición.
		System.out.println("\n========== LCK EQUIPOS ==========");
		List<Equipo> equiposLCK = DaoProvider.getEquipoDAO(consSession).getEquiposByCompeticion(lckData);
		for (Equipo e : equiposLCK)
			System.out.println(e.getNombre());
//		3. Obtén la lista de todos los deportistas de un equipo específico.
		System.out.println("\n========== DRX TEAM ==========");
		List<Jugador> jugadoresDRX = DaoProvider.getEquipoDAO(consSession)
				.getJugadoresByEquipo(EquipoDS.getEquipoByName("DRX"));
		for (Jugador j : jugadoresDRX)
			System.out.println(j.getNombre());
//		4. Identifica y lista todos los patrocinadores asociados a un equipo concreto.
		System.out.println("\n========== DRX PATROCINADORES ==========");
		List<Patrocinador> patrocinadoresDRX = DaoProvider.getPatrocinadorDAO(consSession)
				.getPatrocinadoresByEquipo(EquipoDS.getEquipoByName("DRX"));
		for (Patrocinador p : patrocinadoresDRX)
			System.out.println(p.getNombre());
//		5.Genera una lista de deportistas y patrocinadores vinculados a un equipo específico.

//		6.Calcula y presenta la edad promedio de los deportistas de un equipo determinado.
		System.out.println("\n========== JUGADORES EDAD PROMEDIO ==========");
		Double edadPromedio = DaoProvider.getJugadorDAO(consSession)
				.getEdadPromedio(EquipoDS.getEquipoByName("OK BRION"));
		System.out.println(edadPromedio);
//		7.Cuenta cuantos deportistas tienen más de veintitrés años en la competición agrupados por nacionalidad.
		System.out.println("\n========== JUGADORES > 23 GROUPED BY COUNTRY ==========");
		List<Object[]> playerCountData = DaoProvider.getJugadorDAO(consSession).countPlayersOver23ByNationality();
		for (Object[] row : playerCountData) {
			String nationality = (String) row[0];
			Long count = (Long) row[1];
			System.out.println(nationality + ": " + count);
		}
//		8.Visualiza la clasificación al inicio, a mitad de temporada y al final de esta.
		System.out.println("\n========== JORNADAS 1 ==========");
		SimulationUtil.printClasificacion(SimulationUtil.getSavedClasiInicial());
		System.out.println("\n========== JORNADAS 5 ==========");
		SimulationUtil.printClasificacion(SimulationUtil.getSavedClasiMitad());
		System.out.println("\n========== JORNADAS 9 ==========");
		SimulationUtil.printClasificacion(SimulationUtil.getSavedClasiFinal());
//		9.Determina y muestra los tres equipos con más puntos y los tres con menos.
		System.out.println("\n========== CLASIFICACIÓN : LIDERES  ==========");
		for (String s : DaoProvider.getEquipoDAO().getClasificados())
			System.out.println(s);
		System.out.println("\n========== CLASIFICACIÓN : COLISTAS ==========");
		for (String s : DaoProvider.getEquipoDAO().getDescenso())
			System.out.println(s);
//		10.Muestra las nuevas incorporaciones a la competición(utiliza una NamedQuery).
		System.out.println("\n========== INCORPORACIONES PARA TEMPORADA 2025 ==========");
		List<Jugador> incorporaciones = DaoProvider.getJugadorDAO(consSession)
				.findPlayersSignedAfter(LocalDate.of(2024, 1, 12));
		for (Jugador j : incorporaciones)
			System.out.println(j.getNombre());
//		11.Enumera todos los fichajes realizados entre los diferentes equipos.
		System.out.println("\n========== INCORPORACIONES PARA TEMPORADA 2025 ==========");
		List<String> clasificados = DaoProvider.getEquipoDAO().getClasificados();
		List<String> descendidos = DaoProvider.getEquipoDAO().getDescenso();
		for (int i = 1; i <= 3; i++)
			System.out.println("Clasificado " + i + "- " + clasificados.get(i - 1) + ", Descendido " + i + "- "
					+ descendidos.get(i - 1));
//		12.Realiza un recuento del total de deportistas que participan en la competición.
		System.out.println("\n========== DEPORTISTAS EN LA LCK ==========");
		System.out.println(DaoProvider.getEquipoDAO().contarDeportistasEnCompeticion(lckData));
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
//		los atributos y el resto solo una parte de ellos. Ejemplo: Dame la lista de deportistas, que tenga X edad , pertenezcan al equipo Y ordenados por nombre.

		// 1.Enumera la clasificación final de los equipos y la remuneración obtenida
		// por cada uno según su posición.

		// 2.Identifica al deportista que más frecuentemente ha sido nombrado BestPlayer
		// en la competición, optando por aquel de un equipo peor clasificado en caso de
		// empate entre varios deportistas.

		// 3.Ordena y presenta los patrocinadores de un equipo según la magnitud de su
		// contribución financiera, de mayor a menor.
		System.out.println("\n========== PATROCINADORES APORTACIÓN A T1==========");
		Equipo t1 = EquipoDS.getEquipoByName("T1");
		System.out.println(DaoProvider.getEquipoDAO().ordenarPorContribucion(t1));

	}
}
