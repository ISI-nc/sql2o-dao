package nc.isi.slq2o_dao.services;

import nc.isi.slq2o_dao.entities.Entity;

public interface EntityToDataSource {

	String provide(Class<? extends Entity> entityClass);

}
