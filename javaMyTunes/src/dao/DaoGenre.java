package dao;

import java.util.List;

import objMetiers.Genre;

/**
 * DAO pour les objets métiers Genre
 * DaoGenre n'utilise pas l'interface Dao<T> !
 * Car il n'est pas nécessaire d'effectuer des modifications sur la liste des genres
 * @author jim
 *
 */
public interface DaoGenre {
	Genre get(int id);
	Genre get(String nom);
	List<Genre> loadAll();
}
