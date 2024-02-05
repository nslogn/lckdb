package data_source;

import java.util.HashMap;
import java.util.Map;

import entity.Entrenador;

/**
 * Provides access to coach information stored in a static map. Allows retrieval
 * of coaches by team name.
 * 
 * @author Sirpa_Jesus
 */
public class EntrenadorDS {
	private static final Map<String, Entrenador> ENTRENADORES = new HashMap<>();

	static {
		ENTRENADORES.put("Dplus KIA", new Entrenador("Zefa"));
		ENTRENADORES.put("DRX", new Entrenador("Micro"));
		ENTRENADORES.put("FearX", new Entrenador("Ryu"));
		ENTRENADORES.put("Gen.G", new Entrenador("Mata"));
		ENTRENADORES.put("Hanwha Life", new Entrenador("DanDy"));
		ENTRENADORES.put("KT Rolster", new Entrenador("Hirai"));
		ENTRENADORES.put("Kwangdong Freecs", new Entrenador("cvMax"));
		ENTRENADORES.put("NS RedForce", new Entrenador("Irean"));
		ENTRENADORES.put("OK BRION", new Entrenador("Edgar"));
		ENTRENADORES.put("T1", new Entrenador("Kkoma"));
	}

	public static Entrenador getEntrenadorByTeamName(String teamName) {
		return ENTRENADORES.get(teamName);
	}

	public static Map<String, Entrenador> getEntrenadores() {
		return ENTRENADORES;
	}
}
