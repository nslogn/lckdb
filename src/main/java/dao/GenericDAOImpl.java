package dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import utils.ReflectionUtil;

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
//		session.remove(session.contains(entity) ? entity : session.merge(entity));
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

	@SuppressWarnings("deprecation")
	@Override
	public List<T> findAll() {
		return session.createQuery("select _it_ from" + persistentClass.getSimpleName() + " _it_").getResultList();
	}
	
	public static void setSession(Session session_) {
		session = session_;
	}
}
