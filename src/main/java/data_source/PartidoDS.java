package data_source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Partido;

public class PartidoDS {
	private static final Map<Integer, List<Partido>> PARTIDOS_MAP = new HashMap<>();
	private static final List<Partido> PARTIDOS = Arrays.asList(
			// J1
			new Partido("KT Rolster", "Hanwha Life"), new Partido("Dplus KIA", "FearX"),
			new Partido("Kwangdong Freecs", "DRX"), new Partido("OK BRION", "NS RedForce"),
			new Partido("KT Rolster", "Gen.G"), new Partido("T1", "Hanwha Life"), new Partido("FearX", "NS RedForce"),
			new Partido("Kwangdong Freecs", "OK BRION"), new Partido("DRX", "Dplus KIA"), new Partido("Gen.G", "T1"),
			// J2
			new Partido("Hanwha Life", "DRX"), new Partido("Gen.G", "Kwangdong Freecs"), new Partido("T1", "FearX"),
			new Partido("Dplus KIA", "OK BRION"), new Partido("NS RedForce", "Gen.G"), new Partido("DRX", "KT Rolster"),
			new Partido("Hanwha Life", "Kwangdong Freecs"), new Partido("OK BRION", "T1"),
			new Partido("FearX", "KT Rolster"), new Partido("NS RedForce", "Dplus KIA"),
			// J3
			new Partido("FearX", "OK BRION"), new Partido("Kwangdong Freecs", "T1"), new Partido("Gen.G", "DRX"),
			new Partido("KT Rolster", "Dplus KIA"), new Partido("Kwangdong Freecs", "NS RedForce"),
			new Partido("Hanwha Life", "FearX"), new Partido("Dplus KIA", "Gen.G"), new Partido("T1", "KT Rolster"),
			new Partido("NS RedForce", "DRX"), new Partido("OK BRION", "Hanwha Life"),
			// J4
			new Partido("Hanwha Life", "Dplus KIA"), new Partido("KT Rolster", "Kwangdong Freecs"),
			new Partido("Gen.G", "OK BRION"), new Partido("DRX", "FearX"), new Partido("KT Rolster", "NS RedForce"),
			new Partido("T1", "Dplus KIA"), new Partido("OK BRION", "DRX"), new Partido("FearX", "Kwangdong Freecs"),
			new Partido("Gen.G", "Hanwha Life"), new Partido("T1", "NS RedForce"),
			// J5
			new Partido("Kwangdong Freecs", "Dplus KIA"), new Partido("DRX", "T1"),
			new Partido("NS RedForce", "Hanwha Life"), new Partido("FearX", "Gen.G"),
			new Partido("OK BRION", "KT Rolster"), new Partido("Dplus KIA", "DRX"), new Partido("T1", "Gen.G"),
			new Partido("NS RedForce", "FearX"), new Partido("KT Rolster", "OK BRION"),
			new Partido("Kwangdong Freecs", "Hanwha Life"),
			// J6
			new Partido("FearX", "Dplus KIA"), new Partido("Gen.G", "NS RedForce"),
			new Partido("Hanwha Life", "KT Rolster"), new Partido("OK BRION", "Kwangdong Freecs"),
			new Partido("T1", "NS RedForce"), new Partido("DRX", "Gen.G"), new Partido("Kwangdong Freecs", "FearX"),
			new Partido("Hanwha Life", "OK BRION"), new Partido("Dplus KIA", "T1"), new Partido("KT Rolster", "DRX"),
			// J7
			new Partido("Dplus KIA", "KT Rolster"), new Partido("FearX", "Hanwha Life"),
			new Partido("Kwangdong Freecs", "Gen.G"), new Partido("DRX", "NS RedForce"),
			new Partido("OK BRION", "Dplus KIA"), new Partido("Hanwha Life", "T1"), new Partido("Gen.G", "KT Rolster"),
			new Partido("NS RedForce", "Kwangdong Freecs"), new Partido("T1", "OK BRION"), new Partido("FearX", "DRX"),
			// J8
			new Partido("Gen.G", "FearX"), new Partido("Kwangdong Freecs", "KT Rolster"), new Partido("T1", "DRX"),
			new Partido("Hanwha Life", "NS RedForce"), new Partido("Dplus KIA", "Kwangdong Freecs"),
			new Partido("OK BRION", "FearX"), new Partido("KT Rolster", "T1"), new Partido("DRX", "Hanwha Life"),
			new Partido("NS RedForce", "OK BRION"), new Partido("Gen.G", "Dplus KIA"),
			// J9
			new Partido("T1", "Kwangdong Freecs"), new Partido("Dplus KIA", "NS RedForce"),
			new Partido("DRX", "OK BRION"), new Partido("Hanwha Life", "Gen.G"), new Partido("FearX", "T1"),
			new Partido("NS RedForce", "KT Rolster"), new Partido("Dplus KIA", "Hanwha Life"),
			new Partido("OK BRION", "Gen.G"), new Partido("DRX", "Kwangdong Freecs"),
			new Partido("KT Rolster", "FearX"));

	static {
		int i = 0;
		int jornada = 1;
		while (i < PARTIDOS.size()) {
			List<Partido> partidos = new ArrayList<>();
			for (int j = 0; j < 10 && i < PARTIDOS.size(); j++, i++) {
				partidos.add(PARTIDOS.get(i));
			}
			PARTIDOS_MAP.put(jornada++, partidos);
		}
	}

	public static Map<Integer, List<Partido>> getPartidosMap() {
		return PARTIDOS_MAP;
	}

	public static List<Partido> getPartidosByJornada(Integer i) {
		return PARTIDOS_MAP.get(i);
	}

	public static List<Partido> getPartidos() {
		return PARTIDOS;
	}
}
