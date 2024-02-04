package dao;

import entity.Patrocinador;
import jakarta.persistence.EntityManager;

public class PatrocinadorDAO extends GenericDAOImpl<Patrocinador>{

	public PatrocinadorDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}


}
