package dao;

import java.util.Arrays;

import org.hibernate.Session;

import entity.Equipo;
import entity.Jugador;
import entity.Partido;
import entity.PartidoResults;
import utils.DaoProvider;
import utils.SimulationUtil;

/**
 * Represents a Data Access Object (DAO) for managing operations related to
 * Partido entity.
 * 
 * This class extends the GenericDAOImpl and provides additional methods
 * specific to Partido entities.
 * 
 * @author Sirpa_Jesus
 */
public class PartidoDAO extends GenericDAOImpl<Partido> {

	public PartidoDAO(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	public void setMatchResults(Equipo local, Equipo away, Partido partido, Session session, boolean winsLocal) {
		local.getPartidosLocal().add(partido);
		partido.setEquipoLocalEntity(local);
		away.getPartidosVisitante().add(partido);
		partido.setEquipoVisitanteEntity(away);

		Jugador jug = null;

		if (winsLocal) {
			jug = SimulationUtil.getRandomPlayer(local);
		} else {
			jug = SimulationUtil.getRandomPlayer(away);
		}
		jug.setCantidadMVP(jug.getCantidadMVP() + 1);
		partido.setMejorJugadorNombre(jug.getNombre());
		partido.setPartidoResult(new PartidoResults(winsLocal == true ? 1 : 0, winsLocal == false ? 1 : 0, partido));
		if (winsLocal)
			DaoProvider.getEquipoDAO(session).updatePuntos(local, local.getPuntos() + 3);
		else
			DaoProvider.getEquipoDAO(session).updatePuntos(away, away.getPuntos() + 3);
		DaoProvider.getEquipoDAO(session).updateAll(Arrays.asList(local, away));
		DaoProvider.getEquipoDAO(session).update(away);
		DaoProvider.getPartidoDAO(session).update(partido);
		DaoProvider.getJugadorDAO(session).update(jug);
	}

}
