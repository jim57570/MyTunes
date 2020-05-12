package dao;

import java.util.List;

import objMetiers.Chanson;

/**
 * DAO pour les objets métiers Chanson
 * @author jim
 *
 */
public interface DaoChanson extends Dao<Chanson> {
	List<Chanson> find(String nom); 
}
