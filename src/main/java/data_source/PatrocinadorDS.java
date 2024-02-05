package data_source;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import entity.Patrocinador;

public class PatrocinadorDS {
	private static final List<Patrocinador> PATROCINADORES = Arrays.asList(
			new Patrocinador("Logitech G", new BigDecimal(1000)),
			new Patrocinador("Twitch", new BigDecimal(2000)),
			new Patrocinador("AfreecaTV",new BigDecimal(3000)),
			new Patrocinador("HyperX", new BigDecimal(4000)),
			new Patrocinador("HP OMEN",new BigDecimal(5000)),
			new Patrocinador("Adidas", new BigDecimal(6000)),
			new Patrocinador("Nike", new BigDecimal(7000))
			);

	public static List<Patrocinador> getPatrocinador() {
		return PATROCINADORES;	
	}
}
