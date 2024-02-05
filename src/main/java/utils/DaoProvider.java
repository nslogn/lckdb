package utils;

import org.hibernate.Session;

import dao.CompeticionDAO;
import dao.EquipoDAO;
import dao.JugadorDAO;
import dao.PartidoDAO;
import dao.PartidoResultsDAO;
import dao.PatrocinadorDAO;

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
//		if (session.equals(sessionInicial))
//			return jugadorDAO;
//		else if (session.equals(currentSession))
//			return jugadorDAO;
//		currentSession = session;
//		return new JugadorDAO(session);
//		currentSession = session;
		JugadorDAO.setSession(session);
		return jugadorDAO;
	}

	public static EquipoDAO getEquipoDAO(Session session) {
//		if (session.equals(sessionInicial))
//			return equipoDAO;
//		else if (session.equals(currentSession))
//			return equipoDAO;
//		currentSession = session;
//		return new EquipoDAO(currentSession);
		EquipoDAO.setSession(session);
		return equipoDAO;
	}

	public static PatrocinadorDAO getPatrocinadorDAO(Session session) {
//		if (session.equals(sessionInicial))
//			return patrocinadorDAO;
//		currentSession = session;
//		return new PatrocinadorDAO(currentSession);
		PatrocinadorDAO.setSession(session);
		return patrocinadorDAO;
	}

	public static CompeticionDAO getCompeticionDAO(Session session) {
//		if (session.equals(sessionInicial))
//			return competicionDAO;
//		else if (session.equals(currentSession))
//			return competicionDAO;
//		currentSession = session;
//		return new CompeticionDAO(currentSession);
		CompeticionDAO.setSession(session);
		return competicionDAO;
	}

	public static PartidoDAO getPartidoDAO(Session session) {
//		if (session.equals(sessionInicial))
//			return partidoDAO;
//		else if (session.equals(currentSession))
//			return partidoDAO;
//		currentSession = session;
//		return new PartidoDAO(currentSession);
		PartidoDAO.setSession(session);
		return partidoDAO;
	}

	public static PartidoResultsDAO getPartidoResultsDAO(Session session) {
//		if (session.equals(sessionInicial))
//			return partidoResultsDAO;
//		else if (session.equals(currentSession))
//			return partidoResultsDAO;
//		currentSession = session;
//		return new PartidoResultsDAO(currentSession);
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
