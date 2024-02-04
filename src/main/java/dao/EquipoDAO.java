package dao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.TypedQuery;
import entity.Equipo;
import entity.Jugador;
import jakarta.persistence.EntityManager;

public class EquipoDAO extends GenericDAOImpl<Equipo> {

	public EquipoDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	public List<String> getClasificacion() {
		String jpql = "SELECT UPPER(e.nombre), e.puntos FROM Equipo e ORDER BY e.puntos DESC";
	    TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
	    List<Object[]> resultList = query.getResultList();

	    List<String> clasificacion = new ArrayList<>();
	    for (Object[] row : resultList) {
	        String nombre = (String) row[0];
	        int puntos = (int) row[1];
	        String equipoString = String.format("%s - Puntos: %d", nombre, puntos);
	        clasificacion.add(equipoString);
	    }
	    return clasificacion;
	}

	public void updatePuntos(Equipo equipo, Integer nuevosPuntos) {
		equipo.setPuntos(nuevosPuntos);
		update(equipo);
	}
	
	public int addNuevosJugadores(Equipo equipo, List<Jugador> jugadores) {
		int count = 0;
		for (Jugador j :jugadores) {
			j.setEquipo(equipo);
			equipo.getJugadores().add(j);
			count++;
		}
		return count;
	}
	
	public int removerJugadores(Equipo equipo, List<Jugador> jugadores) {
		int count = 0;
		for (Jugador j :jugadores) {
			entityManager.detach(j);
			equipo.getJugadores().remove(j);
			count++;
		}
		return count;
	}
}
