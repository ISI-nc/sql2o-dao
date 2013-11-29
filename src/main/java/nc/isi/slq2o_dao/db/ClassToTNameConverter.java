package nc.isi.slq2o_dao.db;

import nc.isi.slq2o_dao.entities.Entity;

public interface ClassToTNameConverter {

	/**
	 * 
	 * @param cl
	 *            : une classe étendant {@link Entity}
	 * @return le nom de la table
	 */
	String convert(Class<? extends Entity> entityClass);

}
