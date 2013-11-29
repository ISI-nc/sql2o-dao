package nc.isi.slq2o_dao.services;

import org.jooq.DSLContext;

public interface SqlDSLProvider {

	DSLContext provide(String key);

}
