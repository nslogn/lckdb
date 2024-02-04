package dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {

	void save(T entity);

	void saveAll(Collection<T> entities);

	void update(T entity);

	void delete(T entity);

	void deleteAll(Collection<T> entities);

	Optional<T> findById(Long id);

	List<T> findAll();
}
