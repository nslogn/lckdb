package dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * A generic interface for Data Access Objects (DAOs) providing CRUD operations
 * for entities of type T.
 *
 * @author Sirpa_Jesus
 */
public interface GenericDao<T> {

	void save(T entity);

	void saveAll(Collection<T> entities);

	void update(T entity);

	void updateAll(Collection<T> entities);

	void delete(T entity);

	void deleteAll(Collection<T> entities);

	Optional<T> findById(Long id);

	List<T> findAll();
}
