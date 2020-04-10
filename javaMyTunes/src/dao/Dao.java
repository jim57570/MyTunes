package dao;

import java.util.List;

/**
 * Interface destinée à l'application métier.
 * Découple la couche logique (métier) de la couche technoloqique (JPA ou autre)
 * @author jim
 *
 */
public interface Dao<T> {
	T get(int id);
	T get(String nom);
	void save(T o);
	void remove(T o);
	List<T> loadAll();
}
