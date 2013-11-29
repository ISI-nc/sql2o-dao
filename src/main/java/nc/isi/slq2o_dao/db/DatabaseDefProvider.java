package nc.isi.slq2o_dao.db;

import nc.isi.slq2o_dao.entities.Entity;

public interface DatabaseDefProvider {

	DatabaseDef getDatabaseDef(Class<? extends Entity> entityClass);

}
