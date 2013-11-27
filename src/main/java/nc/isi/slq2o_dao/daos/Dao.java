package nc.isi.slq2o_dao.daos;

import java.util.Collection;

import nc.isi.slq2o_dao.entities.Entity;

public interface Dao<T extends Entity> {

	/**
	 * @return {@link Collection} contenant tous les éléments de type T
	 */
	Collection<T> getAll();

	/**
	 * 
	 * @param id
	 * @return l'élément T identifié par id
	 */
	T get(String id);

	/**
	 * 
	 * @param object
	 *            l'élément à enregistré dans la base
	 */
	void insert(T object);

}