package dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import utils.ReflectionUtil;

@SuppressWarnings("unchecked")
public class GenericDAOImpl<T> implements GenericDao<T> {
	protected final EntityManager entityManager;
	protected Class<T> persistentClass = (Class<T>) ReflectionUtil
			.getTypeArguments(GenericDAOImpl.class, this.getClass()).get(0);

	public GenericDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void save(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void saveAll(Collection<T> entities) {
		for (T entity : entities) {
			save(entity);
		}
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public void deleteAll(Collection<T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
	}

	@Override
	public Optional<T> findById(Long id) {
		return Optional.ofNullable(entityManager.find(persistentClass, id));
	}

	@Override
	public List<T> findAll() {
		return entityManager.createQuery("select _it_ from" + persistentClass.getSimpleName() + " _it_")
				.getResultList();
	}
}
