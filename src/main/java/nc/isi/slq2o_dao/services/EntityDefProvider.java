package nc.isi.slq2o_dao.services;

import nc.isi.slq2o_dao.entities.Entity;
import nc.isi.slq2o_dao.entities.EntityDef;

public interface EntityDefProvider {
	<T extends Entity> EntityDef<T> provide(Class<T> entityClass);

}
