package data_source;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import entity.Jugador;

public class JugadorDS {
	private static final List<Jugador> JUGADORES = Arrays.asList(
			// DPLUS KIA
			new Jugador("Kingen", "Korea", LocalDate.of(2000, 3, 11), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Lucid", "Korea", LocalDate.of(2005, 1, 28), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Showmaker", "Korea", LocalDate.of(2000, 7, 22), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Aiming", "Korea", LocalDate.of(2000, 7, 20), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Kellin", "Korea", LocalDate.of(2001, 2, 1), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			// DRX
			new Jugador("Rascal", "Korea", LocalDate.of(1997, 10, 16), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Sponge", "Korea", LocalDate.of(2004, 7, 25), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("SeTab", "Korea", LocalDate.of(2004, 10, 13), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Teddy", "Korea", LocalDate.of(1998, 3, 15), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Pleata", "Korea", LocalDate.of(2003, 3, 28), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			// FEARX
			new Jugador("Clear", "Korea", LocalDate.of(2002, 12, 19), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Willer", "Korea", LocalDate.of(2003, 4, 24), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Clozer", "Korea", LocalDate.of(2003, 7, 27), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Hena", "Korea", LocalDate.of(1999, 10, 8), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Execute", "Korea", LocalDate.of(2000, 2, 22), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Seobi", "Korea", LocalDate.of(2000, 1, 1), 0, LocalDate.of(2023, 1, 1), //
					LocalDate.of(2024, 1, 1)),
			// GEN-G
			new Jugador("Kiin", "Korea", LocalDate.of(1999, 5, 28), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Canyon", "Korea", LocalDate.of(2001, 6, 18), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Chovy", "Korea", LocalDate.of(2001, 3, 3), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Peyz", "Korea", LocalDate.of(2005, 12, 5), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Lehends", "Korea", LocalDate.of(1998, 12, 24), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			// Hanwha Life Esports
			new Jugador("Doran", "Korea", LocalDate.of(2000, 7, 22), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Peanut", "Korea", LocalDate.of(1998, 2, 3), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Zeka", "Korea", LocalDate.of(2002, 11, 28), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Viper", "Korea", LocalDate.of(2000, 10, 19), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Delight", "Korea", LocalDate.of(2002, 9, 12), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			// KT ROLSTERS
			new Jugador("PerfecT", "Korea", LocalDate.of(2004, 5, 17), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Pyosik", "Korea", LocalDate.of(2000, 3, 12), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Bdd", "Korea", LocalDate.of(1999, 3, 1), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Deft", "Korea", LocalDate.of(1996, 10, 23), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Beryl", "Korea", LocalDate.of(1997, 4, 1), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			// Kwangdong Freecs
			new Jugador("Dudu", "Korea", LocalDate.of(2001, 3, 8), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Cuzz", "Korea", LocalDate.of(1999, 10, 30), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("YoungJae", "Korea", LocalDate.of(2002, 11, 16), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Bull", "Korea", LocalDate.of(2005, 4, 15), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Andil", "Korea", LocalDate.of(2003, 5, 16), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("BuLLDoG", "Korea", LocalDate.of(2002, 10, 5), 0, LocalDate.of(2023, 1, 1), //
					LocalDate.of(2024, 1, 1)),
			// Nongshim RedForce
			new Jugador("DnDn", "Korea", LocalDate.of(2003, 5, 18), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Sylvie", "Korea", LocalDate.of(2002, 12, 4), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Callme", "Korea", LocalDate.of(2003, 5, 30), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("FIESTA", "Korea", LocalDate.of(2003, 5, 4), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Jiwoo", "Korea", LocalDate.of(2004, 3, 20), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Peter", "Korea", LocalDate.of(2003, 4, 28), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			// OK BRION
			new Jugador("Morgan", "Korea", LocalDate.of(2001, 9, 26), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("DDoiV", "Korea", LocalDate.of(2001, 10, 21), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Karis", "Korea", LocalDate.of(2003, 5, 21), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Envyy", "Korea", LocalDate.of(2000, 4, 7), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Effort", "Korea", LocalDate.of(2000, 11, 23), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			// SK-T1
			new Jugador("Zeus", "Korea", LocalDate.of(2004, 1, 31), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Oner", "Korea", LocalDate.of(2002, 12, 24), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1)),
			new Jugador("Faker", "Korea", LocalDate.of(1996, 5, 7), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2030, 1, 1)), // FAKER 2030
			new Jugador("Gumayusi", "Korea", LocalDate.of(2002, 2, 6), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2030, 1, 1)), // GUMA 2030
			new Jugador("Keria", "Korea", LocalDate.of(2002, 10, 14), 0, LocalDate.of(2023, 1, 1),
					LocalDate.of(2024, 1, 1))
			);

	public static Jugador getJugadorByName(String name) {
		for (Jugador j : JUGADORES) {
			if (j.getNombre().equalsIgnoreCase(name)) {
				return j;
			}
		}
		return null;
	}

	public static List<Jugador> getJugadores() {
		return JUGADORES;
	}
}
