package utils;

import org.hibernate.Session;

import dao.CompeticionDAO;
import dao.EquipoDAO;
import dao.JugadorDAO;
import dao.PartidoDAO;
import dao.PartidoResultsDAO;
import dao.PatrocinadorDAO;

/**
 * Provides access to Data Access Objects (DAOs) for managing various entities.
 * 
 * Note: Instances of this class are typically used to access DAO instances
 * throughout the application.
 * 
 * @author Sirpa_Jesus
 */
public class DaoProvider {
	private static JugadorDAO jugadorDAO;
	private static EquipoDAO equipoDAO;
	private static PatrocinadorDAO patrocinadorDAO;
	private static CompeticionDAO competicionDAO;
	private static PartidoDAO partidoDAO;
	private static PartidoResultsDAO partidoResultsDAO;
	protected static final Session sessionInicial;
	protected static Session currentSession;

	static {
		sessionInicial = HibernateUtils.getSessionFactory().openSession();
		jugadorDAO = new JugadorDAO(sessionInicial);
		equipoDAO = new EquipoDAO(sessionInicial);
		patrocinadorDAO = new PatrocinadorDAO(sessionInicial);
		competicionDAO = new CompeticionDAO(sessionInicial);
		partidoDAO = new PartidoDAO(sessionInicial);
		partidoResultsDAO = new PartidoResultsDAO(sessionInicial);
	}

	public static JugadorDAO getJugadorDAO(Session session) {
		JugadorDAO.setSession(session);
		return jugadorDAO;
	}

	public static EquipoDAO getEquipoDAO(Session session) {
		EquipoDAO.setSession(session);
		return equipoDAO;
	}

	public static PatrocinadorDAO getPatrocinadorDAO(Session session) {
		PatrocinadorDAO.setSession(session);
		return patrocinadorDAO;
	}

	public static CompeticionDAO getCompeticionDAO(Session session) {
		CompeticionDAO.setSession(session);
		return competicionDAO;
	}

	public static PartidoDAO getPartidoDAO(Session session) {
		PartidoDAO.setSession(session);
		return partidoDAO;
	}

	public static PartidoResultsDAO getPartidoResultsDAO(Session session) {
		PartidoResultsDAO.setSession(session);
		return partidoResultsDAO;
	}

	public static JugadorDAO getJugadorDAO() {
		return jugadorDAO;
	}

	public static EquipoDAO getEquipoDAO() {
		return equipoDAO;
	}

	public static PatrocinadorDAO getPatrocinadorDAO() {
		return patrocinadorDAO;
	}

	public static CompeticionDAO getCompeticionDAO() {
		return competicionDAO;
	}

	public static PartidoDAO getPartidoDAO() {
		return partidoDAO;
	}

	public static PartidoResultsDAO getPartidoResultsDAO() {
		return partidoResultsDAO;
	}
}
