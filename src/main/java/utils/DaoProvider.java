package utils;

import dao.CompeticionDAO;
import dao.EquipoDAO;
import dao.JugadorDAO;
import dao.PatrocinadorDAO;
import jakarta.persistence.EntityManager;

public class DaoProvider {
	private static final JugadorDAO jugadorDAO;
    private static final EquipoDAO equipoDAO;
    private static final PatrocinadorDAO patrocinadorDAO;
    private static final CompeticionDAO competicionDAO;

    static {
        EntityManager entityManager = JPAUtils.getEntityManager();
        jugadorDAO = new JugadorDAO(entityManager);
        equipoDAO = new EquipoDAO(entityManager);
        patrocinadorDAO = new PatrocinadorDAO(entityManager);
        competicionDAO = new CompeticionDAO(entityManager);
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
}
