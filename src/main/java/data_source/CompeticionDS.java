package data_source;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entity.Competicion;

/**
 * Provides access to competition information stored in a static map. Allows
 * retrieval of start dates, duration, and team count.
 * 
 * @author Sirpa_Jesus
 */
public class CompeticionDS {
	private static final Map<String, Competicion> COMPETICION = new HashMap<>();

	static {
		COMPETICION.put("LCK", new Competicion("LCK", LocalDate.of(2024, 1, 17), 9, 10));
		COMPETICION.put("Playoffs", new Competicion("Playoffs", LocalDate.of(2024, 3, 24), 4, 6));
		COMPETICION.put("LCK Summer", new Competicion("LCK Summer", LocalDate.of(2024, 5, 1), 9, 10));
		COMPETICION.put("LCK Summer Playoffs", new Competicion("LCK Summer Playoffs", LocalDate.of(2024, 8, 24), 4, 6));
	}

	public static Competicion getCompeticionByName(String competicionName) {
		return COMPETICION.get(competicionName);
	}

	public static Map<String, Competicion> getCompeticiones() {
		return COMPETICION;
	}
}
