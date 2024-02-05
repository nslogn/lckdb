package dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import utils.ReflectionUtil;

/**
 * Represents a generic implementation of the GenericDao interface, allowing
 * CRUD operations for entities of type T.
 * 
 * @param <T> the type of entity handled by this implementation.
 * @author Sirpa_Jesus
 */
@SuppressWarnings("unchecked")
public class GenericDAOImpl<T> implements GenericDao<T> {
	protected static Session session;
	protected Class<T> persistentClass = (Class<T>) ReflectionUtil
			.getTypeArguments(GenericDAOImpl.class, this.getClass()).get(0);

	public GenericDAOImpl(Session session_) {
		session = session_;
	}

	@Override
	public void save(T entity) {
		session.persist(entity);
	}

	@Override
	public void saveAll(Collection<T> entities) {
		for (T entity : entities) {
			save(entity);
		}
	}

	@Override
	public void update(T entity) {
		session.merge(entity);
	}

	@Override
	public void updateAll(Collection<T> entities) {
		for (T entity : entities) {
			update(entity);
		}
	}

	@Override
	public void delete(T entity) {
		session.remove(entity);
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
	}

	@Override
	public Optional<T> findById(Long id) {
		return Optional.ofNullable(session.find(persistentClass, id));
	}

	@Override
	public List<T> findAll() {
		String queryString = "SELECT _it_ FROM " + persistentClass.getSimpleName() + " _it_";
		return session.createQuery(queryString, persistentClass).getResultList();
	}

	public static void setSession(Session session_) {
		session = session_;
	}
}
