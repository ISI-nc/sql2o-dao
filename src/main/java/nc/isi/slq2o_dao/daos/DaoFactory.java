package nc.isi.slq2o_dao.daos;

import nc.isi.slq2o_dao.entities.Entity;

public interface DaoFactory {

	<T extends Entity> Dao<T> get(Class<T> tClass);

}
