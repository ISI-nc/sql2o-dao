package nc.isi.slq2o_dao.daos;

import nc.isi.slq2o_dao.entities.BaseEntity;

public interface DaoFactory {

	<T extends BaseEntity> Dao<T> get(Class<T> tClass);

}
