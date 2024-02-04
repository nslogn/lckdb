package data_source;

import java.util.Arrays;
import java.util.List;

import entity.Patrocinador;

public class PatrocinadorDS {
	private static final List<Patrocinador> PATROCINADORES = Arrays.asList(
			new Patrocinador("Logitech G"),
			new Patrocinador("Twitch"),
			new Patrocinador("AfreecaTV"),
			new Patrocinador("HyperX"),
			new Patrocinador("HP OMEN"),
			new Patrocinador("Adidas"),
			new Patrocinador("Nike")
			);

	public static List<Patrocinador> getPatrocinador() {
		return PATROCINADORES;	
	}
}
