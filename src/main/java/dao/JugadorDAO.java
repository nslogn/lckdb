package dao;

import entity.Equipo;
import entity.Jugador;
import jakarta.persistence.EntityManager;

public class JugadorDAO extends GenericDAOImpl<Jugador> {

	public JugadorDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	public void updateTraspaso(Jugador jugador, Equipo equipoDestino) {
		Equipo oldTeam = jugador.getEquipo();
		jugador.setEquipo(equipoDestino);
		oldTeam.getJugadores().remove(jugador);
		oldTeam.setNumeroJugadores(oldTeam.getNumeroJugadores() - 1);
		equipoDestino.getJugadores().add(jugador);
		equipoDestino.setNumeroJugadores(equipoDestino.getNumeroJugadores() + 1);
	}
}
